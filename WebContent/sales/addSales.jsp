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
<title>제품 구매</title>
<style>
.container-fluid { width:1280px; }
</style>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px;">
	<div class="container-fluid">
		<h2>제품 구매</h2>
		<p>${msg }</p>
		<form action="${path1 }/AddPayment.do" method="POST" onsubmit="return payCheck(this)">
			<h3>구매 제품 정보</h3>
			<table class="table">
				<tbody>
					<tr>
						<th>상품명</th>
						<td>${pro.p_name }
							<input type="hidden" id="p_name" name="p_name" value="${pro.p_name }">
							<input type="hidden" id="cate" name="cate" value="${pro.cate }">
							<input type="hidden" id="p_code" name="p_code" value="${pro.p_code }">
							<input type="hidden" id="id" name="id" value="${user.id }">
							<input type="hidden" id="name" name="name" value="${user.name }">
							<input type="hidden" id="email" name="email" value="${user.email }">
							<input type="hidden" id="utel" name="utel" value="${user.tel }">
							<input type="hidden" id="uaddr" name="uaddr" value="${user.addr }">
							<c:if test="${!empty b_no}">
								<input type="hidden" id="b_no" name="b_no" value="${b_no }">
							</c:if>
						</td>
					</tr>
					<tr>
						<th>규격</th><td>${pro.p_size }</td>
					</tr>
					<tr>
						<th>상품 설명</th><td>${pro.p_com }</td>
					</tr>
					<tr>
						<th>구매 가격</th><td>${pro.price*1.4 }
						<input type="hidden" id="price" name="price" value="${pro.price*1.4 }"></td>
					</tr>
					<tr>
						<th><label for="amount">수량</label></th>
						<td>
							구매 가능 수량 : <strong>${pro.amount }</strong><br>
							<input type="number" name="amount" id="amount" value="${amount }" min="0" max="${pro.amount }" title="1~500" class="form-control" required>
						</td>
					</tr>
					<tr>
						<th>상품 이미지</th>
						<td><img src='${path1 }/product/${pro.picture }' alt="${pro.p_name }"/></td>
					</tr>
				</tbody>
			</table>
			<hr>
			<h3>배송 정보</h3>
			<table class="table">
				<tbody>
					<tr>
						<th>배송지 주소</th>
						<td>
							<input type="text" name="address1" id="address1" placeholder="기본 주소 입력" class="form-control" required /><br>
							<input type="text" name="address2" id="address2" placeholder="상세 주소 입력" class="form-control" required /><br>
							<input type="text" name="postcode" id="postcode" style="width:160px;float:left;margin-right:20px;" placeholder="우편번호" class="form-control" required>
							<button type="button" id="post_btn" onclick="findAddr()" class="btn btn-primary" style="margin-bottom:36px;">우편번호 검색</button>
						</td>
					</tr>
					<tr>
						<th>받는 사람 연락처</th>
						<td>			
							<input type="tel" name="tel" id="tel" required>
						</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<h3>결제 정보</h3>
			<div class="btn-group" style="margin-bottom:36px;">
				<button type="button" id="pay" class="btn btn-primary">결제하기</button>
			</div>
			<table class="table">
				<tbody>
					<tr>
						<th>결제 수단</th>
						<td>
							<input type="hidden" name="conf" id="p_type">
							<input type="radio" name="p_type" id="p_type1" value="신용카드" class="paytype" checked><label for="p_type1">신용카드</label> &nbsp;
							<input type="radio" name="p_type" id="p_type2" value="체크카드" class="paytype"><label for="p_type2">체크카드</label> &nbsp;
							<input type="radio" name="p_type" id="p_type3" value="계좌이체" class="paytype"><label for="p_type3">계좌이체</label> &nbsp;
						</td>
					</tr>
					<tr>
						<th><label for="paycom" id="paycomLb">결제사</label></th>
						<td>			
							<select name="paycom" id="paycom" class="form-control" required>
								<option value="">선택안함</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="paycom" id="pt_noLb">결제 번호</label></th>
						<td>			
							<input type="text" name="pt_no" id="pt_no" class="form-control" required>
							<input type="hidden" name="payamount" id="payamount">
							<input type="hidden" name="payck" id="payck" value="no">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn-group">
				<input type="submit" value="상품 구매 완료" class="btn btn-danger">
				<a href="javascript:history.go(-1)" class="btn btn-primary">뒤로 가기</a>				
			</div>
		</form>
		<script>
		function findAddr() {
			new daum.Postcode({
				oncomplete: function(data) {
					console.log(data);
					var roadAddr = data.roadAddress;
					var jibunAddr = data.jibunAddress;
					document.getElementById("postcode").value = data.zonecode;
					if(roadAddr !== '') {
						document.getElementById("address1").value = roadAddr;				
					} else if(jibunAddr !== ''){
						document.getElementById("address1").value = jibunAddr;
					}
					document.getElementById("address2").focus();
				}
			}).open();
		}
		</script>
		<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		//결제모듈 연동
		$(document).ready(function(){
			var totalPay=0;
			var proName;
			$("#pay").click(function(){
				proName = $("#p_name").val();
				if($("#amount").val()!="") {
					totalPay = parseInt($("#price").val()) * parseInt($("#amount").val());
				} else {
					alert("구매할 수량을 입력하지 않으셨습니다.");
					$("#amount").focus();
					return;
				}
				alert("결제할 금액 : "+totalPay);
				//상품명_현재시간
	 			var d = new Date();
				var date = d.getFullYear()+''+(d.getMonth()+1)+''+d.getDate()+''+d.getHours()+''+d.getMinutes()+''+d.getSeconds();
				IMP.init('imp31083748'); // 결제 API를 사용하기 위한 코드 입력!
				IMP.request_pay({		//결제 요청
					merchant_uid : $("#p_name").val() + '_' + date, //상점 거래 ID
					name : $("#p_name").val(),				// 결제 명
					amount : totalPay,					// 결제금액
					buyer_email : $("#email").val(), // 구매자 email
					buyer_name : $("#name").val(),				// 구매자 이름
					buyer_tel : $("#utel").val(),		// 구매자 전화번호
					buyer_addr : $("#uaddr").val(),		// 구매자 주소
					buyer_postcode : $("#postcode").val()			// 구매자 우편번호
					
					//지정 가능한 속성
					//https://docs.iamport.kr/sdk/javascript-sdk 참조
					//pg : 결제 프로그램 코드
					//pay_method : 결제 방법
					//escrow : 에스크로 결제창 호출 여부
					//merchant_uid : 상점 거래 ID
					//name : 주문명
					//amount : 결제 금액
					//custom_data : 가맹점 임의 지정 데이터
					//tax_free : 면세공급가액
					//currency : 거래 통화
					//language : 결제창 화면의 언어
					//buyer_name : 주문자명
					//buyer_tel : 주문자 연락처
					//buyer_email : 주문자 이메일
					//buyer_addr : 주문자 주소
					//buyer_postcode : 주문자 우편번호
					//display : 결제 화면 구성 설정
					//display.card_quota : 50,000원 이상 금액 결제시 할부 개월 수 옵션
					//digital : 결제 상품이 컨텐츠인지 여부(휴대폰 소액 결제시 필수)
					//v_bank_due : 가상계좌 입금기한
					//m_redirect_url : 리다이렉션 방식으로 호출된 결제창에서 결제 후 이동 될 주소
					//app_scheme : 모바일 앱 결제 중 앱 복귀를 위한 URL
					//biz_num : 계약된 사업자등록번호 10자리
					
				}, function(rsp){
					if(rsp.success){
						console.log(rsp);
						var msg = '결제가 완료 되었습니다.';
						var r1 = '고유 아이디 : ' +rsp.imp_uid;
						var r2 = '상점 거래 아이디 : ' +rsp.merchant_uid;
						var r3 = '결제 금액 : ' +rsp.paid_amount;
						var r4 = '카드 승인 번호 : '+rsp.apply_num;
						
						//반환 가능한 속성
						//https://docs.iamport.kr/sdk/javascript-sdk 참조
						//rsp.success : 결제 성공 여부
						//rsp.error_code : 결제 실패시 에러 코드
						//rsp.error_msg : 결제 실패시 에러 메시지
						//rsp.imp_uid : 아임포트 고유 결제번호
						//rsp.merchant_uid : 가맹점 고유 주문번호
						//rsp.pay_method : 결제 방법
						//rsp.paid_amount : 결제 금액
						//rsp.status : 결제 상태
						//rsp.name : 주문명
						//rsp.pg_provider : 일반 결제 승인된 PG사
						//rsp.emb_pg_provider : 간편 결제 승인된 PG사 
						//rsp.pg_tid : PG사 거래고유번호
						//rsp.buyer_name : 주문자 이름
						//rsp.buyer_email : 주문자 Email
						//rsp.buyer_tel : 주문자 연락처
						//rsp.buyer_addr : 주문자 주소
						//rsp.buyer_postcode : 주문자 우편번호
						//rsp.custom_data : 가맹점 임의 지정 데이터
						//rsp.paid_at : 결제승인시각
						//rsp.receipt_url : PG사에서 발행되는 거래 매출전표 URL
						//rsp.apply_num : 카드사 승인번호
						//rsp.vbank_num : 가상계좌 입금계좌번호
						//rsp.vbank_name : 가상계좌 은행명
						//rsp.vbank_holder : 가상계좌 예금주
						//rsp.vbank_date : 가상계좌 입금기한
						
						/* 아래 내용은 각주 처리 해제 */
						//$("#payck").val("yes");
						//$("#payamount").val(rsp.paid_amount);
						//$("#paymethod").val(rsp.pay_method);
						//$("#paycom").val(rsp.pg_provider);
						//$("#pt_no").val(rsp.apply_num);
						//alert(msg);
						//$("#paymentResult").html(r1+"<br>"+r2+"<br>"+r3+"<br>"+r4);
					} else{
						//$("#paymentResult").html('결제실패<br>'+'에러내용 : ' +rsp.error_msg);
					}
					//테스트용이므로 실패시에도 그냥 통과시킴 그러므로 실제 사용시에는 아래 내용은 각주 처리하든지 지워야함
					$("#payck").val("yes");
					$("#payamount").val(totalPay);
				});
	 		});
		});
		</script>
		<script>
		$(document).ready(function(){
			var card = ["BC카드", "KB국민카드", "삼성카드", "신한카드", "우리카드", "하나카드", "롯데카드", "현대카드", "NH농협카드"];
			var bank = ["IBK기업은행", "하나은행", "농협은행", "우리은행", "우체국", "새마을금고", "신협", "한국씨티은행", "수협은행", "대구은행", "부산은행", "케이뱅크", "토스뱅크", "카카오뱅크"];
			$("input[name='p_type']").change(function(){
				// 신용카드 결제 선택 시.
				if($("#p_type1:checked").val() == '신용카드'){
					$("#p_type").val("신용카드");
					$('#paycomLb').text("신용카드사 또는 종류");
					$('#pt_noLb').text("신용카드번호");
					$("#paycom").empty();
					$("#paycom").append("<option value=''>선택안함</option>");
					for(var key in card){
						$("#paycom").append("<option value='"+card[key]+"'>"+card[key]+"</option>");
					}
					$('#pt_no').val("");
				}	
				// 체크카드 결제 선택 시.
				else if($("#p_type2:checked").val() == '체크카드'){
					$("#p_type").val("체크카드");
					$('#paycomLb').text("체크카드사 또는 종류");
					$('#pt_noLb').text("체크카드번호");
					$("#paycom").empty();
					$("#paycom").append("<option value=''>선택안함</option>");
					for(var key in card){
						$("#paycom").append("<option value='"+card[key]+"'>"+card[key]+"</option>");
					}
					$('#pt_no').val("");
				}
				// 계좌이체 결제 선택 시.
				else if($("#p_type3:checked").val() == '계좌이체'){
					$("#p_type").val("계좌이체");
					$('#paycomLb').html("입금 은행 : 농협,<br> 예금주 : 더조은책방");
					$('#pt_noLb').html("계좌 번호 :<br> 217-23-25639");
					$("#paycom").empty();
					$("#paycom").append("<option value=''>선택안함</option>");
					for(var key in bank){
						$("#paycom").append("<option value='"+bank[key]+"'>"+bank[key]+"</option>");
					}
					$('#pt_no').val("");
				}
			}).change();
		});
		function payCheck(f){
			var payCk = f.payck.value;
			if(payCk!="yes"){
				alert("아직 결제 전 입니다.");
				return;
			}
		}
		</script>
		<div id="paymentResult"></div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>