/**
 * 
 */

$(document).ready(function (){
	$(".left-side").hide();
});


function getdataInDropdown(element){
	var result = [];
	$('#'+element).find('li').each(function(index){
		result.push($(this).text());
	});
	return result;
}