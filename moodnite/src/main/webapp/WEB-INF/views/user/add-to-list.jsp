<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
    
      <form id="form" method="POST" action="${pageContext.servletContext.contextPath}/user/list/${movie.tmdbId}">
        <div class="field">
          <input type="text" name="listName" placeholder="List name">
        </div>
        
        <div class="field">
          <input type="submit" value="New list"/>
        </div>
      </form>
      
      <c:choose><c:when test="${not empty lists_names}">
      <form id="form" method="POST" action="${pageContext.servletContext.contextPath}/user/list/${movie.tmdbId}">
        <select id="listName" name="listName">
          <option value="" selected="selected">- select one of your list -</option>
          <c:forEach items="${lists_names}" var="tag">
            <option value="${tag}">Add to ${tag}</option>
          </c:forEach>
        </select>
        
        <div class="field">
            <input type="submit" value="Add"/>
        </div>
      </form>
      </c:when></c:choose>
      
    </article>
  </t:user-layout>
</t:layout>
