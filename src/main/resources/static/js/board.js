let index = {
    init: function(){
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		 $("#btn-search").on("click", () => {
	            this.search();
	    });
		 
	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		// console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
	
	}
}

index.init();