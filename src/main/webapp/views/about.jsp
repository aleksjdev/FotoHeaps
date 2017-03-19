<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <h1>FotoHeaps -  средство для удобного просмотра альбомов с fotki.yandex.ru</h1>
            <p>Если у Вас имеются пожелания и предложения, как сделать сервис лучше - напишите нам:</p>

            <form:form id="sendFeedbackForm" action="sendFeedback.do" method="POST" commandName="emailSenderContent"
                       class="form-horizontal form-border" role="form">
                <div id="submitResult"></div>

                <div class="form-group">
                    <div class="col-xs-5">
                        <label for="email">Адрес email:</label>
                        <form:input path="email" type="text" class="form-control" id="email" placeholder="Введите Ваш адрес email"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-5">
                        <label for="userName">Ваше имя:</label>
                        <form:input path="userName" type="text" class="form-control" id="userName" placeholder="Введите Ваше имя"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <label for="textContent">Ваши пожелания и отзывы:</label>
                        <form:textarea path="textContent" type="text" class="form-control" id="textContent" placeholder="Напишите Ваши пожелания и отзывы" rows="5"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Отправить</button>
            </form:form>
        </div>
    </div>
    <div class="panel-footer"/>
</div>
</body>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.11.3.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-ui.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/validationFormSubmit.js"/>'></script>
<script type="text/javascript">
    $(document).ready(function() {
        setValidationFormSubmitById('sendFeedbackForm');
    });
</script>
</html>

