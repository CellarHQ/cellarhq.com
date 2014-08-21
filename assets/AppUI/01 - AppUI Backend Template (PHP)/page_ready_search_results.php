<?php include 'inc/config.php'; $template['header_link'] = 'PAGES'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Search Results Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <div class="header-section">
                    <form action="page_ready_search_results.php" method="post" onsubmit="return false;">
                    <div class="form-group">
                        <input type="text" id="search-term" name="search-term" class="form-control" placeholder="Search for something..">
                    </div>
                    <div class="form-group">
                        <select id="search-categories" name="search-categories" class="select-chosen" data-placeholder="Choose categories.." style="width: 250px;" multiple>
                            <option value="images" selected>Images</option>
                            <option value="users">Users</option>
                            <option value="sites">Sites</option>
                            <option value="filse">Files</option>
                        </select>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-effect-ripple btn-effect-ripple btn-primary">Search Database</button>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </div>
    <!-- END Search Results Header -->

    <!-- Search Results Block -->
    <div class="block">
        <!-- Search Styles Title -->
        <div class="block-title">
            <ul class="nav nav-tabs" data-toggle="tabs">
                <li class="active"><a href="#search-tab-images">Images</a></li>
                <li><a href="#search-tab-users">Users</a></li>
                <li><a href="#search-tab-sites">Sites</a></li>
                <li><a href="#search-tab-files">Files</a></li>
            </ul>
        </div>
        <!-- END Search Styles Title -->

        <!-- Search Results Content -->
        <div class="tab-content">
            <!-- Images Results -->
            <div class="tab-pane active" id="search-tab-images">
                <div class="row gallery">
                    <div class="col-sm-4">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo22.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo22.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo23.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo23.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo2.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo2.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo15.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo15.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo17.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo17.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo19.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo19.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo18.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo18.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo11.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo11.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo13.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo13.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="gallery-image-container animation-fadeInQuick">
                            <img src="img/placeholders/photos/photo20.jpg" alt="Image">
                            <div class="gallery-image-options">
                                <h3 class="text-light"><strong>Awesome Image</strong></h3>
                                <a href="img/placeholders/photos/photo20.jpg" class="gallery-link btn btn-primary" data-toggle="lightbox-image" title="Image Info"><i class="fa fa-search-plus"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-success" data-toggle="tooltip" title="Edit"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-danger" data-toggle="tooltip" title="Delete"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Pagination -->
                <div class="text-center">
                    <ul class="pagination">
                        <li class="disabled"><a href="javascript:void(0)">Prev</a></li>
                        <li class="active"><a href="javascript:void(0)">1</a></li>
                        <li><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)">Next</a></li>
                    </ul>
                </div>
                <!-- END Pagination -->
            </div>
            <!-- END Images Results -->

            <!-- Users Results -->
            <div class="tab-pane" id="search-tab-users">
                <div class="row">
                    <div class="col-sm-6">
                        <table class="table table-striped table-borderless table-hover table-vcenter">
                            <tbody>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center" style="width: 100px;"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user1@example.com</td>
                                    <td class="text-center" style="width: 100px;">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user2@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user3@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user4@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user5@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user6@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user7@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user8@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user9@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user10@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-6">
                        <table class="table table-striped table-borderless table-hover table-vcenter">
                            <tbody>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center" style="width: 100px;"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user11@example.com</td>
                                    <td class="text-center" style="width: 100px;">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user12@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user13@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user14@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user15@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user16@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user17@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user18@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user19@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr class="animation-fadeInQuickInv">
                                    <td class="text-center"><img src="img/placeholders/avatars/avatar<?php echo rand(1, 16); ?>.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar"></td>
                                    <td class="hidden-xs">user20@example.com</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-effect-ripple btn-xs btn-success"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-effect-ripple btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Pagination -->
                <div class="text-center">
                    <ul class="pagination">
                        <li class="disabled"><a href="javascript:void(0)">Prev</a></li>
                        <li class="active"><a href="javascript:void(0)">1</a></li>
                        <li><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)">Next</a></li>
                    </ul>
                </div>
                <!-- END Pagination -->
            </div>
            <!-- END Users Results -->

            <!-- Sites Results -->
            <div class="tab-pane" id="search-tab-sites">
                <ol>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                    <li>
                        <h4>
                            <a href="javascript:void(0)"><strong>AppUI - Web App and Admin Template</strong></a><br>
                            <small><a href="javascript:void(0)" class="text-muted"><strong>http://pixelcave.com/appui/</strong></a></small>
                        </h4>
                        <p>Mauris tincidunt tincidunt turpis in porta. Integer fermentum tincidunt auctor. Vestibulum ullamcorper, odio sed rhoncus imperdiet, enim elit sollicitudin orci, eget dictum leo mi nec lectus. Nam commodo turpis id lectus scelerisque vulputate. Integer sed dolor erat. Fusce erat ipsum, varius vel euismod sed, tristique et lectus? Etiam egestas fringilla enim, id convallis lectus laoreet at. Fusce purus nisi, gravida sed consectetur ut, interdum quis nisi. Quisque egestas nisl id lectus facilisis scelerisque? Proin rhoncus dui at ligula vestibulum ut facilisis ante sodales! Suspendisse potenti. Aliquam tincidunt sollicitudin sem nec ultrices.</p>
                    </li>
                </ol>
                <!-- Pagination -->
                <div class="text-center">
                    <ul class="pagination">
                        <li class="disabled"><a href="javascript:void(0)">Prev</a></li>
                        <li class="active"><a href="javascript:void(0)">1</a></li>
                        <li><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)">Next</a></li>
                    </ul>
                </div>
                <!-- END Pagination -->
            </div>
            <!-- END Sites Results -->

            <!-- Files Results -->
            <div class="tab-pane" id="search-tab-files">
                <div class="row">
                    <div class="col-sm-6">
                        <table class="table table-striped table-borderless table-hover table-vcenter">
                            <tbody>
                                <tr>
                                    <td class="text-center" style="width: 150px;">
                                        <a href="img/placeholders/photos/photo5.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo5.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>car.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 100kb<br>
                                            <strong>Downloads:</strong> 1506
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fi fi-zip fa-4x text-muted"></i>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>archive.zip</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> ZIP<br>
                                            <strong>Size:</strong> 10mb<br>
                                            <strong>Downloads:</strong> 1560
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <a href="img/placeholders/photos/photo13.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo13.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>flower.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 150kb<br>
                                            <strong>Downloads:</strong> 3241
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fi fi-mp3 fa-4x text-muted"></i>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>live.mp3</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> MP3<br>
                                            <strong>Size:</strong> 26.5mb<br>
                                            <strong>Downloads:</strong> 63
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <a href="img/placeholders/photos/photo19.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo19.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>nature.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 690kb<br>
                                            <strong>Downloads:</strong> 15
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fi fi-avi fa-4x text-muted"></i>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>film.avi</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> AVI<br>
                                            <strong>Size:</strong> 16mb<br>
                                            <strong>Downloads:</strong> 6215
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <a href="img/placeholders/photos/photo15.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo15.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>food.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 256kb<br>
                                            <strong>Downloads:</strong> 2365
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-6">
                        <table class="table table-striped table-borderless table-hover table-vcenter">
                            <tbody>
                                <tr>
                                    <td class="text-center" style="width: 150px;">
                                        <a href="img/placeholders/photos/photo1.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo1.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>mountains.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 160kb<br>
                                            <strong>Downloads:</strong> 2600
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fi fi-ppt fa-4x text-muted"></i>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>presentation.ppt</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> PPT<br>
                                            <strong>Size:</strong> 15mb<br>
                                            <strong>Downloads:</strong> 2651
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <a href="img/placeholders/photos/photo11.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo11.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>pizza.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 610kb<br>
                                            <strong>Downloads:</strong> 9501
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fi fi-less fa-4x text-muted"></i>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>styles.less</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> LessCSS<br>
                                            <strong>Size:</strong> 200kb<br>
                                            <strong>Downloads:</strong> 8541
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <a href="img/placeholders/photos/photo7.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo7.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>road.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 430kb<br>
                                            <strong>Downloads:</strong> 396
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <i class="fi fi-text fa-4x text-muted"></i>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>description.txt</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> TXT<br>
                                            <strong>Size:</strong> 3kb<br>
                                            <strong>Downloads:</strong> 3614
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-center">
                                        <a href="img/placeholders/photos/photo12.jpg" data-toggle="lightbox-image">
                                            <img src="img/placeholders/photos/photo12.jpg" alt="image" class="img-responsive center-block" style="max-width: 150px;">
                                        </a>
                                    </td>
                                    <td>
                                        <h4><a href="javascript:void(0)"><strong>drinks.jpg</strong></a></h4>
                                        <div class="text-muted">
                                            <strong>Type:</strong> JPEG<br>
                                            <strong>Size:</strong> 365kb<br>
                                            <strong>Downloads:</strong> 960
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Pagination -->
                <div class="text-center">
                    <ul class="pagination">
                        <li class="disabled"><a href="javascript:void(0)">Prev</a></li>
                        <li class="active"><a href="javascript:void(0)">1</a></li>
                        <li><a href="javascript:void(0)">2</a></li>
                        <li><a href="javascript:void(0)">3</a></li>
                        <li><a href="javascript:void(0)">Next</a></li>
                    </ul>
                </div>
                <!-- END Pagination -->
            </div>
            <!-- END Files Results -->
        </div>
        <!-- END Search Results Content -->
    </div>
    <!-- END Search Results Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>
<?php include 'inc/template_end.php'; ?>