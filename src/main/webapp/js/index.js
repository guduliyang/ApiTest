var element;
var table;

function addNavLi(poj) {
    var li = "<li class=\"layui-nav-item\" id=\"" + poj + "\">\n" +
        "                        <a href=\"javascript:;\">" + poj + "</a>\n" +
        "                    </li>";
    addNavLi
    $("#nav-tree-left").append(li);
    return li;
}

function dropNavLi(poj) {
    $("#" + poj).remove();
}

function dropNavDd(projectName, api) {
    $("#" + projectName + " #" + api).remove();
}

function addNavDd(projectName, api) {
    var dd = "<dl class=\"layui-nav-child\" id=\"" + api + "\">\n" +
        "         <dd><a>" + api + "</a></dd>\n" +
        "     </dl>";
    $("#" + projectName).append(dd);
    return dd;
}

function updataNav() {
    $("#nav-tree-left").empty()
    loadNav();
}

//初始化
function onload() {
    loadBasicData();
    renderTable();
}

//加载导航数据
function loadBasicData() {
    var data;
    $("#nav-tree-left").empty();
    $.ajax({
        type: "GET",
        url: 'loadnav',
        data: "",
        dataType: "Json",
        async: true,
        success: function (data) {
            data = data;
            $.each(data, function (n, value) {
                var projectName = value.project_name;
                addNavLi(projectName);
                $.each(value.apiList, function (n, value) {
                    addNavDd(projectName, value);
                });
            });
            $("#api_project_name").empty();
            $.each(data, function (n, value) {
                $("#api_project_name").append(" <option value=\"" + value.project_name + "\">" + value.project_name + "</option>")
            });
            $("#test_project_name").empty();
            $.each(data, function (n, value) {
                $("#test_project_name").append(" <option value=\"" + value.project_name + "\">" + value.project_name + "</option>")
            });
            renderForm();
            renderElement();
        }
    });
}

//更新导航栏
function renderElement() {
    layui.use('element', function () {
        var element = layui.element;
        //事件监听
        element.on('nav(nav-left)', function (elem) {
            var name = elem[0].children[0].name;
            $(".context").css("display", "none");
            $("#" + name).css("display", "block");
        });
        element.on('nav(nav-tree-left)', function (elem) {
            $(".context").css("display", "none");
            $("#ApiTest").css("display", "block");
            var project_name = $(elem.parent().parent().children()[0]).text();
            var api_name = $(elem).find('a').text();
            load_info(api_name,project_name);
        });

        //更新渲染
        element.init();
    })
}

//更新表单
function renderForm() {
    layui.use('form', function () {
        var form = layui.form;

        //事件监听
        form.on('select(api_method)',function (data) {
           var method = data.value;
           if (method == "get"){
               $("#fixed_body input:last").addClass("layui-btn-disabled");
           }else {
               $("#fixed_body input:last").removeClass("layui-btn-disabled");
           }
        });

        //更新渲染
        form.render();
    })
}

//更新Table
function renderTable() {
    layui.use('table', function () {
        table = layui.table;

        //事件监听
        table.on('edit(projectTable)', function (obj) {
            // console.log(obj.value); //得到修改后的值
            // console.log(obj.field); //当前编辑的字段名
            // console.log(obj.data);
            // console.log(obj.data._id)
        });
        table.on('tool(project_projects)', function (obj) {
            var data = obj.data;
            var layEvnnt = obj.event
            var tr = obj.tr;
            if (layEvnnt == 'updata') {
                $.ajax({
                    type: 'GET',
                    url: "projects/updata",
                    data: {
                        "_id": data._id,
                        "name": data.name,
                        "host_wuxia": data.host_wuxia,
                        "host_wuxib": data.host_wuxib,
                        "host_uat": data.host_uat
                    },
                    success: function (data) {
                        onload();
                    }
                });
            } else if (layEvnnt == 'del') {
                layer.confirm('确定删除项目' + data.name + '吗？', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    dropProject(data._id);
                    onload();
                });
            }
        });
        table.on('tool(api_apis)', function (obj) {
            var data = obj.data;
            var layEvnnt = obj.event;
            if (layEvnnt == 'edit') {
                edit(data);
                // updataApi(data);
            } else if (layEvnnt == 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    delApi(data._id);
                });
            } else if (layEvnnt == 'name_evn'){

            }

        });

        //更新渲染
        table.render({
            elem: '#project_projects',
            height: 'full-310',
            url: 'projects/findAll',
            cols: [[ //标题栏
                {field: '_id', title: 'ID', sort: true},
                {field: 'name', title: 'Name', sort: true, edit: 'text'},
                {field: 'host_wuxia', title: '无锡A地址', sort: true, edit: 'text'},
                {field: 'host_wuxib', title: '无锡B地址', sort: true, edit: 'text'},
                {field: 'host_uat', title: '测试地址', sort: true, edit: 'text'},
                {title: '操作', toolbar: '#project_bar', align: 'center'}
            ]]
        });
        table.render({
            elem: '#api_apis',
            height: 'full-310',
            url: 'apis/findAll',
            cols: [[ //标题栏
                {field: 'project_name', title: '项目名', sort: true},
                {field: 'name', title: '接口名', sort: true , event: 'name_evn'},
                {field: 'method', title: '请求方式', sort: true},
                {field: 'url', title: '请求地址', sort: true},
                {field: 'headers', title: '请求头'},
                {field: 'params', title: '请求参数'},
                {field: 'bodys', title: '请求body'},
                {title: '操作', toolbar: '#api_bar', align: 'center'}
            ]]
        });
    })
}