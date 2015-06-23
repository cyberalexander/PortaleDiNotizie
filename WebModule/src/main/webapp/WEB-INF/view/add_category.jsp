<!--
Created by IntelliJ IDEA.
User: Alexander
Date: 17.11.2014
Time: 20:10
To change this template use File | Settings | File Templates.
-->
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="dateNow" class="java.util.Date" scope="session"/>
<html>
<head>
</head>
<body>
<h1 class="custom-header-first-level">Create new category, please, and put "save" button.</h1>

<div class="container">
    <div class="col-md-2"> </div>
    <div class="col-md-8" align="right">
        <s:form modelAttribute="news" method="post" action="add_write_category.do">

            <label for="pageId" class="text">Name of new CATEGORY:</label>
            <s:input size="30" id="pageId" name="pageId" path="pageId"/><br/>

            <label for="title" class="text">Title:</label>
            <s:input size="30" id="title" name="title" path="title"/><br/>

            <label for="menuTitle" class="text">Menu title:</label>
            <s:input size="30" id="menuTitle" name="menuTitle" path="menuTitle"/><br/>

            <label for="date" class="text">Date:</label>
            <label for="date" class="text">Date:</label>
            <input size="30" id="date" name="date" value="${dateNow}" readonly="true"/><br/>

            <p class="text" style="text-align: right">Annotation:</p>
            <s:textarea rows="6" cols="100" name="annotation" path="annotation"/><br/>

            <p class="text" style="text-align: right">Content:</p>
            <s:textarea rows="13" cols="100" name="content" path="content"/><br/><br/>

            <button type="submit" class="btn btn-lg btn-info">SAVE</button>
        </s:form>
    </div>
    <div class="col-md-2">
    </div>
</div>
</body>
</html>