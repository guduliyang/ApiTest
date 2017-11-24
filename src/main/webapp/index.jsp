<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<head>
    <title>Api Test</title>
    <link href="layui/css/layui.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
</head>
<body onload="onload()">
<script src="layui/layui.js" type="application/javascript"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/index.js"></script>
<script src="js/Projects.js"></script>
<script src="js/Apis.js"></script>
<script src="js/test.js"></script>
<div class="layui-layout layui-layout-admin">
    <jsp:include page="jsp/Nav.jsp"></jsp:include><!--导航栏-->
    <div class="layui-body" id="context">
        <!-- 内容主体区域 -->
        <div class="context" id="Projects">
            <jsp:include page="jsp/Projects.jsp"></jsp:include>
        </div>
        <div class="context" id="Apis">
            <jsp:include page="jsp/Apis.jsp"></jsp:include>
        </div>
        <div class="context" id="ApiTest">
            <jsp:include page="jsp/test.jsp"></jsp:include>
        </div>
        <div class="context" id="FlowTest">
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

</body>
</html>
