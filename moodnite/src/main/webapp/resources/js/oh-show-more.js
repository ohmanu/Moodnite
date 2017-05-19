$(document).ready(function () {
	size_li = $("#ohList li").length;
	shown = 7;
	row = 7;
	
	$('#ohList li:lt('+ row +')').show();
	
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