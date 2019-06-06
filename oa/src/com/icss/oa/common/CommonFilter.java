package com.icss.oa.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 公共过滤器
 * @author Administrator
 *
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	@Override
	public void destroy() {
				
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//设置允许跨域请求
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", "*");	
				
		/***************登录拦截******************/
		//请求uri
		String uri = request.getRequestURI();
		System.out.println("请求uri：" + uri);
		
		//web应用名称
		String app = request.getContextPath();
		System.out.println(app);
		
		
		//判断是否是不需要登录验证的uri		
		if (!uri.equals(app + "/") 
				&& !uri.equals(app + "/login.html")
				&& !uri.equals(app + "/logout.jsp")
				&& !uri.equals(app + "/emp/login")
				&& !uri.startsWith(app + "/css")
				&& !uri.startsWith(app + "/js")
				&& !uri.startsWith(app + "/images")) {
			
			//登录判断
			HttpSession session = request.getSession();			
			String empLoginName = (String) session.getAttribute("empLoginName");
			
			if (empLoginName == null) {
				
				//判断是否是ajax请求
				String xhr = request.getHeader("x-requested-with");
				
				if (xhr != null && xhr.equals("XMLHttpRequest")) {
					//响应前端一个自定义报头信息
					response.setHeader("sessionStatus", "timeout");						
				} else {
					response.sendRedirect(app + "/login.html"); //重定向到登陆页
				}				
								
				return;
			}			
		}
				
		
		//继续向下执行
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
}