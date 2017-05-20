<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:profile-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/reviews">Reviews</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/watched">Watched</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/friends">Friends</a></li>
      </ul>
    </nav>
    
    <article class="watched-list">
      <c:choose><c:when test="${not empty watched_list}">
        <ul class="films masonry">
          <li class="separator-short brick" id="pink">
            <h2>Watched list</h2>
          </li>
          <c:forEach items="${watched_list}" var="watched">
            <li class="film brick">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${watched.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${watched.movie.background}" alt="${watched.movie.title}"/>
                </a>
              </div>
              <div class="data">
                <p>${review.rate}</p>
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${watched.movie.tmdbId}">${watched.movie.title}</a></b></h4>
                <p>${watched.comment}</p>
                <p>${watched.formattedDate}</p>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:profile-layout>
</t:layout>