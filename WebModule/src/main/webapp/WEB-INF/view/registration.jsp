<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 02.05.15
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- My styles css file -->
<link rel="stylesheet" href="./assests/style-login.css" type="text/css"/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap.css"/>
<!-- Optional theme -->
<link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap-theme.css"/>
<!-- Latest compiled and minified JavaScript -->
<script type="text/javascript" src="./assests/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
<html>
<head>
  <title>registration page</title>
</head>
<body>

<div class="row">
  <h1 class="headerfirstlevel">Registration form</h1>
</div>
<div class="container">
  <div class="col-md-2">

  </div>
  <div class="col-md-8" align="right">
    <form name="registration_form" method="post" action="controller">
      <input type="hidden" name="command" value="adduser">
      <p class="errormessage">${duplicateEmail} ${nullemailorpassword}</p>

        <label for="name" class="text">Name:</label>
        <input size="30" id="name" name="name" placeholder="what`s your name?"/></br>

      <label for="lastname" class="text">Lastname:</label>
      <input size="30" id="lastname" name="lastname" placeholder="what`s your lastname"/><br>

      <label for="email" class="text">Email for entry to site:</label>
      <input size="30" id="email" name="email" placeholder="your email adress"/><br>

      <label for="sentmemail" class="text">I want to receive messages to email about new developments on the site and in the world!</label>
      <INPUT type="checkbox" autocomplete="on" name="sentmemail" id="sentmemail"><br>

      <label for="password" class="text">Password:</label>
      <input size="30" id="password" name="password" type="password"/><br>

      <label for="confirm_password" class="text">Confirm passoword:</label>
      <input size="30" id="confirm_password" name="confirm_password" type="password"/><br>

      <label for="birthday" class="text">Date:</label>
      <input size="30" id="birthday" name="birthday" placeholder="YYYY-MM-DD"/><br>

      <input type="hidden" name="role" value="user"/>

      <button type="submit" class="btn btn-primary">SAVE</button>
    </form>
  </div>
  <div class="col-md-2">

  </div>
</div>
</body>
</html>
