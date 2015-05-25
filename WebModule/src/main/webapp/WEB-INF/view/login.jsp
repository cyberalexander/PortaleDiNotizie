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
    <title>welcome page</title>
    <c:import url="common/styles-common.jsp"/>
</head>
<body>
    <div class="container">

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
          </div>

          <div class="row">
              <div class="col-md-7">
                  <div class="image"><a href="#"><img src="./assests/img/28749.jpg" alt="Photo_1"></a></div>
                  <div class="image"><a href="#"><img src="./assests/img/28752.jpg" alt="Photo_2"></a></div>
                  <div class="image"><a href="#"><img src="./assests/img/28757.jpg" alt="Photo_3"></a></div>
              </div>
              <div class="col-md-5" style="text-align: center">

                    <form name="login_form" method="post" action="<c:url value="controller"/>">
                        <input type="hidden" name="command" value="login" />
                        <table cellpadding="5" cellspacing="20">
                            <caption><p class="text">Input your email and password, PLEASE!</p></caption>
                            <tr>
                                <td><p class="text">Login:</p></td>
                                <td><input type="text" name="email" /></td>
                            </tr>
                            <tr>
                                <td><p class="text">Password:</p></td>
                                <td><input type="password" name="password"/></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right">
                                    <button type="submit" class="btn btn-default">LOG IN :-)</button>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="2">
                                    <p class="errormessage">
                                            ${errorLoginPassMessage}
                                            ${wrongAction}
                                            ${nullPage}
                                            ${accessDenied}
                                    </p>
                                </th>
                            </tr>
                        </table>
                    </form>
                  <c:import url="common/registration_button.jsp"/>
              </div>
        </div>
    </div>
</body>
</html>