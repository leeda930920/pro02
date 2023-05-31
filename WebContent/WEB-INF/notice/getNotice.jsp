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
<title>공지사항 글 상세보기</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>글 상세보기</h2>
		<table class="table">
			<tbody>
				<tr>
					<th>글 번호</th>
					<td>${noti.idx }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${noti.title }</td>
				</tr>
				<tr>
					<th>글 내용</th>
					<td>${noti.content }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${noti.author }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>
						<fmt:parseDate value="${noti.resdate }" var="resdate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${resdate }" pattern="yyyy년 MM월 dd일" />
					</td>
				</tr>
				<c:if test="${!empty noti.file1 }">
				<tr>
					<th>첨부 파일</th>
					<td>
						<c:set var="lh" value="${fn:length(noti.file1) }" />
						<c:set var="download" value="${fn:substring(noti.file1,5,lh) }" />
						<a href="${path1 }/${filepath1 }/${file1 }" download>${download }</a>
					</td>
				</tr>
				</c:if>
				<tr>
					<th>읽은 횟수</th>
					<td>${noti.readcnt }</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-group">
			<a href="${path1 }/NoticeList.do" class="btn btn-primary">글 목록</a>
			<a href="${path1 }/InsertNotice.do" class="btn btn-primary">글 등록</a>
			<%-- 로그인한 사람의 이름이나 작성자이거나 관리자와 같은 경우 편집 및 삭제 가능 --%>
			<a href="${path1 }/UpdateNotice.do?idx=${noti.idx }" class="btn btn-primary">글 변경</a>
			<a href="${path1 }/DelNotice.do?idx=${noti.idx }" class="btn btn-primary">글 삭제</a>
		</div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>