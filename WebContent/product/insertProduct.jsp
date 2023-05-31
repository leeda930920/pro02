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
<title>제품 등록 하기</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>제품 등록</h2>
		<p>${msg }</p>
		<form action="${path1 }/InsertProductPro.do" method="POST" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th>
							<label for="cate1">상품 분류</label><br>
							<span id="p_codetxt"></span>
						</th>
						<td>
							대분류 : 
							<select id="cate1" name="cate1" class="form-control">
								<option value="">선택안함</option>
								<c:forEach items="${cateList }" var="cate1">
								<option value="${cate1.ct }">${cate1.categroup }</option>
								</c:forEach>	
							</select>
							소분류 : 
							<select id="cate" name="cate" class="form-control">
								<option value="">선택안함</option>
							</select><br>
							<input type="hidden" id="p_code" name="p_code" value="">
							<input type="hidden" id="p_codeck" name="p_codeck" value="no">
							<input type="button" value="상품 코드 발급" class="btn btn-primary" onclick="productCodeGenerator()">
						</td>
					</tr>
					<tr>
						<th><label for="p_name">상품명</label></th>
						<td>
							<input type="text" name="p_name" id="p_name" title="40자 내로 작성" placeholder="40자 내로 작성" class="form-control" required>
						</td>
					</tr>
					<tr>
						<th><label for="p_size">규격</label></th>
						<td>
							<input type="text" name="p_size" id="p_size" title="100자 내로 작성" placeholder="100자 내로 작성" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="p_com">상품 설명</label></th>
						<td>
							<textarea rows="5" cols="100" name="p_com" id="p_com" maxlength="500" title="500자 내로 작성" class="form-control">500자 이내</textarea>
						</td>
					</tr>
					<tr>
						<th><label for="price">가격</label></th>
						<td>
							<input type="number" name="price" id="price" value="1000" min="0" max="5000000" step="100" title="0~5000000" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="amount">수량</label></th>
						<td>
							<input type="number" name="amount" id="amount" value="1" min="0" max="500" title="1~500" class="form-control">
						</td>
					</tr>
					<tr>
						<th><label for="picture">상품 이미지 첨부</label></th>
						<td>
							이미지 : <p id="picAttac1"></p><input type="file" accept="image/*" name="picture" id="picture" class="form-control file"><br>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="제품 등록" class="btn btn-danger">
							<a href="${path1 }/AdminProductList.do" class="btn btn-primary">글 목록</a>				
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
			$("#cate1").change(function(){
				if($("#cate1").val()==""){
					alert("대분류 카테고리를 선택하시기 바랍니다.");
					$("#cate1").focus();
					return;
				}
				var params = { cate1:$("#cate1").val() }
				$.ajax({
					url:"${path1 }/CategoryLoading.do",
					type:"post",
					dataType:"json",
					encType:'UTF-8',
					data:params,
					success:function(result){
						console.log(result);
						var ctList = result.ctList;
						var msg = result.msg;
						$("#cate").empty();
						$("#cate").append("<option value=''>선택안함</option>");
						for(var c in ctList){
							$("#cate").append("<option value='"+ctList[c]["cate"]+"'>"+ctList[c]["catename"]+"</option>");
						}
					}
				});				
			});
			
		});
		function productCodeGenerator(){
			if($("#cate1").val()=="" || $("#cate").val()==""){
				alert("대분류/소분류 카테고리를 선택하시기 바랍니다.");
				$("#cate1").focus();
				return;
			}
			var params = { cate:$("#cate").val() }
			$.ajax({
				url:"${path1 }/ProductCodeGenerator.do",
				type:"post",
				dataType:"json",
				data:params,
				encType:"UTF-8",
				success:function(result){
					console.log(result);
					var msg = result.msg;
					var proCode = result.p_code;
					$("#p_codeck").val("yes");
					$("#p_code").val(proCode);
					$("#p_codetxt").html("("+proCode+")");
					$("#msg").html("<strong>상품 코드가 발급되었습니다.</strong>");
					$("#p_name").focus();
				}
			});
		}
		function productCheck(f){
			if(f.p_codeck.value!="yes"){
				alert("상품코드를 생성하지 않으셨습니다.");
				return false;
			}
		}
	</script>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>