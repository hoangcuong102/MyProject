
var data = [];

var dataTable;

var params = {
		companyCd : '',
		entryDateFrom : '',
		entryDateTo : '',
		nonDelivery : false,
		expiringOrders : false
};
$(document)
		.ready(
				function() {
					$('#confirmRedirectMenu').val(true);
					$(".left-side").hide();

					// create date time picker and link between them
					$('#datetimepickerF').datepicker({
						language : 'en',
						autoclose : true,
						todayHighlight : true,
						forceParse : false,
						format : "dd/mm/yyyy"
					}).on(
							"changeDate",
							function(e) {
								$('#datetimepickerT').datepicker(
										"setStartDate",
										$("#datetimepickerF").datepicker(
												"getDate"));
							})
					$('#datetimepickerT').datepicker({
						language : 'en',
						autoclose : true,
						todayHighlight : true,
						forceParse : false,
						format : "dd/mm/yyyy"
					}).on(
							"changeDate",
							function(e) {
								$('#datetimepickerF').datepicker(
										"setEndDate",
										$("#datetimepickerT").datepicker(
												"getDate"));
							})

					// call ajax for data
					callAjax(
							[],
							'sac9921_01',
							'json',
							makeUpData,
							 function() {
								return;
							});

					// prebtn for datatable api

					// $('#fromDate .input-group.date').datepicker({
					// format: "dd/mm/yyyy"
					// });
					// $('#toDate .input-group.date').datepicker({
					// format: "dd/mm/yyyy"
					// });
				});

/**
 * process data from server
 * 
 */
makeUpData = function (response) {
	// pick up data
	data = response.data;
	// pre data to blind to data table

	// trim, remove null, validate, init data
	// colspan
	$(data)
			.each(
					function(index, dataObj) {
						for ( var property in dataObj) {
							switch (property) {
							case 'UPDATED_DT_LOCAL':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'SANKYU_REGIST_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'SANKYU_ORDER_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'SEA_REQUEST_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'KDDI_ACCEPT_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'KDDI_ORDER_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'KDDI_RECEIVE_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'KDDI_LEASE_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							case 'DELIVER_DT':
								dataObj[property] = dateTimeParse(dataObj[property]);
								break;
							default:
								dataObj[property] = replaceNull(dataObj[property]);
							}
						}
						var tmp = dataObj.ORDER_ID
								.split(" ");
						dataObj.BTN = "";
						dataObj.STATUS = "";
						dataObj.ORDER_ID = tmp[0];
						dataObj.CCODE = tmp[1];
						if (dataObj.DELIVER_DT != "") {
							dataObj.KDDI_DELIVER_DT = "<div class=\"flex-container\">"
									+ "<div class=\"text-center border-right\" style=\"flex:  25%;\">"
									+ dataObj.total
									+ "</div>"
									+ "<div class=\"text-center\" style=\"flex:  25%;\">"
									+ dataObj.DELIVER_DT
									+ "</div>"
									+ "</div>";
						} else
							dataObj.KDDI_DELIVER_DT = "<div class=\"flex-container\">"
									+ "<div class=\"text-center\" style=\"flex:  25%;\">"
									+ ""
									+ "</div>"
									+ "<div class=\"text-center\" style=\"flex:  25%;\">"
									+ ""
									+ "</div>"
									+ "</div>";
					});
	// init data in rowspan

	data = groupBy(data, [ "BTN", "ORDER_ID",
			"DEPT_CD", "TTL_MRC", "TTL_OTC",
			"UPDATED_DT_LOCAL", "UPDATED_USER",
			"SANKYU_REGIST_DT", "SANKYU_ORDER_DT",
			"SEA_REQUEST_DT", "KDDI_ACCEPT_DT",
			"KDDI_ORDER_DT", "KDDI_RECEIVE_DT",
			"KDDI_LEASE_DT", "CCODE"])

	// merge type and qty
	$(data)
			.each(
					function(index, dataObj) {
						var list1 = (dataObj.CATEGORY_ABBREV + "")
								.split(",");
						var list2 = (dataObj.ITEM_QTY + "")
								.split(",");
						var list3 = (dataObj.KDDI_DELIVER_DT + "")
								.split(",");
						var list4 = (dataObj.STATUS + "")
								.split(",");
						var arr1 = []
						var arr2 = []
						var arr4 = []
						for (i = 0; i < list1.length; i++) {
							if (arr1
									.includes(list1[i])) {
								var y = arr1
										.indexOf(list1[i])
								arr4[y] += 1
							} else {
								arr1.push(list1[i])
								arr2.push(list2[i])
								arr4.push(1)
							}
						}
						html1 = "<div class=\"\">";
						html2 = "<div class=\"\">";
						html3 = "<div class=\"\">";
						html4 = "<div class=\"\">";
						for (i = 0; i < arr1.length; i++) {
							if(i == arr1.length - 1) {
								html1 += "<div class=\"text-center\" style=\"flex:  100%; height: ";
								html2 += "<div class=\"text-center\" style=\"flex:  100%; height: ";
							} else {
								html1 += "<div class=\"text-center border-bottom\" style=\"flex:  100%; height: ";
								html2 += "<div class=\"text-center border-bottom\" style=\"flex:  100%; height: ";
							}
							html1 += arr4[i]
									* 25
									+ "px; line-height: "
									+ arr4[i]
									* 25
									+ "px;\">"
									+ arr1[i]
									+ "</div>";
							html2 += arr4[i]
									* 25
									+ "px; line-height: "
									+ arr4[i]
									* 25
									+ "px;\">"
									+ arr2[i]
									+ "</div>";
						}
						// deliver
						for (i = 0; i < list3.length; i++) {
							if ( i == list3.length - 1) {
								html3 += "<div class=\"text-center\" style=\"flex:  100%; height: "
							} else 
								html3 += "<div class=\"text-center border-bottom\" style=\"flex:  100%; height: "
							html3 += 25
									+ "px; line-height: "
									+ 25
									+ "px;\">"
									+ list3[i]
									+ "</div>";
						}
						var approve = "";
						var reject = "";
						// status
						html4 += "<div class=\"text-center flex-container-center\" height: "
								+ arr4.reduce((a, b) => a + b, 0) * 25
								+ "px; line-height: "
								+ 25
								+ "px;\">"
								+ '<button class="btn-reject" onclick="doReject(' + dataObj.ORDER_ID + " " + dataObj.CCODE + ')"> &lt; </button>'
								+ '<button class="btn-approve" onclick="doApprove(' + dataObj.ORDER_ID + " " + dataObj.CCODE + ')"> &gt; </button>'
								+ "</div>";
						dataObj.CATEGORY_ABBREV = html1
								+ "</div>";
						dataObj.ITEM_QTY = html2
								+ "</div>";
						dataObj.KDDI_DELIVER_DT = html3
								+ "</div>";
						dataObj.STATUS = html4
								+ "</div>";
					})
	if(dataTable == null) {
		// blind data to data table
		dataTable = $('#data_demo')
				.DataTable(
						{
							"ordering" : false,
							"scrollY" : "500px",
							"scrollCollapse" : true,
							"data" : data,
							"fnInitComplete" : function() {
								$('.dataTables_scrollBody').perfectScrollbar();
							},
							"stripeClasses": [],
							"dom" : "<'row'<'col-sm-12'tr>><'row'<'col-sm-5'i><'col-sm-7'p>>",
							"columns" : [
									{
										"data" : "BTN",
										"render" : function(
												data,
												type,
												row) {
											return '<label><input type="radio" name="optradio" value="'
													+ row
													+ '"></label>';
										}
									},
									{
										"data" : "ORDER_ID"
									},
									{
										"data" : "DEPT_CD"
									},
									{
										"data" : "TTL_MRC"
									},
									{
										"data" : "TTL_OTC"
									},
									{
										"data" : "CATEGORY_ABBREV",
										"render" : function(
												data,
												type,
												row) {
											return data
													+ "";
										}
									},
									{
										"data" : "ITEM_QTY"
									},
									{
										"data" : "UPDATED_DT_LOCAL"
									},
									{
										"data" : "UPDATED_USER"
									},
									{
										"data" : "STATUS",
										"render" : function(
												data,
												type,
												row) {
											return data
													+ "";
										}
									},
									{
										"data" : "SANKYU_REGIST_DT"
									},
									{
										"data" : "SANKYU_ORDER_DT"
									},
									{
										"data" : "SEA_REQUEST_DT"
									},
									{
										"data" : "KDDI_ACCEPT_DT"
									},
									{
										"data" : "KDDI_ORDER_DT"
									},
									{
										"data" : "KDDI_RECEIVE_DT"
									},
									{
										"data" : "KDDI_DELIVER_DT"
									},
									{
										"data" : "KDDI_LEASE_DT"
									} ]
						});
	} else {
		dataTable.clear().rows.add(data).draw()
	}
}


/**
 * on chage CompanyCode chage data show at datatable on search
 * 
 * 
 */
function reload() {
	//set param
	params.companyCd = $("#form_cc").val()
	// call ajax for data
	callAjax(
			params,
			'sac9921_01',
			'json',
			makeUpData,
			 function() {
				return;
			});
}



/**
 * array container obj compare by those key
 * 
 * 
 * 
 */
var containObyK = function(arr, O, K) {
	if (arr.length == 0)
		return -1;
	for (var i = 0; i < arr.length; i++) {
		if (compareObyK(arr[i], O, K))
			return i;
	}
	return -1;
}
/**
 * compare obj by key
 * 
 * 
 * 
 */
var compareObyK = function(O1, O2, keys) {
	for (var y = 0; y < keys.length; y++) {
		if (O1[keys[y]] != O2[keys[y]])
			return false;
	}
	return true;
}
/**
 * group data in array by key
 * 
 * 
 * 
 */
var groupBy = function(xs, gkey) {
	return xs.reduce(function(ar, x) {
		var i = containObyK(ar, x, gkey);
		if (i > -1) {
			keys = Object.keys(x);
			for (var y = 0; y < keys.length; y++) {
				if (!gkey.includes(keys[y])) {
					ar[i][keys[y]] = ar[i][keys[y]] + "," + x[keys[y]];
				}
			}
		} else {
			ar.push(x);
		}
		return ar;
	}, [])
};

/**
 * eleminate null, undefined
 * 
 * 
 * 
 * 
 */
function replaceNull(params) {
	return params == null ? "" : params;
}

/**
 * convert string to date format date <->
 * 
 * 
 * 
 */
function dateTimeParse(params) {
	try {
		if (params === null || params === '')
			return "";
		var date = new Date(params);
		return "" + date.toUTCString().split(" ").slice(1, 3).join("-");
	} catch (e) {
		return params;
	}
}

/**
 * call ajax when request data or pass action to server
 * 
 * @param params
 * @param actionName
 * @param methodType
 * @returns
 */
function callAjax(params, actionName, methodType, finishOrder, errorOrder) {
	disableAllBtn();
	// Call ajax load data
	loading_ajax();
	$.ajax({
		cache : false,
		url : actionName,
		type : methodType,
		data : params,
		traditional: true,
		dataType : "json",
		success : function(response) {
			finishOrder(response);
			close_loading_ajax();
		},
		error : function(jqXhr, textStatus, errorThrown) {
			errorOrder();
			alert(textStatus);
			close_loading_ajax();
		}
	});
}

/**
 * disabled all button
 * 
 * @returns
 */
function disableAllBtn() {
	// $("#cancel").attr("disabled", true);
}