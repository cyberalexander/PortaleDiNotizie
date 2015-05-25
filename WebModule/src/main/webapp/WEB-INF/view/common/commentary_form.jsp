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
            <input type="hidden" name="command" value="addwritecommentary"/>
            <input type="hidden" name="newsId" value="${news.newsId}"/>
            <input type="hidden" name="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dateNow}"/>">

            <c:if test="${commentForEdit eq null}">
                <input type="hidden" name="personId" value="${person.personId}"/>
                <input type="text" maxlength="20" size="16" name="name" id="name" value="${person.name}" readonly/>
                <sup><em>*</em></sup><label for="name"><em>Name of commentary author</em></label><br>
                <textarea name="content" cols="80" rows="3"></textarea>
                <input type="submit" value="add comment">
            </c:if>

            <c:if test="${commentForEdit != null}">
                <input type="hidden" name="personId" value="${commentForEdit.person.personId}"/>
                <input type="hidden" name="commentaryId" value="${commentForEdit.commentaryId}"/>
                <textarea name="content" cols="80" rows="3">${commentForEdit.comment}</textarea>
                <input type="submit" value="edit comment">
            </c:if>

        </fieldset>
    </form>
</div>

</body>
</html>
