var element, table;
layui.use('element', function () {
    element = layui.element;
    element.on('nav(nav-left)', function (elem) {
        var name = elem[0].children[0].name;
        $(".context").css("display", "none");
        $("#" + name).css("display", "block");
    });

    element.on('nav(nav-tree-left)', function (elem) {
        console.log(elem)
    })
});

layui.use('table', function () {
    table = layui.table;

    table.on('edit(projectTable)', function (obj) {
        // console.log(obj.value); //得到修改后的值
        // console.log(obj.field); //当前编辑的字段名
        // console.log(obj.data);
        // console.log(obj.data._id)
    });
    table.on('tool(projectTable)', function (obj) {
        var data = obj.data;
        var layEvnnt = obj.event
        var tr = obj.tr;

        if (layEvnnt == 'edit') {
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
                    alert(data)
                },
                error:function (data) {
                    alert(data)
                }
            });
        } else if (layEvnnt == 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                dropProject(data._id);
            });
        } else if (layEvnnt == 'add') {
            addProject(table, element);
        }

    });
    table.on("tool(apiTable)", function (obj) {
        var data = obj.data;
        var layEvnnt = obj.event
        var tr = obj.tr;
        console.log(data)
        if (layEvnnt == 'edit') {
            editApi(data, table);
        } else if (layEvnnt == 'del') {

        } else if (layEvnnt == 'add') {

        }
    })
    renderProject();
});

function navData() {
    $.ajax({
        type: 'GET',
        url: "projects/findAll",
        data: {
            "_id": data._id,
            "name": data.name,
            "address_wuxia": data.address_wuxia,
            "address_wuxib": data.address_wuxib,
            "address_uat": data.address_uat
        },
        // dataType: "Json",
        async: false,
        success: function (data) {
            alert(data)
        }
    });
}

function renderProject() {
    table.render({
        elem:'#projectTable',
        height:'full-180',
        url:'projects/findAll',
        cols:  [[ //标题栏
            {checkbox: true},
            {field: '_id', title: 'ID', sort: true},
            {field: 'name',title: 'Name',sort: true, edit: 'text'},
            {field: 'host_wuxia',title: '无锡A地址',  sort: true, edit: 'text'},
            {field: 'host_wuxib', title: '无锡B地址',sort: true, edit: 'text'},
            {field: 'host_uat',title: '测试地址',sort: true, edit: 'text'},
            {title: '操作', toolbar: '#bar_demo',align:'center'}
        ]]
    });
}

