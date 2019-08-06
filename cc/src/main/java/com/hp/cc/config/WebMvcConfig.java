package com.hp.cc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 配置fastjson消息转化器
 * 
 * Spring-boot2.0以上在集成swagger后，配置WebConfig时不要extends WebMvcConfigurationSupport，需要修改为最新的implements WebMvcConfigurer，不然访问http://localhost:8080/swagger-ui.html时读取不到swagger-ui的静态资源文件。
 * 
 * @author ck
 * @date 2019年2月27日 下午4:40:57
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	/** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    
    
    @Bean
    public FastJsonConfig fastJsonConfig(){
    	
    	// 配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
    	 // 时间格式化 不然返回时间戳
        fastJsonConfig.setDateFormat(DEFAULT_DATE_TIME_FORMAT); 
        
        // 过滤返回结果  参看下面注释
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,//用来关闭引用检测
                SerializerFeature.WriteMapNullValue,  // 输出值为 null 的字段
                SerializerFeature.WriteNullStringAsEmpty, // null 输出为"",而非null
                SerializerFeature.WriteNullListAsEmpty    // List 为 []
        );
        /**
         * fastJson配置类fastJsonConfig 调用setSerializerFeatures方法配置过滤选择
         * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * WriteNullBooleanAsFalse：Boolean字段为null,输出为false,而非null
         * WriteNullStringAsEmpty ：字符类型为null,输出为"",而非null
         * WriteNullListAsEmpty  ：List字段为null,输出为[],而非null
         * WriteMapNullValue      ：是否输出值为null的字段,默认为false。
         */    
        return fastJsonConfig;
    }
    
    /**
     * 配置fastjson消息转化器
     */
    @Override
	public void configureMessageConverters(
    		List<HttpMessageConverter<?>> converters) {
    	 // fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        //这里可以添加多种MediaType
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        
        fastConverter.setFastJsonConfig(fastJsonConfig());
        converters.add(6,fastConverter);
        
        converters.forEach(System.out::println);
        
      
    }
	
}
