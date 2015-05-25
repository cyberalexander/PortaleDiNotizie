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

<jsp:useBean id="news" class="by.leonovich.notizieportale.domain.News" scope="session"/>
<jsp:useBean id="person" class="by.leonovich.notizieportale.domain.Person" scope="session"/>
<jsp:useBean id="dateNow" class="java.util.Date" scope="session"/>
<html>
<head>
    <c:import url="common/headtitle.jsp"/>
    <c:import url="common/styles-common.jsp"/>
</head>
<body>
<div class="container">
    <%-- HEADER FOR NEWS OR FOR PAGE, WHAT USER WATCH NOW --%>
    <c:import url="common/header.jsp"/>
    <p class="errormessage">${addNewsComErr} ${addingNewsError}${NoPageForEdit}</p>

    <div class="col-md-2" style="text-align: left">
        <%-- BODY OF NEWS-PAGE --%>

        <%-- BUTTTON FOR RETURN ON MAIN PAGE, OR ON HEAD PAGE OF CATEGORY --%>
        <a href="controller?command=shownews&pageId=${news.category.categoryName}">
            <c:if test="${news.category.categoryName != null}">
                <c:if test="${news.category.categoryName eq 'main'}">
                    <p>Вернуться на main page</p>
                </c:if>
                <c:if test="${news.category.categoryName != 'main'}">
                    <p>Вернуться на главную страницу раздела</p>
                </c:if>
            </c:if>
        </a>

        <%-- LIST WITH CATEGORIES OF NEWS --%>
        <ul class="nav nav-pills nav-stacked">
            <c:forEach items="${categories}" var="categoryObj">
                <li>
                    <a href="controller?command=shownews&pageId=${categoryObj.categoryName}">${categoryObj.categoryName}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="col-md-8">
        <!-- NEWS CONTENT -->
        <c:if test="${news.newsId == 1}">
            <p>${news.content}</p>

            <p align="right"><em>${news.date}</em></p>
        </c:if>

        <c:if test="${news.category.categoryName eq 'main' && news.newsId != 1}">
            <c:forEach items="${newses}" var="newsObj">
                <p>${newsObj.menuTitle}</p>
                <p>
                  ${newsObj.annotation}
                  <a href="controller?command=shownews&pageId=${newsObj.pageId}">read more...</a>
                </p>
                <p align="right">
                    <em>
                        <fmt:formatDate pattern="dd-MMM-yyyy" value="${newsObj.date}"/>
                    </em>
                </p>
            </c:forEach>
        </c:if>

        <c:if test="${news.category.categoryId != 1}">
            <h3>${news.menuTitle}</h3>

            <p>${news.content}</p>

            <p align="left"><em>${news.person.surname}</em></p>

            <p align="right"><em>${news.date}</em></p>
            <c:if test="${usertype eq 'ADMINISTRATOR' || usertype eq 'USER'}">
                <%-- IMPORT FORM FOR ADDING COMMENT FOR NEWS --%>
                <c:import url="common/commentary_form.jsp"/>
                <hr/>
            </c:if>
        </c:if>
        <c:if test="${usertype eq 'ADMINISTRATOR' || usertype eq 'USER'}">
            <%-- EDIT-PANEL FOR NEWS OR FOR PAGE, WHAT AUTORIZED USER WATCH NOW --%>
            <c:import url="common/edit-panel.jsp"/>
            <hr/>
        </c:if>

        <%--COMMENT-CONTENT FOR NEWS, WHAT USER WATCH NOW --%>
        <c:import url="common/commentaries.jsp"/>
    </div>

    <div class="col-md-2">
        <div>
            <br/>

            <h3 class="headersecondlevel">Most popular</h3>
            <c:forEach items="${listPopNews}" var="popNews">
                <hr/>
                <a href="controller?command=shownews&pageId=${popNews.pageId}&category=${popNews.category.categoryName}">
                    <p class="mostpopnewsheader">${popNews.title}</p>
                </a>
                <em class="mostpopnews">
                        ${popNews.annotation}
                    <a href="controller?command=shownews&pageId=${popNews.pageId}&category=${popNews.category.categoryName}">read
                        more...</a>
                </em>
            </c:forEach>
        </div>

    </div>
</div>
</div>
</body>
</html>
