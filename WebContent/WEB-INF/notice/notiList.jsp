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
<title>메인 페이지</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>공지사항</h2>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
			</thead>
			<tbody>
				<c:forEach var="noti" items="${notiList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><a href="${path1 }/GetNotice.do?idx=${noti.idx }">${noti.title }</a></td>
					<td>${noti.author }</td>
					<td>
						<fmt:parseDate value="${noti.resdate }" var="resdate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${resdate }" pattern="yyyy년 MM월 dd일" />
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty notiList }">
				<tr>
					<td colspan="4">해당 공지사항이 존재하지 않습니다.</td>
				</tr>
				</c:if>	
			</tbody>
		</table>
		<div class="btn-group">
			<a href="${path1 }/InsertNotice.do" class="btn btn-primary">글 등록</a>
		</div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>