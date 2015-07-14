<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
          <base href="<%=basePath%>">
      <style type="text/css">
          html,body{padding: 0;margin: 0;background: #fff;}
          #table1{width: 1000px;position: relative;top:300px;}
          .header{width: 100%;height:60px;background: #3083eb;}
          .nav{width: 900px;margin: 0 auto;text-align: right;height: 60px;line-height: 60px;}
          .nav a{text-decoration: none;color: #fff;margin: 0 5px;}
      </style>
  </head> 
  <body>
  <div class="header">
      <div class="nav">
          <a href="user/add.html">增加</a>
          <span>|</span>
          <a href="j_spring_security_logout">退出</a>
      </div>
  </div>
  <br/> 
   欢迎您${sessionScope['SPRING_SECURITY_LAST_USERNAME']}
<form action="user/list.html"  method="post"  name="spform"><!--表单-->
  <table>
   <tr><td>
<input type="text" name="keyword" value="${keyword}"/>
<input type="submit" value="查询"/> 
</td>
</tr>
 <c:forEach items="${all.pageList}" var="u">
   	<tr>
   		<td>${u.username}</td>
   		<td>${u.useremail}</td>
   		<td>${u.role}</td>
   		<td><fmt:formatDate value="${u.createtime}" pattern="yyyy-MM-dd"/></td>
   		<td><fmt:formatDate value="${u.logintime}" pattern="yyyy-MM-dd"/></td>
        <td>${u.count}</td>
	   	<td>
	   	<a href="user/update/${u.id}.html">修改</a>
	   	<a href="user/delete/${u.id}.html?cp=${all.cp} &&ls=${all.ls}">删除</a>
	   	</td>
   	</tr>
   </c:forEach>
     <!--只要包含这段代码，即可实现分页-->
<jsp:include page="../split_page_plugin.jsp">

<jsp:param name="allRecorders" value="${all.totalNo}"/>
<jsp:param name="cp" value="${all.cp}"/>
<jsp:param name="ls" value="${all.ls}"/>
</jsp:include>
</table>
</body>
</html>
