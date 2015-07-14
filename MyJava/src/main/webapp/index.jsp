<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 15-7-4
  Time: 下午5:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>欢迎页</title>
</head>
<body>
      <script type="text/javascript">
          window.location.href="<%=basePath%>index.html";
      </script>
</body>
</html>