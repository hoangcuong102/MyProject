/**
 * 
 */

$(document).ready(function (){
	$('#confirmRedirectMenu').val("true");
	$(".left-side").hide();
	fixHeightDropdown($('#categoryCd'));
	$('#categoryCd a').click(function(){
		var category_cd = $(this).attr('data-value');
		$('#categoryCdValue').val(category_cd);
		getItem_CD(category_cd);
		
	});
});


function getItem_CD(categoryCode){
	var itemcdList=[];
	var params={};
	params.category_cd = categoryCode;
	loading_ajax();
	$.ajax({
		cache: false,
		url: "sab9921_01",
		type: "GET",
		data: params,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(response){
			close_loading_ajax();
			itemcdList = response.listItemCd;		
			$('#categoryName').val(response.pab9921Bean.category_name);
			$('#itemCd').empty();
			for(var i=0;i<itemcdList.length;i++){
				var li = '<li><a href="#" data-value="'+itemcdList[i]+'">'+itemcdList[i]+'</a></li>'
				$('#itemCd').append(li);
			}
			fixHeightDropdown($('#itemCd'));
			$('#itemCd a').click(function(){
				var itemCD = $(this).attr('data-value');
				
				$('#itemCdValue').val(itemCD);
				getItemName(itemCD, categoryCode);
			});
		},
		error: function(jqXhr, textStatus, errorThrown){
			alert(textStatus);
			close_loading_ajax();
		}
	});
	
	
}

function getItemName(itemcode,categoryCode){
	var params ={};
	params.item_cd = itemcode;
	params.category_cd = categoryCode;
	$.ajax({
		cache: false,
		url: "sab9921_02",
		type: "GET",
		data: params,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(response){
			$('#itemName').val(response.pab9921Bean.item_name);
		},
		error: function(jqXhr, textStatus, errorThrown){
			alert(textStatus);
			close_loading_ajax();
		}
	});
}

function getdataInDropdown(element){
	var result = [];
	element.find('li').each(function(index){
		result.push($(this).text());
	});
	alert(result);
	return result;
}

function fixHeightDropdown(element){
	if(element.height() > 200){
		element.height(200);
	}
}