<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="/admin/film/detail?id=${film.id}">&times;</a>

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
                        <td><a href="/admin/screentime/delete?id=${screenTime.id}&filmid=${film.id}"}>${screenTime.status ? "Delete" : "Retrieve"}</a></td>
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


<script>
    $(document).ready(function () {
        $('.page-link').click(function () {
            $.ajax({
                type: 'GET',
                url: "/admin/screentime/page",
                dataType: "JSON",
                data: {
                    page: $(this).text(),
                    filmid: ${film.id}
                },
                success: function (response) {
                    var output = "";

                    var result = response["result"];
                    for(var key in result){
                        output += '<tr>\n' +
                            '<td scope="row">' + result[key].id + '</td>\n' +
                            '<td>' + result[key].filmId + '</td>\n' +
                            '<td>' + result[key].studioId + '</td>\n' +
                            '<td>' + result[key].time + '</td>\n' +
                            '<td>' + result[key].duration + '</td>\n';

                        output += '</td>\n' +
                            '<td><a href=/admin/screentime/update?id=' + result[key].id + '&filmid=' + ${film.id} + '&duration=' + ${film.duration} +'>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/screentime/delete?id=' + result[key].id + '&filmid=' + ${film.id} + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/screentime/delete?id=' + result[key].id + '&filmid=' + ${film.id} + '>Retrieve</a></td>\n';
                        }
                    }

                    $('tbody')[0].innerHTML = output;
                },
                error : function (response) {
                    console.log(response);
                }
            });
            return false;
        })
    });
</script>

<%@ include file = "/include/foot.jsp" %>