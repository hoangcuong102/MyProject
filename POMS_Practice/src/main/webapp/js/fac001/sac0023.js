"use strict";

var listItemObject = [];
var order = {};
var table;

$(document).ready(function() {
	$('#confirmRedirectMenu').val(true);
	$(".left-side").hide();
	table = $('#resultTable').DataTable({
		"sScrollY" : calcDataTableHeight(12),
		"bScrollCollapse" : true,
		"paging" : false,
		"searching" : false,
		"columnDefs" : [ {
			"targets" : [ 2, 3, 4, 5 ],
			"orderable" : false
		}],
		"asStripeClasses": [],
		"aoColumns" : [
			{"sClass" : "t-center no-padding-right", },
			{"sClass" : "t-center no-padding-right", },
			{"sClass" : "text-center text-ellipsis", },
			{"sClass" : "t-center", },
			{"sClass" : "t-center text-ellipsis", },
			{"sClass" : "t-center", }
		],
		"fnInitComplete" : function() {
			$('.dataTables_scrollBody').perfectScrollbar();
		},
		"dom" : '<"top"l>rt<"bottom"ip><"clear">',
		"fnRowCallback" : function(nRow, aData, iDisplayIndex,
				iDisplayIndexFull) {
			if (aData[5] != "-"){
				$(nRow).find("td:nth-child(6)").addClass("quantity");
			}
		}
	});
	getAllDataSac0023();
	$("#btnCloseOrderLog").click(function() {
		showConfirmDialog("closeOrderLog", "", [ "fac001", "sac0021" ], {
			"position" : "200"
		});
	})
});

/**
 * Draw table after search
 */
function drawData(listData) {
	var dataSet = [];
	var length = listData.length;
	table.clear();
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			var index = i + 1;
			var dataRow = [];

			var updatedAt = replaceNull(listData[i].timestamp);
			var updatedBy = replaceNull(listData[i].updatedUser);
			var from = replaceNull(listData[i].phase).replace("_", " &rarr; ");
			var status = statusText((replaceNull(listData[i].action)).toUpperCase());
			var type = replaceNull(listData[i].categoryName);
			type = (type ===  "") ? "-" : type;
			var quantity = "";
			if (listData[i].deliveryQty !== null) {
				quantity = listData[i].deliveryQty + " ("
						+ (listData[i].itemQty - listData[i].deliveryQty) + ")";
			}
			quantity = (quantity ===  "") ? "-" : quantity;
			dataRow.push(updatedAt, updatedBy, from, status, type, quantity);
			dataSet.push(dataRow);
		}
		table.rows.add(dataSet);
	}
	table.draw();
}

/**
 * 
 * @returns
 */
function statusText(value) {
	if (value == ""){
		value = "-";
	}
	var text = "";
	if (value === "Reject" || value === "REJECT") {
		text += '<span class="color-red">' + value + '</span>';
	} else if (value === "Approve" || value === "APPROVE") {
		text += '<span class="color-green">' + value + '</span>';
	} else if (value === "Deliver" || value === "DELIVER") {
		text += '<span class="color-green">' + value + '</span>';
	} else if (value === "Delete" || value === "DELETE") {
		text += '<span class="color-red">' + value + '</span>';
	} else if (value === "Update" || value === "UPDATE") {
		text += '<span class="color-black">' + value + '</span>';
	} else {
		return value;
	}
	return text;
}

/**
 * if null => return ""
 * 
 * @param value
 * @returns
 */
function replaceNull(value) {
	return (value === null || value === "null") ? "" : value;
}

function doEventDialog01(idEvent, element) {
	// In case close
	if (idEvent == "closeOrderLog") {
		$("#redirectFunctionId").val('fac001');
		$("#redirectScreenId").val('sac0021');
		$("form[id=form_menu]").submit();
	}
}

/**
 * get All data
 * 
 * @returns
 */
function getAllDataSac0023() {
	loading_ajax();
	var params = {};
	params.orderId = $("#orderId").val();
	$.ajax({
		url : '../fac001/sac0023_01',
		type : "GET",
		data : params,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			close_loading_ajax();
			order = response.orderData.order;
			listItemObject = response.orderData.listOrderStatus;

			$("#quotationNo").val(order.quoteMrc + " / " + order.quoteOtc);
			drawData(listItemObject);

		},
		error : function(jqXhr, textStatus, errorThrown) {
			$("#contentMessage").text("Error!");
			$("#modalInfo").modal("show");
			close_loading_ajax();
		}
	});
}

/**
 * Convert String with first letter Uppercase
 * @param str
 * @returns
 */
function toUpperCaseFirstLetter(str){
	var temp = str.toLowerCase();
	return temp.charAt(0).toUpperCase() + temp.slice(1);
}


/**
 * resize div when left-menu show
 * 
 * @returns
 */
function divResize(){
	if($(".left-side").is(":hidden")){
		$("#div-table-result").removeClass('col-md-9');
		$("#div-table-result").addClass('col-md-7');
	} else {
		$("#div-table-result").removeClass('col-md-7');
		$("#div-table-result").addClass('col-md-9');
	}
}