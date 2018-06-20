/**
 * categoryLarge =[ desktop, laptop middle, laptop low, mobile high end.]
 * 
 * addon = [OS, office, memory, monitor, pc kitting, anti-virus software,
 * monitoring] (anti-virus software, monitoring = hidden)
 * 
 * model item have item.category === item.categoryLarge (anti-virus software,
 * monitoring have quantiy = model.qty where addon.catgoryLarge ===
 * model.categorylarge)
 * 
 * totalMonthlyRecurring = all MRC ( include anti-virus software, monitoring)
 */

"use strict";
var listCompanies = {};
var userInfo = {};
var largeCategory = [];
var largeCategorySelected = [];
var companySelectedOject = {};
var listAllItems = {};
var listAddon = {};
var listHiddenItems = [];
var oldValue = "";

var modelItems = [];

var addonItems = [];

var listItemOrder = [];

var orderId;
var quoteMrc;
var quoteOtc;

var totalOneTimeCharge;
var totalMonthlyRecurring;
var sumQuantityData;
var fireEyeEdgeXSoftware;
var pcMonitoringSoftware;

// edit order
var orderInfo = {};
var flagEdit;
var flagOrderValidAddonQty;
var checkExistCtegoryLarge;

var error = {};
var anyChange = false;
var redirect;
var css;

function confirmSave() {
	showConfirmDialog("save", "", "", {
		"position" : "200"
	});
}

$(document).ready(function() {
	 //$.ajaxSetup({ cache: false });
	contextPath = $('#contextPath').val();
	$('#confirmRedirectMenu').val(true);
	$(".left-side").hide();
	getAllDataSac0011();
	$("#newOrder").click(function() {
		if (validate()) {
			validateSumQuantity();
		}

	})
	$("#printOrder").attr("disabled", "disabled");

	$("#sendOrder").attr("disabled", "disabled");
	$("#deleteOrder").attr("disabled", "disabled");

	// Validation maxlength with 250
	$("#remark").bind("keyup change", function() {
		checkMaxLength(this.id, 250);
	});

	$("#error-sac0011").hide();
	
	$('#departments').change(function(event) {
	    var text = $(this).find("option:selected").text();

	    // Show text of department
		var deptCd = $(this).find("option:selected").val();
		var deptName = findDepartmentName(deptCd);
		if (!deptCd) {
			deptCd = "";
		}
		if (deptName && deptName != ""){
			deptName = "(" + deptName + ")";
		} else {
			deptName = "";
		}
		$("#deptCd-span").text(deptCd);
		$("#deptName-span").text(deptName);
	});
	
	$('#departments').focusin(function(event) {
		$(".div-dept").removeClass("div-focus");
		$(".div-dept").addClass("div-focus");
	}).focusout(function(event) {
		$(".div-dept").addClass("div-focus");
		$(".div-dept").removeClass("div-focus");
	});
	
	$(".right-side").css("zoom", "66%");
});

/**
 * object add to order
 * 
 * @param categoryLarge
 * @param itemCd
 * @param categoryCd
 * @param addonCd
 * @param qty
 * @param unitPrice
 * @param leaseType
 * @param warningFg
 * @param addonConfig
 * @returns
 */
function ItemOrder(categoryLarge, itemCd, itemName, categoryCd, addonCd, qty, unitPrice,
		leaseType, warningFg, addonConfig) {
	this.categoryLarge = categoryLarge;
	this.itemCd = itemCd;
	this.itemName = itemName;
	this.categoryCd = categoryCd;
	this.addonCd = addonCd;
	this.qty = parseInt(qty);
	this.unitPrice = parseFloat(unitPrice);
	this.leaseType = leaseType;
	this.warningFg = warningFg;
	this.addonConfig = addonConfig;
}

/**
 * get all data to screen (add + edit)
 * 
 * @returns
 */
function getAllDataSac0011() {
	loading_ajax();
	var params = {};
	if (flagEdit === true && orderId !== null) {
		params.flagEdit = true;
		params.orderId = orderId;
	}
	$.ajax({
		cache: false,
		url : '../fac001/sac0011_01',
		type : "GET",
		data : params,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			close_loading_ajax();
			listCompanies = response.listCompanies;
			userInfo = response.userInfo;
			listAllItems = response.listAllItems;
			listHiddenItems = response.listHiddenItems;
			listAddon = response.listAddon;
			orderInfo = response.orderInfo;
			redirect = response.redirect;
			orderId = response.orderId;
			quoteMrc = response.quoteMrc;
			quoteOtc = response.quoteOtc;
			drawCompanies();

			if (flagEdit === true && orderId !== null) {
				if (orderInfo !== null && orderInfo !== undefined
						&& orderInfo !== "") {
					drawToEditOrder();
				}
			}
			setTabindex();
		},
		error : function(jqXhr, textStatus, errorThrown) {
			$("#contentMessage").text(getMessageWithParams("PCOD-0010"));
			$("#modalInfo").modal("show");
			close_loading_ajax();
		}
	});
}
/**
 * set tabindex
 */
// setId(inputType, idLargeCate, categoryCd, orderNo, leaseType)
function setTabindex() {
	$('#companies').attr('tabindex', 1);
	//$('#departments').attr('tabindex', 2);
	$('.div-dept').attr('tabindex', 2);
	$.each(categoryObject, function(i, item) {
		if (item.categoryAbbrev !== null && item.addonCd === null) {
			$('#' + setId(0, item.categoryCd, item.categoryCd, 0, "MRC")).attr(
					'tabindex', 3);
			$('#' + setId(1, item.categoryCd, item.categoryCd, 0, "MRC")).attr(
					'tabindex', 4);
		}
		var indexSelect = 5;
		var indexQty = 6;
		$.each(listAddon, function(m, addonItem) {
			if (addonItem.displayOrder !== null) {
				for (var k = 0; k < addonItem.config; k++) {
					$(
							'#'
									+ setId(0, item.categoryCd,
											addonItem.addonCd, k,
											addonItem.addonType)).attr(
							'tabindex', indexSelect);
					$(
							'#'
									+ setId(1, item.categoryCd,
											addonItem.addonCd, k,
											addonItem.addonType)).attr(
							'tabindex', indexQty);
					indexSelect += 2;
					indexQty += 2;
				}

			}
		});
	});
	$('#remark').attr('tabindex', 21);
	$('#newOrder').attr('tabindex', 22);
	$('#printOrder').attr('tabindex', 23);
	$('#sendOrder').attr('tabindex', 24);
	$('#closeButton').attr('tabindex', 25);
	$('#deleteOrder').attr('tabindex', 26);
	/*
	 * $('.select-item').attr('tabindex', 3);
	 * $('.quantity-item').attr('tabindex', 4);
	 */
}

/**
 * draw combo box company code
 * 
 * @returns
 */
function drawCompanies() {
	getLargeCategory();
	detachItems();
	if ("1" === userInfo.userAuthorityCd) {
		$('#companies').append($('<option>', {
			value : userInfo.userCompanyCd,
			text : userInfo.userCompanyCd
		}));
		$.each(listCompanies, function(i, c) {
			if (c.companyCd === userInfo.userCompanyCd) {
				companySelectedOject = c;
			}
		});
		$("#companies").attr("disabled", "disabled");
		listItemOrder = [];
		drawDepartment(userInfo.userCompanyCd);
	    // Show text of department
		var deptCd = $('#departments').find("option:selected").val();
		var deptName = findDepartmentName(deptCd);
		if (!deptCd) {
			deptCd = "";
		}
		if (deptName && deptName != ""){
			deptName = "(" + deptName + ")";
		} else {
			deptName = "";
		}
		$("#deptCd-span").text(deptCd);
		$("#deptName-span").text(deptName);
		// End show text of department
		
		drawCategory();
		pushData();
		countPrice();
	} else {
		$('#companies').append($('<option>', {
			value : "",
			text : ""
		}));
		$.each(listCompanies, function(i, c) {
			$('#companies').append($('<option>', {
				value : c.companyCd,
				text : c.companyCd
			}));
		});
	}

	$("#companies").val($("#companies option:first").val());

	$("#companies").change(function() {
		listItemOrder = [];
		drawDepartment(this.value);
		drawCategory();
		pushData();
		countPrice();
		$("#departments").removeClass("border-error");
		
		// Show text of department
		var deptCd = $("#departments").find("option:selected").val();
		var deptName = findDepartmentName(deptCd);
		if (!deptCd) {
			deptCd = "";
		}
		if (deptName && deptName != ""){
			deptName = "(" + deptName + ")";
		} else {
			deptName = "";
		}
		$("#deptCd-span").text(deptCd);
		$("#deptName-span").text(deptName);
	});

}

/**
 * get large category
 */
function getLargeCategory() {
	$.each(categoryObject, function(i, item) {
		if (item.categoryAbbrev !== null && item.addonCd === null) {
			largeCategory.push(item);
		}
	});
	css = (100 / largeCategory.length) + "%";

}

/**
 * divide items (model, add on - "os, office, memory,..", hidden - "anti-virus,
 * monitoring" )
 * 
 * @returns
 */
function detachItems() {
	$.each(listAllItems, function(i, item) {
		var isModel = false;
		$.each(largeCategory, function(j, cate) {
			if (item.categoryCd === cate.categoryCd) {
				item.leaseType = "MRC";
				isModel = true
				return false;
			}
		});

		if (isModel) {
			modelItems.push(item);
		} else {
			addonItems.push(item);
		}

	});
}

/**
 * create columns with category
 * 
 * @returns
 */
function drawCategory() {
	$("#mainData").html("");
	var text = "";
	var checkDraw = false;
	if (companySelectedOject.companyCd !== undefined) {
		checkDraw = true;
	}
	for (var i = 0; i < largeCategory.length; i++) {
		text += '<div class="col-md-3" style="width: '
				+ css
				+ ';"><div class="text-center"><div class="new-line"></div><h4><strong>';
		text += largeCategory[i].categoryName;
		var idPdf = 'imgDownload_' + i;
		text += '</strong></h4><div class="img-for-category" id="imgItem'
				+ i
				+ '"><div id="priceItem'
				+ i
				+ '" class="price-item"></div><img alt="" id="'
				+ idPdf
				+ '" class="bottom-right pdf-item-download" onclick="pdfDownLoad(\''
				+ idPdf + '\')">';
		if (checkDraw) {
			text += '</div><br><h5 class="text-left"><strong>Monthly Recurring Fee (Lease)</strong></h5></div>';
			text += '<div class="col-sm-12">';
			text += '<div id="mrc' + i + '">';
			text += '</div>';
			text += '<div class="text-center"><br><h5 class="text-left"><strong>One Time Charge</strong></h5></div>';
			text += '<div id="otc' + i + '">';
			text += '</div>';

		} else {
			text += '</div><br><h5 class="text-left"></h5></div>';
			text += '<div class="col-sm-12">';
		}
		text += '</div>';
		text += '</div>';
	}

	$("#mainData").append(text);

}

/**
 * create detail for each column
 * 
 * @returns
 */
function createDetailForEachCategory(i) {
	$("#mrc" + i).empty();
	$("#otc" + i).empty();
	var textMrc = "";
	var textOtc = "";
	// MODEL
	textMrc += '<div class="col-sm-10 text-center table-bordered header-mrc"><strong>Model</strong></div>';
	textMrc += '<div class="col-sm-2 text-center table-bordered header-mrc"><strong>Qty</strong></div>';
	textMrc += '<div class="col-sm-10"><select class="form-control select-item" id="'
			+ setId(0, largeCategory[i].categoryCd,
					largeCategory[i].categoryCd, 0, "MRC")
			+ '" onchange="setImage(' + i + ')"></select></div>';
	textMrc += '<div class="col-sm-2"><input type="text" class="form-control quantity-item" id="'
			+ setId(1, largeCategory[i].categoryCd,
					largeCategory[i].categoryCd, 0, "MRC")
			+ '" maxlength="3"></div>'
	for (var a = 0; a < listAddon.length; a++) {
		if (listAddon[a].displayOrder !== null) {
			if (listAddon[a].addonType.toUpperCase() === "MRC") {
				textMrc += drawAddon(listAddon[a], i, listAddon[a].addonType
						.toUpperCase());
			} else if (listAddon[a].addonType.toUpperCase() === "OTC") {
				textOtc += drawAddon(listAddon[a], i, listAddon[a].addonType
						.toUpperCase());
			}
		}

	}
	$("#mrc" + i).append(textMrc);
	$("#otc" + i).append(textOtc);
	setTabindex();
}

function drawDepartment(companySelected) {
	$('#departments').find('option').remove();
	companySelectedOject = {};
	$.each(listCompanies, function(i, company) {
		if (company.companyCd === companySelected) {
			companySelectedOject = company;
			$.each(company.listDepartment, function(i, item) {
				$('#departments').append($('<option>', {
					value : item.deptCd,
					text : item.deptCd + ' (' + item.deptName + ')'
				}));
			});
			return false;
		}

	})
}

/**
 * push data to html (select box)
 */
function pushData() {
	for (var i = 0; i < largeCategory.length; i++) {
		createDetailForEachCategory(i);
		drawSelectModel(i);
	}

	// Validate number
	$('.quantity-item').keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			return false;
		} else {
			oldValue = Number(this.value);
		}

	});

	// // Add handler sum quantity
	// $('.quantity-item').keyup(function(event) {
	// sumQuantity(this);
	// });

	$('.quantity-item').focus(function() {
		// Store the current value on focus, before it changes
		oldValue = this.value;
	}).change(function() {
		// Do soomething with the previous value after the change
		sumQuantity(this);
		oldValue = this.value;
	});

	var previous;

	$('.select-item').focus(function() {
		// Store the current value on focus, before it changes
		previous = this.value;
	}).change(function() {
		// Do soomething with the previous value after the change

		changeSelect(this.id, this.value, previous);
		previous = this.value;

		countPrice();
	});
}

/**
 * inputType = 0 => select inputType = 1 => inputQty inputType = 2 => sumQty
 * 
 * @param inputType
 * @param idLargeCate
 * @param categoryCd
 * @param orderNo
 * @param leaseType
 * @returns
 */
function setId(inputType, idLargeCate, categoryCd, orderNo, leaseType) {
	var type = "";
	var itemCd = itemCd;
	if (inputType === 0) {
		type = "_sel_";
	} else if (inputType === 1) {
		type = "_qty_";
	} else if (inputType === 2) {
		type = "_sum_";
		orderNo = "SUM";
	}
	return idLargeCate + type + categoryCd + "_" + orderNo + "_" + leaseType;
}

/**
 * draw add on
 */
function drawAddon(addon, k, leaseType) {
	var textAddon = '';
	var name = addon.addonName;
	var css = 'header-' + addon.addonType.toLowerCase();

	if (largeCategory[k].categoryCd !== "04DESKTOP"
			&& addon.addonCd === "05MONITOR") {
		textAddon += '<div class="col-md-12 new-line"></div>';
		textAddon += '<div class="col-md-12 new-line"></div>';
		textAddon += '<div class="col-md-12 new-line1"></div>';
		
	} else {
		textAddon += '<div class="col-sm-10 text-center table-bordered ' + css
				+ '"><strong>' + name + '</strong></div>';
		textAddon += '<div class="col-sm-2 text-center table-bordered  ' + css
				+ '"><strong>Qty</strong></div>';
		var check = 0;

		for (var t = 0; t < addon.config; t++) {
			check++;
			textAddon += '<div class="col-sm-10"><select class="form-control select-item" id="'
					+ setId(0, largeCategory[k].categoryCd, addon.addonCd, t,
							leaseType) + '">';
			textAddon += createOptions(companySelectedOject.countryCd,
					addon.addonCd);
			textAddon += '</select></div>';
			textAddon += '<div class="col-sm-2"><input type="text" class="form-control quantity-item" id="'
					+ setId(1, largeCategory[k].categoryCd, addon.addonCd, t,
							leaseType) + '" maxlength="3"></div>';

		}
		var cssNone = "";
		var cssNewLine = "new-line";
		if (check > 0) {
			if (check === 1) {
				cssNone = 'style="display: none"';
				cssNewLine = '';
			}
			textAddon += '<div class="col-sm-10"'
					+ cssNone
					+ '></div><div class="col-sm-2"><input type="text" '
					+ cssNone
					+ ' class="form-control total" id="'
					+ setId(2, largeCategory[k].categoryCd, addon.addonCd,
							addon.addonCd, leaseType) + '" readonly></div>';
		} else {
			textAddon = "";
		}

		textAddon += '<div class="col-md-12 ' + cssNewLine + '"></div>';
	}

	return textAddon;
}

/**
 * Redrawn option (Ex: option1, option 2, option3 when option 2 is change value then drawn option1, option 2)
 * @countryCd country code
 * @addonCd addon code
 * @selectId Id of element used set selected for selectbox
 */
function createOptionsIgnoreSelected(countryCd, addonCd, selectId, listSelected) {
	// Drawn select id
	var selectData = $("#" + selectId).val();
	var options = '';
	options += '<option value=""></option>';
	// Create array, add item code in array after insert into option
	var arrAppendInOption = {};
	for (var x = 0; x < listSelected.length; x ++) {
		arrAppendInOption["'"+listSelected[x]+"'"] = listSelected[x];
	}
	$.each(addonItems, function(i, item) {
		if (item.countryCd === countryCd && item.categoryCd === addonCd) {
			for (var x = 0; x < listSelected.length; x ++) {
				// Check item code not exist in array (listSelected, arrAppendInOption)
				if (listSelected[x] !== item.itemCd && arrAppendInOption["'"+item.itemCd+"'"] === undefined) {
					options += '<option value="' + item.itemCd + '">' + item.itemName + " - S$ " + convertNumberToString7v2(item.unitPrice)
							+ '</option>';
					break;
				}
			}
		}

	});
	// Remove option
	$("#" + selectId).find('option').remove();
	// Append option
	$("#" + selectId).append(options);
	// Set selected after drawn option
	$("#" + selectId  +" option[value='"+selectData+"']").prop('selected', true);
}

/**
 * create option
 */
function createOptions(countryCd, addonCd) {
	var options = '';
	options += '<option value=""></option>';
	$.each(addonItems, function(i, item) {
		if (item.countryCd === countryCd && item.categoryCd === addonCd) {

			options += '<option value="' + item.itemCd + '">' + item.itemName + " - S$ " + convertNumberToString7v2(item.unitPrice)
					+ '</option>';
		}

	});
	return options;
}

/**
 * draw item
 * 
 * @param i
 * @returns
 */
function drawSelectModel(k) {

	var idSelect = "#"
			+ setId(0, largeCategory[k].categoryCd,
					largeCategory[k].categoryCd, 0, "MRC");
	$(idSelect).find('option').remove();
	$(idSelect).append($('<option>', {
		value : "",
		text : ""
	}));
	$.each(modelItems, function(i, item) {
		if (item.categoryCd === largeCategory[k].categoryCd
				&& item.countryCd === companySelectedOject.countryCd
				&& item.String !== "false") {
			$(idSelect).append($('<option>', {
				value : item.itemCd,
				text : item.itemName
			}));
		}

	});
}

/**
 * check exist image
 * 
 * @param url
 * @param callback
 * @returns
 */
function imageExists(url, callback) {
	var img = new Image();
	img.onload = function() {
		callback(true);
	};
	img.onerror = function() {
		callback(false);
	};
	img.src = url;
}

function validateImageURL(imgLink, id) {

	var imageUrl = imgLink;

	imageExists(imageUrl, function(exists) {
		// Show the result
		if (exists) {
			$(id).css('background-image', 'url(' + imgLink + ')');
		} else {
			$(id).css('background-image', 'url(../images/no-image-box.png)');
		}
	});

}

/**
 * set image background for item
 */
function setImage(k) {
	var idSelect = setId(0, largeCategory[k].categoryCd,
			largeCategory[k].categoryCd, 0, "MRC");
	var idSelectQty = setId(1, largeCategory[k].categoryCd,
			largeCategory[k].categoryCd, 0, "MRC");
	var itemCdSelected = $("#" + idSelect).val();
	var quantitySelected = $("#" + idSelectQty).val();
	if (quantitySelected === "") {
		quantitySelected = 0;
	}
	quantitySelected = parseInt(quantitySelected, 10);
	var imgName = "";
	var priceItem;
	var item = null;
	if (itemCdSelected !== "") {
		$.each(modelItems, function(i, item) {
			if (item.itemCd === itemCdSelected && item.countryCd === companySelectedOject.countryCd) {
				imgName = item.imgName;
				priceItem = item.unitPrice;
				return false;
			}
		});

		validateImageURL('../fac001/ImageAction?imgName=' + imgName, '#imgItem'
				+ k);

		$('#priceItem' + k).text(
				"S$ " + convertNumberToString7v2(priceItem) + " / month");
		item = findItem(modelItems, largeCategory[k].categoryCd, itemCdSelected);
		$('#imgDownload_' + k).attr('src', '../images/pdf-download.png');
	} else {
		$('#imgItem' + k).css('background-image',
				'url(../images/no-image-box.png');
		$('#priceItem' + k).text("");
		$('#imgDownload_' + k).removeAttr('src');
	}

	var checkExist = false;
	$.each(listItemOrder, function(i, itemOrder) {
		if (largeCategory[k].categoryCd === itemOrder.categoryCd
				&& itemOrder.categoryCd === itemOrder.categoryLarge) {
			if (item !== null) {
				checkExist = true;
				itemOrder.itemCd = item.itemCd;
				itemOrder.itemName = item.itemName;
				itemOrder.unitPrice = item.unitPrice;
				itemOrder.warningFg = item.warningFg;
			} else {
				listItemOrder.splice(i, 1);
			}
			return false;
		}
	});
	if (checkExist === false && item !== null) {
		listItemOrder.push(new ItemOrder(largeCategory[k].categoryCd,
				item.itemCd, item.itemName, item.categoryCd, item.addonCd, quantitySelected,
				item.unitPrice, item.leaseType, item.warningFg,
				item.addonConfig));
	}

	// clear listItemOrder
	$.each(listItemOrder, function(i, itemOrder) {
		if (itemOrder.qty === "0" || itemOrder.qty === 0) {
			if (itemOrder.categoryCd === itemOrder.categoryLarge) {
				clearItemOrderWithcategoryLarge(itemOrder.categoryLarge);
			}
		}
	});

	countPrice();

}

/**
 * calculate sum Quantity
 */
var confirmNotEnoughStock = null;
var confirmAddMemory = null;
function sumQuantity(object) {
	var quantity = object.value;
	if (quantity === "") {
		quantity = 0;
	}
	quantity = Number(quantity);

	var item = null;
	var idQuantity = object.id;
	var idItem = idQuantity.replace("_qty_", "_sel_");
	var itemCd = $("#" + idItem).val();
	var haveSumId = false;
	idQuantity = object.id;
	var idSumId = idQuantity.replace("_qty_", "_sum_").split("_");
	if (idSumId[0] !== idSumId[2]) {
		haveSumId = true;
		idSumId = idSumId[0] + "_" + idSumId[1] + "_" + idSumId[2] + "_SUM_"
				+ idSumId[4];
	} else {

		// item model
		// clear addon
		if (idSumId[0] === idSumId[2] && quantity === 0) {
			clearAddon(idSumId);
		}
	}
	if ($.isNumeric(quantity) && Math.floor(quantity) === quantity
			&& itemCd !== "") {

		var idItemArray = idItem.split("_");
		var largeCategory = idItemArray[0];
		var leaseType = idItemArray[4];
		// check item is Model
		if (idItemArray[0] === idItemArray[2]) {
			item = findItem(modelItems, idItemArray[2], itemCd);
			// LongND 12_apr_2018 remove check for not enough stock
			/*if ((item.qtyInStock === null && quantity > 0 && flagEdit === false)
					|| (quantity > 0 && quantity > item.qtyInStock && flagEdit === false)) {
				ConfirmDialog(getMessageWithParams("PCOD-0011"), object, 1);
				if (confirmNotEnoughStock === true) {
					confirmNotEnoughStock = null;
				} else {
					confirmNotEnoughStock = null;
					return false;
				}
			}*/

		} else {
			item = findItem(addonItems, idItemArray[2], itemCd);

		}

		if (item !== null) {
			var checkExist = false;
			$.each(listItemOrder, function(i, itemOrder) {
				if (item.itemCd === itemOrder.itemCd
						&& largeCategory === itemOrder.categoryLarge) {
					checkExist = true;
					itemOrder.qty = quantity;
					return false;
				}
			});
			if (checkExist === false) {
				listItemOrder.push(new ItemOrder(largeCategory, item.itemCd,  item.itemName,
						item.categoryCd, item.addonCd, quantity,
						item.unitPrice, item.leaseType, item.warningFg,
						item.addonConfig));
			}

		} else {
			$("#" + object.id).val(oldValue);
		}
		if (haveSumId === true && !calculateSum(idSumId) && quantity > 0) {

			$.each(listItemOrder, function(i, itemOrder) {
				if (itemCd === itemOrder.itemCd
						&& largeCategory === itemOrder.categoryLarge) {
					if (oldValue === "") {
						oldValue = 0;
					}
					//Hantt shift the quantity check to Save
					/*if (itemOrder.qty > oldValue) {
						oldValue = 0;
						itemOrder.qty = oldValue;
					} else {*/
						oldValue = itemOrder.qty;
					//}
					$("#" + object.id).val(oldValue);
					calculateSum(idSumId);
					return false;
				}

			});
			
			
			//Hantt shift the quantity check to Save
			/*if (redirect === "1") {
				$("#contentMessage").text(getMessageWithParams("PCOD-0012"));
				$("#modalInfo").modal("show");
			}*/

		} 
//		else if (item.addonCd === "MEMORY" && quantity > 0) {
//			if (confirmAddMemory === null && flagEdit === false) {
//				$.each(listItemOrder, function(i, itemOrder) {
//					if (idItemArray[0] === itemOrder.categoryCd
//							&& itemOrder.categoryCd === itemOrder.categoryLarge
//							&& itemOrder.warningFg === "1") {
//						ConfirmDialog(getMessageWithParams("PCOD-0013"),
//								object, 2);
//
//						return false;
//					}
//				});
//			} else if (confirmAddMemory === true) {
//				$.each(listItemOrder, function(i, itemOrder) {
//					if (idItemArray[0] === itemOrder.categoryLarge
//							&& itemOrder.itemCd === item.itemCd) {
//						itemOrder.warningFg = "1";
//						return false;
//					}
//				});
//				confirmAddMemory = null;
//			} else {
//				confirmAddMemory = null;
//				return false;
//			}
//		}
	} else if (itemCd === "") {
		$("#" + object.id).val("");
	} else {
		if (oldValue === 0) {
			$("#" + object.id).val("");
		} else {
			$("#" + object.id).val(oldValue);
		}

	}
	// clear listItemOrder
	$.each(listItemOrder, function(i, itemOrder) {
		if (itemOrder.qty === "0" || itemOrder.qty === 0) {
			if (itemOrder.categoryCd === itemOrder.categoryLarge) {
				clearItemOrderWithcategoryLarge(itemOrder.categoryLarge);
				return false;
			}

		}
	});
	if (haveSumId === true) {
		calculateSum(idSumId);
	}
	countPrice();

	// Check quantity exceeds the total quantity.
	//Longnd 12_Apr_2018 remove checl quantity
	//Hantt shift the quantity check to Save
	/*if (checkQuantityExceeds()) {
		$("#contentMessage").text(getMessageWithParams("PCOD-0012"));
		$("#modalInfo").modal("show");
	}*/
	/*
	 * if (quantity == 0) { $("#" + object.id).val(""); }
	 */
}

/**
 * 
 * @param message
 * @param object
 * @param type
 * @returns
 */
/*function ConfirmDialog(message, object, type) {
	$('<div></div>').appendTo('body').html(
			'<div><h6>' + message + '?</h6></div>').dialog({
		modal : true,
		title : 'Not Enough Stock',
		zIndex : 10000,
		autoOpen : true,
		width : 'auto',
		resizable : false,
		buttons : {
			Yes : function() {
				alert("yes");
				if (type === 1) {
					confirmNotEnoughStock = true;
					sumQuantity(object);
				} else if (type === 2) {
					confirmAddMemory = true;
					sumQuantity(object);
				}

				$(".ui-dialog-titlebar-close").click();
			},
			No : function() {
				alert("no");
				$("#" + object.id).val("");
				if (type === 1) {
					$("#" + object.id).val("0");
					confirmNotEnoughStock = false;
					sumQuantity(object);
				} else if (type === 2) {
					confirmAddMemory = false;
					sumQuantity(object);
				}
				$(".ui-dialog-titlebar-close").click();
			}
		},
		close : function(event, ui) {
			alert("close");
			$(this).remove();
		}
	});
	$(".ui-dialog-titlebar-close").hide();
};*/


function ConfirmDialogForMemory(message, item) {
	$('<div></div>').appendTo('body').html(
			'<div><h6>' + message + '?</h6></div>').dialog({
		modal : true,
		title : 'Warning',
		zIndex : 10000,
		autoOpen : true,
		width : 'auto',
		resizable : false,
		buttons : {
			Yes : function() {
				confirmAddMemory = true;
				// LongND 23_Apr_2018 change warning
				/*for(var i = 0; i < item.length; i++){
					listItemOrder[item[i]].warningFg = "1";
				}
				*/
				changeWarningFlg("yes");
				$(".ui-dialog-titlebar-close").click();
			},
			No : function() {
				// LongND 23_Apr_2018 change warning
//				confirmAddMemory = false;
				/*for(var i = 0; i < item.length; i++){
					listItemOrder[item[i]].warningFg = "0";
				}

				changeWarningFlg("no");*/
				$(".ui-dialog-titlebar-close").click();
			}
		},
		close : function(event, ui) {
			$(this).remove();
		}
	});
	$(".ui-dialog-titlebar-close").hide();
};

/**
 * find Item
 */
function findItem(listItem, categoryCd, itemCd) {
	var itemData = null;
	// Compnay code
	var companies = $("#companies").val();
	// Country code
	var countryCd = "";
	// Get country by company
	$.each(listCompanies, function(i, c) {
		if (c.companyCd === companies) {
			countryCd = c.countryCd;
		}
	});
	$.each(listItem, function(i, item) {
		if (item.itemCd === itemCd && item.categoryCd === categoryCd && item.countryCd === countryCd) {
			itemData = item;
			return false;
		}
	});

	return itemData;
}

/**
 * calculated sum quantity
 */
function calculateSum(idSum) {
	var idSumArray = idSum.split("_");
	var quantityData = 0;
	var maxQuantity = 0;
	var check = true;
	for (var t = 0; t < largeCategorySelected.length; t++) {
		if (idSumArray[0] === largeCategorySelected[t].categoryCd) {
			maxQuantity = largeCategorySelected[t].qty;
			$.each(listItemOrder, function(i, itemOrder) {

				if (idSumArray[2] === itemOrder.categoryCd
						&& idSumArray[0] === itemOrder.categoryLarge) {
					quantityData += itemOrder.qty;
				}
			});
			break;
		}

	}
	
	//Longnd 12_Apr_2018 remove checl quantity
	if (quantityData > maxQuantity || quantityData == 0 || quantityData == null ) {
		check = false;
	}
 
	$("#" + idSum).val(quantityData);
	var valSum = $("#" + idSum).val();
	if (valSum === "0") {
		$("#" + idSum).val("");
	}
	return check;

}

/**
 * clear with categoryLarge
 */
function clearItemOrderWithcategoryLarge(categoryLargeValue) {
	for (var m = listItemOrder.length - 1; m >= 0; m--) {
		if (listItemOrder[m].categoryLarge === categoryLargeValue) {
			listItemOrder.splice(m, 1);

		}
	}
}

//10_May_2018
function disableAddMemory(arrayId) {
	var max = 0;
	$.each(listAddon, function(i, addon) {
		if(addon.addonCd === "06MEMORY") {
			//alert(addon.addonCd);
			max = addon.config;
			return false;
		}
	});
	var itemCd = $("#" + arrayId[0]+"_"+ arrayId[1]+"_"+ arrayId[2]+"_"+ arrayId[3]+"_"+ arrayId[4]).val();
	var item = findItem(modelItems, arrayId[2], itemCd);
	//alert(itemCd + "-- " + item.warningFg);
	if (!item.warningFg === 1 || !item.warningFg === "1" || item.warningFg === null) {
		for (var i = 0; i< max; i++) {
			var idDisableSel = setId(0, arrayId[0], "06MEMORY", i, "OTC");
			var idDisableQty = setId(1, arrayId[0], "06MEMORY", i, "OTC");
			var idDisableSum = setId(2, arrayId[0], "06MEMORY", i, "OTC");
			$("#" + idDisableSel).prop('disabled', true);
			for (var j = listItemOrder.length - 1; j >= 0; j--) {
				if (listItemOrder[j].categoryCd === "06MEMORY" && listItemOrder[j].categoryLarge === arrayId[0]) {
					listItemOrder.splice(j, 1);

				}
			}
			$("#" + idDisableSel).val("");
			$("#" + idDisableQty).prop('disabled', true);
			$("#" + idDisableQty).val("");
			$("#" + idDisableSum).val("");
		}
	}
	else {
		for (var i = 0; i< max; i++) {
			var idDisableSel = setId(0, arrayId[0], "06MEMORY", i, "OTC");
			var idDisableQty = setId(1, arrayId[0], "06MEMORY", i, "OTC");
			$("#" + idDisableSel).prop('disabled', false);
			$("#" + idDisableQty).prop('disabled', false);
		}
	}
	
	
}


/**
 * when change select
 */
function changeSelect(idSelect, value, oldItemCode) {
	var oldIdSelect = idSelect;
	var idArray = idSelect.split("_");
	var listIdOtherSelectAddon = [];
	var listValueSelected = {};
	// select is addon
	if (idArray[0] !== idArray[2]) {
		var idOther = "";
		// Get id select of selectbox item is not change (Ex: option1, option2, option3. When option 2 change value then get id of option1, option2)
		for (var count = 0; count < 20; count++) {
			idOther = setId(0, idArray[0], idArray[2], count, idArray[4]);
			var data = $("#" + idOther).val();

			if (oldIdSelect !== idOther && data !== undefined) {
				listValueSelected[idOther] = data;
			} else if (data === undefined) {
				break;
			}

		}
		for (var i in listValueSelected) {
			var idOtherSub = "";
			var listValueSelectedSub = [];
			// Get select value selectbox item is not change (Ex: option1, option2, option3. When option 2 change value then get value of option1, option2)
			for (var count = 0; count < 20; count++) {
				idOtherSub = setId(0, idArray[0], idArray[2], count, idArray[4]);
				var data = $("#" + idOtherSub).val();

				if (i !== idOtherSub && data !== undefined) {
					listValueSelectedSub.push(data);
				} else if (data === undefined) {
					break;
				}

			}
			// Redrawn option (Ex: option1, option 2, option3 when option 2 is change value then drawn option1, option 2)
			createOptionsIgnoreSelected(companySelectedOject.countryCd, idArray[2], i, listValueSelectedSub);
		}
		$.each(listItemOrder, function(i, itemOrder) {

			if (itemOrder.categoryLarge === idArray[0]
					&& itemOrder.itemCd === oldItemCode) {
				listItemOrder.splice(i, 1);
				return false;

			}
		});
		var object = {};
		var idQtyItem = oldIdSelect.replace("_sel_", "_qty_");
		if (value === "") {
			$("#" + idQtyItem).val("");
		}
		var valueQty = $("#" + idQtyItem).val();
		object.id = idQtyItem;
		object.value = valueQty;
		sumQuantity(object);
	} else {
		// select is ItemModel
		if (idArray[0] === idArray[2]) {
			var dataSelect = $("#" + oldIdSelect).val();
			// 10_May_2018
			disableAddMemory(idArray);
			
			if (dataSelect === "") {
				// when remove item model
				clearAddon(idArray);

			} else {
				var object = {};
				var idQtyItem = oldIdSelect.replace("_sel_", "_qty_");
				var valueQty = $("#" + idQtyItem).val();
				object.id = idQtyItem;
				object.value = valueQty;
				sumQuantity(object);
			}

		}
	}

}

/**
 * clear add-on
 */
function clearAddon(idArray) {
	var idOther = "";
	$.each(listAddon, function(m, addon) {

		for (var count = 0; count < 20; count++) {
			idOther = setId(1, idArray[0], addon.addonCd, count,
					addon.addonType);
			var data = $("#" + idOther).val();
			if (data === undefined) {
				break;
			} else if (data !== "") {
				$("#" + idOther).val("");
				var object = {};
				object.id = idOther;
				object.value = 0;
				sumQuantity(object);

			}

		}
	});
}

/**
 * remove option in combo box add-on
 * 
 * @param selectId
 * @param countryCd
 * @param addonCd
 * @param listValueSelected
 * @returns
 */
function removeOption(selectId, countryCd, addonCd, listValueSelected) {
	var dataThisSelect = $("#" + selectId).val();
	if (dataThisSelect === "") {
		$("#" + selectId).find('option').remove();
		$("#" + selectId).append(createOptions(countryCd, addonCd));
	}

	for (var k = 0; k < listValueSelected.length; k++) {
		if (dataThisSelect !== listValueSelected[k]) {
			$("#" + selectId + " option[value='" + listValueSelected[k] + "']")
					.remove();
		}

	}
}

/**
 * remove option in combo box add-on
 * 
 * @param selectId
 * @param countryCd
 * @param addonCd
 * @param listValueSelected
 * @returns
 */
function removeOptionAnhnt(selectId, selectIdOrder, countryCd, addonCd, listValueSelected) {
	var dataThisSelect = $("#" + selectId).val();
	var dataThisSelectOrder = $("#" + selectIdOrder).val();

	if (dataThisSelect === "") {
		$("#" + selectId).find('option').remove();
		$("#" + selectId).append(createOptions(countryCd, addonCd));
	}

	for (var k = 0; k < listValueSelected.length; k++) {
		if (dataThisSelectOrder === listValueSelected[k]) {
			$("#" + selectId + " option[value='" + listValueSelected[k] + "']")
					.remove();
		}

	}
}
/**
 * count price
 */
function countPrice() {
	totalOneTimeCharge = 0;
	totalMonthlyRecurring = 0;
	sumQuantityData = 0;
	fireEyeEdgeXSoftware = 0;
	pcMonitoringSoftware = 0;
	largeCategorySelected = [];
	flagOrderValidAddonQty = true;
	checkExistCtegoryLarge = false;
	anyChange = true;

	// clear listItemOrder
	/*for (var i = listItemOrder.length - 1; i >= 0; i--) {
		if (listItemOrder[i].qty === "0" || listItemOrder[i].qty === 0) {
			listItemOrder.splice(i, 1);

		}
	}*/

	// get price of MRC, OTC
	// get total quantity = model.quantity
	// not include anti-virus and monitoring because quantity need set = total
	// quanrity later
	$
			.each(
					listItemOrder,
					function(i, itemOrder) {
						if (itemOrder.leaseType === "MRC"
								&& !(itemOrder.categoryCd === "ANTIVIRUS" || itemOrder.categoryCd === "MONITORING")) {
							totalMonthlyRecurring += itemOrder.unitPrice
									* itemOrder.qty
							totalMonthlyRecurring;
							if (itemOrder.categoryLarge === itemOrder.categoryCd) {
								sumQuantityData += itemOrder.qty;
								largeCategorySelected.push(itemOrder);
							}
						} else if (itemOrder.leaseType === "OTC") {
							totalOneTimeCharge += itemOrder.unitPrice
									* itemOrder.qty;
						}
					});
	for (var m = 0; m < largeCategorySelected.length; m++) {
		if (checkExitsAntivirusMonitoring(largeCategorySelected[m].categoryLarge) === false) {
			$.each(listHiddenItems, function(i, item) {
				if (companySelectedOject.countryCd === item.countryCd) {
					listItemOrder.push(new ItemOrder(
							largeCategorySelected[m].categoryLarge,
							item.itemCd, item.itemName, item.categoryCd, item.addonCd,
							largeCategorySelected[m].qty, item.unitPrice,
							item.leaseType, item.warningFg, item.addonConfig));
				}

			});

		}
		var countAddon = 0;

		$
				.each(
						listItemOrder,
						function(i, itemOrder) {
							if (itemOrder.addonCd === "ANTIVIRUS") {
								if (itemOrder.categoryLarge === largeCategorySelected[m].categoryLarge) {
									itemOrder.qty = largeCategorySelected[m].qty;
									//Hantt shift the quantity check to Save
									//fireEyeEdgeXSoftware += itemOrder.qty
											//* itemOrder.unitPrice;
								}
							} else if (itemOrder.addonCd === "MONITORING") {
								if (itemOrder.categoryLarge === largeCategorySelected[m].categoryLarge) {
									itemOrder.qty = largeCategorySelected[m].qty;
									//Hantt shift the quantity check to Save
									//pcMonitoringSoftware += itemOrder.qty
											//* itemOrder.unitPrice;
								}
							} else if ((itemOrder.categoryLarge === largeCategorySelected[m].categoryLarge)
									&& itemOrder.categoryLarge !== itemOrder.categoryCd) {
								checkExistCtegoryLarge = true;
								countAddon++;
								if (itemOrder.qty > largeCategorySelected[m].qty) {
									flagOrderValidAddonQty = false;
								}
							}
						});
	}
	if (countAddon === 0) {
		checkExistCtegoryLarge = true;
	}

	//Hantt delete FireEye EdgeX Software and PC Monitoring
	//totalMonthlyRecurring += fireEyeEdgeXSoftware;
	//totalMonthlyRecurring += pcMonitoringSoftware;

	/*$("#totalOneTomeCharge").val(totalOneTimeCharge.toLocaleString("en"));
	$("#monthlyRecurring").val(
			parseFloat(totalMonthlyRecurring).toLocaleString("en"));
	$("#fireEyeEdgeXSoftware").val(fireEyeEdgeXSoftware.toLocaleString("en"));
	$("#pcMonitoringSoftware").val(pcMonitoringSoftware.toLocaleString("en"));*/
	
	$("#totalOneTomeCharge").val(convertNumber2Decimal(totalOneTimeCharge));
	$("#monthlyRecurring").val(convertNumber2Decimal(totalMonthlyRecurring));
	//$("#fireEyeEdgeXSoftware").val(convertNumber2Decimal(fireEyeEdgeXSoftware));
	//$("#pcMonitoringSoftware").val(convertNumber2Decimal(pcMonitoringSoftware));
}

/**
 * check exist anti-virus. monitoring with largeCategory
 */
function checkExitsAntivirusMonitoring(categoryLarge) {
	var checkExits = false;
	$
			.each(
					listItemOrder,
					function(i, itemOrder) {
						if (itemOrder.categoryLarge === categoryLarge
								&& (itemOrder.addonCd === "ANTIVIRUS" || itemOrder.addonCd === "MONITORING")) {
							checkExits = true;
							return false;
						}
					});
	return checkExits;
}

/**
 * The selected quantity exceeds the total quantity.
 * 
 * @returns exceeds return true else false
 */
function checkQuantityExceeds() {
	listSumError = [];
	var isExceeds = false;
	var max = 0;
	var idSum;

	$.each(largeCategorySelected, function(j, cate) {
		$.each(listAddon, function(i, addon) {
			if (addon.addonCd !== "99WINDOWS") {
				max = addon.config;

				var countModel = cate.qty;
				for (var i = 0; i < max; i++) {
					idSum = setId(2, cate.categoryLarge, addon.addonCd, i,
							"OTC");
					var idQty1 = setId(1, cate.categoryLarge, addon.addonCd, i,
					"OTC");
					var checkQty = $("#" + idSum).val();
					if (checkQty !== "" && checkQty > countModel) {
						//isExceeds = true;
						if(max === 1){
							listSumError.push(idQty1);
						}
						listSumError.push(idSum);
						isExceeds = true;
						return false;
					}

				}
			}
		});

	});
	if (isExceeds) {
		$("#"+idSum).css("background-color","red");
		$("#"+idSum).css("color","white");
	} else {
		//$("#"+idSum).css("border","");
	}
	
	return isExceeds;
}

var idSum;
function checkEqualQtyOS(){
	var max = 0;
	$.each(listAddon, function(i, addon) {
		if(addon.addonCd === "99WINDOWS") {
			max = addon.config;
			return false;
		}
	});
	var isEqual = false;
	
	$.each(largeCategorySelected, function(j, cate) {
		var countModel = cate.qty;
		for (var i = 0; i< max; i++) {
			idSum = setId(2, cate.categoryLarge, "99WINDOWS", i, "MRC");
			var checkQty = $("#"+idSum).val();
			for (var j = listItemOrder.length - 1; j >= 0; j--) {
				if (listItemOrder[j].categoryCd === "99WINDOWS" && checkQty !== "" && checkQty != countModel && listItemOrder[j].categoryLarge === cate.categoryCd ) {
					isEqual = true;

				}
			}
		}
		if (isEqual) {
			$("#"+idSum).css("background-color","red");
			$("#"+idSum).css("color","white");
		} else {
			$("#"+idSum).css("background-color","");
			$("#"+idSum).css("color","black");
		}
		
	});
	
	return isEqual;
}

var listSumError = [];

function checkEqualQtyAddOn(){
	
	listSumError = [];
	var hasError = false;
	var max = 0;
	var idSum;

	$.each(largeCategorySelected, function(j, cate) {
		$.each(listAddon, function(i, addon) {
			if (addon.addonCd !== "99WINDOWS") {
				max = addon.config;

				var countModel = cate.qty;
				for (var i = 0; i < max; i++) {
					idSum = setId(2, cate.categoryLarge, addon.addonCd, i,
							"OTC");
					var idQty1 = setId(1, cate.categoryLarge, addon.addonCd, i,
					"OTC");
					var checkQty = $("#" + idSum).val();
					if (checkQty !== "" && checkQty < countModel) {
						hasError = true;
						if(max === 1){
							listSumError.push(idQty1);
						}
						listSumError.push(idSum);
						return false;
					}

				}
			}
		});

	});

	return hasError;
	
}

function checkQtyNull(){
	var checkQtyNull = false;
	$.each(largeCategorySelected, function(j, cate) {
		$.each(listItemOrder, function(i, itemOrder) {
			
			
			if (itemOrder.categoryLarge ===  cate.categoryCd 
					&& itemOrder.categoryCd !== null && itemOrder.categoryCd !== itemOrder.categoryLarge) {
				var max;
				$.each(listAddon, function(i, addon) {
					if(addon.addonCd === itemOrder.categoryCd) {
						//alert(addon.addonCd);
						max = addon.config;
						return false;
					}
				});
				for (var i = 0; i < max; i++){
					var select = setId(0, cate.categoryLarge, itemOrder.categoryCd, i, "OTC");
					
					var idQty = setId(1, cate.categoryLarge, itemOrder.categoryCd, i, "OTC");
					var checkQty = $("#"+idQty).val();
					if (checkQty === undefined) {
						select = setId(0, cate.categoryLarge, itemOrder.categoryCd, i, "MRC");
						
						idQty = setId(1, cate.categoryLarge, itemOrder.categoryCd, i, "MRC");
						checkQty = $("#"+idQty).val();
					}
					if ("" != $("#"+select).val() && (checkQty === "" || checkQty === "0")) {
						checkQtyNull = true;
						return false;
					}
				}
				
			}
		});
		
	});
	return checkQtyNull;
}

// addon qty check

function confirmWarningAddonQty(confirm) {
	$("#modalInfo2").modal("hide");
	if (confirm === 'yes') {
		countPrice();
		createNewOrder();
	} else {
		for (var i = 0; i < listSumError.length; i++) {
			$("#" + listSumError[i]).css("background-color","red");
			$("#" + listSumError[i]).css("color","white");
		}
	}
}
/**
 * Draw department with companyCode
 * 
 * @param companySelected
 * @returns
 */
function doEventDialog01(idEvent, element) {
	if (idEvent == "save") {
		// Check quantity exceeds the total quantity.
		//Longnd 12_Apr_2018 remove checl quantity
		//Hantt shift the quantity check to Save
		for (var i = 0; i < listSumError.length; i++) {
			$("#" + listSumError[i]).css("background","");
			$("#" + listSumError[i]).css("color","black");
		}
		if (checkQuantityExceeds()) {
			$("#contentMessage").text(getMessageWithParams("PCOD-0012"));
			$("#btnOk").attr("onclick","confirmWarningAddonQty('no')");
			$("#modalInfo").modal("show");
			return false;
		}
		else if (checkEqualQtyOS()) {
			$("#contentMessage").text(getMessageWithParams("PCOD-0018"));
			$("#modalInfo").modal("show");
			return false;
		}
		else if (checkQtyNull()) {
			$("#contentMessage").text(getMessageWithParams("PCOD-0019"));
			$("#modalInfo").modal("show");
			return false;
		} else if (checkEqualQtyAddOn()) {
			//$("#contentMessage").text("DDD");
			
			//$("#modalConfirm").modal("show");
			
			//var contentConfirm = getMessageWithParams(messageId,"");
			var contentConfirm = "The item quantity not equal to the model quantity. Do you want to save?"
			document.getElementById("contentMessage2").innerHTML = contentConfirm;
			$("#buttonYes2").attr("onclick","confirmWarningAddonQty('yes')");
			$("#buttonNo2").attr("onclick","confirmWarningAddonQty('no')");
			$("#modalInfo2").modal("show");
			
			return false;
		} else {
			$("#buttonYes2").attr("onclick","doYes('save')");
			$("#buttonNo2").attr("onclick","");
		}
		countPrice();
		createNewOrder();
	} else if (idEvent === "printOrder") {
		printOrder();
	} else if (idEvent === "sendOrder") {
		sendOrder();
	} else if (idEvent === "deleteOrder") {
		deleteOrder();
	} else if (idEvent === "close") {
		closeScreen();
	} else if (idEvent === "alertMemory") {
		alert("Memory");
	}
}

/**
 * save new Order
 */
function createNewOrder() {
	if (listItemOrder.length === 0) {
		$("#contentMessage").text("No item in order!");
		$("#modalInfo").modal("show");
	} else if ($("#departments").val() === null) {
		$("#contentMessage").text("Please select department!");
		$("#modalInfo").modal("show");
	//Longnd 12_Apr_2018 remove checl quantity
	} else if (flagOrderValidAddonQty === false
			|| checkExistCtegoryLarge === false) {
		$("#contentMessage").text(getMessageWithParams("PCOD-0012"));
		$("#modalInfo").modal("show");
	} else {
		var params = {};
		params.countryCd = companySelectedOject.countryCd;
		params.companyCd = companySelectedOject.companyCd;
		params.deptCd = $("#departments").val();
		params.remark = $("#remark").val();
		params.totalMonthlyRecurring = totalMonthlyRecurring;
		params.totalOneTimeCharge = totalOneTimeCharge;
		//Hantt shift the quantity check to Save
		//params.fireEyeEdgeXSoftware = fireEyeEdgeXSoftware;
		//params.pcMonitoringSoftware = pcMonitoringSoftware;
		params.listItemOrder = JSON.stringify(listItemOrder);
		params.orderId = orderId;
		params.quoteMrc = quoteMrc;
		params.quoteOtc = quoteOtc;

		loading_ajax();
		$.ajax({
			url : '../fac001/sac0011_03',
			type : "POST",
			data : params,
			success : function(response) {
				close_loading_ajax();
				orderId = response.orderId;
				quoteMrc = response.quoteMrc;
				quoteOtc = response.quoteOtc;
				error = response.error;
				if (error) {
					$("#contentMessage").text(error.value);
					$("#modalInfo").modal("show");
				} else {
					$("#sendOrder").attr("disabled", false);
					$("#orderId").val(orderId);
					$("#quotationNo").val(quoteMrc + " / " + quoteOtc);
					$("#printOrder").removeAttr("disabled");
					$("#deleteOrder").removeAttr("disabled");
					$("#printOrder").click(function() {
						printOrder();

					})

					$("#sendOrder").click(function() {
						showConfirmDialog("sendOrder", "", "", {
							"position" : "200"
						});

					})

					$("#deleteOrder").click(function() {
						showConfirmDialog("deleteOrder", "", "", {
							"position" : "200"
						});

					})
				}

			},
			error : function(jqXhr, textStatus, errorThrown) {
				$("#contentMessage").text(getMessageWithParams("PCOD-0010"));
				$("#modalInfo").modal("show");
				close_loading_ajax();
			}
		});
	}

}
/**
 * down pdf file
 */

function pdfDownLoad(id) {
	var categoryCdDownload = largeCategory[id.split("_")[1]].categoryCd;
	var idItem = setId(0, categoryCdDownload, categoryCdDownload, 0, "MRC");
	var itemCd = $("#" + idItem).val();
	var item = findItem(modelItems, categoryCdDownload, itemCd);
	$("#fileNameDownload_pdf").val(item.pdfName);
	var params = {};
	params.fileNameDownload = item.pdfName;
	params.checkPdfExist = true;
	loading_ajax();
	$.ajax({
		url : '../fac001/sac0011_08',
		type : "GET",
		data : params,
		contentType : "application/xml; charset=utf-8",
		success : function(response) {
			if (response.error === null) {

				$("#formDownLoad_pdf").submit();

			} else {

				$("#contentMessage").text(response.error.value);
				$("#modalInfo").modal("show");
			}
			close_loading_ajax();
		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});

}
/**
 * print order
 */
function printOrder() {
	/*$("#modalConfirmPrint").modal("show");
	// submit form to download file
	$("#buttonOk").click(function() {*/

		$("#nameFileMrc").text(quoteMrc + ".pdf");
		$("#nameFileOtc").text(quoteOtc + ".pdf");
		$("#formDownLoad").attr('target', '_blank');
		$("#orderIdDowload").val(orderId);
		$("#filePath").val(configObject.quoteDir + "\\");
		$("#fileNameDownload").val(quoteMrc + ".pdf");
		$("#formDownLoad").submit();

		$("#formDownLoad_2").attr('target', '');
		$("#orderIdDowload_2").val(orderId);
		$("#filePath_2").val(configObject.quoteDir + "\\");
		$("#fileNameDownload_2").val(quoteOtc + ".pdf");
		setTimeout(function() {
			$("#formDownLoad_2").submit();

		}, 2000);

		//$("#modalConfirmPrint").modal('hide');
		$("#sendOrder").removeAttr("disabled");
	//});
	/*$("#closePrint").click(function() {
		$("#modalConfirmPrint").modal('hide');
	});*/

}

/**
 * send order
 */
function sendOrder() {
	var params = {};
	params.orderId = orderId;
	loading_ajax();
	$.ajax({
		url : '../fac001/sac0011_05',
		type : "POST",
		data : params,
		success : function(response) {
			close_loading_ajax();
			showSuccessDialog("PCOD-0016");

		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
			showErrorDialog("PCOD-0017");
		}
	});
}

/**
 * delete order
 */
function deleteOrder() {
	var params = {};
	params.orderId = orderId;
	loading_ajax();
	$.ajax({
		url : '../fac001/sac0011_06',
		type : "POST",
		data : params,
		success : function(response) {
			close_loading_ajax();

		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});
}

/**
 * 
 */
function doClose() {
	if (anyChange) {
		showConfirmDialog("close", "", "", {
			"position" : "200"
		})
	} else {
		closeScreen();
	}
}

function closeScreen() {
	if (redirect === "1") {
		loading_ajax();
		$.ajax({
			url : '../fac001/sac0011_09',
			type : "GET",
			data : "",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				location.href = "../fac001/sac0021.action";
			},
			error : function(jqXhr, textStatus, errorThrown) {
				close_loading_ajax();
			}
		});

	} else {
		location.href = "../faa001/saa0012.action";
	}

};

/*******************************************************************************
 * draw to edit order
 ******************************************************************************/
function drawToEditOrder() {
	$('#remark').val(orderInfo.order.remark);
	$("#orderId").val(orderInfo.order.orderId);
	quoteMrc = orderInfo.order.quoteMrc;
	quoteOtc = orderInfo.order.quoteOtc;
	$("#quotationNo").val(quoteMrc + " / " + quoteOtc);
	$('#companies').val(orderInfo.order.companyCd);
	var selectedText = $("#companies option:selected").html();
	drawDepartment(selectedText);
	$('#departments').val(orderInfo.order.deptCd);
	// Show text of department
	var deptCd = orderInfo.order.deptCd;
	var deptName = findDepartmentName(deptCd);
	if (!deptCd) {
		deptCd = "";
	}
	if (deptName && deptName != ""){
		deptName = "(" + deptName + ")";
	} else {
		deptName = "";
	}
	$("#deptCd-span").text(deptCd);
	$("#deptName-span").text(deptName);
	
	drawCategory();
	pushData();

	drawItemToEdit();
	drawAddonItem();
	flagEdit = false;
	anyChange = false;
	
	// Enable Button PrintOrder, SendOrder
	$("#printOrder").removeAttr("disabled");
	
	$("#printOrder").click(function() {
		printOrder();

	})
	if (orderInfo.order.printFg === "1"){
		$("#sendOrder").removeAttr("disabled");
		$("#sendOrder").click(function() {
			showConfirmDialog("sendOrder", "", "", {
				"position" : "200"
			});
	
		});
	}
}

/**
 * 
 * @param categoryLarge
 * @param categoryCd
 * @param selectId
 * @param selectValue
 * @param inputId
 * @param inputValue
 * @returns
 */
function ItemDraw(categoryLarge, categoryCd, selectId, selectValue, inputId,
		inputValue) {
	this.categoryLarge = categoryLarge;
	this.categoryCd = categoryCd;
	this.selectId = selectId;
	this.selectValue = selectValue;
	this.inputId = inputId;
	this.inputValue = inputValue;
}

var listItemDraw = [];
var listAddonItemDraw = [];

/**
 * draw order
 * 
 * @returns
 */
function drawItemToEdit() {
	$.each(orderInfo.listOrderDtl, function(i, itemOrder) {
		var selectId = setId(0, itemOrder.categoryCd, itemOrder.categoryCd, 0,
				itemOrder.leaseType);
		var selectValue = itemOrder.itemCd;
		var inputId = setId(1, itemOrder.categoryCd, itemOrder.categoryCd, 0,
				itemOrder.leaseType);
		var inputValue = itemOrder.itemQty;

		listItemDraw.push(new ItemDraw(itemOrder.categoryCd,
				itemOrder.categoryCd, selectId, selectValue, inputId,
				inputValue));
	});

	$.each(listItemDraw, function(i, itemDraw) {
		$("#" + itemDraw.selectId).val(itemDraw.selectValue);
		$("#" + itemDraw.inputId).val(itemDraw.inputValue);
		var object = {};
		object.id = itemDraw.inputId;
		object.value = itemDraw.inputValue;
		sumQuantity(object);
	});
	for (var i = 0; i < largeCategory.length; i++) {
		setImage(i);
	}

}
/**
 * draw addon
 * 
 * @returns
 */
function drawAddonItem() {
	$.each(orderInfo.listOrderCfg, function(i, itemOrder) {
		if (itemOrder.addonCd !== "ANTIVIRUS"
				&& itemOrder.addonCd !== "MONITORING") {
			var orderNo = getOrderNoAddonItem(itemOrder.categoryCd,
					itemOrder.addonCd);
			var selectId = setId(0, itemOrder.categoryCd, itemOrder.addonCd,
					orderNo, itemOrder.leaseType);
			var selectValue = itemOrder.itemCd;
			var inputId = setId(1, itemOrder.categoryCd, itemOrder.addonCd,
					orderNo, itemOrder.leaseType);
			var inputValue = itemOrder.itemQty;

			listAddonItemDraw.push(new ItemDraw(itemOrder.categoryCd,
					itemOrder.addonCd, selectId, selectValue, inputId,
					inputValue));

		}

	});

	$.each(listAddonItemDraw, function(i, itemDraw) {
		$("#" + itemDraw.selectId).val(itemDraw.selectValue);
		$("#" + itemDraw.inputId).val(itemDraw.inputValue);
		var object = {};
		object.id = itemDraw.inputId;
		object.value = itemDraw.inputValue;

		sumQuantity(object);
		var previous = "";
		changeSelect(itemDraw.selectId, itemDraw.selectValue, previous);
	});

	setWarningFg();
}

/**
 * 
 * @param categoryLarge
 * @param addonCd
 * @returns
 */
function getOrderNoAddonItem(categoryLarge, addonCd) {
	var orderNo = 0;
	$.each(listAddonItemDraw, function(i, itemDraw) {
		if (itemDraw.categoryLarge === categoryLarge
				&& itemDraw.categoryCd === addonCd) {
			orderNo++;
		}
	})
	return orderNo;
}
/**
 * set warning flag for add-on memory in order
 * 
 * @returns
 */
function setWarningFg() {
	$.each(orderInfo.listOrderCfg, function(i, item) {
		if (item.addonCd === "06MEMORY" && item.warningFg === "1") {
			//setId(inputType, idLargeCate, categoryCd, orderNo, leaseType).disabled = true;
			$.each(listItemOrder, function(i, itemOrder) {
				if (itemOrder.categoryLarge === item.categoryCd
						&& itemOrder.itemCd === item.itemCd) {
					itemOrder.warningFg = "1";
					
					return false;

				}
			});

		}

	});
}

function changeWarningFlg(item) {
	if (item === null) {
		confirmSave();
	}
	confirmAddMemory = null;
	confirmSave();
}

function validateSumQuantity(){
	var showDialog = false;
	var additionalMemoryMemberNames = "";
	
	// LongND 23_Apr_2018 change warning
	/*for (var m = listItemOrder.length - 1; m >= 0; m--) {
		item = listItemOrder[m];
		
		if (item.addonCd === "MEMORY" && item.qty > 0) {
			if (confirmAddMemory === null && flagEdit === false) {
				
				$.each(listItemOrder, function(i, itemOrder) {
					if (item.categoryLarge === itemOrder.categoryCd
							&& itemOrder.categoryCd === itemOrder.categoryLarge
							&& itemOrder.warningFg === "1") {
						
						showDialog = true;
						additionalMemoryMembers[nAdditionalMemory] = m;
						nAdditionalMemory++;
						additionalMemoryMemberNames += itemOrder.itemName + "<br>";
					} 
				});
				
			} 
		}
	}*/
	
	$.each(listItemOrder, function(i, itemOrder) {
		if (itemOrder.categoryCd === itemOrder.categoryLarge
				&& itemOrder.warningFg === "1") {
			var existAddonMemmory = false;
			$.each(listItemOrder, function(i, addon) {
				if (addon.addonCd === "06MEMORY" && addon.qty > 0 && addon.categoryLarge === itemOrder.categoryCd) {
					existAddonMemmory = true;
				}
			});
			if (existAddonMemmory === false) {
				showDialog = true;
				additionalMemoryMemberNames += itemOrder.itemName + "<br>";
			}
		}
	});
	
	if (showDialog) {
		ConfirmDialogForMemory(additionalMemoryMemberNames + "<br>" + getMessageWithParams("PCOD-0013"), "");
		
		/*var contentConfirm = additionalMemoryMemberNames + getMessageWithParams("PCOD-0013");
		document.getElementById("contentWarning").innerHTML = contentConfirm;
		$('#buttonYesWar').attr('onclick', "changeWarningFlg('yes')");
		$("#modalWarning").modal("show");*/
	} else {
		changeWarningFlg(null);
	}
}
/**
 * Check validate form
 * 
 * @returns
 */
function validate() {
	removeError();
	var flag = true;
	
	var companies = $("#companies").val();
	if (companies == undefined || companies == "") {
		flag = false;
		$("#companies").addClass("border-error");
		$("#companyError").append(generateHtmlError("PCOD-0014", ""));
	}
	var departments = $("#departments").val();
	if (departments == undefined || departments == "") {
		flag = false;
		$("#departments").addClass("border-error");
		$("#departmentError").append(generateHtmlError("PCOD-0015", ""));
	}
	if (!flag) {
		showError();
	}
	return flag;
}

/**
 * remove all error
 */
function removeError() {
	$("#error-sac0011").hide();
	$("#companies").removeClass("border-error");
	$("#departments").removeClass("border-error");
	$("#companyError").empty();
	$("#departmentError").empty();
}

/**
 * show error when validate
 * 
 * @returns
 */
function showError() {
	// Show tr error
	$("#error-sac0011").show();
	// Get td company code, entry date
	var tdField = $("#itemData tbody tr:first-child td");
	// Get td error
	var tdError = $("#itemData tbody tr:last-child td");
	// Set width company code error
	tdError.eq(1).outerWidth(
			tdField.eq(1).outerWidth() + tdField.eq(2).outerWidth());
	// Set width entry date code error
	tdError.eq(3).outerWidth(
			tdField.eq(4).outerWidth() + tdField.eq(5).outerWidth());
}

/**
 * Add maxlength on textArea using jQuery
 */
function checkMaxLength(textareaID, maxLength) {

	var currentLengthInTextarea = $("#" + textareaID).val().length;
	if (currentLengthInTextarea > (maxLength)) {
		// Trim the field current length over the maxlength.
		$("#remark").val($("#remark").val().slice(0, maxLength));

	}
}

/**
 * Add maxlength on textArea using jQuery
 */
function showSuccessDialog(idEvent) {
	$("#modalInfo_image").attr("src", "../style/imgs/succsessIcon.png");
	var message = getMessageWithParams(idEvent, "");
	var title = "<span class='glyphicon glyphicon-ok-circle'></span>  Success";
	document.getElementById("titleModal").innerHTML = title;
	document.getElementById("contentMessage").innerHTML = message;
	$("#modalInfo").modal("show");
}

/**
 * convert number to string
 * format number ##,###.##
 * 
 * @param value
 * @returns
 */
function convertNumber2Decimal(value){
	if (value == null || value == "null" || value == undefined){
		return "0.00";
	} else {
		if (typeof value === "number"){
			return value.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
		} else {
			return "0.00";
		}
	}
}

/**
 * find department name
 * 
 * @param departmentCd
 * @returns
 */
function findDepartmentName(departmentCd) {
	var result = "";
	var companyCd = $("#companies").find("option:selected").val().trim();
	if (!departmentCd || !companyCd){
		return "";
	}
	$.each(listCompanies, function(i, company) {
		if (company.companyCd === companyCd) {
			$.each(company.listDepartment, function(i, item) {
				if (item.deptCd === departmentCd){
					result = item.deptName;
					return false;
				}
			});
			return false;
		}
	});
	return result;
}