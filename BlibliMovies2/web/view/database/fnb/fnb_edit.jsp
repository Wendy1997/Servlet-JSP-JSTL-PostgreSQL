<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Food and Beverages</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/fnb/edit" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Food and Beverages</h2><br>

                    <div class="form-group">
                        <label for="file">Cover:</label>
                        <input type="file" class="form-control" id="file" name="file" value="${film.cover}">
                    </div>

                    <div class="form-group">
                        <label for="name">Nama:</label>
                        <input type="name" class="form-control" name="name" id="name" value="${fnb.name}" required>
                    </div>

                    <div class="form-group">
                        <label for="type">Jenis:</label>
                        <select class="form-control" id="type" name="type" value="${fnb.type}" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <c:forEach items="${}">

                            </c:forEach>
                            <option value="Food" ${fnb.type == 'Food' ? 'selected' : ''}>Food</option>
                            <option value="Beverages" ${fnb.type == 'Beverages' ? 'selected' : ''}>Beverages</option>
                            <option value="Combo" ${fnb.type == 'Combo' ? 'selected' : ''}>Combo</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="size">Ukuran:</label>
                        <select class="form-control" id="size" name="size" value="${fnb.size}" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <option value="Regular" ${fnb.size == 'Regular' ? 'selected' : ''}>Regular</option>
                            <option value="Large" ${fnb.size == 'Large' ? 'selected' : ''}>Large</option>
                            <option value="Jumbo" ${fnb.size == 'Combo' ? 'selected' : ''}>Jumbo</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="price">Harga:</label>
                        <input type="number" class="form-control" id="price" name="price" value="${fnb.price}" required>
                    </div>

                    <input type="hidden" name="id" value="${fnb.id}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>