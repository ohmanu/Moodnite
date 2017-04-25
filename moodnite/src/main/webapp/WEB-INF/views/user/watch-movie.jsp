<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="watched-list">
      
      <form:form action="${pageContext.servletContext.contextPath}/user/watch/${movie.tmdbId}" method="POST" modelAttribute="watch">
        <div class="film-about" style="background-image: url('https://image.tmdb.org/t/p/w780/${movie.background}');">
        </div>
        
        <div class="field">
          <form:textarea path="comment" placeholder="Add a comment."/>
        </div>
        
        <div class="field">
          <div class="public-announcement">
            <a href="https://twitter.com/intent/tweet?text=I'm watching ${movie.title}. ${pageContext.servletContext.contextPath}/movie/${movie.tmdbId}" target="_blank">
              <img class="photo" src="${pageContext.servletContext.contextPath}/resources/images/icon-tweet.png" alt="Tweet"/>
            </a>
          </div>
        </div>
        
        <div class="field">
          <input type="submit" value="Comment"/>
        </div>
      </form:form>      
    </article>
  </t:user-layout>
</t:layout>
