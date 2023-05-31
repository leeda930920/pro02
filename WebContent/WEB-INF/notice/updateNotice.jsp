<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>공지사항 글 수정하기</title>
<style>
.container-fluid { width:1280px; }
#fileSel1:checked ~ #file1 { display:none; }
#fileSel2:checked ~ #file1 { display:block; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>글 수정</h2>
		<p>${msg }</p>
		<form action="${path1 }/UpdateNoticePro.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="title">제목</label></th>
						<td>
							<input type="hidden" name="author" id="author" value="${sid }">
							<input type="hidden" name="idx" id="idx" value="${noti.idx }">
							<input type="text" name="title" id="title" value="${noti.title }" maxlength="98" title="100자 내로 작성" placeholder="100자 내로 작성" class="form-control" required autofocus>
						</td>
					</tr>
					<tr>
						<th><label for="content">글 내용</label></th>
						<td>
							<textarea rows="10" cols="100" name="content" id="content" maxlength="990" title="1000자 내로 작성" class="form-control">${noti.content }</textarea>
						</td>
					</tr>
					<tr>
						<th><label for="file1">첨부 파일</label></th>
						<td>
							<span>
								<c:set var="lh" value="${fn:length(noti.file1) }" />
								<c:set var="download" value="${fn:substring(noti.file1,5,lh) }" />
								${download }
							</span><br>
							<input type="radio" name="fileSel" id="fileSel1" onclick="fileCall()" checked>교체 안함<br> 
							<input type="radio" name="fileSel" id="fileSel2" onclick="fileCall()" >교체<br>
							<input type="file" name="file1" id="file1" class="">
							<input type="hidden" name="file1" id="file2" value="${download }">
							<script>
							function fileCall(){
								var fileSel1 = document.getElementById("fileSel1");
								var fileSel2 = document.getElementById("fileSel2");
								var file1 = document.getElementById("file1");
								var file2 = document.getElementById("file2");
								if(fileSel1.checked){
									file1.style.display = "none";
									file1.setAttribute("disabled");
									file2.removeAttribute("disabled");
								} else {
									file1.style.display = "block";
									file1.removeAttribute("disabled");
									file2.setAttribute("disabled");
								}
							}
							</script>
						</td></tr><tr>
						<td colspan="2">
							<input type="submit" value="글 수정" class="btn btn-primary">
							<a href="${path1 }/NoticeList.do" class="btn btn-primary">글 목록</a>				
						</td></tr></tbody></table>
		</form>		
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>