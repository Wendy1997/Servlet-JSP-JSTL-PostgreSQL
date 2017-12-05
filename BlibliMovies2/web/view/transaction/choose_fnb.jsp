<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="container-fluid thumnail col-lg-8" align="center" >
            <!-- Title -->
            <!-- <div >
                <h1 class="title">Food and Beverages</h1><br>
            </div> -->

            <!-- List fnb -->

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class=" nav navbar-nav" >
                        <div class="row">
                            <li><a href="#" class="tab">Ticket</a></li>
                            <li><a href="#" class="tab">Pick Your Seat</a></li>
                            <li><a href="#" class="tab active-tab">Snack and Beverages</a></li>
                        </div>
                    </ul>
                </div>
            </nav>

            <div class="stripe"></div><br>

            <div class="container">
                <div class="fnbcontainer-responsive row">
                    <c:forEach items="${fnblist}" var="fnb">
                        <div id="${fnb.id}" class="col-md-2">
                            <div>
                                <img class="circle" style="background-image:url('${fnb.cover}');width:120px;height:120px;">
                            </div>
                            <p>${fnb.name} ${fnb.size}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- <div class="container-fluid">
                <form class="row" action="">
                  <input type="radio" name="food" value="food"> Food
                  <input type="radio" name="beverages" value="beverages"> Beverages
                  <input type="radio" name="combo" value="combo"> Combo
                </form>
            </div> -->

        </div>

        <div class="container-fluid thumnail col-lg-4" align="center">
            <!-- Title -->
            <div >
                <h3>Summary</h3>
            </div>
            <div class="stripe"></div><br>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h5>Order Details:</h5>
                    </div>
                </div>

                <div id="keranjang" class="box-window summarycontainer-responsive"></div>

                <p>Masukkan ID Member(Jika ada)</p>
                <input type="name" class="form-control" id="member" name="member" required>
                <input type="hidden" id="filmid" name="filmid" value="${film.id}">
                <input type="hidden" id="screeningid" name="screeningid" value="${screening.id}">
                <input type="hidden" id="studioid" name="studioid" value="${studio.id}">
                <input type="hidden" id="ticket" name="ticket" value="${ticketQuantity}">
                <input type="hidden" id="studioPrice" name="studioid" value="${studio.price}">
                <input type="hidden" id="filmTitle" name="ticket" value="${film.title}">
                <button type="submit" class="btn btn-default" id="memberAccept">Accept ></button>

                <div class="row">
                    <dir class="col-lg-6">
                        <p id="total">Total: Rp. 0 ,-</p>
                        <p id="discount">Member Discount: 0%</p>
                    </dir>

                    <dir class="col-lg-6" align="right">
                        <p>Amount Payable</p>
                        <p><strong id="amount">Rp. 0 ,-</strong></p>
                    </dir>
                </div>
            </div>

            <button type="submit" class="btn btn-default" id="accept">Accept ></button>

        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>

<script>

    var listBuy = [];
    var listHarga = [];
    var harga = 0;
    var hargaFinal = 0;
    var diskon = 0;
    var hasFilm = false;

    function deleteContent(id, jumlah, price) {
        $('#'+id+jumlah).remove();
        listBuy[id] -= jumlah;
        listHarga[id] -= price;

        harga -= price;
        hargaFinal = harga - (harga * diskon / 100);

        $('#total')[0].innerHTML = "Total: Rp. "+ harga +" ,-";
        $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";
    }

    $(document).ready(function () {
       $('.col-md-2').click(function () {
          var jumlah = prompt("Masukkan jumlah");
           $.ajax({
               type: 'POST',
               dataType: "JSON",
               url: "/view/fnb",
               data: {
                   id: $(this).attr("id")
               },
               success: function(response){

                   var price = response.price * jumlah;

                   if(listBuy[response.id] == null){
                       listBuy[response.id] = parseInt(jumlah);
                       listHarga[response.id] = parseInt(price);
                   } else {
                       listBuy[response.id] += parseInt(jumlah);
                       listHarga[response.id] += parseInt(price);
                   }

                   $('#keranjang')[0].innerHTML += '<div id="'+response.id+jumlah+'" class="row">\n' +
                       '                        <div class="col-lg-7">\n' +
                       '                            <div class="row box">\n' +
                       '                                <div class="col-lg-4">\n' +
                       '                                    <div class="smallCircle"></div>\n' +
                       '                                </div>\n' +
                       '                                <div class="col-lg-8">\n' +
                       '                                    <p class="invoice">'+ response.name + '</p>\n' +
                       '                                    <p class="invoice">'+ response.type + '</p>\n' +
                       '                                </div>\n' +
                       '                            </div>\n' +
                       '                        </div>\n' +
                       '                        <div class="col-lg-2" align="center">\n' +
                       '                            <p>x'+ jumlah +'</p>\n' +
                       '                        </div>\n' +
                       '                        <div class="col-lg-3" align="right">\n' +
                       '                            Rp '+ price +' ,- ' +
                       '                            <p onclick="deleteContent('+response.id+","+jumlah+","+price+')">x</p>\n' +
                       '                        </div>\n' +
                       '                    </div>';

                   harga += price;
                   hargaFinal = harga - (harga * diskon / 100);

                   $('#total')[0].innerHTML = "Total: Rp. "+ harga +" ,-";
                   $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";
               }
           });


           console.log(listBuy);
       });

       $('#memberAccept').click(function () {
           $.ajax({
               type: 'POST',
               dataType: "JSON",
               url: "/check/member",
               data: {
                   id: $('#member').val()
               },
               success: function(response) {
                    diskon = response;
                    $('#discount')[0].innerHTML = 'Member Discount: ' + diskon + '%';

                    hargaFinal = harga - (harga * diskon / 100);
                    $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";
               }
           });
       });

       $('#accept').click(function () {
           var jsonListBuy = "";
           for(var key in listBuy) {
               jsonListBuy += key + ',' + listBuy[key] + ';';
           }
           console.log(jsonListBuy);
           $.ajax({
               type: 'POST',
               dataType: "JSON",
               url: "/cashier/fnb",
               data: {
                   member: $('#member').val(),
                   fnb: jsonListBuy,
                   totalHarga: hargaFinal,
                   hasFilm: hasFilm,
                   idFilm: $('#filmTitle').val() + "," + $('#ticket').val() + "," + $('#studioPrice').val()
               },
               success: function(response) {
                   window.location.href = "/cashier/invoice?id=" + response;
               },
               error: function (response) {
                   console.log(response);
               }
           });
       });

       var ticket = $('#ticket').val().length;

        if(ticket !== 0){
            var namaFilm = $('#filmTitle').val();
           var jumlah = $('#ticket').val();
           var studioPrice = $('#studioPrice').val();
           var price = parseInt(jumlah) * parseInt(studioPrice);

           listBuy[namaFilm] = parseInt(jumlah);
           listHarga[namaFilm] = parseInt(price);

           $('#keranjang')[0].innerHTML += '<div id="'+namaFilm + jumlah+'" class="row">\n' +
               '                        <div class="col-lg-7">\n' +
               '                            <div class="row box">\n' +
               '                                <div class="col-lg-4">\n' +
               '                                    <div class="smallCircle"></div>\n' +
               '                                </div>\n' +
               '                                <div class="col-lg-8">\n' +
               '                                    <p class="invoice">'+ namaFilm + '</p>\n' +
               '                                    <p class="invoice">tickets</p>\n' +
               '                                </div>\n' +
               '                            </div>\n' +
               '                        </div>\n' +
               '                        <div class="col-lg-2" align="center">\n' +
               '                            <p>x'+ jumlah +'</p>\n' +
               '                        </div>\n' +
               '                        <div class="col-lg-3" align="right">\n' +
               '                            Rp '+ price +' ,- ' +
               '                        </div>\n' +
               '                    </div>';

           harga += price;
           hargaFinal = harga - (harga * diskon / 100);

           $('#total')[0].innerHTML = "Total: Rp. "+ harga +" ,-";
           $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";

           hasFilm = true;
       }
    });

</script>