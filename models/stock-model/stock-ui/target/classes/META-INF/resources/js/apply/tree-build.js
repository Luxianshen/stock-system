//树的数据
jQuery(document).ready(function () {
    $("#material-modal").on("shown.bs.modal", function (e) {
        $('#MyTree2').tree({
            dataSource: treeDataSource2,
            loadingHTML: "<img src='/metronic/img/input-spinner.gif'/>",
        });
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

//var getMaterials = function (id) {
//    var param = "id=" + id;
//    $.ajax({
//        type: "post",
//        url: "/material/ajaxList.html",
//        data: param,
//        async: false,
//        success: function (result) {
//            $("#materialPanel").empty();
//            $.each(result, function (i, o) {
//                $("#materialPanel").append($("<div class='col-md-4 material-item'> <a onclick='CreateInput(\""+o.id+"\",\""+o.name+"\")' id='"
//                    + o.id
//                    + "' class='btn btn-default btn-block'>" + o.name + "</a> </div>"));
//            });
//
//            eventA();
//            $("#searchcode").val("");
//            materialArr = new Array;
//            $("#materialPanel").find("a").each(function (i, o) {
//                if (jQuery.inArray(o.parentNode, materialArr) < 0) {
//                    materialArr.push(o.parentNode);
//                }
//            });
//
//
//        }
//    });
//};

function eventA() {
    $(".material-item > a").on("click", function () {
        $("#material-property-modal").modal("show");
    });
}

var materialArr = new Array;

var searchMaterial = function () {
    var newArr = materialArr;
    var name = $("#searchcode").val();
    $("#materialPanel").empty();
    $.each(newArr, function (i, o) {
        var nameHtml = o.firstElementChild.innerHTML;
        if (nameHtml.indexOf(name) >= 0) {
            $("#materialPanel").append(o);
        }
    });
    eventA();
};


$("#MyTree2").click(function () {
    var t = $(this).find(".tree-selected");
    if (t != undefined && t != null) {
        var id = t.find("span").attr("code");
        if (id != undefined) {
            getMaterials(id);
        }
        return;
    }
});

var dataMethod = function (parameters) {
    var arr = new Array;
    var param = "superiorId=";
    if (parameters != undefined) {
        param += parameters.id;
    }
    /*if(param.length>"superiorId=".length) {
        $.ajax({
            type: "post",
            url: "/category/ajaxFindById.html",
            data: param,
            async: false,
            success: function (o) {
                var obj2 = {
                    name: "<span code='" + o.id + "'>" + o.name + "</span>" + "<div class='tree-actions'></div>",
                    type: "item",
                    additionalParameters: {id: o.id}
                };
                arr.push(obj2);
            }
        });
    }*/
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
                    name = "<span code='" + o.id + "'>" + name + "</span>";
                }else{
                    name = "<span code='folder_" + o.id + "'>" + name + "</span>";
                }
                var obj = {
                    name: name + "<div class='tree-actions'></div>",
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

//搜索模块
/*
 function Search(){
 //获取输入查询
 var searchcode=$("#searchcode").val();
 /!*if(searchcode==null&&searchcode==undefined){
 alert("请先输入参数");
 }else{*!/
 $.ajax({
 type: "post",
 url: "/material/ajaxSearchList.html",
 data:{"searchcode":searchcode},
 async: false,
 success: function (result) {
 //if(result!=undefined && result!=null && result.length()>0){
 $("#materialPanel").empty();
 $.each(result, function (i, o) {
 $("#materialPanel").append($("<div class='col-md-4 material-item'> <a onclick='CreateInput(this.id)' id='"
 +o.id
 +"' class='btn btn-default btn-block'>" + o.name + "</a> </div>"));
 });
 $(".material-item > a").on("click", function () {
 $("#material-property-modal").modal("show");
 });
 /!* }else{
 alert("失败了");
 }*!/
 }
 });
 //}
 };*/
