<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Film</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/film/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Film</h2><br>

                    <div class="form-group">
                        <label for="cover">Cover:</label>
                        <input type="name" class="form-control" id="cover" name="cover" value="${film.cover}" required>
                    </div>

                    <div class="form-group">
                        <label for="nama">Nama:</label>
                        <input type="name" class="form-control" id="nama" name="nama" value="${film.title}" required>
                    </div>

                    <div class="form-group">
                        <label for="genre">Genre:</label>
                        <select class="form-control" id="genre" name="genre" value="${film.genre}" required>
                            <option disabled selected value> -- pilih Genre -- </option>
                            <option value="Action" ${film.genre == 'Action' ? 'selected' : ''}>Action</option>
                            <option value="Horror" ${film.genre == 'Horror' ? 'selected' : ''}>Horror</option>
                            <option value="Romance" ${film.genre == 'Romance' ? 'selected' : ''}>Romance</option>
                            <option value="Melodrama" ${film.genre == 'Melodrama' ? 'selected' : ''}>Melodrama</option>
                            <option value="Adventure" ${film.genre == 'Adventure' ? 'selected' : ''}>Adventure</option>
                            <option value="Comedy" ${film.genre == 'Comedy' ? 'selected' : ''}>Comedy</option>
                            <option value="Fantasy" ${film.genre == 'Fantasy' ? 'selected' : ''}]>Fantasy</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="durasi">Durasi:</label>
                        <input type="number" class="form-control" id="durasi" name="durasi" value="${film.duration}" required>
                    </div>

                    <div class="form-group">
                        <label for="director">Director:</label>
                        <input type="name" class="form-control" id="director" name="director" value="${film.director}" required>
                    </div>

                    <div class="form-group">
                        <label for="rating">Rating:</label>
                        <input type="number" class="form-control" id="rating" name="rating" value="${film.rating}" required>
                    </div>

                    <div class="form-group">
                        <label for="jumlah">Jumlah Review:</label>
                        <input type="number" class="form-control" id="jumlah" name="jumlah" value="${film.reviewTotal}" required>
                    </div>

                    <div class="form-group">
                        <label for="waktu_mulai">Waktu Mulai:</label>
                        <input type="date" class="form-control" id="waktu_mulai" name="waktu_mulai" value="${film.startTime}" required>
                    </div>

                    <div class="form-group">
                        <label for="waktu_akhir">Waktu Akhir:</label>
                        <input type="date" class="form-control" id="waktu_akhir" name="waktu_akhir" value="${film.endTime}" required>
                    </div>

                    <div class="form-group">
                        <label for="language">Language:</label>
                        <input type="name" class="form-control" id="language" name="language" value="${film.language}" required>
                    </div>

                    <div class="form-group">
                        <label for="subtitle">Subtitle:</label>
                        <input type="name" class="form-control" id="subtitle" name="subtitle" value="${film.subtitle}" required>
                    </div>

                    <div class="form-group">
                        <label for="actor">Actors:</label>
                        <input type="name" class="form-control" id="actor" name="actor" value="${film.actor}" required>
                    </div>

                    <div class="form-group">
                        <label for="sinopsis">Sinopsis:</label>
                        <textarea class="form-control" rows="10" id="sinopsis" name="sinopsis" required>${film.sinopsis}</textarea>
                    </div>
                </div>

                <input type="hidden" id="id" name="id" value="${film.id}">
                <button type="submit" class="btn btn-default">Submit ></button>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>