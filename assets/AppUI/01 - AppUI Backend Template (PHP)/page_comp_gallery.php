<?php include 'inc/config.php'; $template['header_link'] = 'COMPONENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Gallery Header -->
    <div class="content-header">
        <!-- Gallery Filter Links -->
        <!-- Custom Gallery functionality is initialized in js/pages/compGallery.js -->
        <!-- Add the category value you want each link in .gallery-filter to filter out in its data-category attribute. Add the value 'all' to show all items -->
        <div class="header-section text-center">
            <div class="btn-group gallery-filter">
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary active" data-category="all">All</a>
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-category="travel">Travel</a>
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-category="food">Food</a>
                <a href="javascript:void(0)" class="btn btn-effect-ripple btn-primary" data-category="nature">Nature</a>
            </div>
        </div>
        <!-- END Gallery Filter Links -->
    </div>
    <!-- END Gallery Header -->

    <!-- Gallery Items -->
    <!-- Lightbox functionality is based on Magnific Popup plugin (initialized in js/app.js -> uiInit()) -->
    <!-- Add the category value for each item in its data-category attribute (for the filter functionality to work) -->
    <div class="row gallery">
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo5.jpg" alt="Image">
                <a href="img/placeholders/photos/photo5.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Car Wheel</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="food">
                <img src="img/placeholders/photos/photo2.jpg" alt="Image">
                <a href="img/placeholders/photos/photo2.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Pizza Time</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="nature">
                <img src="img/placeholders/photos/photo1.jpg" alt="Image">
                <a href="img/placeholders/photos/photo1.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Mountains</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="nature">
                <img src="img/placeholders/photos/photo3.jpg" alt="Image">
                <a href="img/placeholders/photos/photo3.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Nature</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="food">
                <img src="img/placeholders/photos/photo11.jpg" alt="Image">
                <a href="img/placeholders/photos/photo11.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Pizza Time</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="food">
                <img src="img/placeholders/photos/photo14.jpg" alt="Image">
                <a href="img/placeholders/photos/photo14.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Croissants</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo16.jpg" alt="Image">
                <a href="img/placeholders/photos/photo16.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Fly Time</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo9.jpg" alt="Image">
                <a href="img/placeholders/photos/photo9.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Lonely Road</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="nature">
                <img src="img/placeholders/photos/photo17.jpg" alt="Image">
                <a href="img/placeholders/photos/photo17.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Red Flower</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="nature">
                <img src="img/placeholders/photos/photo19.jpg" alt="Image">
                <a href="img/placeholders/photos/photo19.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Leafs</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="food">
                <img src="img/placeholders/photos/photo15.jpg" alt="Image">
                <a href="img/placeholders/photos/photo15.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Sweet</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo24.jpg" alt="Image">
                <a href="img/placeholders/photos/photo24.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>City Trip</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="nature">
                <img src="img/placeholders/photos/photo21.jpg" alt="Image">
                <a href="img/placeholders/photos/photo21.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Sunflower</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo10.jpg" alt="Image">
                <a href="img/placeholders/photos/photo10.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>In the clouds</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo22.jpg" alt="Image">
                <a href="img/placeholders/photos/photo22.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>Skycrapers</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="gallery-image-container animation-fadeInQuick2" data-category="travel">
                <img src="img/placeholders/photos/photo23.jpg" alt="Image">
                <a href="img/placeholders/photos/photo23.jpg" class="gallery-image-options" data-toggle="lightbox-image" title="Image Info">
                    <h2 class="text-light"><strong>City at Night</strong></h2>
                    <i class="fa fa-search-plus fa-5x text-light"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- END Gallery Items -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/compGallery.js"></script>
<script>$(function(){ CompGallery.init(); });</script>

<?php include 'inc/template_end.php'; ?>