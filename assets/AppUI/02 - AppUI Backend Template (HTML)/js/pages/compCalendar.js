/*
 *  Document   : compCalendar.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Calendar page
 */

var CompCalendar = function() {
    var calendarEvents  = $('.calendar-events');

    /* Function for initializing drag and drop event functionality */
    var initEvents = function() {
        calendarEvents.find('li').each(function() {
            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            var eventObject = { title: $.trim($(this).text()), color: $(this).css('background-color') };

            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);

            // make the event draggable using jQuery UI
            $(this).draggable({ zIndex: 999, revert: true, revertDuration: 0 });
        });
    };

    return {
        init: function() {
            /* Initialize drag and drop event functionality */
            initEvents();

            /* Add new event in the events list */
            var eventInput      = $('#add-event');
            var eventInputVal   = '';

            // When the add button is clicked
            $('#add-event-btn').on('click', function(){
                // Get input value
                eventInputVal = eventInput.prop('value');

                // Check if the user entered something
                if ( eventInputVal ) {
                    // Add it to the events list
                    calendarEvents.prepend('<li class="animation-fadeInQuick2Inv"><i class="fa fa-calendar"></i> ' + $('<div />').text(eventInputVal).html() + '</li>');

                    // Clear input field
                    eventInput.prop('value', '');

                    // Init Events
                    initEvents();

                    // Focus the input at the end
                    eventInput.focus();
                }

                // Don't let the form submit
                return false;
            });

            /* Initialize FullCalendar */
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            $('#calendar').fullCalendar({
                header: {
                    left: 'title',
                    center: '',
                    right: 'today month,agendaWeek,agendaDay prev,next'
                },
                firstDay: 1,
                editable: true,
                droppable: true,
                drop: function(date, allDay) { // this function is called when something is dropped

                    // retrieve the dropped element's stored Event Object
                    var originalEventObject = $(this).data('eventObject');

                    // we need to copy it, so that multiple events don't have a reference to the same object
                    var copiedEventObject = $.extend({}, originalEventObject);

                    // assign it the date that was reported
                    copiedEventObject.start = date;

                    // render the event on the calendar
                    // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                    $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                    // remove the element from the "Draggable Events" list
                    $(this).remove();
                },
                events: [
                    {
                        title: 'Cinema',
                        start: new Date(y, m, 2),
                        allDay: true,
                        color: '#de815c'
                    },
                    {
                        title: 'Live Conference',
                        start: new Date(y, m, 5)
                    },
                    {
                        title: 'Secret Project',
                        start: new Date(y, m, 4),
                        end: new Date(y, m, 4),
                        color: '#555555'
                    },
                    {
                        id: 999,
                        title: 'Work (repeated)',
                        start: new Date(y, m, d + 3, 8, 0),
                        allDay: false,
                        color: '#555555'
                    },
                    {
                        id: 999,
                        title: 'Work (repeated)',
                        start: new Date(y, m, d + 5, 8, 0),
                        allDay: false,
                        color: '#555555'
                    },
                    {
                        title: 'Work meeting',
                        start: new Date(y, m, d, 10, 00),
                        allDay: false,
                        color: '#de815c'
                    },
                    {
                        title: 'Bootstrap Tutorial',
                        start: new Date(y, m, d + 4, 12, 15),
                        end: new Date(y, m, d + 4, 18, 15),
                        allDay: false,
                        color: '#deb25c'
                    },
                    {
                        title: 'Admin Template',
                        start: new Date(y, m, 24),
                        end: new Date(y, m, 27),
                        allDay: true,
                        color: '#afde5c'
                    },
                    {
                        title: 'Trip to Asia',
                        start: new Date(y, m, d + 8, 21, 0),
                        end: new Date(y, m, d + 8, 23, 30),
                        allDay: false
                    },
                    {
                        title: 'Follow me on Twitter',
                        start: new Date(y, m, 23),
                        end: new Date(y, m, 23),
                        allDay: true,
                        url: 'http://twitter.com/pixelcave'
                    }
                ]
            });
        }
    };
}();