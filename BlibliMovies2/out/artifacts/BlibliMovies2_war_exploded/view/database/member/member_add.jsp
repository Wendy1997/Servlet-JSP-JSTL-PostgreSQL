<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'member_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Member Card</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/membercard/add" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Member Card</h2><br>

                    <div class="form-group">
                        <label for="name">Full Name:</label>
                        <input type="name" class="form-control" id="name" name="fullname" required>
                    </div>

                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select class="form-control" id="gender" name="gender" required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <option value="Pria">Pria</option>
                            <option value="Wanita">Wanita</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birth">Birth Date:</label>
                        <input type="date" class="form-control" id="birth" name="birthdate" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone Number:</label>
                        <input type="number" class="form-control" id="phone" name="phonenumber" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email Address:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>