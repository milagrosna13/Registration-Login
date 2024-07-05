<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h2>Register</h2>
				<!-- FORM:FORM GENERA UNA INSTANCIA -->
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div>
						<form:label path="firstName">First Name: </form:label>
						<form:input path="firstName" class="form-control"></form:input>	
						<form:errors path="firstName" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="lastName">last Name: </form:label>
						<form:input path="lastName" class="form-control"></form:input>	
						<form:errors path="lastName" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="email">Email: </form:label>
						<form:input path="email" class="form-control"></form:input>	
						<form:errors path="email" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="password">Password: </form:label>
						<form:input path="password" class="form-control"></form:input>	
						<form:errors path="password" class="text-danger"></form:errors>
					</div>
					<div>
						<form:label path="confirmation">Confirmation: </form:label>
						<form:input path="confirmation" class="form-control"></form:input>	
						<form:errors path="confirmation" class="text-danger"></form:errors>
					</div>
					<input type="submit" class="btn btn-primary" value="REGISTER">
				</form:form>
			</div>
			<div class="col-6">
				<h2>Login</h2>
				<p class="text-danger">${errorLogin}</p>
				<form action="/login" method="post">
					<div>
						<label>Email</label>
						<input type="email" class="form-control" name="email">
					</div>
					<div>
						<label>Password</label>
						<input type="password" class="form-control" name="password">	
					</div>
					<input type="submit" class="btn btn-info mt-3" value="login">
				</form>	
			</div>
		</div>
	</div>
</body>
</html>