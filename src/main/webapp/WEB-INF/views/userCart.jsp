<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link rel="shortcut icon" href="resources/img/open.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <title>Cart</title>
</head>
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

                </div>

            </div>
            <div class="col-xs-2" >
                <div class="wrap-basked">
                    <div class="header-basket_block">
                        <a href="/cart" class="text-center pull-left header-basket">
                            <img src="/resources/img/basket.png" alt="">
                        </a>
                        <span class="text-center basket-circle "><fmt:formatNumber  type="number"  value="${quantity}" /></span>
                        Total price:<fmt:formatNumber  type="currency"  value="${total}" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<body>
    <div class="container">
        <div class="row">
            <h1 class="cart-header">Your cart</h1>
            <c:forEach items="${products}" var="product">
                <div class="col-xs-6">
                    <div class="thumbnail thumbnail2">

                        <a class="delete-but" onclick="deleteFromCart('${product.name}')" href="javascript:void (0)"><img src="<c:url value="/resources/img/error.png"/>"></a>

                        <img src="/products/imageDisplay?name=${product.name}" alt="product">
                        <div class="caption">
                            <h3>${product.name}</h3>
                            <p>Price: ${product.price}</p>
                            <p><a href="/view/${product.name}" class="btn btn-primary" role="button">Review </a>

                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a href="<c:url value='/' />"><img src="<c:url value="/resources/img/back.png"/>" alt=""></a>
    </div>

    <script src="<c:url value="/resources/js/jquery-3.1.1.js"/>"></script>
    <script src="<c:url value="/resources/js/main.js"/>"></script>

</body>
</html>
