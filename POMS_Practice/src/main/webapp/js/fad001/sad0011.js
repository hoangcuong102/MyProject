/**
 * JS file sab0021
 */
var companyObject = {};
var userAuthorityCd;
var paramList = [];
var sankyuCd = "3";
$(document).ready(function() {
	$('#confirmRedirectMenu').val("true");
	$(".left-side").hide();
	createSelectDate();
	loadDataSad0011("", contextPath + 'fad001/loadDataSad0011Action', 'GET');
	
	// fill data combox (List country)
	$('#country').append($('<option>', {
		value : '',
		text : 'ALL'
	}));
	$.each(countryObject, function(i, item) {
		$('#country').append($('<option>', {
			value : item.countryCd,
			text : item.countryCd
		}));
	});
	
	// fill data combox (List company)
	$('#company').append($('<option>', {
		value : '',
		text : 'ALL'
	}));
	$('#company').append('<option></option>');
	$.each(companyObject, function(i, item) {
		$('#company').append($('<option>', {
			value : item.companyCd,
			text : item.companyCd
		}));
	});
	setTabIndex();
});

/**
 * Close screen
 */
function doClose(){
	showConfirmDialog("close","",["faa001", "saa0012"],{"position":"200"});
}

/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Download/Close
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element) {
	// In case close
	if (idEvent == "close"){
		$("#redirectFunctionId").val(paramForRedirectMenu[0]);
		$("#redirectScreenId").val(paramForRedirectMenu[1]);
		$("form[id=form_menu]").submit();
	}
}

function showConfirmDialog(idEvent, listButton, param, paramSettingDialog) {
	paramForRedirectMenu = param;
	var messageId = "";
	if (idEvent == "download") {
		messageId = "CM-0005";
	} else if (idEvent == "close") {
		messageId = "CM-0006";
	} else if (idEvent == "logout") {
		messageId = "CA-0001";
		var contentConfirmLogout = getMessageWithParams(messageId,"");
		document.getElementById("contentConfirmLogout").innerHTML = contentConfirmLogout;
		$('#buttonYesLogout').attr('onclick', "doYes('"+idEvent+"')");
		$("#modalConfirmLogout").modal("show");
		return;
	} else if (idEvent == "redirectMenu") {
		messageId = "CA-0002";
	}
	
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirm").innerHTML = /*messageId+" : "+ */contentConfirm;
	if (idEvent == "close"){
		$("#contentConfirm").parent().parent().css("text-align", "center");
		//$("#contentConfirm").parent().parent().css("padding", "0 5px");
	}
	if (idEvent == "download") {
		
		var outputLeaseInvoiceDataYearly = $('#outputLeaseInvoiceDataYearly')[0].checked;
		$('#buttonYes').attr('onclick', "doDataDownload()");
		/*if (outputLeaseInvoiceDataYearly) {
			$('#buttonYes').attr('onclick', "doDataDownloadYearly()");
		} else {
			$('#buttonYes').attr('onclick', "doDataDownload()");
		}*/
		
	}
	else {
		$('#buttonYes').attr('onclick', "doYes('"+idEvent+"')");
	}
	$("#modalConfirm").modal("show");
}

function createSelectDate() {
	// Create list month
	var monthMMM = new Array();
	monthMMM[1] = "Jan";
	monthMMM[2] = "Feb";
	monthMMM[3] = "Mar";
	monthMMM[4] = "Apr";
	monthMMM[5] = "May";
	monthMMM[6] = "Jun";
	monthMMM[7] = "Jul";
	monthMMM[8] = "Aug";
	monthMMM[9] = "Sep";
	monthMMM[10] = "Oct";
	monthMMM[11] = "Nov";
	monthMMM[12] = "Dec";
	
	var selectYear = $("[name='year']");
	selectYear.empty();
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	for (var i=14; i>=1; i--){
		selectYear.append('<option value="'+ (year - i) +'">'+ (year - i) +'</option>');
	}
	selectYear.append('<option value="'+ (year) +'" selected="selected">'+ (year) +'</option>');
	
	var selectMonth = $("#month");
	selectMonth.empty();
	for (var i=1; i<monthMMM.length; i++){
		if (i!=month){
			selectMonth.append('<option value="'+ (i) +'">'+ (monthMMM[i]) +'</option>');
		} else {
			selectMonth.append('<option value="'+ (i) +'" selected="selected">'+ (monthMMM[i]) +'</option>');
		}
	}
}

/**
 * download the lease invoice data and lease data.
 * 
 */
function doDowload() {
	showConfirmDialog("download","","",{"position":"200"});
}

function doDataDownload() {
	if (validate()) {
		var year = $('#year').val();
		var month = $('#month').val();
		var country = $('#country').val();
		var company = $('#company').val();
		var outputLeaseInvoiceData = $('#outputLeaseInvoiceData')[0].checked;
		var outputLeaseInvoiceDataKYearly = $('#outputLeaseInvoiceDataYearly')[0].checked;
		if (outputLeaseInvoiceDataKYearly) {
			year = $('#yearly').val();
		}
		var outputLeaseData = $('#outputLeaseData')[0].checked;
		var statusActive = $('#statusActive')[0].checked;
		window.focus();
		window.location.href = "doDataDownloadAction?year="+year
		+"&month="+month
		+"&country="+country
		+"&company="+company
		+"&outputLeaseInvoiceData="+outputLeaseInvoiceData
		+"&outputLeaseInvoiceDataKYearly="+outputLeaseInvoiceDataKYearly
		+"&outputLeaseData="+outputLeaseData
		+"&statusActive="+statusActive;
	}
}

function doDataDownloadYearly() {
	if (validate()) {
		var year = $('#yearly').val();
		var month = $('#month').val();
		var country = $('#country').val();
		var company = $('#company').val();
		var outputLeaseInvoiceData = $('#outputLeaseInvoiceDataYearly')[0].checked;
		var outputLeaseData = $('#outputLeaseData')[0].checked;
		var statusActive = $('#statusActive')[0].checked;
		// If userAuthority = '2' (KDDI Admin)
		// Include report DataK Yearly
		if (userAuthorityCd && userAuthorityCd === "2") {
			window.open("doDataDownloadAction?year="+year
					+"&month="+month
					+"&country="+country
					+"&company="+company
					+"&outputLeaseInvoiceData="+outputLeaseInvoiceData
					+"&outputLeaseInvoiceDataKYearly="+true
					+"&outputLeaseData="+outputLeaseData
					+"&statusActive="+statusActive);
		}
	}
}

/*function doDataDownload1() {
	if (validate()) {
		var year = $('#year').val();
		var month = $('#month').val();
		var country = $('#country').val();
		var company = $('#company').val();
		var outputLeaseInvoiceData = $('#outputLeaseInvoiceData')[0].checked;
		var outputLeaseData = $('#outputLeaseData')[0].checked;
		var statusActive = $('#statusActive')[0].checked;
		window.location.href = "doDataDownloadAction?year="+year
		+"&month="+month
		+"&country="+country
		+"&company="+company
		+"&outputLeaseInvoiceData="+outputLeaseInvoiceData
		+"&outputLeaseData="+outputLeaseData
		+"&statusActive="+statusActive;
		var a = document.createElement('a');
		 a.href = "doDataDownloadAction?year="+year
			+"&month="+month
			+"&country="+country
			+"&company="+company
			+"&outputLeaseInvoiceData="+outputLeaseInvoiceData
			+"&outputLeaseData="+outputLeaseData
			+"&statusActive="+statusActive;
		 a.target = '_self';
		 (document.body || document.documentElement).appendChild(a);
		 a.click();
		var open1 = window.open("doDataDownloadAction?year="+year
				+"&month="+month
				+"&country="+country
				+"&company="+company
				+"&outputLeaseInvoiceData="+outputLeaseInvoiceData
				+"&outputLeaseInvoiceDataKYearly="+true
				+"&outputLeaseData="+outputLeaseData
				+"&statusActive="+statusActive);

		setTimeout(function(){
			var open2 = window.open("doDataDownloadAction?year="+year
					+"&month="+month
					+"&country="+country
					+"&company="+company
					+"&outputLeaseInvoiceData="+outputLeaseInvoiceData
					
					+"&outputLeaseData="+outputLeaseData
					+"&statusActive="+statusActive);
		}, 500);
		window.focus();
	}
}*/

/**
 * Load list company
 * 
 * @param params
 *            data
 * @param actionName
 *            action name
 * @param methodType
 *            type
 */
function loadDataSad0011(params, actionName, methodType) {
	loading_ajax();
	$.ajax({
		url : actionName,
		type : methodType,
		data : params,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(response) {
			close_loading_ajax();
			companyObject = response.companyList;
			userAuthorityCd = response.userAuthorityCd;
			if (response.userAuthorityCd && response.userAuthorityCd === sankyuCd) {
				$("#monthlySummary").hide();
				$('#outputLeaseInvoiceData').removeAttr("checked");
				$('#outputLeaseInvoiceData').attr("disabled", true);
				$('#outputLeaseInvoiceDataYearly').attr("checked", true);
				// $("#divYearly").hide();
			}
			
		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});
}
/**
 * remove all error
 * @returns
 */
function removeError() {
	$("#year").removeClass("border-error");
	$("#month").removeClass("border-error");
	$("#errorYear, #errorMonth").hide();
}

function checkEmpty(valueCheck) {
	if (valueCheck==undefined || valueCheck=="") {
		return true;
	}
	return false;
}

function validate() {
	removeError();
	var flag = true;
	var year = $("#year").val();
	var yearTextSelect = $('#year option:selected').text();
    if (checkEmpty(year) || checkEmpty(yearTextSelect)){
    	flag = false;
    	$("#year").addClass("border-error");
    	$("#errorYear").show();
    	$("#errorYear").append(generateHtmlError("CM-0006", ""));
    }
    
	var month = $("#month").val();
    if (month==undefined || month==""){
    	flag = false;
    	$("#month").addClass("border-error");
    	$("#errorMonth").show();
    	$("#errorMonth").append(generateHtmlError("CM-0007", ""));
    }
    return flag;
}

/**
 * Set tab index
 */
function setTabIndex() {
	$("#outputLeaseInvoiceData").attr("tabindex", "1");
	$("#year").attr("tabindex", "2");
	$("#month").attr("tabindex", "3");
	$("#outputLeaseData").attr("tabindex", "4");
	$("#country").attr("tabindex", "5");
	$("#company").attr("tabindex", "6");
	$("#statusActive").attr("tabindex", "7");
	$(".btn-download").attr("tabindex", "8");
	$(".btn-close").attr("tabindex", "9");
	$(".btn-logout").attr("tabindex", "9");
	
	$("#formSad0011").keydown(function(e){
		if($('#month').is(":focus") && (e.which || e.keyCode) == 9 && !event.shiftKey) {
			e.preventDefault();
			//$("#outputLeaseData").prop("checked", true);
			$("#outputLeaseData").first().focus();
			return;
		}
		if($('#year').is(":focus") && (e.which || e.keyCode) == 9 && event.shiftKey) {
			e.preventDefault();
			//$("#outputLeaseInvoiceData").prop("checked", true);
			$("#outputLeaseInvoiceData").first().focus();
			return;
		}
		if($('#outputLeaseInvoiceData').is(":focus") && (e.which || e.keyCode) == 9 && event.shiftKey) {
			e.preventDefault();
			$(".btn-logout").first().focus();
			return;
		}
		if($('#country').is(":focus") && (e.which || e.keyCode) == 9 && event.shiftKey) {
			e.preventDefault();
			//$("#outputLeaseData").prop("checked", true);
			$("#outputLeaseData").first().focus();
			return;
		}
	});
	
	$(".user-login-css").keydown(function(e){
		if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 9 && !event.shiftKey) {
			e.preventDefault();
			if (!$('#outputLeaseInvoiceData')[0].disabled) {
				$("#outputLeaseInvoiceData").prop("checked", true);
				$("#outputLeaseInvoiceData").first().focus();
			} else {
				$("#outputLeaseInvoiceDataYearly").prop("checked", true);
				$("#outputLeaseInvoiceDataYearly").first().focus();
			}
			
		}
	});
}
