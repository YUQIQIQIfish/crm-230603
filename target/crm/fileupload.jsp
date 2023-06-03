<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 2023/5/24
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+ "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
<head>
    <title>fileuploadtest</title>
</head>
<body>
<%--表单组件的标签必须是type="file",请求方式必须是post,get请求只能提交文本数据，表单的提交格式必须是enctype="multipart/form-data"--%>
<form action="workbench/activity/fileUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="myFile"><br>
    <input type="text" name="userName"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
