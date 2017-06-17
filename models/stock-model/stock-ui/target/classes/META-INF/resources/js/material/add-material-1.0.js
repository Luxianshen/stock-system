/**
 * Created by lujiasheng on 2017/1/18。
 */

var num = 0;
var sum = 0;
//字典数据
function createData(){
    $("#name1").val("");
    $("#code1").val("");
    $("#length").val("");
    $("#dictionaryId").val("");
    $("#remark").val("");
    var codelist = "<option value='NODICTIONARY' >无字典</option>";
    //获取数据字典code
    $.ajax({
        type: "post",
        url: "/dictionary/listCode.html",
        async: false,
        success: function (result) {

            if (result != null && result != undefined) {
                $.each(result, function (i, o) {
                    codelist += "<option value='" + o.code + "' >" + o.name + "</option>";
                });
            } else {
                alert("fail");
            }
        }
    });
    $("#dictionaryId").html(codelist);
    $("#material-property").modal("show");

    num++;
    sum++;
}

//校验
function checkInput(id,value){
    //校验输入的是否是英文字母
    var pattern = /^[A-Za-z]+$/;
    var boolen = pattern.test(value);
    //转换大写
    $("#"+id+"").val(value.toUpperCase());
    if (boolen == false) {
        $("#"+id+"").val("");
        alert("物料代码：请输入全英文！");
        return false;
    }
    for(j=0;j<num;j++){
        var bb = $("#coden" + j + "").text();
        if (bb == value.toUpperCase()) {
            $("#"+id+"").val("");
            alert("已经存在这个code！");
            return false;
        }
    }
}
//保存数据
function SaveData(name){

    var aa = $("#name1").val();
    var bb = $("#code1").val();
    var cc = $("#datatype option:selected").val();
    var dd = $("#length").val();
    var ee = $("#dictionaryId option:selected").val();
    var ff = $("#remark").val();
    //判断选项是否被勾选
    if ($("#statistics").attr('checked') == "checked") {
        var gg = "是";
    } else {
        var gg = "否";
    }
    if ($("#require").attr('checked') == "checked") {
        var hh = "是";
    } else {
        var hh = "否";
    }
    if ($("#uniqued").attr('checked') == "checked") {
        var kk = "是";
    } else {
        var kk = "否";
    }
    if (aa != undefined && aa != "" && bb != undefined && bb != "" && cc != undefined && cc != ""
        && dd != undefined && dd != "" && ee != undefined && ee != "") {

        for(var j=0;j<num;j++){
            var jj = $("#coden" + j + "").text();
            if (jj == bb.toUpperCase()) {
                alert("已经存在这个code！");
                return false;
            }
        }

        $("#TB").append($("<tr><td id='namen"+num+"'>"+aa+"</td><td id='coden"+num+"'>"+bb+"</td><td id='datatypen"+num+"'>"+cc+"</td>" +
            "<td id='lengthn"+num+"'>"+dd+"</td><td id='dictionaryCoden"+num+"'>"+ee+"</td>" + "<td id='remarkn"+num+"'>"+ff+"</td>" +
            "<td id='uniquedn"+num+"'>"+kk+"</td><td id='statisticsn"+num+"'>"+gg+"</td><td id='requiren"+num+"'>"+hh+"</td><td>" +
            "<input type='button' class='btn btn-info' value='删除' id='del"+num+"' onclick='del(id)'></td></tr>"));
    } else {
        if(aa == undefined || aa == ""){
            alert("属性名为空！请检查");
            return false;
        }else if(bb == undefined || bb == ""){
            alert("属性代码为空！请检查");
            return false;
        }else if(dd == undefined || dd == ""){
            alert("数据长度为空！请检查");
            return false;
        }

    }
    if(name != "next"){
        $("#material-property").modal("hide");
    }else{
        createData();
    }
    $("#TB").css("visibility","visible");
}
function del(id){
    sum--;
    $("#"+id+"").parent().parent().remove();
    if(sum == 0){
        $("#TB").css("visibility","hidden");
    }
}

function MaterialType() {
    var aa = $("#materialType option:selected").text();
    if (aa == "按件") {
        $("#materialPos").show();
        $("#materialtype").css("visibility","visible");
    } else {
        $("#materialPos").hide();
        $("#materialtype").css("visibility","hidden");
    }
}
function SaveTable() {

    var data = new Array();
    var n;
    for (n = 1; n <=num; n++) {
        var onedata = new Object();
        var aa = $("#namen" + n + "").text();
        var bb = $("#coden" + n + "").text();
        var cc = $("#datatypen" + n + "").text();
        var dd = $("#lengthn" + n + "").text();
        var ee = $("#dictionaryCoden" + n + "").text();
        var ff = $("#remarkn" + n + "").text();
        //判断选项是否被勾选
        var gg= $("#statisticsn" + n + "").text();
        var hh= $("#requiren" + n + "").text();
        var kk = $("#uniquedn" + n + "").text();
        if(gg=="是"){
            gg=1;
        }else{
            gg=0;
        }
        if(hh=="是"){
            hh=1;
        }else{
            hh=0;
        }
        if(kk=="是"){
            kk=1;
        }else{
            kk=0;
        }

        if (aa != undefined && aa != "" && bb != undefined && bb != "" && cc != undefined && cc != ""
            && dd != undefined && dd != "" && ee != undefined && ee != "") {
            onedata.name = aa;
            onedata.code = bb;
            onedata.dataType = cc;
            onedata.length = dd;
            onedata.dictionaryCode = ee;
            onedata.remark = ff;
            onedata.statistics = gg;
            onedata.require = hh;
            onedata.uniqued = kk;

            data.push(onedata);
        }

    }

    var code = $("input[name='code']").val();
    var name = $("input[name='name']").val();
    var type = $("#materialType option:selected").val();
    var materielCategoryId = $("#materielCategoryId option:selected").val();
    if(materielCategoryId == ""|| materielCategoryId == undefined){
        alert("请选择物料分类！");
        return false;
    }
    if (code != "" && code != undefined && name != "" && name != undefined && type != ""
        && type != undefined && materielCategoryId != "" && materielCategoryId != undefined) {
        var param = JSON.stringify(data);
        //插入数据到页面 一起form提交
        $("#jsondata").val(param);
        addTable.submit();
        num=0;
        sum=0;
    }else {
        return false;
    }
}