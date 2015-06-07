<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 02.05.15
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>registration page</title>
  <c:import url="common/styles-common.jsp"/>
</head>
<body>

<div class="row">
  <h1 class="headerfirstlevel">Registration form</h1>
</div>
<div class="container">
  <div class="col-md-3">

  </div>
  <div class="col-md-6" align="right">
    <form name="registration_form_one" method="post" action="controller">
      <input type="hidden" name="command" value="addpersonfirststep">
      <p class="errormessage">${duplicateEmail} ${nullemailorpassword}</p>

        <label for="name" class="text">Name:</label>
        <input size="30" id="name" name="name" placeholder="what`s your name?"/></br>

      <label for="surname" class="text">Surname:</label>
      <input size="30" id="surname" name="surname" placeholder="what`s your surname"/><br>

      <button type="submit" class="btn btn-primary">CONTINUE</button>
    </form>

  </div>
  <div class="col-md-3">

  </div>
</div>
</body>
</html>
