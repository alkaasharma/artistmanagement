<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>
<body>

	<form:form method="post" action="saveInfo" modelAttribute="user">
		<table>
			<tr>
				<td>First Name :</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td>Date Of Birth</td>
				<td id="datepicker"><form:input path="dob" /> <span
					class="fa fa-calendar"></span></td>
			</tr>

			<tr>
				<td>Address :</td>
				<td><form:input path="address" /></td>
			</tr>

			<tr>
				<td>Phone Number :</td>
				<td><form:input path="phoneNumber" /></td>
			</tr>

			<tr>
				<td>Image</td>
				<td>
					<div>
						<img id="captcha_id" name="imgCaptcha" src="captcha.jpg">
					</div>
				</td>

				<td align="left"><a href="javascript:;"
					title="change captcha text"
					onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false">
						<span class="fa fa-refresh"></span>
				</a></td>

			</tr>

			<tr>
				<td>Enter above captcha</td>
				<td><form:input path="captcha" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>

	<!-- Bootstrap DatePicker -->
	<script type="text/javascript">
		$(function() {
			$('#datepicker').datepicker();
		});
	</script>
</body>
</html>