<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<input type="text" name="title" class="form-control" id="title">
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
		<button id="btn-save" class="btn btn-secondary">등록</button>
		<button class="btn btn-success" onclick="history.back()">목록</button>
</div>

    <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
