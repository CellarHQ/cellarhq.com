var Beer = function() {
    return {
        init: function() {
            var styleLearner = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                remote: '/api/styles/live-search?name=%QUERY'
            });
            styleLearner.initialize();

            $('#style').typeahead(null, {
                displayKey: 'name',
                source: styleLearner.ttAdapter()
            }).bind('typeahead:selected', function(obj, datum, name) {
                $('#styleId').val(datum['id']);
                styleLearner.clearRemoteCache();
            }).blur(function() {
                if (!$('#styleId').val()) {
                    $(this).val('');
                }
            });

            var glasswareLearner = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                remote: '/api/glassware/live-search?name=%QUERY'
            });
            glasswareLearner.initialize();

            $('#glassware').typeahead(null, {
                displayKey: 'name',
                source: glasswareLearner.ttAdapter()
            }).bind('typeahead:selected', function(obj, datum, name) {
                $('#glasswareId').val(datum['id']);
                glasswareLearner.clearRemoteCache();
            }).blur(function() {
                if (!$('#glasswareId').val()) {
                    $(this).val('');
                }
            });

            $('#new-beer-form').validate({
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
                    'style': {
                        remote: {
                            url: '/api/styles/validate-name',
                            type: 'put',
                            data: {
                                exists: true,
                                name: function() {
                                    return $('#style').val();
                                }
                            }
                        }
                    },
                    'glassware': {
                        remote: {
                            url: '/api/glassware/validate-name',
                            type: 'put',
                            data: {
                                exists: true,
                                name: function() {
                                    return $('glassware').val();
                                }
                            }
                        }
                    },
                    'name': {
                        required: true,
                        minlength: 3,
                        remote: {
                            url: '/api/drinks/validate-name',
                            type: 'put',
                            data: {
                                organizationId: function() {
                                    return $('#organizationId').val();
                                },
                                name: function() {
                                    return $('#name').val();
                                }
                            }
                        }
                    },
                    'srm': {
                        digits: true
                    },
                    'ibu': {
                        digits: true
                    },
                    'abv': {
                        number: true
                    }
                },
                messages: {
                    'organization': {
                        required: 'Please enter a brewery',
                        remote: 'The brewery must already exist'
                    },
                    'style': {
                        required: 'Please enter a style',
                        remote: 'You must select a style that already exists'
                    },
                    'glassware': {
                        required: 'Please enter a glassware',
                        remote: 'You must select a glassware that already exists'
                    },
                    'name': {
                        required: 'Please enter a name',
                        minlength: 'The beer\'s name must consist of at least 3 characters',
                        remote: 'This beer name already exists for the brewery'
                    },
                    'srm': {
                        digits: 'SRM must be a whole number'
                    },
                    'ibu': {
                        digits: 'IBU must be a whole number'
                    },
                    'abv': {
                        number: 'ABV must be a number'
                    }
                }
            });

            $('#edit-beer-form').validate({
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
                    if (e.closest('.form-group').find('.help-block').length === 2) {
                        e.closest('.help-block').remove();
                    } else {
                        e.closest('.form-group').removeClass('has-success has-error');
                        e.closest('.help-block').remove();
                    }
                },
                rules: {
                    'style': {
                        remote: {
                            url: '/api/styles/validate-name',
                            type: 'put',
                            data: {
                                exists: true,
                                name: function() {
                                    return $('#style').val();
                                }
                            }
                        }
                    },
                    'glassware': {
                        remote: {
                            url: '/api/glassware/validate-name',
                            type: 'put',
                            data: {
                                exists: true,
                                name: function() {
                                    return $('glassware').val();
                                }
                            }
                        }
                    },
                    'name': {
                        required: true,
                        minlength: 3,
                        remote: {
                            url: '/api/drinks/validate-name',
                            type: 'put',
                            data: {
                                organizationId: function() {
                                    return $('#organizationId').val();
                                },
                                name: function() {
                                    return $('#name').val();
                                }
                            }
                        }
                    },
                    'srm': {
                        digits: true
                    },
                    'ibu': {
                        digits: true
                    },
                    'abv': {
                        number: true
                    }
                },
                messages: {
                    'style': {
                        required: 'Please enter a style',
                        remote: 'You must select a style that already exists'
                    },
                    'glassware': {
                        required: 'Please enter a glassware',
                        remote: 'You must select a glassware that already exists'
                    },
                    'name': {
                        required: 'Please enter a name',
                        minlength: 'The beer\'s name must consist of at least 3 characters',
                        remote: 'This beer name already exists for the brewery'
                    },
                    'srm': {
                        digits: 'SRM must be a whole number'
                    },
                    'ibu': {
                        digits: 'IBU must be a whole number'
                    },
                    'abv': {
                        number: 'ABV must be a number'
                    }
                }
            });
        }
    }
}();