$(document).ready(function () {
	size_li = $("#ohList li").length;
	width = $('.people').width();
	
	if(width == 410) {
		shown = 4;
		row = 2;
	}
	else if(width == 615) {
		shown = 3;
		row = 3;
	}
	else if(width == 820) {
		shown = 4;
		row = 4;
	}
	else if(width == 1025) {
		shown = 5;
		row = 5;
	}
	else if(width == 1230) {
		shown = 6;
		row = 6;
	}
	else {
		shown = 7;
		row = 7;
	}
	
	$('#ohList li:lt('+ shown +')').show();
	
	$('#loadMore').click(function () {
		shown = (shown + row <= size_li) ? shown + row : size_li;
		$('#ohList li:lt('+ shown +')').show();
		
		if (shown >= size_li) {
			$('#loadMore').hide();
		}
	});
	
    $('#showLess').click(function () {
    	shown = (shown - row < 0) ? shown : shown - row;
        $('#ohList li').not(':lt('+ shown +')').hide();
    });
});