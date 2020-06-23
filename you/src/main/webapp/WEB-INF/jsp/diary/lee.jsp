<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lee's Diary</title>
<%@ include file="../include/header.jsp" %>
<style>
table {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
<h1>이현주 다이어리</h1>
<input id="content" onkeypress="if( event.keyCode==13 ){ insert(); }" />
<hr>
<table border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<!-- <tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<button>삭제</button>
			</td>
		</tr> -->
	</tbody>
</table>
<script>
$(document).ready(function(){
	list();
});
async function list(){
	//$('tbody').empty();
	let tbody = document.querySelector('tbody');
	tbody.innerHTML = "";
	try {
		const res = await axios.get(URI+'/todo/lee/asyncList');
		//console.log(res);
		if(res.status == 200){
			const list = res.data.list;
			list.forEach((item, i) => {
				//console.log(item);
				$('<tr>')
				.append($('<td>').html(item.rnum))
				.append($('<input>').attr('type','hidden').val(item.idx))
				.append($('<td>').html(item.content))
				.append($('<td>').html(item.writer))
				.append($('<td>').html(item.regDt))
				.append($('<td>').append($('<button>').attr('id',item.idx).html('삭제')))
				.appendTo('tbody');
				$('#'+item.idx).attr('onclick','asyncDelete('+item.idx+')');
				i++;
			});
		}
	}catch(e){
		console.log(e);
	}
}
const insert = async function(){
	let content = $('#content').val();
	let paramData = { content: content };
	try {
		const res = await axios.post(URI+'/todo/lee/insert', paramData);
		if(res.status == 200){
			$('#content').val('');
			list();
		}
	} catch (e) {
		console.log(e);
	}
}
const asyncDelete = async (idx) => {
	//console.log(idx);
	try {
		const res = await axios.delete(URI+'/todo/lee/'+idx);
		if(res.status == 200){
			list();
		}
	} catch (e) {
		console.log(e);
	}
}
</script>
</body>
</html>