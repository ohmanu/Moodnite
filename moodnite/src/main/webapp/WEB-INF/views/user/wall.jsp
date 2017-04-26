<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="wall">
      <c:choose><c:when test="${not empty publications}">
        <ul class="films" data-masonry='{ "itemSelector": ".masonry", "columnWidth": ".film" }'>
          <li class="separator-short masonry" id="pink">
            <h2>Wall</h2>
          </li>
          <c:forEach items="${publications}" var="publication">
            <li class="film masonry">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${publication.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${publication.movie.background}" alt="${publication.movie.title}"/>
                </a>
              </div>
              
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${publication.movie.tmdbId}">${publication.movie.title}</a></b></h4>
                <p>${publication.getText1()}</p>
                
                <div class="stars">
                  ${publication.getText2()}
                </div>
              </div>
              
              <div class="user">
              	<div class= "avatar">
                  <img class="user-avatar" 
                  src="${pageContext.servletContext.contextPath}/resources/images/avatars/${publication.getUser().photo}" 
                  alt="${publication.getUser().name}"/>
                </div>
                <div class="by">
                  by <a href="${pageContext.servletContext.contextPath}/profile/${publication.getUser().id}/reviews">${publication.getUser().name}</a>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>