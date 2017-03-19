<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fotoset" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>FotoHeaps</title>
    <link rel="icon" href="<c:url value='/resources/img/fav_1.ico'/>" type="image/x-icon"/>
    <link rel="shortcut icon" href="<c:url value='/resources/img/fav_1.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css"
          media="screen"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/styles.css' />">
</head>
<body>
<div class="panel panel-primary">
    <fotoset:navbar brandTitle="FotoHeaps" brandHREF="/"/>

    <div class="jumbotron">
        <div class="container">
            <h1>Попробуйте просмотреть содержимое своего аккаунта на Яндекс-фотках</h1>
            <form:form id="userLoginForm" action="showUserAccount.do" method="POST" commandName="connectionPreference"
                  class="form-horizontal form-border" role="form">
                <div class="form-group">
                    <div class="col-xs-5">
                        <label for="userName">Логин на Яндекс-фотках:</label>
                        <form:input path="userName" type="text" class="form-control" id="userName"
                               placeholder="Введите Ваш логин или email на Яндекс-фотках"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Вход</button>
            </form:form>
        </div>
    </div>

    <div class="panel-footer"/>
</div>
</body>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.11.3.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-ui.min.js"/>'></script>
</html>

