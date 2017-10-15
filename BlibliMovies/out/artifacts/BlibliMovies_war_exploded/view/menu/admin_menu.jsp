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
            <div class="col-lg-2" onclick="document.location.href = '/admin/film';">
                <div class="circle"></div>
                <p>Film</p>
            </div>

            <div class="col-lg-2" onclick="document.location.href = '/admin/fnb';">
                <div class="circle"></div>
                <p>Food and Beverages</p>
            </div>

            <div class="col-lg-2" onclick="document.location.href = '/admin/member';">
                <div class="circle"></div>
                <p>Member Card</p>
            </div>

            <div class="col-lg-2" onclick="document.location.href = '/admin/invoice';">
                <div class="circle"></div>
                <p>Invoice</p>
            </div>

            <div class="col-lg-2" onclick="document.location.href = '/admin/ledger';">
                <div class="circle"></div>
                <p>Ledger</p>
            </div>

            <div class="col-lg-2" onclick="document.location.href = '/admin/account';">
                <div class="circle"></div>
                <p>Account</p>
            </div>
        </div>
    </div>

</div>

<%@ include file = "/include/foot.jsp" %>
