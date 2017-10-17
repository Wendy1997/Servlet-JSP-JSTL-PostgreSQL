<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Film</h1><br>
        <a href="/admin/film/add"><h5>Create New Film</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Cover</th>
                    <th>Nama</th>
                    <th>Genre</th>
                    <th>Director</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>

                    <c:forEach items="${films}" var="films">
                    <tr>
                        <td scope="row"><c:out value="${films.id}"></c:out></td>
                        <td><c:out value="${films.cover}"></c:out></td>
                        <td><c:out value="${films.title}"></c:out></td>
                        <td><c:out value="${films.genre}"></c:out></td>
                        <td><c:out value="${films.director}"></c:out></td>
                        <td><a href="/admin/film/edit">Edit</a></td>
                        <td><a href="/admin/film/delete">Delete</a></td>
                    </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>