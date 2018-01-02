<%@ include file = "/include/head.jsp" %>

<!-- Content -->
<div class="container-fluid invoice-pdf">

    <div class="jumbotron" id="detail">

        <!-- Content -->

        <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" id="form1">
            <div class="row">
                <div class="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                    <h2>Invoice</h2>
                    <h4>Order ID: ${invoice.id}</h4>
                </div>
                <div class="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" align="right">
                    <h5>Order Date:</h5>
                    <h5>${invoice.orderDate}</h5>
                </div>
                <div class="stripe"></div><br>
            </div>

            <div class="row">
                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col- lg-12 col-xl-12">
                    <h3>Order Details:</h3><br>
                </div>
            </div>

            <div class="box-window">
                <c:forEach var="orderDetails" items="${orderDetails}">
                    <div class="row">
                        <div class="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                            <p class="invoice">${orderDetails.itemName}</p>
                        </div>
                        <div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3" align="center">
                            <p>x${orderDetails.quantity}</p>
                        </div>
                        <div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3" align="right">
                            Rp ${orderDetails.price},-
                        </div>
                    </div>
                </c:forEach>


                <div class="row">
                    <div class="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6"><br>
                        <p>Total: Rp. ${invoice.memberId == 0 ? invoice.totalPrice : invoice.totalPrice * 100 / (100 - promo.discountAmount) } ,-</p>
                        <p>Member Discount: ${invoice.memberId == 0 ? "-" : promo.discountAmount} %</p>
                    </div>

                    <div class="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" align="right">
                        <br>
                        <p>Amount Payable</p>
                        <p><strong>Rp. ${invoice.totalPrice} ,-</strong></p>
                    </div>
                </div>

                <div class="row" data-html2canvas-ignore="true">
                    <button type="submit" class="btn btn-default print-invoice" id="accept">Print ></button>
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

            if(${invoice.memberId == 0 ? false : true}){
                var email;
                var name;
                $.ajax({
                    type: 'POST',
                    url: "/cashier/member",
                    dataType: "JSON",
                    data: {
                        memberid: ${invoice.memberId}
                    },
                    success: function (response) {
                        var head = '<%@ include file = "/include/emailHead.jsp" %>';
                        var foot = '<%@ include file = "/include/emailFoot.jsp" %>';
                        var content =
                            '       <table width="100%" cellpadding="0" cellspacing="0">\n' +
                            '        <tr>\n' +
                            '         <td class="content-block">\n' +
                            '          <h1 class="aligncenter">' + ${invoice.totalPrice} + ' Paid</h1>\n' +
                            '         </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '         <td class="content-block">\n' +
                            '          <h2 class="aligncenter">Thanks for using BlibliMovies</h2>\n' +
                            '         </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '         <td class="content-block aligncenter">\n' +
                            '          <table class="invoice">\n' +
                            '           <tr>\n' +
                            '               <td>Order ID: ' + ${invoice.id} + '<br>Order Date: ' + '${invoice.orderDate}' + '</td>\n' +
                            '           </tr>\n' +
                            '           <tr>\n' +
                            '            <td>\n' +
                            '             <table class="invoice-items" cellpadding="0" cellspacing="0">\n';

                        <c:forEach var="orderDetails" items="${orderDetails}">
                        content +=
                            '              <tr>\n' +
                            '               <td>' + '${orderDetails.itemName}' + ' x' + ${orderDetails.quantity}  +'</td>\n' +
                            '               <td class="alignright">Rp ' + ${orderDetails.price} + ',-</td>\n' +
                            '              </tr>\n';
                        </c:forEach>

                        content +=
                            '              <tr class="total">\n' +
                            '               <td>Price: ' + ${invoice.totalPrice * 100 / (100 - promo.discountAmount)} + '<br>Member Discount: ' + ${promo.discountAmount} + '</td>\n' +
                            '               <td class="alignright">Amount Payable<br>Rp ' + ${invoice.totalPrice} + ',-</td>\n' +
                            '              </tr>\n' +
                            '             </table>\n' +
                            '            </td>\n' +
                            '           </tr>\n' +
                            '          </table>\n' +
                            '         </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '         <td class="content-block aligncenter">\n' +
                            '          BlibliMovies\n' +
                            '         </td>\n' +
                            '        </tr>\n' +
                            '       </table>\n';

                        $.ajax({
                            type: 'POST',
                            url: "https://api.elasticemail.com/v2/email/send",
                            dataType: "JSON",
                            data: {
                                apikey: "312ad91b-ecd9-48bf-b0db-14b304b67fe0",
                                bodyHtml: head + content + foot,
                                from: "wendydamar.wb@gmail.com",
                                fromName: "Bliblimovies",
                                to: response.email,
                                replyToName: response.fullname,
                                subject: "Thank You!"
                            },
                            success: function (response) {
                                console.log(response);
                            },
                            error: function (response) {
                                console.log(response);
                            }
                        });
                    }
                });
            }
        });
    </script>

<%@ include file = "/include/foot.jsp" %>