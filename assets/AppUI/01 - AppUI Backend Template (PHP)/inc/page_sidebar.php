<?php
/**
 * page_sidebar.php
 *
 * Author: pixelcave
 *
 * The main sidebar of each page
 *
 */
?>
<!-- Main Sidebar -->
<div id="sidebar">
    <!-- Sidebar Brand -->
    <div id="sidebar-brand" class="themed-background">
        <a href="index.php" class="sidebar-title">
            <i class="fa fa-cube"></i> <span class="sidebar-nav-mini-hide">App<strong>UI</strong></span>
        </a>
    </div>
    <!-- END Sidebar Brand -->

    <!-- Wrapper for scrolling functionality -->
    <div id="sidebar-scroll">
        <!-- Sidebar Content -->
        <div class="sidebar-content">
            <?php if ($primary_nav) { ?>
            <!-- Sidebar Navigation -->
            <ul class="sidebar-nav">
                <?php foreach( $primary_nav as $key => $link ) {
                    $link_class = '';
                    $li_active  = '';
                    $menu_link  = '';

                    // Get 1st level link's vital info
                    $url        = (isset($link['url']) && $link['url']) ? $link['url'] : '#';
                    $active     = (isset($link['url']) && ($template['active_page'] == $link['url'])) ? ' active' : '';
                    $icon       = (isset($link['icon']) && $link['icon']) ? '<i class="' . $link['icon'] . ' sidebar-nav-icon"></i>' : '';

                    // Check if the link has a submenu
                    if (isset($link['sub']) && $link['sub']) {
                        // Since it has a submenu, we need to check if we have to add the class active
                        // to its parent li element (only if a 2nd or 3rd level link is active)
                        foreach ($link['sub'] as $sub_link) {
                            if (in_array($template['active_page'], $sub_link)) {
                                $li_active = ' class="active"';
                                break;
                            }

                            // 3rd level links
                            if (isset($sub_link['sub']) && $sub_link['sub']) {
                                foreach ($sub_link['sub'] as $sub2_link) {
                                    if (in_array($template['active_page'], $sub2_link)) {
                                        $li_active = ' class="active"';
                                        break;
                                    }
                                }
                            }
                        }

                        $menu_link = 'sidebar-nav-menu';
                    }

                    // Create the class attribute for our link
                    if ($menu_link || $active) {
                        $link_class = ' class="'. $menu_link . $active .'"';
                    }
                ?>
                <?php if ($url == 'separator') { // if it is a separator and not a link ?>
                <li class="sidebar-separator">
                    <i class="fa fa-ellipsis-h"></i>
                </li>
                <?php } else { // If it is a link ?>
                <li<?php echo $li_active; ?>>
                    <a href="<?php echo $url; ?>"<?php echo $link_class; ?>><?php if (isset($link['sub']) && $link['sub']) { // if the link has a submenu ?><i class="fa fa-chevron-left sidebar-nav-indicator sidebar-nav-mini-hide"></i><?php } echo $icon; ?><span class="sidebar-nav-mini-hide"><?php echo  $link['name']; ?></span></a>
                    <?php if (isset($link['sub']) && $link['sub']) { // if the link has a submenu ?>
                    <ul>
                        <?php foreach ($link['sub'] as $sub_link) {
                            $link_class = '';
                            $li_active = '';
                            $submenu_link = '';

                            // Get 2nd level link's vital info
                            $url        = (isset($sub_link['url']) && $sub_link['url']) ? $sub_link['url'] : '#';
                            $active     = (isset($sub_link['url']) && ($template['active_page'] == $sub_link['url'])) ? ' active' : '';

                            // Check if the link has a submenu
                            if (isset($sub_link['sub']) && $sub_link['sub']) {
                                // Since it has a submenu, we need to check if we have to add the class active
                                // to its parent li element (only if a 3rd level link is active)
                                foreach ($sub_link['sub'] as $sub2_link) {
                                    if (in_array($template['active_page'], $sub2_link)) {
                                        $li_active = ' class="active"';
                                        break;
                                    }
                                }

                                $submenu_link = 'sidebar-nav-submenu';
                            }

                            if ($submenu_link || $active) {
                                $link_class = ' class="'. $submenu_link . $active .'"';
                            }
                        ?>
                        <li<?php echo $li_active; ?>>
                            <a href="<?php echo $url; ?>"<?php echo $link_class; ?>><?php if (isset($sub_link['sub']) && $sub_link['sub']) { ?><i class="fa fa-chevron-left sidebar-nav-indicator"></i><?php } echo  $sub_link['name']; ?></a>
                            <?php if (isset($sub_link['sub']) && $sub_link['sub']) { ?>
                                <ul>
                                    <?php foreach ($sub_link['sub'] as $sub2_link) {
                                        // Get 3rd level link's vital info
                                        $url    = (isset($sub2_link['url']) && $sub2_link['url']) ? $sub2_link['url'] : '#';
                                        $active = (isset($sub2_link['url']) && ($template['active_page'] == $sub2_link['url'])) ? ' class="active"' : '';
                                    ?>
                                    <li>
                                        <a href="<?php echo $url; ?>"<?php echo $active ?>><?php echo $sub2_link['name']; ?></a>
                                    </li>
                                    <?php } ?>
                                </ul>
                            <?php } ?>
                        </li>
                        <?php } ?>
                    </ul>
                    <?php } ?>
                </li>
                <?php } ?>
                <?php } ?>
            </ul>
            <!-- END Sidebar Navigation -->
            <?php } ?>
        </div>
        <!-- END Sidebar Content -->
    </div>
    <!-- END Wrapper for scrolling functionality -->

    <!-- Sidebar Extra Info -->
    <div id="sidebar-extra-info" class="sidebar-content sidebar-nav-mini-hide">
        <div class="push-bit">
            <span class="pull-right">
                <a href="javascript:void(0)" class="text-light"><i class="fa fa-plus"></i></a>
            </span>
            <small><strong class="text-light">78 GB</strong> / 100 GB</small>
        </div>
        <div class="progress progress-mini push-bit">
            <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100" style="width: 78%"></div>
        </div>
        <div class="text-center">
            <small>Crafted with <i class="fa fa-heart text-danger"></i> by <a href="http://goo.gl/vNS3I" target="_blank">pixelcave</a></small><br>
            <small><span id="year-copy"></span> &copy; <a href="javascript:void(0)" target="_blank"><?php echo $template['name'] . ' ' . $template['version']; ?></a></small>
        </div>
    </div>
    <!-- END Sidebar Extra Info -->
</div>
<!-- END Main Sidebar -->
