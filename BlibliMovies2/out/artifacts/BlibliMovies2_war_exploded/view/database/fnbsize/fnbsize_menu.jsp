<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Sizes</h1><br>
        <a href="/admin/fnbsize/add"><h5>Create New Sizes</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Size</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${size}" var="size">
                        <tr>
                            <td scope="row"><c:out value="${size.id}"></c:out></td>
                            <td><c:out value="${size.size}"></c:out></td>
                            <td><a <c:out value='href=/admin/fnbsize/edit?id=${size.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/fnbsize/delete?id=${size.id}'></c:out>>${size.status ? "Delete" : "Retrieve"}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>