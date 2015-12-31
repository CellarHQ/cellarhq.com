var YourCellar = function () {
  return {
    init: function () {
      $('label.switch').click(this.checkboxSwitchControl);
      $('#tradeable').change(this.addBeerTradeableChangeControl);
      $('a.drink-cellared-beer').click(this.drinkCellaredBeer);
      $('a.delete-cellared-beer').click(this.confirmDeleteCellaredBeer);
      $('a.confirm-delete-cellared-drink').click(this.deleteCellaredBeer);

      $.validator.addMethod('multidate', function (value, element) {
        return this.optional(element) || moment(value, ['YYYY', 'YYYY-MM', 'YYYY-MM-DD']).isValid();
      });

      var breweryLearner = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 20,
        remote: '/api/organizations/live-search?name=%QUERY'
      });
      breweryLearner.initialize();

      var beerLearner = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 20,
        remote: {
          url: '/api/drinks/live-search',
          replace: function (url, query) {
            return ['/api/drinks/live-search', '?name=', query, '&organizationId=', $('#breweryId').val()].join('');
          }
        }
      });
      beerLearner.initialize();

      $('#brewery').typeahead(null, {
        displayKey: 'name',
        source: breweryLearner.ttAdapter()
      }).bind('typeahead:selected', function (obj, datum, name) {
        $('#breweryId').val(datum['id']);
        beerLearner.clearRemoteCache();
      }).blur(function () {
        if (!$('#breweryId').val()) {
          $(this).val('');
        }
      });

      $('#beer').typeahead(null, {
        displayKey: 'name',
        source: beerLearner.ttAdapter()
      }).bind('typeahead:selected', function (obj, datum, name) {
        $('#beerId').val(datum['id']);
      }).blur(function () {
        if (!$('#beerId').val()) {
          $(this).val('');
        }
      });

      $('#add-beer-form').validate({
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
            multidate: true
          },
          'dateAcquired': {
            multidate: true
          },
          'drinkByDate': {
            multidate: true
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

    checkboxSwitchControl: function () {
      var checkbox = $(this).closest('input[type=checkbox]');
      if (checkbox.is(':checked')) {
        checkbox.prop('checked', false);
      } else {
        checkbox.prop('checked', true);
      }
    },

    addBeerTradeableChangeControl: function () {
      var numTradeable = $('#numTradeable');
      if ($(this).is(':checked')) {
        numTradeable.prop('disabled', false);
      } else {
        numTradeable.val('');
        numTradeable.prop('disabled', true);
      }
    },

    drinkCellaredBeer: function () {
      var
        id = $(this).data('cellareddrinkid'),
        cellar = $(this).data('cellar'),
        beerName = $(this).data('beername');

      $.ajax('/api/cellars/' + cellar + '/drinks/' + id + '/drink', {
        type: 'PUT',
        cache: false,
        dataType: 'json'
      })
        .done(function () {
          window.location = '/yourcellar?success=Hope that ' + beerName + ' was tasty. If that was your last beer, we removed it from the list.'
        })
        .fail(function () {
          window.location = '/yourcellar?error=An error occurred while drinking ' + beerName + '. Try again!'
        });
    },

    confirmDeleteCellaredBeer: function () {
      var
        id = $(this).data('cellareddrinkid'),
        cellar = $(this).data('cellar'),
        beerName = $(this).data('beername'),
        deleteButton = $('a.confirm-delete-cellared-drink'),
        beerNameSpan = $('#delete-beer-name')[0];

      deleteButton.data('cellar', cellar);
      deleteButton.data('cellareddrinkid', id);
      deleteButton.data('beername', beerName);

      beerNameSpan.innerHTML = beerName;

      $('#confirmDeleteCellaredBeerModal').modal();
    },

    deleteCellaredBeer: function () {
      var
        id = $(this).data('cellareddrinkid'),
        cellar = $(this).data('cellar'),
        beerName = $(this).data('beername');

      if (id && cellar) {
        $.ajax('/api/cellars/' + cellar + '/drinks/' + id, {
          type: 'DELETE',
          cache: false,
          dataType: 'json'
        })
          .done(function () {
            //TODO: flash the row, hide it and update the beer counts.
            window.location = '/yourcellar/?success=' + beerName + ' was deleted.'
          })
          .fail(function (data) {
            var response = $.parseJSON(data.responseText);
            var message = response.message;
            if (message == null) {
              message = 'An error occurred while deleting ' + beerName + '. Try again!';
            }
            window.location = ['/yourcellar/?error=' + message].join();
          })
      } else {
        console.log('ERR: Cannot delete cellared beer: Data was not populated');
      }
    }
  }
}();
