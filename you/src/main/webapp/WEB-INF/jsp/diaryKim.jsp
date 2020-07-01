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
<script type="text/javascript">
function insert(){
	document.form1.action = "/todo/insert";
	document.form1.submit();
	alert("입력되었습니다.");
}
const list=(page,search)=>{
	if(search == null||search==""){
	page = page || 1;
	location.href = "/todo/kim?curPage="+page;
	}else {
		$('#sendSearch').attr('action','/todo/kim');
		$('#sendSearch').submit();
	}
	
}
</script>
<h1>김종학 다이어리</h1>
<h6><a href="/exceljjud">주드러스한 다운로드</a></h6>
<table border="1" style="margin: 0 auto; border: 1px solid black; border-collapse: collapse;" >
	<thead>
	<form id="sendSearch" name = "sendSearch" method="post" action="">
		<tr>
			<td colspan="4"><input type="text" name = "search" id="search"></td>
			<td colspan="1"><button type="button" onclick="list(${pager.curPage},$('#search').val())">검색</button></td>
		</tr>
	</form>
		<tr>
			<th>번호</th>
			<th>글내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
<%-- 	<c:if test="${ list == null }">
			<td colspan="5" style="text-align: center;">내용이 존재하지 않습니다.</td>
	</c:if> --%>
	
		<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.rnum}</td>
			<td>${list.content }</td>
			<td>${list.writer}</td>
			<td>${list.regDt }</td>
			<td><a href="#" onclick="del(${list.idx})"><button>삭제</button></a></td>
		</tr>
		<script type="text/javascript">
			function del(idx){
				if(confirm("삭제하시겠습니까?")){
					location.href="/todo/del/"+idx;
					alert("삭제되었습니다.");
				}else{
					alert('취소되었습니다.');
				}
			}
		</script>
		</c:forEach>
		
	</tbody>
	<tfoot>
		<tr>
		<form id="form1" name = "form1" action="#" method="post">
			<td colspan="4"><input type="text"  name = "content"></td>
			<td><button onclick="insert()">입력</button></td>
		</form>
		</tr>
	</tfoot>
</table>
 <c:if test="${pager.curBlock > 1}">
               <a href="#" onclick="list('1')">[처음]</a>
            </c:if>
            <c:if test="${pager.curBlock > 1}">
               <a href="#" onclick="list('${pager.prevPage}')">
               [이전]</a>
            </c:if>
            <c:forEach var="num" begin="${pager.blockStart}" end="${pager.blockEnd}">
               <c:choose>
                  <c:when test="${num == pager.curPage}">
                  <!-- 현재 페이지인 경우 하이퍼링크 제거 -->
                     <span style="color:red;">${num}</span>
                  </c:when>
                  <c:otherwise>
                     <a href="#" onclick="list('${num}')">${num}</a>
                  </c:otherwise>
               </c:choose>
            </c:forEach>
            <c:if test="${pager.curBlock < pager.totBlock}">
               <a href="#"   onclick="list('${pager.nextPage}')">[다음]</a>
            </c:if>
            <c:if test="${pager.curPage < pager.totPage}">
               <a href="#"   onclick="list('${pager.totPage}')">[끝]</a>
            </c:if>

<br>

<h1>파일첨부 연습</h1>
	<hr>
	<form action="/uploadFileTest" method="post" enctype="multipart/form-data">
		<table border="1" style="margin: 0 auto; border: 1px solid black; border-collapse: collapse;">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><input type="text" name="writer" size="10" /></td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70">업로드</td><td align="left">
				<input type="file" name="uploadFile"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="글 쓰기" /></td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="/testview">글보러 가기</a>
<a href="/async/list">비동기 페이지</a>
<!-- @breif accept 태그는 파일 업로드시 그것을 이미지 파일로 제한한다. -->
<br>
<h1 style="text-align: center;">섬네일기능</h1>
    <input type="file" id="upImgFiles" onChange="uploadImgPreview();" accept="image/*" multiple>

    <hr/>
<!-- <img alt="aa" src="" id='imgs' width="100px" height="100px"> -->
   <div id="thumbnailImgs"></div>
    <script>
    function uploadImgPreview() {
    	// @breif 업로드 파일 읽기
    	let fileList = document.getElementById( "upImgFiles" ).files;
		// @breif 업로드 파일 읽기
		function readAndPreview( fileList ) {
			// @breif 이미지 확장자 검사
			if ( /\.(jpe?g|png|gif)$/i.test( fileList.name ) ) {
				let reader = new FileReader();
				reader.addEventListener( "load", function() {
					let image = new Image();
					image.width = "100";
					image.height = "100";
					image.title = fileList.name;
					image.src = this.result;
					//경로에 사진 붙여주기
					//$('#imgs').attr("src",this.result);
					// @details 이미지 확장자 검사(div에 붙여주는 방법)
					document.getElementById( "thumbnailImgs" ).appendChild( image );
				}, false );
				// @details readAsDataURL( )을 통해 파일의 URL을 읽어온다.
				if( fileList ) {
					reader.readAsDataURL( fileList );
				}
			}
		}
    	if( fileList ) {
		// @details readAndPreview() 함수를 forEach문을통한 반복 수행
		[].forEach.call( fileList, readAndPreview );
        }

    }
    </script>
</body>
</html>