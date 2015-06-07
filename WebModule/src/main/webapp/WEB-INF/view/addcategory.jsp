w<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 17.11.2014
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>


<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="newsdata" class="by.leonovich.notizieportale.domain.News" scope="request"/>
<jsp:useBean id="categoryData" class="by.leonovich.notizieportale.domain.Category"/>
<jsp:useBean id="dateNow" class="java.util.Date" />

<html>
    <head>
        <title>Add news page</title>
        <c:import url="common/styles-common.jsp"/>
    </head>
    <body>
            <h1 class="headerfirstlevel">Create new category, please, and put "save" button.</h1>
        <div class="container">
            <div class="col-md-2">

            </div>
            <div class="col-md-8" align="right">
                <form name="add_news_form" method="post" action="controller">
                    <input type="hidden" name="command" value="addwritecategory">

                        <label for="pageId" class="text">Name of new CATEGORY:</label>
                        <input size="30" id="pageId" name="pageId" value="${pageId}"/></br>

                    <label for="category" class="text">Parent category:</label>
                    <input size="30" id="category" name="category" value="${category}" readonly/><br>

                    <label for="title" class="text">Title:</label>
                    <input size="30" id="title" name="title" ><br>

                    <label for="menuTitle" class="text">Menu title:</label>
                    <input size="30" id="menuTitle" name="menuTitle" ><br>

                    <label for="personName" class="text">Author:</label>
                    <input size="30" id="personName" name="personName" value="${person.name}"readonly/><br>
                    <input type="hidden" name="personId" value="${person.personId}"/>

                    <label for="date" class="text">Date:</label>
                    <input size="30" id="date" name="date"
                           value="${dateNow}<%--<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dateNow}"/>--%>" readonly
                           pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/><br>

                    <p class="text">Annotation:</p><br>
                    <textarea rows="6" cols="100"  name="annotation"></textarea><br>
                    <p class="text">Content:</p><br>
                    <textarea rows="13" cols="100" name="content"></textarea><br>

                    <button type="submit" class="btn btn-primary">SAVE</button>
                </form>
            </div>
            <div class="col-md-2">

            </div>
        </div>

    </body>
</html>