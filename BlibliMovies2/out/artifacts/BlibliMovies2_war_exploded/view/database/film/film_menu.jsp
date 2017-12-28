<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Film</h1>
        <a href="/admin/filmgenre"><h6>Genre Database</h6></a><br>
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
                    <th></th>
                </tr>
                </thead>

                <tbody>

                    <c:forEach items="${films}" var="films">
                    <tr>
                        <td scope="row"><c:out value="${films.id}"></c:out></td>
                        <td><c:out value="${films.cover}"></c:out></td>
                        <td><c:out value="${films.title}"></c:out></td>
                        <td>
                            <c:forEach items="${genre}" var="genre">
                                ${genre.id == films.genre ? genre.genre : ""}
                            </c:forEach>
                        </td>
                        <td><c:out value="${films.director}"></c:out></td>
                        <td><a <c:out value='href=/admin/film/edit?id=${films.id}'></c:out>>Edit</a></td>
                        <td><a <c:out value='href=/admin/film/delete?id=${films.id}'></c:out>>Delete</a></td>
                        <td><a <c:out value='href=/admin/film/detail?id=${films.id}'></c:out>>Detail</a></td>
                    </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>