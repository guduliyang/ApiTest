<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<title>InterfaceTesting</title>
<head>
    <link href="layui/css/layui.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
</head>
<body class="layui-layout-body">
<script src="layui/layui.js" type="application/javascript"></script>
<script src="js/main.js" type="application/javascript"></script>
<script src="js/ApiList.js" type="application/javascript"></script>
<script src="js/ProjectList.js" type="application/javascript"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Api Test</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left" id="nav" lay-filter="nav-left">
            <li class="layui-nav-item"><a name="ApiTest">接口测试</a></li>
            <li class="layui-nav-item"><a name="FlowTest">流程测试</a></li>
            <li class="layui-nav-item"><a name="Projects">项目管理</a></li>
            <li class="layui-nav-item"><a name="Apis">接口管理</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="nav-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="nav-tree-left">
                <c:forEach var="item" items="${projects}" varStatus="vs">
                    <li class="layui-nav-item" id="${vs.count}">
                        <a href="javascript:;">${item.name}</a>
                        <c:forEach var="api" items="${requestScope[item.name]}">
                            <dl class="layui-nav-child">
                                <dd><a>${api.name}</a></dd>
                            </dl>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-body" id="context">
        <!-- 内容主体区域 -->
        <div class="context" id="Projects">
            <table class="layui-table" lay-filter="projectTable" id="projectTable"></table>
        </div>
        <div class="context" id="Apis">
            <table class="layui-table" lay-data="{height:'full-160', url:'apis/find'}" lay-filter="apiTable" id="apiTable">
                <thead>
                <tr>
                    <th lay-data="{field: '_id',width:'15%', title: 'ID', sort: true, fixed: 'left'}"></th>
                    <th lay-data="{field: 'project_name',width: '8%', title: '项目名',  sort: true,}"></th>
                    <th lay-data="{field: 'name',width: '8%', title: '接口名',sort: true}"></th>
                    <th lay-data="{field: 'method',width: '10%', title: '请求方式',sort: true}"></th>
                    <th lay-data="{field: 'url',width: '10%', title: '请求地址',sort: true}"></th>
                    <%--<th lay-data="{field: 'headers',width: '13%', title: '请求头',sort: true}"></th>--%>
                    <th lay-data="{field: 'params',width: '16%', title: '请求参数',sort: true}"></th>
                    <th lay-data="{field: 'bodys',width: '13%', title: '请求body',sort: true}"></th>
                    <th lay-data="{width: '15%', title: '操作', toolbar: '#bar_demo',align:'center'}"></th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="context" id="ApiTest">
            <form class="layui-form  layui-form-pane">
                <div class="layui-form-item">
                    <label class="layui-form-label">项目名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="project_name" placeholder="请输入项目名" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">接口名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" placeholder="请输入接口名" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请求方式</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" name="method" value="">
                        <select class="layui-form-select">
                            <option value="Get">get</option>
                            <option value="Post">Post</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请求地址</label>
                    <div class="layui-input-inline">
                        <input type="text" name="url" placeholder="请输入请求地址" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请求头</label>
                    <div class="layui-input-inline">
                        <input type="text" name="headers" placeholder="请输入请求头" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请求参数</label>
                    <div class="layui-input-inline">
                        <input type="text" name="params" placeholder="请求参数" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请求body</label>
                    <div class="layui-input-inline">
                        <input type="text" name="bodys" placeholder="请输入请求body" class="layui-input" value="">
                    </div>
                </div>
            </form>
        </div>
        <div class="context" id="FlowTest">

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script type="text/css" id="bar_demo">
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "add">增加</a>
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "edit">编辑</a>
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "del">删除</a>
</script>
<script type="text/css" id="switchMethod">
</script>
</body>
