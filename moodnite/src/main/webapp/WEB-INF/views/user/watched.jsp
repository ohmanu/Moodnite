<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <nav class="menu">
      <ul>
        <li><a href="${pageContext.servletContext.contextPath}/user/wall">Wall</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/reviews">Reviews</a></li>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/user/watched">Watched</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/friends">Friends</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/notifications">Notifications</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/config">Config</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
      </ul>
    </nav>
    
    <article class="watched-list">
      <c:choose><c:when test="${not empty watched_list}">
        <ul class="films" data-masonry='{ "itemSelector": ".masonry", "columnWidth": ".film" }'>
          <li class="separator-short masonry" id="pink">
            <h2>Watched list</h2>
          </li>
          <c:forEach items="${watched_list}" var="watched">
            <li class="film masonry">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${watched.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${watched.movie.background}" alt="${watched.movie.title}"/>
                </a>
              </div>
              <div class="data">
                <p>${review.rate}</p>
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${watched.movie.tmdbId}">${watched.movie.title}</a></b></h4>
                <p>${watched.comment}</p>
                <p>${watched.formattedDate} <a class="remove" href="${pageContext.servletContext.contextPath}/user/delete/watch/${watched.id}" title="Delete">&#10006;</a></p>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>