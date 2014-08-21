<?php include 'inc/config.php'; $template['header_link'] = 'SOCIAL NET'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<!--
    Available classes when #page-content-sidebar is used:

    'inner-sidebar-left'      for a left inner sidebar
    'inner-sidebar-right'     for a right inner sidebar
-->
<div id="page-content" class="inner-sidebar-right">
    <!-- Inner Sidebar -->
    <div id="page-content-sidebar">
        <!-- Collapsible People List -->
        <a href="javascript:void(0)" class="btn btn-block btn-effect-ripple btn-primary visible-xs" data-toggle="collapse" data-target="#people-nav">People</a>
        <div id="people-nav" class="collapse navbar-collapse remove-padding">
            <div class="block-section">
                <h4 class="inner-sidebar-header">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-default pull-right"><i class="fa fa-cog"></i></a>
                    Online
                </h4>
                <ul class="nav-users nav-users-online">
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar6.jpg" alt="image" class="nav-users-avatar">
                            <span class="label label-success nav-users-indicator">5</span>
                            <span class="nav-users-heading">Olivia Rogers</span>
                            <span class="text-muted">Graphic Designer</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar5.jpg" alt="image" class="nav-users-avatar">
                            <span class="label label-success nav-users-indicator">3</span>
                            <span class="nav-users-heading">Joe Jones</span>
                            <span class="text-muted">Web Designer</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar12.jpg" alt="image" class="nav-users-avatar">
                            <span class="label label-success nav-users-indicator">2</span>
                            <span class="nav-users-heading">Nancy Cruz</span>
                            <span class="text-muted">Writer</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar4.jpg" alt="image" class="nav-users-avatar">
                            <span class="nav-users-heading">Ethan Hayes</span>
                            <span class="text-muted">Web Developer</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar10.jpg" alt="image" class="nav-users-avatar">
                            <span class="nav-users-heading">Julie Bennett</span>
                            <span class="text-muted">Photographer</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="block-section">
                <h4 class="inner-sidebar-header">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-default pull-right"><i class="fa fa-cog"></i></a>
                    Away
                </h4>
                <ul class="nav-users nav-users-away">
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar14.jpg" alt="image" class="nav-users-avatar">
                            <span class="nav-users-heading">Harold Green</span>
                            <span class="text-muted">Product Manager</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar15.jpg" alt="image" class="nav-users-avatar">
                            <span class="nav-users-heading">Alan George</span>
                            <span class="text-muted">Freelancer</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="block-section">
                <h4 class="inner-sidebar-header">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-default pull-right"><i class="fa fa-cog"></i></a>
                    Offline
                </h4>
                <ul class="nav-users nav-users-offline">
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar7.jpg" alt="image" class="nav-users-avatar">
                            <span class="nav-users-heading">Nathan Moore</span>
                            <span class="text-muted">UX Designer</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <img src="img/placeholders/avatars/avatar16.jpg" alt="image" class="nav-users-avatar">
                            <span class="nav-users-heading">Jason Gomez</span>
                            <span class="text-muted">CEO</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- END Collapsible People List -->
    </div>
    <!-- END Inner Sidebar -->

    <!-- Social Net Header -->
    <!-- For an image header add the class 'content-header-media' and an image as in the following example -->
    <div class="content-header content-header-media">
        <div class="header-section">
            <div class="row">
                <div class="col-sm-7 col-md-8 col-lg-9 content-float-hor push-bit-top-bottom clearfix">
                    <img src="img/placeholders/avatars/avatar9.jpg" alt="User Image" class="img-circle img-thumbnail img-thumbnail-avatar pull-left">
                    <h1>Jeremy Doe</h1>
                    <h2 class="text-light-op"> Web Designer, Tokyo</h2>
                </div>
                <div class="col-sm-5 col-md-4 col-lg-3 text-right push-bit-top-bottom">
                    <div class="row">
                        <div class="col-xs-6 col-sm-12">
                            <a href="javascript:void(0)" class="btn btn-block btn-effect-ripple btn-default"><i class="fa fa-plus"></i> Friend Request</a>
                        </div>
                        <div class="col-xs-6 col-sm-12">
                            <a href="javascript:void(0)" class="btn btn-block btn-effect-ripple btn-default"><i class="fa fa-share"></i> Follow</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- For best results use an image with a resolution of 2560x260 pixels (you could use a blurred image for smaller file size) -->
        <img src="img/placeholders/layout/socialnet_header.jpg" alt="Header Image" class="animation-pulseSlow">
    </div>
    <!-- END Social Net Header -->

    <!-- Social Net Content -->
    <div class="row">
        <!-- Latest News -->
        <div class="col-lg-7">
            <!-- New Photos -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background-dark text-light-op">
                    <span class="pull-right text-muted">just now</span>
                    <a href="javascript:void(0)">Dennis Franklin</a> uploaded <a href="javascript:void(0)">4 New Photos</a>
                </div>
                <div class="widget-content">
                    <div class="row">
                        <div class="col-md-3 push-bit">
                            <a href="img/placeholders/photos/photo5.jpg" data-toggle="lightbox-image" title="Image Info">
                                <img src="img/placeholders/photos/photo5.jpg" alt="Image">
                            </a>
                        </div>
                        <div class="col-md-3 push-bit">
                            <a href="img/placeholders/photos/photo7.jpg" data-toggle="lightbox-image" title="Image Info">
                                <img src="img/placeholders/photos/photo7.jpg" alt="Image">
                            </a>
                        </div>
                        <div class="col-md-3 push-bit">
                            <a href="img/placeholders/photos/photo1.jpg" data-toggle="lightbox-image" title="Image Info">
                                <img src="img/placeholders/photos/photo1.jpg" alt="Image">
                            </a>
                        </div>
                        <div class="col-md-3 push-bit">
                            <a href="img/placeholders/photos/photo2.jpg" data-toggle="lightbox-image" title="Image Info">
                                <img src="img/placeholders/photos/photo2.jpg" alt="Image">
                            </a>
                        </div>
                    </div>
                    <div class="push-bit-top">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-heart"></i> Love</a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-primary"><i class="fa fa-twitter"></i> Tweet</a>
                    </div>
                </div>
                <div class="widget-content themed-background-muted">
                    <form action="page_app_social.php" method="post" onsubmit="return false;">
                        <textarea class="form-control" rows="2" placeholder="Write your comment.."></textarea>
                        <button type="submit" class="btn btn-effect-ripple btn-sm btn-primary">Publish</button>
                    </form>
                </div>
            </div>
            <!-- END New Photos -->

            <!-- Story -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background-dark text-light-op">
                    <span class="pull-right text-muted">1 week ago</span>
                    <a href="javascript:void(0)">George Nelson</a> posted <a href="javascript:void(0)">1 New Article</a>
                </div>
                <div class="widget-content">
                    <h3>How to be more productive <small><a href="javascript:void(0)">Read More..</a></small></h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus.</p>
                    <p>Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices. Sed at mi velit. Ut egestas tempor est, in cursus enim venenatis eget!</p>
                    <div class="push-bit-top">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-heart"></i> Love</a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-primary"><i class="fa fa-twitter"></i> Tweet</a>
                    </div>
                </div>
                <div class="widget-content themed-background-muted">
                    <ul class="media-list push">
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar13.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <a href="javascript:void(0)"><strong>Madison Carr</strong></a>
                                <span class="text-muted"><small><em>50 min ago</em></small></span>
                                <p>Thank you for this, many great tips!</p>
                            </div>
                        </li>
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar3.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <a href="javascript:void(0)"><strong>Vincent Castillo</strong></a>
                                <span class="text-muted"><small><em>1 day ago</em></small></span>
                                <p>An awesome read, well done!</p>
                            </div>
                        </li>
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar9.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <form action="page_app_social.php" method="post" onsubmit="return false;">
                                    <textarea class="form-control" rows="2" placeholder="Write your comment.."></textarea>
                                    <button type="submit" class="btn btn-effect-ripple btn-sm btn-primary">Publish</button>
                                </form>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- END Story -->

            <!-- Map Checkin -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background-dark text-light-op">
                    <span class="pull-right text-muted">30 min ago</span>
                    <a href="javascript:void(0)">Laura Ross</a> checked in at <a href="javascript:void(0)">Bar-Cafe</a>
                </div>
                <div class="widget-content">
                    <div id="gmap-checkin" class="gmap" style="height: 360px;"></div>
                    <div class="push-bit-top">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-heart"></i> Love</a>
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-primary"><i class="fa fa-twitter"></i> Tweet</a>
                    </div>
                </div>
                <div class="widget-content themed-background-muted">
                    <form action="page_app_social.php" method="post" onsubmit="return false;">
                        <textarea class="form-control" rows="2" placeholder="Write your comment.."></textarea>
                        <button type="submit" class="btn btn-effect-ripple btn-sm btn-primary">Publish</button>
                    </form>
                </div>
            </div>
            <!-- END Map Checkin -->
        </div>
        <!-- END Latest News -->

        <!-- Extra Widgets -->
        <div class="col-lg-5">
            <!-- Birthday Widget -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background-warning text-light-op">
                    <span class="pull-right text-dark-op">2 People</span>
                    <i class="gi gi-cake fa-fw"></i> Birthday this week
                </div>
                <div class="widget-content widget-content-full">
                    <table class="table table-borderless table-striped table-vcenter">
                        <tbody>
                            <tr>
                                <td class="text-center" style="width: 100px;">
                                    <img src="img/placeholders/avatars/avatar14.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-warning">Dylan Cruz</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>29 years old on Friday</small></a>
                                </td>
                                <td class="text-center" style="width: 80px;">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-info" data-toggle="tooltip" title="Send a gift"><i class="fa fa-gift"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" style="width: 100px;">
                                    <img src="img/placeholders/avatars/avatar15.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-warning">Phillip Peters</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>34 years old tomorrow</small></a>
                                </td>
                                <td class="text-center" style="width: 80px;">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-info" data-toggle="tooltip" title="Send a gift"><i class="fa fa-gift"></i></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- END Birthday Widget -->

            <!-- People Widget -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background-info text-light-op">
                    <span class="pull-right text-dark-op">5 People</span>
                    <i class="fa fa-fw fa-users"></i> People you may know
                </div>
                <div class="widget-content widget-content-full">
                    <table class="table table-borderless table-striped table-vcenter">
                        <tbody>
                            <tr>
                                <td class="text-center" style="width: 100px;">
                                    <img src="img/placeholders/avatars/avatar13.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-info">Sarah Hart</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>5 common friends</small></a>
                                </td>
                                <td class="text-center" style="width: 80px;">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-success" data-toggle="tooltip" title="Friend Request"><i class="fa fa-plus"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger" data-toggle="tooltip" title="Follow"><i class="fa fa-share"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <img src="img/placeholders/avatars/avatar5.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-info">Mark Aguilar</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>2 common friends</small></a>
                                </td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-success" data-toggle="tooltip" title="Friend Request"><i class="fa fa-plus"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger" data-toggle="tooltip" title="Follow"><i class="fa fa-share"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <img src="img/placeholders/avatars/avatar12.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-info">Karen Jackson</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>3 common friends</small></a>
                                </td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-success" data-toggle="tooltip" title="Friend Request"><i class="fa fa-plus"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger" data-toggle="tooltip" title="Follow"><i class="fa fa-share"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <img src="img/placeholders/avatars/avatar1.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-info">Russell Larson</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>5 common friends</small></a>
                                </td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-success" data-toggle="tooltip" title="Friend Request"><i class="fa fa-plus"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger" data-toggle="tooltip" title="Follow"><i class="fa fa-share"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <img src="img/placeholders/avatars/avatar15.jpg" alt="User Image" class="img-circle">
                                </td>
                                <td>
                                    <a href="javascript:void(0)" class="text-info">Howard Adams</a><br>
                                    <a href="javascript:void(0)" class="text-muted"><small>5 common friends</small></a>
                                </td>
                                <td class="text-center">
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-success" data-toggle="tooltip" title="Friend Request"><i class="fa fa-plus"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-danger" data-toggle="tooltip" title="Follow"><i class="fa fa-share"></i></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- END People Widget -->

            <!-- Twitter Widget -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background text-light-op">
                    <a href="javascript:void(0)" class="pull-right text-dark-op"><i class="fa fa-refresh"></i></a>
                    <i class="fa fa-fw fa-twitter"></i> Twitter
                </div>
                <div class="widget-content">
                    <ul class="media-list">
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar2.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <span class="text-muted pull-right"><small><em>30 min ago</em></small></span>
                                <a href="javascript:void(0)"><strong>Howard Adams</strong></a>
                                <p>In hac <a href="javascript:void(0)">habitasse</a> platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend. <a href="javascript:void(0)" class="text-danger"><strong>#dev</strong></a></p>
                            </div>
                        </li>
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar9.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <span class="text-muted pull-right"><small><em>3 hours ago</em></small></span>
                                <a href="javascript:void(0)"><strong>Jeremy Doe</strong></a>
                                <p>Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam quis ligula elit. Aliquam at orci ac neque semper dictum.</p>
                            </div>
                        </li>
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar1.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <span class="text-muted pull-right"><small><em>yesterday</em></small></span>
                                <a href="javascript:void(0)"><strong>Russell Larson</strong></a>
                                <p>In hac habitasse platea dictumst. Proin ac nibh rutrum <a href="javascript:void(0)">lectus</a> rhoncus eleifend <a href="javascript:void(0)" class="text-danger"><strong>#design</strong></a></p>
                            </div>
                        </li>
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar12.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <span class="text-muted pull-right"><small><em>2 days ago</em></small></span>
                                <a href="javascript:void(0)"><strong>Nancy Kruz</strong></a>
                                <p>Donec lacinia venenatis metus at bibendum? In hac habitasse platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.</p>
                            </div>
                        </li>
                        <li class="media">
                            <a href="javascript:void(0)" class="pull-left">
                                <img src="img/placeholders/avatars/avatar13.jpg" alt="Avatar" class="img-circle">
                            </a>
                            <div class="media-body">
                                <span class="text-muted pull-right"><small><em>3 days ago</em></small></span>
                                <a href="javascript:void(0)"><strong>Madison Carr</strong></a>
                                <p>In hac habitasse platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend.</p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- END Twitter Widget -->

            <!-- Account Widget -->
            <div class="widget">
                <div class="widget-content widget-content-mini themed-background-danger text-light-op">
                    <a href="page_ready_pricing_tables.php" class="pull-right text-dark-op">Pro Plan</a>
                    <i class="fa fa-fw fa-paw"></i> Account Status
                </div>
                <div class="widget-content">
                    <!-- Mini Widgets -->
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="javascript:void(0)" class="widget text-center">
                                <div class="widget-content themed-background-muted text-center">
                                    <div class="widget-icon center-block push">
                                        <i class="fa fa-folder text-success"></i>
                                    </div>
                                    <strong class="text-dark-op">100GB</strong>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-6">
                            <a href="javascript:void(0)" class="widget text-center">
                                <div class="widget-content themed-background-muted text-center">
                                    <div class="widget-icon center-block push">
                                        <i class="fa fa-database text-danger"></i>
                                    </div>
                                    <strong class="text-dark-op">3 Databases</strong>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-6">
                            <a href="javascript:void(0)" class="widget text-center remove-margin-bottom">
                                <div class="widget-content themed-background-muted text-center">
                                    <div class="widget-icon center-block push">
                                        <i class="fa fa-envelope text-warning"></i>
                                    </div>
                                    <strong class="text-dark-op">2 Emails</strong>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-6">
                            <a href="javascript:void(0)" class="widget text-center remove-margin-bottom">
                                <div class="widget-content themed-background-muted text-center">
                                    <div class="widget-icon center-block push">
                                        <i class="fa fa-support text-info"></i>
                                    </div>
                                    <strong class="text-dark-op"><i class="fa fa-check"></i> Support</strong>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- END Mini Widgets -->
                </div>
            </div>
            <!-- END Account Widget -->
        </div>
        <!-- END Extra Widgets -->
    </div>
    <!-- END Social Net Content -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Google Maps API + Gmaps Plugin, must be loaded in the page you would like to use maps (Remove 'http:' if you have SSL) -->
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script src="js/plugins/gmaps.min.js"></script>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/appSocial.js"></script>
<script>$(function(){ AppSocial.init(); });</script>

<?php include 'inc/template_end.php'; ?>