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
        <div class="text">
        <table>
            <caption>
                <h4 style="text-align: center">User info</h4>
            </caption>
            <tr>
                <td>
                    <p>Name: </p>
                </td>
                <td>
                    <p>${person.name}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Surname: </p>
                </td>
                <td>
                    <p>${person.surname}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Email: </p>
                </td>
                <td>
                    <p>${person.personDetail.email}</p>
                </td>
            </tr>
            <%--<tr>
                <td>
                    Password
                </td>
                <td>
                    ${person.personDetail.password}
                </td>
            </tr>--%>
            <tr>
                <td>
                    <p>Birthday: </p>
                </td>
                <td>
                    <p>${person.personDetail.birthday}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Your role on site: </p>
                </td>
                <td>
                    <p>${person.personDetail.role}</p>
                </td>
            </tr>
        </table>
        </div>
    </div>
    <hr/>
    <hr/>
</div>

</body>
</html>
