<?php include 'inc/config.php'; $template['header_link'] = 'COMPONENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Animations Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>CSS3 Animations</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>Components</li>
                        <li><a href="">CSS3 Animations</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Animations Header -->

    <!-- Animate Various Elements Block -->
    <div class="block full">
        <!-- Animate Various Elements Title -->
        <div class="block-title">
            <h2>Animate Elements</h2>
        </div>
        <!-- END Animate Various Elements Title -->

        <!-- Animate Various Elements Content -->
        <div class="row text-center">
            <div class="col-sm-6 col-lg-4">
                <h4 class="sub-header"><strong>All Animations</strong></h4>
                <div class="row">
                    <div class="col-xs-6">
                        <p class="animation-buttons">
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-slideDown">SlideDown</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-slideUp">SlideUp</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-slideLeft">SlideLeft</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-slideRight">SlideRight</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-slideExpandUp">SlideExpandUp</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeIn">FadeIn</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeInQuick">FadeInQuick</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeInQuickInv">FadeInQuickInv</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeInQuick2">FadeInQuick2</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeInQuick2Inv">FadeInQuick2Inv</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeIn360">FadeIn360</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeInLeft">FadeInLeft</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-fadeInRight">FadeInRight</a>
                        </p>
                    </div>
                    <div class="col-xs-6">
                        <p class="animation-buttons">
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-expandOpen">ExpandOpen</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-expandUp">ExpandUp</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-bigEntrance">BigEntrance</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-hatch">Hatch</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-bounce">Bounce</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-pulse">Pulse</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-pulseSlow">PulseSlow</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-floating">Floating</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-floatingHor">FloatingHor</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-tossing">Tossing</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-pullUp">PullUp</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-pullDown">PullDown</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-stretchLeft">StretchLeft</a>
                            <a href="javascript:void(0)" class="btn btn-effect-ripple btn-block btn-primary" data-animation="animation-stretchRight">StretchRight</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-lg-8">
                <h4 class="sub-header"><strong>class=&quot;<span id="animation-class" class="text-danger"></span>&quot;</strong></h4>
                <img src="img/placeholders/photos/photo5.jpg" id="animation-element" class="center-block" alt="avatar" style="width: 280px;">
            </div>
        </div>
        <!-- END Animate Various Elements Content -->
    </div>
    <!-- END Animate Various Elements Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/compAnimations.js"></script>
<script>$(function(){ CompAnimations.init(); });</script>

<?php include 'inc/template_end.php'; ?>