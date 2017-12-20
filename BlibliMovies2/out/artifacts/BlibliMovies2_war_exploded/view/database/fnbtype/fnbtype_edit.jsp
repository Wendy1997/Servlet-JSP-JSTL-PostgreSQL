<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'fnbtype_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Type</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/fnbtype/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Type</h2><br>

                    <div class="form-group">
                        <label for="type">Type:</label>
                        <input type="name" class="form-control" name="type" id="type" value="${type.type}" required>
                    </div>

                    <input type="hidden" name="id" value="${type.id}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>