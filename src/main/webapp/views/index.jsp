<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="panel-body">
        <fotoset:imageAlbumsInTable albums="${albums}" rowCount="4" displaySize="SMALL" isOnlyRootAlbums="true" accountUserName="${accountUserName}"/>
    </div>
    <div class="panel-footer"/>
</div>
</body>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.11.3.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-ui.min.js"/>'></script>
</html>

