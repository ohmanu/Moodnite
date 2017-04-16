<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      
      <form:form action="${pageContext.servletContext.contextPath}/user/review/${movieId}" method="POST" modelAttribute="rate">
        <div class="film-about" style="background-image: url('https://image.tmdb.org/t/p/w780/${backdrop_path}');">
        </div>
        
        <div class="field">
          <form:textarea path="reviewXS" placeholder="${rate.reviewXS}"/>
        </div>
        
        <div class="field">
          <input type="submit" value="Save"/>
        </div>
        
        <a href="https://twitter.com/intent/tweet?text=${rate.movie.title} <c:forEach var="i" begin="1" end="${rate.rate}"> ★</c:forEach><c:forEach var="i" begin="1" end="${10-rate.rate}"> ☆</c:forEach> #moodnite ${pageContext.servletContext.contextPath}/movie/${movie.tmdbId}" target="_blank">Tweet</a>
        
        <div class="field">
          <div class="public-announcement">Do you want to <a href="${pageContext.servletContext.contextPath}/user/delete/rate/${movieId}">DELETE</a> this review?</div>
        </div>
      </form:form>      
    </article>
  </t:user-layout>
</t:layout>
