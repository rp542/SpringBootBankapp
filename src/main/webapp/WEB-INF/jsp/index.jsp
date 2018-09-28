<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%@include file="header.jsp"%>
	<%-- <c:if test="${sessionScope.customer.customerId != null}"> --%>
		<h3>Customer Id: ${sessionScope.customer.customerId}</h3>
		<h3>Customer Name: ${sessionScope.customer.customerName}</h3>
		<h3>Customer email: ${sessionScope.customer.email}</h3>
		<h3>Account Balance: ${sessionScope.customer.account.balance}</h3>
		<h3>Address: ${sessionScope.customer.address}</h3>
	
	<c:if test="${sessionScope.customer.customerId == null}">

</c:if>

</body>
</html>