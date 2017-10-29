<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbar.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Title -->
    <div class="jumbotron">
        <h1 class="title">Halo <c:out value="${sessionScope.storename}"></c:out></h1>
    </div>

    <!-- Form -->
    <div class="container-fluid">
        <form action="/login" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="name" class="form-control" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-default">Submit ></button>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>
