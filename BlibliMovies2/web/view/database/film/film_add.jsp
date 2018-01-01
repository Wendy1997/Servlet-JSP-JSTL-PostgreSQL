<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Film</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/film/add" method="post" enctype='multipart/form-data' onsubmit="return confirm('Are You Sure?');">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Film</h2><br>

                    <div class="form-group">
                        <label for="nama">Nama:</label>
                        <input type="name" class="form-control" id="nama" name="nama" maxlength="100" required>
                    </div>

                    <div class="form-group">
                        <label for="genre">Genre:</label>
                        <select class="form-control" id="genre" name="genre" required>
                            <option disabled selected value> -- pilih Genre -- </option>
                            <c:forEach var="genre" items="${genre}">
                                <option value="${genre.id}">${genre.genre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="durasi">Durasi:</label>
                        <input type="number" class="form-control" id="durasi" name="durasi" min="1" required>
                    </div>

                    <div class="form-group">
                        <label for="director">Director:</label>
                        <input type="name" class="form-control" id="director" name="director" required>
                    </div>

                    <div class="form-group">
                        <label for="rating">Rating:</label>
                        <input type="number" class="form-control" id="rating" name="rating" min="0" max="5" step=".01" required>
                    </div>

                    <div class="form-group">
                        <label for="jumlah">Jumlah Review:</label>
                        <input type="number" class="form-control" id="jumlah" name="jumlah" min="0" required>
                    </div>

                    <div class="form-group">
                        <label for="waktu_mulai">Waktu Mulai:</label>
                        <input type="date" class="form-control" id="waktu_mulai" name="waktu_mulai" required>
                    </div>

                    <div class="form-group">
                        <label for="waktu_akhir">Waktu Akhir:</label>
                        <input type="date" class="form-control" id="waktu_akhir" name="waktu_akhir" required>
                    </div>

                    <div class="form-group">
                        <label for="language">Language:</label>
                        <input type="name" class="form-control" id="language" name="language" required>
                    </div>

                    <div class="form-group">
                        <label for="subtitle">Subtitle:</label>
                        <input type="name" class="form-control" id="subtitle" name="subtitle" required>
                    </div>

                    <div class="form-group">
                        <label for="actor">Actors:</label>
                        <input type="name" class="form-control" id="actor" name="actor" required>
                    </div>

                    <div class="form-group">
                        <label for="sinopsis">Sinopsis:</label>
                        <textarea class="form-control" rows="10" id="sinopsis" name="sinopsis" required></textarea>
                    </div>


                    <div class="form-group">
                        <label for="file">Cover:</label>
                        <input type="file" class="form-control" id="file" name="file" accept=".jpg, .png" required>
                    </div>
                </div>

                <button type="submit" class="btn btn-default">Submit ></button>
            </div>
        </form>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("input[name='waktu_mulai']").change(function () {
            $("input[name='waktu_akhir']").val($(this).val());
            $("input[name='waktu_akhir']").attr('min', $(this).val());
        });
    });
</script>

<%@ include file = "/include/foot.jsp" %>