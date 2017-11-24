<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fieldset class="layui-elem-field layui-field-title">
    <legend>添加项目</legend>
</fieldset>
<form class="layui-form  layui-form-pane">
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">项目名</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="项目名" id="project_name">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">无锡A地址</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="无锡A地址" id="project_host_wuxia">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">无锡B地址</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="无锡B地址" id="project_host_wuxib">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">测试地址</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="测试地址" id="project_host_uat">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <div class="layui-input-inline">
            <input type="button" class="layui-btn  layui-btn-primary" value="添加" onclick="addProject()">
            <input type="reset" class="layui-btn  layui-btn-primary" value="清空">
        </div>
    </div>
</form>
<fieldset class="layui-elem-field layui-field-title">
    <legend>项目列表</legend>
</fieldset>
<table class="layui-table" lay-filter="project_projects" id="project_projects"></table>
<script type="text/css" id="project_bar">
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "updata">更新</a>
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "del">删除</a>
</script>

