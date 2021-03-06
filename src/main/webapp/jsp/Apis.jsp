<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fieldset class="layui-elem-field layui-field-title">
    <legend>添加接口</legend>
</fieldset>
<form class="layui-form  layui-form-pane">
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">项目名</label>
        <div class="layui-input-inline">
            <select id="api_project_name">
                <option value="">请选择项目</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">接口名</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="接口名" id="api_name">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">请求方式</label>
        <div class="layui-input-inline">
            <select id="api_method" lay-filter="api_method">
                <option value="">请求方式</option>
                <option value="get">Get</option>
                <option value="post">Post</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">请求地址</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="请求地址" id="api_url">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <div class="layui-input-block">
            <input type="button" class="layui-btn  layui-btn-primary" value="保存" onclick="saveApi()">
            <input type="reset" class="layui-btn  layui-btn-primary" value="清空" onclick="clearApi()">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-collapse layui-input-inline" style="width: 500px">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">Headers 设置</h2>
                <div class="layui-colla-content">
                    <div id="content_hearder" class="layui-input-inline" style="width: 480px"></div>
                    <hr>
                    <div id="fixed_hearder" class="layui-input-inline" style="width: 480px">
                        <div class="layui-input-inline" style="width: 190px">
                            <input class="layui-input" placeholder="key">
                        </div>
                        <div class="layui-input-inline" style="width: 200px">
                            <input class="layui-input" placeholder="key">
                        </div>
                        <div class="layui-input-inline" style="width: 60px">
                            <input type="button" class="layui-btn layui-btn-primary" value="增加" onclick="addHeard()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-collapse layui-input-inline" style="width: 500px">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">Params 设置</h2>
                <div class="layui-colla-content">
                    <div id="content_params" class="layui-input-inline" style="width: 480px"></div>
                    <hr>
                    <div id="fixed_params" class="layui-input-inline" style="width: 480px">
                        <div class="layui-input-inline" style="width: 140px">
                            <input class="layui-input" placeholder="name"></div>
                        <div class="layui-input-inline" style="width:150px">
                            <select>
                                <option value="">参数类型</option>
                                <option value="0">TEXT</option>
                                <option value="1">INT</option>
                                <option value="2">DATA</option>
                                <option value="3">PHONE</option>
                                <option value="4">IDCARD</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 90px">
                            <select>
                                <option value="false">可选</option>
                                <option value="true">必选</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 60px">
                            <input type="button" class="layui-btn layui-btn-primary" value="增加" onclick="addParam()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-collapse layui-input-inline" style="width: 500px">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">Body 设置</h2>
                <div class="layui-colla-content">
                    <div id="content_body" class="layui-input-inline" style="width: 480px"></div>
                    <hr>
                    <div id="fixed_body" class="layui-input-inline" style="width: 480px">
                        <div class="layui-input-inline" style="width: 140px">
                            <input class="layui-input" placeholder="name"></div>
                        <div class="layui-input-inline" style="width:150px">
                            <select>
                                <option value="">参数类型</option>
                                <option value="0">TEXT</option>
                                <option value="1">INT</option>
                                <option value="2">DATA</option>
                                <option value="3">PHONE</option>
                                <option value="4">IDCARD</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 90px">
                            <select>
                                <option value="false">可选</option>
                                <option value="true">必选</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 60px">
                            <input type="button" class="layui-btn layui-btn-primary" value="增加" onclick="addBody()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<fieldset class="layui-elem-field layui-field-title">
    <legend>接口列表</legend>
</fieldset>

<table class="layui-table" lay-filter="api_apis" id="api_apis"></table>

<script type="text/css" id="api_bar">
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "edit">编辑</a>
    <a class = "layui-btn layui-btn-primary layui-btn-xs" lay-event = "del">删除</a>
</script>

