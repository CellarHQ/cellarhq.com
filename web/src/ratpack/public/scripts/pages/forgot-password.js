var ForgotPassword = function () {
  return {
    init: function () {
      $('#forgot-password-form').validate({
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
          'email': {
            required: true,
            email: true
          }
        },
        messages: {
          'email': 'Please enter your account\'s email'
        }
      });
    }
  };
}();
