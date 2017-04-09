<%@ tag description="user-layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="user">
  <header class="profile">
    <div class="avatar">
      <img class="photo" src="${pageContext.servletContext.contextPath}/resources/images/avatars/${loggedInUser.photo}" alt="Avatar"/>
    </div>
    
    <div class="data">
      <h2>${loggedInUser.name}</h2>
      <p>${loggedInUser.bio}</p>
    </div>
  </header>
  
  <nav class="menu">
    <ul>
      <li><a href="${pageContext.servletContext.contextPath}/user/wall">Wall</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/reviews">Reviews</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/watched">Watched</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/friends">Friends</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/notifications">Notifications</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/config">Config</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
    </ul>
  </nav>
  
  <section class="content">
    <jsp:doBody />
  </section>
</article>