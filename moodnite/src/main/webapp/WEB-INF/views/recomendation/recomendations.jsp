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
    
    <article class="recomendations">
      <ul class="films">        
          <li class="the-chosen-one">
            <div class="poster">
              <a href="${pageContext.servletContext.contextPath}/movie/${the_chosen_one.movie.tmdbId}">
                <img src="https://image.tmdb.org/t/p/w300/${the_chosen_one.movie.background}" alt="${the_chosen_one.movie.title}"/>
              </a>
            </div>
            <div class="data">
              <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${the_chosen_one.movie.tmdbId}">${the_chosen_one.movie.title}</a></b></h4>
              <c:choose><c:when test="${the_chosen_one.lustrumThisYear}">
              	<p>Turns ${the_chosen_one.yearsElapsed} this year.</p>
              </c:when></c:choose>
              <h2>SCORE: ${the_chosen_one.score}</h2>
              <h4><b><a href="${pageContext.servletContext.contextPath}/recomendation/refuse/${the_chosen_one.movie.tmdbId}">Refuse</a></b></h4>
            </div>
          </li>
        </ul>
          
        <ul class="films masonry"> 
          <li class="separator-short brick" id="pink">
            <h2>Candidates</h2>
          </li>
        
        <c:forEach items="${recomendation_summary.candidates}" var="candidate">
          <li class="film brick">
            <div class="poster">
              <a href="${pageContext.servletContext.contextPath}/movie/${candidate.movie.tmdbId}">
                <img src="https://image.tmdb.org/t/p/w185/${candidate.movie.background}" alt="${candidate.movie.title}"/>
              </a>
            </div>
            <div class="data">
              <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${candidate.movie.tmdbId}">${candidate.movie.title}</a></b></h4>
              <h2>SCORE: ${candidate.score}</h2>
            </div>
          </li>
        </c:forEach>
      </ul>    
    </article>
  </t:user-layout>
</t:layout>