<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <nav class="menu">
      <ul>
        <li class="selected"><a href="${pageContext.servletContext.contextPath}/user/wall">Wall</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/reviews">Reviews</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/watched">Watched</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/lists">Lists</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/friends">Friends</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/notifications">Notifications</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/config">Config</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/logout">Logout</a></li>
      </ul>
    </nav>
      
    <article class="wall">
      <c:choose><c:when test="${not empty publications}">
        <ul class="films masonry" id="ohList">
          <li class="separator-short brick" id="pink">
            <h2>Wall</h2>
          </li>
          <c:forEach items="${publications}" var="publication">
            <li class="film brick">
              <div class="poster">
                <a href="${pageContext.servletContext.contextPath}/movie/${publication.movie.tmdbId}">
                  <img src="https://image.tmdb.org/t/p/w185/${publication.movie.background}" alt="${publication.movie.title}"/>
                </a>
              </div>
              
              <c:choose>
                <c:when test="${publication.getType() == 'RATED'}">
                  <div class="data">
                    <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${publication.movie.tmdbId}">${publication.movie.title}</a></b></h4>
                    <p>${publication.reviewXS}</p>
                    
                    <div class="stars">
                      <c:forEach var="i" begin="1" end="${publication.rate}">
                        &#9733;
                      </c:forEach>
                      <c:forEach var="i" begin="1" end="${10-publication.rate}">
                        &#9734;
                      </c:forEach>
                    </div>
                  </div>
                </c:when>
                
                <c:when test="${publication.getType() == 'WATCHED'}">
                  <div class="data">
                    <h4><b><a href="${pageContext.servletContext.contextPath}/movie/${publication.movie.tmdbId}">${publication.movie.title}</a></b></h4>
                    <p>${publication.comment}</p>
                    
                    <div class="stars">
                      <p>${publication.formattedDate}</p>
                    </div>
                  </div>
                </c:when>
                
                <c:otherwise>
              	  <p>No posts.</p>
                </c:otherwise>
              </c:choose>
              
              <div class="user">
              	<div class= "avatar">
                  <img class="user-avatar" 
                  src="${pageContext.servletContext.contextPath}/resources/images/avatars/${publication.getUser().photo}" 
                  alt="${publication.getUser().name}"/>
                </div>
                <div class="by">
                  by <a class="user-link" href="${pageContext.servletContext.contextPath}/profile/${publication.getUser().id}/reviews">${publication.getUser().name}</a>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
        <div class=sub-actions>
         <a id="loadMore">Load more</a>
        </div>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>