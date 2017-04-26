<%@ tag description="user-layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="user">
  <header class="profile">
    <div class="avatar">
      <img class="photo" src="${pageContext.servletContext.contextPath}/resources/images/avatars/${profile.photo}" alt="Avatar"/>
    </div>
    
    <div class="data">
      <h2>${profile.name}</h2>
      <p>${profile.bio}</p>
    </div>
  </header>
  
  <nav class="menu">
    <ul>
      <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/reviews">Reviews</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/watched">Watched</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/lists">Lists</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/friends">Friends</a></li>
    </ul>
  </nav>
  
  <section class="content">
    <jsp:doBody />
  </section>
</article>