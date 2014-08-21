<?php include 'inc/config.php'; $template['header_link'] = 'FORMS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Validation Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Form Validation</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Forms</li>
                        <li><a href="">Validation</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Validation Header -->

    <!-- Form Validation Content -->
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
            <!-- Form Validation Block -->
            <div class="block">
                <!-- Form Validation Title -->
                <div class="block-title">
                    <h2>Example Form</h2>
                </div>
                <!-- END Form Validation Title -->

                <!-- Form Validation Form -->
                <form id="form-validation" action="page_forms_validation.php" method="post" class="form-horizontal form-bordered">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-username">Username <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="text" id="val-username" name="val-username" class="form-control" placeholder="Choose a nice username..">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-email">Email <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="text" id="val-email" name="val-email" class="form-control" placeholder="Enter your valid email..">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-password">Password <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="password" id="val-password" name="val-password" class="form-control" placeholder="Choose a good one..">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-confirm-password">Confirm Password <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="password" id="val-confirm-password" name="val-confirm-password" class="form-control" placeholder="..and confirm it to be safe!">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-suggestions">Suggestions <span class="text-danger">*</span></label>
                        <div class="col-md-9">
                            <textarea id="val-suggestions" name="val-suggestions" rows="7" class="form-control" placeholder="Share your ideas with us.."></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-skill">Best Skill <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <select id="val-skill" name="val-skill" class="form-control">
                                <option value="">Please select</option>
                                <option value="html">HTML</option>
                                <option value="css">CSS</option>
                                <option value="javascript">Javascript</option>
                                <option value="ruby">Ruby</option>
                                <option value="php">PHP</option>
                                <option value="asp">ASP.NET</option>
                                <option value="python">Python</option>
                                <option value="mysql">MySQL</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-website">Website <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="text" id="val-website" name="val-website" class="form-control" placeholder="http://example.com">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-digits">Digits <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="text" id="val-digits" name="val-digits" class="form-control" placeholder="3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-number">Number <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="text" id="val-number" name="val-number" class="form-control" placeholder="3.0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="val-range">Range [1, 5] <span class="text-danger">*</span></label>
                        <div class="col-md-6">
                            <input type="text" id="val-range" name="val-range" class="form-control" placeholder="3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><a href="#modal-terms" data-toggle="modal">Agree to Terms</a> <span class="text-danger">*</span></label>
                        <div class="col-md-8">
                            <label class="switch switch-primary" for="val-terms">
                                <input type="checkbox" id="val-terms" name="val-terms" value="1">
                                <span data-toggle="tooltip" title="I agree to the terms"></span>
                            </label>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-8 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Form Validation Form -->

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
            <!-- END Form Validation Block -->
        </div>
    </div>
    <!-- END Form Validation Content -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/formsValidation.js"></script>
<script>$(function() { FormsValidation.init(); });</script>

<?php include 'inc/template_end.php'; ?>