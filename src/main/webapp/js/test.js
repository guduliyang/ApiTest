function load_info(api_name,project_name) {
    $.ajax({
        type: 'GET',
        url: "apis/findByName",
        data: {"name": api_name,"project_name":project_name},
        dataType: "Json",
        async: true,
        success: function (data) {
            $("#test_project_name").val(data.project_name);
            $("#test_name").val(data.name);
            $("#test_method").val(data.method);
            $("#test_url").val(data.url);
            test_clearApi();
            $.each(data.headers,function (key,value) {
                if (key != "" && value != "") {
                    $("#test_content_hearder").append(addHeardString(key, value));
                }
            });
            $.each(data.params,function (index, value) {
                console.log(value);
                var name = value.name;
                var type = value.type;
                var necessary = value.necessary;
                $("#test_content_params").append(addKVString("content_params_",name));
                $("#test_content_params #content_params_"+name+" select:eq(0)").val(type);
                $("#test_content_params #content_params_"+name+" select:eq(1)").val(necessary?"true":"false");
            });
            $.each(data.bodys,function (index, value) {
                var name = value.name;
                var type = value.type;
                var necessary = value.necessary;
                $("#test_content_body").append(addKVString("content_body_",name));
                $("#test_content_body #content_body_"+name+" select:eq(0)").val(type);
                $("#test_content_body #content_body_"+name+" select:eq(1)").val(necessary?"true":"false");
            });
            renderForm();
            renderRequestTable(api_name,project_name);
        }
    });
}

function test_clearApi(){
    $("#test_content_hearder").empty();
    $("#test_content_body").empty();
    $("#test_content_params").empty();
};