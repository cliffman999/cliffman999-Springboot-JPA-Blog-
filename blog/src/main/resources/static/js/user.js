let index = {
	init: function() {
		$("#btn-save").on("click", () => {//function을 사용하지 않는 이유 this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-login").on("click", () => {
			this.login();
		});
	},

	save : function() {
		//alert("user의 save함수 호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);
		//ajax통신을 사용하는 이유 : 웹개 앱에서 하나의 서버만으로 동작시키기 위함, 비동기통신을 하기위해서
		//비동기 통신이란? 절차적이지만 순서에 상관없이 일을 처리하는것.
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 DB에 insert요청
		$.ajax({ //회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user/join",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터의 타입(MIME)
			dataType: "json" //json응답이 왔을때 javascript오브젝트로 변환
		}).done(function(resp) { //정상일시 수행
			alert("회원가입이 완료되었습니다");
			location.href = "/blog";
		}).fail(function(error) { //실패시 수행
			alert(JSON.stringify(error));
		});
	},

	login : function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		$.ajax({ 
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", 
			dataType: "json"
		}).done(function(resp) { 
			alert("로그인이 완료되었습니다");
			location.href = "/blog";
		}).fail(function(error) { 
			alert(JSON.stringify(error));
		});
	}
}

index.init();