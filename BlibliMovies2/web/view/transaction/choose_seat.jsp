<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center" >

    <!-- List nav bar film -->

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <ul class=" nav navbar-nav" >
                <div class="row">
                    <li><a href="#" class="tab">Ticket</a></li>
                    <li><a href="#" class="tab active-tab">Pick Your Seat</a></li>
                    <li><a href="#" class="tab">Snack and Beverages</a></li>
                </div>
            </ul>
        </div>
    </nav>

    <!-- Close Button
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button> -->

    <!-- <div class="jumbotron"> -->

    <!-- Title -->
    <div class="stripe"></div>
    <br><br>

    <div id="screen" style="background-color: #666878; border-radius: 5px; width: 400px; margin-bottom:50px;margin-left:20px;text-align: center;">SCREEN</div>

    <!-- Forms -->
    <form id="cancel" action="film_detail.html"></form>


        <div id="seat-number"></div>

        <div class="legend" align="left" style="margin-right:70px;margin-top: 20px; display: inline-block;">
            <div class="row">
                <div class="smallSquare nonSeat unavailableSeat"></div>
                <div class="smallSquare nonSeat" style="margin: 0 40px"></div>
                <div class="smallSquare nonSeat selectedSeat"></div>
            </div>
            <div class="row">
                <div>Taken</div>
                <div style="margin: 0 20px">Available</div>
                <div>Choosen</div>
            </div>
        </div>

        <div class="button" style="display: inline-block;">
            <button type="submit" class="btn btn-default" id="accept">Accept ></button>
            <button type="hidden" class="btn btn-default" form="cancel" />Cancel ></button>
        </div>

        <div class="detail" style="margin-left:80px; display: inline-block;">
            <div class="row">
                <div style="font-size: 18px">${film.title}</div>
            </div>
            <div class="row">
                <div>${film.genre}/${film.duration}</div>
            </div>

        </div>

</div>

<script>

    $(document).ready(function () {
       console.log("aaaa");
    });

    var wrapper = document.getElementById('seat-number');

    var huruf = 'A';
    var angka = 1;
    var cornerAlphabet = 'J';

    for (var i = 1; i <= 10; i++) {

        wrapper.innerHTML += '<div style="display:inline-block; width:35px; vertical-align:top; padding-right:20px">' + cornerAlphabet;
        wrapper.innerHTML += '<div class="row" style="display:inline-block; padding:10px"><div class="rounded"></div>';

        for (var j = 1; j <= 10; j++) {

            // buat unavailable seat (masih nembak asal sih)
            // wrapper.innerHTML += '<div class="smallSquare unavailableSeat" id="' + huruf + angka + '" style="cursor: not-allowed; outline: 0 !important"></div>';
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
        wrapper.innerHTML += '<div style="height: 35px;width: 35px;display: inline-block;margin-left:5px">' + angka + '</div>';
        angka++;
    }

    var pointer = 0;
    var addClass = 'selectedSeat';
    var listTicket = new Array();

    $(document).ready(function () {
        $(".smallSquare").click(function(e){
            if($(this).hasClass('nonSeat')){
                console.log('asdasd');
            } else {
                if ($(this).hasClass('smallSquare unavailableSeat'))
                    $(this).click(false);
                else {
                    if ($(this).hasClass(addClass)){
                        $(this).removeClass(addClass);
                        pointer--;
                        var index = listTicket.indexOf($(this).attr('id'));
                        listTicket.splice(index, index + 1);
                        window.alert($(this).attr('id') + " canceled.\n jumlah tiket " + pointer + "\n list tiket = " + listTicket.toString() + "\n");
                    } else {
                        $(this).addClass(addClass);
                        pointer++;
                        listTicket.push($(this).attr('id'));
                        window.alert($(this).attr('id') + " canceled.\n jumlah tiket " + pointer + "\n list tiket = " + listTicket.toString());
                    }
                }
            }
        });
        $('#accept').click(function () {
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
        });
    });
</script>
<%@ include file = "/include/foot.jsp" %>