<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="resources/img/open.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <title>Registration</title>
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
                    <security:authorize access="isAuthenticated()">
                        <a href="/logout"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Log out</button></a>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/admin/users"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Users list</button></a>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/admin/add"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Register new product</button></a>
                    </security:authorize>
                </div>
            </div>

            <div class="col-xs-2" >
                <div class="wrap-basked">
                    <div class="header-basket_block">
                        <a href="#" class="text-center pull-left header-basket">
                            <img src="/resources/img/basket.png" alt="">
                        </a>
                        <span class="text-center basket-circle ">0</span>
                        Cart
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<form:form class="form-horizontal" modelAttribute="user" method="POST" enctype="utf8">

<div class="container">
    <label class="col-sm-2 control-label"></label>
    <h1>
        Registration
    </h1>


    <div class="form-group">
        <label class="col-sm-2 control-label"><spring:message code="label.user.firstName"></spring:message></label>
        <div class="col-sm-10">
            <form:input path="firstName" value="" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><spring:message code="label.user.lastName"></spring:message></label>
        <div class="col-sm-10">
            <form:input path="lastName" value="" />
            <form:errors path="lastName" element="div" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label"><spring:message code="label.user.email"></spring:message></label>
        <div class="col-sm-10">
            <form:input path="email" value="" />
            <form:errors path="email" element="div" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label"><spring:message code="label.user.password"></spring:message></label>
        <div class="col-sm-10">
            <form:input path="password" value="" type="password"/>
            <form:errors path="password" element="div" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><spring:message code="label.user.confirmPass"></spring:message></label>
        <div class="col-sm-10">
            <form:input path="matchingPassword" value="" type="password" />
            <form:errors element="div" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <button type="submit">
                <spring:message code="form.user.sign_up"></spring:message>
            </button>
        </div>
    </div>
    <a href="<c:url value='/' />"><img src="<c:url value="/resources/img/back.png"/>"></a>
</div>
</form:form>
<br>

</body>
</html>

