<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<c:choose>
		<c:when test="${personInfo != null}">
			<h3>${personInfo.name}</h3>
			<p><img src="https://image.tmdb.org/t/p/w500/${personInfo.profile_path}" alt="${personInfo.name}"/></p>
			<p>${personInfo.biography}</p>
		</c:when>
	
		<c:otherwise>
			<h2>Person cannot be found.</h2>
		</c:otherwise>
	</c:choose>
</t:layout>
