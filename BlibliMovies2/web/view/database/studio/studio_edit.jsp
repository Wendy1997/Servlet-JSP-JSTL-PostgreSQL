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
        <form action="/admin/studio/edit" method="post" onsubmit="return confirm('Are You Sure?');">
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
                                <option value="${type.id}" ${studio.type == type.id ? 'selected' : ''}>${type.type}</option>
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