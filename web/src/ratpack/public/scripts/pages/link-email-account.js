var LinkEmailAccount = function() {

  return {
    init: function() {
      $('#link-email-form').validate({
        errorClass: 'help-block animation-slideUp',
        errorElement: 'div',
        errorPlacement: function(error, e) {
          e.parents('.form-group > div').append(error);
        },
        highlight: function(e) {
          $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
          $(e).closest('.help-block').remove();
        },
        success: function(e) {
          e.closest('.form-group').removeClass('has-success has-error');
          e.closest('.help-block').remove();
        },
        rules: {
          'email': {
            'required': true,
            'email': true
          }
        },
        messages: {
          'email': {
            required: 'Please enter an email',
            email: 'Your email does not appear to be valid'
          }
        }
      });
    }
  };
}();
