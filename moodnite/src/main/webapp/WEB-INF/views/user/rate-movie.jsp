<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${1}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${2}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${3}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${4}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${5}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${6}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${7}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${8}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${9}">&#9733;</a>
      <a href="${pageContext.servletContext.contextPath}/user/rate/${movieId}/${10}">&#9733;</a>
    </article>
  </t:user-layout>
</t:layout>
