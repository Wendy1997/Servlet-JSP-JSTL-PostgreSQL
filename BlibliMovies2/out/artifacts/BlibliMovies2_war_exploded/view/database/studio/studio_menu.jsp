<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Genres</h1><br>
        <a href="/admin/filmgenre/add"><h5>Create New Genres</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Genre</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${genre}" var="genre">
                        <tr>
                            <td scope="row"><c:out value="${genre.id}"></c:out></td>
                            <td><c:out value="${genre.genre}"></c:out></td>
                            <td><a <c:out value='href=/admin/filmgenre/edit?id=${genre.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/filmgenre/delete?id=${genre.id}'></c:out>>Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>