<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="userName">이름:</label> <input type="text"
				class="form-control" id="userName">
		</div>

		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password"
				class="form-control" id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input
				class="form-check-input" type="checkbox"> 로그인 상태 유지
			</label>
		</div>

		<button type="submit" class="btn btn-secondary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
