<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<c:choose>
		<c:when test="${movieInfo != null}">
			<h3>${movieInfo.title}</h3>
			<p><img src="https://image.tmdb.org/t/p/w500/${movieInfo.poster_path}" alt="${movieInfo.title}"/></p>
				<c:choose>
					<c:when test="${not empty casts}">
						<ul>
							<c:forEach items="${casts}" var="casts">
								<li>
									<p><a href="${pageContext.servletContext.contextPath}/person/${casts.id}">${casts.name}</a></p>
									<p><img src="https://image.tmdb.org/t/p/w500/${casts.profile_path}" alt="${casts.name}"/></p>
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
