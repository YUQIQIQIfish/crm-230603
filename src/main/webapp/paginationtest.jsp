<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 2023/5/11
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+ "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <!--  It is a good idea to bundle all CSS in one file. The same with JS -->

    <!--引入jquery-->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <!--bootstrap框架-->
    <link rel="stylesheet" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <!--  PAGINATION plugin -->
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
    <title>演示bs_paginationtest插件</title>
<script type="text/javascript">
    $(function() {

        $("#demo_pag1").bs_pagination({
            totalPages: 1
        });
    });
</script>
</head>
<body>

<!--  Just create a div and give it an ID -->

<div id="demo_pag1"></div>
</body>
</html>
