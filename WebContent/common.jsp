<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="common_path" value="${pageContext.request.contextPath }" />  
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 문서 정보 등록 -->
<!-- 검색엔진최적화(Search Engine Optimization) : SEO -->
<meta name="subject" content="오마이집">
<meta name="keywords" content="오 마이 집, 리빙관련 전 제품 구매, 가구, 수납·정리, 주방용품, 조명 등 ">
<meta name="description" content="오마이집(ohmyzip)은 나의 공간에 채워질 작고 큰 모든 제품들을 한번에!">
<meta name="author" content="Young">

<!-- 파비콘(favicon) 설정 -->
<link rel="shortcut icon" href="${common_path }/img/favicon.ico">

<!-- 오픈그래프 설정 -->
<meta name="og:site_name" content="오마이집">
<meta name="og:title" content="오마이집">
<meta name="og:description" content="오마이집은 리빙에 관련된 가구, 수납·정리, 주방용품, 조명 등을 판매합니다.">
<meta name="og:url" content="https://www.ohmyzip.com">
<meta name="og:image" content="${common_path }/img/favicon.png">  
      
<!-- 기본 폰트 및 초기화 로딩 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR&display=swap" rel="stylesheet">

<!-- 스타일 초기화 -->
<!-- reset.css나 normalize.css를 CDN 또는 다운로드 받아 link로 연결 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>
<style>
/* 마우스 롤 오버시 스타일 */
.dropdown-toggle::after { transition: transform 0.15s linear;}
.show.dropdown .dropdown-toggle::after {transform: translateY(3px);}
.dropdown-menu {margin-top: 0;} /* hover 메뉴때문에 넣음 */
</style>
<script>
/* 마우스 롤 오버시 스크립트 */
$(document).ready(function(){
	var $dropdown = $(".navbar-nav .dropdown");
	var $dropdownToggle = $(".dropdown-toggle");
	var $dropdownMenu = $(".dropdown-menu");
	var showClass = "show";
	$(window).on("load resize", function() {
		if (this.matchMedia("(min-width: 768px)").matches) {
			$dropdown.hover(
				function() {
					var $this = $(this);
					$this.addClass(showClass);
					$this.find($dropdownToggle).attr("aria-expanded", "true");
					$this.find($dropdownMenu).addClass(showClass);
				},
				function() {
					var $this = $(this);
					$this.removeClass(showClass);
					$this.find($dropdownToggle).attr("aria-expanded", "false");
					$this.find($dropdownMenu).removeClass(showClass);
				}
			);
		} else {
			$dropdown.off("mouseenter mouseleave");
		}
	});
});
</script>