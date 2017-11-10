<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<head>
    <link href="layui/css/layui.css" rel="stylesheet" media="all">
</head>
<body>
<script src="layui/layui.js" type="application/javascript"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/edit.js" type="application/javascript"></script>
<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">请求方式</label>
        <div class="layui-input-inline">
            <select name="Method">
                <option value="">请选择请求方式</option>
                <option value="get">get</option>
                <option value="post">post</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="address" class="layui-input">
        </div>
        <button class="layui-btn layui-btn-primary">Params</button>
    </div>
    <div class="layui-form-item">
        <div class="layui-row layui-col-space10">
            <div class="layui-col-md1">
                <select name="Method">
                    <option value="">请选择请求方式</option>
                    <option value="get">get</option>
                    <option value="post">post</option>
                </select>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="address" class="layui-input">
            </div>
            <div class="layui-col-md1">
                <button class="layui-btn layui-btn-primary">Params</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
