<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center;">
<h1>김종학 다이어리</h1>
<table border="1" style="margin: 0 auto; border: 1px solid black; border-collapse: collapse;" >
	<thead>
		<tr>
			<th>번호</th>
			<th>글내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="" var="list">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><button name="del" value="1">삭제</button></td>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4"><input type="text" id="content"></td>
			<td><button id="input">입력</button></td>
		</tr>
	</tfoot>
</table>
</body>
</html>