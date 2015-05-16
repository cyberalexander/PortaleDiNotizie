<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 18.04.15
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="by.leonovich.notizieportale.domain.User" scope="session"/>

<html>
<head>
    <title>user cabinet</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- My styles css file -->
    <%--<link rel="stylesheet" href="./assests/style-login.css" type="text/css"/>--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap.css"/>
    <!-- Optional theme -->
    <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap-theme.css"/>
    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="./assests/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
</head>
</head>
<body>
<div class="row" align="center">
    <h3>Welcome</h3>
    <hr/>
    <h4>${user.name} ${user.lastname}, hello!</h4>
    <hr/>
    <h5>${infoUpdated}</h5>
    <hr/>
</div>
<div class="container">
    <div class="col-md-4">
        <table>
            <caption>
                MENU
            </caption>
            <tr>
                <th>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="shownews"/>
                        <button type="submit" class="btn btn-success">Go to show news</button>
                    </form>
                </th>
            </tr>
            <tr>
                <th>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="edituser"/>
                        <button type="submit" class="btn btn-info">Change info</button>
                    </form>
                </th>
            </tr>
            <tr>
                <th>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="logout"/>
                        <button type="submit" class="btn btn-warning">log out from site</button>
                    </form>
                </th>
            </tr>
        </table>

    </div>
    <div class="col-md-8">
        <table>
            <caption>
                User info
            </caption>
            <tr>
                <td>
                    Name
                </td>
                <td>
                    ${user.name}
                </td>
            </tr>
            <tr>
                <td>
                    LastName
                </td>
                <td>
                    ${user.lastname}
                </td>
            </tr>
            <tr>
                <td>
                    Email
                </td>
                <td>
                    ${user.email}
                </td>
            </tr>
            <tr>
                <td>
                    Password
                </td>
                <td>
                    ${user.password}
                </td>
            </tr>
            <tr>
                <td>
                    Birthday
                </td>
                <td>
                    ${user.birthday}
                </td>
            </tr>
            <tr>
                <td>
                    Your role on site:
                </td>
                <td>
                    ${user.role}
                </td>
            </tr>
        </table>
    </div>

</div>

</body>
</html>
