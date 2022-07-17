<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Last Pet Clinic Edit Owner</title>
	</head>
	<body>
		<h1>Owner Edit Page</h1>
		<form:form modelAttribute="owner" method="post">
			Firstname:<form:input path="firstName"/>
			<form:errors path="firstName" cssStyle="color:red"/>
			<br/>
			Lastname:<form:input path="lastName" />
			<form:errors path="lastName" cssStyle="color:red"/>
			<br/>
			<form:button name="submit">Update</form:button>
		</form:form>
	</body>
</html>