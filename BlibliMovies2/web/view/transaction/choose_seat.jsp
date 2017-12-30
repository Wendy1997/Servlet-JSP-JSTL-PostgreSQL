<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default">
            <div class="d-flex flex-row">
                <div class="p-2">Ticket</div>
                <div class="p-2">Pick Your Seat</div>
                <div class="p-2 active-tab">Snack and Beverages</div>
            </div>
        </nav>

        <!-- Title -->
        <div class="stripe"></div>

        <div class="container seat">
            <div class="seatcontainer-responsive row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">

                    <div id="screen">SCREEN</div>

                    <!-- Forms -->
                    <form id="cancel" action="javascript:history.back()"></form>

                    <div id="seat-number"></div>

                    <div class="row nonSeat">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-3">
                            <div class="legend">
                                <div class="row">
                                    <div class="legends">
                                        <div class="smallSquare nonSeat unavailableSeat"></div>
                                        <div>Taken</div>
                                    </div>
                                    <div class="legends">
                                        <div class="smallSquare nonSeat"></div>
                                        <div>Available</div>
                                    </div>
                                    <div class="legends">
                                        <div class="smallSquare nonSeat selectedSeat"></div>
                                        <div>Choosen</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-9">
                            <div class="legend">
                                <div class="section">
                                    <div class="col-xl-6 d-inline">
                                        <div id="btnChooseSeat">
                                            <button type="submit" class="btn btn-default" id="accept">Accept ></button>
                                            <button type="hidden" class="btn btn-default" form="cancel" />Cancel ></button>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 d-inline">
                                        <div id="dtlChooseSeat">
                                            <div class="row">
                                                <div id="txtChosenMovie">${film.title}</div>
                                            </div>
                                            <div class="row">
                                                <div id="txtChosenMovieDetail">${film.genre}/${film.duration}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>

    var wrapper = document.getElementById('seat-number');

    var huruf = 'A';
    var angka = 1;
    var cornerAlphabet = 'J';

    for (var i = 1; i <= 10; i++) {
        wrapper.innerHTML += '<div class="huruf">' + cornerAlphabet;
        wrapper.innerHTML += '<div class="row seat"><div class="rounded"></div>';

        for (var j = 1; j <= 10; j++) {

            wrapper.innerHTML += '<div class="smallSquare" id="' + huruf + angka + '"></div>';
            angka++;
        }

        wrapper.innerHTML += '<br>';

        angka = 1;
        huruf = String.fromCharCode(huruf.charCodeAt() + 1);
        cornerAlphabet = String.fromCharCode(cornerAlphabet.charCodeAt() - 1);
        wrapper.innerHTML += '</div></div>';
    }

    <c:forEach var="ticket" items="${filmTickets}">
        $('#${ticket.seatNumber}').attr('class', 'smallSquare unavailableSeat');
        $('#${ticket.seatNumber}').attr('style', 'cursor: not-allowed; outline: 0 !important');
    </c:forEach>

    for (var i = 1; i <= 10; i++) {
        wrapper.innerHTML += '<div class="angka">' + angka + '</div>';
        angka++;
    }

    var pointer = 0;
    var addClass = 'selectedSeat';
    var listTicket = new Array();

    $(document).ready(function () {
        $(".smallSquare").click(function(e) {
            if($(this).hasClass('nonSeat')){
                console.log('asdasd');
            } else {
                if ($(this).hasClass('smallSquare unavailableSeat'))
                    $(this).click(false);
                else {
                    if ($(this).hasClass(addClass)) {
                        $(this).removeClass(addClass);
                        pointer--;
                        var index = listTicket.indexOf($(this).attr('id'));
                        listTicket.splice(index, index + 1);
                        window.alert($(this).attr('id') + " canceled.\n jumlah tiket " + pointer + "\n list tiket = " + listTicket.toString() + "\n");
                    } else {
                        $(this).addClass(addClass);
                        pointer++;
                        listTicket.push($(this).attr('id'));
                        window.alert($(this).attr('id') + " booked.\n jumlah tiket " + pointer + "\n list tiket = " + listTicket.toString());
                    }
                }
            }
        });

        $('#accept').click(function () {
            if(confirm("Are you sure?")){
                $.ajax({
                    type: 'POST',
                    url: "/cashier/seat",
                    dataType: "JSON",
                    data: {tickets: listTicket.toString(),
                        filmid: ${filmid},
                        screeningid: ${screeningid},
                        studioid: ${studioid}
                    },
                    success: window.location.href = "/cashier/fnb?film=" + ${filmid} + "&screeningid=" + ${screeningid} + "&studioid=" + ${studioid} + "&ticketQuantity=" + listTicket.length
                });
            }
        });
    });
</script>
<%@ include file = "/include/foot.jsp" %>