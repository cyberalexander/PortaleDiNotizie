<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 25.04.15
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <table>
        <caption><p class="custom-header-third-level">EDIT PANEL</p></caption>
        <tr>
            <c:if test="${(persontype eq 'ROLE_ADMIN')  || persontype eq 'ROLE_USER' && personTo.personId == news.person.personId}">
                <th>
                    <a class="btn btn-warning"
                       href="edit_news.do?newsId=${news.newsId}"
                       role="button">edit this news page</a>
                </th>
                <th>
                    <c:if test="${news.category.categoryId != 1}">
                        <a class="btn btn-danger"
                           href="delete_news.do?newsId=${news.newsId}&pageId=${news.category.categoryName}"
                           role="button">DELETE this news page !!!</a>
                    </c:if>
                </th>
            </c:if>
            <th>
                <a class="btn btn-success" href="add_news.do" role="button">Add new news</a>
            </th>
            <th>
                <a class="btn btn-success" href="add_category.do" role="button">Add new category</a>
            </th>
        </tr>
    </table>
</sec:authorize>
</body>
</html>
