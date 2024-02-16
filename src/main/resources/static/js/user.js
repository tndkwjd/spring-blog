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
		    type: "POST",
		    url: "/auth/joinProc",
		    data: JSON.stringify(data),
		    contentType: "application/json; charset=utf-8",
		  }).done(function(resp){
			  if(resp.status === 500){
			  Swal.fire({
				icon: 'error',
			    title: '해당 회원이 존재합니다.',
		      showConfirmButton: true,
			  });
			  } else { 
				  Swal.fire({
				   icon: 'success',
				   title: '회원가입이 완료되었습니다!',
				   showConfirmButton: true,  
		    }).then(function(){
		      location.href = "/";
		    });
			  }
		  }).fail(function(error){
		    alert(JSON.stringify(error));
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
			    Swal.fire({
			      icon: 'success',
			      title: '정보 수정이 완료되었습니다!',
			      showConfirmButton: true,
			    }).then(function(){
			      location.href = "/";
			    });
			  }).fail(function(error){
			    alert(JSON.stringify(error));
			  });
			},
	
}

index.init();