let index = {
    init: function(){
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-login").on("click", () => {
			this.login();
		});
	},
	
	save: function(){
		// alert('user의 save함수 호출 됨')
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val(),
			email: $("#email").val()
			
		};
		
		// console.log(data);
		
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			// location.href = "/blog";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
	}, login: function(){
		// alert('user의 login 함수 호출 됨')
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val()
		};
		
		
		$.ajax({
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
	}
	
}

index.init();