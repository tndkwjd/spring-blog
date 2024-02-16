let index = {
    init: function(){
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		 $("#btn-delete").on("click", () => {
	        this.deleteById();
	    });
		 
		 $("#btn-update").on("click", () => {
			this.update();
		});
		 
		 $("#btn-reply-save").on("click", () => {
			this.replySave();
		});
		
		 
	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		 if (data.title.trim() === "") {
			    Swal.fire({
			      icon: 'warning',
			      title: '제목을 입력해주세요.',
			      showConfirmButton: false,
			      timer: 1500
			    });
			    return false;
			    
			  } else if (data.content.trim() === "") {
				    Swal.fire({
					      icon: 'warning',
					      title: '내용을 입력해주세요.',
					      showConfirmButton: false,
					      timer: 1500
					    });
					    return false;
					  } 
		
		 $.ajax({
			    type: "POST",
			    url: "/api/board",
			    data: JSON.stringify(data),
			    contentType: "application/json; charset=utf-8",
			  }).done(function(resp){
			    Swal.fire({
			      icon: 'success',
			      title: '글쓰기가 완료되었습니다!',
			      showConfirmButton: true,
			    }).then(function(){

			      location.href = "/";
			    });
			  }).fail(function(error){
			    alert(Json.stringify(error));
			  });
			},
	
	deleteById: function(){
		
		$.ajax({
		    type: "DELETE",
		    url: "/api/board/"+id,
		    dataType: "json"
		  }).done(function(resp){
		    Swal.fire({
		      icon: 'success',
		      title: '삭제가 완료되었습니다!',
		      showConfirmButton: true,
		    }).then(function(){
		      location.href = "/";
		    });
		  }).fail(function(error){
		    alert(Json.stringify(error));
		  });
		},
	

	update: function(){
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
		    type: "PUT",
		    url: "/api/board/"+id,
		    data: JSON.stringify(data),
		    contentType: "application/json; charset=utf-8",
		  }).done(function(resp){
		    Swal.fire({
		      icon: 'success',
		      title: '수정이 완료되었습니다!',
		      showConfirmButton: true,
		    }).then(function(){
		      location.href = "/";
		    });
		  }).fail(function(error){
		    alert(Json.stringify(error));
		  });
		},
	
	replySave: function(){
		let data = {
			content: $("#reply-content").val()
		};
		
		let boardId = $("#boardId").val();
		
		  if (data.content.trim() === "") {
		    Swal.fire({
		      icon: 'warning',
		      title: '댓글 내용을 입력해주세요.',
		      showConfirmButton: false,
		      timer: 1500
		    });
		    return false;
		  }

		  $.ajax({
			    type: "POST",
			    url: `/api/board/${boardId}/reply`,
			    data: JSON.stringify(data),
			    contentType: "application/json; charset=utf-8",
			  }).done(function(resp){
			    Swal.fire({
			      icon: 'success',
			      title: '댓글 작성이 완료되었습니다!',
			      showConfirmButton: true,
			    }).then(function(){
			      location.href = `/board/${boardId}`;
			    });
			  }).fail(function(error){
			    alert(JSON.stringify(error));
			  });
			},
			
	 replyDelete: function(boardId, replyId){
		 
		 	let data = {
					content: $("#reply-content").val()
				};
		 
				$.ajax({
				    type: "DELETE",
				    url: `/api/board/${boardId}/reply/${replyId}`,
				    dataType: "json"
				  }).done(function(resp){
				    Swal.fire({
				      icon: 'success',
				      title: '삭제가 완료되었습니다!',
				      showConfirmButton: true,
				    }).then(function(){
				    	 location.href = `/board/${boardId}`;
				    });
				  }).fail(function(error){
				    alert(Json.stringify(error));
				  });
	   },
			
	
			
	
}

index.init();