$(document).ready(function() {
	setTabIndex();

	// Set again first focus for menu screen
	   $('.header').keydown(function(e){
	     if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 9) {
	       e.preventDefault();
	       $(".menu_link_2").first().focus();
	     }

	     if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 13) {
		       e.preventDefault();
		       $(".btn-logout").click();
		    }
	   });

	//set action when keycode = 13 (Enter)
	   $('.cl-body').keydown(function(e){
		    if($('.menu_link_2').is(":focus") && (e.which || e.keyCode) == 13) {
		       e.preventDefault();
		       $('.menu_link_2').click();
		    }
		    if($('.menu_link_3').is(":focus") && (e.which || e.keyCode) == 13) {
			       e.preventDefault();
			       $('.menu_link_3').click();
			    }
		    if($('.menu_link_6').is(":focus") && (e.which || e.keyCode) == 13) {
			       e.preventDefault();
			       $('.menu_link_6').click();
			    }
		    if($('.menu_link_7').is(":focus") && (e.which || e.keyCode) == 13) {
			       e.preventDefault();
			       $('.menu_link_7').click();
			    }
		    if($('.menu_link_10').is(":focus") && (e.which || e.keyCode) == 13) {
			       e.preventDefault();
			       $('.menu_link_10').click();
			    }
		    if($('.menu_link_11').is(":focus") && (e.which || e.keyCode) == 13) {
			       e.preventDefault();
			       $('.menu_link_11').click();
			    }
		    if($('.menu_link_12').is(":focus") && (e.which || e.keyCode) == 13) {
			       e.preventDefault();
			       $('.menu_link_12').click();
			    }
		});
})

/**
 * Set tabindex
 */
function setTabIndex() {
	$("th, input, select, a").attr('tabindex','-1');
	$(".menu_link_2").attr('tabindex','1');
	$(".menu_link_3").attr('tabindex','2');
	$(".menu_link_6").attr('tabindex','3');
	$(".menu_link_7").attr('tabindex','4');
	$(".menu_link_10").attr('tabindex','5');
	$(".menu_link_11").attr('tabindex','6');
	$(".menu_link_12").attr('tabindex','7');
	$(".btn-logout").attr('tabindex','8');

	$(".menu_link_2").first().focus();
}