<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Member Card</h1><br>
        <a href="/admin/membercard/add"><h5>Create New Member Card</h5></a>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Nama</th>
                    <th>Gender</th>
                    <th>Birth Date</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${memberCards}" var="memberCards">
                        <tr>
                            <td scope="row"><c:out value="${memberCards.id}"></c:out></td>
                            <td><c:out value="${memberCards.fullname}"></c:out></td>
                            <td>
                                <c:forEach items="${gender}" var="gender">
                                    ${gender.id == memberCards.gender ? gender.gender : ""}
                                </c:forEach>
                            </td>
                            <td><c:out value="${memberCards.birthDate}"></c:out></td>
                            <td><c:out value="${memberCards.phoneNumber}"></c:out></td>
                            <td><c:out value="${memberCards.email}"></c:out></td>
                            <td><a <c:out value='href=/admin/membercard/edit?id=${memberCards.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/membercard/delete?id=${memberCards.id}'></c:out>>${memberCards.status ? "Delete" : "Retrieve"}</a></td>
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
                url: "/admin/membercard/page",
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
                            '<td>' + result[key].fullname + '</td>\n' +
                            '<td>\n';

                        <c:forEach items="${gender}" var="gender">
                        if(result[key].gender == ${gender.id}){
                            output += '${gender.gender}';
                        }
                        </c:forEach>

                        output += '<td>' + result[key].birthDate + '</td>\n' +
                            '<td>' + result[key].phoneNumber + '</td>\n' +
                            '<td>' + result[key].email + '</td>\n';

                        output += '</td>\n' +
                            '<td><a href=/admin/membercard/edit?id=' + result[key].id + '>Edit</a></td>\n';

                        if(result[key].status){
                            output += '<td><a href=/admin/membercard/delete?id=' + result[key].id + '>Delete</a></td>\n';
                        } else {
                            output += '<td><a href=/admin/membercard/delete?id=' + result[key].id + '>Retrieve</a></td>\n';
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