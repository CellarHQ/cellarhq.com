<?php include 'inc/config.php'; $template['header_link'] = 'UI ELEMENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Navigation - Extras Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Navigation &amp; More</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>User Interface</li>
                        <li>Elements</li>
                        <li><a href="">Navigation &amp; More</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Navigation - Extras Header -->

    <!-- Sidebars Row -->
    <div class="row">
        <div class="col-sm-6">
            <!-- Main Sidebar Block -->
            <div class="block full">
                <!-- Main Sidebar Title -->
                <div class="block-title">
                    <h2>Main Sidebar</h2>
                </div>
                <!-- END Main Sidebar Title -->

                <!-- Main Sidebar Content -->
                <button class="btn btn-effect-ripple btn-success" onclick="App.sidebar('open-sidebar');">Open</button>
                <button class="btn btn-effect-ripple btn-danger" onclick="App.sidebar('close-sidebar');">Close</button>
                <button class="btn btn-effect-ripple btn-primary" onclick="App.sidebar('toggle-sidebar');">Toggle</button>
                <!-- END Main Sidebar Content -->
            </div>
            <!-- END Main Sidebar Block -->
        </div>
        <div class="col-sm-6">
            <!-- Alternative Sidebar Block -->
            <div class="block full">
                <!-- Alternative Sidebar Title -->
                <div class="block-title">
                    <h2>Alternative Sidebar</h2>
                </div>
                <!-- END Alternative Sidebar Title -->

                <!-- Alternative Sidebar Content -->
                <button class="btn btn-effect-ripple btn-success" onclick="App.sidebar('open-sidebar-alt');">Open</button>
                <button class="btn btn-effect-ripple btn-danger" onclick="App.sidebar('close-sidebar-alt');">Close</button>
                <button class="btn btn-effect-ripple btn-primary" onclick="App.sidebar('toggle-sidebar-alt');">Toggle</button>
                <!-- END Alternative Sidebar Content -->
            </div>
            <!-- END Alternative Sidebar Block -->
        </div>
    </div>
    <!-- END Sidebars Row -->

    <!-- Various Carousels Row -->
    <div class="row">
        <div class="col-sm-4">
            <!-- Info Carousel Block -->
            <div class="block">
                <!-- Info Carousel Title -->
                <div class="block-title">
                    <h2>Info</h2>
                </div>
                <!-- END Info Carousel Title -->

                <!-- Info Carousel Content -->
                <div class="block-content-full">
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
                                <img src="img/placeholders/photos/photo8.jpg" alt="image">
                                <div class="carousel-caption">
                                    <h3><strong>City by Night</strong></h3>
                                </div>
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo7.jpg" alt="image">
                                <div class="carousel-caption">
                                    <h3><strong>Lonely Road</strong></h3>
                                </div>
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo11.jpg" alt="image">
                                <div class="carousel-caption">
                                    <h3><strong>Yummy Pizza</strong></h3>
                                </div>
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo10.jpg" alt="image">
                                <div class="carousel-caption">
                                    <h3><strong>City in the Clouds</strong></h3>
                                </div>
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
                <!-- END Info Carousel Content -->
            </div>
            <!-- END Info Carousel Block -->
        </div>
        <div class="col-sm-4">
            <!-- Simple Carousel Block -->
            <div class="block">
                <!-- Simple Carousel Title -->
                <div class="block-title">
                    <h2>Simple</h2>
                </div>
                <!-- END Simple Carousel Title -->

                <!-- Simple Carousel Content -->
                <div class="block-content-full">
                    <div id="simple-carousel" class="carousel remove-margin">
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="img/placeholders/photos/photo11.jpg" alt="image">
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo14.jpg" alt="image">
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo2.jpg" alt="image">
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo15.jpg" alt="image">
                            </div>
                        </div>
                        <!-- END Wrapper for slides -->

                        <!-- Controls -->
                        <a class="left carousel-control" href="#simple-carousel" data-slide="prev">
                            <span><i class="fa fa-chevron-left"></i></span>
                        </a>
                        <a class="right carousel-control" href="#simple-carousel" data-slide="next">
                            <span><i class="fa fa-chevron-right"></i></span>
                        </a>
                        <!-- END Controls -->
                    </div>
                </div>
                <!-- END Simple Carousel Content -->
            </div>
            <!-- END Simple Carousel Block -->
        </div>
        <div class="col-sm-4">
            <!-- Animation and Auto Slide Carousel Block -->
            <div class="block">
                <!-- Animation and Auto Slide Carousel Title -->
                <div class="block-title">
                    <h2>Animation &amp; Auto Slide</h2>
                </div>
                <!-- END Animation and Auto Slide Carousel Title -->

                <!-- Animation and Auto Slide Carousel Content -->
                <div class="block-content-full">
                    <div id="animation-carousel" class="carousel slide remove-margin" data-ride="carousel" data-interval="1500">
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="img/placeholders/photos/photo18.jpg" alt="image">
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo19.jpg" alt="image">
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo21.jpg" alt="image">
                            </div>
                            <div class="item">
                                <img src="img/placeholders/photos/photo7.jpg" alt="image">
                            </div>
                        </div>
                        <!-- END Wrapper for slides -->

                        <!-- Controls -->
                        <a class="left carousel-control" href="#animation-carousel" data-slide="prev">
                            <span><i class="fa fa-chevron-left"></i></span>
                        </a>
                        <a class="right carousel-control" href="#animation-carousel" data-slide="next">
                            <span><i class="fa fa-chevron-right"></i></span>
                        </a>
                        <!-- END Controls -->
                    </div>
                </div>
                <!-- END Animation and Auto Slide Carousel Content -->
            </div>
            <!-- END Animation and Auto Slide Carousel Block -->
        </div>
    </div>
    <!-- END Various Carousels Row -->

    <!-- Nav Row -->
    <div class="row">
        <div class="col-md-6">
            <!-- List Groups Block -->
            <div class="block">
                <!-- List Groups Title -->
                <div class="block-title">
                    <h2>List Groups</h2>
                </div>
                <!-- END List Groups Title -->

                <!-- List Groups Content -->
                <div class="list-group">
                    <a href="javascript:void(0)" class="list-group-item active">
                        <span class="badge"><i class="fa fa-fw fa-inbox"></i></span>
                        <h4 class="list-group-item-heading"><strong>Inbox</strong></h4>
                        <p class="list-group-item-text">Your new messages</p>
                    </a>
                    <a href="javascript:void(0)" class="list-group-item">
                        <span class="badge"><i class="fa fa-fw fa-send"></i></span>
                        <h4 class="list-group-item-heading"><strong>Sent</strong></h4>
                        <p class="list-group-item-text">Everything you've sent it's here</p>
                    </a>
                    <a href="javascript:void(0)" class="list-group-item">
                        <span class="badge"><i class="fa fa-fw fa-archive"></i></span>
                        <h4 class="list-group-item-heading"><strong>Archive</strong></h4>
                        <p class="list-group-item-text">All your archived messages</p>
                    </a>
                    <a href="javascript:void(0)" class="list-group-item">
                        <span class="badge"><i class="fa fa-fw fa-trash-o"></i></span>
                        <h4 class="list-group-item-heading"><strong>Trash</strong></h4>
                        <p class="list-group-item-text">All your deleted messages</p>
                    </a>
                </div>
                <div class="list-group">
                    <a href="javascript:void(0)" class="list-group-item active">
                        <span class="badge">9</span>
                        <h4 class="list-group-item-heading"><strong>Inbox</strong></h4>
                    </a>
                    <a href="javascript:void(0)" class="list-group-item">
                        <span class="badge">2.5k</span>
                        <h4 class="list-group-item-heading"><strong>Sent</strong></h4>
                    </a>
                    <a href="javascript:void(0)" class="list-group-item">
                        <span class="badge">3k</span>
                        <h4 class="list-group-item-heading"><strong>Archive</strong></h4>
                    </a>
                    <a href="javascript:void(0)" class="list-group-item">
                        <span class="badge">150</span>
                        <h4 class="list-group-item-heading"><strong>Trash</strong></h4>
                    </a>
                </div>
                <!-- END List Groups Content -->
            </div>
            <!-- END List Groups Block -->

            <!-- Pagination Block -->
            <div class="block">
                <!-- Pagination Title -->
                <div class="block-title">
                    <h2>Pagination</h2>
                </div>
                <!-- END Pagination Title -->

                <!-- Default Pagination and States -->
                <ul class="pagination">
                    <li class="disabled"><a href="javascript:void(0)">Prev</a></li>
                    <li class="active"><a href="javascript:void(0)">1</a></li>
                    <li><a href="javascript:void(0)">2</a></li>
                    <li><a href="javascript:void(0)">3</a></li>
                    <li><a href="javascript:void(0)">Next</a></li>
                </ul>
                <!-- END Default Pagination and States -->

                <!-- Pagination Sizes -->
                <h4 class="sub-header">Sizes</h4>
                <div>
                    <ul class="pagination pagination-sm">
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a href="javascript:void(0)">1</a></li>
                        <li class="active"><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </div>
                <div>
                    <ul class="pagination">
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a href="javascript:void(0)">1</a></li>
                        <li class="active"><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </div>
                <div>
                    <ul class="pagination pagination-lg">
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a href="javascript:void(0)">1</a></li>
                        <li class="active"><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </div>
                <!-- END Pagination Sizes -->

                <!-- Pagination Alignment -->
                <h4 class="sub-header">Alignments</h4>
                <div>
                    <ul class="pagination">
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a href="javascript:void(0)">1</a></li>
                        <li class="active"><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </div>
                <div class="text-center">
                    <ul class="pagination">
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a href="javascript:void(0)">1</a></li>
                        <li class="active"><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </div>
                <div class="text-right">
                    <ul class="pagination">
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a href="javascript:void(0)">1</a></li>
                        <li class="active"><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </div>
                <!-- END Pagination Alignment -->
            </div>
            <!-- END Pagination Block -->
        </div>
        <div class="col-md-6">
            <!-- Popovers, Tooltips and Modals Block -->
            <div class="block">
                <!-- Popovers, Tooltips and Modals Title -->
                <div class="block-title">
                    <h2>Popovers, Tooltips &amp; Modals</h2>
                </div>
                <!-- END Popovers, Tooltips and Modals Title -->

                <!-- Popovers, you can also use the class 'enable-popover' instead of data-toggle attribute -->
                <div class="block-section">
                    <button class="btn btn-effect-ripple btn-default" data-toggle="popover" data-content="Content.." title="Title">Popover</button>
                    <button class="btn btn-effect-ripple btn-primary" data-toggle="popover" data-content="Content.." data-placement="top" title="Title"><i class="fa fa-chevron-up fa-fw"></i></button>
                    <button class="btn btn-effect-ripple btn-primary" data-toggle="popover" data-content="Content.." data-placement="right" title="Title"><i class="fa fa-chevron-right fa-fw"></i></button>
                    <button class="btn btn-effect-ripple btn-primary" data-toggle="popover" data-content="Content.." data-placement="bottom" title="Title"><i class="fa fa-chevron-down fa-fw"></i></button>
                    <button class="btn btn-effect-ripple btn-primary" data-toggle="popover" data-content="Content.." data-placement="left" title="Title"><i class="fa fa-chevron-left fa-fw"></i></button>
                </div>
                <!-- END Popovers -->

                <!-- Tooltips, you can also use the class 'enable-tooltip' instead of data-toggle attribute -->
                <div class="block-section">
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Tooltip">Tooltip</a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="top" title="Top Tooltip"><i class="fa fa-chevron-up fa-fw"></i></a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="right" title="Right Tooltip"><i class="fa fa-chevron-right fa-fw"></i></a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="bottom" title="Bottom Tooltip"><i class="fa fa-chevron-down fa-fw"></i></a>
                    <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="left" title="Left Tooltip"><i class="fa fa-chevron-left fa-fw"></i></a>
                </div>
                <!-- END Tooltips -->

                <!-- Modals -->
                <!-- For advanced modal usage you can check out http://getbootstrap.com/javascript/#modals -->
                <div class="block-section">
                    <!-- Modal links -->
                    <a href="#modal-regular" class="btn btn-effect-ripple btn-default" data-toggle="modal">Modal</a>
                    <a href="#modal-small" class="btn btn-effect-ripple btn-primary" data-toggle="modal">Small</a>
                    <a href="#modal-large" class="btn btn-effect-ripple btn-primary" data-toggle="modal">Large</a>
                    <a href="#modal-fade" class="btn btn-effect-ripple btn-danger" data-toggle="modal">Fade In</a>
                    <a href="#modal-tabs" class="btn btn-effect-ripple btn-warning" data-toggle="modal">Tabs</a>
                </div>
                <!-- END Modals -->

                <!-- Regular Modal -->
                <div id="modal-regular" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3 class="modal-title"><strong>Modal</strong></h3>
                            </div>
                            <div class="modal-body">
                                Content..
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-effect-ripple btn-primary">Save</button>
                                <button type="button" class="btn btn-effect-ripple btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Regular Modal -->

                <!-- Small Modal -->
                <div id="modal-small" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3 class="modal-title"><strong>Small Modal</strong></h3>
                            </div>
                            <div class="modal-body">
                                Content..
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-effect-ripple btn-primary">Save</button>
                                <button type="button" class="btn btn-effect-ripple btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Small Modal -->

                <!-- Large Modal -->
                <div id="modal-large" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3 class="modal-title"><strong>Large Modal</strong></h3>
                            </div>
                            <div class="modal-body">
                                Content..
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-effect-ripple btn-primary">Save</button>
                                <button type="button" class="btn btn-effect-ripple btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Large Modal -->

                <!-- Regular Fade -->
                <div id="modal-fade" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3 class="modal-title"><strong>Modal</strong></h3>
                            </div>
                            <div class="modal-body">
                                Content..
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-effect-ripple btn-primary">Save</button>
                                <button type="button" class="btn btn-effect-ripple btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Regular Fade -->

                <!-- Regular Tabs -->
                <div id="modal-tabs" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <ul class="nav nav-tabs" data-toggle="tabs">
                                    <li class="active"><a href="#modal-tabs-home"><i class="fa fa-home"></i> Home</a></li>
                                    <li><a href="#modal-tabs-settings" data-toggle="tooltip" title="Settings"><i class="gi gi-settings"></i> Settings</a></li>
                                </ul>
                            </div>
                            <div class="modal-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="modal-tabs-home">Home..</div>
                                    <div class="tab-pane" id="modal-tabs-settings">Settings..</div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-effect-ripple btn-primary">Save</button>
                                <button type="button" class="btn btn-effect-ripple btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Regular Tabs -->
            </div>
            <!-- END Popovers, Tooltips and Modals Block -->

            <!-- Block Tabs -->
            <div class="block full">
                <!-- Block Tabs Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <ul class="nav nav-tabs" data-toggle="tabs">
                        <li class="active"><a href="#block-tabss-home">Home</a></li>
                        <li><a href="#block-tabss-profile">Profile</a></li>
                        <li><a href="#block-tabss-settings" data-toggle="tooltip" title="Settings"><i class="gi gi-settings"></i></a></li>
                    </ul>
                </div>
                <!-- END Block Tabs Title -->

                <!-- Tabs Content -->
                <div class="tab-content">
                    <div class="tab-pane active" id="block-tabss-home">Block Tabs..</div>
                    <div class="tab-pane" id="block-tabss-profile">Profile..</div>
                    <div class="tab-pane" id="block-tabss-settings">Settings..</div>
                </div>
                <!-- END Tabs Content -->
            </div>
            <!-- END Block Tabs -->

            <!-- Navs Block -->
            <div class="block">
                <!-- Navs Title -->
                <div class="block-title">
                    <h2>Navigation Elements</h2>
                </div>
                <!-- END Navs Title -->

                <!-- Breadcrumb -->
                <div class="block-section">
                    <h4 class="sub-header">Breadcrumb</h4>
                    <ol class="breadcrumb">
                        <li><a href="javascript:void(0)"><i class="fa fa-home"></i></a></li>
                        <li><a href="javascript:void(0)">Profile</a></li>
                        <li><a href="javascript:void(0)">Settings</a></li>
                    </ol>
                </div>
                <!-- END Breadcrumb -->

                <!-- Tabs -->
                <div class="block-section">
                    <h4 class="sub-header">Tabs</h4>
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="javascript:void(0)">Home</a></li>
                        <li><a href="javascript:void(0)">News</a></li>
                        <li><a href="javascript:void(0)"><i class="gi gi-settings"></i></a></li>
                    </ul>
                </div>
                <!-- END Tabs -->

                <!-- Pills -->
                <div class="block-section">
                    <h4 class="sub-header">Pills</h4>
                    <ul class="nav nav-pills">
                        <li class="active"><a href="javascript:void(0)">Home</a></li>
                        <li><a href="javascript:void(0)">News</a></li>
                        <li class="disabled"><a href="javascript:void(0)">Disabled</a></li>
                    </ul>
                </div>
                <!-- END Pills -->

                <!-- Stacked Pills -->
                <div class="block-section">
                    <h4 class="sub-header">Stacked Pills</h4>
                    <div class="row">
                        <div class="col-md-6">
                            <ul class="nav nav-pills nav-stacked">
                                <li class="active">
                                    <a href="javascript:void(0)"><span class="badge pull-right">3</span><i class="fa fa-fw fa-home icon-push"></i> Home</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><span class="badge pull-right">9</span><i class="fa fa-fw fa-tasks icon-push"></i> Notifications</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><span class="badge pull-right">5</span><i class="fa fa-fw fa-asterisk icon-push"></i> News</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <ul class="nav nav-pills nav-stacked">
                                <li class="active">
                                    <a href="javascript:void(0)"><span class="badge pull-right">1</span>Home</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><span class="badge pull-right">3</span>News</a>
                                </li>
                                <li class="disabled">
                                    <a href="javascript:void(0)">Disabled</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- END Stacked Pills -->

                <!-- Pager -->
                <div class="block-section">
                    <h4 class="sub-header">Pager</h4>
                    <div class="block full">
                        <ul class="pager">
                            <li class="previous"><a href="javascript:void(0)">Previous</a></li>
                            <li class="next disabled"><a href="javascript:void(0)">Next</a></li>
                        </ul>
                    </div>
                    <div class="block full">
                        <ul class="pager">
                            <li class="previous"><a href="javascript:void(0)"><i class="fa fa-arrow-left"></i></a></li>
                            <li class="next"><a href="javascript:void(0)"><i class="fa fa-arrow-right"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- END Pager -->
            </div>
            <!-- END Navs Block -->
        </div>
    </div>
    <!-- END Nav Row -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>
<?php include 'inc/template_end.php'; ?>