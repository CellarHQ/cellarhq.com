/*
 *  Document   : compAnimations.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Animations page
 */

var CompAnimations = function() {

    return {
        init: function() {
            var animButtons     = $('.animation-buttons .btn');
            var animClass       = '';

            /* Add/Remove Animation for element */
            animButtons.click(function() {
                animButtons.removeClass('active');
                $(this).addClass('active');
                animClass = $(this).data('animation');

                $('#animation-element').removeClass().addClass(animClass);
                $('#animation-class').text(animClass);
            });
        }
    };
}();