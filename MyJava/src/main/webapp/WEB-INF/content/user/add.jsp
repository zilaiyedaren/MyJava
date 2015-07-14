<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加用户</title>
 

  </head>

  <body>
  
   <form action="<%=request.getContextPath()%>/user/add.html" method="post">
   	姓名：<input name="username"><br/>
   	密码：<input type="password" name="password"><br/>
    角色：<input type="text" name="role" value="${tusers.role}"><br/>
	  <input type="submit" value="提交">
   </form>
  </body>
</html>
