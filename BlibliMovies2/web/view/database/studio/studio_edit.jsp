<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'filmgenre_menu.html';">&times;</button>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Genre</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/studio/edit" method="post">
            <div class="row">
                <div class="col-lg-6" id="form1">
                    <h2>Data Genre</h2><br>

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="name" class="form-control" name="name" id="name" value="${studio.name}" required>
                    </div>

                    <div class="form-group">
                        <label for="type">Type:</label>
                        <select class="form-control" id="type" name="type" required>
                            <option disabled selected value> -- pilih Tipe Studio -- </option>
                            <c:forEach items="${type}" var="type">
                                <option value="${type.type}" ${studio.type == type.type ? 'selected' : ''}>${type.type}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="number" class="form-control" name="price" id="price" value="${studio.price}" required>
                    </div>

                    <input type="hidden" name="id" value="${studio.id}">
                    <button type="submit" class="btn btn-default">Submit ></button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>