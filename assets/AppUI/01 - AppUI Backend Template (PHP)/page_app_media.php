<?php include 'inc/config.php'; $template['header_link'] = 'MEDIA BOX'; ?>
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
        <!-- Collapsible Options -->
        <a href="javascript:void(0)" class="btn btn-block btn-default visible-xs" data-toggle="collapse" data-target="#media-options">Media Box</a>
        <div id="media-options" class="collapse navbar-collapse remove-padding">
            <!-- Filter -->
            <div class="block-section">
                <h4 class="inner-sidebar-header">
                    <a href="javascript:void(0)" class="btn btn-xs btn-default pull-right"><i class="fa fa-plus"></i></a>
                    Filter
                </h4>
                <!-- Filter by Type links -->
                <!-- Custom files functionality is initialized in js/pages/appMedia.js -->
                <!-- Add the category value you want each link in .media-filter to filter out in its data-category attribute. Add the value 'all' to show all items -->
                <ul class="nav nav-pills nav-stacked nav-icons media-filter">
                    <li class="active">
                        <a href="javascript:void(0)" data-category="all">
                            <i class="fa fa-fw fa-folder text-primary icon-push"></i> <strong>All</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-category="video">
                            <i class="fa fa-fw fa-folder text-danger icon-push"></i> <strong>Videos</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-category="image">
                            <i class="fa fa-fw fa-folder text-success icon-push"></i> <strong>Images</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-category="audio">
                            <i class="fa fa-fw fa-folder text-info icon-push"></i> <strong>Audio</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-category="pdf">
                            <i class="fa fa-fw fa-folder text-warning icon-push"></i> <strong>Pdf</strong>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-category="zip">
                            <i class="fa fa-fw fa-folder text-muted icon-push"></i> <strong>Zip</strong>
                        </a>
                    </li>
                </ul>
                <!-- END Filter by Type links -->
            </div>
            <!-- END Filter -->

            <!-- Upload New File -->
            <div class="block-section">
                <h4 class="inner-sidebar-header">Upload New File</h4>
                <form action="page_app_media.php" class="dropzone"></form>
            </div>
            <!-- END Upload New File -->
        </div>
        <!-- END Collapsible Options -->
    </div>
    <!-- END Inner Sidebar -->

    <!-- Media Box Content -->
    <!-- Add the category value for each item in its data-category attribute (for the filter functionality to work) -->
    <div class="row media-filter-items">
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="zip">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-archive-o fa-5x text-muted"></i>
                </div>
                <h4>
                    <strong>Project</strong>.zip<br>
                    <small>3 hours ago, 3.2MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="image">
                <div class="media-items-options text-right">
                    <a href="img/placeholders/photos/photo1.jpg" class="btn btn-xs btn-info" data-toggle="lightbox-image">View</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-picture-o fa-5x text-success"></i>
                </div>
                <h4>
                    <strong>Mountains</strong>.jpg<br>
                    <small>4 hours ago, 7.5MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="video">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-video-o fa-5x text-danger"></i>
                </div>
                <h4>
                    <strong>Trip</strong>.mov<br>
                    <small>7 hours ago, 120MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="image">
                <div class="media-items-options text-right">
                    <a href="img/placeholders/photos/photo2.jpg" class="btn btn-xs btn-info" data-toggle="lightbox-image">View</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-picture-o fa-5x text-success"></i>
                </div>
                <h4>
                    <strong>Pizza</strong>.jpg<br>
                    <small>8 hours ago, 3.9MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="zip">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-archive-o fa-5x text-muted"></i>
                </div>
                <h4>
                    <strong>Freebies</strong>.zip<br>
                    <small>9 hours ago, 56.2MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="audio">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-audio-o fa-5x text-info"></i>
                </div>
                <h4>
                    <strong>Classical</strong>.mp3<br>
                    <small>11 hours ago, 3.3MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="image">
                <div class="media-items-options text-right">
                    <a href="img/placeholders/photos/photo7.jpg" class="btn btn-xs btn-info" data-toggle="lightbox-image">View</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-picture-o fa-5x text-success"></i>
                </div>
                <h4>
                    <strong>Road</strong>.jpg<br>
                    <small>12 hours ago, 4.4MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="zip">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-archive-o fa-5x text-muted"></i>
                </div>
                <h4>
                    <strong>FileArchive</strong>.zip<br>
                    <small>1 day ago, 530MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="image">
                <div class="media-items-options text-right">
                    <a href="img/placeholders/photos/photo3.jpg" class="btn btn-xs btn-info" data-toggle="lightbox-image">View</a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-picture-o fa-5x text-success"></i>
                </div>
                <h4>
                    <strong>Nature</strong>.jpg<br>
                    <small>1 day ago, 6.2MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="video">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-video-o fa-5x text-danger"></i>
                </div>
                <h4>
                    <strong>Rollercoaster</strong>.mov<br>
                    <small>2 days ago, 260MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="video">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-video-o fa-5x text-danger"></i>
                </div>
                <h4>
                    <strong>Sunset</strong>.mov<br>
                    <small>3 days ago, 76.9MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="pdf">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-pdf-o fa-5x text-warning"></i>
                </div>
                <h4>
                    <strong>Guide</strong>.pdf<br>
                    <small>4 days ago, 7.3MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="audio">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-audio-o fa-5x text-info"></i>
                </div>
                <h4>
                    <strong>Soundtrack</strong>.mp3<br>
                    <small>1 week ago</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="pdf">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-pdf-o fa-5x text-warning"></i>
                </div>
                <h4>
                    <strong>Tutorial</strong>.pdf<br>
                    <small>2 weeks ago, 9.1MB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="audio">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-audio-o fa-5x text-info"></i>
                </div>
                <h4>
                    <strong>Effect</strong>.wav<br>
                    <small>3 weeks ago, 250KB</small>
                </h4>
            </div>
        </div>
        <div class="col-sm-4 col-lg-3">
            <div class="media-items animation-fadeInQuick2" data-category="zip">
                <div class="media-items-options text-right">
                    <a href="javascript:void(0)" class="btn btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                    <a href="javascript:void(0)" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                </div>
                <div class="media-items-content">
                    <i class="fa fa-file-archive-o fa-5x text-muted"></i>
                </div>
                <h4>
                    <strong>Backup</strong>.zip<br>
                    <small>3 weeks ago, 14.9MB</small>
                </h4>
            </div>
        </div>
    </div>
    <!-- END Media Box Content -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/appMedia.js"></script>
<script>$(function(){ AppMedia.init(); });</script>

<?php include 'inc/template_end.php'; ?>