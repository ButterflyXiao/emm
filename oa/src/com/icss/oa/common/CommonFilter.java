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
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		//设置允许跨域
		response.setHeader("Access-Control-Allow-Origin", "*");	
		
		
		//登录验证
		String uri = request.getRequestURI();
		System.out.println("请求uri：" + uri);
		
		//web应用名称
		String app = request.getContextPath();
		
		
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
					response.setHeader("sessionStatus", "timeout");	//响应前端一个自定义报头信息					
				} else {
					response.sendRedirect(app + "/logout.jsp"); //重定向到登录页
				}
				
				return;
			}			
			
		}		
								
		
		//继续向下执行没写
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
				
	}

}