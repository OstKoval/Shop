<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="resources/img/open.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <title>Review</title>
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
            </div>
            <security:authorize access="isAuthenticated()">
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
            </security:authorize>
        </div>
    </div>
</header>


<form:form class="form-horizontal" modelAttribute="product" method="GET" enctype="utf8">
    <div class="container">
        <div class="row">
            <div class="col-xs-5">
                <div class="product-photo">
                    <img src="/products/imageDisplay?name=${product.name}" alt="product">
                </div>
            </div>
            <div class="col-xs-7">
                <div class="product-descr">
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <div class="cart-add">
                        <div class="col-xs-4">
                            <p>Price:${product.price}</p>
                        </div>
                        <security:authorize access="isAuthenticated()">
                        <div class="col-xs-2 col-xs-offset-5">
                            <button onclick="addToCart('${product.name}')" class="btn btn-success add-success" >Add to cart<span class="success-content"><span></span></span></button>
                        </div>
                        </security:authorize>

                    </div>
                </div>
            </div>
        </div>
        <a href="<c:url value='/' />"><img src="<c:url value="/resources/img/back.png"/>" alt=""></a>
    </div>
</form:form>

<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>
