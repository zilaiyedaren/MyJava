<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  
   <form action="<%=request.getContextPath()%>/user/update/${tusers.id}.html" method="post">
   <input type="hidden" name="id" value="${tusers.id}">
   	姓名：<input name="username" value="${tusers.username}"><br/>
   	密码：<input type="password" name="password" value="${tusers.password}"><br/> 
   	角色：<input type="text" name="role" value="${tusers.role}"><br/>
	<input type="submit" value="提交">
   </form>
  </body>
</html>
