<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>Список заказов</h1>
<ul>
    <%-- Проходим по списку заказов, переданному через request --%>
    <c:forEach var="order" items="${orders}">
        <li>
            <a href="${pageContext.request.contextPath}/orders?id=${order.id}">Посмотреть заказ</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>





