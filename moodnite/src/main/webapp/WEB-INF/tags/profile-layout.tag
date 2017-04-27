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
  
  <section class="content">
    <jsp:doBody />
  </section>
</article>