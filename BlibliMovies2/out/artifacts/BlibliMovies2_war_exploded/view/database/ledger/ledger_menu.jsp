<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Ledger</h1><br>
    </div>

    <div class="container">
        <div class="nav nav-tabs">
            <button class="tablinks" onclick="openReport(event, 'Daily')">Daily</button>
            <button class="tablinks" onclick="openReport(event, 'Weekly')">Weekly</button>
            <button class="tablinks" onclick="openReport(event, 'Monthly')">Monthly</button>
            <button class="tablinks" onclick="openReport(event, 'Yearly')">Yearly</button>
        </div>
    </div>

    <br>

    <div id="Daily" class="tabcontent">
        <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <!-- ledger -->
            <div class="container ledger">
                <div class="table-responsive">
                    <p class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4" style="float: left"> Date : <input type="date" class="form-control" style="display: inline-block" name="day" id="day" /> </p>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Member</th>
                            <th>Cashier</th>
                            <th>Total Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="day-content"></tbody>
                    </table>
                    <div id="day-notif">Harap Tunggu</div>
                    <div id="day-income" class="ledger total-income"><h4> Total Income = Rp 40.000,-</h4></div>
                </div>
            </div>
        </div>
    </div>

    <div id="Weekly" class="tabcontent">
        <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <!-- ledger -->
            <div class="container ledger">
                <div class="table-responsive">
                    <p class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4" style="float: left"> Week : <input type="week" class="form-control" style="display: inline-block" name="week" id="week" /> </p>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Member</th>
                            <th>Cashier</th>
                            <th>Total Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="week-content"></tbody>
                    </table>
                    <div id="week-notif">Harap Tunggu</div>
                    <div id="week-income" class="ledger total-income"><h4> Total Income = Rp 40.000,-</h4></div>
                </div>
            </div>
        </div>
    </div>

    <div id="Monthly" class="tabcontent">
        <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <!-- ledger -->
            <div class="container ledger">
                <div class="table-responsive">
                    <p class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4" style="float: left"> Month : <input type="month" class="form-control" style="display: inline-block" name="month" id="month" /></p>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Member</th>
                            <th>Cashier</th>
                            <th>Total Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="month-content"></tbody>
                    </table>
                    <div id="month-notif">Harap Tunggu</div>
                    <div id="month-income" class="ledger total-income"><h4> Total Income = Rp 40.000,-</h4></div>
                </div>
            </div>
        </div>
    </div>

    <div id="Yearly" class="tabcontent">
        <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <!-- ledger -->
            <div class="container ledger">
                <div class="table-responsive">
                    <p class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4" style="float: left">Year : <select class="form-control" id="year" name="year"></select></p>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Member</th>
                            <th>Cashier</th>
                            <th>Total Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="year-content"></tbody>
                    </table>
                    <div id="year-notif">Harap Tunggu</div>
                    <div id="year-income" class="ledger total-income"><h4> Total Income = Rp 40.000,-</h4></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function openReport(evt, timeRange) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(timeRange).style.display = "block";

        if(timeRange == 'Daily'){
            getLedgerDay();
        } else if (timeRange == 'Weekly'){
            getLedgerWeek();
        } else if (timeRange == 'Monthly'){
            getLedgerMonth();
        } else{
            getLedgerYear();
        }
    }

    function prepare(){
        $('#day').attr("value", ${year} + "-" + ${month} + "-" + ${day});
        $('#week').attr("value", ${year} + "-W" + ${week});
        $('#month').attr("value", ${year} + "-" + ${month});

        var start = 1900;
        var end = new Date().getFullYear();
        var options = "";
        for(var year = start ; year <=end; year++){
            if(year == ${year}){
                options += "<option value='"+year+"' selected>"+ year +"</option>";
            } else{
                options += "<option value='"+year+"'>"+ year +"</option>";
            }
        }
        document.getElementById("year").innerHTML = options;
    }

    function getLedgerDay(){
        $.ajax({
            type: 'POST',
            dataType: "JSON",
            url: "/admin/ledger/daily",
            data: {
                date: $('#day').val()
            },
            success: function(response) {
                if(response.length == 0){
                    var income = 0.0;
                    document.getElementById("day-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("day-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("day-content").innerHTML = output;
                } else {
                    var income = 0.0;
                    var output = "";
                    for(var key in response) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + response[key].id + "</td>\n" +
                            "<td>" + response[key].orderDate + "</td>\n" +
                            "<td>" + response[key].memberId + "</td>\n" +
                            "<td>" + response[key].accountUsername + "</td>\n" +
                            "<td>" + response[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + response[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                        income += parseFloat(response[key].totalPrice);
                    }
                    document.getElementById("day-income").innerHTML = "<h4>Total Income = Rp " + income + ",-";
                    document.getElementById("day-content").innerHTML = output;
                    document.getElementById("day-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerWeek(){
        $.ajax({
            type: 'POST',
            dataType: "JSON",
            url: "/admin/ledger/weekly",
            data: {
                date: $('#week').val()
            },
            success: function(response) {
                if(response.length == 0){
                    var income = 0.0;
                    document.getElementById("week-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("week-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("week-content").innerHTML = output;
                } else {
                    var income = 0.0;
                    var output = "";
                    for(var key in response) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + response[key].id + "</td>\n" +
                            "<td>" + response[key].orderDate + "</td>\n" +
                            "<td>" + response[key].memberId + "</td>\n" +
                            "<td>" + response[key].accountUsername + "</td>\n" +
                            "<td>" + response[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + response[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                        income += parseFloat(response[key].totalPrice);
                    }
                    document.getElementById("week-income").innerHTML = "<h4>Total Income = Rp " + income + ",-";
                    document.getElementById("week-content").innerHTML = output;
                    document.getElementById("week-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerMonth(){
        $.ajax({
            type: 'POST',
            dataType: "JSON",
            url: "/admin/ledger/monthly",
            data: {
                date: $('#month').val()
            },
            success: function(response) {
                if(response.length == 0){
                    var income = 0.0;
                    document.getElementById("month-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("month-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("month-content").innerHTML = output;
                } else {
                    var income = 0.0;
                    var output = "";
                    for(var key in response) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + response[key].id + "</td>\n" +
                            "<td>" + response[key].orderDate + "</td>\n" +
                            "<td>" + response[key].memberId + "</td>\n" +
                            "<td>" + response[key].accountUsername + "</td>\n" +
                            "<td>" + response[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + response[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                        income += parseFloat(response[key].totalPrice);
                    }
                    document.getElementById("month-income").innerHTML = "<h4>Total Income = Rp " + income + ",-";
                    document.getElementById("month-content").innerHTML = output;
                    document.getElementById("month-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerYear(){
        $.ajax({
            type: 'POST',
            dataType: "JSON",
            url: "/admin/ledger/yearly",
            data: {
                date: $('#year').val()
            },
            success: function(response) {
                if(response.length == 0){
                    var income = 0.0;
                    document.getElementById("year-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("year-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("year-content").innerHTML = output;
                } else {
                    var income = 0.0;
                    var output = "";
                    for(var key in response) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + response[key].id + "</td>\n" +
                            "<td>" + response[key].orderDate + "</td>\n" +
                            "<td>" + response[key].memberId + "</td>\n" +
                            "<td>" + response[key].accountUsername + "</td>\n" +
                            "<td>" + response[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + response[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                        income += parseFloat(response[key].totalPrice);
                    }
                    document.getElementById("year-income").innerHTML = "<h4>Total Income = Rp " + income + ",-";
                    document.getElementById("year-content").innerHTML = output;
                    document.getElementById("year-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    $(document).ready(function () {
        prepare();
        openReport(event, 'Daily');

        $('#day').change(function () {
          getLedgerDay();
        });

        $('#week').change(function () {
            getLedgerWeek();
        });

        $('#month').change(function () {
            getLedgerMonth();
        });

        $('#year').change(function () {
            getLedgerYear();
        });
    });

</script>
<%@ include file = "/include/foot.jsp" %>