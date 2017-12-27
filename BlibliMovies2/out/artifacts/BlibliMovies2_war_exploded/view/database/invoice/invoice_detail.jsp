<%@ include file = "/include/head.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <div class="jumbotron" id="detail" style="width: 550px; margin: 0 auto;">

        <!-- Content -->

        <div class="col-lg-12" id="form1">
            <div class="row">
                <div class="col-lg-8 col-xs-12">
                    <h2>Invoice</h2>
                    <h4>Order ID: ${invoice.id}</h4>
                </div>
                <div class="col-lg-4 col-xs-6" align="right">
                    <h4>Order Date:</h4>
                    <h4>${invoice.orderDate}</h4>
                </div>
                <div class="stripe"></div><br>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <h3>Order Details:</h3><br>
                </div>
            </div>

            <div class="box-window">
                <c:forEach var="orderDetails" items="${orderDetails}">
                    <div class="row">
                        <div class="col-lg-6">
                            <p class="invoice">${orderDetails.itemName}</p>
                        </div>
                        <div class="col-lg-3" align="center">
                            <p>x${orderDetails.quantity}</p>
                        </div>
                        <div class="col-lg-3" align="right">
                            Rp ${orderDetails.price},-
                        </div>
                    </div>
                </c:forEach>

                <div class="row">
                    <div class="col-lg-6">
                        <br>
                        <p>Total: Rp. ${invoice.memberId == 0 ? invoice.totalPrice : invoice.totalPrice * 100 / (100 - promo.discountAmount)} ,-</p>
                        <p>Member Discount: ${invoice.memberId == 0 ? "-" : promo.discountAmount} %</p>
                    </div>

                    <div class="col-lg-6" align="right">
                        <br>
                        <p>Amount Payable</p>
                        <p><strong>Rp. ${invoice.totalPrice} ,-</strong></p>
                    </div>
                </div>

                <div class="row" data-html2canvas-ignore="true">
                    <button type="submit" class="btn btn-default" id="accept">Print ></button>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>
    <script>
        function print() {
            var doc = new jsPDF('p', 'pt', [$('.jumbotron').width(), $('.jumbotron').height()]);
            $('*').css('background-color', 'white');
            $('*').css('color', 'black');
            $('.stripe').css('background-color', 'black');
            doc.text(10, 10, 'This is a test');
            doc.addHTML(
                $('.jumbotron').get(0), function () {
                    doc.autoPrint();
                    doc.save('[Invoice] ' + ${invoice.id});
                });
            $('*').css('background-color', 'black');
            $('*').css('color', 'white');
            $('.stripe').css('background-color', 'white');
        }
        $(document).ready(function () {
            $('#accept').click(function () {
                print();
            });
        });
    </script>
    
<%@ include file = "/include/foot.jsp" %>