<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarSuperAdmin.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Store Accounts</h1><br>
        <a href="/admin/storeaccount/add"><h5>Create New Store Accounts</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Store Name</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${storeaccounts}" var="storeaccounts">
                        <tr>
                            <td scope="row"><c:out value="${storeaccounts.username}"></c:out></td>
                            <td><c:out value="${storeaccounts.password}"></c:out></td>
                            <td><c:out value="${storeaccounts.nama}"></c:out></td>
                            <td><a href=/admin/storeaccount/edit?id=${storeaccounts.username}>Edit</a></td>
                            <td><a href=/admin/storeaccount/delete?id=${storeaccounts.username}>${storeaccounts.status ? "Delete" : "Retrieve"}</a></td>
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
                url: "/admin/storeaccount/page",
                dataType: "JSON",
                data: {
                    page: $(this).text()
                },
                success: function (response) {
                    var output = "";

                    var result = response["result"];
                    for(var key in result){
                        output += '<tr>\n' +
                            '<td scope="row">' + result[key].username + '</td>\n' +
                            '<td>' + result[key].password + '</td>\n' +
                            '<td>' + result[key].nama + '</td>\n' +
                            '<td><a href=/admin/storeaccount/edit?id=' + result[key].username + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/storeaccount/delete?id=' + result[key].username + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/storeaccount/delete?id=' + result[key].username + '>Retrieve</a></td>\n';
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