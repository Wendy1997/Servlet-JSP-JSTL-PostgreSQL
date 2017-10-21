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
        <a href="<c:out value="${link}"></c:out>"><c:out value="${title} >"></c:out></a>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>