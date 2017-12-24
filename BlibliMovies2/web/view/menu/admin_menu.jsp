<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Hai <c:out value="${sessionScope.role}"></c:out> from <c:out value="${sessionScope.storename}"></c:out></h1>
    </div>

    <!-- Menu -->
    <div class="container">
        <div class="row">
            <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2" onclick="document.location.href = '/admin/film';">
                <div class="circle"></div>
                <p>Film</p>
            </div>

            <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2" onclick="document.location.href = '/admin/fnb';">
                <div class="circle"></div>
                <p>Food and Beverages</p>
            </div>

            <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2" onclick="document.location.href = '/admin/membercard';">
                <div class="circle"></div>
                <p>Member Card</p>
            </div>

            <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2" onclick="document.location.href = '/admin/invoice';">
                <div class="circle"></div>
                <p>Invoice</p>
            </div>

            <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2" onclick="document.location.href = '/admin/ledger';">
                <div class="circle"></div>
                <p>Ledger</p>
            </div>

            <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2" onclick="document.location.href = '/admin/account';">
                <div class="circle"></div>
                <p>Account</p>
            </div>
        </div>

        <div class="row" style="float: none; margin: 0 auto;">
            <%--<div class="col-lg-2"></div>--%>
            <%--<div class="col-lg-2"></div>--%>
            <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" onclick="document.location.href = '/cashier/film';">
                <div class="circle"></div>
                <p>Film Transaction</p>
            </div>

            <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" onclick="document.location.href = '/cashier/fnb';">
                <div class="circle"></div>
                <p>FnB Transaction</p>
            </div>
        </div>
    </div>

</div>

<%@ include file = "/include/foot.jsp" %>
