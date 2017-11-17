function addProject(table,element){
    layer.open({
        title:"Add Project"
        ,type: 1
        ,id: 'layerDemo'//防止重复弹出
        ,content: projectString()
        ,btn: 'Submit'
        ,btnAlign: 'c' //按钮居中
        ,shade: 0 //不显示遮罩
        ,yes: function(){
            var name = $("[name='name_poj']").val();
            var host_wuxia = $("[name='host_wuxia']").val();
            var host_wuxib = $("[name='host_wuxib']").val();
            var host_uat = $("[name='host_uat']").val();
            $.ajax({
                type: 'GET',
                url: "projects/add",
                data: {"name":name,"host_wuxia":host_wuxia,"host_wuxib":host_wuxib,"host_uat":host_uat},
                // dataType: "Json",
                async:false,
                success: function (data) {
                    console.log(data);
                    table.reload('projectTable',{
                        url: 'projects/findAll'
                        ,height:'full-160'
                    });
                    element.init();
                }
            });
            layer.closeAll();
        }
    });
}

function dropProject(id) {
    $.ajax({
        type: 'GET',
        url: "projects/drop",
        data: {"_id":id},
        // dataType: "Json",  //返回数据类型
        success: function (success) {

        }
    });
}

function projectString() {
    var project = "<form class=\"layui-form layui-form-pane\">\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">项目名</label>\n" +
        "        <div class=\"layui-input-inline\">\n" +
        "            <input type=\"text\" name=\"name_poj\" required  lay-verify=\"required\" placeholder=\"在此输入项目名\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">无锡A地址</label>\n" +
        "        <div class=\"layui-input-inline\">\n" +
        "            <input type=\"text\" name=\"host_wuxia\" required  lay-verify=\"required\" placeholder=\"在此输入无锡A地址\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">无锡B地址</label>\n" +
        "        <div class=\"layui-input-inline\">\n" +
        "            <input type=\"text\" name=\"host_wuxib\" required  lay-verify=\"required\" placeholder=\"在此输入无锡B地址\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">UAT地址</label>\n" +
        "        <div class=\"layui-input-inline\">\n" +
        "            <input type=\"text\" name=\"host_uat\" required  lay-verify=\"required\" placeholder=\"在此输入UAT地址\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</form>";
    return project;
}

