<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="search">
    <div class="films-results">
      <c:choose><c:when test="${not empty films_results}">
        <ul class="films">
          <li class="separator" id="pink">
            <h2>Movies</h2>
          </li>
          <c:forEach items="${films_results}" var="film">
            <c:choose><c:when test="${film.poster_path != null}">
              <li class="film">
                <div class="poster">
                  <a href="${pageContext.servletContext.contextPath}/movie/${film.id}">
                    <img src="https://image.tmdb.org/t/p/w185/${film.poster_path}" alt="${film.title}"/>
                  </a>
                </div>
              </li>
            </c:when></c:choose>
          </c:forEach>
        </ul>
      </c:when>
      <c:otherwise><center><h2>Your search did not match any movies.</h2></center></c:otherwise>
      </c:choose>
    </div>
    
    <div class="people-results">
      <c:choose><c:when test="${not empty people_results}">
        <ul class="people">
          <li class="separator">
            <h2>People</h2>
          </li>
          <c:forEach items="${people_results}" var="person">
            <li class="person-sheet">
              <div class="photo" style="background-image: url('https://image.tmdb.org/t/p/w185/${person.profile_path}');">
                <a class="person-photo" href="${pageContext.servletContext.contextPath}/person/${person.id}"></a>
              </div>
              <div class="data">
                <h4><b><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${person.name}</a></b></h4>
                <br>
                <h4>${person.character}</h4>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:when>
      <c:otherwise><center><h2>Your search did not match any people.</h2></center></c:otherwise>
      </c:choose>
    </div>
    
    <div class="users-results">
      <c:choose><c:when test="${not empty users_results}">
        <ul class="people">
          <li class="separator" id="pink">
            <h2>Users</h2>
          </li>
          <c:forEach items="${users_results}" var="user">
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
      </c:when>
      <c:otherwise><center><h2>Your search did not match any users.</h2></center></c:otherwise>
      </c:choose>
    </div>
  </article>
</t:layout>