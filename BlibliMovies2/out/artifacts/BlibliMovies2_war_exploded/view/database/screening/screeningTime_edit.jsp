<%@ include file = "/include/head.jsp" %>
<%@ include file = "/include/navbarAccount.jsp" %>

<!-- Content -->
<div class="container-fluid">

    <!-- Close Button -->
    <a type="button" class="close" href="javascript:history.back()">&times;</a>

    <div class="jumbotron">

        <!-- Title -->
        <h1>Edit Screening Time</h1>
        <div class="stripe"></div>
        <br><br>

        <!-- Forms -->
        <form action="/admin/screentime/update" method="post" onsubmit="return confirm('Are You Sure?');">
            <div>
                <div class="col-lg-6" id="form1">
                    <h2>Screening Time</h2><br>

                    <div class="form-group">
                        <label for="screen_time_1">Screening Time 1:</label>
                        <input type="time" class="form-control" id="screen_time_1" name="screen_time" value="${screeningTime.time}" required>
                    </div>

                    <div class="form-group">
                        <label for="studio1">Studio 1:</label>
                        <select class="form-control" id="studio1" name="studio" required>
                            <option disabled selected value> -- pilih Studio -- </option>
                            <c:forEach items="${studio}" var="studio">
                                <option value="<c:out value='${studio.id}'></c:out>" ${screeningTime.studioId == studio.id ? 'selected' : ''}><c:out value="${studio.name}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <input type="hidden" name="id" value="${screeningTime.id}">
                <input type="hidden" name="filmid" value="${filmid}">
                <input type="hidden" name="duration" value="${duration}">
                <button type="submit" class="btn btn-default">Submit ></button>
            </div>
        </form>
    </div>
</div>

<%@ include file = "/include/foot.jsp" %>