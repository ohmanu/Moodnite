<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" >
	<title>Moodnite</title>
</head>
<body>
	<header class="moodnite">
		<nav>
			<div class="logo"><a href="${pageContext.servletContext.contextPath}/">moodnite V0.1</a></div>
		</nav>
	</header>
	<section class="content">
		<jsp:doBody />
	</section>
</body>
</html>