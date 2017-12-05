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
      <c:choose>
        <c:when test="${loggedInUser.admin}">
          <a href="${pageContext.servletContext.contextPath}/admin/user-list"><img src="${pageContext.servletContext.contextPath}/resources/images/icon-admin.png" alt="admin"/></a>
        </c:when>
      </c:choose>
      <p>${loggedInUser.bio}</p>
      <a class="recommendation" href="${pageContext.servletContext.contextPath}/recommendation/recommendations">Recommendation</a>
    </div>
  </header>
  
  <section class="content">
    <jsp:doBody />
  </section>
</article>