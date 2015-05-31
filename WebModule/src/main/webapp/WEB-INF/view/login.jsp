<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 15.04.15
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WELCOME PAGE</title>
    <c:import url="common/styles-common.jsp"/>
</head>
<body>
<div class="container" align="center">

    <div class="row">
        <div class="col-md-12">
            <h1 class="headerfirstlevel">-= Hello! You are on News PORTAL! =-</h1>

            <h2 class="watchnewsbutton">
                <form method="post" action="<c:url value="controller"/>">
                    <input type="hidden" name="command" value="shownews"/>
                    <button type="submit" class="btn btn-info"><| WATCH NEWS |></button>
                </form>
            </h2>
        </div>
        <p class="errormessage">
            ${errorLoginPassMessage}${wrongAction}${nullPage}${accessDenied}
        </p>
    </div>
    <div class="row">
        <form class="form-inline" name="login_form" method="post" action="<c:url value="controller"/>">
            <input type="hidden" name="command" value="login" />
            <div class="form-group">
                <label class="sr-only" for="exampleInputEmail3">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail3" placeholder="Enter email" name="email">
            </div>
            <div class="form-group">
                <label class="sr-only" for="exampleInputPassword3">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password" name="password">
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox"><em> Remember me</em>
                </label>
            </div>
            <button type="submit" class="btn btn-default">Sign in</button>
        </form>
        <c:import url="common/registration_button.jsp"/>
    </div>
    <%--<div class="row">
        <div class="col-md-7">
            <div class="image"><a href="#"><img src="./assests/img/28749.jpg" alt="Photo_1"></a></div>
            <div class="image"><a href="#"><img src="./assests/img/28752.jpg" alt="Photo_2"></a></div>
        </div>
    </div>--%>
</div>
</body>
</html>