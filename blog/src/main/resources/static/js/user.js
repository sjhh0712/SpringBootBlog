let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // function을 사용하지 않는 이유 : this를 바인딩하기 위함
			this.save();
		});
	},
	
	save:function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		//console.log(data);
		
		// ajax호출시 default는 비동기 호출(회원가입 실행이 되고있는 와중에 다른 로직 수행 가능.)
		// ajax통신을 이용해서 3개의 data를 json으로 변환하여 insert요청 
		// ajax가 통신 성공 후 json을 리턴해주면 자동으로 java 오브젝트로 변환해줌..
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), // json은 http body에 담김
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 json형식의 문자열로 응답이 오면 javascript 오브젝트로 변환 -> 근데 안적어도 스프링에서 알아서 파싱한데..
		}).done(function(res){
			// 응답이 정상일 경우
			alert("회원가입이 완료되었습니다.");
			//console.log(res);
			location.href = "/blog";
		}).fail(function(){
			// 응답이 실패일 경우
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();