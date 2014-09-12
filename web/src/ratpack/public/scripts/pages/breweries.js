var FormsValidation = function () {
    return {
        init: function () {
            /*
             *  Jquery Validation, Check out more examples and documentation at https://github.com/jzaefferer/jquery-validation
             */

            /* Initialize Form Validation */
            $('#brewery_form').validate({
                errorClass: 'help-block animation-pullUp', // You can change the animation class for a different entrance animation - check animations page
                errorElement: 'div',
                errorPlacement: function (error, e) {
                    e.parents('.form-group > div').append(error);
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
                    $(e).closest('.help-block').remove();
                },
                success: function (e) {
                    // You can use the following if you would like to highlight with green color the input after successful validation!
                    e.closest('.form-group').removeClass('has-success has-error'); // e.closest('.form-group').removeClass('has-success has-error').addClass('has-success');
                    e.closest('.help-block').remove();
                },
                rules: {
                    'name': {
                        required: true,
                        minlength: 3,
                        remote: {
                            url: "/api/organizations/valid-name",
                            type: "get",
                            data: {
                                name: function() {
                                    return $( "#name" ).val();
                                }
                            }
                        }
                    },
                    'established': {
                        digits: true,
                        minlength: 4,
                        maxlength: 4
                    },
                    'phone': {
                        required: false,
                        maxlength: 20
                    },
                    'website': {
                        url: true,
                        required: false,
                        maxlength: 100
                    },
                    'address': {
                        required: false,
                        maxlength: 100
                    },
                    'address2': {
                        required: false,
                        maxlength: 100
                    },
                    'locality': {
                        required: false,
                        maxlength: 100
                    },
                    'region': {
                        required: false,
                        maxlength: 100
                    },
                    'country': {
                        required: false,
                        maxlength: 100
                    },
                    'postalcode': {
                        required: false,
                        maxlength: 20
                    }
                },
                messages: {
                    'name': {
                        required: 'Please enter a name',
                        minlength: 'The brewery\'s name must consist of at least 3 characters',
                        remote: 'This brewery name has already been used'
                    },
                    'established': 'Please enter a valid 4 digit year',
                    'phone': {
                        maxlength: 'The phone number must be less than 21 characters'
                    },
                    'website': {
                        maxlength: 'The url must be less than 100 characters'
                    },
                    'address': {
                        maxlength: 'The address must be less than 100 characters'
                    },
                    'address2': {
                        maxlength: 'The secondary address must be less than 100 characters'
                    },
                    'locality': {
                        maxlength: 'The city must be less than 100 characters'
                    },
                    'region': {
                        maxlength: 'The state/region must be less than 100 characters'
                    },
                    'country': {
                        maxlength: 'The country must be less than 100 characters'
                    },
                    'postalcode': {
                        maxlength: 'The postal code must be less than 21 characters'
                    }

                }
            });
        }
    };
}();