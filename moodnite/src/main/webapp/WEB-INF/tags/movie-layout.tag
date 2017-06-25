<%@ tag description="movie-layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, user-scalable=no">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/responsive.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jqcloud.css">
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <title>Moodnite</title>
</head>
<body>
  <%@ include file="/WEB-INF/fragments/header.jspf" %>
  
  <section class="content movie-content">
    <jsp:doBody />
  </section>
</body>
</html>