<?php include 'inc/config.php'; $template['header_link'] = 'UI ELEMENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Table Styles Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Tables</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Elements</li>
                        <li><a href="">Tables</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Table Styles Header -->

    <!-- Table Styles Block -->
    <div class="block">
        <!-- Table Styles Title -->
        <div class="block-title clearfix">
            <!-- Changing classes functionality initialized in js/pages/tablesGeneral.js -->
            <div class="block-options pull-right">
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default active" id="style-striped" data-toggle="tooltip" title=".table-striped">Striped</a>
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" id="style-condensed" data-toggle="tooltip" title=".table-condensed">Condensed</a>
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" id="style-hover" data-toggle="tooltip" title=".table-hover">Hover</a>
            </div>
            <div class="block-options pull-right">
                <div id="style-borders" class="btn-group">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" id="style-default" data-toggle="tooltip">Default</a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default active" id="style-bordered" data-toggle="tooltip" title=".table-bordered">Bordered</a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" id="style-borderless" data-toggle="tooltip" title=".table-borderless">Borderless</a>
                </div>
            </div>
            <h2><span class="hidden-xs">Table</span> Styles</h2>
        </div>
        <!-- END Table Styles Title -->

        <!-- Table Styles Content -->
        <div class="table-responsive">
            <!--
            Available Table Classes:
                'table'             - basic table
                'table-bordered'    - table with full borders
                'table-borderless'  - table with no borders
                'table-striped'     - striped table
                'table-condensed'   - table with smaller top and bottom cell padding
                'table-hover'       - rows highlighted on mouse hover
                'table-vcenter'     - middle align content vertically
            -->
            <table id="general-table" class="table table-striped table-bordered table-vcenter">
                <thead>
                    <tr>
                        <th style="width: 80px;" class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></th>
                        <th>Name</th>
                        <th>Email</th>
                        <th style="width: 320px;">Progress</th>
                        <th style="width: 120px;" class="text-center"><i class="fa fa-flash"></i></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Gabriel Morris</strong></td>
                        <td>gabriel.morris@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-danger" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Ellis Thompson</strong></td>
                        <td>ellis.thompson@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Reece Bell</strong></td>
                        <td>reece.bell@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-success" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100" style="width: 95%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Scarlett Reid</strong></td>
                        <td>user4@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Alfie Harrison</strong></td>
                        <td>alfie.harrison@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-danger" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" style="width: 25%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Finley Hunt</strong></td>
                        <td>finley.hunt@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-warning" role="progressbar" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100" style="width: 55%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Oliver Watson</strong></td>
                        <td>oliver.watson@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Maddison Reid</strong></td>
                        <td>maddison.reid@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-success" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Katie Ward</strong></td>
                        <td>katie.ward@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-success" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label></td>
                        <td><strong>Aidan Powell</strong></td>
                        <td>aidan.powell@example.com</td>
                        <td>
                            <div class="progress progress-mini active remove-margin">
                                <div class="progress-bar progress-bar-striped progress-bar-warning" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%"></div>
                            </div>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-sm btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-sm btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- END Table Styles Content -->
    </div>
    <!-- END Table Styles Block -->

    <!-- Tables Row -->
    <div class="row">
        <div class="col-lg-6">
            <!-- Partial Responsive Block -->
            <div class="block">
                <!-- Partial Responsive Title -->
                <div class="block-title">
                    <h2>Partial Responsive Table</h2>
                </div>
                <!-- END Partial Responsive Title -->

                <!-- Partial Responsive Content -->
                <table class="table table-striped table-borderless table-vcenter">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th class="hidden-xs">Email</th>
                            <th class="hidden-sm hidden-xs">Status</th>
                            <th style="width: 80px;" class="text-center"><i class="fa fa-flash"></i></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>User1</strong></td>
                            <td class="hidden-xs">user1@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-warning">Pending..</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User2</strong></td>
                            <td class="hidden-xs">user2@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-success">Active</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User3</strong></td>
                            <td class="hidden-xs">user3@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-info">On hold..</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User4</strong></td>
                            <td class="hidden-xs">user4@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-success">Active</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User5</strong></td>
                            <td class="hidden-xs">user5@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-danger">Disabled</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User6</strong></td>
                            <td class="hidden-xs">user6@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-info">On hold..</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User7</strong></td>
                            <td class="hidden-xs">user7@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-danger">Disabled</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User8</strong></td>
                            <td class="hidden-xs">user8@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-success">Active.</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>User9</strong></td>
                            <td class="hidden-xs">user9@example.com</td>
                            <td class="hidden-sm hidden-xs"><a href="javascript:void(0)" class="label label-warning">Pending..</a></td>
                            <td class="text-center">
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <!-- END Partial Responsive Content -->
            </div>
            <!-- END Partial Responsive Block -->
        </div>
        <div class="col-lg-6">
            <!-- Row Styles Block -->
            <div class="block">
                <!-- Row Styles Title -->
                <div class="block-title">
                    <h2>Table Row Styles</h2>
                </div>
                <!-- END Row Styles Title -->

                <!-- Row Styles Content -->
                <div class="table-responsive">
                    <table class="table table-borderless table-vcenter">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Status</th>
                                <th style="width: 80px;" class="text-center"><i class="fa fa-flash"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="active">
                                <td><strong>User1</strong></td>
                                <td>user1@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-warning">Pending..</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>User2</strong></td>
                                <td>user2@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-success">Active</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr class="success">
                                <td><strong>User3</strong></td>
                                <td>user3@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-info">On hold..</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>User4</strong></td>
                                <td>user4@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-success">Active</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr class="warning">
                                <td><strong>User5</strong></td>
                                <td>user5@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-danger">Disabled</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>User6</strong></td>
                                <td>user6@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-info">On hold..</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr class="danger">
                                <td><strong>User7</strong></td>
                                <td>user7@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-danger">Disabled</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>User8</strong></td>
                                <td>user8@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-warning">Pending..</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <tr class="info">
                                <td><strong>User9</strong></td>
                                <td>user9@example.com</td>
                                <td><a href="javascript:void(0)" class="label label-danger">Disabled</a></td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- END Row Styles Content -->
            </div>
            <!-- END Row Styles Block -->
        </div>
    </div>
    <!-- END Tables Row -->

    <!-- Datatables Block -->
    <!-- Datatables is initialized in js/pages/uiTables.js -->
    <div class="block full">
        <div class="block-title">
            <h2>Datatables</h2>
        </div>
        <div class="table-responsive">
            <table id="example-datatable" class="table table-striped table-bordered table-vcenter">
                <thead>
                    <tr>
                        <th class="text-center" style="width: 50px;">ID</th>
                        <th>User</th>
                        <th>Email</th>
                        <th style="width: 120px;">Status</th>
                        <th class="text-center" style="width: 75px;"><i class="fa fa-flash"></i></th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                    $labels['0']['class'] = "label-success";
                    $labels['0']['text'] = "Active";
                    $labels['1']['class'] = "label-info";
                    $labels['1']['text'] = "On hold..";
                    $labels['2']['class'] = "label-danger";
                    $labels['2']['text'] = "Disabled";
                    $labels['3']['class'] = "label-warning";
                    $labels['3']['text'] = "Pending..";
                    ?>
                    <?php for($i=1; $i<31; $i++) { ?>
                    <tr>
                        <td class="text-center"><?php echo $i; ?></td>
                        <td><strong>AppUser<?php echo $i; ?></strong></td>
                        <td>app.user<?php echo $i; ?>@example.com</td>
                        <?php $rand = rand(0, 3); ?>
                        <td><span class="label<?php echo ($labels[$rand]['class']) ? " " . $labels[$rand]['class'] : ""; ?>"><?php echo $labels[$rand]['text'] ?></span></td>
                        <td class="text-center">
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Edit User" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                            <a href="javascript:void(0)" data-toggle="tooltip" title="Delete User" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                    <?php } ?>
                </tbody>
            </table>
        </div>
    </div>
    <!-- END Datatables Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/uiTables.js"></script>
<script>$(function(){ UiTables.init(); });</script>

<?php include 'inc/template_end.php'; ?>