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
var id_arr = [ '688c70f92e92ff64540b25e5dacf9d60',
		'6898305e2e88a71024912da539f9abdb', 'f67fd42cac0cc2af4320b22239bf102e',
		'deefc6b7063a8bb9d91b0a973fbe8a6c', '44817ee8d8603d84a62397ab3a34939e',
		'fd6fd185e79fda33c6d044c3e5bea867', '263ec18930ba17c8deef6b041d2b37d8',
		'46e4a504257561318ed909febda8c98c', '22c51b37898344ff52e617f5e48e9561',
		'b1bf294f3cfe26fd92191fab5bb5b09d', 'fb1e6f608a0105d8a9a590c567a6cda1' ];
// 点击确定按钮执行的方法
function qryHistory() {
	var execId = $('#execId').val(); // 如果该ID没有权限，返回
	if (execId == '') {
		alert("请输入操作人ID！");
		return;
	}
	// 如果该ID没有权限，返回
	if (!checkID(execId)) {
		alert("该ID没有权限！");
		return;
	}
	window.location.href = '/dbmanager/getExecHistory?operId=' + execId;

}
function clickButtSure() {
	$("#content_right").empty();

	var execId = $('#execId').val(); // 如果该ID没有权限，返回
	if (execId == '') {
		alert("请输入操作人ID！");
		return;
	}
	// 如果该ID没有权限，返回
	if (!checkID(execId)) {
		alert("该ID没有权限！");
		return;
	}

	var sql = $('#sqlTextArea').val();
	if (sql == '') {
		alert('没有输入sql！');
		return;
	}
	if (check(sql)) {
		alert('只允许执行增删改查的sql');
		return;
	}
	// if (!window.confirm('确认要执行输入的sql？')) { return; }
	var jqdata;
	$.ajax({
		url : "/dbmanager/execSql",
		type : "POST",
		data : {
			sql : sql,
			operId : $('#execId').val()
		},
		dataType : 'text',
		success : function(data) {
			if (data.indexOf("异常：") == 0) {
				// alert(data);
				$("#resut_exec").empty();// 清空resut_exec下子节点
				$("#grid").hide();// 隐藏grid下子节点
				var $p_error = $("<p>执行失败，" + data + "</p>");
				$("#resut_exec").append($p_error);
				return;
			}
			// 返回的是字符串为数字，说明执行的不是select语句
			if (!isNaN(data)) {
				// alert('执行成功，影响的行数为：' + data);
				$("#resut_exec").empty();// 清空resut_exec下子节点
				$("#grid").hide();// 隐藏grid下子节点
				var $p_success = $("<p>执行成功，影响的行数：" + data + "</p>");
				$("#resut_exec").append($p_success);
				return;
			}
			var data_json = eval(data);
			// 如果查询的结果数组长度为0
			if (getJsonLength(data_json) == 0) {
				// alert("0");
				$("#resut_exec").empty();// 清空resut_exec下子节点
				var $p_select = $("<p>执行成功，查到0条记录！</p>");
				$("#resut_exec").append($p_select);
				return;
			}
			// 返回的不是数字，说明执行的是select语句
			$("#resut_exec").empty();// 清空resut_exec下子节点
			var $p_select = $("<p>查询成功，结果如下：</p>");
			$("#resut_exec").append($p_select);
			$("#grid").show();// 显示grid下子节点
			pageInit(data_json);
		}
	});

}

// 点击清空按钮执行的方法
function clickButtEmpty() {
	$('#execId').val('');// 执行人Id输入框清空
	$('#sqlTextArea').val('');// 输入的sql清空
	jQuery("#jqGrid").GridUnload();// 删除表原有数据
	$("#resut_exec").empty();// 清空resut_exec下子节点
	$("#content_right").empty();
}

function checkID(id) {
	// md5加密
	var id_md5 = md5(id);
	// 和数组id_arr比对
	for (var i = 0; i < id_arr.length; i++) {
		if (id_md5 == id_arr[i]) {
			return true;
		}
	}
	return false;

}

// 校验sql
function check(sql_exec) {
	var sql = sql_exec.toLowerCase();
	var c = sql.indexOf('insert') == -1 && sql.indexOf('delete') == -1
			&& sql.indexOf('update') == -1 && sql.indexOf('select') == -1
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
			editable : true
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
		mtype : "post",// 排序顺序，升序或者降序（asc or desc）
		viewrecords : true,// 是否要显示总记录数
		autowidth : true,// 宽度自适应
		height : 500,// 高度
		ExpandColClick : true,
		forceFit : true,//
		caption : "查询语句的结果",// 表格的标题名字
		onCellSelect : function(rowid) {
			showRowData(rowid);
		}
	});
	// 将jqdata的值循环添加进jqGrid
	for (var i = 0; i <= jqdata.length; i++) {
		jQuery("#jqGrid").jqGrid('addRowData', i + 1, jqdata[i]);
	}
	$("#bsdata").click(function() {
		jQuery("#jqGrid").jqGrid('searchGrid', {
			sopt : [ 'cn', 'bw', 'eq', 'ne', 'lt', 'gt', 'ew' ]
		});
	});
}

function showRowData(rowid) {
	var rowData = jQuery("#jqGrid").jqGrid('getRowData', rowid);
	var rowData_str = "";
	// 遍历显示json对象
	for ( var key in rowData) {
		rowData_str += "<xmp style='white-space:normal;font-size:16px;margin-top: 0px;margin-bottom: 0px;margin-left: 30px;'>"
				+ key + " : " + rowData[key] + "</xmp>" + "<br>";
	}
	$("#content_right").html(rowData_str);// 被<xmp></xmp>包含的html代码将在网页上显示出来
}

// 获取json数组的长度
function getJsonLength(jsonData) {
	var jsonLength = 0;
	for ( var item in jsonData) {
		jsonLength++;
	}
	return jsonLength;
}