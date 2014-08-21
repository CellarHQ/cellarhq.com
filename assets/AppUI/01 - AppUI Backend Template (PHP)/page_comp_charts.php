<?php include 'inc/config.php'; $template['header_link'] = 'COMPONENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Charts Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Statistics &amp; Charts</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>Components</li>
                        <li><a href="">Charts</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Charts Header -->

    <!-- Classic and Stacked Chart -->
    <div class="row">
        <div class="col-sm-6">
            <!-- Classic Chart Block -->
            <div class="block full">
                <!-- Classic Chart Title -->
                <div class="block-title">
                    <h2>Classic</h2>
                </div>
                <!-- END Classic Chart Title -->

                <!-- Classic Chart Content -->
                <!-- Flot Charts (initialized in js/pages/compCharts.js), for more examples you can check out http://www.flotcharts.org/ -->
                <div id="chart-classic" style="height: 380px;"></div>
                <!-- END Classic Chart Content -->
            </div>
            <!-- END Classic Chart Block -->
        </div>
        <div class="col-sm-6">
            <!-- Stacked Chart Block -->
            <div class="block full">
                <!-- Stacked Chart Title -->
                <div class="block-title">
                    <h2>Stacked</h2>
                </div>
                <!-- END Stacked Chart Title -->

                <!-- Stacked Chart Content -->
                <!-- Flot Charts (initialized in js/pages/compCharts.js), for more examples you can check out http://www.flotcharts.org/ -->
                <div id="chart-stacked" style="height: 380px;"></div>
                <!-- END Stacked Chart Content -->
            </div>
            <!-- END Stacked Chart Block -->
        </div>
    </div>
    <!-- END Classic and Stacked Chart -->

    <!-- Mini and Easy Pie Charts -->
    <div class="row">
        <div class="col-sm-6">
            <!-- Easy Pie Charts Block -->
            <div class="block">
                <!-- Easy Pie Charts Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <!-- Randomize functionality (initialized in js/pages/compCharts.js) -->
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-pies" data-toggle="tooltip" title="Randomize"><i class="fa fa-refresh"></i></a>
                    </div>
                    <h2>Easy Pie</h2>
                </div>
                <!-- END Easy Pie Charts Title -->

                <!-- Easy Pie Charts Content -->
                <!-- Easy Pie Charts (initialized in js/app.js -> uiInit()) -->
                <!-- Just add the class .pie-chart as well as the values you would like at data-percent and data-size (default is 80px) -->
                <!-- You can also change the bar color, the track color or line width by setting the 'data-bar-color', 'data-track-color' and 'data-line-width' attributes respectively -->
                <div class="row text-center">
                    <div class="col-sm-4">
                        <div class="pie-chart block-section" data-percent="25" data-line-width="3" data-bar-color="#5ccdde" data-track-color="#ebeef2">
                            <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="pie-avatar img-circle">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="pie-chart block-section" data-percent="50" data-line-width="3" data-bar-color="#454e59" data-track-color="#ebeef2">
                            <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="pie-avatar img-circle">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="pie-chart block-section" data-percent="75" data-line-width="3" data-bar-color="#5cadde" data-track-color="#ebeef2">
                            <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="pie-avatar img-circle">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="pie-chart block-section" data-percent="25" data-size="109" data-line-width="2" data-bar-color="#de815c" data-track-color="#ebeef2">
                            <span><i class="fa fa-database text-danger"></i></span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="pie-chart block-section" data-percent="50" data-size="109" data-line-width="2" data-bar-color="#deb25c" data-track-color="#ebeef2">
                            <span><i class="fa fa-signal text-warning"></i></span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="pie-chart block-section" data-percent="75" data-size="109" data-line-width="2" data-bar-color="#afde5c" data-track-color="#ebeef2">
                            <span><i class="fa fa-upload text-success"></i></span>
                        </div>
                    </div>
                </div>
                <!-- END Easy Pie Charts Content -->
            </div>
            <!-- END Easy Pie Charts Block -->
        </div>
        <div class="col-sm-6">
            <!-- Mini Charts Block -->
            <!-- Jquery Sparkline (initialized in js/pages/compCharts.js), for more examples you can check out http://omnipotent.net/jquery.sparkline/#s-about -->
            <div class="block">
                <!-- Mini Charts Title -->
                <div class="block-title">
                    <h2>Mini</h2>
                </div>
                <!-- END Mini Charts Title -->

                <!-- Mini Charts Content -->
                <div class="row text-center">
                    <div class="col-xs-6">
                        <div class="block-section">
                            <span id="mini-chart-line1">1,5,4,8,5,6,3,2</span>
                            <h4 class="text-center"><i class="fa fa-ticket text-muted"></i> Tickets</h4>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="block-section">
                            <span id="mini-chart-line2">980,1210,900,500,700,1500,1350,1485</span>
                            <h4 class="text-center"><i class="fa fa-thumbs-o-up text-muted"></i> Earnings</h4>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="block-section">
                            <span id="mini-chart-bar1">1,5,4,8,5,6,3,2</span>
                            <h4 class="text-center"><i class="fa fa-ticket text-muted"></i> Tickets</h4>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="block-section">
                            <span id="mini-chart-bar2">980,1210,900,500,700,1500,1350,1485</span>
                            <h4 class="text-center"><i class="fa fa-thumbs-o-up text-muted"></i> Earnings</h4>
                        </div>
                    </div>
                </div>
                <!-- END Mini Charts Content -->
            </div>
            <!-- END Mini Charts Block -->
        </div>
    </div>
    <!-- END Mini and Easy Pie Charts -->

    <!-- Pie and Bars Chart -->
    <div class="row">
        <div class="col-sm-6">
            <!-- Pie Chart Block -->
            <div class="block full">
                <!-- Pie Chart Title -->
                <div class="block-title">
                    <h2>Pie</h2>
                </div>
                <!-- END Pie Title -->

                <!-- Pie Chart Content -->
                <div id="chart-pie" style="height: 380px;"></div>
                <!-- END Pie Chart Content -->
            </div>
            <!-- END Pie Chart Block -->
        </div>
        <div class="col-sm-6">
            <!-- Bars Chart Block -->
            <div class="block full">
                <!-- Bars Chart Title -->
                <div class="block-title">
                    <h2>Bars</h2>
                </div>
                <!-- END Bars Chart Title -->

                <!-- Bars Chart Content -->
                <!-- Flot Charts (initialized in js/pages/compCharts.js), for more examples you can check out http://www.flotcharts.org/ -->
                <div id="chart-bars" style="height: 380px;"></div>
                <!-- END Bars Chart Content -->
            </div>
            <!-- END Bars Chart Block -->
        </div>
    </div>
    <!-- END Pie and Bars Chart -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/compCharts.js"></script>
<script>$(function(){ CompCharts.init(); });</script>

<?php include 'inc/template_end.php'; ?>