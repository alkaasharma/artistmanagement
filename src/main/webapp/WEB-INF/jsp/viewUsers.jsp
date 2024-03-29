<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
a.disabled {
	pointer-events: none;
	cursor: default;
}
</style>
</head>
<body>
<a href="viewArtistProfile">My profile</a>
<a href="viewUsers">View Users</a>
<a href="logoutProfile">Logout profile</a>

	<table border="1" cellpadding="5" cellspacing="5" id="usrtable"> 
		<tr>
			<td>Department:</td>
			<td><select  path="departmentId" onchange="getUsers(this.value)">
					<option value="0">All</option>
					<c:forEach var="departments" items="${departmentList}">
						<c:choose>
							<c:when test="${departments.departmentId == selectedDepartment}">
								<option selected="true" value="${departments.departmentId}">${departments.departmentName}</option>
							</c:when>
							<c:otherwise>
								<option value="${departments.departmentId}">${departments.departmentName}</option>
							</c:otherwise>
						</c:choose>


					</c:forEach>
			</select></td>
			<td colspan=2></td>
			<td><input type="text" name="search"></td>
			<td><button type="submit">
					<i class="fa fa-search"></i>
				</button></td>
		</tr>

		<tr>
			<th>USER ID</th>
			<th>FIRST NAME</th>
			<th>LAST NAME</th>
			<th>ACCESS TYPE</th>
			<th>DEPARTMENT</th>
			<th>OPERATION</th>
		</tr>

		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.userId}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.role.roleName}</td>
				<td>${user.department.departmentName}</td>
				<td><c:choose>
						<c:when test="${user.role.roleName =='ARTIST'}">
							<a href="updateUser/${user.userId}" class="btn btn-primary">Update</a>
							<a href="deleteUser/${user.userId}" class="btn btn-primary">Delete</a>
						</c:when>
						<c:otherwise>
							<a class="disabled" href="">Update</a>
							<a class="disabled" href="">delete</a>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>


		<%--For displaying Previous link except 
		for the 1st page --%>
		<%-- <c:if test="${currentPage != 1}">
		<td><a href="viewUsers?page=${currentPage - 1}">Previous</a></td>
	</c:if> --%>

		<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>

		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">

				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a href="viewUsers?page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>

	</table>

	<%--For displaying Next link --%>
	<%-- <c:if test="${currentPage lt noOfPages}">
		<td><a href="viewUsers?page=${currentPage + 1}">Next</a></td>
	</c:if> --%>

	<!--view users on the basis of departments-->
	<script type="text/javascript">
		function getUsers(id) {
			var department = id;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("usrtable").innerHTML = this.response;
				}
			};
			xhttp.open("GET", "viewUsers?departmentId=" + department, true);
			xhttp.send();
		}
	</script>
<a href="welcome_manager">back</a>
</body>
</html>