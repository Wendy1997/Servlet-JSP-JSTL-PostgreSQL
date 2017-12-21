<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BlibliMovies</title>

    <!-- JQuery -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/bootstrap.css"></link>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/bootstrap-grid.css"></link>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/bootstrap-reboot.css"></link>
    <%--<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>--%>

    <!-- html2canvas -->
    <script src="${pageContext.request.contextPath}/js/html2canvas.js"></script>

    <!-- jsPDF -->
    <script src="https://unpkg.com/jspdf@latest/dist/jspdf.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/autoPrint.js"></script>

    <!-- Elastic Email -->
    <script src="https://smtpjs.com/v2/smtp.js">
    </script>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/style.css"></link>

    <!-- Font -->
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">

</head>
<body>