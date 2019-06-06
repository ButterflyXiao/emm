<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();//销毁当前会话对象
	//response.sendRedirect("login.html");//重定向到登陆页
%>

<script>
	//在最外层框架重定向
	window.top.location.href="login.html";
</script>