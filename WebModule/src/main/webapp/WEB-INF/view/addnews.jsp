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
<jsp:useBean commentaryId="newsdata" class="by.leonovich.notizieportale.domain.News" scope="request"/>
<jsp:useBean commentaryId="dateNow" class="java.util.Date" />

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
        <c:if test="${pageId != null}">
            <h1 class="headerfirstlevel">Create new news page, please, and put "save" button.</h1>
        </c:if>
        <c:if test="${pageId eq null}">
            <h1 class="headerfirstlevel">Create new category, please, and put "save" button.</h1>
        </c:if>
        <div class="container">
            <div class="col-md-2">

            </div>
            <div class="col-md-8" align="right">
                <form name="add_news_form" method="post" action="controller">
                    <input type="hidden" name="command" value="addwritenews">

                    <c:if test="${pageId != null}">
                        <label for="pageId" class="text">Identificator of news page:</label>
                        <input size="30" commentaryId="pageId" name="pageId" value="${pageId}" readonly/></br>
                    </c:if>
                    <c:if test="${pageId eq null}">
                        <label for="pageId" class="text">Name of new CATEGORY:</label>
                        <input size="30" commentaryId="pageId" name="pageId" value="${pageId}"/></br>
                    </c:if>

                    <label for="parent_id" class="text">PARENT_ID:</label><input size="30" commentaryId="parent_id" name="parent_id" value="${parent_id}" readonly/><br>
                    <label for="title" class="text">Title:</label><input size="30" commentaryId="title" name="title" ><br>
                    <label for="menuTitle" class="text">Menu title:</label><input size="30" commentaryId="menuTitle" name="menuTitle" ><br>
                    <label for="personId" class="text">Identificator of author:</label><input size="30" commentaryId="personId" name="personId" value="${user.commentaryId}" readonly/><br>
                    <label for="commentDate" class="text">Date:</label><input size="30" commentaryId="commentDate" name="commentDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateNow}"/>"><br>
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