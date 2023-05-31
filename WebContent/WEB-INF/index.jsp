<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common.jsp" %>
<title>메인 페이지</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<section class="page">
		<div class="container-fluid">
			<h2 class="title">메인 페이지 콘텐츠</h2>
		</div>
	</section>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>