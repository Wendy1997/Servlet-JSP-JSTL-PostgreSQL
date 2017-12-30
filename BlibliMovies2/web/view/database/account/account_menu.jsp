<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Accounts</h1><br>
        <a href="/admin/account/add"><h5>Create New Accounts</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${accounts}" var="accounts">
                        <tr>
                            <td scope="row"><c:out value="${accounts.username}"></c:out></td>
                            <td><c:out value="${accounts.password}"></c:out></td>
                            <td>
                                 <c:forEach items="${roles}" var="role">
                                    ${role.id == accounts.roleid ? role.role : ""}
                                 </c:forEach>
                             </td>
                            <td><a <c:out value='href=/admin/account/edit?id=${accounts.username}'></c:out>>Edit</a></td>
                            <td><a href=/admin/account/delete?id=${accounts.username}>${accounts.status ? "Delete" : "Retrieve"}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination pagination-sm justify-content-center">
                    <c:forEach begin="1" end="${page}" varStatus="loop">
                        <li class="page-item"><a class="page-link" href="#">${loop.index}</a></li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>