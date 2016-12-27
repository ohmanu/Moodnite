<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<article class="person">
		<c:choose>
			<c:when test="${personInfo != null}">
				<div class="details">
					<h3>${personInfo.name}</h3>
					<p><img src="https://image.tmdb.org/t/p/w500/${personInfo.profile_path}" alt="${personInfo.name}"/></p>
					<p>${personInfo.biography}</p>
				</div>
					
				<div class="cast-credits">
					<ul class="area">
						<c:forEach items="${personCastCredits}" var="film">
							<c:choose>
								<c:when test="${film.poster_path != null}">
									<li class="film">
										<a href="${pageContext.servletContext.contextPath}/movie/${film.id}">
											<img src="https://image.tmdb.org/t/p/w185/${film.poster_path}" alt="${film.title}"/>
										</a>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
					
				<div class="crew-credits">
					<ul class="area">
						<c:forEach items="${personCrewCredits}" var="film">
							<c:choose>
								<c:when test="${film.poster_path != null}">
									<li class="film">
										<a href="${pageContext.servletContext.contextPath}/movie/${film.id}"><img src="https://image.tmdb.org/t/p/w185/${film.poster_path}" alt="${film.title}"/></a>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
			</c:when>
		</c:choose>
	</article>
</t:layout>
