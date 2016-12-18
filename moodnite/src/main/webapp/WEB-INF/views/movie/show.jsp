<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<c:choose>
		<c:when test="${movieInfo != null}">
			<article class="film" style="background-image: url('https://image.tmdb.org/t/p/w1280/${movieInfo.backdrop_path}');">
				<div class="rate">
				</div>
				
				<div class="rug">
					<header class=title><h1>${movieInfo.title}</h1></header>
					
					<div class="data-sheet">${movieInfo.release_date}</div>
					
					<div class="synopsis"><p><h2>Overview</h2></p><p>${movieInfo.overview}</p></div>	
				</div>
			</article>

			<img src="https://image.tmdb.org/t/p/w500/${movieInfo.poster_path}" alt="${movieInfo.title}"/>
				<c:choose>
					<c:when test="${not empty casts}">
						<c:forEach items="${casts}" var="casts">
							<a href="${pageContext.servletContext.contextPath}/person/${casts.id}"><img src="https://image.tmdb.org/t/p/w500/${casts.profile_path}" alt="${casts.name}"/></a>
						</c:forEach>
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
