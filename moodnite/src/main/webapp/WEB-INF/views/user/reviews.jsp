<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="reviews">
    <c:choose><c:when test="${not empty loggedInUser.name}">
      ${loggedInUser.name}
    </c:when></c:choose>
  </article>
</t:layout>
