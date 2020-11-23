<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${Student != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${Student == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${Student != null}">
            			Edit Student
            		</c:if>
						<c:if test="${Student == null}">
            			Add New Student
            		</c:if>
					</h2>
				</caption>

				<c:if test="${Student != null}">
					<input type="hidden" name="roll_No" value="<c:out value='${Student.roll_No}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${Student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Email</label> <input type="text"
						value="<c:out value='${Student.email_Id}' />" class="form-control"
						name="email_Id">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Student Mobile No</label> <input type="text"
						value="<c:out value='${Student.mobile_No}' />" class="form-control"
						name="mobile_No">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Blood Group</label> <input type="text"
						value="<c:out value='${Student.blood_Group}' />" class="form-control"
						name="blood_Group">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>