<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>회원 직권 등록</title>
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
		<h2 class="title">회원 직권 등록</h2>
		<form name="frm1" id="frm1" action="${path1 }/InsertUserPro.do" method="post" onsubmit="return joinCheck(this)">
			<table class="table">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<div class="form-row">
								<input type="text" name="id" id="id" placeholder="영문소문자 및 숫자를 혼용하여 15글자 이내로 아이디 입력" class="form-control" pattern="^[a-z0-9]{8,16}" maxlength="15" style="width:80%;display:inline-block;" autofocus required />
								<input type="button" class="btn btn-primary" value="아이디 중복 확인" onclick="idCheck()">
								<input type="hidden" name="idck" id="idck" value="no">
							</div>
							<div>
								<c:if test="${empty qid }">
								<p id="msg" style="padding-left:0.5rem">아직 아이디 중복 체크를 하지 않으셨습니다.</p>
								</c:if>
								<c:if test="${not empty qid }">
								<p id="msg" style="padding-left:0.5rem">아이디 중복 체크후 수정하였습니다.</p>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" name="pw" id="pw" placeholder="비밀번호 입력" class="form-control" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$" maxlength="16" required />
							<p>비밀번호는 최소 8자리에서 최대 16자리까지 숫자, 영문, 특수문자 각 1개 이상 포함되어야 함</p>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="pw2" id="pw2" placeholder="비밀번호  확인 입력" class="form-control" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$" maxlength="16" required /></td>
					</tr>
					<tr>
						<th>회원이름</th>
						<td><input type="text" name="name" id="name" placeholder="이름 입력" class="form-control" required /></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="email" id="email" placeholder="이메일 입력" class="form-control" required></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="tel" name="tel" id="tel" maxlength="11" placeholder="전화번호 숫자만 입력 00000000000" class="form-control" required></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="address1" id="address1" placeholder="기본 주소 입력" class="form-control" required /><br>
						<input type="text" name="address2" id="address2" placeholder="상세 주소 입력" class="form-control" required /><br>
						<input type="text" name="postcode" id="postcode" style="width:160px;float:left;margin-right:20px;" placeholder="우편번호" class="form-control">
						<button type="button" id="post_btn" onclick="findAddr()" class="btn btn-primary">우편번호 검색</button>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="회원등록" class="btn btn-primary"/>
							<input type="reset" value="취소" class="btn btn-primary"/>
							<a href="${path1 }/MemberList.do" class="btn btn-primary">회원 목록</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<script>
		function idCheck(){
			if($("#id").val()==""){
				alert("아이디를 입력하시기 바랍니다.");
				$("#id").focus();
				return;
			}
			var params = { id:$("#id").val() }
			$.ajax({
				url:"${path1 }/IdCheck.do",
				type:"post",
				dataType:"json",
				data:params,
				success:function(result){
					//console.log(result);
					var idChk = result.result;
					if(idChk==false){
						$("#idck").val("no");
						$("#msg").html("<strong>사용할 수 없는 아이디 입니다.</strong>")
					} else if(idChk==true){
						$("#idck").val("yes");
						$("#id").attr("readonly","true");
						$("#msg").html("<strong>사용 가능한 아이디 입니다.</strong>");
						$("#pw").focus();
					} else {
						$("#msg").html("<strong>아이디가 확인되지 않았습니다. 다시 시도 바랍니다.</strong>")
					}
				}
			});
		}
		function joinCheck(f){
			if(f.pw.value!=f.pw2.value){
				alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
				f.pw.focus();
				return false;
			}
			if(f.idck.value!="yes"){
				alert("아이디 중복 체크를 하지 않으셨습니다.");
				return false;
			}
		}
		function findAddr(){
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
	</section>	
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>