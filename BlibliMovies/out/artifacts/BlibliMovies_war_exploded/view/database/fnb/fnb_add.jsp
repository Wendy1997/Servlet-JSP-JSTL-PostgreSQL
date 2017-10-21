<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'fnb_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Food and Beverages</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/fnb/add" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Food and Beverages</h2><br>

                    <div class="form-group">
                        <label for="cover">Cover:</label>
                        <input type="name" class="form-control" id="cover" name="cover" required>
                    </div>

                    <div class="form-group">
                        <label for="name">Nama:</label>
                        <input type="name" class="form-control" id="name" name="name" required>
                    </div>

                    <div class="form-group">
                        <label for="type">Jenis:</label>
                        <select class="form-control" id="type" name="type" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <option value="Food">Food</option>
                            <option value="Beverages">Beverages</option>
                            <option value="Combo">Combo</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="size">Ukuran:</label>
                        <select class="form-control" id="size" name="size" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <option value="Regular">Regular</option>
                            <option value="Large">Large</option>
                            <option value="Jumbo">Jumbo</option>
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