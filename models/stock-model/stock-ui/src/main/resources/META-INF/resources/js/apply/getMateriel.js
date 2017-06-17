function getMaterials(id) {
    var param = "id=" + id;
    $.ajax({
        type: "post",
        url: "/material/ajaxList.html",
        data: param,
        async: false,
        success: function (result) {
            $("#materialPanel").empty();
            $.each(result, function (i, o) {
                $("#materialPanel").append($("<div class='col-md-4 material-item'> <a onclick='CreateInput(\""+o.id+"\",\""+o.name+"\")' id='"
                    + o.id
                    + "' class='btn btn-default btn-block'>" + o.name + "</a> </div>"));
            });

            eventA();
            $("#searchcode").val("");
            materialArr = new Array;
            $("#materialPanel").find("a").each(function (i, o) {
                if (jQuery.inArray(o.parentNode, materialArr) < 0) {
                    materialArr.push(o.parentNode);
                }
            });


        }
    });
};