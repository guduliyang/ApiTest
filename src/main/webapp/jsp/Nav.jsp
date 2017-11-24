<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <ul class="layui-nav layui-nav-tree" lay-filter="nav-tree-left" id="nav-tree-left">
        </ul>
    </div>
</div>