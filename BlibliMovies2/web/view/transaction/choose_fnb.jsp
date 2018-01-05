<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<div class="container-fluid">
    <div class="row cashier">
        <div class="container-fluid thumbnail col-12 col-sm-12 col-md-11 col-lg-6 col-xl-7">

            <nav class="navbar navbar-default">
                <div class="d-flex flex-row">
                    <div class="p-2">Ticket</div>
                    <div class="p-2">Pick Your Seat</div>
                    <div class="p-2 active-tab">Food and Beverages</div>
                </div>
            </nav>

            <div class="stripe"></div><br>

            <div class="container fnb">
                <div class="fnbcontainer-responsive row">
                    <c:forEach items="${fnblist}" var="fnb">
                        <div id="${fnb.id}" class="col-4 col-sm-4 col-md-3 col-lg-4 col-xl-3">
                            <img class="circle" id="thumbnail" src="${fnb.cover}">
                            <p>${fnb.name}
                                <c:forEach items="${fnbsize}" var="size">
                                    ${fnb.size == size.id ? size.size : ""}
                                </c:forEach>
                            </p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="container-fluid thumbnail col-11 col-sm-11 col-md-11 col-lg-6 col-xl-5 cashier">
            <!-- Title -->
            <div >
                <h4 id="txtSummary">Summary</h4>
            </div>
            <div class="stripe summary"></div><br>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-xl-10">
                        <h5>Order Details:</h5>
                    </div>
                </div>

                <div id="keranjang" class="box-window summarycontainer-responsive"></div>

                <div class="stripe summary"></div><br>

                <p>Masukkan ID Member(Jika ada)</p>
                <input type="name" class="form-control" id="member" name="member" required>
                <input type="hidden" id="filmid" name="filmid" value="${film.id}">
                <input type="hidden" id="screeningid" name="screeningid" value="${screening.id}">
                <input type="hidden" id="studioid" name="studioid" value="${studio.id}">
                <input type="hidden" id="ticket" name="ticket" value="${ticketQuantity}">
                <input type="hidden" id="studioPrice" name="studioid" value="${studio.price}">
                <input type="hidden" id="filmTitle" name="ticket" value="${film.title}">
                <button type="submit" class="btn btn-default" id="memberAccept">Member Accept ></button>
                <button class="btn btn-default" id="memberAdd">Member Add ></button>

                <div class="stripe summary"></div>

                <div class="row amount">
                    <div class="col-7 col-sm-7 col-md-7 col-lg-7 col-xl-7">
                        <p id="total">Total: Rp. 0 ,-</p>
                        <p id="discount">Member Discount: 0%</p>
                    </div>

                    <div class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5">
                        <p>Amount Payable</p>
                        <p><strong id="amount">Rp. 0 ,-</strong></p>
                    </div>
                </div>

                <button type="submit" class="btn btn-default" id="accept">Accept ></button>
            </div>

        </div>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>

<script>
    var type = [];
    <c:forEach items="${fnbtype}" var="type">
        type['${type.id}'] = '${type.type}';
    </c:forEach>

    var listBuy = [];
    var listHarga = [];
    var harga = 0;
    var hargaFinal = 0;
    var diskon = 0;
    var hasFilm = false;

    function deleteContent(id, jumlah, price) {
        $('#'+id+jumlah).remove();
        listBuy[id] -= jumlah;
        if(listBuy[id] == 0){
            delete listBuy[id];
        }
        listHarga[id] -= price;

        harga -= price;
        hargaFinal = harga - (harga * diskon / 100);

        $('#total')[0].innerHTML = "Total: Rp. "+ harga +" ,-";
        $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";
    }

    $(document).ready(function () {
        $('#memberAdd').click(function () {
            window.open("/admin/membercard/add", '_blank');
        });

       $('.col-4').click(function () {
          var jumlah = prompt("Masukkan jumlah");
          if(jumlah > 0){
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

                          $('#keranjang')[0].innerHTML += '<div id="'+response.id+jumlah+'" class="row">\n' +
                              '                        <div class="col-lg-7">\n' +
                              '                            <div class="row box">\n' +
                              '                                <div class="col-lg-4">\n' +
                              '                                    <div class="smallCircle" style="background-image:url(\'' + "/uploads" + response.cover + '\');width:50px;height:50px;"></div>\n' +
                              '                                </div>\n' +
                              '                                <div class="col-lg-8">\n' +
                              '                                    <p class="invoice">'+ response.name + '</p>\n' +
                              '                                    <p class="invoice">' + type[response.type] + '</p>\n' +
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

                      } else {
                          var jumlahLama = listBuy[response.id];
                          listBuy[response.id] += parseInt(jumlah);
                          listHarga[response.id] += parseInt(price);

                          $('#' + response.id + jumlahLama)[0].innerHTML =
                              '                        <div class="col-lg-7">\n' +
                              '                            <div class="row box">\n' +
                              '                                <div class="col-lg-4">\n' +
                              '                                    <div class="smallCircle" style="background-image:url(\'' + "/uploads" + response.cover + '\');width:50px;height:50px;"></div>\n' +
                              '                                </div>\n' +
                              '                                <div class="col-lg-8">\n' +
                              '                                    <p class="invoice">'+ response.name + '</p>\n' +
                              '                                    <p class="invoice">' + type[response.type] + '</p>\n' +
                              '                                </div>\n' +
                              '                            </div>\n' +
                              '                        </div>\n' +
                              '                        <div class="col-lg-2" align="center">\n' +
                              '                            <p>x'+ listBuy[response.id] +'</p>\n' +
                              '                        </div>\n' +
                              '                        <div class="col-lg-3" align="right">\n' +
                              '                            Rp '+ listHarga[response.id] +' ,- ' +
                              '                            <p onclick="deleteContent('+response.id+","+listBuy[response.id]+","+listHarga[response.id]+')">x</p>\n' +
                              '                        </div>\n';

                          $('#' + response.id + jumlahLama).attr("id", response.id + "" + listBuy[response.id]);
                      }

                      harga += price;
                      hargaFinal = harga - (harga * diskon / 100);

                      $('#total')[0].innerHTML = "Total: Rp. "+ harga +" ,-";
                      $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";
                  }
              });
          }
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

                   if(response == 0){
                       alert("Member ID not found");

                       diskon = response;
                       $('#discount')[0].innerHTML = 'Member Discount: -%';

                       hargaFinal = harga;
                       $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";

                   } else {
                       diskon = response;
                       $('#discount')[0].innerHTML = 'Member Discount: ' + diskon + '%';

                       hargaFinal = harga - (harga * diskon / 100);
                       $('#amount')[0].innerHTML = "Rp. "+ hargaFinal +" ,-";
                   }
               }
           });
       });

       $('#accept').click(function () {
           if(confirm("Are you sure?")){
               var jsonListBuy = "";
               for(var key in listBuy) {
                   jsonListBuy += key + ',' + listBuy[key] + ';';
               }
               if(jsonListBuy.length > 0){
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
               } else {
                   window.location.href = "/menu";
               }
           }
       });

       var ticket = $('#ticket').val().length;

        if(ticket !== 0){
            var namaFilm = $('#filmTitle').val();
           var jumlah = $('#ticket').val();
           var studioPrice = $('#studioPrice').val();
           var price = parseInt(jumlah) * parseInt(studioPrice);

           if(parseInt(jumlah) > 0){
               listBuy[namaFilm] = parseInt(jumlah);
               listHarga[namaFilm] = parseInt(price);

               $('#keranjang')[0].innerHTML += '<div id="'+namaFilm + jumlah+'" class="row">\n' +
                   '                        <div class="col-lg-7">\n' +
                   '                            <div class="row box">\n' +
                   '                                <div class="col-lg-4">\n' +
                   '                                    <div class="smallCircle" style="background-image:url(\'' + "/uploads" + '${film.cover}' + '\');width:50px;height:50px;"></div>\n' +
                   '                                </div>\n' +
                   '                                <div class="col-lg-8">\n' +
                   '                                    <p class="invoice">'+ namaFilm + '</p>\n' +
                   '                                    <p class="invoice">Tickets</p>\n' +
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
       }
    });

</script>