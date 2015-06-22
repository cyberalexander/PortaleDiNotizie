<!--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 18.04.15
  Time: 18:03
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
      <head>
          <!-- My styles css file -->
          <link href="./assests/css/custom-style-library.css" rel="stylesheet"/>
          <!-- Latest compiled and minified CSS -->
          <link href="./assests/css/bootstrap.min.css" rel="stylesheet"/>
          <!-- Optional theme -->
          <link href="./assests/css/bootstrap-theme.css" rel="stylesheet"/>
          <!-- JQuery scripts -->
          <script src="./assests/jquery/jquery-2.1.4.js" type="text/javascript"></script>
          <!-- Latest compiled and minified JavaScript -->
          <script src="./assests/js/bootstrap.js" type="text/javascript"></script>
          <script src="./assests/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
            <title>SCUSI</title>
      </head>
      <body>
      <div class="row">
            <h2 align="center" class="custom-header-first-level">Error page</h2>
      </div>
          <div class="container">
              <div class="col-md-2">
                  <br/>
              </div>
                  <div class="col-md-8">
                      <p class="text">
                          Request from ${pageContext.errorData.requestURI} is failed <br/>
                          Servlet name or type: ${pageContext.errorData.servletName} <br/>
                          Status code: ${pageContext.errorData.statusCode} <br/>
                          Exception: ${pageContext.errorData.throwable}<br/>
                      </p>
                      <div style="align-content: center">
                          <img src="./assests/img/leopard.jpg"/>
                      </div>
                      <div style="align-content: center">
                          <img src="./assests/img/scusi.jpg"/>
                      </div>
                      <%--<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
                          <p>${trace}</p>
                      </c:forEach>--%>
                  </div>
              <div class="col-md-2">
                  <br/>
              </div>
          </div>
      </body>
</html>
