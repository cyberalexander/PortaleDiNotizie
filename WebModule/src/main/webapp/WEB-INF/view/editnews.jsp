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

                    <label for="id" class="text">ID:</label>
                    <input id="id" name="id" value ="${news.id}" readonly/><br>

                    <label for="page_id" class="text">PAGE_ID:</label>
                    <input size="30" id="page_id" name="page_id" value="${news.page_id}" readonly/></br>

                    <label for="parent_id" class="text">PARENT_ID:</label>
                    <input size="30" id="parent_id" name="parent_id" value="${news.parent_id}" readonly/><br>

                    <label for="title" class="text">Title:</label>
                    <input size="30" id="title" name="title" value="${news.title}"/><br>

                    <label for="menu_title" class="text">Menu title:</label>
                    <input size="30" id="menu_title" name="menu_title" value="${news.menu_title}"/><br>

                    <label for="user_id" class="text">Identificator of author:</label>
                    <input size="30" id="user_id" name="user_id" value="${news.user_id}" readonly/><br>

                    <label for="date" class="text">Date:</label>
                    <input size="30" id="date" name="date" value="${news.date}"/><br>

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
