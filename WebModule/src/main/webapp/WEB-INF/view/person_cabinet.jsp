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
<jsp:useBean id="person" class="by.leonovich.notizieportale.domain.Person" scope="session"/>
<jsp:useBean id="personD" class="by.leonovich.notizieportale.domain.PersonDetail" scope="session"/>
<html>
<head>
    <title>${person.name} cabinet</title>
</head>
<body>
<div class="row" align="center">
    <h3 class="custom-header-second-level">Welcome</h3>
    <hr/>
    <h4 class="custom-header-second-level">${person.name} ${person.surname}, hello!</h4>
    <hr/>
</div>
<div class="container">
    <div class="col-md-4">
        <table>
            <caption>MENU</caption>
            <tr>
                <th>
                    <a class="btn btn-lg btn-success" href="shownews.do" role="button"><locale:message code="button.watchnews"/></a>
                </th>
            </tr>
            <tr>
                <th>
                    <a class="btn btn-lg btn-default" href="editperson.do" role="button"><locale:message code="button.changeinfo"/></a>
                </th>
            </tr>
            <tr>
                <th>
                    <a class="btn btn-lg btn-info" href="logout.do" role="button"><locale:message code="button.logout"/></a>
                </th>
            </tr>
            <tr>
                <th>
                    <a class="btn btn-lg btn-info" href="add_news.do" role="button">Add new news</a>
                </th>
            </tr>
            <tr>
                <th>
                    <a class="btn btn-lg btn-info" href="add_category.do" role="button">Add new Category</a>
                </th>
            </tr>
        </table>
    </div>
    <div class="col-md-8">
        <table>
            <caption><h4 class="custom-header-second-level" style="text-align: center">User info</h4></caption>
            <tr>
                <td>
                    <p class="text">Name: </p>
                </td>
                <td>
                    <p class="text">${person.name}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text">Surname: </p>
                </td>
                <td>
                    <p class="text">${person.surname}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text">Email: </p>
                </td>
                <td>
                    <p class="text">${person.personDetail.email}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text">Birthday: </p>
                </td>
                <td>
                    <p class="text">
                        ${person.personDetail.birthday}
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="text">Your role on site: </p>
                </td>
                <td>
                    <p class="text">${person.personDetail.role}</p>
                </td>
            </tr>
        </table>
    </div>
    <hr/>
    <hr/>
</div>

</body>
</html>
