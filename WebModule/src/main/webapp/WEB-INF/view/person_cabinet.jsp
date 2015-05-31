<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 18.04.15
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:useBean id="person" class="by.leonovich.notizieportale.domain.Person" scope="session"/>
<jsp:useBean id="personD" class="by.leonovich.notizieportale.domain.PersonDetail" scope="session"/>
<html>
<head>
    <title>${person.name} cabinet</title>
    <c:import url="common/styles-common.jsp"/>
</head>
</head>
<body>
<div class="row" align="center">
    <h3 class="headersecondlevel">Welcome</h3>
    <hr/>
    <h4 class="headersecondlevel">${person.name} ${person.surname}, hello!</h4>
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
                        <input type="hidden" name="command" value="editperson"/>
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
                <h4 class="headersecondlevel" style="text-align: center">User info</h4>
            </caption>
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
