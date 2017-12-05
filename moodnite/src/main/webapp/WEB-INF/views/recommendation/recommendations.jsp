<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>  
    <article class="recommendations">
      <c:choose><c:when test="${not empty the_chosen_one}">
      
      <ul class="films plus">
        
          <li class="the-chosen-one">
            <div class="poster">
              <a href="${pageContext.servletContext.contextPath}/movie/${the_chosen_one.movie.tmdbId}">
                <img class="avatar" src="https://image.tmdb.org/t/p/w300/${the_chosen_one.movie.background}" alt="${the_chosen_one.movie.title}"/>
              </a>
            </div>
            
            <div class="data">
              <div class="top">
                <h2><a href="${pageContext.servletContext.contextPath}/movie/${the_chosen_one.movie.tmdbId}">${the_chosen_one.movie.title}</a></h2>
                <c:choose><c:when test="${the_chosen_one.lustrumThisYear}">
              	  <p class="year-bonus">Turns ${the_chosen_one.yearsElapsed} this year.</p>
                </c:when></c:choose>
                <p class="extra-score">EXTRA SCORE: ${the_chosen_one.score}</p>
              </div>
              <div class="actions">
                <a class="refuse" href="${pageContext.servletContext.contextPath}/recommendation/refuse/${the_chosen_one.movie.tmdbId}">Refuse</a>
                <a class="watch" href="${pageContext.servletContext.contextPath}/recommendation/watch/${the_chosen_one.movie.tmdbId}">Watch</a>
              </div>
            </div>
          </li>
          
          <c:choose><c:when test="${not empty recommendation_summary.userFavoriteTags}">
          <li class="tag-cloud" >
          	<div id="cloud" style="width: 100%; height: 100%;">
          	</div>
          </li>
          </c:when></c:choose>
        </ul>
        
        <c:choose><c:when test="${not empty recommendation_summary.candidates}">
        <ul class="films masonry"> 
          <li class="separator-short brick" id="pink">
            <h2>Candidates</h2>
          </li>
        
        <c:forEach items="${recommendation_summary.candidates}" var="candidate">
          <li class="film brick">
            <div class="poster">
              <a href="${pageContext.servletContext.contextPath}/movie/${candidate.movie.tmdbId}">
                <img src="https://image.tmdb.org/t/p/w185/${candidate.movie.background}" alt="${candidate.movie.title}"/>
              </a>
            </div>
            <div class="data">
              <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${candidate.movie.tmdbId}">${candidate.movie.title}</a></b></h4>
              <p>EXTRA SCORE: ${candidate.score}</p>
            </div>
          </li>
        </c:forEach>
      </ul>
      </c:when></c:choose>
      
      <script type="text/javascript">
        var tags = [
        <c:forEach items="${recommendation_summary.userFavoriteTags}" var="tagValue">
          {text: "${tagValue.key}", weight: ${tagValue.value}},
        </c:forEach>
        ];
        $(function() {
            $("#cloud").jQCloud(tags);
          });
      </script>
      
      </c:when>
      <c:otherwise>
        <center><h2>Sorry. Moodnite needs to know more about you.</h2></center><br>
        <center><h2>TIP: Rate movies and follow interesting people.</h2></center><br>
        
        <div class="users-results">
          <c:choose><c:when test="${not empty people_you_should_follow}">
            <ul class="people">
              <li class="separator" id="pink">
                <h2>PEOPLE YOU SHOULD FOLLOW</h2>
              </li>
              <c:forEach items="${people_you_should_follow}" var="user">
              <li class="person-sheet">
                <div class="user-photo" style="background-image: url('${pageContext.servletContext.contextPath}/resources/images/avatars/${user.photo}');">
                  <a class="person-photo" href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews"></a>
                </div>
                <div class="data">
                  <h4><b><a href="${pageContext.servletContext.contextPath}/profile/${user.id}/reviews">${user.name}</a></b></h4> 
                  <a class="add" href="${pageContext.servletContext.contextPath}/user/follow/${user.id}" title="Follow">&#10010;</a>
                  <br>
                  <h4>${user.bio} </h4>
                </div>
              </li>
              </c:forEach>
            </ul>
          </c:when></c:choose>
        </div>
      </c:otherwise>
      </c:choose>
    </article>
</t:layout>