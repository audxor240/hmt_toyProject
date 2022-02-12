let index = {
	init: function() {
		$("#btn-save").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.save();
		});
		
		$("#btn-delete").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.delete();
		});
	},

	save: function() {
		var id = $("#board_id").val();
		
		$.ajax({
			type:"DELETE",
			url:"/api/board",
			data:JSON.stringify(data),	
			contentType:"application/json; charset=utf-8",
			dataType:"json"	
		}).done(function(resp){
			alert("글 등록 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	},
	
	delete: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			id: $("#board-id").val()
		};
		
		$.ajax({
			type:"POST",
			url:"/api/board_delete",
			data:JSON.stringify(data),	
			contentType:"application/json; charset=utf-8",
			dataType:"json"	
		}).done(function(resp){
			alert("글 삭제 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	},
	
}

index.init();
