//Define options of scrollbar
var opts = {wheelSpeed: 2,wheelPropagation: true,minScrollbarLength: 20};
var valueCustomerCd;
var contextPath;

function doLogout() {
	showOptConfirm($.def.logout.confirm, $.def.logout.msg, null, $.def.logout.ok, null, $.def.logout.cancel,
				   function() {
					   location.href = "../PAA0011/logout.action";
				   });
}

/**
 * close current screen
 * 
 * @returns
 */
function doClose(){
	showConfirmDialog("close","",["faa001", "saa0012"],{"position":"200"})
}


function popupErrorBox() {
	$("#errMsgPopBox").show();
}

function closeMsgPop() {
	$("#errMsgPopBox").hide();
}

function GoToNavigate(menuId) {
	var r = getFunIdFromProURL();

	if (r != "") {
		switch (r) {
			case 'PAC0011':
			case 'PAC0021':
			case 'PAD0011':
			case 'PAD0021':
			case 'PAD0031':
			case 'PAD0041':
			case 'PAD0051':
			case 'PAD0061':
			case 'PAD0062':
			case 'PAD0071':
				showOptConfirm($.def.edit.confirm, $.def.edit.msg.confirm, null, "Proceed", null, "Return", function() {
					RunGoToNavigate(menuId);
				});
				break;
			default:
				RunGoToNavigate(menuId);
				break;
		}
	} else {
		RunGoToNavigate(menuId);
	}
}

function getFunIdFromProURL(){
	var proUrl = window.location.href;
	var arrTmp = proUrl.split("\/");
	if (isArrayObj(arrTmp)) {
		return arrTmp[arrTmp.length-2];
	}
	return "";
}

function isArrayObj(obj) {
  return Object.prototype.toString.call(obj) === '[object Array]';
}

function RunGoToNavigate(menuId) {
	var gotoUrl = "../PAA0011/logout.action";
	switch (menuId) {
	case 'FAA002':
		gotoUrl = "../PAA0012/init.action?selMenu=FAA001";
		break;
	case 'FAB001':
		gotoUrl = "../PAB0011/init.action?selMenu=FAB001&selShortcut=MANAGEMENT";
		break;
	case 'FAB002':
		gotoUrl = "../PAB0021/init.action?selMenu=FAB002&selShortcut=MANAGEMENT";
		break;
	case 'FAB003':
		gotoUrl = "../PAB0031/init.action?selMenu=FAB003&selShortcut=MANAGEMENT";
		break;
	case 'FAC001':
		gotoUrl = "../PAC0011/init.action?selMenu=FAC001&selShortcut=WORK";
		break;
	case 'FAC002':
		gotoUrl = "../PAC0021/init.action?selMenu=FAC002&selShortcut=WORK";
		break;
	case 'FAC003':
		gotoUrl = "../PAC0031/init.action?selMenu=FAC003&selShortcut=WORK";
		break;
	case 'FAC004':
		gotoUrl = "../PAC0041/init.action?selMenu=FAC004&selShortcut=WORK";
		break;
	case 'FAC005':
		gotoUrl = "../PAC0051/init.action?selMenu=FAC005&selShortcut=WORK";
		break;
	case 'FAC006':
		gotoUrl = "../PAC0061/init.action?selMenu=FAC006&selShortcut=WORK";
		break;
	case 'FAC007':
		gotoUrl = "../PAC0071/init.action?selMenu=FAC007&selShortcut=WORK";
		break;
	case 'FAC007R':
		gotoUrl = "../PAC0071R/init.action?selMenu=FAC007R&selShortcut=WORK";
		break;
	case 'FAC008':
		gotoUrl = "../PAC0081/init.action?selMenu=FAC008&selShortcut=WORK";
		break;
	case 'FAC008R':
		gotoUrl = "../PAC0081R/init.action?selMenu=FAC008R&selShortcut=WORK";
		break;
	case 'FAC009':
		gotoUrl = "../PAC0091/init.action?selMenu=FAC009&selShortcut=WORK";
		break;
	case 'FAC010':
		gotoUrl = "../PAC0101/init.action?selMenu=FAC010&selShortcut=WORK";
		break;
	case 'FAD001':
		gotoUrl = "../PAD0011/init.action?selMenu=FAD001&selShortcut=SYSTEM";
		break;
	case 'FAD002':
		gotoUrl = "../PAD0021/init.action?selMenu=FAD002&selShortcut=SYSTEM";
		break;
	case 'FAD003':
		gotoUrl = "../PAD0031/init.action?selMenu=FAD003&selShortcut=SYSTEM";
		break;
	case 'FAD004':
		gotoUrl = "../PAD0041/init.action?selMenu=FAD004&selShortcut=SYSTEM";
		break;
	case 'FAD005':
		gotoUrl = "../PAD0051/init.action?selMenu=FAD005&selShortcut=SYSTEM";
		break;
	case 'FAD006':
		gotoUrl = "../PAD0061/init.action?selMenu=FAD006&selShortcut=SYSTEM";
		break;
	case 'FAD007':
		gotoUrl = "../PAD0071/init.action?selMenu=FAD007&selShortcut=SYSTEM";
		break;
	default:
		gotoUrl = "../PAA0011/logout.action";
	}

	location.href = gotoUrl;
}

window.onresize = function() {
	$('.dataTables_scrollBody').perfectScrollbar('update');
	
};

window.onkeydown = function(event) {
	// F11, F12 keys
// if (event.keyCode == 122 || event.keyCode == 123) {
// return false;
// }
// // backspace
	if (event.keyCode == 8) {
		var objName = document.activeElement.localName;
		if (objName == "input" || objName == "textarea") {
			return true;
		}
		return false;
	}
	// enter
	if (event.keyCode == 13) {
		var objName = document.activeElement.localName;
		if (objName == "textarea") {
			return true;
		}
		return false;
	}
	// space
	if (event.keyCode == 32) {
		var objName = document.activeElement.localName;
		if (objName == "input" || objName == "textarea") {
			return true;
		}
		return false;
	}
};

function onLoad() {
	$(":text").focus(function() {
		onFocus(this);
	});

	$("[format^='number']").focus(function() {
		numberOnFocus(this);
	}).blur(function() {
		numberOnBlur(this);
	}).keydown(function() {
		numberOnKeydown(this, event);
	}).addClass("ime-off");

	$.each($("[format^='number']"), function() {
		numberOnBlur(this);
	});
	$.each($("[format='date']"), function() {
		dateOnBlur(this);
	});

	$("[format='date']").blur(function() {
		dateOnBlur(this);
	}).addClass("ime-off").datepicker({
		showButtonPanel : true,
		dateFormat : "dd.MM.yy",
		showOn : 'button',
		yearRange : '20:+20',
		changeMonth : true,
		changeYear : true,
		buttonImage : '../style/images/calendar.png',
		buttonImageOnly : true
	});

	$("[format='letnum']").keydown(function() {
		letnumOnKeydown(this, event);
	}).addClass("ime-off");

	$("[format='code']").keydown(function() {
		codeOnKeydown(this, event);
	}).addClass("ime-off");

	$("[format='imeActive']").addClass("ime-active");
	$("[format='imeInactive']").addClass("ime-inactive");
	$("[format='imeOff']").addClass("ime-off");

	$(':input[readonly]').unbind();
	$(':input[readonly]').unbind();
	$("[format='date'][readonly]").datepicker('disable');

	$(':input').not(":disabled").each(function(i, e) {
		this.tabindex = i;
	});
};

function dateOnBlur(u) {
	str = u.value;
	if (!isEmpty(str)) {
		if (str.length == 8) {
			str = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
		}
		str = str.replace(/\//g, '-');

		try {
			var ds = str.match(/\d+/g);
			if (ds[1].length == 1) {
				ds[1] = "0" + ds[1];
			}
			if (ds[2].length == 1) {
				ds[2] = "0" + ds[2];
			}
			str = ds[0] + "-" + ds[1] + "-" + ds[2];
			var ts = [ 'getFullYear', 'getMonth', 'getDate' ];
			var d = new Date(str);
			var i = 3;
			ds[1]--;
			while (i--) {
				var t = d[ts[i]]();
				if (!isNaN(t) && ds[i] * 1 != t) {
					return;
				}
			}
		} catch (e) {
		}
		u.value = str;
	}
}

function onFocus(u) {
	u.select();
}

function numberOnBlur(u) {
	if (typeof u == "undefined") {
		u.value = "";
		return;
	}
	if (u.value == null || u.value.length == 0) {
		u.value = "";
		return;
	}
	if (!isDecimal(u.value)) {
		u.value = "";
		return;
	}
	var dotindex = u.value.indexOf('.');
	var intvalue = u.value;
	var floatvalue = 0.0;
	var rtnVal;
	var s = u.getAttribute("format").replace('number', '') * 1;

	if (dotindex >= 0) {
		intvalue = u.value.substr(0, dotindex);
		if (s > 0) {
			floatvalue = u.value.substr(dotindex, s + 1);
		}
	}

	floatvalue = (myToFixed(parseFloat(floatvalue), s)).replace("0.", ".");
	rtnVal = intvalue.replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,') + floatvalue;

	u.value = rtnVal;
}

function myToFixed(s, n) {
	n = n > 0 && n <= 20 ? n : 0;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for ( var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	if (n == 0) {
		return t.split("").reverse().join("");
	} else {
		return t.split("").reverse().join("") + "." + r;
	}
}

function isInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
};

function isNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
};

function isDecimal(str) {
	/*
	 * if (isInteger(str)) { return true; } var re =
	 * /^[-]{0,1}(\d+)[\.]+(\d+)$/; if (re.test(str)) { if (RegExp.$1 == 0 &&
	 * RegExp.$2 == 0) { return false; } return true; } else { return false; }
	 */

	var re = /^0$|^[1-9]+\d*(\.\d*[0-9])?$|^0\.\d+$/;
	if (re.test(str)) {
		return true;
	} else {
		return false;
	}

};

function numberOnFocus(u) {
	u.value = u.value.replace(/\,/g, '');
	onFocus(u);
}

function numberOnKeydown(u, e) {
	var s = u.getAttribute("format").replace('number', '') * 1;
	if (e.ctrlKey || e.shiftKey || e.keyCode == 37 || e.keyCode == 39 || e.keyCode == 46 || e.keyCode == 8
		|| e.keyCode == 9 || s > 0 && (e.keyCode == 110 || e.keyCode == 190) && u.value.indexOf('.') < 0) {
	} else if (e.keyCode < 48 || e.keyCode > 57 && e.keyCode < 96 || e.keyCode > 105) {
		e.returnValue = false;
		e.preventDefault();
	}
}

function letnumOnKeydown(u, e) {
	if (e.ctrlKey || e.shiftKey || e.keyCode == 13 || e.keyCode == 37 || e.keyCode == 39 || e.keyCode == 46
		|| e.keyCode == 8 || e.keyCode == 9) {
	} else if (e.keyCode < 48 || e.keyCode > 57 && e.keyCode < 65 || e.keyCode > 90 && e.keyCode < 96
			   || e.keyCode > 105) {
		e.returnValue = false;
		e.preventDefault();
	}
}

function codeOnKeydown(u, e) {
	if (e.ctrlKey || e.shiftKey || e.keyCode == 37 || e.keyCode == 39 || e.keyCode == 46 || e.keyCode == 8
		|| e.keyCode == 9) {
	} else if (e.keyCode < 48 || e.keyCode > 57 && e.keyCode < 96 || e.keyCode > 105) {
		e.returnValue = false;
		e.preventDefault();
	}
}
function showDialog(title, content, w, h, callback) {
	var modalopt = {
		width : w,
		height : h,
		buttons : [ {
			text : $.jgrid.btn.ok,
			id : "btnOk",
			onClick : function() {
				callback.call();
				hideModal("#info_dialog");
			}
		} ]
	};
	info_dialog(title, content, modalopt, 0);
}

function showWarningMsg(title, message, field, callback) {
	var modalopt = {
		buttons : [ {
			text : $.jgrid.btn.ok,
			id : "btnOk",
			onClick : function() {
				hideModal("#info_dialog");
				if (field != null && field != "undefine") {
					$(field).focus();
				}
				if (callback != null && callback != "undefine") {
					callback.call();
				}
			}
		} ]
	};
	info_dialog(title, message, modalopt, 4);
}

function showErrorMsg(title, message, field, callback) {
	var modalopt = {
		buttons : [ {
			text : $.jgrid.btn.ok,
			id : "btnOk",
			onClick : function() {
				hideModal("#info_dialog");
				if (field != null && field != "undefine") {
					$(field).focus();
				}
				if (callback != null && callback != "undefine") {
					callback.call();
				}
			}
		} ]
	};
	info_dialog(title, message, modalopt, 3);
}

function showMsg(title, message, field, callback) {
	var modalopt = {
		buttons : [ {
			text : $.jgrid.btn.ok,
			id : "btnOk",
			onClick : function() {
				hideModal("#info_dialog");
				if (field != null && field != "undefine") {
					$(field).focus();
				}
				if (callback != null && callback != "undefine") {
					callback.call();
				}
			}
		} ]
	};
	info_dialog(title, message, modalopt, 1);
}

function showConfirm(title, message, yesField, noField, callback) {
	var modalopt = {
		buttons : [ {
			text : $.jgrid.btn.ok,
			id : "btnOk",
			onClick : function() {
				hideModal("#info_dialog");
				if (yesField != null && yesField != "undefine") {
					$(yesField).focus();
				}
				if (callback != null && callback != "undefine") {
					callback.call();
				}
			}
		}, {
			text : $.jgrid.btn.cancel,
			id : "btnCancel",
			onClick : function() {
				hideModal("#info_dialog");
				if (noField != null && noField != "undefine") {
					$(noField).focus();
				}
			}
		} ]
	};
	info_dialog(title, message, modalopt, 2);
}

function showOptConfirm(title, message, yesField, yesText, noField, noText, callback, noCallback) {
	var modalopt = {
		buttons : [ {
			text : yesText,
			id : "btnOk",
			onClick : function() {
				hideModal("#info_dialog");
				if (yesField != null && yesField != "undefine") {
					$(yesField).focus();
				}
				if (callback != null && callback != "undefine") {
					callback.call();
				}
			}
		}, {
			text : noText,
			id : "btnCancel",
			onClick : function() {
				hideModal("#info_dialog");
				if (noField != null && noField != "undefine") {
					$(noField).focus();
				}
				if (noCallback != null && noCallback != "undefine") {
					noCallback.call();
				}
			}
		} ]
	};
	info_dialog(title, message, modalopt, 2);
}

function show_hide_menu(menuId) {
	var m1Css = $("#h-menu-management").attr("class");
	var m2Css = $("#h-menu-work_control").attr("class");
	var m3Css = $("#h-menu-system").attr("class");

	if (menuId == 1) {
		if (m1Css == "selected") {
			$("#menu-management").hide();
			$("#h-menu-management").removeClass("selected");
			$("#h-menu-management").addClass("closed");
		} else {
			$("#menu-management").show();
			$("#h-menu-management").removeClass("closed");
			$("#h-menu-management").addClass("selected");
		}
	}

	if (menuId == 2) {
		if (m2Css == "selected") {
			$("#menu-work").hide();
			$("#h-menu-work_control").removeClass("selected");
			$("#h-menu-work_control").addClass("closed");
		} else {
			$("#menu-work").show();
			$("#h-menu-work_control").removeClass("closed");
			$("#h-menu-work_control").addClass("selected");
		}
	}

	if (menuId == 3) {
		if (m3Css == "selected") {
			$("#menu-system").hide();
			$("#h-menu-system").removeClass("selected");
			$("#h-menu-system").addClass("closed");
		} else {
			$("#menu-system").show();
			$("#h-menu-system").removeClass("closed");
			$("#h-menu-system").addClass("selected");
		}
	}
}

String.prototype.format = function(args) {
	var result = this;
	var reg;
	if (arguments.length > 0) {
		if (arguments.length == 1 && typeof (args) == "object") {
			for ( var key in args) {
				if (args[key] != undefined) {
					reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			}
		} else {

			for ( var i = 0; i < arguments.length; i++) {
				if (arguments[i] != undefined) {
					reg = new RegExp("({)" + i + "(})", "g");
					result = result.replace(reg, arguments[i]);
				}
			}
		}
	}
	return result;
};

function lpad(desstr, padchar, lenint) {
	var result = "";
	for ( var i = 1; i <= lenint - desstr.length; i++) {
		result += padchar;
	}
	result += desstr;
	return result;
}

function rpad(desstr, padchar, lenint) {
	var result = "";
	for ( var i = 1; i <= lenint - desstr.length; i++) {
		result += padchar;
	}
	result = desstr + result;
	return result;
}

function formatDateTo_ddMMMyyyy(d, m, y) {
	var month = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
	return lpad(d, '0', 2) + "." + month[m] + "." + ((("" + y).length > 2) ? y : ("20" + y));
}

function openWin(targetURL, winName, features, myWidth, myHeight, isCenter) {
	if (window.screen)
		if (isCenter)
			if (isCenter == "true") {
				var myLeft = (screen.width - myWidth) / 2;
				var myTop = (screen.height - myHeight) / 2;
				features += (features != '') ? ',' : '';
				features += ',left=' + myLeft + ',top=' + myTop;
			}
	window.open(targetURL, winName, features + ((features != '') ? ',' : '') + 'width=' + myWidth + ',height='
									+ myHeight);
}

function openWinTest() {
	openWin("../S0601/init.action?selMenu=SYS006&selShortcut=SYSTEM", "test",
			"scrollbars=no,status=no,toolbar=no,menubar=no,location=no", "1280", "768", false);
}

function changeValidDate(obj) {
	// format like '28.Dec.2014'
	var regx1 = /^\d{2}\.[A-Z][a-z]{2}\.\d{4}$/g,
	// format like '28122014'
	regx2 = /^\d{8}$/g,
	// format like '28/12/2014'
	regx3 = /^\d{2}\/\d{2}\/\d{4}$/g,
	// format like '28-12-2014'
	regx4 = /^\d{2}-\d{2}-\d{4}$/g,
	// input date
	dateStr = obj.value,
	// day
	d,
	// month
	m,
	// year
	y;

	if (regx1.test(dateStr)) {
		return false;
	}

	if (regx2.test(dateStr) || regx3.test(dateStr) || regx4.test(dateStr)) {
		dateStr = dateStr.replace(/[-,\/]/g, "");
	} else {
		return false;
	}

	d = parseInt(dateStr.substr(0, 2));
	m = parseInt(dateStr.substr(2, 2));
	y = dateStr.substr(4, 4);

	if ((d >= 1 && d <= 31) && (m >= 1 && m <= 12)) {
		obj.value = formatDateTo_ddMMMyyyy(d.toString(), (m - 1).toString(), y);
	}
}

$.fn.extend({
	setReadOnly : function(bool) {
		if (bool) {
			$(this).attr("readonly", true);
			$(this).attr("tabindex", -1);
		} else {
			$(this).attr("readonly", false);
			$(this).attr("tabindex", 0);
		}
	}
});

function dispNullToEmpty(str) {
	if (str && str.trim() != "") {
		return str;
	}
	return "";
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r;
	var proUrl = document.referrer;
	var inx = proUrl.indexOf("?") + 1;
	r = proUrl.substr(inx).match(reg);
	if (r != null){
		return unescape(r[2]);
	} else {
		return "";
	}
}


function getLocQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r=window.location.search.substr(1).match(reg);
	if (r != null){
		return unescape(r[2]);
	} else {
		return "";
	}
}

function nullToSpace(str) {
	if (str) {
		return str;
	}
	return "&nbsp;";
}

function loading_ajax(){
	$('body').append('<div id="mpb_loadingAjax"></div>');
    $('#mpb_loadingAjax').fadeIn(300);
}

function close_loading_ajax(){
	$('#mpb_loadingAjax').fadeOut(300 , function() {
	    $('#mpb_loadingAjax').remove();
	});
}

function callAjaxData(params, actionName, methodType){
	loading_ajax();
            $.ajax({
                    url : actionName,
                    type : methodType,
                    data: params,
                    dataType : "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(response) {
                    	 close_loading_ajax();
                    },
                    error:function(jqXhr, textStatus, errorThrown){
                        alert(textStatus);
                        close_loading_ajax();
                    }
            });

 }

/**
 * wrap row & add tooltips for long items
 * 
 * @params : element
 */
function tooltipsForRow(elem) {
	var $this = elem || null;
	if ($this != null && $this.text() != null && $this.text() != "") {
			 $this.css({
					'word-wrap' : 'normal',
					'white-space' : 'nowrap',
					'overflow-x' : 'hidden',
					'text-overflow' : 'ellipsis'
				});
			 if (isEllipsisActive($this)){
				 $this.qtip({
						content : {
							text : convertXSS($this.text())
						},
						position : {
							my : 'top left',
							at : 'bottom left',
							target : $this
						},
						style : {
							classes : 'qtip-bootstrap'
						}
					});
			 }
	}
}

function isEllipsisActive(e) {
    return ($(e).innerWidth() < $(e)[0].scrollWidth);
}

/**
 * Added for dis-allow script
 * 
 * @params : str
 */
function convertXSS(str)
{
  str = str.replace(/&/g, "&amp;");
  str = str.replace(/>/g, "&gt;");
  str = str.replace(/</g, "&lt;");
  str = str.replace(/"/g, "&quot;");
  str = str.replace(/'/g, "&#039;");
  return str;
}
function toggleMenu(){
	if(isMenuLeftEmpty()){
	    console.log("Menu Empty")
	    return
    }
	$(".left-side").toggle({
		direction : 'left',
		complete: function(){
			resizeWhenToggleMenu()
		}
	}, "slow");
}
function isMenuLeftEmpty(){
    return $('#menuDisplay').val() === "false"
}
function resizeWhenToggleMenu(){
	if($('#ui-datepicker-div') != null) {
		$('#ui-datepicker-div').hide();
	}
	$('.dataTables_scrollBody').perfectScrollbar('update');
	if($(".left-side").is(":hidden")){
		$(".right-side").addClass("col-xs-12");
		$(".right-side").removeClass("col-xs-10 col-sm-10 col-md-10 col-lg-10");
	}else{
		$(".right-side").addClass("col-xs-10");
		$(".right-side").removeClass("col-xs-12");
		
	}
	divResize();
}
$( document ).ready(function() {
    $(".h6_menu_left").on("click",function(){
    	toggleMenu()
    });
    $(".icon-menu").on("click",function(){
    	toggleMenu()
    	$(".table-header").css('width', '97.8%');
    }).hover(function(){
        if(isMenuLeftEmpty()){
            this.style.cursor = 'not-allowed'
        }
    });

    $(".left-side").hover(function(){
    }, function(){
    	toggleMenu()
    	$(".table-header").css('width', '98%');
    });

    $(".menu_link").on("click",function(){
    	redirectMenu($(this).attr("function_id"),$(this).attr("screen_id"));
    });
	$("#linkDemo").click(function(){
        $("#myModal").modal();
	});
	$(".btn-logout").click(function(){
		showConfirmDialog("logout")
	});
	$(".btn-delete").css('background-color','grey');

});

/**
 * calculate height table.
 * 
 * @returns {Number}
 */
function calcDataTableHeight(headerRow) {
	return $(window).height() - 250 - (10*headerRow);
}

function convertTodate(date){
	var resultDate = "";
	if(date != "" && date != undefined){
		var arrDate = date.split("/");
		var day = arrDate[0];
		var month = arrDate[1];
		var year = arrDate[2];
		var dtMonth = "";
		switch (month) {
	    case 'Jan':
	        dtMonth = '01';
	        break;
	    case 'Feb':
	        dtMonth = '02';
	        break;
	    case 'Mar':
	        dtMonth = '03';
	        break;
	    case 'Apr':
	        dtMonth = '04';
	        break;
	    case 'May':
	        dtMonth = '05';
	        break;
	    case 'Jun':
	        dtMonth = '06';
	        break;
	    case 'Jul':
	        dtMonth = '07';
	        break;
	    case 'Aug':
	        dtMonth = '08';
	        break;
	    case 'Sep':
	        dtMonth = '09';
	        break;
	    case 'Oct':
	        dtMonth = '10';
	        break;
	    case 'Nov':
	        dtMonth = '11';
	        break;
	    case 'Dec':
	        dtMonth = '12';
	        break;
	      }
		resultDate =  day + "/" + dtMonth + "/" + year;
	}
	
	return  resultDate;
}

/**
 * Get client timezone
 */
function getClientTimeZone() {
	var dateVar = new Date();
	var timeZone = dateVar.getTimezoneOffset()/60 * (-1); 
	return timeZone;
}

/**
 * Get current datetime of client
 */
function getCurrentDateTime() {
	var date = new Date();
	var dt = date.getDate() + '-' + (date.getMonth()+1) + '-' + date.getFullYear() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
	return dt;
}

/**
 * Format time
 * 
 * @param str
 * @param textbox
 * @param loc
 * @param delim
 */
function mask(str,textbox,loc,delim){
	var key = event.keyCode;
	if(key != 8) {
		 var locs = loc.split(',');
		    for (var i = 0; i <= locs.length; i++){
		        for (var k = 0; k <= str.length; k++){
		            if (k == locs[i]){
		                if (str.substring(k, k+1) != delim){
		                    str = str.substring(0,k) + delim + str.substring(k,str.length)
		                }
		            }
		        }
		    }
		    textbox.value = str
	} 
}

/**
 * convert string to number
 * format number ##,###.##
 * 
 * @param value
 * @returns
 */
function convertNumberToString7v2(value){
	var temp = "";
	if (value == null || value == "null" || value == undefined){
		return "0.00";
	} else {
		if (!isNaN(parseFloat(value))){
			return parseFloat(value).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
		} else {
			return "0.00";
		}
	}
}

/**
 * resize div when left-menu show
 * 
 * @returns
 */
function divResize(){}