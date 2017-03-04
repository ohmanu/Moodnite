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
          <input type="text" name="query">
        </form>
      </div>
      <div class="user">
        <a href="${pageContext.servletContext.contextPath}/user/reviews"><img src="${pageContext.request.contextPath}/resources/images/user.png"/></a>
        
        <c:choose><c:when test="${not empty loggedInUser.name}">
          <a href="${pageContext.servletContext.contextPath}/user/reviews">${loggedInUser.name}</a>
        </c:when></c:choose>
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