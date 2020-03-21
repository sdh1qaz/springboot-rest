//点击确定按钮执行的方法
function clickButtSure() {
	sql = $('#sqlTextArea').val();
	if (sql == '') {
		alert('没有输入sql！');
		return;
	}
	if (check(sql)) {
		alert('只允许执行增删改查的sql');
		return;
	}
	if (!window.confirm('确认要执行输入的sql？')) {
		return;
	}
}

// 校验sql
function check(sql) {
	var c = sql.indexOf('insert') == -1 && sql.indexOf('INSERT') == -1
			&& sql.indexOf('delete') == -1 && sql.indexOf('DELETE') == -1
			&& sql.indexOf('update') == -1 && sql.indexOf('UPDATE') == -1
			&& sql.indexOf('select') == -1 && sql.indexOf('SELECT') == -1;
	return c;
}

/*
 * //加载的主表（对表格进行遍历 $(document).ready(function () { // var applicationId =
 * $('#applicationId option:selected').val(); $("#jqGrid").jqGrid({
 * url:"/dbmanager/transactionFlow/informationQuery", mtype: "POST", datatype:
 * "json", postData: { 'sql': function () { return $('#sql').val().trim();} },
 * styleUI: 'Bootstrap', colModel: [ <c:forEach items="${list3}" var="user"
 * varStatus="status"> {label: '${user}', name: '${user}', autowidth: true,
 * sortable: false}, </c:forEach> ], viewrecords: true, height: 400,
 * shrinkToFit: false, rowNum: 10, autowidth: true, pager: "#jqGridPager", page:
 * 1 }).trigger("reloadGrid"); return false; var len =
 * $("#jqGrid").getGridParam("width"); $("#jqGrid").setGridWidth(len); });
 * //点击重置按钮 $("#back").click(function () { $("#sql").empty(); });
 */