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
        <form action="/admin/account/add" method="post">
            <div class="row">
                <div class="col-12 col-xs-12 col-sm-12 col-md-8 col-lg-6 col-xl-6" id="form1">
                    <h2>Data Account</h2><br>

                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="name" class="form-control" name="username" id="username" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="name" class="form-control" name="password" id="password" required>
                    </div>

                    <div class="form-group">
                        <label for="role">Role:</label>
                        <select class="form-control" id="role" name="role" required>
                            <option disabled selected value> -- pilih Role -- </option>
                            <c:forEach var="role" items="${role}">
                                <option value="${role.role}">${role.role}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file = "/include/foot.jsp" %>