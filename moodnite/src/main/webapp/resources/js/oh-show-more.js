jQuery( document ).ready(function( $ ) {
 $('#ohList').imagesLoaded( function(){
	size_li = $("#ohList li").length;
	width = $('#ohList').width();
	
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
		
		jQuery( document ).ready(function( $ ) {
		 $('.masonry').imagesLoaded( function(){
		    $('.masonry').masonry({
		        itemSelector: '.brick',
		        columnWidth: '.brick',
		        isAnimated: true,
		        isFitWidth: true
		    });
		 });

		 $(window).resize(function() {
		    $('.masonry').masonry({
		        itemSelector: '.brick',
		        isAnimated: true
		    }, 'reload');
		 });
		});
	});
	
    $('#showLess').click(function () {
    	shown = (shown - row < 0) ? shown : shown - row;
        $('#ohList li').not(':lt('+ shown +')').hide();
    }); 
    
 });
});