<?php include 'inc/config.php'; $template['header_link'] = 'COMPONENTS'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Google Maps Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Google Maps</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>Components</li>
                        <li><a href="">Google Maps</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Google Maps Header -->

    <!-- Google Maps Content -->
    <div class="row">
        <div class="col-sm-6">
            <!-- Terrain Map Block -->
            <div class="block">
                <!-- Terrain Map Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2><i class="fa fa-map-marker"></i> Terrain Map</h2>
                </div>
                <!-- END Terrain Map Title -->

                <!-- Terrain Map Content -->
                <!-- Gmaps.js (initialized in js/pages/compMaps.js), for more examples you can check out http://hpneo.github.io/gmaps/examples.html -->
                <div class="block-content-full">
                    <div id="gmap-terrain" class="gmap" style="height: 360px;"></div>
                </div>
                <!-- END Terrain Map Content -->
            </div>
            <!-- END Terrain Map Block -->
        </div>
        <div class="col-sm-6">
            <!-- Satellite Map Block -->
            <div class="block">
                <!-- Satellite Map Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2><i class="fa fa-globe"></i> Satellite Map</h2>
                </div>
                <!-- END Satellite Map Title -->

                <!-- Satellite Map Content -->
                <!-- Gmaps.js (initialized in js/pages/compMaps.js), for more examples you can check out http://hpneo.github.io/gmaps/examples.html -->
                <div class="block-content-full">
                    <div id="gmap-satellite" class="gmap" style="height: 360px;"></div>
                </div>
                <!-- END Satellite Map Content -->
            </div>
            <!-- END Satellite Map Block -->
        </div>
        <div class="col-sm-6">
            <!-- Map with Markers Block -->
            <div class="block">
                <!-- Map with Markers Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2><i class="fa fa-map-marker"></i> Markers Map</h2>
                </div>
                <!-- END Map with Markers Title -->

                <!-- Map with Markers Content -->
                <!-- Gmaps.js (initialized in js/pages/compMaps.js), for more examples you can check out http://hpneo.github.io/gmaps/examples.html -->
                <div class="block-content-full">
                    <div id="gmap-markers" class="gmap" style="height: 360px;"></div>
                </div>
                <!-- END Map with Markers Content -->
            </div>
            <!-- END Map with Markers Block -->
        </div>
        <div class="col-sm-6">
            <!-- Overlay Map Block -->
            <div class="block">
                <!-- Overlay Map Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2><i class="fa fa-info-circle"></i> Overlay Map</h2>
                </div>
                <!-- END Overlay Map Title -->

                <!-- Overlay Map Content -->
                <!-- Gmaps.js (initialized in js/pages/compMaps.js), for more examples you can check out http://hpneo.github.io/gmaps/examples.html -->
                <div class="block-content-full">
                    <div id="gmap-overlay" class="gmap" style="height: 360px;"></div>
                </div>
                <!-- END Overlay Map Content -->
            </div>
            <!-- END Overlay Map Block -->
        </div>
        <div class="col-sm-6">
            <!-- Geolocation Block -->
            <div class="block">
                <!-- Geolocation Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2><i class="fa fa-map-marker"></i> Geolocation Map</h2>
                </div>
                <!-- END Geolocation Title -->

                <!-- Geolocation Content -->
                <!-- Gmaps.js (initialized in js/pages/compMaps.js), for more examples you can check out http://hpneo.github.io/gmaps/examples.html -->
                <div class="block-content-full">
                    <div id="gmap-geolocation" class="gmap" style="height: 360px;"></div>
                </div>
                <!-- END Geolocation Content -->
            </div>
            <!-- END Geolocation Block -->
        </div>
        <div class="col-sm-6">
            <!-- Street View Block -->
            <div class="block">
                <!-- Street View Title -->
                <div class="block-title">
                    <div class="block-options pull-right">
                        <a href="javascript:void(0)" class="btn btn-effect-ripple btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                    </div>
                    <h2><i class="fa fa-road"></i> Street View Map</h2>
                </div>
                <!-- END Street View Title -->

                <!-- Street View Content -->
                <!-- Gmaps.js (initialized in js/pages/compMaps.js), for more examples you can check out http://hpneo.github.io/gmaps/examples.html -->
                <div class="block-content-full">
                    <div id="gmap-street" class="gmap" style="height: 360px;"></div>
                </div>
                <!-- END Street View Content -->
            </div>
            <!-- END Street View Block -->
        </div>
    </div>
    <!-- END Google Maps Content -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>

<!-- Google Maps API + Gmaps Plugin, must be loaded in the page you would like to use maps (Remove 'http:' if you have SSL) -->
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script src="js/plugins/gmaps.min.js"></script>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/compMaps.js"></script>
<script>$(function(){ CompMaps.init(); });</script>

<?php include 'inc/template_end.php'; ?>