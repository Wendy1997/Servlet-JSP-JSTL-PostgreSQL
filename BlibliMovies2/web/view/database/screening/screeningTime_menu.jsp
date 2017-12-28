<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Screening Time</h1><br>
        <a href="/admin/screentime/add?filmid=${film.id}&duration=${film.duration}"><h5>Create New Screening Time</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Film ID</th>
                    <th>Studio ID</th>
                    <th>Time</th>
                    <th>Duration</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${screenTime}" var="screenTime">
                    <tr>
                        <td scope="row"><c:out value="${screenTime.id}"></c:out></td>
                        <td><c:out value="${screenTime.filmId}"></c:out></td>
                        <td><c:out value="${screenTime.studioId}"></c:out></td>
                        <td><c:out value="${screenTime.time}"></c:out></td>
                        <td><c:out value="${screenTime.duration}"></c:out></td>
                        <td><a href="/admin/screentime/update?id=${screenTime.id}&filmid=${film.id}&duration=${film.duration}"}>Edit</a></td>
                        <td><a href="/admin/screentime/delete?id=${screenTime.id}&filmid=${film.id}"}>Delete</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>