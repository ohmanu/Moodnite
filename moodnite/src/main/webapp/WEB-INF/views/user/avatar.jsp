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
      
    </article>
  </t:user-layout>
</t:layout>
