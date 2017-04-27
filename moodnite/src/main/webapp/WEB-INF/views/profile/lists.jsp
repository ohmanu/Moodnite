<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:profile-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/watched">Watched</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/friends">Friends</a></li>
      </ul>
    </nav>
    
    <article class="lists">
      <c:choose><c:when test="${not empty lists_names}">
        <ul class="films">
          <li class="separator-short" id="pink">
            <h2>Lists</h2>
          </li>
          <c:forEach items="${lists_names}" var="tag">
            <li class="tag">
              <h2><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/list/${tag}">${tag}</a></h2>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:profile-layout>
</t:layout>
