<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Accounts</h1><br>
        <a href="/admin/account/add"><h5>Create New Accounts</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${accounts}" var="accounts">
                        <tr>
                            <td scope="row"><c:out value="${accounts.username}"></c:out></td>
                            <td><c:out value="${accounts.password}"></c:out></td>
                            <td>
                                 <c:forEach items="${roles}" var="role">
                                    ${role.id == accounts.roleid ? role.role : ""}
                                 </c:forEach>
                             </td>
                            <td><a <c:out value='href=/admin/account/edit?id=${accounts.username}'></c:out>>Edit</a></td>
                            <td><a href=/admin/account/delete?id=${accounts.username}>${accounts.status ? "Delete" : "Retrieve"}</a></td>
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
                url: "/admin/account/page",
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
                            '<td>\n';

                        <c:forEach items="${roles}" var="role">
                        if(result[key].roleid == ${role.id}){
                            output += '${role.role}';
                        }
                        </c:forEach>

                        output += '</td>\n' +
                            '<td><a href=/admin/account/edit?id=' + result[key].username + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/account/delete?id=' + result[key].username + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/account/delete?id=' + result[key].username + '>Retrieve</a></td>\n';
                        }
                    }

                    $('tbody')[0].innerHTML = output;
                },
                error : function (response) {
                    console.log(response);
                }
            });
        })
    });
</script>


<%@ include file = "/include/foot.jsp" %>