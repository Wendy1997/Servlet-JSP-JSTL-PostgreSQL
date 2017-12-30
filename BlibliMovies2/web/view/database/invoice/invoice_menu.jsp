<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Invoice</h1><br>
    </div>

    <!-- Form -->
    <div class="container">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>has Member?</th>
                    <th>Member ID</th>
                    <th>Amount</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${invoices}" var="invoices">
                        <tr>
                            <td scope="row"><c:out value="${invoices.id}"></c:out></td>
                            <td><c:out value="${invoices.orderDate}"></c:out></td>
                            <td><c:out value="${invoices.totalPrice}"></c:out></td>
                            <td><c:out value="${invoices.memberId == 0 ? 'False' : 'True'}"></c:out></td>
                            <td><c:out value="${invoices.memberId == 0 ? 'Null' : invoices.memberId}"></c:out></td>
                            <td><c:out value="${invoices.totalPrice}"></c:out></td>
                            <td><a <c:out value='href=/admin/invoice/detail?id=${invoices.id}'></c:out>>Detail</a></td>
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

<%@ include file = "/include/foot.jsp" %>