<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="container-fluid thumnail col-lg-8" align="center" >
            <!-- Title -->
            <!-- <div >
                <h1 class="title">Food and Beverages</h1><br>
            </div> -->

            <!-- List fnb -->

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class=" nav navbar-nav" >
                        <div class="row">
                            <li><a href="#" class="tab">Ticket</a></li>
                            <li><a href="#" class="tab">Pick Your Seat</a></li>
                            <li><a href="#" class="tab active-tab">Snack and Beverages</a></li>
                        </div>
                    </ul>
                </div>
            </nav>

            <div class="stripe"></div><br>

            <div class="container">
                <div class="fnbcontainer-responsive row">
                    <c:forEach items="${fnblist}" var="fnb">
                        <div id="${fnb.name} ${fnb.size}" class="col-md-2">
                            <div>
                                <img class="circle" src="src/img/popcorn.jpg" style="width:120px;height:120px;">
                            </div>
                            <p>${fnb.name} ${fnb.size}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- <div class="container-fluid">
                <form class="row" action="">
                  <input type="radio" name="food" value="food"> Food
                  <input type="radio" name="beverages" value="beverages"> Beverages
                  <input type="radio" name="combo" value="combo"> Combo
                </form>
            </div> -->

        </div>



        <div class="container-fluid thumnail col-lg-4" align="center">
            <!-- Title -->
            <div >
                <h3>Summary</h3>
            </div>
            <div class="stripe"></div><br>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h5>Order Details:</h5>
                    </div>
                </div>

                <div id="keranjang" class="box-window summarycontainer-responsive">

                    <div class="row">
                        <div class="col-lg-7">
                            <div class="row box">
                                <div class="col-lg-4">
                                    <div class="smallCircle"></div>
                                </div>
                                <div class="col-lg-8">
                                    <p class="invoice">Lalallaasjfbaldjbakdfbakdfbjak</p>
                                    <p class="invoice">asddbdgbdfwdcadvwva</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2" align="center">
                            <p>x3</p>
                        </div>
                        <div class="col-lg-3" align="right">
                            Rp 25.000,-
                        </div>
                    </div>

                </div>

                <div class="row">
                    <dir class="col-lg-6">
                        <p>Total: Rp. 215.000 ,-</p>
                        <p>Member Discount: 20%</p>
                    </dir>

                    <dir class="col-lg-6" align="right">
                        <p>Amount Payable</p>
                        <p><strong>Rp. 217.000 ,-</strong></p>
                    </dir>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>

<script>
    $(document).ready(function () {


       <%--$('.col-md-2').click(function () {--%>
           <%--$.ajax({--%>
               <%--type: 'POST',--%>
               <%--url: "/cashier/seat",--%>
               <%--dataType: "JSON",--%>
               <%--data: {tickets: listTicket.toString(),--%>
                   <%--filmid: ${filmid},--%>
                   <%--screeningid: ${screeningid},--%>
                   <%--studioid: ${studioid}--%>
               <%--},--%>
               <%--success: window.location.href = "/cashier/fnb"--%>
           <%--});--%>
      
</script>