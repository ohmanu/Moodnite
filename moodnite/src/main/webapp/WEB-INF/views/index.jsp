<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<p>
		<a href="sign-in">Sign-in</a>
	</p>
	
	<c:choose>
		<c:when test="${not empty popularMovies}">
			<h1>Popular Movies</h1>
			<c:forEach items="${popularMovies}" var="pupularMovie">
				<p><a href="movie/${pupularMovie.id}">${pupularMovie.title}</a></p>
				<p><img src="https://image.tmdb.org/t/p/w342/${pupularMovie.poster_path}" alt="${pupularMovie.title}"/></p>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2>No popular movies.</h2>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${not empty upcomingMovies}">
			<h1>Upcoming Movies</h1>
			<c:forEach items="${upcomingMovies}" var="upcomingMovie">
				<p><a href="movie/${upcomingMovie.id}">${upcomingMovie.title}</a></p>
				<p><img src="https://image.tmdb.org/t/p/w342/${upcomingMovie.poster_path}" alt="${upcomingMovie.title}"/></p>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2>No upcoming movies.</h2>
		</c:otherwise>
	</c:choose>
</t:layout>