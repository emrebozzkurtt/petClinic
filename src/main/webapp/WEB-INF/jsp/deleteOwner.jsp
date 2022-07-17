<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Last Pet Clinic Delete Owner</title>
	</head>
	<body>
		<h1>Owner Delete Page</h1>
		<form:form modelAttribute="owner" method="post">
			Firstname:<form:input path="firstName"/>
			<br/>
			Lastname:<form:input path="lastName" />
			<br/>
			<form:button name="submit">Delete</form:button>
		</form:form>
	</body>
</html>