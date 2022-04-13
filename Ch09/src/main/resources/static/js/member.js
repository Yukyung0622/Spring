$(function(){
	//member
	$('.member_list').click(function(){
		$.ajax({
			url:'/member',
			type:'GET',
			dataType:'json',
			success:function(data){
				console.log(data);
			}
		});
	});
	
	$('.member_register').click(function(){
		let jsonData = {
			"uid":"aa11",
			"name":"홍길순",
			"hp":"010-1111-1971",
			"pos":"사원",
			"dep":"101",
		};
		
		$.ajax({
			url:'/member',
			type:'POST',
			data:jsonData,
			dataType:'json',
			success:function(data){
				console.log(data);
			}
			
		});
	});
	
	$('.member_modify').click(function(){
		let jsonData = {
			"name":"홍길준",
			"hp":"010-1111-1981",
			"pos":"과장",
			"dep":"102",
		};
		
		$.ajax({
			url:'/member/aa11',
			type:'PUT',
			data:jsonData,
			dataType:'json',
			success:function(data){
				console.log(data);
			}
			
		});
		
	});
	
	$('.member_delete').click(function(){
		$.ajax({
			url:'/member/aa11',
			type:'DELETE',
			data:jsonData,
			dataType:'json',
			success:function(data){
				console.log(data);
			}
			
		});
	});
});