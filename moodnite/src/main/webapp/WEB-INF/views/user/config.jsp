<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
    
      <form:form action="${pageContext.servletContext.contextPath}/user/avatar" method="POST" enctype="multipart/form-data">	
        <div class="field">
          <input type="file" name="file">
        </div>
        
        <div class="field">
          <input type="submit" value="Upload"/>
        </div>
      </form:form>
      
      <form:form action="${pageContext.servletContext.contextPath}/user/update" method="POST" modelAttribute="user">
        <div class="field">
          <form:input path="name" placeholder="${user.name}"/>
        </div>
        
        <div class="field">
          <form:textarea path="bio" placeholder="${user.bio}" cols="50" rows="3"/>
        </div>
        
        <div class="field">
          <input type="submit" value="Update"/>
        </div>
      </form:form>
      
    </article>
  </t:user-layout>
</t:layout>
