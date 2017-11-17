function editApi(data,table) {
    layer.open({
        title:"Edit Api",
        type:1,
        id:"editApi",
        content:apiEditString(data),
        btn:"submit",
        btnAlign:"c",
        shade:0,
        yes:function () {
            var project_name = $("[name='project_name']");
            var name = $("[name='name']");
            var method = $("[name='method']");
            var requstUrl = $("[name='requstUrl']");
            var headers = $("[name='headers']");
            var params = $("[name='params']");
            var bodys = $("[name='bodys']");
            $.ajax({
                type: 'GET',
                url: "apis/edit",
                data: {"_id":data._id,"project_name":project_name,"name":name,"method":method,"requstUrl":requstUrl,"headers":headers,"params":params,"bodys":bodys},
                // dataType: "Json",
                async:false,
                success: function (data) {
                    console.log(data);
                    table.reload('apiTable',{
                        url: 'apis/find'
                        ,height:'full-160'
                    })
                }
            });
            layer.closeAll();
        }
    })
}

function addApi(table) {
    layer.open({
        title:"Edit"
    })
}

function apiEditString(data) {
    var edit = "<form class=\"layui-form  layui-form-pane\">\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">项目名</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"project_name\" placeholder=\"请输入项目名\" class=\"layui-input\" value=\""+data.project_name+"\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">接口名</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"name\" placeholder=\"请输入接口名\" class=\"layui-input\" value=\""+data.name+"\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">请求方式</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"method\" class=\"layui-input\" value=\""+data.method+"\"> " +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">请求地址</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"requstUrl\" placeholder=\"请输入请求地址\" class=\"layui-input\" value=\""+data.requstUrl+"\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">请求头</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"headers\" placeholder=\"请输入请求头\" class=\"layui-input\" value=\""+data.headers+"\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">请求参数</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"params\" placeholder=\"请求参数\" class=\"layui-input\" value=\""+data.params+"\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">请求body</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input type=\"text\" name=\"bodys\" placeholder=\"请输入请求body\" class=\"layui-input\" value=\""+data.bodys+"\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </form>"
    return edit;
}