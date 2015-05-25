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

<jsp:useBean id="news" class="by.leonovich.notizieportale.domain.News" scope="session"/>



<html>
    <head>
        <!-- JQuery scripts -->
        <script type="text/javascript" src="./assests/jquery-2.1.4.js"></script>
        <!-- My styles css file -->
        <link rel="stylesheet" href="./assests/custom-style-library.css" type="text/css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="./assests/bootstrap-3.3.4-dist/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script type="text/javascript" src="./assests/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="./assests/jquery-migrate-1.2.1.min.js"></script>
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
                    <input id="commentaryId" name="commentaryId" value ="${news.newsId}" readonly/><br>

                    <label for="pageId" class="text">PAGE_ID:</label>
                    <input size="30" id="pageId" name="pageId" value="${news.pageId}" readonly/></br>

                    <label for="category" class="text">PARENT_ID:</label>
                    <input size="30" id="category" name="category" value="${news.category.categoryName}" readonly/><br>
                    <input type="hidden" name="categoryId" value="${news.category.categoryId}" readonly/><br>

                    <label for="title" class="text">Title:</label>
                    <input size="30" id="title" name="title" value="${news.title}"/><br>

                    <label for="menuTitle" class="text">Menu title:</label>
                    <input size="30" id="menuTitle" name="menuTitle" value="${news.menuTitle}"/><br>

                    <label for="personId" class="text">Identificator of author:</label>
                    <input size="30" id="personId" name="personId" value="${news.person.personId}" readonly/><br>

                    <label for="date" class="text">Date:</label>
                    <input size="30" id="date" name="date" value="${news.date}" readonly
                           pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/><br>

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
