<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 25.04.15
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<table>
    <caption>EDIT PANEL</caption>
    <tr>
        <th>
            <%-- BUTTON FOR EDIT NEWS-PAGE OR FOR EDIT CATEGORY. IF TALKING SIMPLE, IT`S BUTTON FOR EDIT PAGE, WHERE USER IS LOCATED NOW  --%>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="editnews"/>
                <input type="hidden" name="newsIdForEdit" value="${news.commentaryId}">
                <button type="submit" class="btn btn-info">edit ${news.title}</button>
            </form>
        </th>
        <th>
            <%-- BUTTON FOR DELETE NEWS-PAGE --%>
                <c:if test="${newsList != null}">
                    <c:if test="${news.parent_id == newsList[0].parent_id}">
                        <form name="delete_news_form" method="post" action="controller">
                            <input type="hidden" name="command" value="deletenews"/>
                            <input type="hidden" name="newsIdForDelete" value="${news.commentaryId}">
                            <button type="submit" class="btn btn-danger">DELETE ${news.title} !!!</button>
                        </form>
                    </c:if>
                </c:if>
        </th>
        <th>
            <%-- BUTTON FOR ADDING NEWS IN CATEGORY OR FOR ADDING NEW CATEGORY --%>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="addnews"/>

                <c:if test="${newsList[0] != null}">
                    <c:set var="parent_id" scope="session" value="${newsList[0].parent_id}"/>
                    <c:set var="newsList" scope="session" value="${newsList}"/>
                </c:if>
                <c:if test="${newsList[0] == null}">
                    <c:set var="parent_id" scope="session" value="${news.pageId}"/>
                </c:if>

                <c:if test="${news.pageId eq 'main'}">
                    <button type="submit"  class="btn btn-success">Add new category</button>
                </c:if>
                <c:if test="${news.parent_id eq 'main'}">
                    <button type="submit"  class="btn btn-success">Add new ${news.pageId} news</button>
                </c:if>
                <c:if test="${news.parent_id != 'main' && news.pageId != 'main'}">
                    <button type="submit"  class="btn btn-success">Add new ${news.parent_id} news</button>
                </c:if>

            </form>
        </th>
    </tr>
</table>
</body>
</html>
