<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 18.04.15
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
      <head>
          <!-- Latest compiled and minified CSS -->
          <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap.css"/>
          <!-- Optional theme -->
          <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap-theme.css"/>
          <!-- Latest compiled and minified JavaScript -->
          <script type="text/javascript" src="./assests/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
          <title>Error page</title>
      </head>
      <body>
      <div class="row">
      <h2 align="center">Error page</h2>
      </div>
      <div class="container">
          <div class="col-md-2">

          </div>
          <div class="col-md-8">
              Request from ${pageContext.errorData.requestURI} is failed <br/>

              Servlet name or type: ${pageContext.errorData.servletName} <br/>

              Status code: ${pageContext.errorData.statusCode} <br/>

              Exception: ${pageContext.errorData.throwable}<br/>
              <%--${pageContext.exception}<br/>

              <c:forEach var="trace"
                         items="${pageContext.exception.stackTrace}">
                  <p>${trace}</p>
              </c:forEach>--%>
          </div>
          <div class="col-md-2">

          </div>
      </div>
      </body>
</html>
