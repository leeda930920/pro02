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
<title>상품 입고 하기</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h3>상품 > <a href="${path1 }/ProductList.do?cate=${pro.cate}">${cateMap.catename }</a> > ${pro.p_name }</h3>
		<br><hr><br>
		<fmt:setLocale value="ko_kr" />
		<form action="ReceiptProductPro.do" method="post">
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">
							<img src='${path1 }/product/${pro.picture }' alt="${pro.p_name }"/>
						</td>
					</tr>
					<tr>
						<th>상품명(상품코드)</th>
						<td>
							${pro.p_name }(${pro.p_code })
							<input type="hidden" name="p_code" id="p_code" value="${pro.p_code }">
						</td>
					</tr>
					<tr>
						<th>규격</th>
						<td>${pro.p_size }</td>
					</tr>
					<tr>
						<th>설명</th>
						<td>${pro.p_com }</td>
					</tr>
					<tr>
						<th>현재 가격</th>
						<td>${pro.price }</td>
					</tr>
					<tr>
						<th><label for="price">입고 가격</label></th>
						<td><input type="number" name="price" id="price" value="${pro.price }" min="0" max="5000000" step="100" title="0~5000000" class="form-control"></td>
					</tr>
					<tr>
						<th>현재 수량</th>
						<td>${pro.amount }</td>
					</tr>
					<tr>
						<th><label for="amount">입고할 수량</label></th>
						<td><input type="number" name="amount" id="amount" value="1" min="1" max="500" title="1~500" class="form-control"></td>
					</tr>
						<td colspan="2">
							<div class="btn-group">
								<c:if test="${sid.equals('admin') }">
									<input type="submit" class="btn btn-primary" role="button" value="입고">
									<a href="${path1 }/InsertProduct.do" class="btn btn-danger" role="button">등록</a>
									<a href="${path1 }/UpdateProduct.do?p_code=${pro.p_code }" class="btn btn-success" role="button">수정</a>
									<a href="${path1 }/DeleteProduct.do?p_code=${pro.p_code }" class="btn btn-warning" role="button">삭제</a>
									<a href="${path1 }/ProductList.do?cate=${pro.cate}" class="btn btn-primary" role="button">목록</a>
								</c:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>