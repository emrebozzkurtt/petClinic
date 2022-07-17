<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LastPet Clinic Login</title>
</head>
<body>
	<h2>LastPet Clinic Login Page</h2>
	<form action="login" method="post">
		Username:<input type="text" name="username"/>
		<br/>
		Password:<input type="password" name="password"/>
		<br/>
		Remember Me:<input type="checkbox" name="remember-me"/>
		<br/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Login"/>
		<font color="red">
			<c:if test="${not empty param.loginFailed}">
				<c:out value="Login Failed, Incorrect Username or Password"></c:out>
			</c:if>
		</font>
	</form>
</body>
</html>