<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <c:choose><c:when test="${movie_details != null}">
    <article class="film">
      <div class="cover" style="background-image: url('https://image.tmdb.org/t/p/w1280/${movie_details.backdrop_path}');">
        <div class="rate">
        
        </div>
      
        <div class="rug">
          <header class="title"><h1>${movie_details.title}</h1></header>
          
          <div class="actions">
            <a href="${pageContext.servletContext.contextPath}/user/watch/${movie_details.id}">Watch</a>
            <a href="${pageContext.servletContext.contextPath}/user/rate/${movie_details.id}">Rate</a>
            <a href="${pageContext.servletContext.contextPath}/user/list/${movie_details.id}">Add to list</a>
          </div>
          
          <div class="data-sheet">
            <c:forEach items="${directors}" var="director">
              <h2><a href="${pageContext.servletContext.contextPath}/person/${director.id}">${director.name}</a>${director.coma}</h2>
            </c:forEach>
          		
            <h2 class="year">${year}</h2>
          </div>
        </div>
      </div>
      
      <div class="details">
        <div class="details-sheet">
          <c:forEach items="${production_countries}" var="countrie">
            <h3>${countrie.name}${countrie.coma}</h3> 
          </c:forEach>
          <br>
          <h3>${movie_details.runtime} mins</h3>
          <br> 
          <h3>${movie_details.vote_average}</h3>
        </div>
        
        <div class="synopsis">
          <c:choose><c:when test="${not empty movie_details.tagline}">
            <p class="tagline">${movie_details.tagline}</p>
          </c:when></c:choose>
          
          <p>${movie_details.overview}</p>
        </div>
        
        <div class="genres">
          <c:forEach items="${genres}" var="genre">
            <h3>${genre.name}</h3> 
          </c:forEach>
        </div>
      </div>
      	
      <c:choose><c:when test="${not empty cast}">
        <div class="cast">
          <ul class="people">
            <c:forEach items="${cast}" var="person">
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
            <li class="person-sheet">
              <div class="photo">
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${movie_details.id}/credits">Full cast and crew</a></b></h4>
              </div>
            </li>
          </ul>
        </div>
      </c:when></c:choose>
      
      <c:choose><c:when test="${not empty similar_movies}">
        <div class="similar-movies">
          <ul class="films">
            <li class="separator">
              <h2>Related movies</h2>
            </li>
            <c:forEach items="${similar_movies}" var="similar_movie">
              <li class="film">
                <div class="poster">
                  <a href="${pageContext.servletContext.contextPath}/movie/${similar_movie.id}">
                    <img src="https://image.tmdb.org/t/p/w185/${similar_movie.poster_path}" alt="${similar_movie.title}"/>
                  </a>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </c:when></c:choose>
      
    </article>
  </c:when></c:choose>
</t:layout>
