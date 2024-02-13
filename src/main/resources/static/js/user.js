let index = {
    init: function(){
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		 $("#btn-update").on("click", () => {
			this.update();
		});
		 
	},
	
	save: function(){
		// alert('user의 save함수 호출 됨')
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
			
		};
		
		// console.log(data);
		
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
		}, 
		
		update: function(){

			let data = {
				id: $("#id").val(),
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			};
			
			$.ajax({
				type: "PUT",
				url: "/user",
				data: JSON.stringify(data), 
				contentType: "application/json; charset=utf-8",
			}).done(function(resp){
				alert("정보 수정이 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(Json.stringify(error));
			});  
		
		},
	
}

index.init();