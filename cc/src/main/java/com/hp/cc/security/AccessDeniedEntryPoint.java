package com.hp.cc.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hp.cc.common.Result;

/**
 * 访问时，没有权限处理
 * 
 * @author ck
 * @date 2019年6月19日 下午4:53:15
 */
@Component
public class AccessDeniedEntryPoint implements AccessDeniedHandler,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(Result.createByErrorCodeMessage(HttpServletResponse.SC_FORBIDDEN, "Access Denieddd")));
	}

}
