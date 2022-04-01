
let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
			//alert('name의 세이브함수 호출됨');
			let data = {
				title: $("#title").val(),
				content: $("#content").val()
			}
			$.ajax({
				type: "post",
				url: "/api/board",
				data:JSON.stringify(data), 
				contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(mime)
				dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 문자열(json이면 -> javascript)
			}).done(function(resp){
				alert("글쓰기가 완료되었습니다.");
				console.log(resp);
				location.href="/"
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}
}

index.init();

