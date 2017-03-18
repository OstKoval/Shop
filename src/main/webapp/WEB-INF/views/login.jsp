<!-- <%@ page language="java" contentType="text/html; charset=utf-8"
              pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <link rel="shortcut icon" href="resources/img/open.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <title tiles:fragment="title">Login</title>
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-2">
                <a class="logo" href="/">
                    <img alt="Brand" src="/resources/img/open.png" href="/">
                </a>
            </div>

            <div class="col-xs-3">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for..." id="search_form">
                    <span class="input-group-btn">
					    <button class="btn btn-default" type="button" onclick=
                                "searchedProducts (document.getElementById('search_form').value)">Go!</button>
					    </span>
                </div>
            </div>

            <div class="col-xs-5">
                <div class="btn-group" role="group" aria-label="...">
                    <security:authorize access="isAnonymous()">
                        <a href="/registration"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#sign-up">Sign up</button></a>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <a href="/logout"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Log out</button></a>
                    </security:authorize>

                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
<div tiles:fragment="content">
    <form name="f" class="form-horizontal" th:action="@{/login}" method="post" modelAttribute="user">

        <fieldset>

            <legend class="col-sm-2 control-label">Please Login</legend>

            <c:if test="${param.error ne null}">
                <div class="alert alert-error col-sm-2 control-label red">
                    Invalid username or(and) password.
                </div>
            </c:if>

            <div class="form-group">
            <c:if test="${param.logout ne null}">
                <div class="alert alert-success col-sm-2 control-label">
                    You have been logged out.
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="username" class="col-sm-2 control-label">Email</label>
            <input type="text" id="username" name="username"/>
            </div>
            <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <input type="password" id="password" name="password"/>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>
</div>
</div>
</body>
</html>

