<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
.mat-td input[type=text]{
	    margin-right: 20px;
	    margin-bottom: 10px;
}

#cook_tb th{
	width:150px;
}
</style>
<div class="container">
  <div>    
  	<h2>쿠킹 작성</h2>
  </div>
  <br>
  <table class="table table-hover"  id="cook_tb">
      <tr id="first_tr">
        <th>선택</th>
        <td>
        	<span>
        	<label class="form-check-label" >
			    <input type="radio"  style="margin-right:5px;" name="sel"  value="1"  >요리
			 </label>
			 &nbsp;&nbsp;&nbsp;&nbsp;
			 <label class="form-check-label" >
			    <input type="radio" style="margin-right:5px;" name="sel"  value="2" checked>베이킹
			 </label>
			 </span>
        </td>
        <th>서브 재료 갯수</th>
        <td colspan="5">
        	<input type="text"  id="line_num"  placeholder="숫자만 입력하세요"  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  value="2">
        </td>
      </tr>
      <tr>
      	<th>메뉴 이름</th>
      	<td colspan="5">
      		<!-- <input type="text"  class="form-control"  placeholder="메뉴명 작성 하세유" id="title" > -->
      		<input type="text"  placeholder="메뉴명 작성 하세유" id="cook_name"  value="">
      	</td>
      </tr>
       <tr>
      	<th></th>
      	<td>
      		레시피
      	</td>
      	<td colspan="2">
      		변환 레시피
      	</td>
      </tr>
      <tr>
      	<th>메인 재료</th>
      	<td  id="main_mat"  class="mat-td">
      		<input type="text"  name="main_material" placeholder="메인 재료 작성 하세유" id="main_material"  value="" >
      		<input type="text"  name="main_gram" placeholder="gram 작성 하세유" id="main_gram"  value="" >
      		<!-- <input type="text"  placeholder="재료 작성 하세유" id="mat"  value="">
      		<input type="text"  placeholder="gram 작성 하세유" id="gram"  value=><br> -->
      	</td>
      	
      	<td  id="main_chan_mat"   class="mat-td" colspan="2">
      		<input type="text"  name="main_chan_material" disabled id="main_chan_material"  value="" >
      		<input type="text"  name="main_chan_gram" disabled id="main_chan_gram"  value="" >
      		<!-- <input type="text"  placeholder="재료 작성 하세유" id="mat"  value="">
      		<input type="text"  placeholder="gram 작성 하세유" id="gram"  value=><br>
      		<input type="text"  placeholder="재료 작성 하세유" id="mat"  value="">
      		<input type="text"  placeholder="gram 작성 하세유" id="gram"  value=> -->
      	</td>
      	 
      </tr>
      <tr>
      		<th>서브 재료</th>
      		<td  id="sub_mat"  class="mat-td">
      		<!-- <input type="text"  placeholder="재료 작성 하세유" id="mat"  value="">
      		<input type="text"  placeholder="gram 작성 하세유" id="gram"  value=><br> -->
      	</td>
      	<td  id="sub_chan_mat"   class="mat-td" colspan="2">
      		<!-- <input type="text"  placeholder="재료 작성 하세유" id="mat"  value="">
      		<input type="text"  placeholder="gram 작성 하세유" id="gram"  value=><br>
      		<input type="text"  placeholder="재료 작성 하세유" id="mat"  value="">
      		<input type="text"  placeholder="gram 작성 하세유" id="gram"  value=> -->
      	</td>
      </tr>
       <tr>
      	<th >변환 재료값</th>
      	<td  id="mat" colspan="5">
      		<input type="text"  placeholder="변환 값"  id="change_val"  value="">
      	</td>
      </tr>
     
  </table>
  <div style="text-align: center;">
	  <button id="btn-save" class="btn btn-primary">등록</button>
	  <a href="javascript:window.history.back();"class="btn btn-primary">취소</a>
	  <button id="btn-change" class="btn btn-primary">변환</button>
  </div>
  <br><br>
  <table class="table table-hover"  id="cook_tb_change"  style="display:none">
      <tr id="mat_change">
      	<th rowspan="2" > 변환 재료</th>
      </tr>
  </table>
</div>
<script src="/js/cook.js"></script>
<script>

$(document).ready(function(){
	let line_num = $("#line_num").val();
	
	for(var i =0; i < line_num; i++){
		
		$("#sub_mat").append('<input type="text"  name="material" placeholder="서브 재료 작성 하세유" id="material_'+i+'"  value="" >');
		$("#sub_chan_mat").append('<input type="text"  name="material" disabled id="material_'+i+'"  value="" >');
		$("#sub_mat").append('<input type="text"  name="gram" placeholder="gram 작성 하세유" id="gram_'+i+'"  value="" ><button onclick="line_copy(this,'+i+');" id="btn-copy" class="btn btn-primary" >+</button><br>');
		$("#sub_chan_mat").append('<input type="text"  name="gram" disabled id="gram_'+i+'"  value="" ><br>');

	}
});

//재료 갯수 변경 할때 마다 input 라인 변경
$("#line_num").on("change keyup paste", function() {
	let line_num = $("#line_num").val();
	
	if(line_num <= 20){
	    if(line_num != "") {
	    	$("#cook_name").val("");
	    	$("#main_mat input").val("");
	    	$("#sub_mat").children().remove();
	    	$("#sub_chan_mat").children().remove();
	    	for(var i =0; i < line_num; i++){
	    		
	    		$("#sub_mat").append('<input type="text"  name="material" placeholder="서브 재료 작성 하세유" id="material_'+i+'"  value="" >');
	    		$("#sub_chan_mat").append('<input type="text"  name="material" disabled id="material_'+i+'"  value="" >');
	    		$("#sub_mat").append('<input type="text"  name="gram" placeholder="gram 작성 하세유" id="gram_'+i+'"  value="" ><button onclick="line_copy(this,'+i+');" id="btn-copy" class="btn btn-primary" >+</button><br>');
	    		$("#sub_chan_mat").append('<input type="text"  name="gram" disabled id="gram_'+i+'"  value="" ><br>');
	    		
	    	}
	    }
	    
	    //$("#cook_tb input").not("#line_num").val("");	// 재료개수 제외하고 모든 input 초기화
	    //$("#cook_tb").not("#first_tr").val("");	// 첫번째 td라인  제외하고 모든 input 초기화(라디오 값까지 초기화 되버림)
	    //$("#mat_td").remove();
	    //$("#cook_tb_change").hide();
	}else{
		alert("20이하로 입력해주세요.");
		return;
	}
 
});


function line_copy(ths,cnt){
	
	$("#sub_mat").append('<input type="text"  name="material" placeholder="서브 재료 작성 하세유" id="material_'+(cnt+1)+'"  value="" >');
	$("#sub_mat").append('<input type="text"  name="gram" placeholder="gram 작성 하세유" id="gram_'+(cnt+1)+'"  value="" ><button onclick="line_copy(this,'+(cnt+1)+');" id="btn-copy" class="btn btn-primary" value="'+(cnt+1)+'">+</button><br>');
	$("#sub_chan_mat").append('<input type="text"  name="material" disabled id="material_'+(cnt+1)+'"  value="" >');
	$("#sub_chan_mat").append('<input type="text"  name="gram" disabled id="gram_'+(cnt+1)+'"  value="" ><br>');
}
</script>
<%@ include file="../layout/footer.jsp"%>