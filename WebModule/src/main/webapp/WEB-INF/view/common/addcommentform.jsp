<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 03.05.15
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="comment" class="by.leonovich.notizieportale.domain.Commentary" scope="session"/>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <form name="review" id="review" method="post" action="controller">
        <fieldset>
            <legend><p>Оставьте Ваш комментарий</p></legend>
            <input type="hidden" name="command" value="addwritecomment"/>
            <input type="hidden" name="news_id" value="${news.id}"/>
            <input type="hidden" name="dateOfComment" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dateNow}"/>">
            <c:if test="${commentForEdit eq null}">
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="text" maxlength="20" size="16" name="name" id="name" value="${user.name}" readonly/>
                <sup><em>*</em></sup><label for="name"><em>Name of commentary author</em></label><br>
            </c:if>
            <c:if test="${commentForEdit != null}">
                <input type="hidden" name="user_id" value="${commentForEdit.user_id}"/>
                <input type="hidden" name="comment_id" value="${commentForEdit.id}"/>
                <textarea name="commentcontent" cols="80" rows="3">${commentForEdit.comment}</textarea>
                <input type="submit" value="edit comment">
            </c:if>
            <c:if test="${commentForEdit eq null}">
                <textarea name="commentcontent" cols="80" rows="3"></textarea>
                <input type="submit" value="add comment">
            </c:if>
        </fieldset>
    </form>
</div>

</body>
</html>
