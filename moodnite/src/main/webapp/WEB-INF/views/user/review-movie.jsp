<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      
      <form:form action="${pageContext.servletContext.contextPath}/user/review/${rateId}" method="POST" modelAttribute="rate">
        <p>
          <form:label path="reviewXS">Micro review: </form:label>
          <form:input path="reviewXS" placeholder="${rate.reviewXS}"/>
        </p>
        
        <input type="submit" value="Save"/>
      </form:form>
      
    </article>
  </t:user-layout>
</t:layout>
