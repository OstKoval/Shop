<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>BluePay</title>
	<link rel="shortcut icon" href="resources/img/open.ico"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<header>
	<div class="container">
		<div class="row">
			<div class="col-xs-2">
				<a class="logo" href="/">
					<img alt="Brand" src="/resources/img/open.png" >
				</a>
			</div>

			<div class="col-xs-3">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search for..." id="search_form">
					<span class="input-group-btn">
					    <button  class="btn btn-default" type="button" id="search_but" onclick=
								"searchedProducts (document.getElementById('search_form').value)">Go!</button>
					    </span>
				</div>
			</div>

			<div class="col-xs-5">
				<div class="btn-group" role="group" aria-label="...">
					<security:authorize access="isAnonymous()">
						<a href="/registration"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#sign-up">Sign up</button></a>
					</security:authorize>
					<security:authorize access="isAnonymous()">
						<a href="/loginPage"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Log in</button></a>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<a href="/logout"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Log out</button></a>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<a href="/admin/users"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Users list</button></a>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<a href="/admin/products"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Products</button></a>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<a href="/admin/add"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#log-in">Register new product</button></a>
					</security:authorize>
				</div>
			</div>

			<security:authorize access="isAuthenticated()">
			<div class="col-xs-2" >
				<div class="wrap-basked">
					<div class="header-basket_block">
						<a href="/cart" class="text-center pull-left header-basket">
							<img src="/resources/img/basket.png" alt="">
						</a>
						<span class="text-center basket-circle ">
							<fmt:formatNumber  type="number"  value="${quantity}" /></span>
						Total price:<fmt:formatNumber  type="currency"  value="${total}" />
					</div>
				</div>
			</div>
			</security:authorize>

		</div>
	</div>
</header>

<section class="slides">
	<div class="container">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner" role="listbox">
				<div class="item product1 active">
				</div>

				<div class="item product2">
				</div>

				<div class="item product3">
				</div>
			</div>

			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</section>

<section class="product-types">
	<div class="container">
		<div class="btn-group btn-group-justified" role="group" aria-label="...">
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Computers
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a onclick="filterProducts('${"Computers"}','${"PC"}')">PC</a></li>
					<li><a onclick="filterProducts('${"Computers"}','${"Notebooks"}')">Notebooks</a></li>
					<li><a onclick="filterProducts('${"Computers"}','${"Software"}')">Software</a></li>
				</ul>
			</div>

			<div class="btn-group" role="group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					TV
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a onclick="filterProducts('${"TV"}','${"TV"}')">TV</a></li>
					<li><a onclick="filterProducts('${"TV"}','${"Cables and adapters"}')">Cables and adapters</a></li>
					<li><a onclick="filterProducts('${"TV"}','${"Supports, mounts for TV"}')">Supports, mounts for TV</a></li>
				</ul>
			</div>

			<div class="btn-group" role="group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Telephones
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a onclick="filterProducts('${"Telephones"}','${"Push-button phones"}')">Push-button phones</a></li>
					<li><a onclick="filterProducts('${"Telephones"}','${"Smartphones"}')">Smartphones</a></li>
					<li><a onclick="filterProducts('${"Telephones"}','${"Office phones"}')">Office phones</a></li>
				</ul>
			</div>

			<div class="btn-group" role="group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Photo and video
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a onclick="filterProducts('${"Photo and video"}','${"Photocameras"}')">Photocameras</a></li>
					<li><a onclick="filterProducts('${"Photo and video"}','${"Videocameras"}')">Videocameras</a></li>
					<li><a onclick="filterProducts('${"Photo and video"}','${"Bags and cases"}')">Bags and cases</a></li>
				</ul>
			</div>

			<div class="btn-group" role="group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Accessories
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a onclick="filterProducts('${"Accessories"}','${"Keyboards"}')">Keyboards</a></li>
					<li><a onclick="filterProducts('${"Accessories"}','${"Mouses"}')">Mouses</a></li>
					<li><a onclick="filterProducts('${"Accessories"}','${"Game consoles"}')">Game consoles</a></li>
				</ul>
			</div>
		</div>
	</div>
</section>


<section class="products">
	<div class="container">
		<div class="row" id="product-container" >
			<jsp:include page="productViewTemplate.jsp"></jsp:include>

		</div>
	</div>
</section>

<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>