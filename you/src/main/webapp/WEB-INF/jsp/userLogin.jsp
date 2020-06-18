<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h1>로그인 페이지</h1>
<form action="/loginProcess" method = "post">
	<table>
		<tr>
			<td>username</td><td><input type="text" name  = "username" placeholder="username 입력해 "></td>
		</tr>
		<tr>
			<td>password</td><td><input type="password" name  = "password" placeholder="password 입력해 "></td>
		</tr>
		<tr>
			<td><button type="submit">로그인</button> </td>
		</tr>
	</table>
</form>
</body>
</html>