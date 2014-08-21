<?php include 'inc/config.php'; $template['header_link'] = 'EMAIL CENTER'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<!--
    Available classes when #page-content-sidebar is used:

    'inner-sidebar-left'      for a left inner sidebar
    'inner-sidebar-right'     for a right inner sidebar
-->
<div id="page-content" class="inner-sidebar-left">
    <!-- Inner Sidebar -->
    <div id="page-content-sidebar">
        <!-- Compose Message -->
        <div class="block-section">
            <a href="#modal-compose" class="btn btn-effect-ripple btn-block btn-success" data-toggle="modal"><i class="fa fa-pencil"></i> Compose Message</a>
        </div>
        <div id="modal-compose" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h3 class="modal-title"><strong>Compose Message</strong></h3>
                    </div>
                    <div class="modal-body">
                        <form action="page_app_email.php" method="post" class="form-horizontal form-bordered" onsubmit="return false;">
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <select id="fcompose-users" name="fcompose-users" class="select-chosen" data-placeholder="To.." style="width: 250px;" multiple="">
                                        <option></option><!-- Required for data-placeholder attribute to work with Chosen plugin -->
                                        <option value="john.doe">John Taylor</option>
                                        <option value="sara.castillo">Sara Castillo</option>
                                        <option value="rachel.fox">Rachel Fox</option>
                                        <option value="vincent.johnson">Vincent Johnson</option>
                                        <option value="russell.perkins">Russell Perkins</option>
                                        <option value="denise.hill">Denise Hill</option>
                                        <option value="helen.gomez">Helen Gomez</option>
                                        <option value="marie.herrera">Marie Herrera</option>
                                        <option value="charles.morris">Charles Morris</option>
                                        <option value="janice.contreras">Janice Contreras</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <textarea id="fcompose-message" name="fcompose-message" rows="7" class="form-control" placeholder="Write your message.."></textarea>
                                </div>
                            </div>
                            <div class="form-group form-actions">
                                <div class="col-xs-12 text-right">
                                    <button type="submit" class="btn btn-effect-ripple btn-primary">Send</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- END Compose Message -->

        <!-- Collapsible Navigation -->
        <a href="javascript:void(0)" class="btn btn-block btn-effect-ripple btn-default visible-xs" data-toggle="collapse" data-target="#email-nav">Navigation</a>
        <div id="email-nav" class="collapse navbar-collapse remove-padding">
            <!-- Menu -->
            <div class="block-section">
                <ul class="nav nav-pills nav-stacked">
                    <li class="active">
                        <a href="page_app_email.php">
                            <i class="fa fa-fw fa-inbox icon-push"></i> <strong>Inbox</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-star icon-push"></i> <strong>Starred</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-exclamation-circle icon-push"></i> <strong>Important</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-send icon-push"></i> <strong>Sent</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-pencil-square-o icon-push"></i> <strong>Draft</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-folder icon-push"></i> <strong>Archive</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-trash-o icon-push"></i> <strong>Trash</strong>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- END Menu -->

            <!-- Labels -->
            <div class="block-section">
                <h4 class="inner-sidebar-header">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-xs btn-default pull-right"><i class="fa fa-plus"></i></a>
                    Labels
                </h4>
                <ul class="nav nav-pills nav-stacked nav-icons">
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-circle icon-push text-info"></i> <strong>Later</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-circle icon-push text-warning"></i> <strong>Family</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-circle icon-push text-danger"></i> <strong>Projects</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa fa-fw fa-circle icon-push text-success"></i> <strong>Work</strong>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- END Labels -->
        </div>
        <!-- END Collapsible Navigation -->
    </div>
    <!-- END Inner Sidebar -->

    <!-- Email Center Content -->
    <div class="block overflow-hidden">
        <!-- Message List -->
        <div id="message-list">
            <!-- Title -->
            <div class="block-title clearfix">
                <div class="block-options pull-right">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default"><i class="fa fa-arrow-left"></i> Prev</a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default">Next <i class="fa fa-arrow-right"></i></a>
                </div>
                <div class="block-options pull-left">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-info" data-toggle="tooltip" title="Archive Selected"><i class="fa fa-briefcase"></i></a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-warning" data-toggle="tooltip" title="Star Selected"><i class="fa fa-star"></i></a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete Selected"><i class="fa fa-times"></i></a>
                </div>
            </div>
            <!-- END Title -->

            <!-- Messages -->
            <div class="block-content-full">
                <table class="table table-borderless table-striped table-vcenter remove-margin">
                    <tbody>
                        <!-- Use the first row as a prototype for your column widths -->
                        <tr>
                            <td class="td-label td-label-muted text-center" style="width: 5%;">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center" style="width: 7%;">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4>
                                    <a href="javascript:void(0)" class="text-dark"><strong>Product updates</strong></a>
                                </h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs text-center" style="width: 30px;">
                                <i class="fa fa-paperclip fa-2x text-muted"></i>
                            </td>
                            <td class="hidden-xs text-right text-muted" style="width: 120px;"><em>just now</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-danger text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>New friend request</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs">
                                <i class="fa fa-paperclip fa-2x text-muted"></i>
                            </td>
                            <td class="hidden-xs text-right text-muted"><em>15 min ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-success text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4>
                                    <a href="javascript:void(0)" class="text-dark"><strong>New project details</strong></a>
                                </h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>40 min ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-success text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>Hi admin</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>1 hour ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-warning text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>Balance changed</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>2 hours ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-info text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>Just wanted to say hi</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>5 hours ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-info text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>Building a new website</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>10 hours ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-danger text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4>
                                    <a href="javascript:void(0)" class="text-dark"><strong>Your subscription was updated</strong></a>
                                </h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs">
                                <i class="fa fa-paperclip fa-2x text-muted"></i>
                            </td>
                            <td class="hidden-xs text-right text-muted"><em>2 days ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-danger text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>A great opportunity</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>1 week ago</em></td>
                        </tr>
                        <tr>
                            <td class="td-label td-label-success text-center">
                                <label class="csscheckbox csscheckbox-primary"><input type="checkbox"><span></span></label>
                            </td>
                            <td class="text-center">
                                <img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle">
                            </td>
                            <td>
                                <h4><a href="javascript:void(0)" class="text-dark"><strong>Account Activation</strong></a></h4>
                                <span class="text-muted">This is the preview text of this message..</span>
                            </td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-right text-muted"><em>2 weeks ago</em></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- END Messages -->
        </div>
        <!-- END Message List -->

        <!-- Message View -->
        <div id="message-view" class="block-section display-none">
            <!-- Title -->
            <div class="block-title clearfix">
                <div class="block-options pull-right">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-warning" data-toggle="tooltip" title="Star"><i class="fa fa-star"></i></a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                </div>
                <div class="block-options pull-left">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" id="message-view-back"><i class="fa fa-chevron-left"></i> Back to Inbox</a>
                </div>
            </div>
            <!-- END Title -->

            <!-- Header -->
            <h3><strong>Project updates </strong> <small><em>1 week ago</em></small></h3>
            <p><a href="javascript:void(0)"><strong>John Doe</strong></a> <strong>&lt;john.doe@example.com&gt;</strong> to <a href="javascript:void(0)"><strong>Admin</strong></a> <strong>&lt;admin@example.com&gt;</strong></p>
            <!-- END Header -->

            <!-- Message Body -->
            <hr>
            <p>Hi,</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices. Sed at mi velit. Ut egestas tempor est, in cursus enim venenatis eget! Nulla quis ligula ipsum. Donec vitae ultrices dolor?</p>
            <p>Donec lacinia venenatis metus at bibendum? In hac habitasse platea dictumst. Proin ac nibh rutrum lectus rhoncus eleifend. Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam quis ligula elit. Aliquam at orci ac neque semper dictum. Sed tincidunt scelerisque ligula, et facilisis nulla hendrerit non. Suspendisse potenti. Pellentesque non accumsan orci. Praesent at lacinia dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ultrices, justo vel imperdiet gravida, urna ligula hendrerit nibh, ac cursus nibh sapien in purus. Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices. Sed at mi velit. Ut egestas tempor est, in cursus enim venenatis eget! Nulla quis ligula ipsum. Donec vitae ultrices dolor?</p>
            <p>Best Regards,</p>
            <p>John</p>
            <hr>
            <!-- END Message Body -->

            <!-- Attachments Row -->
            <div class="row block-section">
                <div class="col-xs-6 col-sm-3 col-lg-2 text-center">
                    <a href="img/placeholders/photos/photo2.jpg" data-toggle="lightbox-image">
                        <img src="img/placeholders/photos/photo2.jpg" alt="photo" class="img-responsive push-bit">
                    </a>
                    <span class="text-muted">IMG0001.JPG</span>
                </div>
                <div class="col-xs-6 col-sm-3 col-lg-2 text-center">
                    <a href="img/placeholders/photos/photo16.jpg" data-toggle="lightbox-image">
                        <img src="img/placeholders/photos/photo16.jpg" alt="photo" class="img-responsive push-bit">
                    </a>
                    <span class="text-muted">IMG0002.JPG</span>
                </div>
                <div class="col-xs-6 col-sm-3 col-lg-2 text-center">
                    <a href="img/placeholders/photos/photo9.jpg" data-toggle="lightbox-image">
                        <img src="img/placeholders/photos/photo9.jpg" alt="photo" class="img-responsive push-bit">
                    </a>
                    <span class="text-muted">IMG0003.JPG</span>
                </div>
                <div class="col-xs-6 col-sm-3 col-lg-2 text-center">
                    <a href="img/placeholders/photos/photo15.jpg" data-toggle="lightbox-image">
                        <img src="img/placeholders/photos/photo15.jpg" alt="photo" class="img-responsive push-bit">
                    </a>
                    <span class="text-muted">IMG0004.JPG</span>
                </div>
            </div>
            <!-- END Attachments Row -->

            <!-- Quick Reply Form -->
            <form action="page_app_email.php" method="post" onsubmit="return false;">
                <textarea id="message-quick-reply" name="message-quick-reply" rows="5" class="form-control push-bit" placeholder="Your message.."></textarea>
                <button class="btn btn-effect-ripple btn-primary"><i class="fa fa-share"></i> Reply</button>
            </form>
            <!-- END Quick Reply Form -->
        </div>
        <!-- END Message View -->
    </div>
    <!-- END Email Center Content -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/appEmail.js"></script>
<script>$(function(){ AppEmail.init(); });</script>

<?php include 'inc/template_end.php'; ?>