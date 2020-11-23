<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Management System</title>
<link rel="stylesheet" 
href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
crossorigin="anonymous">

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand"> Student Management System </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Students</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Students</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Student</a>
			</div>
			<div class="container text-center">
				<a href="<%=request.getContextPath()%>/newproc" class="btn btn-success">Add
					New Student via Procedure</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Roll No</th>
						<th>Name</th>
						<th>Email Id</th>
						<th>Mobile No</th>
						<th>Blood Group</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="student" items="${listStudent}">
						<tr>
							<td><c:out value="${student.roll_No}" /></td>
							<td><c:out value="${student.name}" /></td>
							<td><c:out value="${student.email_Id}" /></td>
							<td><c:out value="${student.mobile_No}" /></td>
							<td><c:out value="${student.blood_Group}" /></td>
							<td><a href="edit?roll_No=<c:out value='${student.roll_No}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?roll_No=<c:out value='${student.roll_No}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<c:if test="${count> 0}">
					<button  class="btn btn-primary">Count <span class="badge badge-light">${count}</span></button>
			</c:if>
			
		</div>
	</div>
</body>
</html>