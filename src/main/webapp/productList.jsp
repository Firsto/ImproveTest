<%--
  Created by IntelliJ IDEA.
  User: razor
  Date: 26.12.15
  Time: 13:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Прайс-лист</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
</head>
<body>
<section>
    <h1>Прайс-лист</h1>

    <form id="filter" method="post" action="products?action=filter">

        <label for="catName">Категория:</label>
        <input type="text" id="catName" name="catName" value="${catName}">

        <label for="name">Наименование:</label>
        <input type="text" id="name" name="name" value="${name}">

        <label for="priceFrom">Цена, от:</label>
        <input type="number" id="priceFrom" name="priceFrom" value="${priceFrom}">

        <label for="priceTo">Цена, до:</label>
        <input type="number" id="priceTo" name="priceTo" value="${priceTo}">

        <button id="search" disabled="disabled" type="submit">Найти</button>
        <a href="/products">Сброс</a>
    </form>

    <script>
        $('#filter > input').on('input', function () {

            var empty = true;

            $('form > input, form > select').each(function () {
                if ($(this).val() != '') {
                    empty = false;
                }
            });

            if (empty) {
                $('#search').attr('disabled', 'disabled');
            } else {
                $('#search').removeAttr('disabled');
            }
        });
    </script>

    <hr>
    <a href="products?action=create">Новый товар</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Категория</th>
            <th>Наименование</th>
            <th>Цена</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productList}" var="product">
            <jsp:useBean id="product" scope="page" type="ru.firsto.improvetest.model.Product"/>
            <tr>
                <td>${product.catalog.name}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td><a href="products?action=update&id=${product.id}">Редактировать</a></td>
                <td><a href="products?action=delete&id=${product.id}">Удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty productList}">
        Нет товаров для отображения. <a href="${addUrl}">Добавить</a> новый.
    </c:if>
</section>
</body>
</html>