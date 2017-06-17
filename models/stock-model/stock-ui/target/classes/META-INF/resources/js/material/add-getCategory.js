/**
 * Created by lujiasheng on 2017/2/4.
 */

var num = 0;
function rechoose(){

    $("#materialCategory").empty();
    $("#materialCategory").append($("<a class='form-control' data-toggle='modal' href='#material-modal'>选择物料分类</a>"));
}
function getMaterials(id){
    if (num == 0) {
        $("#modal-footer").append($("<button id='"+id+"' type='button' onclick='setCategory(id)' class='btn'>选择</button>"));
        num++;
    }else {
        $("#modal-footer").empty();
        $("#modal-footer").append($("<button type='button' data-dismiss='modal' class='btn'>关闭</button> " +
            "<button id='"+id+"' type='button' onclick='setCategory(id)' class='btn'>选择</button>"));
    }

}

function setCategory(id){
    var param = id;
    $.ajax({
        type: "post",
        url: "/category/ajaxFindById.html",
        data: {"superiorId":param},
        async: false,
        success: function (result) {

            $("#materialCategory").empty();
            $("#materialCategory").append($("<select class='form-control' id='materielCategoryId' name='materielCategoryId' onclick='rechoose()'><option value='"+result.id+"'>"+result.name+"</option> </select>"));
            $("#material-modal").modal("hide");
        }
    });

}
