<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>
<div class="container">
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="#" class="btn btn-secondary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<form id="searchForm">
    <div class="row mt-2">
        <div class="col">
            <div class="input-group">
                <select id="searchType" class="form-control col-2"> 
                    <option value="">전체</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" class="form-control" name="keyword" id="keyword" placeholder="검색어를 입력하세요">
                <div class="input-group-append">
                    <button id="btn-search" onclick="findAll();" class="btn btn-secondary">검색</button>
                </div>
            </div>
        </div>
    </div>
</form>
	<div class="mt-2"></div>
	<ul class="pagination justify-content-center">
		<c:if test="${startPage > 1}">
			<li class="page-item"><a class="page-link"
				href="?page=${startPage - 1}">Previous</a></li>
		</c:if>

		<c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
			<c:choose>
				<c:when test="${pageNum eq boards.number}">
					<li class="page-item active"><span class="page-link">${pageNum}</span>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=${pageNum}">${pageNum}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${endPage < boards.totalPages}">
			<li class="page-item"><a class="page-link"
				href="?page=${endPage + 1}">Next</a></li>
		</c:if>
	</ul>
</div>

<%@ include file="layout/footer.jsp"%>
