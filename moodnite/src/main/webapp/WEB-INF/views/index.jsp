<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<article class="home">
		<p><a href="sign-in">Sign-in</a></p>

		<c:choose><c:when test="${not empty popularMovies}">
			<div class="popular-movies">
				<ul class="area">
					<c:forEach items="${popularMovies}" var="pupularMovie">
						<li class="film">
							<div class="poster">
								<a href="${pageContext.servletContext.contextPath}/movie/${pupularMovie.id}">
									<img src="https://image.tmdb.org/t/p/w185/${pupularMovie.poster_path}" alt="${pupularMovie.title}"/>
								</a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:when></c:choose>
						
		<c:choose><c:when test="${not empty upcomingMovies}">
			<div class="upcoming-movies">
				<ul class="area">
					<c:forEach items="${upcomingMovies}" var="upcomingMovie">
						<li class="film">
							<div class="poster">
								<a href="${pageContext.servletContext.contextPath}/movie/${upcomingMovie.id}">
									<img src="https://image.tmdb.org/t/p/w185/${upcomingMovie.poster_path}" alt="${upcomingMovie.title}"/>
								</a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:when></c:choose>
		
	</article>
</t:layout>