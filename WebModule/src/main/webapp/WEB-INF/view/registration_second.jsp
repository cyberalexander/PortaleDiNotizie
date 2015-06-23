<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 02.05.15
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>registration page</title>
</head>
<body>

<div class="row">
    <h1 class="custom-header-first-level">Registration form</h1>
</div>
<div class="container">
    <div class="col-md-3">

    </div>
    <div class="col-md-6" align="right">
        <s:form modelAttribute="personDetailTO"  method="post" action="addwriteperson.do">

            <label for="email" class="text">Email for entry to site:</label>
            <s:input size="30" id="email" placeholder="your email adress" path="email"/><br>

            <label for="sentmemail" class="text">I want to receive messages to email about new developments on the site
                and in the world!</label>
            <input type="checkbox" autocomplete="on" name="sentmemail" id="sentmemail"><br>

            <label for="password" class="text">Password:</label>
            <s:input size="30" id="password" type="password" path="password"/><br>

            <label for="confirm_password" class="text">Confirm passoword:</label>
            <input size="30" id="confirm_password" name="confirm_password" type="password"/><br>

            <label for="birthday" class="text">Date:</label>
            <s:input size="30" id="birthday" placeholder="YYYY-MM-DD"
                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" path="birthday"/><br>
            <button type="submit" class="btn btn-primary">SAVE</button>
        </s:form>
            <a class="btn btn-info" href="/back_first_step.do" role="button">Back</a>
    </div>
    <div class="col-md-3">

    </div>
</div>
</body>
</html>
