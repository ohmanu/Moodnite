<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/user/wall">Wall</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/watched">Watched</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/friends">Friends</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/user/notifications">Notifications</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/config">Config</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
      </ul>
    </nav>
    
    <article class="follows">
      <c:choose><c:when test="${not empty notifications}">
        <ul class="people masonry">
          <li class="separator-short brick" id="pink">
            <h2>Notifications</h2>
          </li>
          <c:forEach items="${notifications}" var="user">
            <li class="person-sheet brick">
              <div class="user-photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
              </div>
              <div class="data">
                <a class="user-link" href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews">${user.name}</a> started following you. <br>
                <a class="remove" href="${pageContext.servletContext.contextPath}/user/notification/ignore/${user.id}" title="Ignore">&#10006;</a>
                <a class="add" href="${pageContext.servletContext.contextPath}/user/follow/${user.id}" title="Follow">&#10010;</a>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>