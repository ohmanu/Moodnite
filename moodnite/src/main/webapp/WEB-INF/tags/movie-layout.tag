<%@ tag description="movie-layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/responsive.css">
  <title>Moodnite</title>
</head>
<body>
  <%@ include file="/WEB-INF/fragments/header.jspf" %>
  
  <section class="content movie-content">
    <jsp:doBody />
  </section>
</body>
</html>