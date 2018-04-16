/*=================================向后台发送请求获取数据===================================================================*/
function getsum(){
	$.ajax({
		url:"http://localhost:8080/MVC_Demo/json/hello.action",
		type:"get",
		cache: false,
		dataType:'json',
		success:function(json){
			console.log("ceshi");
			console.log(json);
			alert("success");
			for(var x in json){
				console.log(json[x].id);
				console.log(json[x].name);
				console.log(json[x].password);
				}
			},
			error:function(){
				alert("error");
			}
		});
	}

/*=================================向后太发送请求并获取数据================================================*/
function setsum(){
	var jsonarray=new Array();
	jsonarray.push({
        id: "2",
        name: "山贝戊",
        password: "lyx1994114"
	});
	
	$.ajax({
		url: "http://localhost:8080/MVC_Demo/json/set.action",
		type: "post",
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		data: JSON.stringify(jsonarray),
		success: function(json){
			alert("success");
			alert(json);
		},
		error: function(){
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
		}
	});

}

/*============================点击按钮方法========================================*/
$(document).ready(function(){
	$(".get").on("click", function(){
		getsum();
	});	
	$(".set").on("click", function(){
		setsum();
	})
});
	