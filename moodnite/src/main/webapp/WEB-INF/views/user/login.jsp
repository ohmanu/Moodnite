<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="user">    
    <div class="login">
      <h2>Login</h2>
      <form:form action="${pageContext.servletContext.contextPath}/user/login" method="POST" modelAttribute="user">	
        <p>
          <form:label path="name">Name: </form:label>
          <form:input path="name" />
        </p>
        
        <p>
          <form:label path="password">Password: </form:label>
          <form:password path="password" />
        </p>
        
        <input type="submit" value="Login"/>
      </form:form>
        
      <a href="${pageContext.servletContext.contextPath}/user/logout">logout</a>
    </div>
  </article>
</t:layout>