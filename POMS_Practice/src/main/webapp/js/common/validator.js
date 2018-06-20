var messageList = {};
var messageObject = {};
var configObject = {};
var countryObject = {};
var categoryObject = {};
var lockedcountries = {};
var errCodeRes;
var userInfo;

$(document)
		.ready(
				function() {
					contextPath = $('#contextPath').val();
					getAllInfo("", contextPath + 'common001', 'GET');
					$(
							"input[validate=true],textarea[validate=true],select[validate=true]")
							.each(
									function() {
										var validate_required = $(this).attr(
												"validate_required");
										if (validate_required == "true") {
											$(this).addClass("required");
										}
									});
				});

/**
 * Call when submit form
 * 
 * @param params
 *            data
 * @param actionName
 *            action name
 * @param methodType
 *            type
 */
function getAllInfo(params, actionName, methodType) {
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
			messageObject = response.listAllMessages;
			configObject = response.configInfo;
			countryObject = response.listCountries;
			categoryObject = response.listCategory;
			userInfo = response.userInfo;
		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});
}

function validateForm() {
	$("input[validate=true],textarea[validate=true],select[validate=true]")
			.removeClass("cdms_error");
	$(".cdms_ul_error").remove();
	var flag = true;
	$("input[validate=true],textarea[validate=true],select[validate=true]")
			.each(
					function() {
						var flagOneInput = true;
						var val = $(this).val();
						var validate_length = $(this).attr("validate_length");
						var validate_length_special = $(this).attr(
								"validate_length_special");
						var validate_required = $(this).attr(
								"validate_required");
						var validate_date = $(this).attr("validate_date");
						var validate_halfwidth = $(this).attr(
								"validate_halfwidth");
						var validate_byte_length = $(this).attr(
								"validate_byte_length");
						// Check GPS format
						var validate_GPS_Format = $(this).attr(
								"validate_gps_format");
						// check Time_1 format
						var validate_Time1_Format = $(this).attr(
								"validate_time1_format");
						// check Time_2 format
						var validate_Time2_Format = $(this).attr(
								"validate_time2_format");
						// check special characters
						var validate_special_character = $(this).attr(
								"validate_special_character");
						// check key
						var validate_key = $(this).attr("validate_key");
						// get Field Name
						var validate_field_name = $(this).attr(
								"validate_field_name");
						// get attr validate number
						var validate_number = $(this).attr("validate_number");
						// get attr validate short date dd/MMM/yyyy
						var validate_short_date = $(this).attr(
								"validate_short_date");
						// get attr validate short date dd/MMM/yyyy(not
						// required)
						var validate_short_date_not_required = $(this).attr(
								"validate_short_date_not_required");

						if (validate_GPS_Format == "true"
								&& flagOneInput == true) {
							if (!validateGPSFormat(val)) {
								$(this).after(generateHtmlError("EA-0004", []));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (val != "") {
							if (validate_Time1_Format == "true"
									&& flagOneInput == true) {
								if (!validateTime1Format(val)) {
									$(this).after(
											generateHtmlError("EA-0004",
													validate_field_name));
									$(this).addClass("cdms_error");
									flag = false;
									flagOneInput = false;
								}
							}
							if (validate_Time2_Format === "true") {
								if (!validateTime2Format(val)) {
									$(this).after(
											generateHtmlError("EA-0004",
													validate_field_name));
									$(this).addClass("cdms_error");
									flag = false;
									flagOneInput = false;
								}
							}
						}

						if (validate_required == "true" && flagOneInput == true) {
							if (val == "" || val == null) {
								$(this).after(
										generateHtmlError("EA-0001",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_date == "true" && flagOneInput == true) {
							if (checkTypeFormatDate(val)) {
								$(this).after(generateHtmlError("EA-0004", []));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_length != undefined
								&& validate_length != ""
								&& flagOneInput == true) {
							if (val.length > validate_length) {
								$(this).after(generateHtmlError("EA-0030", []));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}
						if (validate_length_special != undefined
								&& validate_length_special != ""
								&& flagOneInput == true) {
							if (val.length > validate_length_special) {
								$(this).after(
										generateHtmlError("EA-0004",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}
						if (validate_byte_length != undefined
								&& validate_byte_length != ""
								&& flagOneInput == true) {
							if (strToByteLength(val) > validate_byte_length) {
								$(this).after(
										generateHtmlError("EA-0030",
												[ validate_byte_length ]));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_special_character == "true"
								&& flagOneInput == true) {
							if (!validateAlphanumericFormat(val)) {
								$(this).after(
										generateHtmlError("EA-0004",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_key == "true" && flagOneInput == true) {
							if (!validateKey(val)) {
								$(this).after(
										generateHtmlError("EA-0004",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_number == "true" && flagOneInput == true) {
							if (!$.isNumeric(val)) {
								$(this).after(
										generateHtmlError("EA-0004",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_short_date == "true"
								&& flagOneInput == true) {
							if (!checkShortDate(val)) {
								$(this).after(
										generateHtmlError("EA-0008",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

						if (validate_short_date_not_required == "true"
								&& flagOneInput == true) {
							if (!checkShortDateNotRequired(val)) {
								$(this).after(
										generateHtmlError("EA-0004",
												validate_field_name));
								$(this).addClass("cdms_error");
								flag = false;
								flagOneInput = false;
							}
						}

					});
	return flag;
}

/**
 * Validate GPS format
 * 
 * @param str
 * @returns {Boolean}
 */
function validateGPSFormat(str) {
	var res = str.match(/^-?\d*(\.\d+)?$/);
	if (res != null && res != '') {
		return true;
	}
	return false;
}
/**
 * validate Alphanumeric format
 * 
 * @param str
 * @returns {Boolean}
 */
function validateAlphanumericFormat(str) {
	if (!str.trim()) {
		return true;
	}
	var res = str.match(/^[\w. -]+$/);
	if (res != null && res != '') {
		return true;
	}
	return false;
}

function validateKey(str) {
	var res = str.indexOf(' ');
	if (res >= 0) {
		return false;
	}
	return true;
}

function validateAlphabets(str) {
	if (str != null && str != "") {
		if (!str.match(/^[a-zA-Z ]*$/)) {
			return false;
		}
	}
	return true;
}

/**
 * Validate format feild Time_1
 * 
 * @returns
 */
function validateTime1Format(str) {
	var checkFormat = false;
	var time1_regex1 = /^[-+]\d{2}:\d{2}$/;
	var time1_regex2 = /^\d{2}:\d{2}$/;
	if (time1_regex1.test(str) || time1_regex2.test(str)) {
		checkFormat = true;
	} else {
		checkFormat = false;
	}
	var array = str.split(':');
	var iHH = parseInt(array[0]);
	var iMM = parseInt(array[1]);
	if (iHH > 23 || iMM > 59 || iHH < -23) {
		return false;
	}
	return checkFormat;
}
/**
 * Validate format feild jobTime
 * 
 * @returns
 */
function validateTime2Format(str) {
	var checkFormat = false;
	var time1_regex2 = /^\d{2}:\d{2}$/;
	if (time1_regex2.test(str)) {
		checkFormat = true;
	} else {
		checkFormat = false;
	}
	var array = str.split(':');
	var iHH = parseInt(array[0]);
	var iMM = parseInt(array[1]);
	if (iHH > 23 || iMM > 59) {
		return false;
	}
	return checkFormat;
}

function generateHtmlError(errorCode, params) {
	var message = getMessageWithParams(errorCode, params);
	var html = "";
	html += '<ul class="cdms_ul_error">';
	html += '<li>';
	html += message;
	html += '</li>';
	html += '</ul>';
	return html;
}

function getMessageWithParams(errorCode, params) {
	var message = {};
	$.each(messageObject, function(k, v) {
		if (v.key === errorCode) {
			message = v;
		}
	});

	var msgContent = "";
	if (message != null && message != "") {
		msgContent = message.value;
		if (params != null && params != "") {
			msgContent = msgContent + "&lt;" + params + "&gt;"
		}
	}
	return msgContent;
}

function checkTypeFormatDate(date) {
	var date_regex = /^(19|20)\d{2}\/(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])$/;
	if (!(date_regex.test(date))) {
		return false;
	}
	return true;
}

/**
 * 
 * @param currVal
 *            Date value dd/MMM/yyyy
 * @returns return true if true format otherwise return false
 * 
 */
function checkShortDateNotRequired(currVal) {
	if (currVal == '')
		return true;

	// Declare Regex
	var rxDatePattern = /^(\d{1,2})(\/|-)([a-zA-Z]{3})(\/|-)(\d{4})$/;

	var dtArray = currVal.match(rxDatePattern); // is format OK?

	if (dtArray == null)
		return false;

	var dtDay = parseInt(dtArray[1]);
	var dtMonth = dtArray[3];
	var dtYear = parseInt(dtArray[4]);

	// need to change to lowerCase because switch is
	// case sensitive
	switch (dtMonth.toLowerCase()) {
	case 'jan':
		dtMonth = '01';
		break;
	case 'feb':
		dtMonth = '02';
		break;
	case 'mar':
		dtMonth = '03';
		break;
	case 'apr':
		dtMonth = '04';
		break;
	case 'may':
		dtMonth = '05';
		break;
	case 'jun':
		dtMonth = '06';
		break;
	case 'jul':
		dtMonth = '07';
		break;
	case 'aug':
		dtMonth = '08';
		break;
	case 'sep':
		dtMonth = '09';
		break;
	case 'oct':
		dtMonth = '10';
		break;
	case 'nov':
		dtMonth = '11';
		break;
	case 'dec':
		dtMonth = '12';
		break;
	}

	// convert date to number
	dtMonth = parseInt(dtMonth);

	if (isNaN(dtMonth))
		return false;
	else if (dtMonth < 1 || dtMonth > 12)
		return false;
	else if (dtDay < 1 || dtDay > 31)
		return false;
	else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11)
			&& dtDay == 31)
		return false;
	else if (dtMonth == 2) {
		var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		if (dtDay > 29 || (dtDay == 29 && !isleap))
			return false;
	}

	return true;
}

/**
 * 
 * @param currVal
 *            Date value dd/MMM/yyyy
 * @returns return true if true format otherwise return false
 * 
 */
function checkShortDate(currVal) {
	if (currVal == '')
		return false;

	// Declare Regex
	var rxDatePattern = /^(\d{1,2})(\/|-)([a-zA-Z]{3})(\/|-)(\d{4})$/;

	var dtArray = currVal.match(rxDatePattern); // is format OK?

	if (dtArray == null)
		return false;

	var dtDay = parseInt(dtArray[1]);
	var dtMonth = dtArray[3];
	var dtYear = parseInt(dtArray[4]);

	// need to change to lowerCase because switch is
	// case sensitive
	switch (dtMonth.toLowerCase()) {
	case 'jan':
		dtMonth = '01';
		break;
	case 'feb':
		dtMonth = '02';
		break;
	case 'mar':
		dtMonth = '03';
		break;
	case 'apr':
		dtMonth = '04';
		break;
	case 'may':
		dtMonth = '05';
		break;
	case 'jun':
		dtMonth = '06';
		break;
	case 'jul':
		dtMonth = '07';
		break;
	case 'aug':
		dtMonth = '08';
		break;
	case 'sep':
		dtMonth = '09';
		break;
	case 'oct':
		dtMonth = '10';
		break;
	case 'nov':
		dtMonth = '11';
		break;
	case 'dec':
		dtMonth = '12';
		break;
	}

	// convert date to number
	dtMonth = parseInt(dtMonth);

	if (isNaN(dtMonth))
		return false;
	else if (dtMonth < 1 || dtMonth > 12)
		return false;
	else if (dtDay < 1 || dtDay > 31)
		return false;
	else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11)
			&& dtDay == 31)
		return false;
	else if (dtMonth == 2) {
		var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		if (dtDay > 29 || (dtDay == 29 && !isleap))
			return false;
	}

	return true;
}

/**
 * 
 * @param currVal
 *            Date value MMM/yyyy
 * @returns return true if true format otherwise return false
 * 
 */
function checkJobMonth(currVal) {
	if (currVal == '')
		return false;

	// Declare Regex
	var rxDatePattern = /^([a-zA-Z]{3})(\/|-)(\d{4})$/;

	var dtArray = currVal.match(rxDatePattern); // is format OK?

	if (dtArray == null)
		return false;

	var dtMonth = dtArray[1];
	var dtYear = parseInt(dtArray[3]);

	// need to change to lowerCase because switch is
	// case sensitive
	switch (dtMonth.toLowerCase()) {
	case 'jan':
		dtMonth = '01';
		break;
	case 'feb':
		dtMonth = '02';
		break;
	case 'mar':
		dtMonth = '03';
		break;
	case 'apr':
		dtMonth = '04';
		break;
	case 'may':
		dtMonth = '05';
		break;
	case 'jun':
		dtMonth = '06';
		break;
	case 'jul':
		dtMonth = '07';
		break;
	case 'aug':
		dtMonth = '08';
		break;
	case 'sep':
		dtMonth = '09';
		break;
	case 'oct':
		dtMonth = '10';
		break;
	case 'nov':
		dtMonth = '11';
		break;
	case 'dec':
		dtMonth = '12';
		break;
	}

	// convert date to number
	dtMonth = parseInt(dtMonth);

	if (isNaN(dtMonth))
		return false;
	else if (dtMonth < 1 || dtMonth > 12)
		return false;
	return true;
}

/**
 * Validate HalfWidth
 * 
 * @param {Dom
 *            element object} input Input element
 */
function validateHalfWidth(input) {
	if (input == "") {
		return true;
	}
	if (!(/^[a-zA-Z0-9ｧ-ﾝﾞﾟ.,:!@#$%^&*()_+=()｡-ﾞ-\s]+$/.test(input))) {
		return false;
	}
	return true;
}
/**
 * Get number byte of string.
 * 
 * @param str
 * @returns
 */
function strToByteLength(str) {
	var byteArray = escapeToEscapedBytes(str, 16, sysCharset).substr(1).split(
			'\\');
	return byteArray.length;
}

var setCookie = function(name, value, expiracy) {
	var exdate = new Date();
	exdate.setTime(exdate.getTime() + expiracy * 1000);
	var c_value = escape(value)
			+ ((expiracy == null) ? "" : "; expires=" + exdate.toUTCString());
	document.cookie = name + "=" + c_value + '; path=/';
};

var getCookie = function(name) {
	var i, x, y, ARRcookies = document.cookie.split(";");
	for (i = 0; i < ARRcookies.length; i++) {
		x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
		y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
		x = x.replace(/^\s+|\s+$/g, "");
		if (x == name) {
			return y ? decodeURI(unescape(y.replace(/\+/g, ' '))) : y; // ;//unescape(decodeURI(y));
		}
	}
};
var downloadTimeout;
var checkDownloadCookie = function() {
	if (getCookie("downloadStarted") == 1) {
		setCookie("downloadStarted", "false", 1000); // Expiration could be
		// anything... As long
		// as we reset the value
	} else {
		downloadTimeout = setTimeout(checkDownloadCookie, 2000); // Re-run
		// this
		// function
		// in 1
		// second.
		close_loading_ajax();
	}
};

/**
 * Validate before export data
 * 
 * @param params
 *            data
 * @param actionName
 *            action name
 * @param methodType
 *            type
 */
function validateExport(params, actionName, methodType) {
	loading_ajax();
	$.ajax({
		url : actionName,
		type : methodType,
		data : params,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			// close_loading_ajax();
			errCodeRes = response.reportErr;
			if (errCodeRes == null || errCodeRes == '') {
				loading_ajax();
				setCookie('downloadStarted', 0, 100);
				var t0 = performance.now();
				doDownload();// start download
				var t1 = performance.now();
				// Expiration could be anything... As long as we reset the
				// value
				setTimeout(checkDownloadCookie, (t1 - t0)); // Initiate the loop
				// to check the
				// cookie.
			} else { // show error when have error
				showErrorDialog(errCodeRes);
			}
		},
		error : function(jqXhr, textStatus, errorThrown) {
			if (checkLoginRequiredForAjax(jqXhr)) {
				alert(textStatus);
			}
			close_loading_ajax();
		}
	});
}

/**
 * Link: https://en.wikipedia.org/wiki/ISO_6346 Below is the Javascript code to
 * create a custom "ISO6346Check":
 * 
 * @param con
 * @returns true if Container No is true, otherwise false
 */
function ISO6346Check(con) {
	if (!con || con == "" || con.length != 11) {
		return false;
	}
	con = con.toUpperCase();
	var re = /^[A-Z]{4}\d{7}/;
	if (re.test(con)) {
		var sum = 0;
		for (i = 0; i < 10; i++) {
			var n = con.substr(i, 1);
			if (i < 4) {
				n = "0123456789A?BCDEFGHIJK?LMNOPQRSTU?VWXYZ".indexOf(con
						.substr(i, 1));
			}
			n *= Math.pow(2, i); // 2的i次方
			sum += n;
		}
		if (con.substr(0, 4) == "HLCU") {
			sum -= 2;
		}
		sum %= 11;
		sum %= 10; // 余数为10的取0
		return sum == con.substr(10);
	} else {
		return false; // 不匹配正则表达式
	}
}

/**
 * @param strTime
 *            Time value HH:MM
 * @returns return true if true format otherwise return false
 */
function checkShortTime(strTime) {
	if (strTime == '')
		return false;

	// Regex
	var rxDatePattern = /^(\d{2})(:)(\d{2})$/;

	var dtArray = strTime.match(rxDatePattern); // is format OK?

	if (dtArray == null)
		return false;

	var dtHour = parseInt(dtArray[1]);
	var dtMinute = parseInt(dtArray[3]);

	// check hour & minute
	if (dtHour < 0 || dtHour > 23 || dtMinute < 0 || dtMinute > 59)
		return false;

	return true;
}

/**
 * validateAlphanumberic all input
 * @returns
 */
function validateAlphanumberic(){
	$(".alphanumberic").keypress(function(e){
		var key = e.keyCode;
		if (!checkAlphanumeric(String.fromCharCode(key), false)){
			//alert(String.fromCharCode(key));
			return false;
		}
	});
	$("#itemName").unbind("keypress");
	$("#itemName").keypress(function(e){
		var key = e.keyCode;
		if (!checkAlphanumeric(String.fromCharCode(key), true)){
			//alert(String.fromCharCode(key));
			return false;
		}
	});
}

/**
 * check alphanumberic
 * @param character
 * @returns true if alphanumberic
 */
function checkAlphanumeric(character, space){
	var regex = /[^a-zA-Z0-9]/g;
	if (space){
		regex = /[^a-zA-Z0-9 ]/g;
	}
	return !regex.test(character);
}