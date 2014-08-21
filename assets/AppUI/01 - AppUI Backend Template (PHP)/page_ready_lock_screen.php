<?php include 'inc/config.php'; ?>
<?php include 'inc/template_start.php'; ?>

<!-- Full Background -->
<!-- For best results use an image with a resolution of 1280x1280 pixels (prefer a blurred image for smaller file size) -->
<img src="img/placeholders/layout/lock_full_bg.jpg" alt="Full Background" class="full-bg full-bg-bottom animation-pulseSlow">
<!-- END Full Background -->

<!-- Login Container -->
<div id="login-container">
    <!-- Lock Header -->
    <p class="text-center push-top-bottom animation-slideDown">
        <img src="img/placeholders/avatars/avatar6@2x.jpg" alt="avatar" class="img-circle img-thumbnail img-thumbnail-avatar-2x">
    </p>
    <h1 class="text-center text-light push-top-bottom animation-fadeInQuick">
        <strong>Emily Wells</strong><br>
        <small><em>Photographer</em></small>
    </h1>
    <!-- END Lock Header -->

    <!-- Lock Form -->
    <form action="index.php" method="post" class="form-horizontal push animation-fadeInQuick">
        <div class="form-group">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2">
                <div class="input-group input-group-lg">
                    <input type="password" id="lock-password" name="lock-password" class="form-control form-control-borderless" placeholder="Enter your pass..">
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-effect-ripple btn-block btn-primary"><i class="fa fa-unlock-alt"></i></button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <p class="text-center animation-fadeInQuick">
        <a href="page_ready_login.php"><small>Not Emily Wells?</small></a>
    </p>
    <!-- END Lock Form -->
</div>
<!-- END Login Container -->

<?php include 'inc/template_scripts.php'; ?>
<?php include 'inc/template_end.php'; ?>