package com.hp.cc.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * mysql 代码生成器
 * 
 * @author wachen
 *
 */
public class MysqlGenerator {

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String DB = "cc";
	public static final String DB_HOST = "localhost";
	public static final int DB_PORT = 3306;
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "111111";

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		String BASE_BACKAGE = MysqlGenerator.class.getCanonicalName().substring(0,
				MysqlGenerator.class.getCanonicalName().lastIndexOf(".generator.MysqlGenerator"));
		System.out.println(BASE_BACKAGE);

		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setAuthor("cc");
		gc.setOutputDir(PROJECT_PATH + "/src/main/java");
		gc.setOpen(false);
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUrl(String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&useSSL=false&characterEncoding=utf8", DB_HOST,
				DB_PORT, DB));
		dsc.setUsername(DB_USER);
		dsc.setPassword(DB_PASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(BASE_BACKAGE);
		pc.setModuleName(null);
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {

			@Override
			public void initMap() {
				// TODO Auto-generated method stub
			}
		};

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {

			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return PROJECT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
						+ StringPool.DOT_XML;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(BASE_BACKAGE + ".entity.BaseEntity");
		strategy.setEntityLombokModel(true);
		strategy.setSuperControllerClass(BASE_BACKAGE + ".controller.BaseController");
		strategy.setInclude(scanner("表名"));
		strategy.setSuperEntityColumns("id", "create_time", "update_time", "created_by", "updated_by");
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_" + "");
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
