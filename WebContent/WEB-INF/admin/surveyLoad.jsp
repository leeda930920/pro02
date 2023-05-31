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
<title>배송 처리</title>
<style>
.container-fluid { width:1280px; }
</style>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px;">
	<div class="container-fluid">
		<h2>판매/배송 처리</h2>
		<p>${msg }</p>
		<form action="${path1 }/SurveyPro.do" method="POST">
			<h3>판매/배송 처리</h3>
			<table class="table">
				<tbody>
					<tr>
						<th>상품명</th>
						<td>${sale.p_name }
							<input type="hidden" id="o_code" name="o_code" value="${sale.o_code }">
						</td>
					</tr>
					<tr>
						<th>주문자</th>
						<td>${sale.username }</td>
					</tr>
					<tr>
						<th>판매 가격</th>
						<td>${sale.price }
					</tr>
					<tr>
						<th><label for="d_name">배송사</label></th>
						<td>
							<span>${sale.d_name }</span><br>
							<select name="d_name" id="d_name" required>
								<option value="">선택안함</option>
								<option value="CJ대한통운">CJ대한통운</option>
								<option value="롯데택배">롯데택배</option>
								<option value="우체국택배">우체국택배</option>
								<option value="로젠택배">로젠택배</option>
								<option value="한진택배">한진택배</option>
								<option value="CU 편의점택배">CU 편의점택배</option>
								<option value="EMS 택배">EMS 택배</option>
								<option value="경동택배">경동택배</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="d_code">배송코드</label></th>
						<td><input type="text" name="d_code" value="${sale.d_code }" id="d_code" required></td>
					</tr>
					<tr>
						<th><label for="d_state">배송상태</label></th>
						<td>
							<span>${sale.d_state }</span><br>
							<select name="d_state" id="d_state" required>
								<option value="배송전">배송전</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
								<option value="구매완료">구매완료</option>
								<option value="주문취소">주문취소</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn-group">
				<input type="submit" value="배송처리" class="btn btn-danger">
				<a href="${path1 }/AdminCancle.do?o_code=${sale.o_code} ">주문 취소 처리</a>
				<a href="javascript:history.go(-1)" class="btn btn-primary">뒤로 가기</a>				
			</div>
		</form>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>