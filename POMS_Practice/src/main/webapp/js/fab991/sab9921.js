/**
 * 
 */

$(document).ready(function (){
	
	$('#confirmRedirectMenu').val("true");
	$(".left-side").hide();
	setTabIndex();
	eventChooseFile();
	fixHeightDropdown($('#categoryCd'));
	$('#categoryCd a').click(function(){
		resetFieldData();
		$(".btn_delete").prop({
			  disabled: false
		});
		var category_cd = $(this).attr('data-value');
		$('#categoryCdValue').val(category_cd);
		getItem_CD(category_cd);
		
	});
	
	// autocomplete for Category_CD
	var category_cd = getdataInDropdown($('#categoryCd'));
	$('#categoryCdValue').autocomplete({
		source: category_cd,
		select: function(event,ui){
			resetFieldData();
			getItem_CD(ui.item.value);
		}
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
				resetFieldData1();
				var itemCD = $(this).attr('data-value');
				
				$('#itemCdValue').val(itemCD);
				getItemName(itemCD, categoryCode);
			});
			
			// autocomplete for Item_CD
			
			$('#itemCdValue').autocomplete({
				source: itemcdList,
				select: function(event,ui){
					resetFieldData1();
					getItemName(ui.item.value, categoryCode );
				}
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
	loading_ajax();
	$.ajax({
		cache: false,
		url: "sab9921_02",
		type: "GET",
		data: params,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(response){
			 close_loading_ajax();
			$('#itemName').val(response.pab9921Bean.item_name);
			var imageBase64=response.imageBase64;
			if(imageBase64 != undefined && imageBase64 != null ){
				$("#noImage").hide();
				$("#imageBox").show();
				$('#imageBox').attr('src', 'data:image/png;base64,'+imageBase64);
				$('#linkToImage').val(response.pab9921Bean.link_to_image);
				$('#imageName').val(response.pab9921Bean.image_name);
				$('#imageSize').val(response.pab9921Bean.image_size);
			}
			
			var pdf = response.pab9921Bean.link_to_pdf;
			if(pdf != undefined && pdf != null){
				$('#linkToPdf').val(pdf);
				$('#pdfName').val(response.pab9921Bean.pdf_name);
				$('#pdfSize').val(response.pab9921Bean.pdf_size);
				$('#pdf_name_file').show();
				
			}else{
				$('#pdf_name_file').hide();
			}
			$('#pdf_name_file').attr('onclick','downloadPdf("'+response.pab9921Bean.pdf_name+'")');
			$('#spanfilename').text(response.pab9921Bean.pdf_name);
			$('#spanpdfSize').text(' ('+response.pab9921Bean.pdf_size+')');
			
			if(response.pab9921Bean.remark == null){
				$('#remarkText').val('');
			}else{
				$('#remarkText').val(response.pab9921Bean.remark);
			}
			
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
	
	return result;
}

function fixHeightDropdown(element){
	if(element.height() > 200){
		element.height(200);
	}
}

function setTabIndex(){
	$('#categoryCdValue').attr('tabindex', '1');
	$('#itemCdValue').attr('tabindex', '2');
	$('#remarkText').attr('tabindex', '3');
	$('#btn-file-image').attr('tabindex', '4');
	$('#btn-file-pdf').attr('tabindex', '5');
	$('.btn-save').attr('tabindex', '6');
	$('.btn-close').attr('tabindex', '7');
	$('.btn_delete').attr('tabindex', '8');
	$('.myBody').keydown(function(event){
		if($('#btn-file-image').is(':focus') && event.which == 13){
			event.preventDefault();
			$('#imageData').click();
		}
		
		if($('#btn-file-pdf').is(':focus') && event.which == 13){
			event.preventDefault();
			$('#pdfData').click();
		}
	
	});
	
}

function eventChooseFile(){
	$('#file-image').mousedown(function(){
		$('#imageData').click();
	});
	$('#btn-file-image').click(function(){
		$('#imageData').click();
	});
	
	$('#file-pdf').mousedown(function(){
		$('#imageData').click();
	});
	
	$('#btn-file-pdf').click(function(){
		$('#imageData').click();
	});
}

function resetFieldData(){
	$('#sab9921')[0].reset();
	$('#noImage').show();
	$('#imageBox').hide();
	$('#imageBox').attr('src','');
	$('#pdf_name_file').attr('onclick','');
	$("#span-file-name").text("");
    $("#span-file-size").text("");
	
}

function resetFieldData1(){
	
	$("#itemCdValue").val("");
	$("#itemName").val("");
	$("#remarkText").val("");
	$("#imageData").val("");
	$("#file-image").val("");
	$("#catalogPdf").val("");
    $("#noImage").show();
    $("#imageBox").attr("src", "");
    $("#imageBox").hide();
    //$("#linkCatalogPdf").text("");
    $("#linkCatalogPdf").removeAttr("href");
    $("#linkCatalogPdf").removeAttr("onclick");
    $("#span-file-name").text("");
    $("#span-file-size").text("");
    $("#file-pdf").val("");
    $("#linkToImage").val("");
	$("#imageName").val("");
	$("#imageSize").val("");
	$("#linkToPdf").val("");
	$("#pdfName").val("");
	$("#pdfSize").val("");
}

function FormatForm(){
	
}

