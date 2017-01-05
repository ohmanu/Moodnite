<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<article class="home">

		<c:choose>
			<c:when test="${not empty search_results}">
				<div class="upcoming-movies"><div class="area">
					<c:forEach items="${search_results}" var="movie">
						<a href="${pageContext.servletContext.contextPath}/movie/${movie.id}"><img src="https://image.tmdb.org/t/p/w185/${movie.poster_path}" alt="${movie.title}"/></a>
					</c:forEach>
				</div></div>
			</c:when>
		</c:choose>
		
	</article>
</t:layout>