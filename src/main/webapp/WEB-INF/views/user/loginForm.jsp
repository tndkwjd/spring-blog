<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="POST">
		<div class="form-group">
			<label for="username">이름:</label> 
			<input type="text" name="username" class="form-control" id="username">
		</div>

		<div class="form-group">
			<label for="password">비밀번호</label> 
			<input type="password" name="password" class="form-control" id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> 
			<input name="remember" class="form-check-input" id="remember" type="checkbox"> 로그인 상태 유지
			</label>
		</div>
	<button id="btn-login" class="btn btn-secondary">로그인</button>
	<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}"><img src="/image/kakao_login_button.png" height="38px"></a>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
