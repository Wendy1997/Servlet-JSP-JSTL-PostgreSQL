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
            <div class="col-lg-6" onclick="document.location.href = '/cashier/film';">
                <div class="circle"></div>
                <p>Film Transaction</p>
            </div>

            <div class="col-lg-6" onclick="document.location.href = '/cashier/fnb';">
                <div class="circle"></div>
                <p>FnB Transaction</p>
            </div>
        </div>
    </div>

</div>

<%@ include file = "/include/foot.jsp" %>