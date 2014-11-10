var Register = function() {

    return {
        init: function() {
            $.ajaxPrefilter(function(options) {
                if (options.url == '/api/cellars/validate-name') {
                    options.url = '/api/cellars/validate-name?name=' + $('#screenName').val();
                }
            });
            $('#form-register').validate({
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
                    'screenName': {
                        required: true,
                        minlength: 1,
                        remote: {
                            url: '/api/cellars/validate-name',
                            type: 'put'
                        }
                    },
                    'email': {
                        required: true,
                        email: true
                    },
                    'emailConfirm': {
                        required: true,
                        email: true,
                        equalTo: '#email'
                    },
                    'password': {
                        required: true,
                        minlength: 5
                    },
                    'passwordConfirm': {
                        required: true,
                        equalTo: '#password'
                    },
                    'register-terms': {
                        required: true
                    }
                },
                messages: {
                    'screenName': {
                        required: 'Please enter a username',
                        remote: 'That screen name has already been taken'
                    },
                    'email': 'Please enter a valid email address',
                    'emailConfirm': 'These email addresses must match',
                    'password': {
                        required: 'Please provide a password',
                        minlength: 'Your password must be at least 5 characters long'
                    },
                    'passwordConfirm': {
                        required: 'Please provide a password',
                        minlength: 'Your password must be at least 5 characters long',
                        equalTo: 'Please enter the same password as above'
                    },
                    'register-terms': {
                        required: 'Please accept the terms!'
                    }
                }
            });
        }
    };
}();
