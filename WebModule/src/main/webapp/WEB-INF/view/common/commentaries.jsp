<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 08.05.15
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="commentary" class="by.leonovich.notizieportale.domain.Commentary" scope="session"/>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <%-- COMMENT-CONTENT FOR NEWS, WHAT USER WATCH NOW --%>
    <c:if test="${commentaries[0] != null}">
        <h5 class="mostpopnewsheader" style="text-align: right">Commentaries: </h5>
    </c:if>
    <c:forEach items="${commentaries}" var="commentObj">
        <hr/>
        <div align="right">
            <em>|  date: <fmt:formatDate pattern="dd-MMM-yyyy" value="${commentObj.date}"/></em><em>   |   user: ${commentObj.person.name}   |</em>
        </div>
        <p class="text">${commentObj.comment}</p>
        <c:if test="${persontype eq 'ADMIN'}">
            <table>
                <tr>
                    <th>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="editwritecommentary"/>
                            <input type="hidden" name="commentaryId" value="${commentObj.commentaryId}">
                            <button type="submit" class="btn btn-default">edit comment</button>
                        </form>
                    </th>
                    <th>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="deletecommentary"/>
                            <input type="hidden" name="commentaryId" value="${commentObj.commentaryId}">
                            <button type="submit" class="btn btn-danger">delete comment</button>
                        </form>
                    </th>
                </tr>
            </table>
        </c:if>
        <c:if test="${persontype eq 'USER'}">
            <c:if test="${person.personId == commentObj.person.personId}">
                <table>
                    <tr>
                        <th>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="editwritecommentary"/>
                                <input type="hidden" name="commentaryId" value="${commentObj.commentaryId}">
                                <button type="submit" class="btn btn-default">edit comment</button>
                            </form>
                        </th>
                        <th>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="deletecommentary"/>
                                <input type="hidden" name="commentaryId" value="${commentObj.commentaryId}">
                                <button type="submit" class="btn btn-danger">delete comment</button>
                            </form>
                        </th>
                    </tr>
                </table>
            </c:if>
        </c:if>
    </c:forEach>
    <hr/>
</div>
</body>
</html>
