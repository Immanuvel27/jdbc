function eventView(){
	var url="Eventview"
	const request=new XMLHttpRequest();
	var value;
	try {
		request.open("POST",url,true);
		request.send();
		request.onreadystatechange=function(){
			if(request.readyState==4){
				value=request.responseText;
				alert(value);
				
			}
		}
		
	} catch (error) {
		alert(error);
	
	}
}