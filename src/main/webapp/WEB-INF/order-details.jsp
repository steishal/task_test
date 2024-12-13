<jsp:useBean id="order" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Order Details</title>
</head>
<body>
<h1>Order Details</h1>
<p>Order Number: ${order.orderNumber}</p>
<p>Items: ${order.items}</p>
<p>Table Number: ${order.tableNumber}</p>
<p>Waiter Name: ${order.waiterName}</p>
<a href="${pageContext.request.contextPath}/orders">Back to List</a>
</body>
</html>