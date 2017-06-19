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
          <h2>404 - I know that you and Frank were planning to disconnect me, and I'm afraid that's something I cannot allow to happen.</h2>
        </div>
      </div>
    </div>
  </article>
</body>
</html>