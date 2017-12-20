<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

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
                <div class="dummy-image" style="background-image: url('${film.cover}')">
                    <%--<img src="${film.cover}">--%>
                </div><br>
            </div>
            <div class="col-lg-5" id="form1">
                <p>Director: ${film.director} </p>
                <p>Language: ${film.language}</p>
                <p>Subtitle: ${film.subtitle}</p>
                <p>Actors: ${film.actor}</p>
                <p>Ratings: ${film.rating}(${film.reviewTotal})</p>
                <p>${film.sinopsis}</p>
            </div>
            <div class="col-lg-4">
                <h2>Screening Time</h2>
                <a href="/admin/screentime?filmid=${film.id}">Add, Update, and Delete Screening Time</a><br>
                <br>

                <c:forEach items="${screeningTime}" var="screeningTime">
                    <p>Studio ${screeningTime.key}:</p>
                    <p><c:forEach items="${screeningTime.value}" var="time">${time.time} </c:forEach></p>
                </c:forEach>

                <br>
                <a class="link" href="/admin/film/edit?id=${film.id}"><h2>Edit ></h2></a><br>
                <a class="link" href="/admin/film/delete?id=${film.id}"><h2>Delete ></h2></a>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>