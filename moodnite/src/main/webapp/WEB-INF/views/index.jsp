<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<h1>moodnite</h1>
	
	<p>
		<a href="sign-in">Sign-in</a>
	</p>
	
	<c:choose>
		<c:when test="${not empty popularMovies}">
			<c:forEach items="${popularMovies}" var="pupularMovie">
				<p><a href="movie/${pupularMovie.id}">${pupularMovie.title}</a></p>
				<p><img src="https://image.tmdb.org/t/p/w500/${pupularMovie.poster_path}" alt="${pupularMovie.title}"/></p>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2>No popular movies.</h2>
		</c:otherwise>
	</c:choose>
</t:layout>