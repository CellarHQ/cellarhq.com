<?php include 'inc/config.php'; $template['header_link'] = 'UI ELEMENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Typography Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Typography</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Elements</li>
                        <li><a href="">Typography</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Typography Header -->

    <!-- Alert Messages Block -->
    <div class="block">
        <!-- Alert Messages Title -->
        <div class="block-title">
            <h2>Alert Messages</h2>
        </div>
        <!-- END Alert Messages Title -->

        <!-- Alert Messages Content -->
        <div class="row">
            <div class="col-sm-6 col-lg-3">
                <!-- Success Alert -->
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><strong>Success</strong></h4>
                    <p>The <a href="javascript:void(0)" class="alert-link">App</a> was updated successfully!</p>
                </div>
                <!-- END Success Alert -->
            </div>
            <div class="col-sm-6 col-lg-3">
                <!-- Info Alert -->
                <div class="alert alert-info alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><strong>Information</strong></h4>
                    <p>Just an information <a href="javascript:void(0)" class="alert-link">message</a>!</p>
                </div>
                <!-- END Info Alert -->
            </div>
            <div class="col-sm-6 col-lg-3">
                <!-- Warning Alert -->
                <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><strong>Warning</strong></h4>
                    <p>Please pay <a href="javascript:void(0)" class="alert-link">attention</a>!</p>
                </div>
                <!-- END Warning Alert -->
            </div>
            <div class="col-sm-6 col-lg-3">
                <!-- Danger Alert -->
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><strong>Error</strong></h4>
                    <p>Opps, an error <a href="javascript:void(0)" class="alert-link">occured</a>!</p>
                </div>
                <!-- END Danger Alert -->
            </div>
        </div>
        <!-- END Alert Messages Content -->
    </div>
    <!-- END Alert Messages Block -->

    <!-- Text Block -->
    <div class="block">
        <!-- Text Title -->
        <div class="block-title">
            <h2>Text</h2>
        </div>
        <!-- END Text Title -->

        <!-- Paragraphs and Links -->
        <div class="row">
            <div class="col-md-6">
                <p class="lead"><strong>Lead paragraph</strong></p>
                <p>Lorem ipsum dolor sit amet, <strong>consectetur adipiscing elit</strong>. Maecenas <a href="javascript:void(0)">ultrices</a>, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate.</p>
                <p><em>Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? <strong>Etiam</strong> egestas fringilla enim, id convallis lectus laoreet at. <a href="javascript:void(0)">Fusce</a> purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices. Sed at mi velit.</em></p>
            </div>
            <div class="col-md-6">
                <p class="lead"><strong>Lead paragraph</strong></p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula <strong>hendrerit</strong> nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus?</p>
                <p>Donec lacinia venenatis metus at bibendum? In hac habitasse platea dictumst. <a href="javascript:void(0)">Proin</a> ac nibh rutrum lectus rhoncus eleifend. Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam quis ligula elit. Aliquam at orci ac neque semper dictum. Sed tincidunt scelerisque ligula, et facilisis nulla hendrerit non. Suspendisse potenti. Pellentesque non accumsan orci. Praesent at lacinia dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </div>
        </div>
        <!-- END Paragraphs and Links -->
    </div>
    <!-- END Text Block -->

    <!-- Labels and Badges -->
    <div class="block full">
        <!-- Labels and Badges Title -->
        <div class="block-title">
            <h2>Labels &amp; Badges</h2>
        </div>
        <!-- END Labels and Badges Title -->

        <!-- Labels and Badges Content -->
        <h4 class="sub-header">Labels</h4>
        <div class="row">
            <div class="col-sm-4">
                <p>
                    <a href="javascript:void(0)" class="label label-default">Link</a>
                    <span class="label label-default">Default</span>
                    <span class="label label-default"><i class="fa fa-anchor"></i></span>
                </p>
                <p>
                    <a href="javascript:void(0)" class="label label-primary">Link</a>
                    <span class="label label-primary">Primary</span>
                    <span class="label label-primary"><i class="fa fa-upload"></i></span>
                </p>
            </div>
            <div class="col-sm-4">
                <p>
                    <a href="javascript:void(0)" class="label label-info">Link</a>
                    <span class="label label-info">Info</span>
                    <span class="label label-info"><i class="fa fa-info-circle"></i></span>
                </p>
                <p>
                    <a href="javascript:void(0)" class="label label-success">Link</a>
                    <span class="label label-success">Success</span>
                    <span class="label label-success"><i class="fa fa-check"></i></span>
                </p>
            </div>
            <div class="col-sm-4">
                <p>
                    <a href="javascript:void(0)" class="label label-warning">Link</a>
                    <span class="label label-warning">Warning</span>
                    <span class="label label-warning"><i class="fa fa-exclamation-triangle"></i></span>
                </p>
                <p>
                    <a href="javascript:void(0)" class="label label-danger">Link</a>
                    <span class="label label-danger">Danger</span>
                    <span class="label label-danger"><i class="fa fa-times"></i></span>
                </p>
            </div>
        </div>
        <h4 class="sub-header">Badges</h4>
        <p>
            <span class="badge">Badge</span>
            <span class="badge">30</span>
            <span class="badge"><i class="fa fa-exclamation-triangle"></i> 3</span>
            <span class="badge"><i class="fa fa-plane"></i></span>
            <span class="badge"><i class="fa fa-cog"></i></span>
            <span class="badge"><i class="fa fa-coffee"></i></span>
            <span class="badge"><i class="fa fa-dashboard"></i></span>
        </p>
        <!-- END Labels and Badges Content -->
    </div>
    <!-- END Labels and Badges Block -->

    <!-- Blockquotes -->
    <div class="row">
        <div class="col-md-6">
            <!-- Blockquote Left Block -->
            <div class="block full">
                <!-- Blockquote Left Title -->
                <div class="block-title">
                    <h4>Left Blockquote</h4>
                </div>
                <!-- END Blockquote Left Title -->

                <!-- Blockquote Left Content -->
                <div class="clearfix">
                    <blockquote class="pull-left">
                        <p>Be yourself; everyone else is already taken.</p>
                        <small>Oscar Wilde</small>
                    </blockquote>
                </div>
                <!-- END Blockquote Left Content -->
            </div>
            <!-- END Blockquote Left Block -->
        </div>
        <div class="col-md-6">
            <!-- Blockquote Right Block -->
            <div class="block full">
                <!-- Blockquote Right Title -->
                <div class="block-title">
                    <h4>Right Blockquote</h4>
                </div>
                <!-- END Blockquote Right Title -->

                <!-- Blockquote Right Content -->
                <div class="clearfix">
                    <blockquote class="pull-right">
                        <p>Don't cry because it's over, smile because it happened.</p>
                        <small>Dr. Seuss</small>
                    </blockquote>
                </div>
                <!-- END Blockquote Right Content -->
            </div>
            <!-- END Blockquote Right Block -->
        </div>
    </div>
    <!-- END Blockquotes -->

    <!-- Wells and Text Emphasis -->
    <div class="row">
        <div class="col-md-6">
            <!-- Wells Block -->
            <div class="block">
                <!-- Wells Title -->
                <div class="block-title">
                    <h2>Wells</h2>
                </div>
                <!-- END Wells Title -->

                <!-- Wells Content -->
                <p class="well well-sm"><strong>Small Well</strong> <code>.well</code> <code>.well-sm</code><br>Gives the paragraph an inset effect!</p>
                <p class="well"><strong>Normal Well</strong> <code>.well</code><br>Gives the paragraph an inset effect!</p>
                <p class="well well-lg"><strong>Large Well</strong> <code>.well</code> <code>.well-lg</code><br>Gives the paragraph an inset effect!</p>
                <!-- END Wells Content -->
            </div>
            <!-- END Wells Block -->
        </div>
        <div class="col-md-6">
            <!-- Text Emphasis Block -->
            <div class="block">
                <!-- Text Emphasis Title -->
                <div class="block-title">
                    <h2>Text Emphasis</h2>
                </div>
                <!-- END Text Emphasis Title -->

                <!-- Text Emphasis Content -->
                <table class="table table-borderless">
                    <tbody>
                        <tr>
                            <td><code>.text-primary</code></td>
                            <td><a href="javascript:void(0)">Link</a> - <span class="text-primary">Primary Text</span></td>
                        </tr>
                        <tr>
                            <td><code>.text-success</code></td>
                            <td><a href="javascript:void(0)" class="text-success">Link</a> - <span class="text-success">Success Text</span></td>
                        </tr>
                        <tr>
                            <td><code>.text-info</code></td>
                            <td><a href="javascript:void(0)" class="text-info">Link</a> - <span class="text-info">Info Text</span></td>
                        </tr>
                        <tr>
                            <td><code>.text-warning</code></td>
                            <td><a href="javascript:void(0)" class="text-warning">Link</a> - <span class="text-warning">Warning Text</span></td>
                        </tr>
                        <tr>
                            <td><code>.text-danger</code></td>
                            <td><a href="javascript:void(0)" class="text-danger">Link</a> - <span class="text-danger">Danger Text</span></td>
                        </tr>
                        <tr>
                            <td><code>.text-muted</code></td>
                            <td><a href="javascript:void(0)" class="text-muted">Link</a> - <span class="text-muted">Muted Text</span></td>
                        </tr>
                    </tbody>
                </table>
                <!-- END Text Emphasis Content -->
            </div>
            <!-- END Text Emphasis Block -->
        </div>
    </div>
    <!-- END Wells and Text Emphasis -->

    <!-- Lists Row -->
    <div class="row">
        <div class="col-md-6 col-lg-3">
            <!-- Unordered List Block -->
            <div class="block full">
                <!-- Unordered List Title -->
                <div class="block-title">
                    <h2>Unordered List</h2>
                </div>
                <!-- END Unordered List Title -->

                <!-- Unordered List Content -->
                <ul>
                    <li>First item</li>
                    <li>Second item</li>
                    <li>
                        Sublist
                        <ul>
                            <li>First subitem</li>
                            <li>Second subitem</li>
                            <li>Third subitem</li>
                        </ul>
                    </li>
                    <li>Third item</li>
                </ul>
                <!-- END Unordered List Content -->
            </div>
            <!-- END Unordered List Block -->
        </div>
        <div class="col-md-6 col-lg-3">
            <!-- Ordered List Block -->
            <div class="block full">
                <!-- Ordered List Title -->
                <div class="block-title">
                    <h2>Ordered List</h2>
                </div>
                <!-- END Ordered List Title -->

                <!-- Ordered List Content -->
                <ol>
                    <li>First item</li>
                    <li>Second item</li>
                    <li>
                        Sublist
                        <ol>
                            <li>First subitem</li>
                            <li>Second subitem</li>
                            <li>Third subitem</li>
                        </ol>
                    </li>
                    <li>Third item</li>
                </ol>
                <!-- END Ordered List Content -->
            </div>
            <!-- END Ordered List Block -->
        </div>
        <div class="col-md-6 col-lg-3">
            <!-- Icon List Block -->
            <div class="block full">
                <!-- Icon List Title -->
                <div class="block-title">
                    <h2>Icon List</h2>
                </div>
                <!-- END Icon List Title -->

                <!-- Icon List Content -->
                <ul class="fa-ul">
                    <li><i class="fa fa-check fa-li"></i>First item</li>
                    <li><i class="fa fa-check fa-li"></i>Second item</li>
                    <li>
                        <i class="fa fa-check fa-li"></i>Sublist
                        <ul class="fa-ul">
                            <li><i class="fa fa-arrow-right fa-li"></i>First subitem</li>
                            <li><i class="fa fa-arrow-right fa-li"></i>Second subitem</li>
                            <li><i class="fa fa-arrow-right fa-li"></i>Third subitem</li>
                        </ul>
                    </li>
                    <li><i class="fa fa-check fa-li"></i>Third item</li>
                </ul>
                <!-- END Icon List Content -->
            </div>
            <!-- END Icon List Block -->
        </div>
        <div class="col-md-6 col-lg-3">
            <!-- Unstyled List Block -->
            <div class="block full">
                <!-- Unstyled List Title -->
                <div class="block-title">
                    <h2>Unstyled List</h2>
                </div>
                <!-- END Unstyled List Title -->

                <!-- Unstyled List Content -->
                <ul class="list-unstyled">
                    <li>First item</li>
                    <li>Second item</li>
                    <li>
                        Sublist
                        <ul class="list-unstyled">
                            <li>First subitem</li>
                            <li>Second subitem</li>
                            <li>Third subitem</li>
                        </ul>
                    </li>
                    <li>Third item</li>
                </ul>
                <!-- END Unstyled List Content -->
            </div>
            <!-- END Unstyled List Block -->
        </div>
    </div>
    <!-- END Lists Row -->

    <!-- Headings Block -->
    <div class="block full">
        <!-- Headings Title -->
        <div class="block-title">
            <div class="block-options pull-right">
                <!-- Changing class functionality is initialized in js/pages/uiTypography.js -->
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-headings-page" data-toggle="tooltip" title="Toggle class">.page-header</a>
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default toggle-headings-sub" data-toggle="tooltip" title="Toggle class">.sub-header</a>
            </div>
            <h2>Headings</h2>
        </div>
        <!-- END Headings Title -->

        <!-- Headings Content -->
        <div class="row headings-container">
            <div class="col-sm-6">
                <h1>h1. Heading <small>Subtext</small></h1>
                <h2>h2. Heading <small>Subtext</small></h2>
                <h3>h3. Heading <small>Subtext</small></h3>
                <h4>h4. Heading <small>Subtext</small></h4>
                <h5>h5. Heading <small>Subtext</small></h5>
                <h6>h6. Heading <small>Subtext</small></h6>
            </div>
            <div class="col-sm-6">
                <h1 class="text-success">h1. Heading <small>Subtext</small></h1>
                <h2 class="text-info">h2. Heading <small>Subtext</small></h2>
                <h3 class="text-primary">h3. Heading <small>Subtext</small></h3>
                <h4 class="text-danger">h4. Heading <small>Subtext</small></h4>
                <h5 class="text-warning">h5. Heading <small>Subtext</small></h5>
                <h6 class="text-muted">h6. Heading <small>Subtext</small></h6>
            </div>
        </div>
        <!-- END Headings Content -->
    </div>
    <!-- END Headings Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/uiTypography.js"></script>
<script>$(function(){ UiTypography.init(); });</script>

<?php include 'inc/template_end.php'; ?>