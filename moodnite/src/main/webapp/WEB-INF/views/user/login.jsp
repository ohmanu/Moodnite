<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="user">    
    <div class="login">
      <form:form action="${pageContext.servletContext.contextPath}/user/login" method="POST" modelAttribute="user">
        <div class="field">
          <form:input path="name" placeholder="Name" />
        </div>
        
        <div class="field">
          <form:password path="password" placeholder="Password" />
        </div>
        
        <div class="field">
          <input type="submit" value="Login"/>
        </div>
        
        <div class="field">
          <div class="public-announcement">New to Moodnite? <a href="${pageContext.servletContext.contextPath}/user/sign-in">CREATE</a> your Moodnite user.</div>
        </div>
      </form:form> 
    </div>
  </article>
</t:layout>