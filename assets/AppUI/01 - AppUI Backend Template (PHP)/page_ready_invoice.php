<?php include 'inc/config.php'; $template['header_link'] = 'PAGES'; ?>
<?php include 'inc/template_start.php'; ?>
<?php include 'inc/page_head.php'; ?>

<!-- Page content -->
<div id="page-content">
    <!-- Invoice Header -->
    <div class="content-header">
        <div class="row">
            <div class="col-sm-6">
                <div class="header-section">
                    <h1>Invoice</h1>
                </div>
            </div>
            <div class="col-sm-6 hidden-xs">
                <div class="header-section">
                    <ul class="breadcrumb breadcrumb-top">
                        <li>Extra Pages</li>
                        <li><a href="">Invoice</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END Invoice Header -->

    <!-- Invoice Block -->
    <div class="block">
        <!-- Invoice Title -->
        <div class="block-title">
            <h2>Invoice <small>#INV001234</small></h2>
        </div>
        <!-- END Invoice Title -->

        <!-- Invoice Info -->
        <div class="row block-section">
            <!-- Company Info -->
            <div class="col-sm-4 col-lg-3">
                <div class="well">
                    <h2><strong>Company</strong></h2>
                    <address>
                        Address<br>
                        Town/City<br>
                        Region, Zip/Postal Code<br>
                        <i class="fa fa-phone"></i> (000) 000-0000
                    </address>
                </div>
            </div>
            <!-- END Company Info -->

            <!-- Client Info -->
            <div class="col-sm-4 col-sm-offset-4 col-lg-3 col-lg-offset-6 text-right">
                <div class="well">
                    <h2><strong>Client</strong></h2>
                    <address>
                        Address<br>
                        Town/City<br>
                        Region, Zip/Postal Code<br>
                        <i class="fa fa-phone"></i> (000) 000-0000
                    </address>
                </div>
            </div>
            <!-- END Client Info -->
        </div>
        <!-- END Invoice Info -->

        <!-- Message -->
        <div class="alert alert-success text-center">
            <h2><strong>Thank you for your business <i class="fa fa-smile-o"></i></strong></h2>
            <p>We will be happy to work together again in the future!</p>
        </div>
        <!-- END Message -->

        <!-- Table -->
        <div class="table-responsive">
            <table class="table table-striped table-hover table-borderless table-vcenter">
                <thead>
                    <tr>
                        <th class="text-center">#</th>
                        <th style="width: 50%;">Product</th>
                        <th class="text-center">Quantity</th>
                        <th class="text-right">Unit</th>
                        <th class="text-right">Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="text-center">1</td>
                        <td>
                            <h4>App Design</h4>
                        </td>
                        <td class="text-center"><span class="label label-success"><strong>x1</strong></span></td>
                        <td class="text-right">$ 3.000,00</td>
                        <td class="text-right">$ 3.000,00</td>
                    </tr>
                    <tr>
                        <td class="text-center">2</td>
                        <td>
                            <h4>Icon Pack Design</h4>
                        </td>
                        <td class="text-center"><span class="label label-success"><strong>x2</strong></span></td>
                        <td class="text-right">$ 1.000,00</td>
                        <td class="text-right">$ 2.000,00</td>
                    </tr>
                    <tr>
                        <td class="text-center">3</td>
                        <td>
                            <h4>Website Design</h4>
                        </td>
                        <td class="text-center"><span class="label label-success"><strong>x2</strong></span></td>
                        <td class="text-right">$ 2.000,00</td>
                        <td class="text-right">$ 4.000,00</td>
                    </tr>
                    <tr>
                        <td colspan="4" class="text-right"><span class="h4">Subtotal</span></td>
                        <td class="text-right"><span class="h4">$ 9.000,00</span></td>
                    </tr>
                    <tr>
                        <td colspan="4" class="text-right"><span class="h4">Vat Rate</span></td>
                        <td class="text-right"><span class="h4">25%</span></td>
                    </tr>
                    <tr>
                        <td colspan="4" class="text-right"><span class="h4">Vat Due</span></td>
                        <td class="text-right"><span class="h4">$ 2.250,00</span></td>
                    </tr>
                    <tr class="warning">
                        <td colspan="4" class="text-right"><span class="h4"><strong>Total Due</strong></span></td>
                        <td class="text-right"><span class="h4"><strong>$ 11.250,00</strong></span></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- END Table -->
    </div>
    <!-- END Invoice Block -->
</div>
<!-- END Page Content -->

<?php include 'inc/page_footer.php'; ?>
<?php include 'inc/template_scripts.php'; ?>
<?php include 'inc/template_end.php'; ?>