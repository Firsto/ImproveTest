<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2>Редактирование товара</h2>
    <hr>
    <jsp:useBean id="product" type="ru.firsto.improvetest.model.Product" scope="request"/>
    <form method="post" action="products">
        <input type="hidden" name="id" value="${product.id}">
        <dl>
            <dt>Категория:</dt>
            <dd>
                <select name="catId">
                    <option disabled>Выберите категорию</option>
                    <c:forEach items="${catalogList}" var="catalog">
                        <jsp:useBean id="catalog" scope="page" type="ru.firsto.improvetest.model.Catalog"/>
                        <option value="${catalog.id}" ${catalog.id == product.catalog.id ? 'selected' : ''}>${catalog.name}</option>
                    </c:forEach>
                    <%--<option value="1" selected>Принтеры</option>--%>
                    <%--<option value="2">Картриджи для принтеров</option>--%>
                </select>
            </dd>
        </dl>
        <dl>
            <dt>Название:</dt>
            <dd><input type="text" value="${product.name}" size=40 name="name"></dd>
        </dl>
        <dl>
            <dt>Цена:</dt>
            <dd><input type="number" value="${product.price}" name="price"></dd>
        </dl>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>
