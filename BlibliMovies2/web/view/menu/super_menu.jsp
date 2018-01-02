<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarSuperAdmin.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Hai Super Admin</h1>
    </div>

    <!-- Menu -->
    <div class="container">
        <div class="row">
            <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" onclick="document.location.href = '/admin/storeaccount';">
                <div class="circle" style="background-image: url('/assets/icon/filmTransaction.png')"></div>
                <p>Store Account</p>
            </div>

            <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" onclick="document.location.href = '/admin/superadmin';">
                <div class="circle" style="background-image: url('/assets/icon/fnbTransaction.png')"></div>
                <p>Super Admin</p>
            </div>
        </div>
    </div>

</div>

<%@ include file = "/include/foot.jsp" %>
