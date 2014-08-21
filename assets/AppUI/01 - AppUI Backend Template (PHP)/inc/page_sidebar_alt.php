<?php
/**
 * page_sidebar_alt.php
 *
 * Author: pixelcave
 *
 * The alternative sidebar of each page
 *
 */
?>
<!-- Alternative Sidebar -->
<div id="sidebar-alt" tabindex="-1" aria-hidden="true">
    <!-- Toggle Alternative Sidebar Button (visible only in static layout) -->
    <a href="javascript:void(0)" id="sidebar-alt-close" onclick="App.sidebar('toggle-sidebar-alt');"><i class="fa fa-times"></i></a>

    <!-- Wrapper for scrolling functionality -->
    <div id="sidebar-scroll-alt">
        <!-- Sidebar Content -->
        <div class="sidebar-content">
            <!-- Color Themes -->
            <!-- Preview a theme on a page functionality can be found in js/app.js - colorThemePreview() -->
            <div class="sidebar-section">
                <h2 class="text-light">Colors</h2>
                <ul class="sidebar-themes clearfix">
                    <li>
                        <a href="javascript:void(0)" class="themed-background-default" data-theme="default" data-theme-navbar="navbar-inverse"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-social" data-theme="css/themes/social.css" data-theme-navbar="navbar-inverse"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-flat" data-theme="css/themes/flat.css" data-theme-navbar="navbar-inverse"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-amethyst" data-theme="css/themes/amethyst.css" data-theme-navbar="navbar-inverse"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-creme" data-theme="css/themes/creme.css" data-theme-navbar="navbar-inverse"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-passion" data-theme="css/themes/passion.css" data-theme-navbar="navbar-inverse"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-default themed-navbar-light" data-theme="default" data-theme-navbar="navbar-default"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-social themed-navbar-light" data-theme="css/themes/social.css" data-theme-navbar="navbar-default"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-flat themed-navbar-light" data-theme="css/themes/flat.css" data-theme-navbar="navbar-default"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-amethyst themed-navbar-light" data-theme="css/themes/amethyst.css" data-theme-navbar="navbar-default"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-creme themed-navbar-light" data-theme="css/themes/creme.css" data-theme-navbar="navbar-default"></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="themed-background-passion themed-navbar-light" data-theme="css/themes/passion.css" data-theme-navbar="navbar-default"></a>
                    </li>
                </ul>
            </div>
            <!-- END Color Themes -->

            <!-- Profile -->
            <div class="sidebar-section">
                <h2 class="text-light">Profile</h2>
                <form action="index.php" method="post" class="form-control-borderless" onsubmit="return false;">
                    <div class="form-group">
                        <label for="side-profile-name">Name</label>
                        <input type="text" id="side-profile-name" name="side-profile-name" class="form-control" value="John Doe">
                    </div>
                    <div class="form-group">
                        <label for="side-profile-email">Email</label>
                        <input type="email" id="side-profile-email" name="side-profile-email" class="form-control" value="john.doe@example.com">
                    </div>
                    <div class="form-group">
                        <label for="side-profile-password">New Password</label>
                        <input type="password" id="side-profile-password" name="side-profile-password" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="side-profile-password-confirm">Confirm New Password</label>
                        <input type="password" id="side-profile-password-confirm" name="side-profile-password-confirm" class="form-control">
                    </div>
                    <div class="form-group remove-margin">
                        <button type="submit" class="btn btn-effect-ripple btn-primary" onclick="App.sidebar('close-sidebar-alt');">Save</button>
                    </div>
                </form>
            </div>
            <!-- END Profile -->

            <!-- Settings -->
            <div class="sidebar-section">
                <h2 class="text-light">Settings</h2>
                <form action="index.php" method="post" class="form-horizontal form-control-borderless" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-xs-7 control-label-fixed">Notifications</label>
                        <div class="col-xs-5">
                            <label class="switch switch-success"><input type="checkbox" checked><span></span></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-7 control-label-fixed">Public Profile</label>
                        <div class="col-xs-5">
                            <label class="switch switch-success"><input type="checkbox" checked><span></span></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-7 control-label-fixed">Enable API</label>
                        <div class="col-xs-5">
                            <label class="switch switch-success"><input type="checkbox"><span></span></label>
                        </div>
                    </div>
                    <div class="form-group remove-margin">
                        <button type="submit" class="btn btn-effect-ripple btn-primary" onclick="App.sidebar('close-sidebar-alt');">Save</button>
                    </div>
                </form>
            </div>
            <!-- END Settings -->
        </div>
        <!-- END Sidebar Content -->
    </div>
    <!-- END Wrapper for scrolling functionality -->
</div>
<!-- END Alternative Sidebar -->

