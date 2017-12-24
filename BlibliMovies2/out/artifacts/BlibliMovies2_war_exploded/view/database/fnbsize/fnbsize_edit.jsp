<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Size</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/fnbsize/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Size</h2><br>

                    <div class="form-group">
                        <label for="size">Size:</label>
                        <input type="name" class="form-control" name="size" id="size" value="${size.size}" required>
                    </div>

                    <input type="hidden" name="id" value="${size.id}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>