<!--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 18.04.15
  Time: 20:04
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="locale" %>
<jsp:useBean id="personTo" class="by.leonovich.notizieportale.domainto.PersonTO" scope="session"/>
<jsp:useBean id="personD" class="by.leonovich.notizieportale.domainto.PersonDetailTO" scope="session"/>
<html>
<head>
    <title>${person.name} cabinet</title>
</head>
<body>
<div class="row" align="center">
    <h3 class="custom-header-second-level"><locale:message code="label.welcome"/></h3>
    <hr/>
    <h4 class="custom-header-second-level">${personTo.name} ${personTo.surname}<locale:message code="label.hello"/></h4>
    <hr/>
</div>
<div class="container">
    <div class="col-md-4">
        <table>
            <caption>MENU</caption>
            <tr>
                <th  style="text-align: center">
                    <a class="btn btn-lg btn-danger" href="logout.do" role="button"><locale:message code="button.logout"/></a>
                </th>
            </tr>
            <tr >
                <th  style="text-align: center">
                    <a class="btn btn-lg btn-info" href="shownews.do" role="button"><locale:message code="button.watchnews"/></a>
                </th>
            </tr>
            <tr>
                <th style="text-align: center">
                    <a class="btn btn-lg btn-warning" href="editperson.do" role="button"><locale:message code="button.changeinfo"/></a>
                </th>
            </tr>
            <tr>
                <th  style="text-align: center">
                    <a class="btn btn-lg btn-success" href="add_news.do" role="button"><locale:message code="button.addnews"/></a>
                </th>
            </tr>
            <tr>
                <th style="text-align: center">
                    <a class="btn btn-lg btn-success" href="add_category.do" role="button"><locale:message code="button.addcategory"/></a>
                </th>
            </tr>
        </table>
    </div>
    <div class="col-md-8">
        <table>
            <caption><h4 class="custom-header-second-level" style="text-align: center"><locale:message code="label.userinfo"/></h4></caption>
            <tr>
                <td>
                    <p class="text"><locale:message code="label.name"/>: </p>
                </td>
                <td>
                    <p class="text">${personTo.name}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text"><locale:message code="label.surname"/>: </p>
                </td>
                <td>
                    <p class="text">${personTo.surname}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text"><locale:message code="label.email"/>: </p>
                </td>
                <td>
                    <p class="text">${personTo.personDetailTO.email}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text"><locale:message code="label.birthday"/>: </p>
                </td>
                <td>
                    <p class="text">
                        ${personTo.personDetailTO.birthday}
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text"><locale:message code="label.role"/>: </p>
                </td>
                <td>
                    <p class="text">${personTo.personDetailTO.role}</p>
                </td>
            </tr>
        </table>
    </div>
    <hr/>
    <hr/>
</div>

</body>
</html>
