<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Types</h1><br>
        <a href="/admin/studiotype/add"><h5>Create New Types</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Type</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${type}" var="type">
                        <tr>
                            <td scope="row"><c:out value="${type.id}"></c:out></td>
                            <td><c:out value="${type.type}"></c:out></td>
                            <td><a <c:out value='href=/admin/studiotype/edit?id=${type.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/studiotype/delete?id=${type.id}'></c:out>>${type.status ? "Delete" : "Retrieve"}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>