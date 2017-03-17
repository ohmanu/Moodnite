<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
      
      <form:form action="${pageContext.servletContext.contextPath}/user/review/${rateId}" method="POST" modelAttribute="rate">
        <div class="film-about" style="background-image: url('https://image.tmdb.org/t/p/w780/${backdrop_path}');">
        </div>
        
        <div class="field">
          <form:textarea path="reviewXS" placeholder="${rate.reviewXS}"/>
        </div>
        
        <div class="field">
          <input type="submit" value="Save"/>
         </div>
      </form:form>
      
    </article>
  </t:user-layout>
</t:layout>
