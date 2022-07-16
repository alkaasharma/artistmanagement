<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	${message}
	<form:form method="post" action="editArtistProfile"
		modelAttribute="user">
		<table>
			<tr>
				<td colspan=2><button>request elevated access</button></td>
			</tr>


			<tr>
				<td></td>
				<td><form:hidden path="userId" /></td>
			</tr>
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
				<td>Address :</td>
				<td><form:input path="address" /></td>
			</tr>




			<tr>
				<td>Date Of Birth</td>
				<td id="datepicker"><form:input path="dob" /> <span
					class="fa fa-calendar"></span></td>
			</tr>


			<%-- <tr>
				<td></td>
				<td><form:hidden path="registeredOn" /></td>
			</tr>
 --%>

			<tr>
				<td>Phone Number :</td>
				<td><form:input path="phoneNumber" /></td>
			</tr>


			<tr>
				<td>AccessType :</td>
				<td><form:select path="role.roleId">
						<form:option value="${user.role.roleId}">${user.role.roleName}</form:option>
					</form:select></td>
			</tr>




			<tr>
				<td>Department:</td>
				<td><form:select path="department.departmentId">
						<c:forEach items="${departmentList}" var="departments">
							<form:option value="${departments.departmentId}">${departments.departmentName}</form:option>
						</c:forEach>
					</form:select></td>
			</tr>






			<tr>
				<td></td>
				<td><input type="submit" value="update" /></td>
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