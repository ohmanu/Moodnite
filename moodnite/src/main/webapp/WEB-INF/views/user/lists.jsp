<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      <c:choose><c:when test="${not empty lists_names}">
        <ul class="films">
          <li class="separator-short" id="pink">
            <h2>Lists</h2>
          </li>
          <c:forEach items="${lists_names}" var="tag">
            <li class="separator-short" id="pink">
              <h2><a href="${pageContext.servletContext.contextPath}/user/list/${tag}">${tag}</a></h2>
            </li>
          </c:forEach>
        </ul>
      </c:when></c:choose>
    </article>
  </t:user-layout>
</t:layout>
