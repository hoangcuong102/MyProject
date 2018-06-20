$(document).ready(function() {
	if($('#userId').val() == "") {
		$('input[name=userId]').focus();
	} else {
		$('input[name=userPassword]').focus();
	}

	$(this).keypress(function(event) {
	    if (event.which == 13) {
	        event.preventDefault();
	        $("form").submit();
	    } else {
	    	var iptUserId = $(event.target);
	    	if ($(event.target).attr("name") == "userId"){
	    		var key = event.keyCode;
	    		if (!checkAlphanumeric(String.fromCharCode(key), false)){
	    			return false;
	    		}
	    	}
	    }
	});

	 $(window).resize(function(){
	  $('.className').css({
	   position:'absolute',
	   left: ($(window).width()
	     - $('.className').outerWidth())/2,
	   top: ($(window).height()
	     - $('.className').outerHeight())/2
	  });

	 });

	 // To initially run the function:
	 $(window).resize();

	// Set again first focus depend on last focus
	$('.login-form').keydown(function(e){
		if($('#btn-submit').is(":focus") && (e.which || e.keyCode) == 9) {
			//e.preventDefault();
			//$('.select-login').focus();
		}
		if($('#btn-submit').is(":focus") && (e.which || e.keyCode) == 9 && !event.shiftKey) {
			e.preventDefault();
			$('#userId').first().focus();
		}
	});
	
	$('#listSystemGroup').select2({
		  dropdownAutoWidth : true,
		  minimumResultsForSearch: Infinity
	});
	
	setTabindex();
})

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

/**
 * set tabindex
 * 
 * @returns
 */
function setTabindex(){
	$("#userId").attr("tabindex", 1);
	$("#userPassword").attr("tabindex", 2);
	$("#btn-submit").attr("tabindex", 3);
}