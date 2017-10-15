<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'account_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Account</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="account_create_confirmation.html">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Account</h2><br>

                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="name" class="form-control" id="username" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="name" class="form-control" id="password" required>
                    </div>

                    <div class="form-group">
                        <label for="role">Role:</label>
                        <select class="form-control" id="role" required>
                            <option disabled selected value> -- pilih Role -- </option>
                            <option>Admin</option>
                            <option>Cashier</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file = "/include/foot.jsp" %>