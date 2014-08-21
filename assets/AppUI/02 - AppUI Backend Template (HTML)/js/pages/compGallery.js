/*
 *  Document   : compGallery.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Image Gallery page
 */

var CompGallery = function() {

    return {
        init: function() {
            var galleryFilter = $('.gallery-filter');
            var gallery       = $('.gallery');
            var showCategory;

            // When a gallery filter link is clicked
            galleryFilter.find('a').on('click', function() {
                // Get its data-category value
                showCategory = $(this).data('category');

                // Procceed only if the user clicked on an inactive category
                if ( ! $(this).hasClass('active')) {
                    // Remove active class from all filter links
                    galleryFilter.find('a').removeClass('active');

                    // Add the active class to the clicked link
                    $(this).addClass('active');

                    // If the value is 'all' hide the current visible items and show them all together, else hide them all and show only from the category we need
                    if (showCategory === 'all') {
                        gallery
                            .find('.gallery-image-container')
                            .parent()
                            .hide(0, function(){
                                $(this).show(0);
                            });
                    } else {
                        gallery
                            .find('.gallery-image-container')
                            .parent()
                            .hide(0, function(){
                                gallery
                                    .find('[data-category="' + showCategory + '"]')
                                    .parent('div')
                                    .show(0);
                            });
                    }
                }
            });
        }
    };
}();