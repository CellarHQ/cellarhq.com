var Login = function () {
  return {
    init: function () {
      $('#login-form').validate({
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
          'username': {
            required: true,
            email: true
          },
          'password': {
            required: true,
            minlength: 6
          }
        },
        messages: {
          'username': 'Please enter your account\'s email',
          'password': {
            required: 'Please provide your password',
            minlength: 'Your password must be at least 5 characters long'
          }
        }
      });
    }
  };
}();
