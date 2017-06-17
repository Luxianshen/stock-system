  var i=0;
    function add(id,name,title){
        //清空旧数据
        $("#ID").val("");
        $("#CODE").val("");
        $("#NAME").val("");
        $("#tbody").html("");
        if(id==undefined||id==""){
            //添加校验
            $("#pagetitle").html("新增页面");
            $("#CODE").attr("readonly",false);
            $("#showtable").css("visibility","hidden");
            $("#CODE").rules("add", {required: true,checkCode: true,specialCharValidate:true, messages: {required: "不能为空！",checkCode: "已经存在该代码"}});
        }else{
            $("#pagetitle").html("修改页面");
            $("#CODE").attr("readonly",true);
            $("#showtable").css("visibility","visible");
            $("#ID").val(id);
            $("#CODE").val(title);
            $("#NAME").val(name);
            $("#NAMENAME").val(name);
            //去除校验
            $("#updateOraddFrom").data('validator').resetForm();
            $("#CODE").rules("remove");
            $.ajax({
                type: "post",
                url: "/dictionary/updatePre.html",
                data: {"dictionaryCode":title},
                async: false,
                success: function (result) {
                    $.each(result, function (i, o) {
                        //插入数据
                        var data="<tr class='odd'><td><input type='hidden' id='idid"+i+"' value='"+o.id+"'>" +
                                "<input type='text' id='optionName"+i+"' name='optionName"+i+"' class='form-control' placeholder='"+o.optionName+"' maxlength='16' onchange='specialCharValidate(id,value)'></td> " +
                                "<td><input type='text' id='optionValue"+i+"' name='optionValue"+i+"' class='form-control' placeholder='"+o.optionValue+"' maxlength='16' onchange='specialCharValidate(id,value)'></td> </tr>"
                        $("#tbody").append($(data));
                        //添加校验
                        $("#optionName"+ i +"").rules("add", {checkInput1: true});
                        $("#optionValue"+ i +"").rules("add", {checkInput2: true});

                    });
                }
            });
        }

        $("#left-col").attr("class","col-md-5");
        $("#right-col").css("display","block");
        i=0;
    }
    function del(){

        $("#tbody").html("");
        $("#right-col").css("display","none");
        $("#left-col").attr("class","col-md-12");
        i=0;
    }
    function addinput(){
        $("#showtable").css("visibility","visible");
        if(i!=0){
          for(var j=0;j<i;j++){
              var jj=$("#id"+j+"").val();
              var kk=$("#name"+j+"").val();
              if((jj=="")||(kk=="")){
                  alert("存在没有填写的数据！");
                  return ;
              }
          }
        }
        var data="<tr class='odd'><td>" +
                "<input id='id"+i+"' type='text' name='id"+i+"' class='form-control' placeholder='请输入名字' maxlength='16' onchange='specialCharValidate(id,value)'></td> " +
                "<td><input id='name"+i+"' type='text' name='name"+i+"' class='form-control' placeholder='请输入值' maxlength='16' onchange='specialCharValidate(id,value)'></td> </tr>"
        $("#tbody").append($(data));
        //添加校验
        $("#id"+ i +"").rules("add", {checkInput1: true });
        $("#name"+ i +"").rules("add", {checkInput2: true});
        i++;
    }
    function delinput(){
        if (i==0) {
           return;
        }else{
            $("#tbody").find("tr").last().remove();
            i--;
        }

    }

    function saveData(){

        var data = new Array();
        //用于存最终数据

        var j = $("#tbody tr").length-i;
        //获取当前表格的长度
        if($("#NAME").val()==""||$("#NAME").val()==undefined){
            alert("NAME没有填写！");
            return;
        }
        if($("#CODE").val()==""||$("#CODE").val()==undefined){
            alert("CODE没有填写！");
            return;
        }

        for (var m = 0; m < j; m++) {

            var optionName=$("#optionName" + m + "").val();
            var optionValue=$("#optionValue" + m + "").val();


           //已经修改的数据
            if ( (optionName!= ""&&optionName!=undefined)|| (optionValue!= ""&&optionValue!=undefined)) {

                var onedata = new Object();
                //存一条数据
                onedata.id = $("#idid" + m + "").val();
                onedata.optionValue = optionValue;
                onedata.optionName = optionName;

                data.push(onedata);
            }
        }

        for (var n = 0; n < i; n++) {
            var onedata = new Object();

            var aa = $("#id" + n + "").val();
            var bb = $("#name" + n + "").val();

                if (aa != undefined && aa != "" && bb != undefined && bb != "" ) {
                    onedata.id = 0;
                    onedata.optionName = aa;
                    onedata.optionValue = bb;

                    data.push(onedata);
                }else{
                    alert("还有数据没填写！");
                    return;
                }
        }
        //储存数据
        var action=$("#pagetitle").text();
        if(action=="新增页面"){
            if(data.length > 0 && data != "" && data != undefined){
                var param = JSON.stringify(data);
                //插入数据到页面 一起form提交
                $("#json").val(param);
                updateOraddDictionary.submit();
                i = 0;
            }else{
                updateOraddDictionary.submit();
                i = 0;
            }

        }else{
            if (data.length > 0 && data != "" && data != undefined) {
                var param = JSON.stringify(data);
                //插入数据到页面 一起form提交
                $("#json").val(param);
                updateOraddDictionary.submit();
                i = 0;
            }else{
                $("#CODE").val("");
                updateOraddDictionary.submit();
                i = 0;
            }
        }

    }
  function specialCharValidate(id,val){
      var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？\\-\\_]");
      if(val!=""){
          if(pattern.test(val)){
              $("#"+id+"").val("");
              alert("输入值包含特殊字符！");
          }
      }
  }