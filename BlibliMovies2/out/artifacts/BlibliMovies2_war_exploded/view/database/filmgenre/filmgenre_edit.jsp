<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Genre</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/filmgenre/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Genre</h2><br>

                    <div class="form-group">
                        <label for="genre">Genre:</label>
                        <input type="name" class="form-control" name="genre" id="genre" value="${genre.genre}" required>
                    </div>

                    <input type="hidden" name="id" value="${genre.id}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>