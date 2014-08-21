<?php include 'inc/config.php'; $template['header_link'] = 'UI ELEMENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Progress - Loading Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Progress &amp; Loading</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Elements</li>
                        <li><a href="">Progress &amp; Loading</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Progress - Loading Header -->

    <!-- Growl Notifications Block -->
    <div class="block full">
        <!-- Growl Notifications Title -->
        <div class="block-title">
            <h2>Growl Notifications</h2>
        </div>
        <!-- END Growl Notifications Title -->

        <!-- Growl Notifications Content -->
        <!-- Notifications functionality is initialized in js/pages/uiProgress.js -->
        <button type="button" class="btn btn-effect-ripple btn-success btn-growl" data-growl="success"><i class="fa fa-fw fa-check-circle"></i> Success</button>
        <button type="button" class="btn btn-effect-ripple btn-info btn-growl" data-growl="info"><i class="fa fa-fw fa-info-circle"></i> Info</button>
        <button type="button" class="btn btn-effect-ripple btn-warning btn-growl" data-growl="warning"><i class="fa fa-fw fa-exclamation-circle"></i> Warning</button>
        <button type="button" class="btn btn-effect-ripple btn-danger btn-growl" data-growl="danger"><i class="fa fa-fw fa-times-circle"></i> Danger</button>
        <!-- END Growl Notifications Content -->
    </div>
    <!-- END Growl Notifications Block -->

    <!-- FontAwesome Block -->
    <div class="block">
        <!-- FontAwesome Title -->
        <div class="block-title">
            <h2>Font Awesome Loaders</h2>
        </div>
        <!-- END FontAwesome Title -->

        <!-- FontAwesome Content -->
        <div class="row text-center">
            <div class="col-md-4">
                <div class="block-section">
                    <i class="fa fa-asterisk fa-2x fa-spin text-success"></i>
                    <i class="fa fa-asterisk fa-2x fa-spin text-primary"></i>
                    <i class="fa fa-asterisk fa-2x fa-spin text-muted"></i>
                    <i class="fa fa-asterisk fa-2x fa-spin text-danger"></i>
                    <i class="fa fa-asterisk fa-2x fa-spin text-warning"></i>
                    <i class="fa fa-asterisk fa-2x fa-spin text-info"></i>
                </div>
            </div>
            <div class="col-md-4">
                <div class="block-section">
                    <i class="fa fa-certificate fa-2x fa-spin text-success"></i>
                    <i class="fa fa-certificate fa-2x fa-spin text-primary"></i>
                    <i class="fa fa-certificate fa-2x fa-spin text-muted"></i>
                    <i class="fa fa-certificate fa-2x fa-spin text-danger"></i>
                    <i class="fa fa-certificate fa-2x fa-spin text-warning"></i>
                    <i class="fa fa-certificate fa-2x fa-spin text-info"></i>
                </div>
            </div>
            <div class="col-md-4">
                <div class="block-section">
                    <i class="fa fa-spinner fa-2x fa-spin text-success"></i>
                    <i class="fa fa-spinner fa-2x fa-spin text-primary"></i>
                    <i class="fa fa-spinner fa-2x fa-spin text-muted"></i>
                    <i class="fa fa-spinner fa-2x fa-spin text-danger"></i>
                    <i class="fa fa-spinner fa-2x fa-spin text-warning"></i>
                    <i class="fa fa-spinner fa-2x fa-spin text-info"></i>
                </div>
            </div>
        </div>
        <!-- END FontAwesome Content -->
    </div>
    <!-- END FontAwesome Block -->

    <!-- Progress Bars Block -->
    <div class="block">
        <!-- Progress Bars Title -->
        <div class="block-title">
            <div class="block-options pull-right">
                <!-- Randomize functionality is initialized in js/pages/uiProgress.js -->
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bars" data-toggle="tooltip" title="Randomize"><i class="fa fa-refresh"></i> </a>
            </div>
            <h2>Progress Bars</h2>
        </div>
        <!-- END Progress Bars Title -->

        <!-- Progress Bars Content -->
        <div class="row">
            <div class="col-md-6 bars-container">
                <h4 class="sub-header">Normal</h4>
                <div class="progress">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%">30%</div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%">50%</div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">70%</div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%">90%</div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">100%</div>
                </div>
            </div>
            <div class="col-md-6 bars-container">
                <h4 class="sub-header">Mini</h4>
                <div class="progress progress-mini">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%"></div>
                </div>
                <div class="progress progress-mini">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%"></div>
                </div>
                <div class="progress progress-mini">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%"></div>
                </div>
                <div class="progress progress-mini">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%"></div>
                </div>
                <div class="progress progress-mini">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 bars-container">
                <h4 class="sub-header">Striped</h4>
                <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%">30%</div>
                </div>
                <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%">50%</div>
                </div>
                <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">70%</div>
                </div>
                <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%">90%</div>
                </div>
                <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">100%</div>
                </div>
            </div>
            <div class="col-md-6 bars-container">
                <h4 class="sub-header">Striped Animated</h4>
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%">30%</div>
                </div>
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%">50%</div>
                </div>
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">70%</div>
                </div>
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%">90%</div>
                </div>
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">100%</div>
                </div>
            </div>
        </div>
        <div class="bars-stacked-container">
            <h4 class="sub-header">Stacked</h4>
            <div class="progress">
                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
            </div>
            <div class="progress progress-striped">
                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
            </div>
            <div class="progress progress-striped active">
                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
                <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">20%</div>
            </div>
        </div>
        <!-- END Progress Bars Content -->
    </div>
    <!-- END Progress Bars Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/uiProgress.js"></script>
<script>$(function(){ UiProgress.init(); });</script>

<?php include 'inc/template_end.php'; ?>