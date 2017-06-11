<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <article class="user">
    <div class="sign-in">    
      <form:form action="${pageContext.servletContext.contextPath}/user/sign-in" method="POST" modelAttribute="user">
        <form:hidden path="id" />
        
        <div class="field">
          <p class="message">${name_message}</p>
        </div>
        
        <div class="field">
          <form:input path="name" placeholder="Name" />
        </div>
        
        <div class="field">
          <p class="message">${password_message}</p>
        </div>
        
        <div class="field">
          <form:password path="password" placeholder="Password" />
        </div>
        
        <div class="field">
          <form:textarea path="bio" placeholder="Bio" />
        </div>
        
        <div class="field">
          <input type="submit" value="SIGN IN"/>
        </div>
        
        <div class="field">
          <div class="public-announcement">Already have an account? <a href="${pageContext.servletContext.contextPath}/user/login">LOGIN</a></div>
        </div>
      </form:form>
    </div>
  </article>
</t:layout>