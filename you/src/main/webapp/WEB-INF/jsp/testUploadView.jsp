<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="text-align: center;">리스트</h1>
<table border="1" style="margin: 0 auto; border: 1px solid black; border-collapse: collapse;">
<thead>
<tr>
	<td>인덱스</td>
	<td>작성자</td>
	<td>제목</td>
	<td>내용</td>
	<td>등록일</td>
	<td>카운트</td>
	<td>파일이름</td>
</tr>
</thead>
<tbody>
<c:forEach items="${list}" var="list">
<tr>
	<td>${list.idx }</td>
	<td>${list.writer }</td>
	<td>${list.title }</td>
	<td>${list.content}</td>
	<td>${list.regDate}</td>
	<td>${list.cnt}</td>
	<td><a href="/fileDownload?oriname=${list.oriname}&filename=${list.fileName}">${list.oriname}</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</body>
</html>