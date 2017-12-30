<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Member Card</h1><br>
        <a href="/admin/membercard/add"><h5>Create New Member Card</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Nama</th>
                    <th>Gender</th>
                    <th>Birth Date</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${memberCards}" var="memberCards">
                        <tr>
                            <td scope="row"><c:out value="${memberCards.id}"></c:out></td>
                            <td><c:out value="${memberCards.fullname}"></c:out></td>
                            <td>
                                <c:forEach items="${gender}" var="gender">
                                    ${gender.id == memberCards.gender ? gender.gender : ""}
                                </c:forEach>
                            </td>
                            <td><c:out value="${memberCards.birthDate}"></c:out></td>
                            <td><c:out value="${memberCards.phoneNumber}"></c:out></td>
                            <td><c:out value="${memberCards.email}"></c:out></td>
                            <td><a <c:out value='href=/admin/membercard/edit?id=${memberCards.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/membercard/delete?id=${memberCards.id}'></c:out>>${memberCards.status ? "Delete" : "Retrieve"}</a></td>
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