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
<title>상품 목록</title>
<style>
.container-fluid { width:1280px; }
.thumbnail { height:480px; }
.comment { width:auto; height:60px; overflow: hidden;  text-overflow: ellipsis; 
 display: -webkit-box;  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical; }
.thumb_box { width:145px; margin:24px auto; margin-bottom:10px; height:auto; overflow:hidden;
padding-top:5px; padding-bottom:5px; 
border:1px solid #e0e0f0; text-align:center; }
.thumb_box::after { content:""; display:block; clear:both; }
.thumb_box img { width:auto; height:193px; }
.pro_title { overflow:hidden; white-space:nowrap; text-overflow:ellipsis; }  
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>상품 - ${cateMap.catename }</h2>
				<hr>
		<c:if test="${sid.equals('admin') }">
			<div class="btn-group">
				<a href="${path1 }/AdminCateProductList.do?cate=01" role="button" class="btn btn-primary">FURNITURE </a>
				<a href="${path1 }/AdminCateProductList.do?cate=02" role="button" class="btn btn-primary">LIGHT </a>
				<a href="${path1 }/AdminCateProductList.do?cate=03" role="button" class="btn btn-primary">STORAGE </a>
				<a href="${path1 }/AdminCateProductList.do?cate=04" role="button" class="btn btn-primary">FABRIC </a>
				<a href="${path1 }/AdminCateProductList.do?cate=05" role="button" class="btn btn-primary">DECO </a>
				<a href="${path1 }/SoldoutProductList.do" role="button" class="btn btn-primary">품절</a>
			</div>
			<hr>
		</c:if>
		<fmt:setLocale value="ko_kr" />
		<article class="row">
			<c:forEach var="pro" items="${proList }" varStatus="status">
			<div class="col-sm-6 col-md-4 col-lg-3">
				<div class="thumbnail">
					<div class="thumb_box">
						<img src='${path1 }/product/${pro.picture }' alt="${pro.p_name }"/>
					</div>
					<div class="caption">
						<a href="${path1 }/ProductDetail.do?p_code=${pro.p_code}">
							<h3 class="pro_title"><strong>${pro.p_name }</strong></h3>
							<p class="comment"><strong>상품 설명</strong> :<br>${pro.p_com }</p>
							<p><strong>수량</strong> :
								<c:if test="${pro.amount<=0 }"><span>품절</span></c:if>
								<c:if test="${pro.amount>0 }">${pro.amount }</c:if>
							</p>
							<p><strong>가격</strong> : <fmt:formatNumber value="${pro.price }" type="currency" /></p>
						</a>
						<div class="btn-group">
							<c:if test="${pro.amount>0 && !sid.equals('admin')}">
								<a href="${path1 }/InsertBasket.do?p_code=${pro.p_code}" class="btn btn-default" role="button">장바구니 담기</a>
							</c:if>
							<c:if test="${sid.equals('admin') }">
								<a href="${path1 }/ReceiptProduct.do?p_code=${pro.p_code }" class="btn btn-primary" role="button">상품 입고</a>
								<a href="${path1 }/UpdateProduct.do?p_code=${pro.p_code }" class="btn btn-success" role="button">상품 수정</a>
								<a href="${path1 }/DeleteProduct.do?p_code=${pro.p_code }" class="btn btn-warning" role="button">상품 삭제</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</article>
		<c:if test="${empty proList }">
		<div class="container">
			<h3>해당 상품이 존재하지 않습니다.</h3>
		</div>
		</c:if>	
		<c:if test="${sid.equals('admin') }">
		<div class="btn-group">
			<a href="${path1 }/InsertProduct.do" class="btn btn-danger">상품 등록</a>
		</div>
		</c:if>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>