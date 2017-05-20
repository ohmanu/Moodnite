<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/user/wall">Wall</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/watched">Watched</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/user/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/friends">Friends</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/notifications">Notifications</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/config">Config</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
      </ul>
    </nav>
    
    <article class="lists">
      <c:choose><c:when test="${not empty tag_movies}">
        <ul class="films masonry">
          <li class="separator-short brick" id="pink">
            <h2>${list_name}</h2>
          </li>
          <c:forEach items="${tag_movies}" var="tag_movie">
            <li class="film brick">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${tag_movie.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${tag_movie.movie.background}" alt="${tag_movie.movie.title}"/>
                </a>
              </div>
              
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${tag_movie.movie.tmdbId}">${tag_movie.movie.title}</a></b></h4>
                <p><a class="remove" href="${pageContext.servletContext.contextPath}/user/delete/movie-from-list/${list_name}/${tag_movie.id}" title="Delete">&#10006;</a></p>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>
