<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="SimiAlex.com.github.applicationB.dto.MessageDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  	<head>
    	<title>Messages</title>
  	</head>

  	<body>
	    <h1>List of received messages</h1>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>MessageText</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="message" items="${sessionScope.messages}" >
					<tr>
						<td>${message.id}</td>
						<td>${message.message}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

  	</body>
</html>