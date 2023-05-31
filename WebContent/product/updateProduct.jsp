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
<title>제품 정보 수정 하기</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>제품 정보 수정</h2>
		<p>${msg }</p>
		<form action="${path1 }/UpdateProductPro.do" method="POST" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th>
							<label for="cate1">상품 분류(상품분류코드)</label>
						</th>
						<td>
							<span id="p_codetxt">${catename } (${pro.cate })</span>
						</td>						
					</tr>
					<tr>
						<th><label for="p_name">상품명</label></th>
						<td>
							<input type="text" name="p_name" id="p_name" value="${pro.p_name }" title="40자 내로 작성" placeholder="40자 내로 작성" class="form-control" required>
							<input type="hidden" name="p_code" id="p_code" value="${pro.p_code }">
						</td>
					</tr>
					<tr>
						<th><label for="p_size">규격</label></th>
						<td>
							<input type="text" name="p_size" id="p_size" value="${pro.p_size }" title="100자 내로 작성" placeholder="100자 내로 작성" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="p_com">상품 설명</label></th>
						<td>
							<textarea rows="5" cols="100" name="p_com" id="p_com" maxlength="500" title="500자 내로 작성" class="form-control">${pro.p_com }</textarea>
						</td>
					</tr>
					<tr>
						<th><label for="price">가격</label></th>
						<td>
							<input type="number" name="price" id="price" value="${pro.price }" min="0" max="5000000" step="100" title="0~5000000" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="amount">수량</label></th>
						<td>
							<input type="number" name="amount" id="amount" value="${pro.amount }" min="1" max="500" title="1~500" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="picture">상품 이미지 변경</label></th>
						<td>
							<label for="picture">이미지 1</label>
							<p id="picAttac1"><img src='${path1 }/product/${pro.picture }' alt="${pro.p_name }"/></p>
							<p></p>
							<input type="file" accept="image/*" name="picture" id="picture" class="form-control file"><br>
							<input type="hidden" name="ori_picture" id="ori_picture" value="${pro.pic1 }">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="상품 정보 수정" class="btn btn-danger">
							<a href="${path1 }/AdminProductList.do" class="btn btn-primary">상품 목록</a>				
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<script>
		$(document).ready(function(){
			$("#p_com").click(function(){
				if($(this).text()=="500자 이내"){
					$(this).text("");
				}
			});
			$(".file").change(function(){
				var tar = $(this).index();
				if($(this).val()!=""){
					$(this).prev("p").html("<strong>이미지 첨부 성공</strong>");
				}
			});
		});
	</script>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>