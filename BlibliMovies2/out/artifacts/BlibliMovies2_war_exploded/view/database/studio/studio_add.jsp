<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'account_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Studio</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/studio/add" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Studio</h2><br>

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="name" class="form-control" name="name" id="name" required>
                    </div>

                    <div class="form-group">
                        <label for="type">Type:</label>
                        <select class="form-control" id="type" name="type" required>
                            <option disabled selected value> -- pilih Tipe Studio -- </option>
                            <c:forEach items="${type}" var="type">
                                <option value="${type.type}">${type.type}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="number" class="form-control" name="price" id="price" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file = "/include/foot.jsp" %>