<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Food and Beverages</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/fnb/add" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Food and Beverages</h2><br>

                    <div class="form-group">
                        <label for="file">Cover:</label>
                        <input type="file" class="form-control" id="file" name="file" required>
                    </div>

                    <div class="form-group">
                        <label for="name">Nama:</label>
                        <input type="name" class="form-control" id="name" name="name" required>
                    </div>

                    <div class="form-group">
                        <label for="type">Jenis:</label>
                        <select class="form-control" id="type" name="type" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <c:forEach items="${type}" var="type">
                                <option value="${type.id}">${type.type}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="size">Ukuran:</label>
                        <select class="form-control" id="size" name="size" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <c:forEach items="${size}" var="size">
                                <option value="${size.size}">${size.size}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="price">Harga:</label>
                        <input type="number" class="form-control" id="price" name="price" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>