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
    <form name="registration_form" method="post" action="controller">
      <input type="hidden" name="command" value="addperson">
      <p class="errormessage">${duplicateEmail} ${nullemailorpassword}</p>

      <label for="email" class="text">Email for entry to site:</label>
      <input size="30" id="email" name="email" placeholder="your email adress" type="email"/><br>

      <label for="sentmemail" class="text">I want to receive messages to email about new developments on the site and in the world!</label>
      <INPUT type="checkbox" autocomplete="on" name="sentmemail" id="sentmemail"><br>

      <label for="password" class="text">Password:</label>
      <input size="30" id="password" name="password" type="password"/><br>

      <label for="confirm_password" class="text">Confirm passoword:</label>
      <input size="30" id="confirm_password" name="confirm_password" type="password"/><br>

      <label for="birthday" class="text">Date:</label>
      <input size="30" id="birthday" name="birthday" placeholder="YYYY-MM-DD"
             pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/><br>

      <button type="submit" class="btn btn-primary">SAVE</button>
    </form>
    <form name="button_back_registration" method="post" action="controller">
      <input type="hidden" name="command" value="registerback">
      <button type="submit" class="btn btn-primary">BACK</button>
    </form>
  </div>
  <div class="col-md-3">

  </div>
</div>
</body>
</html>
