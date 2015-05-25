<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 08.05.15
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div>
  <form method="post" action="<c:url value="controller"/>">
    <input type="hidden" name="command" value="registration"/>
    <button type="submit" class="btn btn-success">Registration</button>
  </form>
</div>

</body>
</html>
