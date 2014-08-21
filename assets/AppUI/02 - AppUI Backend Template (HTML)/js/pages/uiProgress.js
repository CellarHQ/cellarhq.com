/*
 *  Document   : uiProgress.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Progress & Loading page
 */

var UiProgress = function() {

    // Get random number function from a given range
    var getRandomInt = function(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };

    return {
        init: function() {
            /* Randomize progress bars width */
            var random = 0;

            $('.toggle-bars').click(function() {
                $('.progress-bar', '.bars-container').each(function() {
                    var $this = $(this);

                    random = getRandomInt(10, 100) + '%';

                    $this.css('width', random);

                    if ( ! $this.parent().hasClass('progress-mini')) {
                        $this.html(random);
                    }
                });

                $('.progress-bar', '.bars-stacked-container').each(function() {
                    random = getRandomInt(10, 25) + '%';
                    $(this).css('width', random).html(random);
                });
            });

            /* Grawl Notifications with Bootstrap-growl plugin, check out more examples at http://ifightcrime.github.io/bootstrap-growl/ */
            $('.btn-growl').on('click', function(){
                var growlType = $(this).data('growl');

                $.bootstrapGrowl('<h4><strong>Notification</strong></h4> <p>Content..</p>', {
                    type: growlType,
                    delay: 3000,
                    allow_dismiss: true,
                    offset: {from: 'top', amount: 20}
                });

                $(this).prop('disabled', true);
            });
        }
    };
}();