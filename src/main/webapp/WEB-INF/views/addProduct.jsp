<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="resources/img/open.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <title>Add product</title>
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
        </div>
    </div>
</header>

<div class="container">
    <h1>
        Adding a product
    </h1>
    <form:form class="form-horizontal" modelAttribute="product" method="POST" enctype="multipart/form-data" >
        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.name"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="name" value="" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.category"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="category" value="" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.type"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="type" value="" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.price"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="price" value="" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.quantity"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="quantity" value="" type="number" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.manufacturer"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="manufacturer" value="" type="text" />
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-2 control-label"><spring:message code="label.product.description"></spring:message></label>
            <div class="col-sm-10">
                <form:input path="description" value="" type="text" />
            </div>
        </div>


        <div class="form-group">
            <label for="imageFile" class="col-sm-2 control-label">Image:</label>
            <div class="col-sm-10">
                <input id="imageFile" name="imageFile" type="file"  />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-10">
                <button type="submit" href="/admin/products">
                    <spring:message code="form.product.submit"></spring:message>
                </button>
            </div>
        </div>

    </form:form>
    <a href="<c:url value='/' />"><img src="<c:url value="/resources/img/back.png"/>"></a>
</div>
</body>
</html>