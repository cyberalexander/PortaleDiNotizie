<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 12.04.15
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean commentaryId="news" class="by.leonovich.notizieportale.domain.News" scope="session"/>
<jsp:useBean commentaryId="user" class="by.leonovich.notizieportale.domain.Person" scope="session"/>
<jsp:useBean commentaryId="dateNow" class="java.util.Date" scope="session"/>
<html>
<head>
    <script type="text/javascript" src="./assests/jquery-2.1.4.js"></script>
    <!-- My styles css file -->
    <link rel="stylesheet" href="./assests/style-login.css" type="text/css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <c:import url="common/headtitle.jsp"/>
</head>
<body>
<div class="container">
    <%-- HEADER FOR NEWS OR FOR PAGE, WHAT USER WATCH NOW --%>
    <c:import url="common/header.jsp"/>
    <p class="errormessage">${addNewsComErr} ${addingNewsError}${NoPageForEdit}</p>

    <div class="col-md-2" style="text-align: left">
        <%-- BODY OF NEWS-PAGE --%>

        <%-- BUTTTON FOR RETURN ON MAIN PAGE, OR ON HEAD PAGE OF CATEGORY --%>
        <a href="controller?command=shownews&pageId=${news.parent_id}">
            <c:if test="${news.parent_id != null}">
                <c:if test="${news.parent_id eq 'main'}">
                    <p>Вернуться на main page</p>
                </c:if>
                <c:if test="${news.parent_id != 'main'}">
                    <p>Вернуться на главную страницу раздела</p>
                </c:if>
            </c:if>
        </a>

        <%-- LIST WITH CATEGORIES OR WITH PAGES OF ONE CATEGORY --%>
        <ul class="nav nav-pills nav-stacked">
            <c:forEach items="${newsList}" var="newsObj">
                <li>
                    <c:if test="${news.pageId eq 'main'}">
                        <a href="controller?command=shownews&pageId=${newsObj.pageId}&parent_id=${newsObj.parent_id}">${newsObj.pageId}</a>
                    </c:if>
                    <c:if test="${news.pageId != 'main'}">
                        <a href="controller?command=shownews&pageId=${newsObj.pageId}&parent_id=${newsObj.parent_id}">${newsObj.title}</a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="col-md-8">
        <p>${news.content}</p>
        <p align="right"><em>${news.commentDate}</em></p>

        <c:if test="${usertype eq 'ADMINISTRATOR' || usertype eq 'USER'}">
            <hr/>
            <%-- EDIT-PANEL FOR NEWS OR FOR PAGE, WHAT AUTORIZED USER WATCH NOW --%>
            <c:import url="common/edit-panel.jsp"/>
            <hr/>
            <%-- IMPORT FORM FOR ADDING COMMENT FOR NEWS --%>
            <c:import url="common/addcommentform.jsp"/>
        </c:if>

         <%--COMMENT-CONTENT FOR NEWS, WHAT USER WATCH NOW --%>
        <c:import url="common/commentary_for_news.jsp"/>
    </div>

        <div class="col-md-2">

            <c:if test="${usertype eq 'ADMINISTRATOR' || usertype eq 'USER'}">
                <%-- BUTTONS FOR LOG-OUT AND FOR GOING IN USERCABINET --%>
                <c:import url="common/usercommonbutton.jsp"/>
                <br/>
            </c:if>
            <c:if test="${usertype eq 'GUEST'}">
                <c:import url="common/registration_button.jsp"/>
            </c:if>

            <div>
                <br/>
                <h3 class="headersecondlevel">Most popular</h3>
                <c:forEach items="${listPopNews}" var="popNews">
                    <hr/>
                        <a href="controller?command=shownews&pageId=${popNews.pageId}&parent_id=${popNews.parent_id}">
                            <p class="mostpopnewsheader">${popNews.title}</p>
                        </a>
                        <em class="mostpopnews">
                            ${popNews.annotation}
                            <a href="controller?command=shownews&pageId=${popNews.pageId}&parent_id=${popNews.parent_id}">read more...</a>
                        </em>
                </c:forEach>
                <hr/>
            </div>

        </div>
    </div>
</div>
</body>
</html>
