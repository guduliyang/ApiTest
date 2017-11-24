function edit(data) {
    $("#api_project_name").val(data.project_name);
    $("#api_name").val(data.name);
    $("#api_method").val(data.method);
    $("#api_url").val(data.url);
    clearApi();
    $.each(JSON.parse(data.headers),function (key,value) {
        if (key != "" && value != "") {
            $("#content_hearder").append(addHeardString(key, value));
        }
    });
    $.each(JSON.parse(data.params),function (index, value) {
        console.log(value);
        var name = value.name;
        var type = value.type;
        var necessary = value.necessary;
        $("#content_params").append(addKVString("content_params_",name));
        $("#content_params #content_params_"+name+" select:eq(0)").val(type);
        $("#content_params #content_params_"+name+" select:eq(1)").val(necessary?"true":"false");
    });
    $.each(JSON.parse(data.bodys),function (index, value) {
        var name = value.name;
        var type = value.type;
        var necessary = value.necessary;
        $("#content_body").append(addKVString("content_body_",name));
        $("#content_body #content_body_"+name+" select:eq(0)").val(type);
        $("#content_body #content_body_"+name+" select:eq(1)").val(necessary?"true":"false");
    });
    renderForm();
}

function delApi(_id) {
    $.ajax({
        type: 'GET',
        url: "apis/del",
        data: {"_id": _id},
        dataType: "Json",
        async: true,
        success: function (data) {
            onload();
        }
    });
}

function saveApi() {
    $.ajax({
        type: 'GET',
        url: "apis/save",
        data: getValues(),
        dataType: "Json",
        async: true,
        success: function (data) {
            onload();
        }
    });
}

function clearApi() {
    $("#content_hearder").empty();
    $("#content_body").empty();
    $("#content_params").empty();
}

//-------------Body Start--------------------
function addBody() {
    var name = $("#fixed_body input:eq(0)").val();
    var type = $("#fixed_body select:eq(0)").val();
    var necessary = $("#fixed_body select:eq(1)").val();
    if (name!=""){
        $("#content_body").append(addKVString("content_body_",name));
        $("#content_body #content_body_"+name+" select:eq(0)").val(type);
        $("#content_body #content_body_"+name+" select:eq(1)").val(necessary);
        $("#fixed_body input:eq(0)").val("");
        $("#fixed_body select:eq(0)").val("false")
        renderForm();
    }
}
//-------------Body End--------------------

//-------------Params Start--------------------
function addParam() {
    var name = $("#fixed_params input:eq(0)").val();
    var type = $("#fixed_params select:eq(0)").val();
    var necessary = $("#fixed_params select:eq(1)").val();
    if (name!=""){
        $("#content_params").append(addKVString("content_params_",name));
        $("#content_params #content_params_"+name+" select:eq(0)").val(type);
        $("#content_params #content_params_"+name+" select:eq(1)").val(necessary);
        $("#fixed_params input:eq(0)").val("");
        $("#fixed_params select:eq(0)").val("false")
        renderForm();
    }
}

function addKVString(type,name) {
    var s = "<div id=\""+type+name+"\" class=\"layui-input-inline\" style=\"width: 480px\">\n" +
        "                        <div class=\"layui-input-inline\" style=\"width: 140px\">\n" +
        "                            <input class=\"layui-input\" placeholder=\"name\" value=\""+name+"\"></div>\n" +
        "                        <div class=\"layui-input-inline\" style=\"width:150px\">\n" +
        "                            <select>\n" +
        "                                <option value=\"\">参数类型</option>\n" +
        "                                <option value=\"0\">TEXT</option>\n" +
        "                                <option value=\"1\">INT</option>\n" +
        "                                <option value=\"2\">DATA</option>\n" +
        "                                <option value=\"3\">PHONE</option>\n" +
        "                                <option value=\"4\">IDCARD</option>\n" +
        "                            </select>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-input-inline\" style=\"width: 90px\">\n" +
        "                            <select>\n" +
        "                                <option value=\"true\">必选</option>\n" +
        "                                <option value=\"false\">可选</option>\n" +
        "                            </select>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-input-inline\" style=\"width: 60px\">\n" +
        "                            <input type=\"button\" class=\"layui-btn layui-btn-primary\" value=\"删除\" onclick=\"remove('"+type+name+"')\">\n" +
        "                        </div>\n" +
        "                    </div>";
    return s;
}
//-------------Params End--------------------


//-------------Hearder Start--------------------
function addHeard() {
    var key = $("#fixed_hearder input:eq(0)").val();
    var val = $("#fixed_hearder input:eq(1)").val();
    if (key != "" && val != "") {
        $("#content_hearder").append(addHeardString(key, val));
        $("#fixed_hearder input:eq(0)").val("");
        $("#fixed_hearder input:eq(1)").val("");
        renderForm();
    }
}

function addHeardString(key, value) {
    var s = "<div id=\"content_hearder_"+key+"\" class=\"layui-input-inline\" style=\"width: 480px\">\n" +
        "       <div class=\"layui-input-inline\" style=\"width: 190px\">\n" +
        "           <input class=\"layui-input\" placeholder=\"key\" value=\""+key+"\">\n" +
        "       </div>\n" +
        "       <div class=\"layui-input-inline\" style=\"width: 200px\">\n" +
        "           <input class=\"layui-input\" placeholder=\"key\" value=\""+value+"\">\n" +
        "       </div>\n" +
        "       <div class=\"layui-input-inline\" style=\"width: 60px\">\n" +
        "           <input type=\"button\" class=\"layui-btn layui-btn-primary\" value=\"删除\" onclick=\"remove('content_hearder_"+key+"')\">\n" +
        "       </div>\n" +
        "    </div>";
    return s;
}
//-------------Hearder End--------------------

function remove(filter) {
    $("#" + filter).remove();
}

function getValues() {
    var project_name = $("#api_project_name").val();
    var api_name = $("#api_name").val();
    var api_method = $("#api_method").val();
    var api_url = $("#api_url").val();
    var headers ="{";
    var params = "[";
    var bodys = "[";

    $.each($("#content_hearder").children(),function () {
       var key = $(this).find('input:eq(0)').val();
       var val = $(this).find('input:eq(1)').val();
       headers+="\""+key+"\":\""+val+"\",";
    });
    headers = (headers.length>1?headers.substr(0,headers.length-1):headers)+"}";

    $.each($("#content_params").children(),function () {
        var name = $(this).find('input:eq(0)').val();
        var type = $(this).find('select:eq(0)').val();
        var necessary = $(this).find('select:eq(1)').val();
        params+="{\"name\":\""+name+"\",\"type\":"+type+",\"necessary\":"+necessary+"},"
    });
    params = (params.length>1?params.substr(0,params.length-1):params)+"]";

    $.each($("#content_body").children(),function () {
        var name = $(this).find('input:eq(0)').val();
        var type = $(this).find('select:eq(0)').val();
        var necessary = $(this).find('select:eq(1)').val();
        bodys+="{\"name\":\""+name+"\",\"type\":"+type+",\"necessary\":"+necessary+"},"
    });
    bodys = (bodys.length>1?bodys.substr(0,bodys.length-1):bodys)+"]";



    return {
        "project_name": project_name,
        "name": api_name,
        "method": api_method,
        "url": api_url,
        "params": params,
        "bodys": bodys,
        "headers": headers
    }
}

