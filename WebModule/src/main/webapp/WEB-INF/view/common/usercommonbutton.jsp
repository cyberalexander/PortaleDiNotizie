<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 06.05.15
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script src="./assests/jquery-1.11.3.js"></script>
</head>
<body>
<div>
  <div align="right">
    <!-- Single button -->
    <div class="btn-group">
      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        ${user.name} menu <span class="caret"></span>
      </button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="controller?command=goincabinet">Go in my cabinet</a></li>
        <li><a href="controller?command=edituser">Change info about me</a></li>
        <li class="divider"></li>
        <li><a href="controller?command=logout">log out</a></li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
