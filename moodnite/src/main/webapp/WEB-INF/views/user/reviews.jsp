<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      <c:choose><c:when test="${not empty reviews}">
        <ul class="films">
          <li class="separator-short" id="pink">
            <h2>${loggedInUser.name} Microreviews</h2>
          </li>
          <c:forEach items="${reviews}" var="review">
            <li class="film">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${review.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${review.movie.background}" alt="${review.movie.title}"/>
                </a>
              </div>
              
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${review.movie.tmdbId}">${review.movie.title}</a></b></h4>
                <p>${review.reviewXS}</p>
                
                <div class="stars">
                  <a href="${pageContext.servletContext.contextPath}/user/rate/${review.movie.tmdbId}" title="Edit review">
                    <c:forEach var="i" begin="1" end="${review.rate}">
                      &#9733;
                    </c:forEach>
                    <c:forEach var="i" begin="1" end="${10-review.rate}">
                      &#9734;
                    </c:forEach>
                  </a>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>
