<?php include 'inc/config.php'; ?>
<?php include 'inc/template_start.php'; ?>

<!-- Full Background -->
<!-- For best results use an image with a resolution of 1280x1280 pixels (prefer a blurred image for smaller file size) -->
<img src="img/placeholders/layout/error_full_bg.jpg" alt="Full Background" class="full-bg full-bg-bottom animation-pulseSlow">
<!-- END Full Background -->

<!-- Error Container -->
<div id="error-container">
    <div class="row text-center">
        <div class="col-md-6 col-md-offset-3">
            <h1 class="text-light animation-fadeInQuick"><strong>Feeling Lost?</strong></h1>
            <h2 class="text-muted animation-fadeInQuickInv"><em>We sorry but this page can't be found..</em></h2>
        </div>
        <div class="col-md-4 col-md-offset-4">
            <form action="page_ready_search_results.php" method="post" class="push">
                <div class="input-group input-group-lg">
                    <input type="text" id="search-term" name="search-term" class="form-control" placeholder="Search <?php echo $template['name']; ?>..">
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-effect-ripple btn-primary"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            <a href="page_ready_blank.php" class="btn btn-effect-ripple btn-default"><i class="fa fa-arrow-left"></i> Go back</a>
        </div>
    </div>
</div>
<!-- END Error Container -->

<?php include 'inc/template_end.php'; ?>