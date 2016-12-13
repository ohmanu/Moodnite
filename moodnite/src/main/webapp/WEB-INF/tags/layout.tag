<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet" />
	<title>Moodnite</title>
</head>
<body>
	<h1>moodnite</h1>
	
	<a href="${pageContext.servletContext.contextPath}/">Home</a>
	
	<section class="content">
		<jsp:doBody />
	</section>
</body>
</html>