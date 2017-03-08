<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
  <article class="home">
  
    <c:choose><c:when test="${not empty watched_list}">
      <div class="upcoming-movies">
        <ul class="films">
          <li class="separator">
            <h2>Watched Movies</h2>
          </li>
          <c:forEach items="${watched_list}" var="watched_movie">
            <li class="film">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${watched_movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${watched_movie.poster_path}" alt="${watched_movie.title}"/>
                </a>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:when></c:choose>
    
  </article>
  </t:user-layout>
</t:layout>