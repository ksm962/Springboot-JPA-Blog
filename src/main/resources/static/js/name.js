
let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
			//alert('name의 세이브함수 호출됨');
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			}
			console.log(data);
			//ajax default 비동기 호출
			//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
			//ajax통신을 성공하고 서버가 sjon을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
			$.ajax({
				type: "post",
				url: "/auth/joinProc",
				data:JSON.stringify(data), //http body데이터
				contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(mime)
				dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 문자열(json이면 -> javascript)
			}).done(function(resp){
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href="/"
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},
		update: function() {
			let data = {
				id:$("#id").val(),
				username:$("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			}
			$.ajax({
				type: "put",
				url: "/name/update",
				data:JSON.stringify(data), //http body데이터
				contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(mime)
				dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 문자열(json이면 -> javascript)
			}).done(function(resp){
				alert("회원수정이 완료되었습니다.");
				console.log(resp);
				location.href="/"
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}
		
		
		
}

index.init();

