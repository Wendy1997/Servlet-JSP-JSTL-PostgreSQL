<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid" align="center">

    <!-- Close Button -->
    <button type="button" class="close" onclick="document.location.href = 'film_menu.html';">&times;</button>

    <!-- Confirmation -->
    <div class="confirmation">
        <h1 class="title"><c:out value="${title} ${complete}"></c:out></h1>
        <div class="stripe"></div><br>
        <a id="link" href="<c:out value="${link}"></c:out>"><c:out value="${title} >"></c:out></a>
    </div>
</div>

<script>
    setTimeout(
        function () {
            window.location.href = document.getElementById("link");
        }, 3000);
</script>

<%@ include file = "/include/foot.jsp" %>