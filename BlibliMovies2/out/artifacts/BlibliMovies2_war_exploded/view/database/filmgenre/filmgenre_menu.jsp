<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="/admin/film">&times;</a>

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
                            <td><a <c:out value='href=/admin/filmgenre/delete?id=${genre.id}'></c:out>>${genre.status ? "Delete" : "Retrieve"}</a></td>
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
                url: "/admin/filmgenre/page",
                dataType: "JSON",
                data: {
                    page: $(this).text()
                },
                success: function (response) {
                    var output = "";

                    var result = response["result"];
                    for(var key in result){
                        output += '<tr>\n' +
                            '<td scope="row">' + result[key].id + '</td>\n' +
                            '<td>' + result[key].genre + '</td>\n';

                        output += '<td><a href=/admin/filmgenre/edit?id=' + result[key].id + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/filmgenre/delete?id=' + result[key].id + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/filmgenre/delete?id=' + result[key].id + '>Retrieve</a></td>\n';
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