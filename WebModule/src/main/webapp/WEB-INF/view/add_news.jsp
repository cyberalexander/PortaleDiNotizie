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
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="dateNow" class="java.util.Date"/>
<html>
<head>
</head>
<body>
<h1 class="custom-header-first-level">Create new news page, please, and push "save" button.</h1>

<div class="container">
    <div class="col-md-2"></div>
    <div class="col-md-8" align="right">
        <s:form modelAttribute="news" method="post" action="add_write_news.do">
            <label for="categoryId" class="text">Category:</label><br/>
            <select name="categoryId" id="categoryId">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select><br/><br/>

            <label for="title" class="text">Title:</label>
            <s:input size="30" id="title" name="title" path="title"/><br/>

            <label for="menuTitle" class="text">Menu title:</label>
            <s:input size="30" id="menuTitle" name="menuTitle" path="menuTitle"/><br/>

            <label for="personName" class="text">Author:</label>
            <input size="30" id="personName" name="personName" value="${person.name}" readonly/><br/>
            <input type="hidden" name="personId" value="${person.personId}"/>

            <label for="dateN" class="text">Date:</label>
            <input size="30" id="dateN" name="dateN" value="${dateNow}" readonly="true"/><br/>

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