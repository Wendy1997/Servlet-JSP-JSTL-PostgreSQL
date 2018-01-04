<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="/admin">&times;</a>

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
                            <td>
                                <c:forEach items="${type}" var="type">
                                    ${type.id == studios.type ? type.type : ""}
                                </c:forEach>
                            </td>
                            <td><c:out value="${studios.price}"></c:out></td>
                            <td><a <c:out value='href=/admin/studio/edit?id=${studios.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/studio/delete?id=${studios.id}'></c:out>>${studios.status ? "Delete" : "Retrieve"}</a></td>
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
                url: "/admin/studio/page",
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
                            '<td>' + result[key].name + '</td>\n' +
                            '<td>\n';

                        <c:forEach items="${type}" var="type">
                        if(result[key].type == ${type.id}){
                            output += '${type.type}';
                        }
                        </c:forEach>

                        output += '</td>\n' +
                            '<td>' + result[key].price + '</td>\n' +
                            '<td><a href=/admin/studio/edit?id=' + result[key].id + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/studio/delete?id=' + result[key].id + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/studio/delete?id=' + result[key].id + '>Retrieve</a></td>\n';
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