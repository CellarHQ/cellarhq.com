<?php include 'inc/config.php'; $template['header_link'] = 'UI ELEMENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Buttons - Dropdowns Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Buttons &amp; Dropdowns</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Elements</li>
                        <li><a href="">Buttons &amp; Dropdowns</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Buttons - Dropdowns Header -->

    <!-- Buttons Row -->
    <div class="row">
        <div class="col-lg-6">
            <!-- Button Styles Block -->
            <div class="block">
                <!-- Button Styles Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Info"><i class="fa fa-info-circle"></i></a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Success"><i class="fa fa-check"></i></a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-info" data-toggle="tooltip" title="Share on Facebook"><i class="fa fa-facebook"></i></a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-warning" data-toggle="tooltip" title="Warning"><i class="fa fa-exclamation-circle"></i></a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-trash-o"></i></a>
                        <div class="btn-group">
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary dropdown-toggle enable-tooltip" data-toggle="dropdown" title="Extras"><i class="fa fa-chevron-down"></i></a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="gi gi-cloud-download pull-right"></i>
                                        Download
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="gi gi-video_hd pull-right"></i>
                                        HD Videos
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="gi gi-wifi fa-fw pull-right"></i>
                                        Wifi
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <h2>Button Styles</h2>
                </div>
                <!-- END Button Styles Title -->

                <!-- Button Styles Content -->
                <div class="block-section">
                    <h4 class="sub-header">Default</h4>
                    <button type="button" class="btn btn-default">Default</button>
                    <button type="button" class="btn btn-primary">Primary</button>
                    <button type="button" class="btn btn-success">Success</button>
                    <button type="button" class="btn btn-info">Info</button>
                    <button type="button" class="btn btn-warning">Warning</button>
                    <button type="button" class="btn btn-danger">Danger</button>
                </div>
                <div class="block-section">
                    <h4 class="sub-header">Ripple Effect</h4>
                    <button type="button" class="btn btn-effect-ripple btn-default">Default</button>
                    <button type="button" class="btn btn-effect-ripple btn-primary">Primary</button>
                    <button type="button" class="btn btn-effect-ripple btn-success">Success</button>
                    <button type="button" class="btn btn-effect-ripple btn-info">Info</button>
                    <button type="button" class="btn btn-effect-ripple btn-warning">Warning</button>
                    <button type="button" class="btn btn-effect-ripple btn-danger">Danger</button>
                </div>
                <div class="block-section">
                    <h4 class="sub-header">Square</h4>
                    <button type="button" class="btn btn-square btn-default">Default</button>
                    <button type="button" class="btn btn-square btn-primary">Primary</button>
                    <button type="button" class="btn btn-square btn-success">Success</button>
                    <button type="button" class="btn btn-square btn-info">Info</button>
                    <button type="button" class="btn btn-square btn-warning">Warning</button>
                    <button type="button" class="btn btn-square btn-danger">Danger</button>
                </div>
                <div class="block-section">
                    <h4 class="sub-header">Rounded</h4>
                    <button type="button" class="btn btn-rounded btn-default">Default</button>
                    <button type="button" class="btn btn-rounded btn-primary">Primary</button>
                    <button type="button" class="btn btn-rounded btn-success">Success</button>
                    <button type="button" class="btn btn-rounded btn-info">Info</button>
                    <button type="button" class="btn btn-rounded btn-warning">Warning</button>
                    <button type="button" class="btn btn-rounded btn-danger">Danger</button>
                </div>
                <div class="block-section">
                    <h4 class="sub-header">Sizes</h4>
                    <button type="button" class="btn btn-xs btn-danger">Extra Small</button>
                    <button type="button" class="btn btn-sm btn-danger">Small</button>
                    <button type="button" class="btn btn-danger">Normal</button>
                    <button type="button" class="btn btn-lg btn-danger">Large</button>
                </div>
                <div class="block-section">
                    <h4 class="sub-header">Block</h4>
                    <button type="button" class="btn btn-block btn-primary">Block Button</button>
                </div>
                <!-- END Button Styles Content -->
            </div>
            <!-- END Button Styles Block -->

            <!-- Disabled Buttons Block -->
            <div class="block full">
                <!-- Disabled Buttons Title -->
                <div class="block-title">
                    <h2>Disabled Buttons</h2>
                </div>
                <!-- END Disabled Buttons Title -->

                <!-- Disabled Buttons Content -->
                <button type="button" class="btn btn-default disabled">Default</button>
                <button type="button" class="btn btn-primary disabled">Primary</button>
                <button type="button" class="btn btn-success disabled">Success</button>
                <button type="button" class="btn btn-info disabled">Info</button>
                <button type="button" class="btn btn-warning disabled">Warning</button>
                <button type="button" class="btn btn-danger disabled">Danger</button>
                <!-- END Disabled Buttons Content -->
            </div>
            <!-- END Disabled Buttons Block -->
        </div>
        <div class="col-lg-6">
            <!-- Icon Buttons Block -->
            <div class="block">
                <!-- Icon Buttons Title -->
                <div class="block-title">
                    <h2>Icon Buttons</h2>
                </div>
                <!-- END Icon Buttons Title -->

                <!-- Icon Buttons Content -->
                <div class="block-section">
                    <button class="btn btn-default"><i class="fa fa-cog"></i> Settings</button>
                    <button class="btn btn-success"><i class="fa fa-plus"></i> Add User</button>
                    <button class="btn btn-primary"><i class="fa fa-cloud-download"></i> Download</button>
                    <button class="btn btn-info"><i class="fa fa-info-circle"></i> More Info</button>
                    <button class="btn btn-warning"><i class="fa fa-css3"></i> CSS3</button>
                    <button class="btn btn-danger"><i class="fa fa-html5"></i> HTML5</button>
                </div>
                <div class="block-section">
                    <button class="btn btn-default">Settings <i class="fa fa-cog"></i></button>
                    <button class="btn btn-success">Add User <i class="fa fa-plus"></i></button>
                    <button class="btn btn-primary">Download <i class="fa fa-cloud-download"></i></button>
                    <button class="btn btn-info">More Info <i class="fa fa-info-circle"></i></button>
                    <button class="btn btn-warning">CSS3 <i class="fa fa-css3"></i></button>
                    <button class="btn btn-danger">HTML5 <i class="fa fa-html5"></i></button>
                </div>
                <div class="block-section">
                    <button class="btn btn-default"><i class="fa fa-cog"></i></button>
                    <button class="btn btn-success"><i class="fa fa-plus"></i></button>
                    <button class="btn btn-primary"><i class="fa fa-cloud-download"></i></button>
                    <button class="btn btn-info"><i class="fa fa-info-circle"></i></button>
                    <button class="btn btn-warning"><i class="fa fa-css3"></i></button>
                    <button class="btn btn-danger"><i class="fa fa-html5"></i></button>
                    <button class="btn btn-sm btn-default"><i class="fa fa-cog"></i></button>
                    <button class="btn btn-sm btn-success"><i class="fa fa-plus"></i></button>
                    <button class="btn btn-sm btn-primary"><i class="fa fa-cloud-download"></i></button>
                    <button class="btn btn-sm btn-info"><i class="fa fa-info-circle"></i></button>
                    <button class="btn btn-sm btn-warning"><i class="fa fa-css3"></i></button>
                    <button class="btn btn-sm btn-danger"><i class="fa fa-html5"></i></button>
                    <button class="btn btn-xs btn-default"><i class="fa fa-cog"></i></button>
                    <button class="btn btn-xs btn-success"><i class="fa fa-plus"></i></button>
                    <button class="btn btn-xs btn-primary"><i class="fa fa-cloud-download"></i></button>
                    <button class="btn btn-xs btn-info"><i class="fa fa-info-circle"></i></button>
                    <button class="btn btn-xs btn-warning"><i class="fa fa-css3"></i></button>
                    <button class="btn btn-xs btn-danger"><i class="fa fa-html5"></i></button>
                </div>
                <div class="block-section">
                    <button class="btn btn-lg btn-success"><i class="fa fa-check"></i> Large</button>
                    <button class="btn btn-success"><i class="fa fa-check"></i> Normal</button>
                    <button class="btn btn-sm btn-success"><i class="fa fa-check"></i> Small</button>
                    <button class="btn btn-xs btn-success"><i class="fa fa-check"></i>Extra Small</button>
                </div>
                <!-- END Icon Buttons Content -->
            </div>
            <!-- END Icon Buttons Block -->

            <!-- Toolbars Block -->
            <div class="block">
                <!-- Toolbars Title -->
                <div class="block-title">
                    <h2>Toolbars</h2>
                </div>
                <!-- END Toolbars Title -->

                <!-- Toolbars Content -->
                <div class="block-section">
                    <div class="btn-toolbar">
                        <div class="btn-group">
                            <button class="btn btn-default"><i class="fa fa-file"></i></button>
                            <button class="btn btn-default"><i class="fa fa-floppy-o"></i></button>
                        </div>
                        <div class="btn-group">
                            <button class="btn btn-default"><i class="fa fa-scissors"></i></button>
                            <button class="btn btn-default"><i class="fa fa-files-o"></i></button>
                            <button class="btn btn-default"><i class="fa fa-clipboard"></i></button>
                        </div>
                        <div class="btn-group" data-toggle="buttons">
                            <button class="btn btn-default"><i class="fa fa-bold"></i></button>
                            <button class="btn btn-default"><i class="fa fa-italic"></i></button>
                            <button class="btn btn-default"><i class="fa fa-underline"></i></button>
                            <button class="btn btn-default"><i class="fa fa-strikethrough"></i></button>
                        </div>
                    </div>
                </div>
                <div class="block-section">
                    <div class="btn-toolbar">
                        <div class="btn-group">
                            <button class="btn btn-primary"><i class="fa fa-align-left"></i></button>
                            <button class="btn btn-primary"><i class="fa fa-align-center"></i></button>
                            <button class="btn btn-primary"><i class="fa fa-align-right"></i></button>
                        </div>
                        <div class="btn-group">
                            <button class="btn btn-primary"><i class="fa fa-outdent"></i></button>
                            <button class="btn btn-primary"><i class="fa fa-indent"></i></button>
                        </div>
                        <div class="btn-group">
                            <button class="btn btn-primary"><i class="fa fa-list-ul"></i></button>
                            <button class="btn btn-primary"><i class="fa fa-list-ol"></i></button>
                            <button class="btn btn-primary"><i class="fa fa-table"></i></button>
                        </div>
                        <div class="btn-group">
                            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li>
                                    <a href="javascript:void(0)">Action 1</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">Action 2</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="javascript:void(0)">Another Action</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- END Toolbars Content -->
            </div>
            <!-- END Toolbars Block -->

            <!-- Button Groups and Dropdowns Block -->
            <div class="block">
                <!-- Button Groups and Dropdowns Title -->
                <div class="block-title">
                    <h2>Button Groups &amp; Dropdowns</h2>
                </div>
                <!-- END Button Groups and Dropdowns Title -->

                <!-- Button Groups and Dropdowns -->
                <div class="block-section">
                    <h4 class="sub-header">Button Groups &amp; Dropdowns</h4>
                    <div class="btn-group">
                        <a href="javascript:void(0)" data-toggle="dropdown" class="btn btn-default dropdown-toggle">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu text-left">
                            <li class="dropdown-header">
                                <i class="fa fa-user pull-right"></i> <strong>ACCOUNT</strong>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="fa fa-pencil pull-right"></i>
                                    Edit
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="fa fa-cog pull-right"></i>
                                    Settings
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="disabled">
                                <a href="javascript:void(0)">Disabled</a>
                            </li>
                        </ul>
                    </div>
                    <div class="btn-group dropup text-left">
                        <a href="javascript:void(0)" data-toggle="dropdown" class="btn btn-success dropdown-toggle">Dropup <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">
                                <i class="fa fa-user pull-right"></i> <strong>ACCOUNT</strong>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="fa fa-pencil pull-right"></i>
                                    Edit
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="fa fa-cog pull-right"></i>
                                    Settings
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="disabled">
                                <a href="javascript:void(0)">Disabled</a>
                            </li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <a class="btn btn-danger">Multiple</a>
                        <a class="btn btn-danger"><i class="fa fa-tree"></i></a>
                        <div class="btn-group">
                            <a href="javascript:void(0)" data-toggle="dropdown" class="btn btn-danger dropdown-toggle"><span class="caret"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right text-left">
                                <li class="dropdown-header">
                                    <i class="fa fa-user pull-right"></i> <strong>ACCOUNT</strong>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="fa fa-pencil pull-right"></i>
                                        Edit
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="fa fa-cog pull-right"></i>
                                        Settings
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li class="disabled">
                                    <a href="javascript:void(0)">Disabled</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- END Button Groups and Dropdowns -->

                <!-- Button Groups -->
                <div class="block-section">
                    <h4 class="sub-header">Button Groups</h4>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="btn-group">
                                <button class="btn btn-default"><i class="fa fa-volume-up"></i></button>
                                <button class="btn btn-default"><i class="fa fa-volume-down"></i></button>
                                <button class="btn btn-default"><i class="fa fa-power-off"></i></button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="btn-group">
                                <button class="btn btn-danger"><i class="fa fa-chevron-left"></i></button>
                                <button class="btn btn-danger">100</button>
                                <button class="btn btn-danger"><i class="fa fa-chevron-right"></i></button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="btn-group">
                                <button class="btn btn-primary"><i class="fa fa-play"></i></button>
                                <button class="btn btn-primary"><i class="fa fa-pause"></i></button>
                                <button class="btn btn-primary"><i class="fa fa-stop"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block-section">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="btn-group-vertical">
                                <button class="btn btn-warning"><i class="fa fa-volume-up"></i></button>
                                <button class="btn btn-warning"><i class="fa fa-volume-down"></i></button>
                                <button class="btn btn-warning"><i class="fa fa-power-off"></i></button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="btn-group-vertical">
                                <button class="btn btn-info"><i class="fa fa-chevron-up fa-fw"></i></button>
                                <button class="btn btn-info">100</button>
                                <button class="btn btn-info"><i class="fa fa-chevron-down fa-fw"></i></button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="btn-group-vertical">
                                <button class="btn btn-success"><i class="fa fa-play"></i></button>
                                <button class="btn btn-success"><i class="fa fa-pause"></i></button>
                                <button class="btn btn-success"><i class="fa fa-stop"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Button Groups -->
            </div>
            <!-- END Button Groups and Dropdowns Block -->
        </div>
    </div>
    <!-- END Buttons Row -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>
<?php include 'inc/template_end.php'; ?>