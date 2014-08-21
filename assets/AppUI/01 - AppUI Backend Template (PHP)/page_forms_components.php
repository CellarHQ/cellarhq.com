<?php include 'inc/config.php'; $template['header_link'] = 'FORMS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Forms Components Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Form Components</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Forms</li>
                        <li><a href="">Components</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Forms Components Header -->

    <!-- Form Components Row -->
    <div class="row">
        <div class="col-md-6">
            <!-- Horizontal Form Block -->
            <div class="block">
                <!-- Horizontal Form Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Horizontal Form</h2>
                </div>
                <!-- END Horizontal Form Title -->

                <!-- Horizontal Form Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-hf-email">Email</label>
                        <div class="col-md-9">
                            <input type="email" id="example-hf-email" name="example-hf-email" class="form-control">
                            <span class="help-block">Please enter your email</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-hf-password">Password</label>
                        <div class="col-md-9">
                            <input type="password" id="example-hf-password" name="example-hf-password" class="form-control">
                            <span class="help-block">Please enter your password</span>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Horizontal Form Content -->
            </div>
            <!-- END Horizontal Form Block -->

            <!-- Labels on top Form Block -->
            <div class="block">
                <!-- Labels on top Form Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Labels on top Form</h2>
                </div>
                <!-- END Labels on top Form Title -->

                <!-- Labels on top Form Content -->
                <form action="page_forms_components.php" method="post" class="form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label for="example-nf-email">Email</label>
                        <input type="email" id="example-nf-email" name="example-nf-email" class="form-control">
                        <span class="help-block">Please enter your email</span>
                    </div>
                    <div class="form-group">
                        <label for="example-nf-password">Password</label>
                        <input type="password" id="example-nf-password" name="example-nf-password" class="form-control">
                        <span class="help-block">Please enter your password</span>
                    </div>
                    <div class="form-group form-actions">
                        <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                        <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                    </div>
                </form>
                <!-- END Labels on top Form Content -->
            </div>
            <!-- END Labels on top Form Block -->

            <!-- Inline Form Block -->
            <div class="block full">
                <!-- Inline Form Title -->
                <div class="block-title">
                    <h2>Inline Form</h2>
                </div>
                <!-- END Inline Form Title -->

                <!-- Inline Form Content -->
                <form action="page_forms_components.php" method="post" class="form-inline" onsubmit="return false;">
                    <div class="form-group">
                        <label class="sr-only" for="example-if-email">Email</label>
                        <input type="email" id="example-if-email" name="example-if-email" class="form-control" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="example-if-password">Password</label>
                        <input type="password" id="example-if-password" name="example-if-password" class="form-control" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                        <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                    </div>
                </form>
                <!-- END Inline Form Content -->
            </div>
            <!-- END Inline Form Block -->

            <!-- General Elements Block -->
            <div class="block">
                <!-- General Elements Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>General Elements</h2>
                </div>
                <!-- END General Elements Title -->

                <!-- General Elements Content -->
                <form action="page_forms_components.php" method="post" enctype="multipart/form-data" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-md-3 control-label">Static</label>
                        <div class="col-md-9">
                            <p class="form-control-static">This is static text</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-text-input">Text</label>
                        <div class="col-md-6">
                            <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                            <span class="help-block">This is a help text</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-email">Email</label>
                        <div class="col-md-6">
                            <input type="email" id="example-email" name="example-email" class="form-control" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-password">Password</label>
                        <div class="col-md-6">
                            <input type="password" id="example-password" name="example-password" class="form-control" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-disabled">Disabled</label>
                        <div class="col-md-6">
                            <input type="text" id="example-disabled" name="example-disabled" class="form-control" placeholder="Disabled" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-textarea-input">Textarea</label>
                        <div class="col-md-9">
                            <textarea id="example-textarea-input" name="example-textarea-input" rows="7" class="form-control" placeholder="Description.."></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-select">Select</label>
                        <div class="col-md-6">
                            <select id="example-select" name="example-select" class="form-control" size="1">
                                <option value="0">Please select</option>
                                <option value="1">HTML</option>
                                <option value="2">CSS</option>
                                <option value="3">Javascript</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-multiple-select">Multiple</label>
                        <div class="col-md-6">
                            <select id="example-multiple-select" name="example-multiple-select" class="form-control" size="5" multiple>
                                <option value="1">Option #1</option>
                                <option value="2">Option #2</option>
                                <option value="3">Option #3</option>
                                <option value="4">Option #4</option>
                                <option value="5">Option #5</option>
                                <option value="6">Option #6</option>
                                <option value="7">Option #7</option>
                                <option value="8">Option #8</option>
                                <option value="9">Option #9</option>
                                <option value="10">Option #10</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Radios</label>
                        <div class="col-md-9">
                            <div class="radio">
                                <label for="example-radio1">
                                    <input type="radio" id="example-radio1" name="example-radios" value="option1"> HTML
                                </label>
                            </div>
                            <div class="radio">
                                <label for="example-radio2">
                                    <input type="radio" id="example-radio2" name="example-radios" value="option2"> CSS
                                </label>
                            </div>
                            <div class="radio">
                                <label for="example-radio3">
                                    <input type="radio" id="example-radio3" name="example-radios" value="option3"> Javascript
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Inline Radios</label>
                        <div class="col-md-9">
                            <label class="radio-inline" for="example-inline-radio1">
                                <input type="radio" id="example-inline-radio1" name="example-inline-radios" value="option1"> HTML
                            </label>
                            <label class="radio-inline" for="example-inline-radio2">
                                <input type="radio" id="example-inline-radio2" name="example-inline-radios" value="option2"> CSS
                            </label>
                            <label class="radio-inline" for="example-inline-radio3">
                                <input type="radio" id="example-inline-radio3" name="example-inline-radios" value="option3"> Javascript
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Checkboxes</label>
                        <div class="col-md-9">
                            <div class="checkbox">
                                <label for="example-checkbox1">
                                    <input type="checkbox" id="example-checkbox1" name="example-checkbox1" value="option1"> HTML
                                </label>
                            </div>
                            <div class="checkbox">
                                <label for="example-checkbox2">
                                    <input type="checkbox" id="example-checkbox2" name="example-checkbox2" value="option2"> CSS
                                </label>
                            </div>
                            <div class="checkbox">
                                <label for="example-checkbox3">
                                    <input type="checkbox" id="example-checkbox3" name="example-checkbox3" value="option3"> Javascript
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Inline Checkboxes</label>
                        <div class="col-md-9">
                            <label class="checkbox-inline" for="example-inline-checkbox1">
                                <input type="checkbox" id="example-inline-checkbox1" name="example-inline-checkbox1" value="option1"> HTML
                            </label>
                            <label class="checkbox-inline" for="example-inline-checkbox2">
                                <input type="checkbox" id="example-inline-checkbox2" name="example-inline-checkbox2" value="option2"> CSS
                            </label>
                            <label class="checkbox-inline" for="example-inline-checkbox3">
                                <input type="checkbox" id="example-inline-checkbox3" name="example-inline-checkbox3" value="option3"> Javascript
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-file-input">File</label>
                        <div class="col-md-9">
                            <input type="file" id="example-file-input" name="example-file-input">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-file-multiple-input">Multiple File</label>
                        <div class="col-md-9">
                            <input type="file" id="example-file-multiple-input" name="example-file-multiple-input" multiple>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END General Elements Content -->
            </div>
            <!-- END General Elements Block -->

            <!-- Switches Block -->
            <div class="block">
                <!-- Switches Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>CSS Switches</h2>
                </div>
                <!-- END Switches Title -->

                <!-- Switches Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Unchecked</label>
                        <div class="col-sm-9">
                            <label class="switch switch-default"><input type="checkbox"><span></span></label>
                            <label class="switch switch-warning"><input type="checkbox"><span></span></label>
                            <label class="switch switch-danger"><input type="checkbox"><span></span></label>
                            <label class="switch switch-info"><input type="checkbox"><span></span></label>
                            <label class="switch switch-success"><input type="checkbox"><span></span></label>
                            <label class="switch switch-primary"><input type="checkbox"><span></span></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Checked</label>
                        <div class="col-sm-9">
                            <label class="switch switch-default"><input type="checkbox" checked><span></span></label>
                            <label class="switch switch-warning"><input type="checkbox" checked><span></span></label>
                            <label class="switch switch-danger"><input type="checkbox" checked><span></span></label>
                            <label class="switch switch-info"><input type="checkbox" checked><span></span></label>
                            <label class="switch switch-success"><input type="checkbox" checked><span></span></label>
                            <label class="switch switch-primary"><input type="checkbox" checked><span></span></label>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Switches Content -->
            </div>
            <!-- END Switches Block -->

            <!-- Checkboxes Block -->
            <div class="block">
                <!-- Checkboxes Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>CSS Checkboxes</h2>
                </div>
                <!-- END Checkboxes Title -->

                <!-- Switches Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Unchecked</label>
                        <div class="col-sm-9">
                            <label class="csscheckbox csscheckbox-default"><input type="checkbox"><span></span></label>
                            <label class="csscheckbox csscheckbox-warning"><input type="checkbox"><span></span></label>
                            <label class="csscheckbox csscheckbox-danger"><input type="checkbox"><span></span></label>
                            <label class="csscheckbox csscheckbox-info"><input type="checkbox"><span></span></label>
                            <label class="csscheckbox csscheckbox-success"><input type="checkbox"><span></span></label>
                            <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Checked</label>
                        <div class="col-sm-9">
                            <label class="csscheckbox csscheckbox-default"><input type="checkbox" checked><span></span></label>
                            <label class="csscheckbox csscheckbox-warning"><input type="checkbox" checked><span></span></label>
                            <label class="csscheckbox csscheckbox-danger"><input type="checkbox" checked><span></span></label>
                            <label class="csscheckbox csscheckbox-info"><input type="checkbox" checked><span></span></label>
                            <label class="csscheckbox csscheckbox-success"><input type="checkbox" checked><span></span></label>
                            <label class="csscheckbox csscheckbox-primary"><input type="checkbox" checked><span></span></label>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Checkboxes Content -->
            </div>
            <!-- END Checkboxes Block -->

            <!-- Select Component Block -->
            <div class="block">
                <!-- Select Component Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Select Component</h2>
                </div>
                <!-- END Select Component Title -->

                <!-- Select Component Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <!-- Chosen plugin (class is initialized in js/app.js -> uiInit()), for extra usage examples you can check out http://harvesthq.github.io/chosen/ -->
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-chosen">Chosen</label>
                        <div class="col-md-5">
                            <select id="example-chosen" name="example-chosen" class="select-chosen" data-placeholder="Choose a Country.." style="width: 250px;">
                                <option></option><!-- Required for data-placeholder attribute to work with Chosen plugin -->
                                <option value="United States">United States</option>
                                <option value="United Kingdom">United Kingdom</option>
                                <option value="Afghanistan">Afghanistan</option>
                                <option value="Aland Islands">Aland Islands</option>
                                <option value="Albania">Albania</option>
                                <option value="Algeria">Algeria</option>
                                <option value="American Samoa">American Samoa</option>
                                <option value="Andorra">Andorra</option>
                                <option value="Angola">Angola</option>
                                <option value="Anguilla">Anguilla</option>
                                <option value="Antarctica">Antarctica</option>
                                <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                                <option value="Argentina">Argentina</option>
                                <option value="Armenia">Armenia</option>
                                <option value="Aruba">Aruba</option>
                                <option value="Australia">Australia</option>
                                <option value="Austria">Austria</option>
                                <option value="Azerbaijan">Azerbaijan</option>
                                <option value="Bahamas">Bahamas</option>
                                <option value="Bahrain">Bahrain</option>
                                <option value="Bangladesh">Bangladesh</option>
                                <option value="Barbados">Barbados</option>
                                <option value="Belarus">Belarus</option>
                                <option value="Belgium">Belgium</option>
                                <option value="Belize">Belize</option>
                                <option value="Benin">Benin</option>
                                <option value="Bermuda">Bermuda</option>
                                <option value="Bhutan">Bhutan</option>
                                <option value="Bolivia, Plurinational State of">Bolivia, Plurinational State of</option>
                                <option value="Bonaire, Sint Eustatius and Saba">Bonaire, Sint Eustatius and Saba</option>
                                <option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
                                <option value="Botswana">Botswana</option>
                                <option value="Bouvet Island">Bouvet Island</option>
                                <option value="Brazil">Brazil</option>
                                <option value="British Indian Ocean Territory">British Indian Ocean Territory</option>
                                <option value="Brunei Darussalam">Brunei Darussalam</option>
                                <option value="Bulgaria">Bulgaria</option>
                                <option value="Burkina Faso">Burkina Faso</option>
                                <option value="Burundi">Burundi</option>
                                <option value="Cambodia">Cambodia</option>
                                <option value="Cameroon">Cameroon</option>
                                <option value="Canada">Canada</option>
                                <option value="Cape Verde">Cape Verde</option>
                                <option value="Cayman Islands">Cayman Islands</option>
                                <option value="Central African Republic">Central African Republic</option>
                                <option value="Chad">Chad</option>
                                <option value="Chile">Chile</option>
                                <option value="China">China</option>
                                <option value="Christmas Island">Christmas Island</option>
                                <option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option>
                                <option value="Colombia">Colombia</option>
                                <option value="Comoros">Comoros</option>
                                <option value="Congo">Congo</option>
                                <option value="Congo, The Democratic Republic of The">Congo, The Democratic Republic of The</option>
                                <option value="Cook Islands">Cook Islands</option>
                                <option value="Costa Rica">Costa Rica</option>
                                <option value="Cote D'ivoire">Cote D'ivoire</option>
                                <option value="Croatia">Croatia</option>
                                <option value="Cuba">Cuba</option>
                                <option value="Curacao">Curacao</option>
                                <option value="Cyprus">Cyprus</option>
                                <option value="Czech Republic">Czech Republic</option>
                                <option value="Denmark">Denmark</option>
                                <option value="Djibouti">Djibouti</option>
                                <option value="Dominica">Dominica</option>
                                <option value="Dominican Republic">Dominican Republic</option>
                                <option value="Ecuador">Ecuador</option>
                                <option value="Egypt">Egypt</option>
                                <option value="El Salvador">El Salvador</option>
                                <option value="Equatorial Guinea">Equatorial Guinea</option>
                                <option value="Eritrea">Eritrea</option>
                                <option value="Estonia">Estonia</option>
                                <option value="Ethiopia">Ethiopia</option>
                                <option value="Falkland Islands (Malvinas)">Falkland Islands (Malvinas)</option>
                                <option value="Faroe Islands">Faroe Islands</option>
                                <option value="Fiji">Fiji</option>
                                <option value="Finland">Finland</option>
                                <option value="France">France</option>
                                <option value="French Guiana">French Guiana</option>
                                <option value="French Polynesia">French Polynesia</option>
                                <option value="French Southern Territories">French Southern Territories</option>
                                <option value="Gabon">Gabon</option>
                                <option value="Gambia">Gambia</option>
                                <option value="Georgia">Georgia</option>
                                <option value="Germany">Germany</option>
                                <option value="Ghana">Ghana</option>
                                <option value="Gibraltar">Gibraltar</option>
                                <option value="Greece">Greece</option>
                                <option value="Greenland">Greenland</option>
                                <option value="Grenada">Grenada</option>
                                <option value="Guadeloupe">Guadeloupe</option>
                                <option value="Guam">Guam</option>
                                <option value="Guatemala">Guatemala</option>
                                <option value="Guernsey">Guernsey</option>
                                <option value="Guinea">Guinea</option>
                                <option value="Guinea-bissau">Guinea-bissau</option>
                                <option value="Guyana">Guyana</option>
                                <option value="Haiti">Haiti</option>
                                <option value="Heard Island and Mcdonald Islands">Heard Island and Mcdonald Islands</option>
                                <option value="Holy See (Vatican City State)">Holy See (Vatican City State)</option>
                                <option value="Honduras">Honduras</option>
                                <option value="Hong Kong">Hong Kong</option>
                                <option value="Hungary">Hungary</option>
                                <option value="Iceland">Iceland</option>
                                <option value="India">India</option>
                                <option value="Indonesia">Indonesia</option>
                                <option value="Iran, Islamic Republic of">Iran, Islamic Republic of</option>
                                <option value="Iraq">Iraq</option>
                                <option value="Ireland">Ireland</option>
                                <option value="Isle of Man">Isle of Man</option>
                                <option value="Israel">Israel</option>
                                <option value="Italy">Italy</option>
                                <option value="Jamaica">Jamaica</option>
                                <option value="Japan">Japan</option>
                                <option value="Jersey">Jersey</option>
                                <option value="Jordan">Jordan</option>
                                <option value="Kazakhstan">Kazakhstan</option>
                                <option value="Kenya">Kenya</option>
                                <option value="Kiribati">Kiribati</option>
                                <option value="Korea, Democratic People's Republic of">Korea, Democratic People's Republic of</option>
                                <option value="Korea, Republic of">Korea, Republic of</option>
                                <option value="Kuwait">Kuwait</option>
                                <option value="Kyrgyzstan">Kyrgyzstan</option>
                                <option value="Lao People's Democratic Republic">Lao People's Democratic Republic</option>
                                <option value="Latvia">Latvia</option>
                                <option value="Lebanon">Lebanon</option>
                                <option value="Lesotho">Lesotho</option>
                                <option value="Liberia">Liberia</option>
                                <option value="Libya">Libya</option>
                                <option value="Liechtenstein">Liechtenstein</option>
                                <option value="Lithuania">Lithuania</option>
                                <option value="Luxembourg">Luxembourg</option>
                                <option value="Macao">Macao</option>
                                <option value="Macedonia, The Former Yugoslav Republic of">Macedonia, The Former Yugoslav Republic of</option>
                                <option value="Madagascar">Madagascar</option>
                                <option value="Malawi">Malawi</option>
                                <option value="Malaysia">Malaysia</option>
                                <option value="Maldives">Maldives</option>
                                <option value="Mali">Mali</option>
                                <option value="Malta">Malta</option>
                                <option value="Marshall Islands">Marshall Islands</option>
                                <option value="Martinique">Martinique</option>
                                <option value="Mauritania">Mauritania</option>
                                <option value="Mauritius">Mauritius</option>
                                <option value="Mayotte">Mayotte</option>
                                <option value="Mexico">Mexico</option>
                                <option value="Micronesia, Federated States of">Micronesia, Federated States of</option>
                                <option value="Moldova, Republic of">Moldova, Republic of</option>
                                <option value="Monaco">Monaco</option>
                                <option value="Mongolia">Mongolia</option>
                                <option value="Montenegro">Montenegro</option>
                                <option value="Montserrat">Montserrat</option>
                                <option value="Morocco">Morocco</option>
                                <option value="Mozambique">Mozambique</option>
                                <option value="Myanmar">Myanmar</option>
                                <option value="Namibia">Namibia</option>
                                <option value="Nauru">Nauru</option>
                                <option value="Nepal">Nepal</option>
                                <option value="Netherlands">Netherlands</option>
                                <option value="New Caledonia">New Caledonia</option>
                                <option value="New Zealand">New Zealand</option>
                                <option value="Nicaragua">Nicaragua</option>
                                <option value="Niger">Niger</option>
                                <option value="Nigeria">Nigeria</option>
                                <option value="Niue">Niue</option>
                                <option value="Norfolk Island">Norfolk Island</option>
                                <option value="Northern Mariana Islands">Northern Mariana Islands</option>
                                <option value="Norway">Norway</option>
                                <option value="Oman">Oman</option>
                                <option value="Pakistan">Pakistan</option>
                                <option value="Palau">Palau</option>
                                <option value="Palestinian Territory, Occupied">Palestinian Territory, Occupied</option>
                                <option value="Panama">Panama</option>
                                <option value="Papua New Guinea">Papua New Guinea</option>
                                <option value="Paraguay">Paraguay</option>
                                <option value="Peru">Peru</option>
                                <option value="Philippines">Philippines</option>
                                <option value="Pitcairn">Pitcairn</option>
                                <option value="Poland">Poland</option>
                                <option value="Portugal">Portugal</option>
                                <option value="Puerto Rico">Puerto Rico</option>
                                <option value="Qatar">Qatar</option>
                                <option value="Reunion">Reunion</option>
                                <option value="Romania">Romania</option>
                                <option value="Russian Federation">Russian Federation</option>
                                <option value="Rwanda">Rwanda</option>
                                <option value="Saint Barthelemy">Saint Barthelemy</option>
                                <option value="Saint Helena, Ascension and Tristan da Cunha">Saint Helena, Ascension and Tristan da Cunha</option>
                                <option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
                                <option value="Saint Lucia">Saint Lucia</option>
                                <option value="Saint Martin (French part)">Saint Martin (French part)</option>
                                <option value="Saint Pierre and Miquelon">Saint Pierre and Miquelon</option>
                                <option value="Saint Vincent and The Grenadines">Saint Vincent and The Grenadines</option>
                                <option value="Samoa">Samoa</option>
                                <option value="San Marino">San Marino</option>
                                <option value="Sao Tome and Principe">Sao Tome and Principe</option>
                                <option value="Saudi Arabia">Saudi Arabia</option>
                                <option value="Senegal">Senegal</option>
                                <option value="Serbia">Serbia</option>
                                <option value="Seychelles">Seychelles</option>
                                <option value="Sierra Leone">Sierra Leone</option>
                                <option value="Singapore">Singapore</option>
                                <option value="Sint Maarten (Dutch part)">Sint Maarten (Dutch part)</option>
                                <option value="Slovakia">Slovakia</option>
                                <option value="Slovenia">Slovenia</option>
                                <option value="Solomon Islands">Solomon Islands</option>
                                <option value="Somalia">Somalia</option>
                                <option value="South Africa">South Africa</option>
                                <option value="South Georgia and The South Sandwich Islands">South Georgia and The South Sandwich Islands</option>
                                <option value="South Sudan">South Sudan</option>
                                <option value="Spain">Spain</option>
                                <option value="Sri Lanka">Sri Lanka</option>
                                <option value="Sudan">Sudan</option>
                                <option value="Suriname">Suriname</option>
                                <option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen</option>
                                <option value="Swaziland">Swaziland</option>
                                <option value="Sweden">Sweden</option>
                                <option value="Switzerland">Switzerland</option>
                                <option value="Syrian Arab Republic">Syrian Arab Republic</option>
                                <option value="Taiwan, Province of China">Taiwan, Province of China</option>
                                <option value="Tajikistan">Tajikistan</option>
                                <option value="Tanzania, United Republic of">Tanzania, United Republic of</option>
                                <option value="Thailand">Thailand</option>
                                <option value="Timor-leste">Timor-leste</option>
                                <option value="Togo">Togo</option>
                                <option value="Tokelau">Tokelau</option>
                                <option value="Tonga">Tonga</option>
                                <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                                <option value="Tunisia">Tunisia</option>
                                <option value="Turkey">Turkey</option>
                                <option value="Turkmenistan">Turkmenistan</option>
                                <option value="Turks and Caicos Islands">Turks and Caicos Islands</option>
                                <option value="Tuvalu">Tuvalu</option>
                                <option value="Uganda">Uganda</option>
                                <option value="Ukraine">Ukraine</option>
                                <option value="United Arab Emirates">United Arab Emirates</option>
                                <option value="United Kingdom">United Kingdom</option>
                                <option value="United States">United States</option>
                                <option value="United States Minor Outlying Islands">United States Minor Outlying Islands</option>
                                <option value="Uruguay">Uruguay</option>
                                <option value="Uzbekistan">Uzbekistan</option>
                                <option value="Vanuatu">Vanuatu</option>
                                <option value="Venezuela, Bolivarian Republic of">Venezuela, Bolivarian Republic of</option>
                                <option value="Viet Nam">Viet Nam</option>
                                <option value="Virgin Islands, British">Virgin Islands, British</option>
                                <option value="Virgin Islands, U.S.">Virgin Islands, U.S.</option>
                                <option value="Wallis and Futuna">Wallis and Futuna</option>
                                <option value="Western Sahara">Western Sahara</option>
                                <option value="Yemen">Yemen</option>
                                <option value="Zambia">Zambia</option>
                                <option value="Zimbabwe">Zimbabwe</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-chosen-multiple">Chosen Multiple</label>
                        <div class="col-md-5">
                            <select id="example-chosen-multiple" name="example-chosen-multiple" class="select-chosen" data-placeholder="Choose a Country.." style="width: 250px;" multiple>
                                <option value="United States">United States</option>
                                <option value="United Kingdom">United Kingdom</option>
                                <option value="Afghanistan">Afghanistan</option>
                                <option value="Aland Islands">Aland Islands</option>
                                <option value="Albania">Albania</option>
                                <option value="Algeria">Algeria</option>
                                <option value="American Samoa">American Samoa</option>
                                <option value="Andorra">Andorra</option>
                                <option value="Angola">Angola</option>
                                <option value="Anguilla">Anguilla</option>
                                <option value="Antarctica">Antarctica</option>
                                <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                                <option value="Argentina">Argentina</option>
                                <option value="Armenia">Armenia</option>
                                <option value="Aruba">Aruba</option>
                                <option value="Australia">Australia</option>
                                <option value="Austria">Austria</option>
                                <option value="Azerbaijan">Azerbaijan</option>
                                <option value="Bahamas">Bahamas</option>
                                <option value="Bahrain">Bahrain</option>
                                <option value="Bangladesh">Bangladesh</option>
                                <option value="Barbados">Barbados</option>
                                <option value="Belarus">Belarus</option>
                                <option value="Belgium">Belgium</option>
                                <option value="Belize">Belize</option>
                                <option value="Benin">Benin</option>
                                <option value="Bermuda">Bermuda</option>
                                <option value="Bhutan">Bhutan</option>
                                <option value="Bolivia, Plurinational State of">Bolivia, Plurinational State of</option>
                                <option value="Bonaire, Sint Eustatius and Saba">Bonaire, Sint Eustatius and Saba</option>
                                <option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
                                <option value="Botswana">Botswana</option>
                                <option value="Bouvet Island">Bouvet Island</option>
                                <option value="Brazil">Brazil</option>
                                <option value="British Indian Ocean Territory">British Indian Ocean Territory</option>
                                <option value="Brunei Darussalam">Brunei Darussalam</option>
                                <option value="Bulgaria">Bulgaria</option>
                                <option value="Burkina Faso">Burkina Faso</option>
                                <option value="Burundi">Burundi</option>
                                <option value="Cambodia">Cambodia</option>
                                <option value="Cameroon">Cameroon</option>
                                <option value="Canada">Canada</option>
                                <option value="Cape Verde">Cape Verde</option>
                                <option value="Cayman Islands">Cayman Islands</option>
                                <option value="Central African Republic">Central African Republic</option>
                                <option value="Chad">Chad</option>
                                <option value="Chile">Chile</option>
                                <option value="China">China</option>
                                <option value="Christmas Island">Christmas Island</option>
                                <option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option>
                                <option value="Colombia">Colombia</option>
                                <option value="Comoros">Comoros</option>
                                <option value="Congo">Congo</option>
                                <option value="Congo, The Democratic Republic of The">Congo, The Democratic Republic of The</option>
                                <option value="Cook Islands">Cook Islands</option>
                                <option value="Costa Rica">Costa Rica</option>
                                <option value="Cote D'ivoire">Cote D'ivoire</option>
                                <option value="Croatia">Croatia</option>
                                <option value="Cuba">Cuba</option>
                                <option value="Curacao">Curacao</option>
                                <option value="Cyprus">Cyprus</option>
                                <option value="Czech Republic">Czech Republic</option>
                                <option value="Denmark">Denmark</option>
                                <option value="Djibouti">Djibouti</option>
                                <option value="Dominica">Dominica</option>
                                <option value="Dominican Republic">Dominican Republic</option>
                                <option value="Ecuador">Ecuador</option>
                                <option value="Egypt">Egypt</option>
                                <option value="El Salvador">El Salvador</option>
                                <option value="Equatorial Guinea">Equatorial Guinea</option>
                                <option value="Eritrea">Eritrea</option>
                                <option value="Estonia">Estonia</option>
                                <option value="Ethiopia">Ethiopia</option>
                                <option value="Falkland Islands (Malvinas)">Falkland Islands (Malvinas)</option>
                                <option value="Faroe Islands">Faroe Islands</option>
                                <option value="Fiji">Fiji</option>
                                <option value="Finland">Finland</option>
                                <option value="France">France</option>
                                <option value="French Guiana">French Guiana</option>
                                <option value="French Polynesia">French Polynesia</option>
                                <option value="French Southern Territories">French Southern Territories</option>
                                <option value="Gabon">Gabon</option>
                                <option value="Gambia">Gambia</option>
                                <option value="Georgia">Georgia</option>
                                <option value="Germany">Germany</option>
                                <option value="Ghana">Ghana</option>
                                <option value="Gibraltar">Gibraltar</option>
                                <option value="Greece">Greece</option>
                                <option value="Greenland">Greenland</option>
                                <option value="Grenada">Grenada</option>
                                <option value="Guadeloupe">Guadeloupe</option>
                                <option value="Guam">Guam</option>
                                <option value="Guatemala">Guatemala</option>
                                <option value="Guernsey">Guernsey</option>
                                <option value="Guinea">Guinea</option>
                                <option value="Guinea-bissau">Guinea-bissau</option>
                                <option value="Guyana">Guyana</option>
                                <option value="Haiti">Haiti</option>
                                <option value="Heard Island and Mcdonald Islands">Heard Island and Mcdonald Islands</option>
                                <option value="Holy See (Vatican City State)">Holy See (Vatican City State)</option>
                                <option value="Honduras">Honduras</option>
                                <option value="Hong Kong">Hong Kong</option>
                                <option value="Hungary">Hungary</option>
                                <option value="Iceland">Iceland</option>
                                <option value="India">India</option>
                                <option value="Indonesia">Indonesia</option>
                                <option value="Iran, Islamic Republic of">Iran, Islamic Republic of</option>
                                <option value="Iraq">Iraq</option>
                                <option value="Ireland">Ireland</option>
                                <option value="Isle of Man">Isle of Man</option>
                                <option value="Israel">Israel</option>
                                <option value="Italy">Italy</option>
                                <option value="Jamaica">Jamaica</option>
                                <option value="Japan">Japan</option>
                                <option value="Jersey">Jersey</option>
                                <option value="Jordan">Jordan</option>
                                <option value="Kazakhstan">Kazakhstan</option>
                                <option value="Kenya">Kenya</option>
                                <option value="Kiribati">Kiribati</option>
                                <option value="Korea, Democratic People's Republic of">Korea, Democratic People's Republic of</option>
                                <option value="Korea, Republic of">Korea, Republic of</option>
                                <option value="Kuwait">Kuwait</option>
                                <option value="Kyrgyzstan">Kyrgyzstan</option>
                                <option value="Lao People's Democratic Republic">Lao People's Democratic Republic</option>
                                <option value="Latvia">Latvia</option>
                                <option value="Lebanon">Lebanon</option>
                                <option value="Lesotho">Lesotho</option>
                                <option value="Liberia">Liberia</option>
                                <option value="Libya">Libya</option>
                                <option value="Liechtenstein">Liechtenstein</option>
                                <option value="Lithuania">Lithuania</option>
                                <option value="Luxembourg">Luxembourg</option>
                                <option value="Macao">Macao</option>
                                <option value="Macedonia, The Former Yugoslav Republic of">Macedonia, The Former Yugoslav Republic of</option>
                                <option value="Madagascar">Madagascar</option>
                                <option value="Malawi">Malawi</option>
                                <option value="Malaysia">Malaysia</option>
                                <option value="Maldives">Maldives</option>
                                <option value="Mali">Mali</option>
                                <option value="Malta">Malta</option>
                                <option value="Marshall Islands">Marshall Islands</option>
                                <option value="Martinique">Martinique</option>
                                <option value="Mauritania">Mauritania</option>
                                <option value="Mauritius">Mauritius</option>
                                <option value="Mayotte">Mayotte</option>
                                <option value="Mexico">Mexico</option>
                                <option value="Micronesia, Federated States of">Micronesia, Federated States of</option>
                                <option value="Moldova, Republic of">Moldova, Republic of</option>
                                <option value="Monaco">Monaco</option>
                                <option value="Mongolia">Mongolia</option>
                                <option value="Montenegro">Montenegro</option>
                                <option value="Montserrat">Montserrat</option>
                                <option value="Morocco">Morocco</option>
                                <option value="Mozambique">Mozambique</option>
                                <option value="Myanmar">Myanmar</option>
                                <option value="Namibia">Namibia</option>
                                <option value="Nauru">Nauru</option>
                                <option value="Nepal">Nepal</option>
                                <option value="Netherlands">Netherlands</option>
                                <option value="New Caledonia">New Caledonia</option>
                                <option value="New Zealand">New Zealand</option>
                                <option value="Nicaragua">Nicaragua</option>
                                <option value="Niger">Niger</option>
                                <option value="Nigeria">Nigeria</option>
                                <option value="Niue">Niue</option>
                                <option value="Norfolk Island">Norfolk Island</option>
                                <option value="Northern Mariana Islands">Northern Mariana Islands</option>
                                <option value="Norway">Norway</option>
                                <option value="Oman">Oman</option>
                                <option value="Pakistan">Pakistan</option>
                                <option value="Palau">Palau</option>
                                <option value="Palestinian Territory, Occupied">Palestinian Territory, Occupied</option>
                                <option value="Panama">Panama</option>
                                <option value="Papua New Guinea">Papua New Guinea</option>
                                <option value="Paraguay">Paraguay</option>
                                <option value="Peru">Peru</option>
                                <option value="Philippines">Philippines</option>
                                <option value="Pitcairn">Pitcairn</option>
                                <option value="Poland">Poland</option>
                                <option value="Portugal">Portugal</option>
                                <option value="Puerto Rico">Puerto Rico</option>
                                <option value="Qatar">Qatar</option>
                                <option value="Reunion">Reunion</option>
                                <option value="Romania">Romania</option>
                                <option value="Russian Federation">Russian Federation</option>
                                <option value="Rwanda">Rwanda</option>
                                <option value="Saint Barthelemy">Saint Barthelemy</option>
                                <option value="Saint Helena, Ascension and Tristan da Cunha">Saint Helena, Ascension and Tristan da Cunha</option>
                                <option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
                                <option value="Saint Lucia">Saint Lucia</option>
                                <option value="Saint Martin (French part)">Saint Martin (French part)</option>
                                <option value="Saint Pierre and Miquelon">Saint Pierre and Miquelon</option>
                                <option value="Saint Vincent and The Grenadines">Saint Vincent and The Grenadines</option>
                                <option value="Samoa">Samoa</option>
                                <option value="San Marino">San Marino</option>
                                <option value="Sao Tome and Principe">Sao Tome and Principe</option>
                                <option value="Saudi Arabia">Saudi Arabia</option>
                                <option value="Senegal">Senegal</option>
                                <option value="Serbia">Serbia</option>
                                <option value="Seychelles">Seychelles</option>
                                <option value="Sierra Leone">Sierra Leone</option>
                                <option value="Singapore">Singapore</option>
                                <option value="Sint Maarten (Dutch part)">Sint Maarten (Dutch part)</option>
                                <option value="Slovakia">Slovakia</option>
                                <option value="Slovenia">Slovenia</option>
                                <option value="Solomon Islands">Solomon Islands</option>
                                <option value="Somalia">Somalia</option>
                                <option value="South Africa">South Africa</option>
                                <option value="South Georgia and The South Sandwich Islands">South Georgia and The South Sandwich Islands</option>
                                <option value="South Sudan">South Sudan</option>
                                <option value="Spain">Spain</option>
                                <option value="Sri Lanka">Sri Lanka</option>
                                <option value="Sudan">Sudan</option>
                                <option value="Suriname">Suriname</option>
                                <option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen</option>
                                <option value="Swaziland">Swaziland</option>
                                <option value="Sweden">Sweden</option>
                                <option value="Switzerland">Switzerland</option>
                                <option value="Syrian Arab Republic">Syrian Arab Republic</option>
                                <option value="Taiwan, Province of China">Taiwan, Province of China</option>
                                <option value="Tajikistan">Tajikistan</option>
                                <option value="Tanzania, United Republic of">Tanzania, United Republic of</option>
                                <option value="Thailand">Thailand</option>
                                <option value="Timor-leste">Timor-leste</option>
                                <option value="Togo">Togo</option>
                                <option value="Tokelau">Tokelau</option>
                                <option value="Tonga">Tonga</option>
                                <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                                <option value="Tunisia">Tunisia</option>
                                <option value="Turkey">Turkey</option>
                                <option value="Turkmenistan">Turkmenistan</option>
                                <option value="Turks and Caicos Islands">Turks and Caicos Islands</option>
                                <option value="Tuvalu">Tuvalu</option>
                                <option value="Uganda">Uganda</option>
                                <option value="Ukraine">Ukraine</option>
                                <option value="United Arab Emirates">United Arab Emirates</option>
                                <option value="United Kingdom">United Kingdom</option>
                                <option value="United States">United States</option>
                                <option value="United States Minor Outlying Islands">United States Minor Outlying Islands</option>
                                <option value="Uruguay">Uruguay</option>
                                <option value="Uzbekistan">Uzbekistan</option>
                                <option value="Vanuatu">Vanuatu</option>
                                <option value="Venezuela, Bolivarian Republic of">Venezuela, Bolivarian Republic of</option>
                                <option value="Viet Nam">Viet Nam</option>
                                <option value="Virgin Islands, British">Virgin Islands, British</option>
                                <option value="Virgin Islands, U.S.">Virgin Islands, U.S.</option>
                                <option value="Wallis and Futuna">Wallis and Futuna</option>
                                <option value="Western Sahara">Western Sahara</option>
                                <option value="Yemen">Yemen</option>
                                <option value="Zambia">Zambia</option>
                                <option value="Zimbabwe">Zimbabwe</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Select Component Content -->
            </div>
            <!-- END Select Component Block -->

            <!-- Sliders Block -->
            <div class="block">
                <!-- Sliders Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Sliders</h2>
                </div>
                <!-- END Sliders Title -->

                <!-- Sliders Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <!-- Slider for Bootstrap (class is initialized in js/app.js -> uiInit()), for extra usage examples you can check out http://www.eyecon.ro/bootstrap-slider -->
                    <div class="form-group">
                        <label class="col-md-3 control-label">Contextual</label>
                        <div class="col-md-9">
                            <div class="input-slider-danger">
                                <input type="text" id="example-slider-context1" name="example-slider-context1" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="20" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="hide">
                            </div>
                            <div class="input-slider-warning">
                                <input type="text" id="example-slider-context2" name="example-slider-context2" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="40" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="hide">
                            </div>
                            <div class="input-slider-info">
                                <input type="text" id="example-slider-context3" name="example-slider-context3" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="60" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="hide">
                            </div>
                            <div class="input-slider-success">
                                <input type="text" id="example-slider-context4" name="example-slider-context4" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="80" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="hide">
                            </div>
                            <input type="text" id="example-slider-context5" name="example-slider-context5" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="100" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="hide">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Horizontal</label>
                        <div class="col-md-9">
                            <input type="text" id="example-slider-hor1" name="example-slider-hor1" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="25" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show">
                            <input type="text" id="example-slider-hor2" name="example-slider-hor2" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="50" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show">
                            <input type="text" id="example-slider-hor3" name="example-slider-hor3" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="75" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show">
                            <input type="text" id="example-slider-hor4" name="example-slider-hor4" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="100" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Vertical</label>
                        <div class="col-md-9">
                            <input type="text" id="example-slider-vert1" name="example-slider-vert1" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="25" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">
                            <input type="text" id="example-slider-vert2" name="example-slider-vert2" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="75" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">
                            <input type="text" id="example-slider-vert3" name="example-slider-vert3" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="25" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">
                            <input type="text" id="example-slider-vert4" name="example-slider-vert4" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="75" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">
                            <input type="text" id="example-slider-vert5" name="example-slider-vert5" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="25" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Range</label>
                        <div class="col-md-9">
                            <input type="text" id="example-slider-range1" name="example-slider-range1" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="[40,60]" data-slider-orientation="horizontal" data-slider-tooltip="show">
                            <input type="text" id="example-slider-range2" name="example-slider-range2" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="[25,75]" data-slider-orientation="horizontal" data-slider-tooltip="hide">
                            <input type="text" id="example-slider-range3" name="example-slider-range3" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="[10,90]" data-slider-orientation="horizontal" data-slider-tooltip="hide">
                            <input type="text" id="example-slider-range4" name="example-slider-range4" class="form-control input-slider" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="[0,100]" data-slider-orientation="horizontal" data-slider-tooltip="hide">
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Sliders Content -->
            </div>
            <!-- END Sliders Block -->
        </div>
        <div class="col-md-6">
            <!-- Input States Block -->
            <div class="block">
                <!-- Input States Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Input States</h2>
                </div>
                <!-- END Input States Title -->

                <!-- Input States Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="state-normal">Normal</label>
                        <div class="col-md-6">
                            <input type="text" id="state-normal" name="state-normal" class="form-control" placeholder="...">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <label class="col-md-3 control-label" for="state-success">Success</label>
                        <div class="col-md-6">
                            <input type="text" id="state-success" name="state-success" class="form-control" placeholder="...">
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <label class="col-md-3 control-label" for="state-warning">Warning</label>
                        <div class="col-md-6">
                            <input type="text" id="state-warning" name="state-warning" class="form-control" placeholder="...">
                        </div>
                    </div>
                    <div class="form-group has-error">
                        <label class="col-md-3 control-label" for="state-danger">Danger</label>
                        <div class="col-md-6">
                            <input type="text" id="state-danger" name="state-danger" class="form-control" placeholder="...">
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Input States Content -->
            </div>
            <!-- END Input States Block -->

            <!-- Input Sizes Block -->
            <div class="block">
                <!-- Input Sizes Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Input Sizes</h2>
                </div>
                <!-- END Input Sizes Title -->

                <!-- Input Sizes Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="example-input-small">Small</label>
                        <div class="col-sm-6">
                            <input type="text" id="example-input-small" name="example-input-small" class="form-control input-sm" placeholder=".input-sm">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="example-input-normal">Normal</label>
                        <div class="col-sm-6">
                            <input type="text" id="example-input-normal" name="example-input-normal" class="form-control" placeholder="Normal">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="example-input-large">Large</label>
                        <div class="col-sm-6">
                            <input type="text" id="example-input-large" name="example-input-large" class="form-control input-lg" placeholder=".input-lg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="example-input-large">Grid Sizes</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder=".col-sm-4">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-5 col-sm-offset-3">
                            <input type="text" class="form-control" placeholder=".col-sm-5">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="text" class="form-control" placeholder=".col-sm-6">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7 col-sm-offset-3">
                            <input type="text" class="form-control" placeholder=".col-sm-7">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8 col-sm-offset-3">
                            <input type="text" class="form-control" placeholder=".col-sm-8">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <input type="text" class="form-control" placeholder=".col-sm-9">
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-danger">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-warning">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-info">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-success">Action</button>
                        </div>
                    </div>
                </form>
                <!-- END Input Sizes Content -->
            </div>
            <!-- END Input Sizes Block -->

            <!-- Flexible Grid Block -->
            <div class="block">
                <!-- Flexible Grid Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Flexible Grid</h2>
                </div>
                <!-- END Flexible Grid Title -->

                <!-- Flexible Grid Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <div class="col-xs-7">
                            <input type="text" class="form-control" placeholder=".col-xs-7">
                        </div>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" placeholder=".col-xs-5">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-8">
                            <input type="text" class="form-control" placeholder=".col-xs-8">
                        </div>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" placeholder=".col-xs-4">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <input type="text" class="form-control" placeholder=".col-xs-3">
                        </div>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" placeholder=".col-xs-9">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-5">
                            <input type="text" class="form-control" placeholder=".col-xs-5">
                        </div>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" placeholder=".col-xs-7">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <input type="text" class="form-control" placeholder=".col-xs-6">
                        </div>
                        <div class="col-xs-6">
                            <input type="text" class="form-control" placeholder=".col-xs-6">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-8">
                            <input type="text" class="form-control" placeholder=".col-md-8">
                        </div>
                        <div class="col-md-4">
                            <input type="text" class="form-control" placeholder=".col-md-4">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-3">
                            <input type="text" class="form-control" placeholder=".col-lg-3">
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" placeholder=".col-lg-9">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-7">
                            <input type="text" class="form-control" placeholder=".col-sm-7">
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" placeholder=".col-sm-5">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder=".col-xs-6">
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder=".col-xs-6">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-3">
                            <input type="text" class="form-control" placeholder=".col-sm-3">
                        </div>
                        <div class="col-md-3">
                            <input type="text" class="form-control" placeholder=".col-sm-3">
                        </div>
                        <div class="col-md-3">
                            <input type="text" class="form-control" placeholder=".col-sm-3">
                        </div>
                        <div class="col-md-3">
                            <input type="text" class="form-control" placeholder=".col-sm-3">
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-12">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-danger">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-warning">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-info">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-success">Action</button>
                        </div>
                    </div>
                </form>
                <!-- END Flexible Grid Content -->
            </div>
            <!-- END Flexible Grid Block -->

            <!-- Input Groups Block -->
            <div class="block">
                <!-- Input Groups Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Input Groups</h2>
                </div>
                <!-- END Input Groups Title -->

                <!-- Input Groups Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-input1-group1">Static</label>
                        <div class="col-md-9">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" id="example-input1-group1" name="example-input1-group1" class="form-control" placeholder="Username">
                            </div>
                        </div>
                        <div class="col-md-9 col-md-offset-3">
                            <div class="input-group">
                                <input type="email" id="example-input2-group1" name="example-input2-group1" class="form-control" placeholder="Email">
                                <span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>
                            </div>
                        </div>
                        <div class="col-md-9 col-md-offset-3">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-euro"></i></span>
                                <input type="text" id="example-input3-group1" name="example-input3-group1" class="form-control" placeholder="..">
                                <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-input1-group2">Buttons</label>
                        <div class="col-md-9">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary"><i class="fa fa-search"></i> Search</button>
                                </span>
                                <input type="text" id="example-input1-group2" name="example-input1-group2" class="form-control" placeholder="Username">
                            </div>
                        </div>
                        <div class="col-md-9 col-md-offset-3">
                            <div class="input-group">
                                <input type="email" id="example-input2-group2" name="example-input2-group2" class="form-control" placeholder="Email">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary">Submit</button>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-9 col-md-offset-3">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary"><i class="fa fa-facebook"></i></button>
                                </span>
                                <input type="text" id="example-input3-group2" name="example-input3-group2" class="form-control" placeholder="Search">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary"><i class="fa fa-twitter"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-input1-group3">Dropdowns</label>
                        <div class="col-md-9">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">Action</a></li>
                                        <li><a href="javascript:void(0)">Another action</a></li>
                                        <li class="divider"></li>
                                        <li><a href="javascript:void(0)">Separated link</a></li>
                                    </ul>
                                </div>
                                <input type="text" id="example-input1-group3" name="example-input1-group3" class="form-control" placeholder="Username">
                            </div>
                        </div>
                        <div class="col-md-9 col-md-offset-3">
                            <div class="input-group">
                                <input type="email" id="example-input2-group3" name="example-input2-group3" class="form-control" placeholder="Email">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></button>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li><a href="javascript:void(0)">Action</a></li>
                                        <li><a href="javascript:void(0)">Another action</a></li>
                                        <li class="divider"></li>
                                        <li><a href="javascript:void(0)">Separated link</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-9 col-md-offset-3">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary">Action</button>
                                    <button type="button" class="btn btn-effect-ripple btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)">Action</a></li>
                                        <li><a href="javascript:void(0)">Another action</a></li>
                                        <li class="divider"></li>
                                        <li><a href="javascript:void(0)">Separated link</a></li>
                                    </ul>
                                </div>
                                <input type="text" id="example-input3-group3" name="example-input3-group3" class="form-control" placeholder="..">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-effect-ripple btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li><a href="javascript:void(0)">Action</a></li>
                                        <li><a href="javascript:void(0)">Another action</a></li>
                                        <li class="divider"></li>
                                        <li><a href="javascript:void(0)">Separated link</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-danger">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-warning">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-info">Action</button>
                            <button type="button" class="btn btn-effect-ripple btn-success">Action</button>
                        </div>
                    </div>
                </form>
                <!-- END Input Groups Content -->
            </div>
            <!-- END Input Groups Block -->

            <!-- Tags Input Block -->
            <div class="block">
                <!-- Tags Input Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Tags Input</h2>
                </div>
                <!-- END Tags Input Title -->

                <!-- Tags Input Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <!-- Jquery Tags Input (class is initialized in js/app.js -> uiInit()), for extra usage examples you can check out https://github.com/xoxco/jQuery-Tags-Input -->
                    <div class="form-group">
                        <label class="col-md-3 control-label">Enter tags</label>
                        <div class="col-md-9">
                            <input type="text" id="example-tags" name="example-tags" class="input-tags" value="HTML, CSS, Javascript">
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Tags Input Content -->
            </div>
            <!-- END Tags Input Block -->

            <!-- Timepicker Block -->
            <div class="block">
                <!-- Timepicker Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Timepicker</h2>
                </div>
                <!-- END Timepicker Title -->

                <!-- Timepicker Content -->
                <!-- Timepicker for Bootstrap (classes are initialized in js/app.js -> uiInit()), for extra usage examples you can check out http://jdewit.github.io/bootstrap-timepicker/ -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-timepicker24">Select Time (24h)</label>
                        <div class="col-md-5">
                            <div class="input-group bootstrap-timepicker">
                                <input type="text" id="example-timepicker24" name="example-timepicker24" class="form-control input-timepicker24">
                                <span class="input-group-btn">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary"><i class="fa fa-clock-o"></i></a>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-timepicker">Select Time</label>
                        <div class="col-md-5">
                            <div class="input-group bootstrap-timepicker">
                                <input type="text" id="example-timepicker" name="example-timepicker" class="form-control input-timepicker">
                                <span class="input-group-btn">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary"><i class="fa fa-clock-o"></i></a>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Timepicker Content -->
            </div>
            <!-- END Timepicker Block -->

            <!-- Datepicker Block -->
            <div class="block">
                <!-- Datepicker Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
                    </div>
                    <h2>Datepicker</h2>
                </div>
                <!-- END Datepicker Title -->

                <!-- Datepicker Content -->
                <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                    <!-- Datepicker for Bootstrap (classes are initialized in js/app.js -> uiInit()), for extra usage examples you can check out http://eternicode.github.io/bootstrap-datepicker -->
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-daterange1">Date Range</label>
                        <div class="col-md-9">
                            <div class="input-group input-daterange" data-date-format="mm/dd/yyyy">
                                <input type="text" id="example-daterange1" name="example-daterange1" class="form-control" placeholder="From">
                                <span class="input-group-addon"><i class="fa fa-chevron-right"></i></span>
                                <input type="text" id="example-daterange2" name="example-daterange2" class="form-control" placeholder="To">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Inline Datepicker</label>
                        <div class="col-md-7">
                            <div id="example-datepicker-inline" class="input-datepicker"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="example-datepicker">Datepicker</label>
                        <div class="col-md-5">
                            <input type="text" id="example-datepicker" name="example-datepicker" class="form-control input-datepicker" data-date-format="mm/dd/yy" placeholder="mm/dd/yy">
                        </div>
                        <div class="col-md-5 col-md-offset-3">
                            <input type="text" id="example-datepicker2" name="example-datepicker2" class="form-control input-datepicker" data-date-format="dd/mm/yy" placeholder="dd/mm/yy">
                        </div>
                        <div class="col-md-5 col-md-offset-3">
                            <input type="text" id="example-datepicker3" name="example-datepicker3" class="form-control input-datepicker" data-date-format="dd-mm-yyyy" placeholder="dd-mm-yyyy">
                        </div>
                        <div class="col-md-5 col-md-offset-3">
                            <input type="text" id="example-datepicker4" name="example-datepicker4" class="form-control input-datepicker" data-date-format="mm-dd-yyyy" placeholder="mm-dd-yyyy">
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                            <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                        </div>
                    </div>
                </form>
                <!-- END Datepicker Content -->
            </div>
            <!-- END Datepicker Block -->

            <!-- Dropzone Block -->
            <div class="block full">
                <!-- Dropzone Title -->
                <div class="block-title">
                    <h2>Dropzone.js</h2>
                </div>
                <!-- END Dropzone Title -->

                <!-- Dropzone Content -->
                <!-- Dropzone.js, You can check out https://github.com/enyo/dropzone/wiki for usage examples -->
                <form action="page_forms_components.php" class="dropzone"></form>
                <!-- END Dropzone Content -->
            </div>
            <!-- END Dropzone Block -->
        </div>
    </div>
    <!-- END Form Components Row -->

    <!-- Simple Editor Block -->
    <div class="block">
        <!-- Simple Editor Title -->
        <div class="block-title">
            <div class="block-options pull-right">
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
            </div>
            <h2>Simple Editor</h2>
        </div>
        <!-- END Simple Editor Title -->

        <!-- Simple Editor Content -->
        <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
            <!-- Bootstrap WYSIWYG5 (class is initialized in js/app.js -> uiInit()) -->
            <div class="form-group">
                <div class="col-xs-12">
                    <textarea id="textarea-wysiwyg" name="textarea-wysiwyg" rows="10" class="form-control textarea-editor"></textarea>
                </div>
            </div>
            <div class="form-group form-actions">
                <div class="col-xs-12">
                    <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                    <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                </div>
            </div>
        </form>
        <!-- END Simple Editor Content -->
    </div>
    <!-- END Simple Editor Block -->

    <!-- CKEditor Block -->
    <div class="block">
        <!-- CKEditor Title -->
        <div class="block-title">
            <div class="block-options pull-right">
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-bordered enable-tooltip" data-toggle="button" title="Toggles .form-bordered class">Borderless</a>
            </div>
            <h2>CKEditor</h2>
        </div>
        <!-- END CKEditor Title -->

        <!-- CKEditor Content -->
        <form action="page_forms_components.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
            <!-- CKEditor, you just need to include the plugin (see at the bottom of this page) and add the class 'ckeditor' to your textarea -->
            <!-- More info can be found at http://ckeditor.com -->
            <div class="form-group">
                <div class="col-xs-12">
                    <textarea id="textarea-ckeditor" name="textarea-ckeditor" class="ckeditor"></textarea>
                </div>
            </div>
            <div class="form-group form-actions">
                <div class="col-xs-12">
                    <button type="submit" class="btn btn-effect-ripple btn-primary">Submit</button>
                    <button type="reset" class="btn btn-effect-ripple btn-danger">Reset</button>
                </div>
            </div>
        </form>
        <!-- END CKEditor Content -->
    </div>
    <!-- END CKEditor Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- ckeditor.js, load it only in the page you would like to use CKEditor (it's a heavy plugin to include it with the others!) -->
<script src="js/plugins/ckeditor/ckeditor.js"></script>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/formsComponents.js"></script>
<script>$(function(){ FormsComponents.init(); });</script>

<?php include 'inc/template_end.php'; ?>