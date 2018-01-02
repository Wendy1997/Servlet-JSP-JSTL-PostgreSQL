<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="container-fluid thumbnail col-12 col-sm-12 col-md-12 col-lg-6 col-xl-7">

            <nav class="navbar navbar-default">
                <div class="d-flex flex-row">
                    <div class="p-2 active-tab">Ticket</div>
                    <div class="p-2">Pick Your Seat</div>
                    <div class="p-2">Snack and Beverages</div>
                </div>
            </nav>

            <div class="stripe"></div><br>

            <div class="container film">
                <div class="filmcontainer-responsive row">
                    <c:forEach items="${films}" var="film">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-10">
                            <div class="row box">
                                <div class="col-3 col-sm-3 col-md-3 col-lg-4 col-xl-3">
                                    <div class="circle" id="thumbnail" style="background-image: url('${film.cover}')"></div>
                                </div>
                                <div class="col-9 col-sm-9 col-md-9 col-lg-8 col-xl-9">
                                    <div id="txtMovieTitle">${film.title}</div>
                                    <div id="txtMovieSubtitle">${film.subtitle}</div>

                                    <div class="tab">
                                        <c:forEach items="${film.screeningList}" var="screeningList">
                                            <p style="margin-bottom: 0">${screeningList.key}:
                                            <c:forEach items="${screeningList.value}" var="screeningTime">
                                                <button class="tablinks" style="float: none;"><a class="screeningTimeTab" href="/cashier/film/detail?id=${film.id}&screeningtime=${screeningTime.id}">${screeningTime.time}</a></button>
                                            </c:forEach>
                                            </p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>


        <div class="container-fluid thumbnail col-lg-6 col-xl-5">
            <!-- Title -->
            <div>
                <h4 id="txtSynopsis">Synopsis</h4>
            </div>
            <div class="stripe synopsis"></div><br>

            <div class="container-fluid">

                <div class="box-window synopsiscontainer-responsive">
                    <c:forEach items="${films}" var="film">
                        <div class="row">
                            <div class="col-lg-12 col-xl-10">
                                <div class="row box">
                                    <div class="col-lg-4 col-xl-4">
                                        <div class="circle" id="smallThumbnail" style="background-image: url('${film.cover}')"></div>
                                    </div>
                                    <div class="col-lg-8 col-xl-8">
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