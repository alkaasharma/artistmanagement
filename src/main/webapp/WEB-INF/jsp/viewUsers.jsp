<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form:select path="department.departmentId">
		<c:forEach items="${departmentList}" var="departments">
			<form:option value="${departments.departmentId}">${departments.departmentName}</form:option>
		</c:forEach>
	</form:select>



	<table border="1" cellpadding="5" cellspacing="5">

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
				<td><button>edit</button>
					<button>view</button></td>
			</tr>
		</c:forEach>
	</table>

	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<td><a href="viewUsers?page=${currentPage - 1}">Previous</a></td>
	</c:if>

	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
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
	<c:if test="${currentPage lt noOfPages}">
		<td><a href="viewUsers?page=${currentPage + 1}">Next</a></td>
	</c:if>
</body>
</html>