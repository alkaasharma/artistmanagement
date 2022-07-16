<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ page isELIgnored="false" %>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
WELCOME ${user.role.roleName}<br>
<a href="viewArtistProfile">My profile</a>
<a href="viewUsers">View Users</a>
<a href="viewUsers">Access Requests</a>
<a href="logoutProfile">Logout profile</a>
</body>
</html>