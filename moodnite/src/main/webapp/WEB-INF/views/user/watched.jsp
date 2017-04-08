<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="watched-list">
      <c:choose><c:when test="${not empty watched_list}">
        <ul class="films" data-masonry='{ "itemSelector": ".masonary", "columnWidth": ".film" }'>
          <li class="separator-short" id="pink">
            <h2>${loggedInUser.name} watched list</h2>
          </li>
          <c:forEach items="${watched_list}" var="watched">
            <li class="film masonary">
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