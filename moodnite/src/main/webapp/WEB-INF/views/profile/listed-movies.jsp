<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:profile-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/watched">Watched</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/friends">Friends</a></li>
      </ul>
    </nav>
    
    <article class="lists">
      <c:choose><c:when test="${not empty tag_movies}">
        <ul class="films" data-masonry='{ "itemSelector": ".masonry", "columnWidth": ".film" }'>
          <li class="separator-short masonry" id="pink">
            <h2>${list_name}</h2>
          </li>
          <c:forEach items="${tag_movies}" var="tag_movie">
            <li class="film masonry">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${tag_movie.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${tag_movie.movie.background}" alt="${tag_movie.movie.title}"/>
                </a>
              </div>
              
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${tag_movie.movie.tmdbId}">${tag_movie.movie.title}</a></b></h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:profile-layout>
</t:layout>
