let index = {
	init: function() {
		$("#btn-save").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.save();
		});
		
		$("#btn-delete").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.deleteById();
		});
		
		$("#btn-update").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.update();
		});
		
		$("#btn-change").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.change();
		});
		
		$("#btn-copy").on("click", () => {	//function(){}, ()=> this를 바인딩하기 위해서!!
			this.line_copy();
		});
	},

	save: function() { //쿠킹 저장
		var sel = $("[name=sel]:checked").val();
		let bool = true;
		
		if($("#cook_name").val() == ""){
			alert("메뉴 이름을 작성 하라우!!");
			return;
		}
		let main_material = $("#main_material").val();
		let main_gram = $("#main_gram").val();
		
		let material_str = "";
		let gram_str = "";
		
		if(main_material == ""){
			alert("메인 재료 작성 하라우!!");
			return ;
		}
		
		if(main_gram == ""){
			alert("메인 재료 gram 작성 하라우!!");
			return ;
		}
		
		material_str +=main_material+",";	//메인재료 추가
		gram_str +=main_gram+",";				//메인재료 그람 추가
		
		//재료 유효성 검사
		$("#sub_mat input").each(function(index){
			
			if(index == "0" && this.value == ""){
					alert("서브 재료 작성 하라우!!");
					bool  = false;
					return false;
			}
			if(index == "1" && this.value == ""){
					alert("서브 재료 gram 작성 하라우!!");
					bool  = false;
					return false;
			}
			
			if(this.value != ""){
				
				if(index % 2 === 0){	//짝수
					//material_arr.push(this.value);
					material_str += this.value+",";
				}else{	//홀수
					gram_str += this.value+",";
				}
			}
			
		});
		
		if(!bool){
			return;
		}
		
		material_str =material_str.slice(0,-1);
		gram_str = gram_str.slice(0,-1);
		
		let data ={
			cook_kind:sel,
			cook_name : $("#cook_name").val(),
			material:material_str,
			gram:gram_str
		}
		console.log("data : "+JSON.stringify(data));
		
		$.ajax({
			type:"POST",
			url:"/api/cook",
			data:JSON.stringify(data),	
			contentType:"application/json; charset=utf-8",
			dataType:"json"	
		}).done(function(resp){
			alert("쿡 등록 되었습니다.");
			location.href = "/cook/mainForm";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	},
	
	change: function() {	//재료 변환
		
		let change_val = $("#change_val").val();					//변환 값
		let gram_0 = $("#main_gram").val();						//첫번째 메인 재료값
		let bool = true;
		
		if($("#cook_name").val() == ""){
			alert("메뉴 이름을 작성 하라우!!");
			return;
		}
		
		//재료 유효성 검사
		$("#main_mat input").each(function(index){
			
			if(index == "0" && this.value == ""){
					alert("메인 재료 작성 하라우!!");
					bool  = false;
					return false;
			}
			if(index == "1" && this.value == ""){
					alert("메인 재료 gram 작성 하라우!!");
					bool  = false;
					return false;
			}
			
			if(index == "2" && this.value == ""){
					alert("서브 재료 작성 하라우!!");
					bool  = false;
					return false;
			}
			if(index == "3" && this.value == ""){
					alert("서브 재료 gram 작성 하라우!!");
					bool  = false;
					return false;
			}
			
		});
		
		if(!bool){
			return;
		}
		
		if(change_val == ""){
			alert("변환값을 입력하라우!!");
			return;
		}
		
		//변환시 초기화
		if($("#mat_change input[name=gram]").length != 0){
			$("#mat_td").remove();
		}
		
		let main_matarial = $("[name=main_material]").val();
		let main_gram = $("[name=main_gram]").val();
		
		$("#main_chan_material").val(main_matarial);
		$("#main_chan_gram").val(change_val);
		//console.log("HTML : "+$("#mat").not("input:empty").html());
		//$("[name=main_material]").clone().appendTo("#main_chan_mat");		//재료 테이블 복사
		//$("[name=main_gram]").clone().appendTo("#main_chan_mat");
		//$("#mat_change td").attr("id","mat_td");
		
		$("#sub_mat input[name=material]").each(function(index){
			$("#sub_chan_mat #material_"+index).val(this.value);
		});
		//복사한 테이블 재정의
		$("#sub_mat input[name=gram]").each(function(index){
			console.log("this.value : "+this.value);
			var per_val = change_val /gram_0;	//비율 퍼센트 값
			var sub_val = this.value *  per_val ; 	//서브 재료 값 * 비율 퍼센트 값
			var chan_val = sub_val.toFixed(2);
			//this.value = sub_val.toFixed(2);	//소수점2자리 까지 보여준다
			$("#sub_chan_mat #gram_"+index).val(chan_val);
			//복사한 input 값이 비어 있으면 삭제한다
			/*
			if(this.value == ""){
				$("#mat_td #material_"+index).remove();
				$("#mat_td #gram_"+index).remove();
			}else{
				var per_val = change_val /gram_0;	//비율 퍼센트 값
				var sub_val = this.value *  per_val ; 	//서브 재료 값 * 비율 퍼센트 값
				this.value = sub_val.toFixed(2);	//소수점2자리 까지 보여준다
			}
			*/
		});
		
		//$("#cook_tb_change input").attr("disabled",true);
		//$("#cook_tb_change").show();
		
		
	},
	
	/*line_copy: function() {	//라인 복사
		
		
	},*/
	
	
}

index.init();
