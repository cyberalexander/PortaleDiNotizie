<!--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 12.04.15
  Time: 21:13
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:useBean id="news" class="by.leonovich.notizieportale.domain.News" scope="session"/>
<jsp:useBean id="personTo" class="by.leonovich.notizieportale.domainto.PersonTO" scope="session"/>
<jsp:useBean id="dateNow" class="java.util.Date" scope="session"/>
<html>
<head>
</head>
<body>
<div class="container">
    <div class="col-md-2" style="text-align: left">
        <!-- BUTTTON FOR RETURN ON MAIN PAGE, OR ON HEAD PAGE OF CATEGORY -->
        <a href="shownews.do?pageId=${news.category.categoryName}">
            <c:if test="${news.category.categoryName != null}">
                <c:if test="${news.category.categoryName eq 'main'}">
                    <p>Вернуться на main page</p>
                </c:if>
                <c:if test="${news.category.categoryName ne 'main'}">
                    <p>Вернуться на главную страницу раздела</p>
                </c:if>
            </c:if>
        </a>

        <!-- LIST WITH CATEGORIES OF NEWS -->
        <ul class="nav nav-pills nav-stacked">
            <c:forEach items="${categories}" var="categoryObj">
                <li>
                    <a href="shownews.do?pageId=${categoryObj.categoryName}&amp;pageNumber=${0}">${categoryObj.categoryName}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="col-md-8">
        <!-- NEWS CONTENT -->
        <c:if test="${news.newsId == 1}">
            <p class="text">${news.content}</p>
            <p align="right">
                <em>${news.date}<%--<fmt:formatDate  dateStyle="dd-MMM-yyyy" value=""/>--%></em>
            </p>
        </c:if>

        <c:if test="${news.category.categoryName eq 'main' and news.newsId != 1}">
            <c:import url="../common/pagination.jsp"/>
            <c:forEach items="${newses}" var="newsObj">
                <p class="most-popular-news-header">
                    <a href="shownews.do?pageId=${newsObj.pageId}">${newsObj.menuTitle}</a>
                </p>
                <p class="text">${newsObj.annotation}
                    <a href="shownews.do?pageId=${newsObj.pageId}">read more...</a>
                </p>
                <p align="right">
                    <em>${newsObj.date}<%--<fmt:formatDate pattern="dd-MMM-yyyy" value=""/>--%></em>
                </p>
            </c:forEach>
            <c:import url="../common/pagination.jsp"/>
        </c:if>

        <c:if test="${news.category.categoryId != 1}">
            <p class="text">${news.content}</p>
            <p align="left"><em>${news.person.surname}</em></p>
            <p align="right"><em>${news.date}<%--<fmt:formatDate pattern="dd-MMM-yyyy" value="${news.date}"/>--%></em></p>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                <!-- IMPORT FORM FOR ADDING COMMENT FOR NEWS -->
                <c:import url="../common/commentary_form.jsp"/>
            </sec:authorize>
        </c:if>

            <!-- EDIT-PANEL FOR NEWS OR FOR PAGE, WHAT AUTORIZED ROLE_USER WATCH NOW -->
            <c:import url="../common/edit-panel.jsp"/>

        <!--COMMENT-CONTENT FOR NEWS, WHAT ROLE_USER WATCH NOW -->
        <c:import url="../common/commentaries.jsp"/>
    </div>

    <div class="col-md-2">
        <div>
            <br/>

            <h3 class="custom-header-second-level">Most popular</h3>
            <c:forEach items="${popularNewses}" var="popNews">
                <hr/>
                <a href="shownews.do?pageId=${popNews.pageId}&amp;category=${popNews.category.categoryName}">
                    <p class="most-popular-news-header">${popNews.title}</p>
                </a>
                <em class="custom-header-third-level">
                        ${popNews.annotation}
                    <a href="shownews.do?pageId=${popNews.pageId}&amp;category=${popNews.category.categoryName}">read
                        more...</a>
                </em>
            </c:forEach>
        </div>

    </div>
</div>
</body>
</html>
