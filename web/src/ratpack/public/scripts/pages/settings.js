var CellarSettings = function () {
  return {
    init: function () {
      $('#settings-form').validate({
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
          'displayName': {
            required: true,
            minlength: 1,
            maxlength: 64
          },
          'location': {
            maxlength: 100
          },
          'website': {
            url: true,
            maxlength: 100
          },
          'contactEmail': {
            email: true
          },
          'bio': {
            maxlength: 500
          },
          'twitter': {
            maxlength: 16
          },
          'reddit': {
            maxlength: 20
          },
          'beeradvocate': {
            url: true,
            maxlength: 255
          },
          'ratebeer': {
            url: true,
            maxlength: 255
          }
        },
        messages: {
          'displayName': {
            required: 'Please enter a name',
            minlength: 'Your name must be at least 1 character long',
            maxlength: 'Your name cannot exceed 64 characters'
          },
          'location': {
            maxlength: 'Your location cannot exceed 100 characters'
          },
          'website': {
            url: 'Your website doesn\'t appear to be a valid url',
            maxlength: 'Your website cannot exceed 100 characters'
          },
          'contactEmail': {
            email: 'Your email does not appear to be valid'
          },
          'bio': {
            maxlength: 'Your bio cannot exceed 500 characters'
          },
          'twitter': {
            maxlength: 'Twitter names cannot exceed 16 characters'
          },
          'reddit': {
            maxlength: 'Reddit username cannot exceed 20 characters'
          },
          'beeradvocate': {
            url: 'Your beer advocate url does not appear to be valid',
            maxlength: 'Your beer advocate url cannot exceed 255 characters'
          },
          'ratebeer': {
            url: 'Your rate beer url does not appear to be valid',
            maxlength: 'Your rate beer url cannot exceed 255 characters'
          }
        }
      });
    }
  }
}();
