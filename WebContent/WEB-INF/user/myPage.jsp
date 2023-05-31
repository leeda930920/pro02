<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>마이 페이지</title>
<style>
.container-fluid { width:1280px; }
.agree_fr { width: 900px; white-space:pre-wrap; margin: 10px auto; 
padding: 24px; border:2px solid #eee; height:600px; overflow-y:auto; }
.title { padding-top:36px; padding-bottom:20px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<section class="container-fluid">
		<h2 class="title">본인 정보</h2>
		<table class="table">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>
						${user.id }	
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						${user.pw }
					</td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td>${user.name }</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${user.email }</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>${user.tel }</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${user.addr }</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${user.regdate }</td>
				</tr>
				<tr>
					<th>회원 포인트</th>
					<td>${user.point }</td>
				</tr>
				<tr>
					<th>방문 횟수</th>
					<td>${user.visited }</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="${path1 }/UpdateUser.do?id=${user.id}" class="btn btn-primary">회원 정보 수정</a>
						<a href="${path1 }/DeleteUser.do?id=${user.id}" class="btn btn-primary">회원 탈퇴</a>
						<a href="javascript:history.go(-1)" class="btn btn-primary">뒤로</a>						
					</td>
				</tr>
			</tbody>
		</table>
	</section>	
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>