<?php include 'inc/config.php'; $template['header_link'] = 'UI ELEMENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Widgets Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Widgets</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li><a href="">Widgets</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Widgets Header -->

    <!-- Widgets Content -->
    <div class="row">
        <div class="col-lg-6 col-lg-push-6">
            <div class="row">
                <!-- Image Widgets -->
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background text-light-op">
                            <i class="fa fa-fw fa-pencil"></i> <strong>Story</strong>
                        </div>
                        <div class="widget-image widget-image-sm">
                            <img src="img/placeholders/photos/photo9.jpg" alt="image">
                            <div class="widget-image-content">
                                <h2 class="widget-heading text-light"><strong>The autumn trip that changed my life</strong></h2>
                                <h3 class="widget-heading text-light-op h4">It changed the way I think</h3>
                            </div>
                            <i class="gi gi-airplane"></i>
                        </div>
                        <div class="widget-content text-dark">
                            <div class="row text-center">
                                <div class="col-xs-4">
                                    <i class="fa fa-heart-o"></i> 9.5k
                                </div>
                                <div class="col-xs-4">
                                    <i class="fa fa-eye"></i> 76k
                                </div>
                                <div class="col-xs-4">
                                    <i class="fa fa-share-alt"></i> 7.2k
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background text-light-op">
                            <i class="fa fa-fw fa-file-text"></i> <strong>Article</strong>
                        </div>
                        <div class="widget-image widget-image-sm">
                            <img src="img/placeholders/photos/photo19.jpg" alt="image">
                            <div class="widget-image-content">
                                <h2 class="widget-heading text-light"><strong>How to be more productive</strong></h2>
                                <h3 class="widget-heading text-light-op h4"><em>A step by step guide</em></h3>
                            </div>
                            <i class="gi gi-check"></i>
                        </div>
                        <div class="widget-content text-dark">
                            <div class="row text-center">
                                <div class="col-xs-4">
                                    <i class="fa fa-heart-o"></i> 3.5k
                                </div>
                                <div class="col-xs-4">
                                    <i class="fa fa-eye"></i> 35k
                                </div>
                                <div class="col-xs-4">
                                    <i class="fa fa-share-alt"></i> 1.5k
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <!-- END Image Widgets -->

                <!-- Stats Widgets -->
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background-danger text-light-op">
                            <i class="fa fa-fw fa-chevron-right"></i> <strong>New Favorites</strong>
                        </div>
                        <div class="widget-content themed-background-muted text-center">
                            <i class="fa fa-heart-o fa-3x text-danger"></i>
                        </div>
                        <div class="widget-content text-center">
                            <h3 class="widget-heading text-dark">+67%</h3>
                        </div>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background-success text-light-op">
                            <i class="fa fa-fw fa-chevron-right"></i> <strong>Balance</strong>
                        </div>
                        <div class="widget-content themed-background-muted text-center">
                            <i class="fa fa-dollar fa-3x text-success"></i>
                        </div>
                        <div class="widget-content text-center">
                            <h2 class="widget-heading text-dark">5,400</h2>
                        </div>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background-dark text-light-op">
                            <i class="fa fa-fw fa-chevron-right"></i> <strong>Active Tickets</strong>
                        </div>
                        <div class="widget-content themed-background-muted text-center">
                            <i class="fa fa-ticket fa-3x text-dark"></i>
                        </div>
                        <div class="widget-content text-center">
                            <h2 class="widget-heading text-dark">15</h2>
                        </div>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background-info text-light-op">
                            <i class="fa fa-fw fa-chevron-right"></i> <strong>New Facebooks Likes</strong>
                        </div>
                        <div class="widget-content themed-background-muted text-center">
                            <i class="fa fa-thumbs-o-up fa-3x text-info"></i>
                        </div>
                        <div class="widget-content text-center">
                            <h2 class="widget-heading text-dark">+75%</h2>
                        </div>
                    </a>
                </div>
                <!-- END Stats Widgets -->

                <!-- Carousel Widget -->
                <div class="col-sm-8">
                    <div class="widget">
                        <div class="widget-content widget-content-mini themed-background text-light-op">
                            <i class="fa fa-fw fa-picture-o"></i> Photo Gallery
                        </div>
                        <div class="widget-content widget-content-full">
                            <div id="info-carousel" class="carousel slide remove-margin">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#info-carousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#info-carousel" data-slide-to="1"></li>
                                    <li data-target="#info-carousel" data-slide-to="2"></li>
                                    <li data-target="#info-carousel" data-slide-to="3"></li>
                                </ol>
                                <!-- END Indicators -->

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img src="img/placeholders/photos/photo11.jpg" alt="image">
                                    </div>
                                    <div class="item">
                                        <img src="img/placeholders/photos/photo12.jpg" alt="image">
                                    </div>
                                    <div class="item">
                                        <img src="img/placeholders/photos/photo2.jpg" alt="image">
                                    </div>
                                    <div class="item">
                                        <img src="img/placeholders/photos/photo8.jpg" alt="image">
                                    </div>
                                </div>
                                <!-- END Wrapper for slides -->

                                <!-- Controls -->
                                <a class="left carousel-control" href="#info-carousel" data-slide="prev">
                                    <span><i class="fa fa-chevron-left"></i></span>
                                </a>
                                <a class="right carousel-control" href="#info-carousel" data-slide="next">
                                    <span><i class="fa fa-chevron-right"></i></span>
                                </a>
                                <!-- END Controls -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Carousel Widget -->

                <!-- Mini Widgets -->
                <div class="col-sm-4">
                    <a href="javascript:void(0)" class="widget text-center">
                        <div class="widget-content">
                            <i class="fa fa-3x fa-cloud-download text-dark"></i>
                        </div>
                        <div class="widget-content themed-background-muted text-dark">
                            <strong>127,000</strong><br>Downloads
                        </div>
                    </a>
                    <a href="javascript:void(0)" class="widget text-center">
                        <div class="widget-content themed-background-info text-light-op text-center">
                            <i class="fa fa-3x fa-facebook push-bit"></i><br>
                            <strong>800 Likes</strong>
                        </div>
                    </a>
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background-danger text-light-op text-center">
                            <i class="fa fa-3x fa-database push-bit"></i><br>
                            <strong>8/10 Databases</strong>
                        </div>
                    </a>
                </div>
                <!-- END Mini Widgets -->
            </div>
        </div>
        <div class="col-lg-6 col-lg-pull-6">
            <div class="row">
                <!-- Simple User Widgets -->
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content text-right clearfix">
                            <img src="img/placeholders/avatars/avatar9.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar pull-left">
                            <h2 class="widget-heading h3"><strong>Jeremy Doe</strong></h2>
                            <span class="text-muted">Web Designer</span>
                        </div>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content themed-background-danger clearfix">
                            <img src="img/placeholders/avatars/avatar12.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar pull-right">
                            <h2 class="widget-heading h3 text-light"><strong>Sara Wright</strong></h2>
                            <span class="text-light-op">Web Developer</span>
                        </div>
                    </a>
                </div>
                <!-- END Simple User Widgets -->

                <!-- Stats User Widgets -->
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content text-center">
                            <img src="img/placeholders/avatars/avatar6@2x.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar-2x">
                            <h2 class="widget-heading h3 text-muted">Grace Griffin</h2>
                        </div>
                        <div class="widget-content themed-background-muted text-dark text-center">
                            <strong>Photographer</strong>, Sydney
                        </div>
                        <div class="widget-content">
                            <div class="row text-center">
                                <div class="col-xs-6">
                                    <h3 class="widget-heading"><i class="gi gi-picture text-success"></i> <br><small>350 Photos</small></h3>
                                </div>
                                <div class="col-xs-6">
                                    <h3 class="widget-heading"><i class="gi gi-charts text-primary"></i> <br><small>4.2k Sales</small></h3>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a href="javascript:void(0)" class="widget">
                        <div class="widget-content text-center">
                            <img src="img/placeholders/avatars/avatar13@2x.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar-2x">
                            <h2 class="widget-heading h3 text-muted">Anna Wigren</h2>
                        </div>
                        <div class="widget-content themed-background-muted text-dark text-center">
                            <strong>Logo Designer</strong>, Sweden
                        </div>
                        <div class="widget-content">
                            <div class="row text-center">
                                <div class="col-xs-6">
                                    <h3 class="widget-heading"><i class="gi gi-briefcase text-info"></i> <br><small>35 Projects</small></h3>
                                </div>
                                <div class="col-xs-6">
                                    <h3 class="widget-heading"><i class="gi gi-heart_empty text-danger"></i> <br><small>5.3k Likes</small></h3>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <!-- END Stats User Widgets -->

                <!-- Media Widgets -->
                <div class="col-sm-12">
                    <div class="widget">
                        <div class="widget-image widget-image-xs">
                            <img src="img/placeholders/photos/photo13@2x.jpg" alt="image" class="animation-pulseSlow">
                            <div class="widget-image-content">
                                <h2 class="widget-heading text-light"><strong>TV Shows</strong></h2>
                                <h3 class="widget-heading text-light-op h4">You have to watch these shows</h3>
                            </div>
                            <i class="gi gi-play"></i>
                        </div>
                        <div class="widget-content widget-content-full">
                            <table class="table table-striped table-borderless remove-margin">
                                <tbody>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Game of Thrones</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2011</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Breakind Bad</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2008</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">True Detective</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2014</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Dexter</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2006</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Lost</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2004</span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="widget">
                        <div class="widget-image widget-image-xs">
                            <img src="img/placeholders/photos/photo18@2x.jpg" alt="image" class="animation-pulseSlow">
                            <div class="widget-image-content">
                                <h2 class="widget-heading text-light"><strong>Video Games</strong></h2>
                                <h3 class="widget-heading text-light-op h4">Favorite games you have to play</h3>
                            </div>
                            <i class="fa fa-gamepad"></i>
                        </div>
                        <div class="widget-content widget-content-full">
                            <table class="table table-striped table-borderless remove-margin">
                                <tbody>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Half Life 2</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2004</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Grand Theft Auto V</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2013</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Dark Souls 2</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2014</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Amnesia: The Dark Descent</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2010</span></td>
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)" class="text-black">Tomb Raider</a></td>
                                        <td class="text-center" style="width: 80px;"><span class="text-muted">2013</span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- END Media Widgets -->
            </div>
        </div>
    </div>
    <!-- END Widgets Content -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>
<?php include 'inc/template_end.php'; ?>