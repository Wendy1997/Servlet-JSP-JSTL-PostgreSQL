<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Studio</h1>
        <a href="/admin/studiotype"><h6>Type Database</h6></a><br>
        <a href="/admin/studio/add"><h5>Create New Studio</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${studios}" var="studios">
                        <tr>
                            <td scope="row"><c:out value="${studios.id}"></c:out></td>
                            <td><c:out value="${studios.name}"></c:out></td>
                            <td><c:out value="${studios.type}"></c:out></td>
                            <td><c:out value="${studios.price}"></c:out></td>
                            <td><a <c:out value='href=/admin/studio/edit?id=${studios.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/studio/delete?id=${studios.id}'></c:out>>Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>