<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
	<title>Moodnite</title>
</head>
<body>
	<header class="moodnite">
		<nav>
			<div class="search">
				<form id="form" method="POST" action="${pageContext.servletContext.contextPath}/search">
				    <input type="text" name="query"> <input type="submit" value="Submit">
				</form>
			</div>
			
			<div class="logo"><a href="${pageContext.servletContext.contextPath}/">moodnite v-0.1</a></div>
		</nav>
	</header>
	<section class="content">
		<jsp:doBody />
	</section>
</body>
</html>