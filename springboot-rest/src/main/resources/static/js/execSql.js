/*var jqdata = [ {
	"userid" : "01",
	"username" : "皮皮虾",
	"password" : "biubiubiu",
	"madan":"12"
}, {
	"userid" : "02",
	"username" : "象拔蚌",
	"password" : "boomboomboom",
	"madan":"12"
} ]*/

// 点击确定按钮执行的方法
function clickButtSure() {
	var sql = $('#sqlTextArea').val();
	if (sql == '') {
		alert('没有输入sql！');
		return;
	}
	/*
	 * if (check(sql)) { alert('只允许执行增删改查的sql'); return; }
	 *//*
		 * if (!window.confirm('确认要执行输入的sql？')) { return; }
		 */
	var jqdata;
	$.ajax({
		url : "/dbmanager/execSql",
		type : "POST",
		data : {sql:sql},
		dataType : 'text',
		success : function(data) {
			//返回的是字符串为数字，说明执行的不是select语句
			if (!isNaN(data)) {
				alert('执行成功，影响的行数为：' + data);
				return;
			}
			//返回的不是数字，说明执行的是select语句
			pageInit(eval(data));
		}
	});
	
}

// 点击清空按钮执行的方法
function clickButtEmpty() {
	$('#sqlTextArea').val('');// 输入的sql清空
	jQuery("#jqGrid").GridUnload();// 删除表原有数据
}

// 校验sql
function check(sql) {
	var c = sql.indexOf('insert') == -1 && sql.indexOf('INSERT') == -1
			&& sql.indexOf('delete') == -1 && sql.indexOf('DELETE') == -1
			&& sql.indexOf('update') == -1 && sql.indexOf('UPDATE') == -1
			&& sql.indexOf('select') == -1 && sql.indexOf('SELECT') == -1;
	return c;
}

/**
 * jqgrid表格生成
 * 
 * @param jqdata
 *            json对象数组
 * @returns
 */
function pageInit(jqdata) {
	jQuery("#jqGrid").GridUnload();// 删除表原有数据
	var names = [];
	var model = [];
	// 此处因为数据源数组中的结构相同且不为空，直接遍历索引为0的数据即可
	$.each(jqdata[0], function(key, value) {
		names.push(key);
		model.push({
			name : key,
			index : key,
			width : 100
		});
	});
	// 创建jqGrid组件
	jQuery("#jqGrid").jqGrid({
		datatype : "json",// 请求数据返回的类型。可选json,xml,txt
		colNames : names,// jqGrid的列显示名字
		colModel : model,
		rowNum : 10,// 在grid上显示记录条数，这个参数是要被传递到后台
		rowList : [ 10, 20, 30 ],// 可供用户选择一页显示多少条
		pager : '#jqGridPager',// 一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		sortname : 'id',// 初始化的时候排序的字段
		sortorder : "desc",// 排序方式,可选desc,asc
		mtype : "post",// 排序顺序，升序或者降序（asc or desc）
		viewrecords : true,// 是否要显示总记录数
		caption : "查询语句的结果"// 表格的标题名字
	});
	// 将jqdata的值循环添加进jqGrid
	for (var i = 0; i <= jqdata.length; i++) {
		jQuery("#jqGrid").jqGrid('addRowData', i + 1, jqdata[i]);
	}
}