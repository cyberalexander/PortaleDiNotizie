<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 20.06.15
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="locale" %>
<html>
<head>
    <!-- My styles css file -->
    <link href="../assests/css/custom-style-library.css" rel="stylesheet"/>
    <!-- Latest compiled and minified CSS -->
    <link href="../assests/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- Optional theme -->
    <link href="../assests/css/bootstrap-theme.css" rel="stylesheet"/>
    <!-- JQuery scripts -->
    <script src="../assests/jquery/jquery-2.1.4.js" type="text/javascript"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="../assests/js/bootstrap.js" type="text/javascript"></script>
    <script src="../assests/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <title><t:insertAttribute name="title"/></title>
</head>
<body>
<div style="float: right;">
    <a href="?locale=ru" style="padding: 5px">RU</a>
    <a href="?locale=en" style="padding: 5px">EN</a>
</div>
<div id="header" class="row">
    <t:insertAttribute name="header"/>
</div>
<div id="content">
    <t:insertAttribute name="body"/>
</div>
<div id="footer" class="row">
    <t:insertAttribute name="footer"/>
</div>
</body>
</html>
