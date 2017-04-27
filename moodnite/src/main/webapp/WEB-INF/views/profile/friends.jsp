<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:profile-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/watched">Watched</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/lists">Lists</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/friends">Friends</a></li>
      </ul>
    </nav>
    
    <article class="follows">
      <c:choose><c:when test="${not empty friends}">
        <ul class="people" data-masonry='{ "itemSelector": ".masonry", "columnWidth": ".person-sheet" }'>
          <li class="separator-short masonry" id="pink">
            <h2>Follows</h2>
          </li>
          <c:forEach items="${friends}" var="user">
            <li class="person-sheet masonry">
              <div class="user-photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews">${user.name}</a></b></h4>
                <br>
                <h4>${user.bio} </h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:profile-layout>
</t:layout>