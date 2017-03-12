<%@ tag description="user-layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="user">
  <nav class="menu">
    <ul>
      <li><a href="${pageContext.servletContext.contextPath}/user/reviews">Reviews</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/watched">Watched</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/avatar">Avatar</a></li>
      <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
    </ul>
  </nav>
  
  <section class="content">
    <jsp:doBody />
  </section>
</article>