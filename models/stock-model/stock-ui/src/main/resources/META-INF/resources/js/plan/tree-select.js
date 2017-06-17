function SelectMateriel(id, name) {
	var values = $("#statisticsBody").find("input.form-control");
	var bo = true;
	$.each(values, function(i, o) {
		var val = $(this).val();
		if (val == id) {
			bo = false;
		}
	});
	
	if(bo){
		var hidden = "<input type='hidden' name='materielId' class='form-control' value=\"" + id + "\">";
		var materielName = "<td class='materielName'>" + name + "</rd>";
		var delOperator = "<td><span onclick='removeStatistics(this)' class='label label-default'><i class='icon-remove'></i> 删除</span></td>";
		var tr = "<tr>" + hidden + materielName + delOperator + "</tr>";
		$("#statisticsBody").append(tr);
	}
	else{
		alert("你已添加了该物料，不能重复添加！");
	}
	$("#material-modal").modal("hide");
}

function removeStatistics(o) {
	var tr = o.parentNode.parentNode;
	document.getElementById("statisticsBody").removeChild(tr);
}