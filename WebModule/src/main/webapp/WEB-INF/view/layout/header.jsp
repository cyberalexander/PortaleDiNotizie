<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 20.06.15
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="row">
    <div class="container">
    <div class="col-md-2">
        <h4 style="text-align: center; color: wheat; font-family: 'Glyphicons Halflings'">PortaleDiNotizie.by</h4>
    </div>
    <div class="col-md-8">
        <h1 class="custom-header-first-level">${news.menuTitle}</h1>
    </div>
    <div class="col-md-2">
        <%-- BUTTONS FOR LOG-OUT AND FOR GOING IN USERCABINET --%>
        <sec:authorize access="!isAuthenticated()">
            <div style="text-align: right;  margin-top: 10px">
                <a class="btn btn-success" href="addperson.do" role="button"><locale:message code="button.signup"/></a>
                <a class="btn btn-default" href="login.do" role="button"><locale:message code="button.signin"/></a>
            </div>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
            <div style="text-align: right; margin-top: 10px">
                <!-- Single button -->
                <div class="btn-group" style="text-align: right">
                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <locale:message code="button.menu"/><span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="person_cabinet.do"><locale:message code="button.goincabinet"/></a></li>
                        <li><a href="editperson.do"><locale:message code="button.changeinfo"/></a></li>
                        <li class="divider"></li>
                        <li><a href="logout.do"><locale:message code="button.logout"/></a></li>
                    </ul>
                </div>
            </div>
        </sec:authorize>
    </div>
    </div>
    <div class="row">
        <h3 align="center" class="alert-success">
            ${registrationSucces}${personInfoUpdated}${newsUpdated}${newsAdded}${languageChanged}
        </h3>
        <h3 class="alert-danger" align="center">
            ${loginFailed}
        </h3>
    </div>
    <hr/>
</div>
</body>
</html>
