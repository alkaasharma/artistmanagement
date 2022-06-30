<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style><%@include file="/WEB-INF/css/login.css"%></style>
</head>
<body>
<header>
</header>
<main>
<h2>Artist management</h2>
<br>
<br>
<c:if test="${not empty errorMessage}">
	<h3 style="color: red;">${errorMessage}</h3>
</c:if>
<form:form method="post" action="login/login" modelAttribute="login">
<form:hidden path="userId"/>
  <div class="form-group">
    <label>Username: </label>
    <form:input path="email" />
  </div>
  <div class="form-group">
    <label>Password: </label>
    <form:input path="password" />
  </div>
  <div class="form-group">
  <button id ="login" type="submit" class="btn btn-success">Login</button>
  <button id ="signup" class="btn btn-success"> <a href="register" alt='Broken Link' style ='text-decoration: none;'>Signup</a></button>
  </div>
</form:form>
</main>
</body>
</html>