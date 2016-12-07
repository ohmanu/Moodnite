<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<c:choose>
		<c:when test="${movie != null}">
			<h3>${movie.title}</h3>
			<p><img src="https://image.tmdb.org/t/p/w500/${movie.poster_path}" alt="${movie.title}"/></p>
				<c:choose>
					<c:when test="${not empty cast}">
						<ul>
							<c:forEach items="${cast}" var="cast">
								<li>
									<p>${cast.name}</p>
									<p><img src="https://image.tmdb.org/t/p/w500/${cast.profile_path}" alt="${cast.name}"/></p>
									
								</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<h2>No people in this movie.</h2>
					</c:otherwise>
				</c:choose>
		</c:when>
	
		<c:otherwise>
			<h2>Movie cannot be found.</h2>
		</c:otherwise>
	</c:choose>
</t:layout>
