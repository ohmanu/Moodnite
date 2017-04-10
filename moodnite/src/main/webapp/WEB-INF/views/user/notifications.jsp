<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="follows">
      <c:choose><c:when test="${not empty notifications}">
        <ul class="people" data-masonry='{ "itemSelector": ".masonry", "columnWidth": ".person-sheet" }'>
          <li class="separator-short masonry" id="pink">
            <h2>Notifications</h2>
          </li>
          <c:forEach items="${notifications}" var="user">
            <li class="person-sheet masonry">
              <div class="user-photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
              </div>
              <div class="data">
                ${user.name} started following you. <br>
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