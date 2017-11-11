<%@ include file = "/include/head.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button>

    <div class="jumbotron" id="detail">

        <!-- Title -->
        <h1>${film.title}</h1>
        <div class="stripe"></div>
        <h2>${film.genre}/${film.duration}</h2><br>

        <!-- Content -->
        <div class="row">
            <div class="col-lg-3" align="center" id="form1">
                <div class="dummy-image"></div><br>
            </div>
            <div class="col-lg-5" id="form1">
                <p>Director: ${film.director} </p>
                <p>Language: ${film.language}</p>
                <p>Subtitle: ${film.subtitle}</p>
                <p>Actors: ${film.actor}</p>
                <p>Harga: ${studio.price}</p>
                <p>Studio: ${studio.name}</p>
                <p>Ratings: ${film.rating}(${film.reviewTotal})</p>
                <p>${film.sinopsis}</p>
            </div>
            <div class="col-lg-4">
                <a href="/cashier/seat?id=${film.id}&screeningid=${screeningtime.id}&studioid=${studio.id}"><h2>Pick Your Seat ></h2></a>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>