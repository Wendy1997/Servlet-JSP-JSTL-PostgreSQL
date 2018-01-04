<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>


<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="/admin">&times;</a>

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
                        <td><a <c:out value='href=/admin/film/delete?id=${films.id}'></c:out>>${films.status ? "Delete" : "Retrieve"}</a></td>
                        <td><a <c:out value='href=/admin/film/detail?id=${films.id}'></c:out>>Detail</a></td>
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
                url: "/admin/film/page",
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
                            '<td>' + result[key].cover + '</td>\n' +
                            '<td>' + result[key].title + '</td>\n' +
                            '<td>\n';

                        <c:forEach items="${genre}" var="genre">
                            if(result[key].genre == ${genre.id}){
                                output += '${genre.genre}'
                            }
                        </c:forEach>

                        output += '</td>\n' +
                            '<td>' + result[key].director + '</td>\n' +
                            '<td><a href=/admin/film/edit?id=' + result[key].id + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/film/delete?id=' + result[key].id + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/film/delete?id=' + result[key].id + '>Retrieve</a></td>\n';
                        }

                           output+= '<td><a href=/admin/film/detail?id=' + result[key].id + '>Detail</a></td>\n' +
                            '</tr>';
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