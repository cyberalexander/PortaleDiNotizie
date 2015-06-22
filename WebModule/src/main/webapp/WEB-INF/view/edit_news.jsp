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
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="dateNow" class="java.util.Date" scope="session"/>
<html>
<head>
</head>
<body>
<div class="container">
    <div class="col-md-2"></div>
    <div class="col-md-8" align="right">
        <s:form modelAttribute="news" method="post" action="edit_write_news.do">

            <label for="newsId" class="text">ID:</label>
            <input id="newsId" name="newsId" path="newsId" readonly="true" value="${news.newsId}"/><br/>

            <label for="pageId" class="text">PAGE_ID:</label>
            <s:input size="30" id="pageId" name="pageId" path="pageId" readonly="true"/><br/>

            <label for="category" class="text">PARENT_ID:</label>
            <input size="30" id="category" name="category" value="${news.category.categoryName}" readonly/><br/>
            <input type="hidden" name="categoryId" value="${news.category.categoryId}"/><br>

            <label for="title" class="text">Title:</label>
            <s:textarea rows="2" cols="100" name="title" path="title"/><br/>

            <label for="menuTitle" class="text">Menu title:</label>
            <s:textarea rows="2" cols="100" name="menuTitle" path="menuTitle"/><br/>

            <label for="personId" class="text">email of author:</label>
            <input size="30" id="personEmail" name="personEmail" value="${news.person.personDetail.email}" readonly="true"/><br/>
            <input type="hidden" id="personId" name="personId" value="${news.person.personId}"/><br/>

            <label for="date" class="text">Date:</label>
            <input size="30" id="date" name="date" path="date" readonly="true" value="${dateNow = news.date}"/><br/>

            <p>Annotation:</p><br/>
            <s:textarea rows="6" cols="100" name="annotation" path="annotation"/><br/>

            <p>Content:</p><br/>
            <s:textarea rows="13" cols="100" name="content" path="content"/><br/>

            <button type="submit" class="btn btn-primary">SAVE</button>
        </s:form>
    </div>
    <div class="col-md-2"><%-- pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"  value="${news.date}"--%></div>
</div>
</body>
</html>
