<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="watched-list">
      <c:choose><c:when test="${not empty friends}">
        <div class="films-results">
          <ul class="people">
            <c:forEach items="${friends}" var="user">
              <li class="person-sheet">
                <div class="photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
                  <a href="${pageContext.servletContext.contextPath}/user/unfollow/${user.id}">&#10006;</a>
                </div>
                <div class="data">
                  <h4><b><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${user.name}</a></b></h4>
                  <br>
                  <h4>${user.bio} </h4>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>