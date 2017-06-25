<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, user-scalable=no">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/landing.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/responsive.css">
  <title>Moodnite</title>
</head>
<body>
  <article class="film">
    <div class="cover" style="background-image: url('https://image.tmdb.org/t/p/w1280/${background}');">
      <div class="box">
        <div class="top">
        </div>
        <div class="actions">
        	<a class="sign-in" href="${pageContext.servletContext.contextPath}/user/sign-in">Sign In</a>
        	<a class="login" href="${pageContext.servletContext.contextPath}/user/login">Login</a>
        	<a class="visit" href="${pageContext.servletContext.contextPath}/home">Visit</a>
        </div>
      </div>
    </div>
  </article>
</body>
</html>