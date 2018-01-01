<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

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
                    <nav aria-label="Page navigation example">
                        <ul class="pagination pagination-sm justify-content-center" id="day-pagination"></ul>
                    </nav>
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
                    <nav aria-label="Page navigation example">
                        <ul class="pagination pagination-sm justify-content-center" id="week-pagination"></ul>
                    </nav>
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
                    <nav aria-label="Page navigation example">
                        <ul class="pagination pagination-sm justify-content-center" id="month-pagination"></ul>
                    </nav>
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
                    <nav aria-label="Page navigation example">
                        <ul class="pagination pagination-sm justify-content-center" id="year-pagination"></ul>
                    </nav>
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
                console.log(response);
                if(response["result"].length == 0){
                    var income = 0.0;
                    document.getElementById("day-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("day-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("day-content").innerHTML = output;
                    $('#day-pagination')[0].innerHTML = '';

                } else {
                    var output = "";
                    var result = response["result"];
                    for(var key in result) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + result[key].id + "</td>\n" +
                            "<td>" + result[key].orderDate + "</td>\n" +
                            "<td>" + result[key].memberId + "</td>\n" +
                            "<td>" + result[key].accountID + "</td>\n" +
                            "<td>" + result[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                    }

                    var pagination = '';
                    for(a = 1; a <= parseInt(response["count"]); a++){
                        pagination += '<li class="page-item"><a class="page-link" href="#" onclick="getLedgerDayPage(' + a + ')">' + a + '</a></li>';
                    }
                    $('#day-pagination')[0].innerHTML = pagination;

                    document.getElementById("day-income").innerHTML = "<h4>Total Income = Rp " + response["sum"] + ",-";
                    document.getElementById("day-content").innerHTML = output;
                    document.getElementById("day-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerDayPage(page){
        $.ajax({
            type: 'GET',
            dataType: "JSON",
            url: "/admin/ledger/daily/page",
            data: {
                date: $('#day').val(),
                page: page
            },
            success: function(response) {
                var output = "";
                var result = response["result"];
                for(var key in result) {
                    output += "<tr>\n" +
                        "<td>" + (parseInt(page-1) * 10 + parseInt(key) + 1) + "</td>\n" +
                        "<td>" + result[key].id + "</td>\n" +
                        "<td>" + result[key].orderDate + "</td>\n" +
                        "<td>" + result[key].memberId + "</td>\n" +
                        "<td>" + result[key].accountID + "</td>\n" +
                        "<td>" + result[key].totalPrice + "</td>\n" +
                        "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                        "</tr>\n";
                }
                console.log(output);
                document.getElementById("day-content").innerHTML = output;
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
                if(response["result"].length == 0){
                    var income = 0.0;
                    document.getElementById("week-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("week-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("week-content").innerHTML = output;
                    $('#week-pagination')[0].innerHTML = '';
                } else {
                    var output = "";
                    var result = response["result"];
                    for(var key in result) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + result[key].id + "</td>\n" +
                            "<td>" + result[key].orderDate + "</td>\n" +
                            "<td>" + result[key].memberId + "</td>\n" +
                            "<td>" + result[key].accountID + "</td>\n" +
                            "<td>" + result[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                    }

                    var pagination = '';
                    for(a = 1; a <= parseInt(response["count"]); a++){
                        pagination += '<li class="page-item"><a class="page-link" href="#" onclick="getLedgerWeekPage(' + a + ')">' + a + '</a></li>';
                    }
                    $('#week-pagination')[0].innerHTML = pagination;

                    document.getElementById("week-income").innerHTML = "<h4>Total Income = Rp " + response["sum"] + ",-";
                    document.getElementById("week-content").innerHTML = output;
                    document.getElementById("week-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerWeekPage(page){
        $.ajax({
            type: 'GET',
            dataType: "JSON",
            url: "/admin/ledger/weekly/page",
            data: {
                date: $('#week').val(),
                page: page
            },
            success: function(response) {
                var output = "";
                var result = response["result"];
                for(var key in result) {
                    output += "<tr>\n" +
                        "<td>" + (parseInt(page-1) * 10 + parseInt(key) + 1) + "</td>\n" +
                        "<td>" + result[key].id + "</td>\n" +
                        "<td>" + result[key].orderDate + "</td>\n" +
                        "<td>" + result[key].memberId + "</td>\n" +
                        "<td>" + result[key].accountID + "</td>\n" +
                        "<td>" + result[key].totalPrice + "</td>\n" +
                        "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                        "</tr>\n";
                }
                document.getElementById("week-content").innerHTML = output;
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
                if(response["result"].length == 0){
                    var income = 0.0;
                    document.getElementById("month-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("month-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("month-content").innerHTML = output;
                    $('#month-pagination')[0].innerHTML = '';
                } else {
                    var output = "";
                    var result = response["result"];
                    for(var key in result) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + result[key].id + "</td>\n" +
                            "<td>" + result[key].orderDate + "</td>\n" +
                            "<td>" + result[key].memberId + "</td>\n" +
                            "<td>" + result[key].accountID + "</td>\n" +
                            "<td>" + result[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                    }

                    var pagination = '';
                    for(a = 1; a <= parseInt(response["count"]); a++){
                        pagination += '<li class="page-item"><a class="page-link" href="#" onclick="getLedgerMonthPage(' + a + ')">' + a + '</a></li>';
                    }
                    $('#month-pagination')[0].innerHTML = pagination;

                    document.getElementById("month-income").innerHTML = "<h4>Total Income = Rp " + response["sum"] + ",-";
                    document.getElementById("month-content").innerHTML = output;
                    document.getElementById("month-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerMonthPage(page){
        console.log("masuk");
        $.ajax({
            type: 'GET',
            dataType: "JSON",
            url: "/admin/ledger/monthly/page",
            data: {
                date: $('#month').val(),
                page: page
            },
            success: function(response) {
                var output = "";
                var result = response["result"];
                for(var key in result) {
                    output += "<tr>\n" +
                        "<td>" + (parseInt(page-1) * 10 + parseInt(key) + 1) + "</td>\n" +
                        "<td>" + result[key].id + "</td>\n" +
                        "<td>" + result[key].orderDate + "</td>\n" +
                        "<td>" + result[key].memberId + "</td>\n" +
                        "<td>" + result[key].accountID + "</td>\n" +
                        "<td>" + result[key].totalPrice + "</td>\n" +
                        "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                        "</tr>\n";
                }
                console.log(output);
                document.getElementById("month-content").innerHTML = output;
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
                if(response["result"].length == 0){
                    var income = 0.0;
                    document.getElementById("year-income").innerHTML = "<h4>Total Income = Rp " + income + ",-</h4>";

                    document.getElementById("year-notif").innerHTML = "<p>Invoice tidak ditemukan</p>";

                    var output = "<tr></tr>";
                    document.getElementById("year-content").innerHTML = output;
                    $('#year-pagination')[0].innerHTML = '';
                } else {
                    var output = "";
                    var result = response["result"];
                    for(var key in result) {
                        output += "<tr>\n" +
                            "<td>" + (parseInt(key)+1) + "</td>\n" +
                            "<td>" + result[key].id + "</td>\n" +
                            "<td>" + result[key].orderDate + "</td>\n" +
                            "<td>" + result[key].memberId + "</td>\n" +
                            "<td>" + result[key].accountID + "</td>\n" +
                            "<td>" + result[key].totalPrice + "</td>\n" +
                            "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                            "</tr>\n";
                    }

                    var pagination = '';
                    for(a = 1; a <= parseInt(response["count"]); a++){
                        pagination += '<li class="page-item"><a class="page-link" href="#" onclick="getLedgerYearPage(' + a + ')">' + a + '</a></li>';
                    }
                    $('#year-pagination')[0].innerHTML = pagination;

                    document.getElementById("year-income").innerHTML = "<h4>Total Income = Rp " + response["sum"] + ",-";
                    document.getElementById("year-content").innerHTML = output;
                    document.getElementById("year-notif").innerHTML = "";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }

    function getLedgerYearPage(page){
        $.ajax({
            type: 'GET',
            dataType: "JSON",
            url: "/admin/ledger/yearly/page",
            data: {
                date: $('#year').val(),
                page: page
            },
            success: function(response) {
                var output = "";
                var result = response["result"];
                for(var key in result) {
                    output += "<tr>\n" +
                        "<td>" + (parseInt(page-1) * 10 + parseInt(key) + 1) + "</td>\n" +
                        "<td>" + result[key].id + "</td>\n" +
                        "<td>" + result[key].orderDate + "</td>\n" +
                        "<td>" + result[key].memberId + "</td>\n" +
                        "<td>" + result[key].accountID + "</td>\n" +
                        "<td>" + result[key].totalPrice + "</td>\n" +
                        "<td><a href='/admin/invoice/detail?id=" + result[key].id + "'>Detail</td>\n" +
                        "</tr>\n";
                }
                console.log(output);
                document.getElementById("year-content").innerHTML = output;
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