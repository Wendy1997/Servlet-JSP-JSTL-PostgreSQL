<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Member Card</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/membercard/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Member Card</h2><br>

                    <div class="form-group">
                        <label for="name">Full Name:</label>
                        <input type="name" class="form-control" id="name" name="fullname" value="${memberCard.fullname}" required>
                    </div>

                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select class="form-control" id="gender" name="gender"${memberCard.gender} required>
                            <option disabled selected value> -- pilih Jenis -- </option>
                            <c:forEach items="${gender}" var="gender">
                                <option value="${gender.gender}" ${memberCard.gender == gender.gender ? 'selected' : ''}>${gender.gender}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birth">Birth Date:</label>
                        <input type="date" class="form-control" id="birth" name="birthdate" value="${memberCard.birthDate}" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone Number:</label>
                        <input type="number" class="form-control" id="phone" name="phonenumber" value="${memberCard.phoneNumber}" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email Address:</label>
                        <input type="email" class="form-control" id="email" name="email" value="${memberCard.email}" required>
                    </div>

                    <input type="hidden" name="id" value="${memberCard.id}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>