<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="search">
    <c:choose><c:when test="${not empty films_results}">
      <div class="films-results">
        <ul class="films">
          <c:forEach items="${films_results}" var="film">
            <c:choose><c:when test="${film.poster_path != null}">
              <li class="film">
                <div class="poster">
                  <a href="${pageContext.servletContext.contextPath}/movie/${film.id}">
                    <img src="https://image.tmdb.org/t/p/w185/${film.poster_path}" alt="${film.title}"/>
                  </a>
                </div>
              </li>
            </c:when></c:choose>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
    
    <c:choose><c:when test="${not empty people_results}">
      <div class="people-results">
        <ul class="people">
          <c:forEach items="${people_results}" var="person">
            <li class="person-sheet">
              <div class="photo" style="background-image: url('https://image.tmdb.org/t/p/w185/${person.profile_path}');">
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${person.name}</a></b></h4>
                <br>
                <h4>${person.character}</h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
    
    <c:choose><c:when test="${not empty users_results}">
      <div class="films-results">
        <ul class="people">
          <c:forEach items="${users_results}" var="user">
            <li class="person-sheet">
              <div class="photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
                <a href="${pageContext.servletContext.contextPath}/user/unfollow/${user.id}">&#10010;</a>
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
</t:layout>