<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<div class="container-fluid">
<div class="row">
    <div class="container-fluid thumnail col-lg-8" >

        <!-- List Film -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <ul class="nav navbar-nav" >
                    <div class="row">
                        <li><a href="#" class="tab active-tab" style="color: white">Ticket</a></li>
                        <li><a href="#" class="tab">Pick Your Seat</a></li>
                        <li><a href="#" class="tab">Snack and Beverages</a></li>
                    </div>
                </ul>
            </div>
        </nav>

        <div class="stripe" style="margin-left:25px;width: 97.5%"></div><br>

        <div class="container">
            <div class="fnbcontainer-responsive row">
                <c:forEach items="${films}" var="film">
                    <div class="col-lg-10">
                        <div class="row box">
                            <div class="col-md-2">
                                <div class="circle" style="background-image:url('${film.cover}');width:100px;height:100px;"></div>
                            </div>
                            <div class="col-md-10">
                                <div style="font-size: 18px">${film.title}</div>
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



    <div class="container-fluid thumnail col-lg-4">
        <!-- Title -->
        <div >
            <h4 style="margin-bottom: 14px">Synopsis</h4>
        </div>
        <div class="stripe"></div><br>

        <div class="container-fluid">

            <div class="box-window summarycontainer-responsive" style="height:550px; margin-right: 10px">
                <c:forEach items="${films}" var="film">
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="row box">
                                <div class="col-lg-5">
                                    <div class="circle" style="background-image:url('${film.cover}');width:75px;height:75px;"></div>
                                </div>
                                <div class="col-lg-7">
                                    <div style="font-size: 18px">${film.title}</div>
                                    <div>${film.subtitle}</div>
                                    <p>${film.rating}/5 (${film.reviewTotal})</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p style="margin-right: 15px">${film.sinopsis}</p>
                </c:forEach>
            </div>


        </div>
    </div>
</div>
</div>
    <%@ include file = "/include/foot.jsp" %>