<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Food and Beverages</h1>
        <a href="/admin/fnbsize" style="display: inline-block"><h6>Size Database</h6></a>
        |
        <a href="/admin/fnbtype" style="display: inline-block"><h6>Type Database</h6></a><br><br>
        <a href="/admin/fnb/add"><h5>Create New Food and Beverages</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Cover</th>
                    <th>Nama</th>
                    <th>Jenis</th>
                    <th>Ukuran</th>
                    <th>Harga</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${fnbs}" var="fnbs">
                        <tr>
                            <td scope="row"><c:out value="${fnbs.id}"></c:out></td>
                            <td><c:out value="${fnbs.cover}"></c:out></td>
                            <td><c:out value="${fnbs.name}"></c:out></td>
                            <td>
                                <c:forEach items="${type}" var="type">
                                    ${type.id == fnbs.type ? type.type : ""}
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach items="${size}" var="size">
                                    ${size.id == fnbs.size ? size.size : ""}
                                </c:forEach>
                            </td>
                            <td><c:out value="${fnbs.price}"></c:out></td>
                            <td><a <c:out value='href=/admin/fnb/edit?id=${fnbs.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/fnb/delete?id=${fnbs.id}'></c:out>>${fnbs.status ? "Delete" : "Retrieve"}</a></td>
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
                url: "/admin/fnb/page",
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
                            '<td>' + result[key].name + '</td>\n' +
                            '<td>\n';

                        <c:forEach items="${type}" var="type">
                        if(result[key].type == ${type.id}){
                            output += '${type.type}';
                        }
                        </c:forEach>

                        output += '</td>\n' +
                            '<td>\n';

                        <c:forEach items="${size}" var="size">
                        if(result[key].size == ${size.id}){
                            output += '${size.size}';
                        }
                        </c:forEach>

                        output += '</td>\n' +
                            '<td><a href=/admin/fnb/edit?id=' + result[key].id + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/fnb/delete?id=' + result[key].id + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/fnb/delete?id=' + result[key].id + '>Retrieve</a></td>\n';
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