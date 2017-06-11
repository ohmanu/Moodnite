<%@ tag description="user-layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="user">
  <header class="profile">
    <div class="avatar">
      <a href="${pageContext.servletContext.contextPath}/user/avatar">
        <img class="photo" src="${pageContext.servletContext.contextPath}/resources/images/avatars/${loggedInUser.photo}" alt="Avatar"/>
      </a>
    </div>
    
    <div class="data">
      <h2>${loggedInUser.name}</h2>
      <p>${loggedInUser.bio}</p>
      <a href="${pageContext.servletContext.contextPath}/recomendation/recomendations">Recomendme</a>
    </div>
  </header>
  
  <section class="content">
    <jsp:doBody />
  </section>
</article>