<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="credits">
    <c:choose><c:when test="${not empty cast}">
      <div class="cast">
        <h3><a href="${pageContext.servletContext.contextPath}/movie/${movie_details.id}">${movie_details.title}</a></h3>
        <br>
        <br>
        <h4><b>- CAST -</b></h4>
        <ul>
          <c:forEach items="${cast}" var="person">
            <li>
              <div class="left-column">
                <h4 class="left"><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${person.name}</a></h4>
              </div>
              <div class="right-column">
                <h4 class="right">${person.character}</h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
    
    <c:choose><c:when test="${not empty crew}">
      <div class="crew">
        <h4><b>- CREW -</b></h4>
        <ul>
          <c:forEach items="${crew}" var="person">
            <li>
              <div class="left-column">
                <h4 class="left"><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${person.name}</a></h4>
              </div>
              <div class="right-column">
                <h4 class="right">${person.job}</h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
  </article>
</t:layout>
