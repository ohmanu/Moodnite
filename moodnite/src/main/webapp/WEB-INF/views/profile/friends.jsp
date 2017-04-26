<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
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
                <h4><b><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${user.name}</a></b></h4>
                <a class="remove" href="${pageContext.servletContext.contextPath}/user/unfollow/${user.id}" title="Unfollow">&#10006;</a>
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