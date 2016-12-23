<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<c:choose>
		<c:when test="${movieDetails != null}">
			<article class="film" style="background-image: url('https://image.tmdb.org/t/p/w1280/${movieDetails.backdrop_path}');">
				<div class="rate">
					<h3>${movieDetails.vote_average}</h3>
				</div>
				
				<div class="rug">
					<header class=title><h1>${movieDetails.title}</h1></header>
					
					<div class="data-sheet">
						<h2><c:forEach items="${directors}" var="director">
							<a href="${pageContext.servletContext.contextPath}/person/${director.id}">${director.name}</a>${director.coma} 
						</c:forEach></h2>
						<h3>${year}</h3>
					</div>
					
					<div class="synopsis"><h2>Synopsis</h2><p>${movieDetails.overview}</p></div>	
				</div>
			
				<c:choose>
					<c:when test="${not empty cast}">
						<div class="cast"><div class="area">
							<c:forEach items="${cast}" var="person">
								<div class="person-sheet">
									<div class="photo" style="background-image: url('https://image.tmdb.org/t/p/w185/${person.profile_path}');">
									</div>
									<div class="data">
										<h4><b><a href="${pageContext.servletContext.contextPath}/person/${person.id}">${person.name}</a></b></h4>
										<br>
										<h4>${person.character}</h4>
									</div>
								</div>
							</c:forEach>
						</div></div>
					</c:when>
				</c:choose>
			
				<c:choose>
					<c:when test="${not empty related_movies}">
						<div class="related-movies"><div class="area">
						<h2>Related movies</h2>
						<br>
						<c:forEach items="${related_movies}" var="related_movie">
							<a href="${pageContext.servletContext.contextPath}/movie/${related_movie.id}"><img src="https://image.tmdb.org/t/p/w185/${related_movie.poster_path}" alt="${related_movie.title}"/></a>
						</c:forEach>
						</div></div>
					</c:when>
				</c:choose>
			</article>
		</c:when>
	</c:choose>
</t:layout>
