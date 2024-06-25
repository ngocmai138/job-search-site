<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job search Home page</title>
</head>
<body>
<h2>Job search Home page</h2>
<c:forEach var="co" items="${companies }">
	Name company: ${co.nameCompany }
</c:forEach>
</body>
</html>