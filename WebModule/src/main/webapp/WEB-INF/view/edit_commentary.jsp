<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 02.05.15
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dateNow" class="java.util.Date" scope="session"/>
<html>
<head>
    <title>Edit commentary</title>
</head>
<body>

<div class="row">
    <h1 class="custom-header-first-level">Edit commentary form</h1>
</div>
<div class="container">
    <div class="col-md-3"></div>
    <div class="col-md-6" align="right">
        <form  method="post" action="edit_write_commentary.do">
            <input type="hidden" name="personId" value="${commentary.person.personId}"/>
            <input type="hidden" name="commentaryId" value="${commentary.commentaryId}"/>
            <input type="hidden" name="newsId" value="${commentary.news.newsId}"/>
            <input type="hidden" id="date" name="date" value="${dateNow}" readonly
                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/><br>
            <textarea name="content" cols="80" rows="3" id="content">${commentary.comment}</textarea>
            <button type="submit" class="btn btn-warning">edit commentary</button>
        </form>
    </div>
    <div class="col-md-3"></div>
</div>
</body>
</html>
