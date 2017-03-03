<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="user">
    <div class="sign-in">
      <h2>Sign in</h2>
    
      <form:form action="${pageContext.servletContext.contextPath}/user/sign-in" method="POST" modelAttribute="user">	
        <form:hidden path="id" />
          <p>
            <form:label path="name">Name: </form:label>
            <form:input path="name" />
          </p>
          
          <p>
            <form:label path="password">Password: </form:label>
            <form:password path="password" />
          </p>
          
          <input type="submit" value="Save"/>
        </form:form>
      </div>
    </article>
</t:layout>