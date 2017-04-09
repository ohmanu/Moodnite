<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
  <title>Moodnite</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
</head>
<body>
  <header class="moodnite">
    <nav>
      <div class="search">
        <form id="form" method="POST" action="${pageContext.servletContext.contextPath}/search">
          <input type="text" name="query" placeholder="Search movies, people and users.">
        </form>
      </div>
      <div class="user">
        <c:choose>
        <c:when test="${not empty loggedInUser.name}">
          <a href="${pageContext.servletContext.contextPath}/user/wall"><img class="photo-xs" src="${pageContext.servletContext.contextPath}/resources/images/avatars/${loggedInUser.photo}" alt="Avatar"/></a>
        </c:when>
        <c:otherwise>
          <a href="${pageContext.servletContext.contextPath}/user/wall"><img src="${pageContext.request.contextPath}/resources/images/user.png"/></a>
        </c:otherwise>
        </c:choose>
      </div>
      
      <div class="logo"><a href="${pageContext.servletContext.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/moodnite-logo.png"/></a></div>    
    </nav>
  </header>
  
  <section class="content">
    <jsp:doBody />
  </section>

  <footer>
    <div class="area">
      @Oh_Manu
    </div>
  </footer>
</body>
</html>