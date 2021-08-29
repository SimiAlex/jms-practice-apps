<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="SimiAlex.com.github.backend.model.Car" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  	<head>
    	<title>Cars</title>
  	</head>

  	<body>
	    <h1>List of cars</h1>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Make</th>
					<th>Model</th>
					<th>Year</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="car" items="${sessionScope.cars}" >
					<tr>
						<td>${car.id}</td>
						<td>${car.make}</td>
						<td>${car.model}</td>
						<td>${car.year}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

  	</body>
</html>