<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      <c:choose><c:when test="${not empty movies}">
        <ul class="films" data-masonry='{ "itemSelector": ".masonry", "columnWidth": ".film" }'>
          <li class="separator-short masonry" id="pink">
            <h2>${list_name}</h2>
          </li>
          <c:forEach items="${movies}" var="movie">
            <li class="film masonry">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${movie.background}" alt="${movie.title}"/>
                </a>
              </div>
              
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${movie.tmdbId}">${movie.title}</a></b></h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>
