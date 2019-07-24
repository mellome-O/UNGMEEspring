<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxName" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resource/css/inc/header.css">
<link rel="stylesheet" type="text/css" href="${ctxName}/resource/css/reset.css">

</head>
<body>
	<div>

		<a href="/index"><img
			src="${ctxName}/resource/images/ummo/logo.jpg"></a>
r
	</div>
	<div class="member-state">
		<ul>

		<security:authorize access="hasRole('ADMIN')">
		웅쏭만 볼 수 있는 내용
		</security:authorize>
			
			<security:authorize access="isAnonymous()">
			<li><a href="${ctxName}/guest/login">로그인</a></li>			
			</security:authorize>
			<li><a href="${ctxName}/guest/signup">회원가입</a></li>
			<li><a href="${ctxName}/couple/page/index">커플페이지</a></li>
			<li><a href="${ctxName}/couple/info/edit">정보수정</a></li>
			<security:authorize access="isAuthenticated()">
			<li><a href="${ctxName}/member/logout">로그아웃</a></li>
				<form action="${ctxName}/member/logout" method="post">
					<input type="submit" value="로그아웃">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>			
			</security:authorize>

			<li><a href="${ctxName}/admin/index">관리자페이지</a></li>
			<li><a href="${ctxName}/member/withdraw">회원탈퇴</a></li>
		</ul>
	</div>
	<nav>
		<ul>
			<li><a href="${ctxName}/course/list?type=who">CourseWho</a></li>
			<li><a href="${ctxName}/course/list?type=what">CourseWhat</a></li>
			<li><a href="${ctxName}/course/list?type=where">CourseWhere</a></li>
			<li><a href="${ctxName}/course/list?type=search">CourseSearch</a></li>
		</ul>
	</nav>
	<div id="weather">
		<a href="">날씨API</a><img
			src="${ctxName}/resource/images/img/2x/baseline_cloud_black_18dp.png">
	</div>
	<div>현재 서울은 개맑음</div>

</body>
</html>