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
<title>상품 판매 목록</title>
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
		<h2>상품 판매 목록</h2>
		<hr>
		<fmt:setLocale value="ko_kr" />
		<table class="table">
			<thead>
				<tr><th>연번</th><th>구매자</th><th>상품</th><th>구매가격</th><th>구매일</th><th>상태</th></tr>
			</thead>
			<tbody>
				<c:forEach var="sale" items="${sList }" varStatus="status">
				<tr>
					<td><span title="주문 번호 : ${sale.o_code }" style="cursor:pointer; display:inline-block;">${status.count }</span></td>
					<td>
						<span title="구매자 아이디 : ${sale.id }" style="cursor:pointer; display:inline-block;">${sale.username }</span>
					</td>
					<td>
						<span title="제품 코드 : ${sale.p_code }" style="cursor:pointer; display:inline-block;">${sale.pname }</span>
					</td>
					<td>
						<span title="구매 수량 : ${sale.amount }" style="cursor:pointer; display:inline-block;">
							<fmt:formatNumber value="${sale.price }" pattern="#,##0 원"/>
						</span>
					</td>
					<td>
						<fmt:parseDate value="${sale.odate }" var="odate" pattern="yyyy-MM-dd HH:mm:ss" />
						<span style="display:inline-block;"><fmt:formatDate value="${odate }" pattern="yyyy년 MM월 dd일" /></span>
					</td>
					<td>
						<span style="display:inline-block; width:100px;" title="판매 완료된 제품은 반품이 불가능합니다.">${sale.ostate }</span> &nbsp; &nbsp; &nbsp;
						<c:if test="${sale.ostate=='배송전' }">
							<a href="${path1 }/SurveyLoad.do?o_code=${sale.o_code }" class="btn btn-primary">배송 처리</a>
						</c:if>
						<c:if test="${sale.ostate=='배송중' || sale.ostate=='배송완료'}">
							<span class="d_name">배송 회사 : ${sale.d_name },</span> &nbsp; &nbsp; 
							<span class="d_name">배송 코드 : ${sale.d_code }</span>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty sList }">
				<tr>
					<td colspan="4">판매 내역이 존재하지 않습니다.</td>
				</tr>
				</c:if>	
			</tbody>
		</table>
		<div class="btn-group">
			<a href="javascript:history.go(-1)" class="btn btn-danger">뒤로 가기</a>
		</div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>