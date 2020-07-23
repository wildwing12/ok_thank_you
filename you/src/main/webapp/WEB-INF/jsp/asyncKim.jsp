<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="include/header.jsp" %>
<script type="text/javascript">
async function list(){
	$('tbody').empty();
	try {
		const res = await axios.get('http://localhost:8008/async/listLoad');
		console.log(res);
		const list = res.data.data;
		
			list.forEach((item,i)=>{
				$('<tr>')
				.append($('<td>').append($('<input>').attr('type','checkbox').attr('id',item.IDX).attr('name','chk').attr('onchange','asyncSelect('+item.IDX+')')))
				.append($('<td>').html(item.RNUM))
				.append($('<td>').html(item.CONTENT))
				.append($('<td>').html(item.WRITER))
				.append($('<td>').html(item.REG_DT))
				.append($('<td>').append($('<button>').attr('id','del'+i).attr('onclick','asyncDelete('+item.IDX+')').html('쥬드')))
				.appendTo('tbody');
				
				
				i++;
			})
		
		
	} catch (e) {
		console.log(e);
		alert("fuck you");
	}
}

var delInput = [];
const asyncSelect = (idx)=>{
	if($("#"+idx).is(":checked")==true){
		delInput.push(idx);
		console.log(delInput);
		$("#deleteBtn").click(function(){
			for(var i = 0; i<delInput.length; i++){
				console.log(delInput[i]);
			    event.cancelBubble = true;
			      event.returnValue = false;
			 alert("새로고침 방지");

			}
			list()
		})
	}else{
		delInput.splice(delInput.indexOf(idx),1);
		console.log(delInput);
	}
}
console.log(delInput);

const ainsert = async ()=>{
	let cont=document.getElementsByName('content')[0].value;
	try {
		const res = await axios.post('http://localhost:8008/async/insert',{content:cont});
		list();
	} catch (e) {
		console.log(e);
	}
}
//선택한 selectbox 처리
const asyncDelete = async (idx) => {
	//console.log(idx);
	await axios.delete(PATH+'/todo/lee/'+idx)
		 .then(res => {
			   if(res.status == 200){
				   list();
			   }
		 })
		 .catch(e => {
			   console.log(e);
		 });
}


//리스트 실행
list();
</script>
</head>
<body>

<!-- 삭제를 위한 form태그 -->
<form id="delfm" name="delfm">
</form>

<h1 style="text-align: center;">비동기 페이지</h1>
<input type="button" id ="deleteBtn" value="삭제연습">
<table border="1" style="margin: 0 auto; border: 1px solid black; border-collapse: collapse;" >
	<thead>
		<tr>
			<th>박스</th>
			<th>번호</th>
			<th>글내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody id="tbody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4"><input type="text"  name = "content"></td>
			<td><button onclick="ainsert()">입력</button></td>
		</tr>
	</tfoot>
</table>
</body>
</html>