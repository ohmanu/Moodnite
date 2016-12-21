<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<c:choose>
		<c:when test="${info != null}">
			<article class="film" style="background-image: url('https://image.tmdb.org/t/p/w1280/${info.backdrop_path}');">
				<div class="rate">
				</div>
				
				<div class="rug">
					<header class=title><h1>${info.title}</h1></header>
					
					<div class="data-sheet">
						<h2><c:forEach items="${directors}" var="director">
							<a href="${pageContext.servletContext.contextPath}/person/${director.id}">${director.name}</a>${director.coma} 
						</c:forEach></h2>
						<h3>${info.release_date}</h3>
					</div>
					
					<div class="synopsis"><h2>Synopsis</h2><p>${info.overview}</p></div>	
				</div>
			</article>

			<img src="https://image.tmdb.org/t/p/w500/${info.poster_path}" alt="${info.title}"/>
				<c:choose>
					<c:when test="${not empty cast}">
						<c:forEach items="${cast}" var="cast">
							<a href="${pageContext.servletContext.contextPath}/person/${cast.id}"><img src="https://image.tmdb.org/t/p/w500/${cast.profile_path}" alt="${cast.name}"/></a>
						</c:forEach>
					</c:when>
				</c:choose>
		</c:when>
	</c:choose>
</t:layout>
