$(function(){
	//customer
	$('.customer_list').click(function(){
		$.ajax({
			url:'/customer',
			type:'GET',
			dateType:'json',
			success:function(data){
				console.log(data);
			}
		});
	});
	
	$('.customer_register').click(function(){
		$.ajax({
			url:'/customer',
			type:'POST',
			data:jsonData,
			dateType:'json',
			seccess:function(data){
				console.log(data);
			}
						
		});
	});
});