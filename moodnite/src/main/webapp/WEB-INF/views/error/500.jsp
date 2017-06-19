<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css">
  <title>Moodnite</title>
</head>
<body>
  <article class="error">
    <div class="container">
      <div class="box">
        <div class="top">
          <a href="${pageContext.servletContext.contextPath}/home"></a>
        </div>
        <div class="error-message">
          <h2>500 - I'm sorry, Dave. I'm afraid I can't do that.</h2>
        </div>
      </div>
    </div>
  </article>
</body>
</html>