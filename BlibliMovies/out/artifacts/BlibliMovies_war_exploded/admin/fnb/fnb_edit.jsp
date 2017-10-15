<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'fnb_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Food and Beverages</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="fnb_edit_confirmation.html">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Food and Beverages</h2><br>

                    <div class="form-group">
                        <label for="cover">Cover:</label>
                        <input type="name" class="form-control" id="cover" required>
                    </div>

                    <div class="form-group">
                        <label for="nama">Nama:</label>
                        <input type="name" class="form-control" id="nama" required>
                    </div>

                    <div class="form-group">
                        <label for="jenis">Jenis:</label>
                        <select class="form-control" id="jenis" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <option>Food</option>
                            <option>Beverages</option>
                            <option>Combo</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="harga">Harga:</label>
                        <input type="number" class="form-control" id="harga" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>