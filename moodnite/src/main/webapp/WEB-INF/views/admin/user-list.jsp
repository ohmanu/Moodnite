<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    
    <article class="follows">
      <c:choose><c:when test="${not empty users}">
        <ul class="people masonry">
          <li class="separator-short brick" id="pink">
            <h2>Follows</h2>
          </li>
          <c:forEach items="${users}" var="user">
            <li class="person-sheet brick">
              <div class="user-photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
                <a class="person-photo" href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews"></a>
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews">${user.name}</a></b></h4>
                <a class="remove" href="${pageContext.servletContext.contextPath}/admin/user/delete/${user.id}" title="Ban">&#10006;</a>
                <br>
                <h4>${user.bio} </h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>