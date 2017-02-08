<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="search">
    <c:choose><c:when test="${not empty films_results}">
      <div class="films-results">
        <div class="section-title">
          <h2>Films</h2>
        </div>
        <ul class="films">
          <c:forEach items="${films_results}" var="movie">
            <li class="film">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${movie.id}">
                  <img src="https://image.tmdb.org/t/p/w185/${movie.poster_path}" alt="${movie.title}"/>
                </a>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
    
    <c:choose><c:when test="${not empty people_results}">
      <div class="people-results">
        <div class="section-title">
          <h2>People</h2>
        </div>
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
  </article>
</t:layout>