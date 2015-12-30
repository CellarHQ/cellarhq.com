var ScreenNameConflict = function () {

  return {
    init: function () {
      $.ajaxPrefilter(function (options) {
        if (options.url == '/api/cellars/validate-name') {
          options.url = '/api/cellars/validate-name?name=' + $('#screenName').val();
        }
      });
      $('#screen-name-conflict-form').validate({
        errorClass: 'help-block animation-slideUp',
        errorElement: 'div',
        errorPlacement: function (error, e) {
          e.parents('.form-group > div').append(error);
        },
        highlight: function (e) {
          $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
          $(e).closest('.help-block').remove();
        },
        success: function (e) {
          e.closest('.form-group').removeClass('has-success has-error');
          e.closest('.help-block').remove();
        },
        rules: {
          'screenName': {
            required: true,
            minlength: 1,
            remote: {
              url: '/api/cellars/validate-name',
              type: 'put'
            }
          },
        },
        messages: {
          'screenName': {
            required: 'Please enter a username',
            remote: 'That screen name has already been taken'
          }
        }
      });
    }
  };
}();
