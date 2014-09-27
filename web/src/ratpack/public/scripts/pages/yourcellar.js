var YourCellar = function() {
  return {
    init: function() {
      $('label.switch').click(this.checkboxSwitchControl);
      $('#tradeable').change(this.addBeerTradeableChangeControl);
      $('button.drink-cellared-beer').click(this.drinkCellaredBeer);
      this.autoComplete('#brewery', '/api/organizations/live-search');
      this.autoComplete('#beer', '/api/drinks/live-search');

      $('#add-beer-form').validate({
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
          'brewery': {
            required: true
          },
          'beer': {
            required: true
          },
          'quantity': {
            required: false,
            digits: true,
            min: 0
          },
          'bottleDate': {
            required: false,
            dateISO: true
          },
          'dateAcquired': {
            required: false,
            dateISO: true
          },
          'drinkByDate': {
            required: false,
            dateISO: true
          },
          'numTradeable': {
            required: false,
            digits: true,
            min: 0
          }
        },
        messages: {
          'brewery': {
            required: 'Please enter a brewery'
          },
          'beer': {
            required: 'Please enter a valid brewery'
          },
          'quantity': {
            digits: 'Only numbers please',
            min: 'You cannot have negative quantity'
          },
          'bottleDate': 'Please enter a real date',
          'dateAcquired': 'Please enter a real date',
          'drinkByDate': 'Please enter a real date',
          'numTradeable': {
            digits: 'Only numbers please',
            min: 'You cannot trade negative quantity'
          }
        }
      });
    },

    checkboxSwitchControl: function() {
      var checkbox = $(this).closest('input[type=checkbox]');
      if (checkbox.is(':checked')) {
        checkbox.prop('checked', false);
      } else {
        checkbox.prop('checked', true);
      }
    },

    addBeerTradeableChangeControl: function() {
      var numTradeable = $('#numTradeable');
      if ($(this).is(':checked')) {
        numTradeable.prop('disabled', false);
      } else {
        numTradeable.val('');
        numTradeable.prop('disabled', true);
      }
    },

    drinkCellaredBeer: function() {
      var
        id = $(this).data('cellaredDrinkId'),
        cellar = $(this).data('cellar');

      $.ajax(['/cellars/', cellar, '/drinks/', id, '/drink'].join(), {
        type: 'PUT',
        cache: false,
        dataType: 'json'
      })
        .done(function() {
          location.reload();
        })
        .fail(function(data) {
          var message = $.parseJSON(data).message;
          if (message == null) {
            message = 'An error occurred while drinking beer. Try again!';
          }
          window.location = ['/cellars/', cellar, '?error=', message].join();
        });
    },

    autoComplete: function(selector, url) {
      $(selector).autocomplete({
        appendTo: ('#add-beer-form'),
        focus: function(event, ui) {
          $(selector).val(ui.item.label);
        },
        select: function(event, ui) {
          $(selector).val(ui.item.label);
          $(selector + 'Id').val(ui.item.value);
          return false;
        },
        source: function(request, response) {
          var data = {
            name: request.term
          };

          if (selector == '#beer') {
            data.organizationId = $('#breweryId').val();
          }

          $.ajax(url, {
            type: 'GET',
            dataType: 'json',
            data: data,
            success: function(data) {
              response($.map(data, function(v, i) {
                return {
                  label: v.name,
                  value: v.id
                };
              }));
            }
          });
        }
      });

      // TODO: If no value, live-search for the value and replace if only one exists; otherwise clear val like it is now
      $(selector).blur(function() {
        var idField = $(selector + 'Id');
        if (!idField.val()) {
          $(this).val('');
        }
      })
    }
  }
}();
