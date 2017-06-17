var num = 0;

//查询右边的数据
function Change(j) {
    //获取code值
    var find = "#" + "change" + j.id;
    var kkk = "#" + j.id;
    var lll = "#" + j.id + " option:selected";
    var code = $(lll).attr("code");

    $.ajax({
        type: "post",
        url: "/dictionary/rightData.html",
        data: {"code": code},
        async: false,
        success: function (result) {
            var str;
            var rightdata = "";
            var n = num - 1;
            if (result != undefined && result != null && result.length > 0) {
                $.each(result, function (i, o) {
                    rightdata += "<option value='" + o.optionValue + "'>" + o.optionName + "</option>";
                });
                str = "<div id='change"
                    + n
                    + "b"
                    + "'class='col-md-6'><select  class='form-control'>"
                    + rightdata
                    + "</select> </div>";

            } else {
                var obj = $(j.selectedOptions);
                var name = obj.attr("oname");
                var dataType = obj.attr("odataType");
                var length = obj.attr("olength");
                var constraints = " ";
                switch(dataType) {
                    case "VARCHAR2":
                        constraints = " placeholder='请输入"+length+"个以内的字符!' maxlength='"+length+"' onKeypress='javascript:if(event.keyCode == 32)event.returnValue = false;'"; break;
                    case "NUMBER":
                        constraints = " placeholder='请输入"+length+"个以内的数字!' constraint='mask_number' onfocus='handleInputMasks(this,"+length+")' "; break;
                    default:
                        ;
                }
                rightdata = "<input " + constraints + "class='form-control' name='"+name+"'>";
                str = "<div id='change"
                    + n
                    + "b"
                    + "'class='col-md-6'>"
                    + rightdata
                    + "</div>";

            }
            $(find).replaceWith(str);
            $(kkk).attr('disabled', true);
        }
    });
}

var carry = new Array();
var mname = "";
function CreateInput(id,name) {
    var code;
    if(name!=undefined && name!="") mname = name;
    if (id != null && id != undefined) {
        code = id;
        //alert(code);
        $("#new").attr("code", code);
        return;
    } else {
        code = $("#new").attr("code");
        //alert(code);
        var b = new Array();
        $.ajax({
            type: "post",
            url: "/material/ajaxLeftList.html",
            data: "id=" + code + "",
            async: false,
            success: function (result) {
                var str;
                var leftdata = "<option value='999'>请选择</option>";
                if (num == 0) {
                    carry = [];
                    $.each(result, function (i, o) {
                        leftdata += "<option odataType='"+ o.dataType +"' olength='" + o.length +"' " +
                            "ocode='"+o.code+"' oname='"+o.name+"' value='" + i + "' code='" + o.dictionaryCode + "' mcode='"+o.code+"'>" + o.name + "</option>";
                        carry.push(i);
                    });
                } else {
                    var n;
                    for (n = num - 1; 0 <= n; n--) {
                        var selected = "#" + n + "a";
                        var checkValue = $(selected).val();

                        if (checkValue == 999) {
                            alert("请先选择");
                            return;
                        } else {
                            b.push(checkValue);
                        }
                    }
                    //排序 方便处理
                    b.sort();
                    var arr = new Array();
                    for (var k = 0; k < carry.length; k++) {
                        arr[k] = carry[k];
                    }
                    for (var i = 0; i < b.length; i++) {
                        for (var j = 0; j < arr.length; j++) {
                            var aa = arr[j];
                            var bb = b[i];
                            if (aa == bb) {
                                arr.splice(j, 1);
                            }
                        }
                    }
                    if (arr.length > 0 && arr != null) {
                        $.each(result, function (i, o) {
                            for (m = 0; m < arr.length; m++) {
                                if (i == arr[m]) {
                                    leftdata += "<option odataType='"+ o.dataType +"' olength='" + o.length +"' " +
                                        "ocode='"+o.code+"' oname='"+o.name+"' value='" + i + "' code='" + o.dictionaryCode + "' mcode='"+o.code+"'>" + o.name + "</option>";
                                }
                            }
                        });
                    } else {
                        alert("没有更多的属性..");
                        return;
                    }
                }
                str = "<div class='row'><div class='space-10'></div><div class='form-group'><div class='col-md-3'> <select onchange='Change(this)' id='"
                    + num
                    + "a"
                    + "' class='form-control'>"
                    + leftdata
                    + "</select> </div>"
                    + "<div id='change"
                    + num
                    + "a"
                    + "'class='col-md-6'><input  class='form-control' type='text'></div>"
                    + "<div class='col-md-3'><button class='btn dark' onclick='del(this)'>"
                    + "删除属性 <i class='icon-remove'></i>" +
                    "</button></div></div>";
                $("#testadd").append($(str));
                num++;
            },
            error: function () {
                alert("error");
            }
        });
    }
}

//Delete  删除
function del(o) {
    $(o).parent().parent().remove();
    if (num == 0) {
        num = 0;
    } else {
        num - 1;
    }
}

function Close() {
    num = 0;
    $("#testadd").empty();
    $("#num").val("");
    $("#material-property-modal").modal("hide");
    //$("#material-modal").modal("hide");
}

function SaveData() {
    //先把一行数据捆绑
    //拼接全部数据
    var materielId = $("#new").attr("code");
    var numaa = $("#num").val();
    if (isNaN(numaa)||numaa==""||numaa<1) {
        alert("请填写正确的申请数量!");
        return;
    }
    var str = "'materielId':'"+materielId+"','count':" + parseFloat($("#num").val()) + ",'applyListProperties':[";
    var pArr = new Array;
    for (var i = 0; i < num; i++) {
        var ddName = $("#" + i + "a" + " option:selected").text();
        var dd = $("#" + i + "a" + " option:selected").attr("mcode");
        var cc = $("#" + "change" + i + "b" + " option:selected").text();
        var ccChildren = $("#" + "change" + i + "b").children().val();
        if (cc != "" && cc != undefined && dd != "" && dd != undefined) {
            str += "{'property':'"+dd + "','value':'" + cc + "'}";
            pArr.push(ddName+" : "+cc);
        } else if (dd != "" && dd != undefined) {
            str += "{'property':'"+dd + "','value':'" + ccChildren + "'}";
            pArr.push(ddName+" : "+ccChildren);
        }
        if(dd!="" && dd!=undefined){
            if(i<num){
                str+=",";
            }
            if((cc==""||cc==undefined)&&(ccChildren==""||ccChildren==undefined)){
                alert("请输入"+ddName+"的属性值!");return;
            }
        }
    }
    //转成json格式
    var lasts = str.charAt(str.length-1);
    if(lasts==","){
        str = str.substr(0,str.length-1);
    }
    var obj = "{" + str + "]}";
    var ajaxObj = eval("(" + obj + ")");
    var hidden = "<input type='hidden' name='applyLists' class='form-control' value=\""+obj+"\">";
    var values = $("#statisticsBody").find("input.form-control");
    var bo = true;
    var propertiesObj = ajaxObj.applyListProperties;
    $.each(values,function(i,o){
        var val = $(this).val();
        var ajaxVal = eval("(" + val + ")");
        var propertiesVal = ajaxVal.applyListProperties;
        if((ajaxVal.materielId==ajaxObj.materielId) && (JSON.stringify(propertiesObj)==JSON.stringify(propertiesVal))){
            bo = false;
        }
    });
    if(bo){
        //$("#params").append(hidden);
        addStatistics(mname,pArr,ajaxObj.count,hidden);
    }else{
        alert("你已添加此数据,本次添加无效!");
    }
    $("#testadd").empty();
    $("#num").val("");
    $("#material-property-modal").modal("hide");
    $("#material-modal").modal("hide");
    $("html").removeAttr("class");
    num=0;
}

function addStatistics(name,arr,count,hidden){
    var nameTd = "<td class='name'>"+name+"</td>";
    var paramsP = "";
    $.each(arr,function(i,o){
        paramsP+="<p class='text-warning'>"+o+"</p>";
    });
    var paramsTd = "<td class='params'>"+ paramsP +"</td>";
    var countTd = "<td class='count'>"+count+"</td>";
    var delTd = "<td><span onclick='removeStatistics(this)' class='label label-default'><i class='icon-remove'></i> 删除</span></td>";
    var tr = "<tr>"+hidden+nameTd+paramsTd+countTd+delTd+"</tr>";

    $("#statisticsBody").append(tr);
}
/*
* <tr>
 <td class="name">POS机身 </td>
 <td class="params">
 <p class="text-warning">型号: P30</p>
 <p class="text-warning">厂商: 联想</p>
 <p class="text-warning">功率: 300</p>
 </td>
 <td class="count">160</td>
 <td><span onclick="removeStatistics(this)" class="label label-default"><i class="icon-remove"></i> 删除</span></td>
 </tr>
* */
function removeStatistics(o){
    var tr = o.parentNode.parentNode;
    document.getElementById("statisticsBody").removeChild(tr);
}