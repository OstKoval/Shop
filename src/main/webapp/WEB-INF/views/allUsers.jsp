<!-- <%@ page language="java" contentType="text/html; charset=utf-8"
              pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="shortcut icon" href="resources/img/open.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <title>Users</title>
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
                        <a href="/admin/products"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Products</button></a>
                    </security:authorize>
                </div>
            </div>

        </div>
    </div>
</header>
<div class="container">
    <h2>List Users</h2>
    <table class="table table-striped">
        <tr>
            <td>Id</td>
            <td>First/Last name</td>
            <td>Email</td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.email}</td>
                <td><a href="<c:url value='/admin/users/edit/id=${user.id}' />"><img src="<c:url value="/resources/img/edit.png"/>" alt=""></a></td>
                <td><a href="#" onclick="deleteUser(
                        '${user.email}'
                        )"><img src="<c:url value="/resources/img/delete.png"/>" alt=""></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value='/' />"><img src="<c:url value="/resources/img/back.png"/>" alt=""></a>
</div>
<br/>
<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>