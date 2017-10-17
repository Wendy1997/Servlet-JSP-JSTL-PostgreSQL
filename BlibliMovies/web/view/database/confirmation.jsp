<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button>

    <!-- Confirmation-Success -->
    <div class="confirmation">
        <h1 class="title">Are you sure?</h1>
        <div class="stripe"></div><br>
        <a href="film_delete_success.html"><h2>Accept</h2></a>
        <a href="film_menu.html">Back</a>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>