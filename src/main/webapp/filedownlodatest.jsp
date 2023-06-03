<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 2023/5/21
  Time: 01:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+ "/";
%>

<html>
<head>
    <base href="<%=basePath%>">


    <!--引入jquery-->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <title>filedownload测试</title>
    <script type="text/javascript">
        $(function (){
            $("#filedownloadBtn").click(function (){
                //发送下载文件请求
                window.location.href="workbench/activity/fileDownload.do";
            });
        });
    </script>
</head>
<body>
    <input type="button" value="下载" id="filedownloadBtn">
</body>
</html>
