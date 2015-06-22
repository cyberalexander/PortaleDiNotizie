<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 20.06.15
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="locale" %>
<html>
<head>
    <title></title>
</head>
<body>
<footer>
    <hr/>
<div class="custom-footer" style="margin-top: 100px">
    <table align="center">
        <tr>
            <th>
                <a href="http://www.caucho.com/"><locale:message code="label.footer.home"/></a>  |
            </th>
            <th>
                <a href="http://www.caucho.com/about/contact/"><locale:message code="label.footer.contactus"/></a>  |
            </th>
            <th>
                <a href="http://www.caucho.com/resin-4.0/"><locale:message code="label.footer.documentation"/></a>  |
            </th>
            <th>
                <a href="http://blog.caucho.com/"><locale:message code="label.footer.blog"/></a>  |
            </th>
            <th>
                <a href="http://wiki.caucho.com/"><locale:message code="label.footer.wiki"/></a>  |
            </th>
            <th>
                <a href="/person_cabinet.do"><locale:message code="label.testlink.security.check"/></a>
            </th>
        </tr>

    </table>
    <div align="center" style="margin-top: 10px">
        <p style="color: #269abc">Copyright (c) 2014-2015 Alexander Leonovich Technology, Inc. All rights reserved.</p><br>
    </div>
</div>
</footer>
</body>
</html>
