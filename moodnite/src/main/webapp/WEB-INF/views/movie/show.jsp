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
            <a class="action-watch" href="${pageContext.servletContext.contextPath}/user/watch/${movie_details.id}">Watch</a>
            <a class="action-rate" href="${pageContext.servletContext.contextPath}/user/rate/${movie_details.id}">Rate</a>
            <a class="action-add-to-list" href="${pageContext.servletContext.contextPath}/user/add-to-list/${movie_details.id}">List</a>
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
          <ul class="people" id="ohList">
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
          
          <div class=sub-actions>
          	<a id="loadMore">Load more</a>
          </div>
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
      
      <c:choose><c:when test="${not empty reviews}">
        <div class="movie-reviews">
          <ul class="films masonry">
            <li class="separator-short brick">
              <h2>Reviews</h2>
            </li>
            <c:forEach items="${reviews}" var="review">
              <li class="film brick">
                <div class="poster">
                  <a href="${pageContext.servletContext.contextPath}/movie/${review.movie.tmdbId}">
                    <img src="https://image.tmdb.org/t/p/w185/${review.movie.background}" alt="${review.movie.title}"/>
                  </a>
                </div>
                
                <div class="data">
                  <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${review.movie.tmdbId}">${review.movie.title}</a></b></h4>
                  <p>${review.reviewXS}</p>
                  
                  <div class="stars">
                    <c:forEach var="i" begin="1" end="${review.rate}">
                      &#9733;
                    </c:forEach>
                    <c:forEach var="i" begin="1" end="${10-review.rate}">
                      &#9734;
                    </c:forEach>
                  </div>
                </div>
                
                <div class="user">
                  <div class= "avatar">
                    <img class="user-avatar" 
                    src="${pageContext.servletContext.contextPath}/resources/images/avatars/${review.getUser().photo}" 
                    alt="${review.getUser().name}"/>
                  </div>
                  <div class="by">
                    by <a class="user-link" href="${pageContext.servletContext.contextPath}/profile/${review.getUser().id}/reviews">${review.getUser().name}</a>
                  </div>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </c:when></c:choose>
    </article>
  </c:when></c:choose>
</t:layout>
