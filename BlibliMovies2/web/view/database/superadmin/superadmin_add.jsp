<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarSuperAdmin.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Super Admin</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/superadmin/add" method="post" onsubmit="return confirm('Are You Sure?');">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Super Admin</h2><br>

                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="name" class="form-control" name="username" id="username" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="name" class="form-control" name="password" id="password" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file = "/include/foot.jsp" %>