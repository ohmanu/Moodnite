<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <t:user-layout>
    <article class="reviews">
    
      <form:form action="${pageContext.servletContext.contextPath}/user/avatar" method="POST" enctype="multipart/form-data">		
        <p>
          <input type="file" name="file">
        </p>
        
        <input type="submit" value="Upload"/>
      </form:form>
      
      <form:form action="${pageContext.servletContext.contextPath}/user/update" method="POST" modelAttribute="user">
        <p>
          <form:label path="name">Name: </form:label>
          <form:input path="name" placeholder="${user.name}"/>
        </p>
        
        <p>
          <form:label path="bio">Bio: </form:label>
          <form:textarea path="bio" placeholder="${user.bio}" cols="50" rows="3"/>
        </p>
        
        <input type="submit" value="Update"/>
      </form:form>
      
    </article>
  </t:user-layout>
</t:layout>
