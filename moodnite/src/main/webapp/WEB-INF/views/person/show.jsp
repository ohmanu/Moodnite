<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="person">
    <c:choose><c:when test="${personInfo != null}">
      <div class="details">
        <div class="photo" style="background-image: url('https://image.tmdb.org/t/p/w342/${personInfo.profile_path}');">
        </div>
        <div class="info">
          <h1>${personInfo.name}</h1>
          <h3>${personInfo.place_of_birth}</h3>
          <h4>${personInfo.birthday}</h4>
          <c:choose><c:when test="${personInfo.deathday != ''}">
            <h4> - ${personInfo.deathday}</h4>
          </c:when></c:choose>
          <p>${personInfo.biography}</p>
        </div>
      </div>
      
      <c:choose><c:when test="${not empty personCastCredits}">
        <div class="cast-credits">
          <ul class="films">
            <li class="separator-long" id="pink">
              <h2>Credits</h2>
            </li>
            <c:forEach items="${personCastCredits}" var="film">
              <c:choose><c:when test="${film.poster_path != null}">
                <li class="film">
                  <div class="poster">
                    <a href="${pageContext.servletContext.contextPath}/movie/${film.id}">
                      <img src="https://image.tmdb.org/t/p/w185/${film.poster_path}" alt="${film.title}"/>
                    </a>
                  </div>
                  <div class="character">${film.character}</div>
                </li>
              </c:when></c:choose>
            </c:forEach>
          </ul>
        </div>
      </c:when></c:choose>
      
      <c:choose><c:when test="${not empty personCrewCredits}">
        <div class="crew-credits">
          <ul class="films">
            <li class="separator-long">
              <h2>Crew</h2>
            </li>
            <c:forEach items="${personCrewCredits}" var="film">
              <c:choose><c:when test="${film.poster_path != null}">
                <li class="film">
                  <div class="poster">
                    <a href="${pageContext.servletContext.contextPath}/movie/${film.id}">
                      <img src="https://image.tmdb.org/t/p/w185/${film.poster_path}" alt="${film.title}"/>
                    </a>
                  </div>
                  <div class="job">${film.job}</div>
                </li>
              </c:when></c:choose>
            </c:forEach>
          </ul>
        </div>
      </c:when></c:choose>
      
    </c:when></c:choose>
  </article>
</t:layout>
