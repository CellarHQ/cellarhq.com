var CellarShow = function() {
  return {
    init: function() {
      $('a.view-cellar-details').click(this.swapDetailsChevron);
    },

    swapDetailsChevron: function() {
      var chevron = $(this).find('i');
      if (chevron.hasClass('fa-chevron-down')) {
        chevron.removeClass('fa-chevron-down');
        chevron.addClass('fa-chevron-up');
      } else {
        chevron.removeClass('fa-chevron-up');
        chevron.addClass('fa-chevron-down');
      }
    }
  }
}();
