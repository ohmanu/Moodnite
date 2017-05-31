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
        <li><a href="${pageContext.servletContext.contextPath}/user/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/friends">Friends</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/notifications">Notifications</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/config">Config</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
      </ul>
    </nav>
    
    <article class="follows">
      <c:choose><c:when test="${not empty recomendation_summary.socialNet}">
        <ul class="people masonry">
          <li class="separator-short brick" id="pink">
            <h2>SocialNet</h2>
          </li>
          <c:forEach items="${recomendation_summary.socialNet}" var="user">
            <li class="person-sheet brick">
              <div class="user-photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews">${user.name}</a></b></h4>
                <a class="remove" href="${pageContext.servletContext.contextPath}/user/unfollow/${user.id}" title="Unfollow">&#10006;</a>
                <br>
                <h4>${user.bio} </h4>
                <c:forEach items="${user.ratedList}" var="review">
                	<p>${review.movie.title}</p>
                </c:forEach>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
      <h1>${recomendation_summary.averageRating }</h1>

        <ul class="films">
          <li class="separator-short" id="pink">
            <h2>Best rated movies</h2>
          </li>
          <c:forEach items="${recomendation_summary.bestRatedMovies}" var="movie">
            <li class="film">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${movie.background}" alt="${movie.title}"/>
                </a>
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${movie.id}">${movie.title}</a></b></h4>
              </div>
            </li>
          </c:forEach>
        </ul>      

    </article>
  </t:user-layout>
</t:layout>