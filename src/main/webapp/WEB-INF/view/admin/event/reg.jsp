<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxName" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${ctxName}/resource/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${ctxName}/resource/css/admin/event/reg.css">
<%-- <script src="${ctxName}/resource/js/admin/event.js"></script> --%>
<%-- <script src="${ctxName}/resource/lib/SmartEditor/js/HuskyEZCreator.js"></script> --%>
<script src="${ctxName}/resource/lib/neweditor/editor.js"></script>
<script src="${ctxName}/resource/lib/neweditor/util.js"></script>
<script>
window.addEventListener("load",function(){
	var editor = new Editor("../../resource/lib/neweditor", ".textarea", "/ungmeespring/upload");	
	/*에디터 하나 더 추가하고 싶을 때 */
	/* var editor2 = new Editor("../../resource/lib/editor", ".textarea2"); */
});

</script>
<title>관리자 이벤트  REG 페이지</title>
</head>
<headers>
  < frame-options policy="SAMEORIGIN" />
  <hsts disable="true"/>
</headers>
	<main>
	<input type="hidden" name="ctx-name" value="${ctxName}" class="ctx-nam
	e">
	<form action="reg?${_csrf.parameterName}=${_csrf.token}" method="post"
		enctype="multipart/form-data">
		<div class="regform">
			
				<select name="category">
					<option value="공지사항">공지사항</option>
					<option value="사내이벤트">사내이벤트</option>
					<option value="외부이벤트">외부이벤트</option>
				</select>
			
			<div>${n.regDate}</div>
			<div>${writer}</div>
			<div class="file"><input type="file" name="file" multiple="multiple"></div>
			<div class="content">
				<textarea class="textarea" name="content" id="content"></textarea>
				<%--  <input name="content" placeholder="내용은 입력안하려구? >3<"> --%>
			</div>
			<div>
			<input type="button" onclick="pasteHTML();" value="본문에 내용 넣기" />
			<input type="button" onclick="showHTML();" value="본문 내용 가져오기" />
			<input type="button" onclick="submitContents(this);" value="서버로 내용 전송" />
			<input type="button" onclick="setDefaultFont();" value="기본 폰트 지정하기 (궁서_24)" />
		</div>	
			<div class="button-box">
				<input type="submit" value="저장">
				<a href="list">취소</a>
			</div>
			
		</div>
	</form>
</html>
