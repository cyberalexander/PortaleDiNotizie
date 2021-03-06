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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
</head>
<body>
<div style="text-align: left">
    <c:if test="${commentaryForEdit != null}">
        <form  method="post" action="edit_write_commentary.do">
            <input type="hidden" name="pageId" value="${news.pageId}"/>
            <button type="submit" class="btn btn-sm btn-warning">edit commentary</button>
            <input type="text" maxlength="20" size="16" name="name" id="namee" value="<sec:authentication property="principal.username"/>" readonly/>
            <sup><em>*</em></sup><label for="namee"><em>Login of commentary author</em><em></em></label><br>
            <textarea name="content" cols="90" rows="3" id="content">${commentaryForEdit.comment}</textarea>
        </form>
    </c:if>
    <c:if test="${commentaryForEdit == null}">
        <form method="post" action="add_write_commentary.do">
            <fieldset>
                <legend><p class="most-popular-news-header" style="text-align: right">Оставьте Ваш комментарий</p></legend>
                <input type="hidden" name="pageId" value="${news.pageId}"/>
                <input type="hidden" name="newsId" value="${news.newsId}"/>
                <input type="hidden" name="date" value="${dateNow}" readonly
                       pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/><br>
                <button type="submit" class="btn btn-sm btn-success">add commentary</button>
                <input type="text" maxlength="20" size="16" name="name" id="name" value="<sec:authentication property="principal.username"/>" readonly/>
                <sup><em>*</em></sup><label for="name"><em>Login of commentary author</em></label><br>
                <textarea name="content" cols="90" rows="3"></textarea>
            </fieldset>
        </form>
    </c:if>
    <hr/>
</div>

</body>
</html>
