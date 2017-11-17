<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<head>
    <link href="layui/css/layui.css" rel="stylesheet" media="all">
</head>
<body class="layui-layout-body">
<script src="layui/layui.js" type="application/javascript"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/ApiList.js" type="application/javascript"></script>

<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-row layui-col-space12">
            <div class="layui-col-md1">
                <select name="Project">
                    <option value="">Select Project</option>
                    <option value="hello">hello</option>
                </select>
            </div>
            <div class="layui-col-md4">
                <input type="text" placeholder="请输入接口名" class="layui-input">
            </div>
            <div class="layui-col-md5">
                <input type="text" placeholder="请输测试地址" class="layui-input">
            </div>
            <div class="layui-col-md2">
                <div class="layui-btn-group">
                    <a class="layui-btn layui-btn-primary">New Project</a>
                    <a class="layui-btn layui-btn-primary" onclick="save()">Save</a>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-row layui-col-space12">
            <div class="layui-col-md1">
                <select name="Method">
                    <option value="">请选择请求方式</option>
                    <option value="get">get</option>
                    <option value="post">post</option>
                </select>
            </div>
            <div class="layui-col-md9">
                <input type="text" name="address" class="layui-input">
            </div>
            <div class="layui-col-md2">
                <div class="layui-btn-group">
                    <a class="layui-btn layui-btn-primary" onclick="displayDiv('Headers')">Headers</a>
                    <a class="layui-btn layui-btn-primary" onclick="displayDiv('Params')">Params</a>
                    <a class="layui-btn layui-btn-primary" onclick="displayDiv('Body')">Body</a>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item" id="Headers" style="display: none">
        <fieldset class="layui-elem-field">
            <legend>Headers</legend>
            <div class="layui-row layui-col-space10" id="addHeader">
                <div class="layui-col-md5">
                    <input type="text" class="layui-input" placeholder="New Key">
                </div>
                <div class="layui-col-md5">
                    <input type="text" class="layui-input" placeholder="Value">
                </div>
                <div class="layui-col-md2">
                    <div class="layui-btn-group">
                        <a class="layui-btn layui-btn-primary" onclick="addRow('addHeader')">增加</a>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>

    <div class="layui-form-item" id="Params" style="display: none">
        <fieldset class="layui-elem-field site-demo-button">
            <legend>Params</legend>
            <div class="layui-row layui-col-space10" id="addParams">
                <div class="layui-col-md5">
                    <input type="text" class="layui-input" placeholder="New Key">
                </div>
                <div class="layui-col-md5">
                    <input type="text" class="layui-input" placeholder="Value">
                </div>
                <div class="layui-col-md2">
                    <div class="layui-btn-group">
                        <a class="layui-btn layui-btn-primary" onclick="addRow('addParams')">增加</a>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>

    <div class="layui-form-item" id="Body" style="display: none">
        <fieldset class="layui-elem-field">
            <legend>Body</legend>
            <div class="layui-row layui-col-space10" id="addBody">
                <div class="layui-col-md5">
                    <input type="text" class="layui-input" placeholder="New Key">
                </div>
                <div class="layui-col-md5">
                    <input type="text" class="layui-input" placeholder="Value">
                </div>
                <div class="layui-col-md2">
                    <div class="layui-btn-group">
                        <a class="layui-btn layui-btn-primary" onclick="addRow('addBody')">增加</a>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
</form>

<form class="layui-form">
    <table class="layui-table">
        <thead>
        <th>ID</th>
        <th>接口名</th>
        <th>
            <select name="project_name" lay-filter="project_name">
                <option value="">All</option>
                <c:forEach var="project" items="${projects}" varStatus="vs">
                    <option value="${project.name}">${project.name}</option>
                </c:forEach>
            </select>
        </th>
        <th>请求方式</th>
        <th>请求地址</th>
        <th>参数列表</th>
        <th>body列表</th>
        <th>请求header</th>
        </thead>
        <tbody id="apis">
        <c:forEach var="api" items="${DataApi}" varStatus="vs">
            <tr id="${api._id}">
                <td><input type="text" class="layui-input" value="${api._id}" readonly></td>
                <td><input type="text" class="layui-input" value="${api.name}"></td>
                <td><input type="text" class="layui-input" value="${api.project_name}"></td>
                <td><input type="text" class="layui-input" value="${api.url}"></td>
                <td><input type="text" class="layui-input" value="${api.params}"></td>
                <td><input type="text" class="layui-input" value="${api.bodys}"></td>
                <td><input type="text" class="layui-input" value="${api.method}"></td>
                <td><input type="text" class="layui-input" value="${api.headers}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

<form></form>
</body>
</html>
