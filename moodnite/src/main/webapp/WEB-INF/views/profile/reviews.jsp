<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:profile-layout>
    <nav class="menu">
      <ul>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/watched">Watched</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/profile/${profile.id}/friends">Friends</a></li>
      </ul>
    </nav>
  
    <article class="reviews">
      <c:choose><c:when test="${not empty reviews}">
        <ul class="films masonry">
          <li class="separator-short brick" id="pink">
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
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:profile-layout>
</t:layout>
