/*
 *  Document   : compCharts.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Charts page
 */

var CompCharts = function() {

    // Get random number function from a given range
    var getRandomInt = function(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };

    return {
        init: function() {
            /* Mini Line Charts with jquery.sparkline plugin, for more examples you can check out http://omnipotent.net/jquery.sparkline/#s-about */
            var miniChartLineOptions = {
                type: 'line',
                width: '120px',
                height: '65px',
                tooltipOffsetX: -25,
                tooltipOffsetY: 20,
                lineColor: '#de815c',
                fillColor: '#de815c',
                spotColor: '#555555',
                minSpotColor: '#555555',
                maxSpotColor: '#555555',
                highlightSpotColor: '#555555',
                highlightLineColor: '#555555',
                spotRadius: 3,
                tooltipPrefix: '',
                tooltipSuffix: ' Tickets',
                tooltipFormat: '{{prefix}}{{y}}{{suffix}}'
            };
            $('#mini-chart-line1').sparkline('html', miniChartLineOptions);

            miniChartLineOptions['lineColor'] = '#5ccdde';
            miniChartLineOptions['fillColor'] = '#5ccdde';
            miniChartLineOptions['tooltipPrefix'] = '$ ';
            miniChartLineOptions['tooltipSuffix'] = '';
            $('#mini-chart-line2').sparkline('html', miniChartLineOptions);

            /* Mini Bar Charts with jquery.sparkline plugin, for more examples you can check out http://omnipotent.net/jquery.sparkline/#s-about */
            var miniChartBarOptions = {
                type: 'bar',
                barWidth: 7,
                barSpacing: 6,
                height: '65px',
                tooltipOffsetX: -25,
                tooltipOffsetY: 20,
                barColor: '#de815c',
                tooltipPrefix: '',
                tooltipSuffix: ' Tickets',
                tooltipFormat: '{{prefix}}{{value}}{{suffix}}'
            };
            $('#mini-chart-bar1').sparkline('html', miniChartBarOptions);

            miniChartBarOptions['barColor'] = '#5ccdde';
            miniChartBarOptions['tooltipPrefix'] = '$ ';
            miniChartBarOptions['tooltipSuffix'] = '';
            $('#mini-chart-bar2').sparkline('html', miniChartBarOptions);

            // Randomize easy pie charts values
            var random;

            $('.toggle-pies').click(function() {
                $('.pie-chart').each(function() {
                    random = getRandomInt(1, 100);
                    $(this).data('easyPieChart').update(random);
                });
            });

            /*
             * Flot Charts Jquery plugin is used for charts
             *
             * For more examples or getting extra plugins you can check http://www.flotcharts.org/
             * Plugins included in this template: pie, resize, stack, time
             */

            // Get the elements where we will attach the charts
            var chartClassic    = $('#chart-classic');
            var chartStacked    = $('#chart-stacked');
            var chartPie        = $('#chart-pie');
            var chartBars       = $('#chart-bars');

            // Data for the charts
            var dataEarnings    = [[1, 1900], [2, 2300], [3, 3200], [4, 2500], [5, 4200], [6, 3100], [7, 3600], [8, 2500], [9, 4600], [10, 3700], [11, 4200], [12, 5200]];
            var dataSales       = [[1, 850], [2, 750], [3, 1500], [4, 900], [5, 1500], [6, 1150], [7, 1500], [8, 900], [9, 1800], [10, 1700], [11, 1900], [12, 2550]];
            var dataTickets     = [[1, 130], [2, 330], [3, 220], [4, 350], [5, 150], [6, 275], [7, 280], [8, 380], [9, 120], [10, 330], [11, 190], [12, 410]];

            var dataSalesBefore = [[1, 200], [4, 350], [7, 700], [10, 950], [13, 800], [16, 1050], [19, 1200], [22, 750], [25, 980], [28, 1300], [31, 1350], [34, 1200]];
            var dataSalesAfter  = [[2, 450], [5, 700], [8, 980], [11, 1200], [14, 1350], [17, 1200], [20, 1530], [23, 1750], [26, 1300], [29, 1620], [32, 1750], [35, 1750]];

            var dataMonths      = [[1, 'Jan'], [2, 'Feb'], [3, 'Mar'], [4, 'Apr'], [5, 'May'], [6, 'Jun'], [7, 'Jul'], [8, 'Aug'], [9, 'Sep'], [10, 'Oct'], [11, 'Nov'], [12, 'Dec']];
            var dataMonthsBars  = [[2, 'Jan'], [5, 'Feb'], [8, 'Mar'], [11, 'Apr'], [14, 'May'], [17, 'Jun'], [20, 'Jul'], [23, 'Aug'], [26, 'Sep'], [29, 'Oct'], [32, 'Nov'], [35, 'Dec']];

            // Classic Chart
            $.plot(chartClassic,
                [
                    {
                        label: 'Earnings',
                        data: dataEarnings,
                        lines: {show: true, fill: true, fillColor: {colors: [{opacity: .6}, {opacity: .6}]}},
                        points: {show: true, radius: 5}
                    },
                    {
                        label: 'Sales',
                        data: dataSales,
                        lines: {show: true, fill: true, fillColor: {colors: [{opacity: .2}, {opacity: .2}]}},
                        points: {show: true, radius: 5}
                    },
                    {
                        label: 'Tickets',
                        data: dataTickets,
                        lines: {show: true, fill: true, fillColor: {colors: [{opacity: .2}, {opacity: .2}]}},
                        points: {show: true, radius: 5}
                    }
                ],
                {
                    colors: ['#5ccdde', '#454e59', '#ffffff'],
                    legend: {show: true, position: 'nw', backgroundOpacity: 0},
                    grid: {borderWidth: 0, hoverable: true, clickable: true},
                    yaxis: {tickColor: '#f5f5f5', ticks: 3},
                    xaxis: {ticks: dataMonths, tickColor: '#f5f5f5'}
                }
            );

            // Creating and attaching a tooltip to the classic chart
            var previousPoint = null, ttlabel = null;
            chartClassic.bind('plothover', function(event, pos, item) {

                if (item) {
                    if (previousPoint !== item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $('#chart-tooltip').remove();
                        var x = item.datapoint[0], y = item.datapoint[1];

                        if (item.seriesIndex === 0) {
                            ttlabel = '$ <strong>' + y + '</strong>';
                        } else if (item.seriesIndex === 1) {
                            ttlabel = '<strong>' + y + '</strong> sales';
                        } else {
                            ttlabel = '<strong>' + y + '</strong> tickets';
                        }

                        $('<div id="chart-tooltip" class="chart-tooltip">' + ttlabel + '</div>')
                            .css({top: item.pageY - 45, left: item.pageX + 5}).appendTo("body").show();
                    }
                }
                else {
                    $('#chart-tooltip').remove();
                    previousPoint = null;
                }
            });

            // Stacked Chart
            $.plot(chartStacked,
                [{label: 'Tickets', data: dataTickets}, {label: 'Sales', data: dataSales}, {label: 'Earnings', data: dataEarnings}],
                {
                    colors: ['#aaaaaa', '#454e59', '#5ccdde'],
                    series: {stack: true, lines: {show: true, fill: true}},
                    lines: {show: true, lineWidth: 0, fill: true, fillColor: {colors: [{opacity: .6}, {opacity: .6}]}},
                    legend: {show: true, position: 'nw', sorted: true, backgroundOpacity: 0},
                    grid: {borderWidth: 0},
                    yaxis: {tickColor: '#f5f5f5', ticks: 3},
                    xaxis: {ticks: dataMonths, tickColor: '#f5f5f5'}
                }
            );

            // Pie Chart
            $.plot(chartPie,
                [
                    {label: 'Sales', data: 30},
                    {label: 'Tickets', data: 10},
                    {label: 'Earnings', data: 60}
                ],
                {
                    colors: ['#454e59', '#5cafde', '#5ccdde'],
                    legend: {show: false},
                    series: {
                        pie: {
                            show: true,
                            radius: 1,
                            label: {
                                show: true,
                                radius: 2/3,
                                formatter: function(label, pieSeries) {
                                    return '<div class="chart-pie-label">' + label + '<br>' + Math.round(pieSeries.percent) + '%</div>';
                                },
                                background: {opacity: .75, color: '#000000'}
                            }
                        }
                    }
                }
            );

            // Bars Chart
            $.plot(chartBars,
                [
                    {
                        label: 'Sales Before',
                        data: dataSalesBefore,
                        bars: {show: true, lineWidth: 0, fillColor: {colors: [{opacity: .6}, {opacity: .6}]}}
                    },
                    {
                        label: 'Sales After',
                        data: dataSalesAfter,
                        bars: {show: true, lineWidth: 0, fillColor: {colors: [{opacity: .6}, {opacity: .6}]}}
                    }
                ],
                {
                    colors: ['#5ccdde', '#454e59'],
                    legend: {show: true, position: 'nw', backgroundOpacity: 0},
                    grid: {borderWidth: 0},
                    yaxis: {ticks: 3, tickColor: '#f5f5f5'},
                    xaxis: {ticks: dataMonthsBars, tickColor: '#f5f5f5'}
                }
            );
        }
    };
}();