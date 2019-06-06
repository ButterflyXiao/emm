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
 * ����������
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
		
		//���������������
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", "*");	
				
		/***************��¼����******************/
		//����uri
		String uri = request.getRequestURI();
		System.out.println("����uri��" + uri);
		
		//webӦ������
		String app = request.getContextPath();
		System.out.println(app);
		
		
		//�ж��Ƿ��ǲ���Ҫ��¼��֤��uri		
		if (!uri.equals(app + "/") 
				&& !uri.equals(app + "/login.html")
				&& !uri.equals(app + "/logout.jsp")
				&& !uri.equals(app + "/emp/login")
				&& !uri.startsWith(app + "/css")
				&& !uri.startsWith(app + "/js")
				&& !uri.startsWith(app + "/images")) {
			
			//��¼�ж�
			HttpSession session = request.getSession();			
			String empLoginName = (String) session.getAttribute("empLoginName");
			
			if (empLoginName == null) {
				
				//�ж��Ƿ���ajax����
				String xhr = request.getHeader("x-requested-with");
				
				if (xhr != null && xhr.equals("XMLHttpRequest")) {
					//��Ӧǰ��һ���Զ��屨ͷ��Ϣ
					response.setHeader("sessionStatus", "timeout");						
				} else {
					response.sendRedirect(app + "/login.html"); //�ض��򵽵�½ҳ
				}				
								
				return;
			}			
		}
				
		
		//��������ִ��
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
}