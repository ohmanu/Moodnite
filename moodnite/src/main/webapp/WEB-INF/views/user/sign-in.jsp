<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<h2>Person Form</h2>
	
	<form:form action="save" method="POST" modelAttribute="user">		
			<form:hidden path="id" />
		<p>
			<form:label path="login">Login: </form:label>
			<form:input path="login" />
		</p>
		
		<p>
			<form:label path="password">Password: </form:label>
			<form:password path="password" />
		</p>
		
		<input type="submit" value="Save"/>
	</form:form>
</t:layout>