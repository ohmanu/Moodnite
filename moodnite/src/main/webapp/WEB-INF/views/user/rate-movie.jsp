<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      <form:form>
        <div class="film-about" style="background-image: url('https://image.tmdb.org/t/p/w780/${backdrop_path}');">
        </div>
        <div class="stars">
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
        </div>
        
        <div class="field">
          <div class="public-announcement">Do you want to <a href="${pageContext.servletContext.contextPath}/user/delete/rate/${movieId}">DELETE</a> this review?</div>
        </div>
      </form:form>
    </article>
  </t:user-layout>
</t:layout>
