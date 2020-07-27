<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="include/header.jsp" %>
<script type="text/javascript">
async function list(no){
	$('tbody').empty();
	try {
		var curPage = (no||1);
		const res = await axios.get('http://localhost:8008/async/listLoad/'+curPage);
		console.log(res);
		const list = res.data.list.data;//이건리스트
		const pages = res.data.pager;
		console.log(pages);
		const boardPaging = Paging(pages.count, 10, 10, curPage,"boardList");
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
		$("#paging").empty().html(boardPaging);
		
	} catch (e) {
		console.log(e);
		alert("fuck you");
	}
}

var delInput = [];
const asyncSelect = (idx)=>{
	if($("#"+idx).is(":checked")==true){
		delInput.push(idx);
		$("#delfm").append($('<input>').attr('type','hidden').attr('name','idxa').attr('value',idx));
		console.log(delInput);
	}else{
		delInput.splice(delInput.indexOf(idx),1);
		$("input[name='idxa']:input[value='"+idx+"']").remove();
		console.log(delInput);
	}
}

//다중 삭제 도전
const deleteTest=()=>{
	console.log($("#delfm").serialize());
	if(confirm("삭제 하시겠습니까?")){
		if($("#delfm").serialize()===""){
			alert("값이 존재 하지 않습니다.");
		}else{
			var values = $("input[name='idxa']").length;
			var arr=[];
			for(var i = 0; i<values;i++){
				arr[i] = $("input[name='idxa']").eq(i).val();
				//console.log(arr[0]);
				asyncDeletes(arr[i]);
			
			}
			list();
			alert("삭제되었습니다.")
		}
	}
	
}

const asyncDeletes = async (idx) => {
	//console.log(idx);
	await axios.delete(PATH+'/todo/lee/'+idx)
		 .catch(e => {
			   console.log(e);
		 });
}


const ainsert = async ()=>{
	let cont=document.getElementsByName('content')[0].value;
	try {
		const res = await axios.post('http://localhost:8008/async/insert',{content:cont});
		list();
	} catch (e) {
		console.log(e);
	}
}
//일반 삭제
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

const goPaging_boardList =(cPage)=>{
		list(cPage); // boardAdmin 개체의 getBoardList 함수를 다시 호출
};	

//페이징
var Paging = (totalCnt, dataSize, pageSize, pageNo, token)=>{//token 실핼 시킬 함수 이름
    totalCnt = parseInt(totalCnt);	// 전체레코드수
    dataSize = parseInt(dataSize);   // 페이지당 보여줄 데이타수
    pageSize = parseInt(pageSize);   // 페이지 그룹 범위       1 2 3 5 6 7 8 9 10
    pageNo = parseInt(pageNo);       // 현재페이지
   
    var  html = new Array();
    if(totalCnt == 0){
               return "";
    }
   
    // 페이지 카운트
    var pageCnt = totalCnt % dataSize;         
    if(pageCnt == 0){
               pageCnt = parseInt(totalCnt / dataSize);
    }else{
               pageCnt = parseInt(totalCnt / dataSize) + 1;
    }
   
    var pRCnt = parseInt(pageNo / pageSize);
    if(pageNo % pageSize == 0){
               pRCnt = parseInt(pageNo / pageSize) - 1;
    }
   
    //이전 화살표
    if(pageNo > pageSize){
               var s2;
               if(pageNo % pageSize == 0){
                           s2 = pageNo - pageSize;
               }else{
                           s2 = pageNo - pageNo % pageSize;
               }
               html.push('<a href=javascript:goPaging_' + token + '("');
               html.push(s2);
               html.push('");>');
               html.push('◀ ');
               html.push("</a>");
    }else{
               html.push('<a href="#">\n');
               html.push('◀ ');
               html.push('</a>');
    }

    //paging Bar
    for(var index=pRCnt * pageSize + 1;index<(pRCnt + 1)*pageSize + 1;index++){
               if(index == pageNo){
                           html.push('<strong>');
                           html.push(index);
                           html.push('</strong>');
               }else{
                           html.push('<a href=javascript:goPaging_' + token + '("');
                           html.push(index);
                           html.push('");>');
                           html.push(index);
                           html.push('</a>');
               }
               if(index == pageCnt){
                           break;
               }else html.push('  |  ');
    }
     
    //다음 화살표
    if(pageCnt > (pRCnt + 1) * pageSize){
               html.push('<a href=javascript:goPaging_' + token + '("');
               html.push((pRCnt + 1)*pageSize+1);
               html.push('");>');
               html.push(' ▶');
               html.push('</a>');
    }else{
               html.push('<a href="#">');
               html.push(' ▶');
               html.push('</a>');
    }

    return html.join("");
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
<button id="deleteBtn" onclick="deleteTest()">삭제 연습</button>
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
<div id="paging" style="margin-left: 190px;"></div>
</body>
</html>