<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<div class="container-fluid">
<div class="row">
    <div class="container-fluid thumbnail col-lg-8" >

        <!-- List Film -->
        <nav class="navbar navbar-default">
            <div class="d-flex flex-row">
                <div class="p-2">Ticket</div>
                <div class="p-2">Pick Your Seat</div>
                <div class="p-2 active-tab">Snack and Beverages
            </div>
        </nav>

        <div class="stripe" id="navbarStripe"></div><br>

        <div class="container film">
            <div class="filmcontainer-responsive row">
                <c:forEach items="${films}" var="film">
                    <div class="col-xl-10">
                        <div class="row box">
                            <div class="col-lg-2">
                                <div class="circle" id="thumbnail" src="${film.cover}"></div>
                            </div>
                            <div class="col-lg-10">
                                <div id="txtMovieTitle">${film.title}</div>
                                <div>${film.subtitle}</div>

                                <div class="tab" style="margin-top: 15px; margin-left: -3px;">
                                    <c:forEach items="${film.screeningTimes}" var="screeningTime">
                                        <button class="tablinks"><a href="/cashier/film/detail?id=${film.id}&screeningtime=${screeningTime.id}" style="color: white">${screeningTime.time}</a></button>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>



    <div class="container-fluid thumbnail col-lg-4">
        <!-- Title -->
        <div >
            <h4 id="txtSynopsis">Synopsis</h4>
        </div>
        <div class="stripe synopsis"></div><br>

        <div class="container-fluid">

            <div class="box-window synopsiscontainer-responsive">
                <c:forEach items="${films}" var="film">
                    <div class="row">
                        <div class="col-xl-7">
                            <div class="row box">
                                <div class="col-lg-5">
                                    <div class="circle" id="smallThumbnail" src="${film.cover}"></div>
                                </div>
                                <div class="col-lg-7">
                                    <div id="txtMovieTitle">${film.title}</div>
                                    <div id="txtMovieSubtitle">${film.subtitle}</div>
                                    <p class="rating">${film.rating}/5 (${film.reviewTotal})</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p>${film.sinopsis}</p>
                </c:forEach>
            </div>


        </div>
    </div>
</div>
</div>
    <%@ include file = "/include/foot.jsp" %>