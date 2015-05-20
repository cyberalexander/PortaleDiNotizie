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
<jsp:useBean commentaryId="comment" class="by.leonovich.notizieportale.domain.Commentary" scope="session"/>

<html>
<head>
    <title></title>
</head>
<body>
<div>

    <%-- COMMENT-CONTENT FOR NEWS, WHAT USER WATCH NOW --%>
    <c:forEach items="${commentList}" var="commentObj">
        <hr/>
        <div align="right">
            <em>|  commentDate: ${commentObj.commentDate}</em><em>   |   user: ${commentObj.personId}   |</em>
        </div>

        <p>${commentObj.comment}</p>

        <c:if test="${usertype eq 'ADMINISTRATOR'}">
            <table>
                <tr>
                    <th>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="editwritecomment"/>
                            <input type="hidden" name="comment_id" value="${commentObj.commentaryId}">
                            <button type="submit" class="btn btn-default">edit comment</button>
                        </form>
                    </th>
                    <th>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="deletecomment"/>
                            <input type="hidden" name="comment_id" value="${commentObj.commentaryId}">
                            <button type="submit" class="btn btn-danger">delete comment</button>
                        </form>
                    </th>
                </tr>
            </table>
        </c:if>
        <c:if test="${usertype eq 'USER'}">
            <c:if test="${user.commentaryId == commentObj.personId}">
                <table>
                    <tr>
                        <th>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="editwritecomment"/>
                                <input type="hidden" name="comment_id" value="${commentObj.commentaryId}">
                                <button type="submit" class="btn btn-default">edit comment</button>
                            </form>
                        </th>
                        <th>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="deletecomment"/>
                                <input type="hidden" name="comment_id" value="${commentObj.commentaryId}">
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
