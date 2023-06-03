<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 2023/5/7
  Time: 13:23
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
    <!--bootstrap框架-->
    <link rel="stylesheet" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <!--bootstrap插件-->
    <link rel="stylesheet" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <title>演示bootstrap_datetimepicker插件</title>
<script type="text/javascript">
    $(function (){
        $("#myDate").datetimepicker({
            language:'zh-CN',
            format:'yyyy-mm-dd',
            minView:'month',
            initData:new Date(),
            autoclose:true,
            todayBtn:true,
            clearBtn:true
        });
    });
</script>
</head>
<body>
<input type="text" id="myDate" readonly>

</body>
</html>
