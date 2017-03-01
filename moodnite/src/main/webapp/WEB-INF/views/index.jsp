<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="home">
	
    <c:choose><c:when test="${not empty popular_movies}">
      <div class="popular-movies">
        <ul class="films">
          <li class="separator-short" id="pink">
            <h2>Popular movies</h2>
          </li>
          <c:forEach items="${popular_movies}" var="pupular_movie">
            <li class="film">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${pupular_movie.id}">
                  <img src="https://image.tmdb.org/t/p/w185/${pupular_movie.backdrop_path}" alt="${pupular_movie.title}"/>
                </a>
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${pupular_movie.id}">${pupular_movie.title}</a></b></h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>

    <c:choose><c:when test="${not empty upcoming_movies}">
      <div class="upcoming-movies">
        <ul class="films">
          <li class="separator">
            <h2>Upcoming movies</h2>
          </li>
          <c:forEach items="${upcoming_movies}" var="upcoming_movie">
            <li class="film">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${upcoming_movie.id}">
                  <img src="https://image.tmdb.org/t/p/w185/${upcoming_movie.poster_path}" alt="${upcoming_movie.title}"/>
                </a>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
    
  </article>
</t:layout>