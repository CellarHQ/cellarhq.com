<?php include 'inc/config.php'; ?>
<?php include 'inc/template_start.php'; ?>

<!-- Login Container -->
<div id="login-container">
    <!-- Reminder Header -->
    <h1 class="h2 text-light text-center push-top-bottom animation-slideDown">
        <i class="fa fa-history"></i> <strong>Password Reminder</strong>
    </h1>
    <!-- END Reminder Header -->

    <!-- Reminder Block -->
    <div class="block animation-fadeInQuickInv">
        <!-- Reminder Title -->
        <div class="block-title">
            <div class="block-options pull-right">
                <a href="page_ready_login.php" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="left" title="Back to login"><i class="fa fa-user"></i></a>
            </div>
            <h2>Reminder</h2>
        </div>
        <!-- END Reminder Title -->

        <!-- Reminder Form -->
        <form id="form-reminder" action="page_ready_reminder.php" method="post" class="form-horizontal">
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="text" id="reminder-email" name="reminder-email" class="form-control" placeholder="Enter your email..">
                </div>
            </div>
            <div class="form-group form-actions">
                <div class="col-xs-12 text-right">
                    <button type="submit" class="btn btn-effect-ripple btn-sm btn-primary"><i class="fa fa-check"></i> Remind Password</button>
                </div>
            </div>
        </form>
        <!-- END Reminder Form -->
    </div>
    <!-- END Reminder Block -->

    <!-- Footer -->
    <footer class="text-muted text-center animation-pullUp">
        <small><span id="year-copy"></span> &copy; <a href="javascript:void(0)" target="_blank"><?php echo $template['name'] . ' ' . $template['version']; ?></a></small>
    </footer>
    <!-- END Footer -->
</div>
<!-- END Login Container -->

<?php include 'inc/template_scripts.php'; ?>

<!-- Load and execute javascript code used only in this page -->
<script src="js/pages/readyReminder.js"></script>
<script>$(function(){ ReadyReminder.init(); });</script>

<?php include 'inc/template_end.php'; ?>