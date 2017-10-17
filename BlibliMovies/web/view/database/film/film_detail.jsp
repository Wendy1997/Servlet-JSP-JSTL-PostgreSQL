<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button>

    <div class="jumbotron" id="detail">

        <!-- Title -->
        <h1>Avatar The Legend of Korra</h1>
        <div class="stripe"></div>
        <h2>Genre/Duration</h2><br>

        <!-- Content -->
        <div class="row">
            <div class="col-lg-3" align="center" id="form1">
                <div class="dummy-image"></div><br>
            </div>
            <div class="col-lg-5" id="form1">
                <p>Director: Marsupilami </p>
                <p>Language: Java</p>
                <p>Subtitle: PeinAkatsukih</p>
                <p>Harga: Gocap</p>
                <p>Actors: Patrick Star</p>
                <p>Ratings: ***** (IMDB)</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
            </div>
            <div class="col-lg-4">
                <h2>Screening Time</h2><br>
                <p>Studio A: 10.00, 13.00, 16.00, 19.00</p>
                <p>Studio B: 11.30, 14.30, 17.30, 20.30</p> <br><br>

                <a href="film_edit"><h2>Edit ></h2></a><br>
                <a href="film_delete_confirmation"><h2>Delete ></h2></a>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>