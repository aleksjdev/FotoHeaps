<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="brandTitle" required="true" rtexprvalue="true"
              description="The title of navigation bar" %>
<%@ attribute name="brandHREF" required="true" rtexprvalue="true"
              description="The HREF of navigation bar" %>

<!-- Панель навигации, используемая на всех страницах-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${brandHREF}">${brandTitle}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">На главную</a></li>
                <li><a href="/about">О проекте</a></li>
                <li><a href="/login">Войти</a></li>
            </ul>
        </div>
    </div>
</nav>
