<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'invoice_menu.html';">&times;</button>

    <div class="jumbotron" id="detail">

        <!-- Content -->

        <div class="col-lg-9" id="form1">
            <div class="row">
                <div class="col-lg-8">
                    <h2>Invoice</h2>
                    <h3>Order ID: ${invoice.id}</h3>
                    <div class="stripe"></div><br>
                </div>
                <div class="col-lg-4" align="right">
                    <h4>Order Date:</h4>
                    <h4>${invoice.orderDate}</h4>
                </div>
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
                            <div class="row box">
                                <div class="col-lg-10">
                                    <p class="invoice">${orderDetails.itemName}</p>
                                        <%--<p class="invoice">asddbdgbdfwdcadvwva</p>--%>
                                </div>
                            </div>
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
                <div class="col-lg-6"><br>
                    <p>Total: Rp. ${invoice.memberId == 0 ? invoice.totalPrice : invoice.totalPrice * 100 / (100 - promo.discountAmount)} ,-</p>
                    <p>Member Discount: ${invoice.memberId == 0 ? "-" : promo.discountAmount} %</p>
                </div>

                <div class="col-lg-6" align="right">
                    <p>Amount Payable</p>
                    <p><strong>Rp. ${invoice.totalPrice} ,-</strong></p>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>