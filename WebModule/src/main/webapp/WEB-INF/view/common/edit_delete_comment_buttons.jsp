<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 10.05.15
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
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
</div>

</body>
</html>
