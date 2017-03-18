<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<form:form modelAttribute="total" method="GET" enctype="utf8">
    <div class="container">
        <div class="row">
            <c:forEach items="${products}" var="product">
            <div class="col-xs-4">
                <div class="thumbnail">
                    <img src="/products/imageDisplay?name=${product.name}" alt="product">
                    <div class="caption">
                        <h3>${product.name}</h3>
                        <p>Price: ${product.price}</p>
                        <p><a href="/view/${product.name}" class="btn btn-primary" role="button">Review </a>
                            <security:authorize access="isAuthenticated()">
                            <a onclick="addToCart('${product.name}') " class="btn btn-success add-success" role="button">Add to cart<span class="success-content"><span></span></span></a>
                            </security:authorize>
                        </p>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</form:form>
<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>

