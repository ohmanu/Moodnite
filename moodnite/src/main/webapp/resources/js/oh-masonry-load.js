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