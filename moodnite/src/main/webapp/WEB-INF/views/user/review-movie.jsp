<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="user">
    <article class="reviews">      
      <form:form action="${pageContext.servletContext.contextPath}/user/review/${movieId}" method="POST" modelAttribute="rate">
        <div class="film-about" style="background-image: url('https://image.tmdb.org/t/p/w780/${backdrop_path}');">
        </div>
        
        
        <div class="stars">
          <a href="${pageContext.servletContext.contextPath}/user/rate/${rate.movie.tmdbId}" title="Edit rate">
            <c:forEach var="i" begin="1" end="${rate.rate}">&#9733;</c:forEach><c:forEach var="i" begin="1" end="${10-rate.rate}">&#9734;</c:forEach>
          </a>
        </div>
       
        
        <div class="field">
          <form:textarea path="reviewXS" placeholder="${rate.reviewXS}"/>
        </div>
        
        <div class="field">
          <input type="submit" value="Save"/>
        </div>
        
        <div class="field">
          <div class="public-announcement">
            <a class="twitter-share-button" href="https://twitter.com/intent/tweet?text=${rate.movie.title} <c:forEach var="i" begin="1" end="${rate.rate}"> ★</c:forEach><c:forEach var="i" begin="1" end="${10-rate.rate}"> ☆</c:forEach> ${pageContext.request.serverName}${pageContext.servletContext.contextPath}/movie/${rate.movie.tmdbId}" target="_blank">
        	  <img class="photo" src="${pageContext.servletContext.contextPath}/resources/images/icon-tweet.png" alt="Tweet"/>
        	</a>
          </div>
        </div>
        
        <div class="field">
          <div class="public-announcement">Do you want to <a href="${pageContext.servletContext.contextPath}/user/delete/rate/${movieId}">DELETE</a> this review?</div>
        </div>
      </form:form>      
    </article>
  </article>
</t:layout>
