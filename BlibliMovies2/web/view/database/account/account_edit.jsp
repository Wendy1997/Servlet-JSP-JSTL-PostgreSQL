<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Account</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/account/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Account</h2><br>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="name" class="form-control" name="password" id="password" value="${account.password}" required>
                    </div>

                    <div class="form-group">
                        <label for="role">Role:</label>
                        <select class="form-control" id="role" name="role" value="${account.roleid}" required>
                            <option disabled selected value> -- pilih Role -- </option>
                            <c:forEach var="role" items="${role}">
                                <option value="${role.id}" ${role.id == account.roleid ? 'selected' : ''}>${role.role}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="hidden" name="username" value="${account.username}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>