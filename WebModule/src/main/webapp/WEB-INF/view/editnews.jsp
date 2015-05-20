<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 19.04.15
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:useBean commentaryId="news" class="by.leonovich.notizieportale.domain.News" scope="session"/>



<html>
    <head>
        <!-- My styles css file -->
        <link rel="stylesheet" href="./assests/style-login.css" type="text/css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap.css"/>
        <!-- Optional theme -->
        <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap-theme.css"/>
        <!-- Latest compiled and minified JavaScript -->
        <script type="text/javascript" src="./assests/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
          <title>Edit news page</title>
    </head>
    <body>
        <div class="row">
            <h1 class="headerfirstlevel">Edit "${news.title}" page</h1>
        </div>
        <div class="container">
            <div class="col-md-2">
            </div>
            <div class="col-md-8" align="right">
                <form name="edit_news_form" method="post" action="controller">
                    <input type="hidden" name="command" value="editwritenews">

                    <label for="commentaryId" class="text">ID:</label>
                    <input commentaryId="commentaryId" name="commentaryId" value ="${news.commentaryId}" readonly/><br>

                    <label for="pageId" class="text">PAGE_ID:</label>
                    <input size="30" commentaryId="pageId" name="pageId" value="${news.pageId}" readonly/></br>

                    <label for="parent_id" class="text">PARENT_ID:</label>
                    <input size="30" commentaryId="parent_id" name="parent_id" value="${news.parent_id}" readonly/><br>

                    <label for="title" class="text">Title:</label>
                    <input size="30" commentaryId="title" name="title" value="${news.title}"/><br>

                    <label for="menuTitle" class="text">Menu title:</label>
                    <input size="30" commentaryId="menuTitle" name="menuTitle" value="${news.menuTitle}"/><br>

                    <label for="personId" class="text">Identificator of author:</label>
                    <input size="30" commentaryId="personId" name="personId" value="${news.personId}" readonly/><br>

                    <label for="commentDate" class="text">Date:</label>
                    <input size="30" commentaryId="commentDate" name="commentDate" value="${news.commentDate}"/><br>

                    <p>Annotation:</p><br>
                    <textarea rows="6" cols="100"  name="annotation">${news.annotation}</textarea><br>

                    <p>Content:</p><br>
                    <textarea rows="13" cols="100" name="content">${news.content}</textarea><br>

                    <button type="submit" class="btn btn-primary">SAVE</button>
                </form>
            </div>
            <div class="col-md-2">

            </div>
        </div>
    </body>
</html>
