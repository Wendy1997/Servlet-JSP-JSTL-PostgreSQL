<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Hai <c:out value="${sessionScope.username}"></c:out> from <c:out value="${sessionScope.storename}"></c:out></h1>
    </div>

    <!-- Menu -->
    <div class="container">
        <div class="row">
            <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4" onclick="document.location.href = '/cashier/film';">
                <div class="circle" style="background-image: url('/assets/icon/filmTransaction.png')"></div>
                <p>Film Transaction</p>
            </div>

            <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4" onclick="document.location.href = '/cashier/fnb';">
                <div class="circle" style="background-image: url('/assets/icon/fnbTransaction.png')"></div>
                <p>FnB Transaction</p>
            </div>

            <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4" onclick="document.location.href = '/admin/membercard/add';">
                <div class="circle" style="background-image: url('/assets/icon/member.png')"></div>
                <p>Add Member Card</p>
            </div>
        </div>
    </div>

</div>

<%@ include file = "/include/foot.jsp" %>
