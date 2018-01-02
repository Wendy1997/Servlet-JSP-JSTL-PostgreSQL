<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Create Member Card</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form id="form">
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
                            <c:forEach items="${gender}" var="gender">
                                <option value="${gender.id}">${gender.gender}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birth">Birth Date:</label>
                        <input type="date" class="form-control" id="birth" name="birthdate" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone Number:</label>
                        <input type="number" class="form-control" id="phone" min="100000000" max="999999999999" name="phonenumber" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email Address:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>

                    <button class="btn btn-default" id="submit">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    $(document).ready(function(){
       $('#form').submit(function (e) {
           e.preventDefault();
           if(onsubmit="return confirm('Are You Sure?');"){
               $.ajax({
                   type: 'POST',
                   url: "/admin/membercard/add",
                   dataType: "JSON",
                   data: {
                       fullname: $('#name').val(),
                       gender: $('#gender').val(),
                       birthdate: $('#birth').val(),
                       phonenumber: $('#phone').val(),
                       email: $('#email').val()
                   },
                   success: function (response) {
                       console.log(response);
                       var head = '<%@ include file = "/include/emailHead.jsp" %>';
                       var foot = '<%@ include file = "/include/emailFoot.jsp" %>';
                       var content =
                           '       <table width="100%" cellpadding="0" cellspacing="0">\n' +
                           '        <tr>\n' +
                           '         <td class="content-block">\n' +
                           '          <h1 class="aligncenter">Hai ' + response["result"].fullname + '!</h1>\n' +
                           '         </td>\n' +
                           '        </tr>\n' +
                           '        <tr>\n' +
                           '         <td class="content-block">\n' +
                           '          <h2 class="aligncenter">Please confirm your email by click this link</h2>\n' +
                           '         </td>\n' +
                           '        </tr>\n' +
                           '        <tr>\n' +
                           '         <td class="content-block aligncenter">\n' + '<a href=http://localhost:8080/confirmation?id=' + response["result"].id + '&code=' + response["hash"] + '>http://localhost:8080/confirmation?id=' + response["result"].id + '&code=' + response["hash"] + '</a>' +
                           '         </td>\n' +
                           '        </tr>\n' +
                           '        <tr>\n' +
                           '         <td class="content-block aligncenter">\n' +
                           '          BlibliMovies\n' +
                           '         </td>\n' +
                           '        </tr>\n' +
                           '       </table>\n';

                       $.ajax({
                           type: 'POST',
                           url: "https://api.elasticemail.com/v2/email/send",
                           dataType: "JSON",
                           data: {
                               apikey: "312ad91b-ecd9-48bf-b0db-14b304b67fe0",
                               bodyHtml: head + content + foot,
                               from: "wendydamar.wb@gmail.com",
                               fromName: "Bliblimovies",
                               to: response["result"].email,
                               replyToName: response["result"].fullname,
                               subject: "Thank You!"
                           },
                           success: function (response) {
                               console.log(response);
                               window.location.href = "/admin/membercard/add/success";
                           },
                           error: function (response) {
                               console.log(response);
                           }
                       });
                   }, error: function (response) {
                       window.alert("Email telah digunakan");
                   }
               });
           }
       });
    });
</script>

<%@ include file = "/include/foot.jsp" %>