<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Film</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/film/add" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Film</h2><br>

                    <div class="form-group">
                        <label for="cover">Cover:</label>
                        <input type="name" class="form-control" id="cover" name="cover" required>
                    </div>

                    <div class="form-group">
                        <label for="nama">Nama:</label>
                        <input type="name" class="form-control" id="nama" name="nama" required>
                    </div>

                    <div class="form-group">
                        <label for="genre">Genre:</label>
                        <select class="form-control" id="genre" name="genre" required>
                            <option disabled selected value> -- pilih Genre -- </option>
                            <option>Action</option>
                            <option>Horror</option>
                            <option>Romance</option>
                            <option>Melodrama</option>
                            <option>Adventure</option>
                            <option>Comedy</option>
                            <option>Fantasy</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="durasi">Durasi:</label>
                        <input type="number" class="form-control" id="durasi" name="durasi" required>
                    </div>

                    <div class="form-group">
                        <label for="director">Director:</label>
                        <input type="name" class="form-control" id="director" name="director" required>
                    </div>

                    <div class="form-group">
                        <label for="rating">Rating:</label>
                        <input type="number" class="form-control" id="rating" name="rating" required>
                    </div>

                    <div class="form-group">
                        <label for="jumlah">Jumlah Review:</label>
                        <input type="number" class="form-control" id="jumlah" name="jumlah" required>
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
                        <label for="harga">Harga:</label>
                        <input type="number" class="form-control" id="harga" name="harga" required>
                    </div>

                    <div class="form-group">
                        <label for="actor">Actors:</label>
                        <input type="name" class="form-control" id="actor" name="actor" required>
                    </div>

                    <div class="form-group">
                        <label for="sinopsis">Sinopsis:</label>
                        <textarea class="form-control" rows="10" id="sinopsis" name="sinopsis" required></textarea>
                    </div>
                </div>

                <div class="col-lg-6">
                    <h2>Screening Time</h2><br>

                    <div class="form-group">
                        <label for="screen_time_1">Screening Time 1:</label>
                        <input type="time" class="form-control" id="screen_time_1" name="screen_time_1" required>
                    </div>

                    <div class="form-group">
                        <label for="studio1">Studio 1:</label>
                        <select class="form-control" id="studio1" name="studio1" required>
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_2">Screening Time 2:</label>
                        <input type="time" class="form-control" id="screen_time_2" name="screen_time_2">
                    </div>

                    <div class="form-group">
                        <label for="studio2">Studio 2:</label>
                        <select class="form-control" id="studio2" name="studio2">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_3">Screening Time 3:</label>
                        <input type="time" class="form-control" id="screen_time_3" name="screen_time_3">
                    </div>

                    <div class="form-group">
                        <label for="studio3">Studio 3:</label>
                        <select class="form-control" id="studio3" name="studio3">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_4">Screening Time 4:</label>
                        <input type="time" class="form-control" id="screen_time_4" name="screen_time_4">
                    </div>

                    <div class="form-group">
                        <label for="studio4">Studio 4:</label>
                        <select class="form-control" id="studio4" name="studio4">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_5">Screening Time 5:</label>
                        <input type="time" class="form-control" id="screen_time_5" name="screen_time_5">
                    </div>

                    <div class="form-group">
                        <label for="studio5">Studio 5:</label>
                        <select class="form-control" id="studio5" name="studio5">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_6">Screening Time 6:</label>
                        <input type="time" class="form-control" id="screen_time_6" name="screen_time_6">
                    </div>

                    <div class="form-group">
                        <label for="studio6">Studio 6:</label>
                        <select class="form-control" id="studio6" name="studio6">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_7">Screening Time 7:</label>
                        <input type="time" class="form-control" id="screen_time_7" name="screen_time_7">
                    </div>

                    <div class="form-group">
                        <label for="studio7">Studio 7:</label>
                        <select class="form-control" id="studio7" name="studio7">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="screen_time_8">Screening Time 8:</label>
                        <input type="time" class="form-control" id="screen_time_8" name="screen_time_8">
                    </div>

                    <div class="form-group">
                        <label for="studio8">Studio 8:</label>
                        <select class="form-control" id="studio8" name="studio8">
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>"><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button type="submit" class="btn btn-default">Submit ></button>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>