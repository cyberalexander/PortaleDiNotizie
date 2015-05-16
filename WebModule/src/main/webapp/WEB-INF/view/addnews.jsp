<%--
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
<jsp:useBean id="dateNow" class="java.util.Date" />

<html>
    <head>
        <title>Add news page</title>
        <!-- My styles css file -->
        <link rel="stylesheet" href="./assests/style-login.css" type="text/css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap.css"/>
        <!-- Optional theme -->
        <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap-theme.css"/>
        <!-- Latest compiled and minified JavaScript -->
        <script type="text/javascript" src="./assests/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
    </head>
    <body>
        <c:if test="${page_id != null}">
            <h1 class="headerfirstlevel">Create new news page, please, and put "save" button.</h1>
        </c:if>
        <c:if test="${page_id eq null}">
            <h1 class="headerfirstlevel">Create new category, please, and put "save" button.</h1>
        </c:if>
        <div class="container">
            <div class="col-md-2">

            </div>
            <div class="col-md-8" align="right">
                <form name="add_news_form" method="post" action="controller">
                    <input type="hidden" name="command" value="addwritenews">

                    <c:if test="${page_id != null}">
                        <label for="page_id" class="text">Identificator of news page:</label>
                        <input size="30" id="page_id" name="page_id" value="${page_id}" readonly/></br>
                    </c:if>
                    <c:if test="${page_id eq null}">
                        <label for="page_id" class="text">Name of new CATEGORY:</label>
                        <input size="30" id="page_id" name="page_id" value="${page_id}"/></br>
                    </c:if>

                    <label for="parent_id" class="text">PARENT_ID:</label><input size="30" id="parent_id" name="parent_id" value="${parent_id}" readonly/><br>
                    <label for="title" class="text">Title:</label><input size="30" id="title" name="title" ><br>
                    <label for="menu_title" class="text">Menu title:</label><input size="30" id="menu_title" name="menu_title" ><br>
                    <label for="user_id" class="text">Identificator of author:</label><input size="30" id="user_id" name="user_id" value="${user.id}" readonly/><br>
                    <label for="date" class="text">Date:</label><input size="30" id="date" name="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateNow}"/>"><br>
                    <p>Annotation:</p><br>
                    <textarea rows="6" cols="100"  name="annotation"></textarea><br>
                    <p>Content:</p><br>
                    <textarea rows="13" cols="100" name="content"></textarea><br>

                    <button type="submit" class="btn btn-primary">SAVE</button>
                </form>
            </div>
            <div class="col-md-2">

            </div>
        </div>

    </body>
</html>