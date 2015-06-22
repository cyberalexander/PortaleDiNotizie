<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <table>
        <caption><p class="custom-header-third-level">EDIT PANEL</p></caption>
        <tr>
            <c:if test="${(persontype eq 'ROLE_ADMIN')  || persontype eq 'ROLE_USER' && person.personId == news.person.personId}">
                <th>
                        <%-- BUTTON FOR EDIT NEWS-PAGE OR FOR EDIT CATEGORY. IF TALKING SIMPLE, IT`S BUTTON FOR EDIT PAGE, WHERE ROLE_USER IS LOCATED NOW  --%>
                    <form method="post" action="edit_news.do">
                        <input type="hidden" name="newsId" value="${news.newsId}">
                            <%--<input type="hidden" name="newses" value="${newses}"/>--%>
                        <button type="submit" class="btn btn-info">edit this news page</button>
                    </form>
                </th>
                <th>
                        <%-- BUTTON FOR DELETE NEWS-PAGE --%>
                    <c:if test="${news.category.categoryId != 1}">
                        <form name="delete_news_form" method="post" action="controller">
                            <input type="hidden" name="command" value="deletenews"/>
                            <input type="hidden" name="newsIdForDelete" value="${news.newsId}">
                            <input type="hidden" name="newses" value="${newses}"/>
                            <button type="submit" class="btn btn-danger">DELETE this news page !!!</button>
                        </form>
                    </c:if>
                </th>
            </c:if>
            <th>
                    <%-- BUTTON FOR ADDING NEWS --%>
                <a class="btn btn-info" href="add_news.do" role="button">Add new news</a>
            </th>
            <th>
                    <%-- BUTTON FOR ADDING NEW CATEGORY --%>
                <a class="btn btn-warning" href="add_category.do" role="button">Add new category</a>
            </th>
        </tr>
    </table>
</sec:authorize>
</body>
</html>
