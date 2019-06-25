<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	session.invalidate(); //销毁当前的session对象
	//response.sendRedirect("login.html");//重定向到登录页
%>
<script>
	//在最外层窗口重定性
	window.top.location.href = 'login.html'	
</script>