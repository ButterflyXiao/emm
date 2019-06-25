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
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		//�����������
		response.setHeader("Access-Control-Allow-Origin", "*");	
		
		
		//��¼��֤
		String uri = request.getRequestURI();
		System.out.println("����uri��" + uri);
		
		//webӦ������
		String app = request.getContextPath();
		
		
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
					response.setHeader("sessionStatus", "timeout");	//��Ӧǰ��һ���Զ��屨ͷ��Ϣ					
				} else {
					response.sendRedirect(app + "/logout.jsp"); //�ض��򵽵�¼ҳ
				}
				
				return;
			}			
			
		}		
								
		
		//��������ִ��ûд
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
				
	}

}