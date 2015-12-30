var ForgotPassword = function () {
  return {
    init: function () {
      $('#change-password-form').validate({
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
          'password': {
            required: true,
            minlength: 5
          },
          'passwordConfirm': {
            required: true,
            equalTo: '#register-password'
          }
        },
        messages: {
          'password': {
            required: 'Please provide a password',
            minlength: 'Your password must be at least 5 characters long'
          },
          'passwordConfirm': {
            required: 'Please provide a password',
            minlength: 'Your password must be at least 5 characters long',
            equalTo: 'Please enter the same password as above'
          }
        }
      });
    }
  };
}();
