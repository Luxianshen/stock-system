//树的数据
jQuery(document).ready(function () {
   //App.init();
        $('#MyTree2').tree({
            dataSource: treeDataSource2,
            loadingHTML: '<img src="/metronic/img/input-spinner.gif"/>',
        });
});
var DataSourceTree = function (options) {
    this._data = options.data;
    this._delay = options.delay;
};
DataSourceTree.prototype = {
    data: function (options, callback) {
        setTimeout(function () {
            var data = dataMethod(options.additionalParameters);
            callback({data: data});
        }, this._delay)
    }
};

var getChild = function (o) {
    var type = "item";
    $.ajax({
        type: "post",
        url: "/category/ajaxList.html",
        data: "superiorId=" + o.id,
        async: false,
        success: function (result) {
            if (result != undefined && result != null && result.length > 0) {
                type = "folder";
            } else {
                type = "item";
            }
        },
        error: function () {
            type = "item";
        }
    });
    return type;
}

//tree的数据写入
var dataMethod = function (parameters) {
    var arr = new Array;
    var param = "superiorId=";
    if (parameters != undefined) {
        param += parameters.id;
    }
    $.ajax({
        type: "post",
        url: "/category/ajaxList.html",
        data: param,
        async: false,
        success: function (result) {
            $.each(result, function (i, o) {
                var item = getChild(o);
                var name = o.name;
                if (item == "item") {
                    name ="<span code='" + o.id + "'>" + name + "</span>";
                }
                var obj = {
                    name: name + "<div class='tree-actions'>" +
                    "<i class='icon-plus' id='tree" + o.id + "' onclick='addTree(id)'></i>" +
                    "<i class='icon-remove' id='tree" + o.id + "' onclick='delTree(id)'></i>" +
                    "<i class='icon-refresh' id='tree" + o.id + "' onclick='updateTree(id)'></i></div>",
                    type: item,
                    additionalParameters: {id: o.id}
                };
                arr.push(obj);
            });
        }
    });
    return arr;
};
var treeDataSource2 = new DataSourceTree({
    delay: 400
});
//保存父级ID
function addTree(id) {
    $("#titleModel").text("新增页面");
    $("#name").attr('placeholder','请输入中文名');
    $("#code").attr('placeholder','请输入英文名');
    $("#fatherId").val(id.replace("tree",""));
    $("#fatherId").attr('name',"add");
    $("#material-property-modal").modal("show");

}

function updateTree(id){
    $("#titleModel").text("修改页面");
    $("#fatherId").val(id.replace("tree",""));
    $("#fatherId").attr('name',"update");
    $("#material-property-modal").modal("show");
    var idid=id.replace("tree","");
    $.ajax({
        type: "post",
        url: "/category/updatePre.html",
        data:{"id":idid},
        async: false,
        success: function (result) {
            $("#name").attr('placeholder',result.name);
            $("#code").attr('placeholder',result.code);
            $("#encoder").attr('placeholder',result.encoder);
        }
    });
}

function delTree(id){

    confirm("删除分类", "你正在删除该分类，确定要删除吗？", function (status) {
        if (status == true) {
            var idid=id.replace("tree","");
            $.ajax({
                type: "post",
                url: "/category/delPre.html",
                data:{"id":idid},
                async: false,
                success: function (result) {
                    if(result!=0){
                        alert("请先删除子节点");
                    }else{
                        isDel(id);
                    }
                }
            });
        } else {

        }
    });

}

function Close() {
   $("#name").val("");
    $("#code").val("");
    $("#encoder").val("");
    $("#material-property-modal").modal("hide");
}

function saveData() {
    var type=$("#fatherId").attr("name");
    var id=$("#fatherId").val();
    var aa=$("#name").val();
    var bb=$("#code").val();
    $.ajax({
        type: "post",
        url: "/category/checkName.html",
        data:{"id":id,"name":aa},
        async: false,
        success: function (result) {
            if(result=="1"){
                alert("已经存在此名称");
                return;
            }else{
                if(type=="add"){
                    if(aa!=undefined&&aa!=""&&bb!=undefined&&bb!=""){
                        var name=aa;
                        var code=bb;
                    }else{
                        alert("请先填写完整数据");
                        return;
                    }
                    $.ajax({
                        type: "post",
                        url: "/category/add.html",
                        data:{"id":id,"name":name,"code":code},
                        async: false,
                        success: function (result) {
                            Close();
                            refresh();
                        }
                    });
                }else{
                    if((aa!=undefined&&aa!="")||(bb!=undefined&&bb!="")){
                        var name=aa;
                        var code=bb;
                    }else{
                        alert("请先填写完整数据");
                        return;
                    }
                    $.ajax({
                        type: "post",
                        url: "/category/update.html",
                        data:{"id":id,"name":name,"code":code},
                        async: false,
                        success: function (result) {
                            Close();
                            refresh();
                        }
                    });
                }
            }
        }
    });
};

function isDel(id){
    var idid=id.replace("tree","");
    $.ajax({
        type: "post",
        url: "/category/del.html",
        data:{"id":idid},
        async: false,
        success: function (result) {
            //刷新页面
            if(result == "yes"){
                alert("删除成功！");
                refresh();
            }else {
                alert("该分类下存在物料，不能删除！");
            }
        }
    });
}
function refresh(){
    window.location.reload();//刷新当前页面.
}
function specialCharValidate(id,val){
    var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？\\-\\_]");
    var reg=/[a-zA-Z]/;
    var regg=/[\u4e00-\u9fa5]/;
    if(id =="code"){
        if(!reg.test(val)){
            $("#"+id+"").val("");
            alert("应该输入全字母！");
        }
    }else{
        if(!regg.test(val)){
            $("#"+id+"").val("");
            alert("应该输入全中文！");
        }
    }
    var val =  $("#"+id+"").val();
    if(val!=""){
        if(pattern.test(val)){
            $("#"+id+"").val("");
            alert("输入值包含特殊字符！");
        }
    }
}
