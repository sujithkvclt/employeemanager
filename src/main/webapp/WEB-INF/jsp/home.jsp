<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link href="<c:url value="../css/home.css" />" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Manager</title>
</head>
<body>
	<div align="center" >
		<h1>Employee List</h1>
		<h3>
			<a href="newEmployee">New Employee</a>
		</h3>
		<table border="1" class="gridtable" >
			<tr>
				<th>Name</th>
				<th>Employee Code</th>
				<th>Designation</th>
				<th>Location</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="employee" items="${employeeList}" varStatus="status">
				<tr>
					<td>${employee.name}</td>
					<td>${employee.code}</td>
					<td>${employee.designation}</td>
					<td>${employee.location}</td>
					<td><a href="deleteEmployee?id=${employee.id}"  onClick="return confirm('Do you want to delete ${employee.code}')">delete</a></td>
					<td><a href="editEmployee?id=${employee.id}">edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
