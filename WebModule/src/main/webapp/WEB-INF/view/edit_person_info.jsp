<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 10.05.15
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="personTo" class="by.leonovich.notizieportale.domainto.PersonTO" scope="session"/>
<html>
<head>
  <title>Edit info about ${personTo.name}</title>
</head>
<body>
<div class="row">
  <h1 class="custom-header-first-level">Edit info about ${personTo.name}</h1>
</div>
<div class="container">
  <div class="col-md-2"></div>
  <div class="col-md-8" align="right">
    <form name="registration_form" method="post" action="editwriteperson.do">
      <label for="name" class="text">Name:</label>
      <input size="30" id="name" name="name" value="${personTo.name}"/></br>

      <label for="surname" class="text">Lastname:</label>
      <input size="30" id="surname" name="surname" value="${personTo.surname}" required/><br>

      <label for="email" class="text">Old email for entry to site:</label>
      <input size="30" id="email" name="email" value="${personTo.personDetailTO.email}"/><br>

      <label for="new_email" class="text">NEW email for entry to site:</label>
      <input size="30" id="new_email" name="new_email" placeholder="write here your new email adress"/><br>

      <label for="sentmemail" class="text">I want to receive messages to email about new developments on the site and in the world!</label>
      <INPUT type="checkbox" autocomplete="on" name="sentmemail" id="sentmemail"><br>

      <label for="password" class="text">Old password:</label>
      <input size="30" id="password" name="password" type="password" value="${personTo.personDetailTO.password}"/><br>

      <label for="new_password" class="text">NEW passoword:</label>
      <input size="30" id="new_password" name="new_password" type="password"/><br>

      <label for="confirm_password" class="text">Confirm new passoword:</label>
      <input size="30" id="confirm_password" name="confirm_password" type="password"/><br>

      <label for="birthday" class="text">Date:</label>
      <input size="30" id="birthday" name="birthday"
             pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
             value="${personTo.personDetailTO.birthday}"/><br>

      <button type="submit" class="btn btn-primary">UPDATE</button>
    </form>
  </div>
  <div class="col-md-2">

  </div>
</div>
</body>
</html>
