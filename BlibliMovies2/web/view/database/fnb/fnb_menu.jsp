<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Food and Beverages</h1>
        <a href="/admin/fnbsize" style="display: inline-block"><h6>Size Database</h6></a>
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
                            <td><c:out value="${fnbs.type}"></c:out></td>
                            <td><c:out value="${fnbs.size}"></c:out></td>
                            <td><c:out value="${fnbs.price}"></c:out></td>
                            <td><a <c:out value='href=/admin/fnb/edit?id=${fnbs.id}'></c:out>>Edit</a></td>
                            <td><a <c:out value='href=/admin/fnb/delete?id=${fnbs.id}'></c:out>>Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>