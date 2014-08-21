<?php include 'inc/config.php'; $template['header_link'] = 'FORMS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Wizard Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Form Wizard</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Forms</li>
                        <li><a href="">Wizard</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Wizard Header -->

    <!-- Wizards Content -->
    <!-- Form Wizards are initialized in js/pages/formsWizard.js -->
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
            <!-- Clickable Wizard Block -->
            <div class="block">
                <!-- Clickable Wizard Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2>Wizard with Clickable Steps</h2>
                </div>
                <!-- END Clickable Wizard Title -->

                <!-- Clickable Wizard Content -->
                <form id="clickable-wizard" action="page_forms_wizard.php" method="post" class="form-horizontal form-bordered">
                    <!-- First Step -->
                    <div id="clickable-first" class="step">
                        <!-- Step Info -->
                        <div class="form-group">
                            <div class="col-xs-12">
                                <ul class="nav nav-pills nav-justified clickable-steps">
                                    <li class="active"><a href="javascript:void(0)" data-gotostep="clickable-first"><i class="fa fa-user"></i> <strong>Account</strong></a></li>
                                    <li><a href="javascript:void(0)" data-gotostep="clickable-second"><i class="fa fa-pencil-square-o"></i> <strong>Profile</strong></a></li>
                                    <li><a href="javascript:void(0)" data-gotostep="clickable-third"><i class="fa fa-check"></i> <strong>Confirmation</strong></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- END Step Info -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-username">Username</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-username" name="example-clickable-username" class="form-control" placeholder="Choose a nice username..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-email">Email</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-email" name="example-clickable-email" class="form-control" placeholder="Enter your valid email..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-password">Password</label>
                            <div class="col-md-6">
                                <input type="password" id="example-clickable-password" name="example-clickable-password" class="form-control" placeholder="Choose a good one..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-password2">Confirm Password</label>
                            <div class="col-md-6">
                                <input type="password" id="example-clickable-password2" name="example-clickable-password2" class="form-control" placeholder="..and confirm it to be safe!">
                            </div>
                        </div>
                    </div>
                    <!-- END First Step -->

                    <!-- Second Step -->
                    <div id="clickable-second" class="step">
                        <!-- Step Info -->
                        <div class="form-group">
                            <div class="col-xs-12">
                                <ul class="nav nav-pills nav-justified clickable-steps">
                                    <li><a href="javascript:void(0)" class="text-muted" data-gotostep="clickable-first"><i class="fa fa-user"></i> <del><strong>Account</strong></del></a></li>
                                    <li class="active"><a href="javascript:void(0)" data-gotostep="clickable-second"><i class="fa fa-pencil-square-o"></i> <strong>Profile</strong></a></li>
                                    <li><a href="javascript:void(0)" data-gotostep="clickable-third"><i class="fa fa-check"></i> <strong>Confirmation</strong></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- END Step Info -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-firstname">Firstname</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-firstname" name="example-clickable-firstname" class="form-control" placeholder="Enter your firstname..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-lastname">Lastname</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-lastname" name="example-clickable-lastname" class="form-control" placeholder="Enter your lastname..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-country">Country</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-country" name="example-clickable-country" class="form-control" placeholder="Where do you live?">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-city">City</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-city" name="example-clickable-city" class="form-control" placeholder="In which city?">
                            </div>
                        </div>
                    </div>
                    <!-- END Second Step -->

                    <!-- Third Step -->
                    <div id="clickable-third" class="step">
                        <!-- Step Info -->
                        <div class="form-group">
                            <div class="col-xs-12">
                                <ul class="nav nav-pills nav-justified clickable-steps">
                                    <li><a href="javascript:void(0)" class="text-muted" data-gotostep="clickable-first"><i class="fa fa-user"></i> <del><strong>Account</strong></del></a></li>
                                    <li><a href="javascript:void(0)" class="text-muted" data-gotostep="clickable-second"><i class="fa fa-pencil-square-o"></i> <del><strong>Profile</strong></del></a></li>
                                    <li class="active"><a href="javascript:void(0)" data-gotostep="clickable-third"><i class="fa fa-check"></i> <strong>Confirmation</strong></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- END Step Info -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-pc">Postal Code</label>
                            <div class="col-md-6">
                                <input type="text" id="example-clickable-pc" name="example-clickable-pc" class="form-control" placeholder="eg: 125314">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-clickable-suggestions">Suggestions</label>
                            <div class="col-md-8">
                                <textarea id="example-clickable-suggestions" name="example-clickable-suggestions" rows="5" class="form-control" placeholder="Share your ideas with us.."></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label"><a href="#modal-terms" data-toggle="modal">Terms</a></label>
                            <div class="col-md-6">
                                <label class="switch switch-primary" for="example-clickable-terms">
                                    <input type="checkbox" id="example-clickable-terms" name="example-clickable-terms" value="1">
                                    <span data-toggle="tooltip" title="I agree to the terms!"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <!-- END Third Step -->

                    <!-- Form Buttons -->
                    <div class="form-group form-actions">
                        <div class="col-md-8 col-md-offset-4">
                            <button type="reset" class="btn btn-effect-ripple btn-danger" id="back">Back</button>
                            <button type="submit" class="btn btn-effect-ripple btn-primary" id="next">Next</button>
                        </div>
                    </div>
                    <!-- END Form Buttons -->
                </form>
                <!-- END Clickable Wizard Content -->
            </div>
            <!-- END Clickable Wizard Block -->

            <!-- Progress Bar Wizard Block -->
            <div class="block">
                <!-- Progress Bars Wizard Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2>Wizard with Progress Bar</h2>
                </div>
                <!-- END Progress Bar Wizard Title -->

                <!-- Progress Wizard Content -->
                <form id="progress-wizard" action="page_forms_wizard.php" method="post" class="form-horizontal form-bordered">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="progress progress-mini remove-margin">
                                <div id="progress-bar-wizard" class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0"></div>
                            </div>
                        </div>
                    </div>
                    <!-- END Progress Bar -->

                    <!-- First Step -->
                    <div id="progress-first" class="step">
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-username">Username</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-username" name="example-progress-username" class="form-control" placeholder="Choose a nice username..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-email">Email</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-email" name="example-progress-email" class="form-control" placeholder="Enter your valid email..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-password">Password</label>
                            <div class="col-md-6">
                                <input type="password" id="example-progress-password" name="example-progress-password" class="form-control" placeholder="Choose a good one..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-password2">Retype Password</label>
                            <div class="col-md-6">
                                <input type="password" id="example-progress-password2" name="example-progress-password2" class="form-control" placeholder="..and confirm it to be safe!">
                            </div>
                        </div>
                    </div>
                    <!-- END First Step -->

                    <!-- Second Step -->
                    <div id="progress-second" class="step">
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-firstname">Firstname</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-firstname" name="example-progress-firstname" class="form-control" placeholder="Enter your firstname..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-lastname">Lastname</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-lastname" name="example-progress-lastname" class="form-control" placeholder="Enter your lastname..">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-country">Country</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-country" name="example-progress-country" class="form-control" placeholder="Where do you live?">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-city">City</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-city" name="example-progress-city" class="form-control" placeholder="In which city?">
                            </div>
                        </div>
                    </div>
                    <!-- END Second Step -->

                    <!-- Third Step -->
                    <div id="progress-third" class="step">
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-pc">Postal Code</label>
                            <div class="col-md-6">
                                <input type="text" id="example-progress-pc" name="example-progress-pc" class="form-control" placeholder="eg: 125314">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="example-progress-suggestions">Suggestions</label>
                            <div class="col-md-8">
                                <textarea id="example-progress-suggestions" name="example-progress-suggestions" rows="5" class="form-control" placeholder="Share your ideas with us.."></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label"><a href="#modal-terms" data-toggle="modal">Terms</a></label>
                            <div class="col-md-8">
                                <label class="switch switch-primary" for="example-progress-terms">
                                    <input type="checkbox" id="example-progress-terms" name="example-progress-terms" value="1">
                                    <span data-toggle="tooltip" title="I agree to the terms!"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <!-- END Third Step -->

                    <!-- Form Buttons -->
                    <div class="form-group form-actions">
                        <div class="col-md-8 col-md-offset-4">
                            <button type="reset" class="btn btn-effect-ripple btn-danger" id="back1">Back</button>
                            <button type="submit" class="btn btn-effect-ripple btn-primary" id="next2">Next</button>
                        </div>
                    </div>
                    <!-- END Form Buttons -->
                </form>
                <!-- END Progress Bar Wizard Content -->
            </div>
            <!-- END Progress Bar Wizard Block -->
        </div>
    </div>
    <!-- END Wizards Content -->

    <!-- Terms Modal -->
    <div id="modal-terms" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center"><strong>Terms and Conditions</strong></h3>
                </div>
                <div class="modal-body">
                    <h4 class="page-header">1. <strong>General</strong></h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor.</p>
                    <h4 class="page-header">2. <strong>Account</strong></h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor.</p>
                    <h4 class="page-header">3. <strong>Service</strong></h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor.</p>
                    <h4 class="page-header">4. <strong>Payments</strong></h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor.</p>
                </div>
                <div class="modal-footer">
                    <div class="text-center">
                        <button type="button" class="btn btn-effect-ripple btn-primary" data-dismiss="modal">I've read them!</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END Terms Modal -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/formsWizard.js"></script>
<script>$(function(){ FormsWizard.init(); });</script>

<?php include 'inc/template_end.php'; ?>