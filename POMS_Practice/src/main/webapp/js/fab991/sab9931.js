/**
 * created by DuyNK
 * 21/06/2018
 */
var tableActiveItem;
var tableAllItem;
var tableInactiveItem;

var listActiveItem;
var listAllItem;
var listInactiveItem;

var listActiveItemNew;
var listInactiveItemNew;

var changed = false;


$(document).ready(function() {
	$('#confirmRedirectMenu').val(true);
	$(".left-side").hide();
	tableActiveItem = settingTable('#activeItem');
	tableAllItem = settingTable('#allItem');
	tableInactiveItem = settingTable('#inactiveItem');
});


//comboBox category or country onchange
function resetData() {

	if($("#categoryCD").val() != ""){
		removeErrorCategory();
	}
	
	if($("#countryCD").val() != ""){
		removeErrorCountry();
	}
	
	if($("#countryCD").val() != "" && $("#categoryCD").val() != ""){
		getDataItem();
	}
}

//setting table
function settingTable(tableID) {
	var table = $(tableID).DataTable({
		"columnDefs" : [ {
			"targets" : [ 0, 1 ],
			"orderable" : false
		} ],
		scrollY : "300px",
		scrollCollapse : true,
		paging : false,
		ordering: false,
		language: {
		      "emptyTable": "No data available in table"
		    },
		"aoColumns" : [ {"sClass" : "align-center"},
			 			{"sClass" : "align-left"},],
		"fnInitComplete" : function() {
			$('.dataTables_scrollBody').perfectScrollbar();
		},
		"fnDrawCallback": function ( oSettings ) {
		    $(oSettings.nTHead).hide();
		},
		"dom" : 't<"clear">'
	});
	$('.dataTables_scrollHead').remove();
	$(tableID).removeAttr("class");
	$(tableID).attr("class","data-table");
	$('.dataTables_empty').attr("valign","data-table");
	$('.dataTables_empty').attr("class","noBorder");
	return table;
}

//draw data after get data by ajax
function drawDatatable(table,list,type){
	table.clear();
	var dataSet = [];
	var active;
	if(type=="active")	active = "1";
	else if (type=="all")	active = "-1";
	else if (type=="inactive")	active = "0";
	
	//add row to table
	if(list != null && list.length > 0){
		for (var i = 0; i < list.length; i++) {
			var dataRow = [];
			var checkbox = "<input type='checkbox' id='itemCD' name='checkbox' value='" + list[i].itemCD + "'/>";
			dataRow.push(checkbox, list[i].itemName);
			dataSet.push(dataRow);
		}
	}
	table.rows.add(dataSet);
	table.draw();
}


//get data from server and draw data to table
function getDataItem() {
	var Pab9931Form = {};
	Pab9931Form.countryCD = $("#countryCD").val();
	Pab9931Form.categoryCD = $("#categoryCD").val();
	listActiveItem = null;
	listAllItem = null;
	listInactiveItem = null;
	loading_ajax();
	$.ajax({
				cache : false,
				url : 'sab9931_getDataItem',
				type : 'GET',
				data : Pab9931Form,
				dataType : 'json',
				success : function(response) {
					close_loading_ajax();
					listAllItem = response.listAllItem;
					listActiveItem = response.listItemActive;
					listInactiveItem = response.listItemInactive;

					//draw data
					drawDatatable(tableAllItem,listAllItem,'all')
					drawDatatable(tableActiveItem,listActiveItem,'active')
					drawDatatable(tableInactiveItem,listInactiveItem,"inactive")
					$('.dataTables_empty').attr("valign","data-table");
					$('.dataTables_empty').attr("class","noBorder");
				},
				error:function(jqXhr, textStatus, errorThrown){
	                alert(textStatus);
	                close_loading_ajax();
	            }
			})
}

//get new data from table
function getNewData(table) {
	var list = [];
	var data = table.rows().data();
	if(data.length == 0 ) list = null;
	else{
		for(var i = 0; i < data.length; i ++){
			var row = data[i];
			var itemCD = row[0].substring(58, row[0].length-3);
			list.push(itemCD);
		}
	}
	return (list == null || list.length == 0) ? null : list;
}

//call ajax to save data to server
function saveData() {
	//get new list item active and inactive
	listActiveItemNew = getNewData(tableActiveItem);
	listInactiveItemNew = getNewData(tableInactiveItem);
	
	var param = {};
	param.listActiveItemCD = listActiveItemNew;
	param.listInactiveItemCD = listInactiveItemNew;
	param.countryCD = $("#countryCD").val();
	param.categoryCD = $("#categoryCD").val();
	loading_ajax();
	$.ajax({
		cache : false,
		url : 'sab9931_saveData',
		type : 'POST',
		data : param,
		dataType : 'json',
		traditional: true,
		success : function() {
			close_loading_ajax();
			resetData();
		},
		error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
	})
}


//save data to server
function doSave() {
	if(validate()){
		showConfirmDialog("save","","",{"position":"200"});
		$('#buttonYes').attr('onclick', "saveData()");
	}
}


//move active item to all item
function activeToAll() {
    $("#activeItem input[name='checkbox']:checked").each(function(){
    	var data = tableActiveItem.row($(this).parents('tr')).data();
    	tableActiveItem.row( $(this).parents('tr')).remove().draw();
    	tableAllItem.row.add(data).draw();    	
    });
    $('.dataTables_empty').attr("valign","data-table");
	$('.dataTables_empty').attr("class","noBorder");
	changed = true;
}

//move all item to active item
function allToActive() {
    $("#allItem input[name='checkbox']:checked").each(function(){
    	var data = tableAllItem.row($(this).parents('tr')).data();
    	tableAllItem.row( $(this).parents('tr')).remove().draw();
    	tableActiveItem.row.add(data).draw();    	
    });
    $('.dataTables_empty').attr("valign","data-table");
	$('.dataTables_empty').attr("class","noBorder");
	changed = true;
}

//move inactive item to all item
function inactiveToAll() {
    $("#inactiveItem input[name='checkbox']:checked").each(function(){
    	var data = tableInactiveItem.row($(this).parents('tr')).data();
    	tableInactiveItem.row( $(this).parents('tr')).remove().draw();
    	tableAllItem.row.add(data).draw();    	
    });
    $('.dataTables_empty').attr("valign","data-table");
	$('.dataTables_empty').attr("class","noBorder");
	changed = true;
}

//move active item to all item
function allToInactive() {
    $("#allItem input[name='checkbox']:checked").each(function(){
    	var data = tableAllItem.row($(this).parents('tr')).data();
    	tableAllItem.row( $(this).parents('tr')).remove().draw();
    	tableInactiveItem.row.add(data).draw();  	
    });
    $('.dataTables_empty').attr("valign","data-table");
	$('.dataTables_empty').attr("class","noBorder");
	changed = true;
}

function validate() {
	removeErrorCategory();
	removeErrorCountry();
	var k = 0;
	if($("#countryCD").val().trim() == "" ||  $("#countryCD").val() == undefined || $("#countryCD").val() == null){
		$("#countryError").append(generateHtmlError("EB-0007", ""));
		$('#countryCD').addClass('border-red');
		k++;
	}
		
	if($("#categoryCD").val().trim() == "" ||  $("#categoryCD").val() == undefined || $("#categoryCD").val() == null){
		$("#categoryError").append(generateHtmlError("EB-0003", ""));
		$('#categoryCD').addClass('border-red');
		k++;
	}
	return (k!=0) ? false : true;
}

//remove text notify and border red
function removeErrorCategory() {
	$('#categoryCD').removeClass('border-red');
	$("#categoryError").empty();
}

function removeErrorCountry() {
	$('#countryCD').removeClass('border-red');
	$("#countryError").empty();
}

//close screen
function doClose(){
	if (changed){
		showConfirmDialog("close","","",{"position":"200"});
	} else {
		paramForRedirectMenu = ["faa001", "saa0012"];
		$("#redirectFunctionId").val(paramForRedirectMenu[0]);
		$("#redirectScreenId").val(paramForRedirectMenu[1]);
		$("form[id=form_menu]").submit();
	}
}

/*close tab CloseConfirm*/
function doCloseConfirmPending(){
	$("#modalConfirmPending").modal("hide");
}
