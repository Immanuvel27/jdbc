	document.getElementById("submit").addEventListener("click", LOGIN);
function LOGIN(e) {
  e.preventDefault();
//  var v= prompt("Who are you?");  
//  alert("I am "+v);  
  var uname = document.getElementById("uname").value;
  var pass = document.getElementById("pass").value;
  var uregex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
  var pregex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.* ).{6,}$/;
  var arrayObject=[];
  if (!pass.match(pregex) && !uname.match(uregex)) {
    document.getElementById("nameerr").innerHTML =
      "Please enter the valid username(Email id)";
    document.getElementById("passerr").innerHTML =
      "Please enter atleast one uppercase,one lowercase,<br/> one special character, one number";
    document.getElementById("uname").style.border="2px solid red";
    document.getElementById("pass").style.border="2px solid red";
  }
  if (!pass.match(pregex)) {
    document.getElementById("passerr").innerHTML =
      "Please enter atleast one uppercase,one lowercase,<br/> one special character, one number";
    document.getElementById("pass").style.border="2px solid red";
  }
  if (!uname.match(uregex)) {
    document.getElementById("nameerr").innerHTML =
      "Please enter the valid username(Email id)";
    document.getElementById("uname").style.border="2px solid red";
  }
  if (uname.match(uregex)) {
    document.getElementById("nameerr").innerHTML = "";
    document.getElementById("uname").style.border="2px solid green";
  }
  if (pass.match(pregex)) {
    document.getElementById("passerr").innerHTML = "";
    document.getElementById("pass").style.border="2px solid green";
  }
  if (pass.match(pregex) && uname.match(uregex)) {
    
      document.getElementById("nameerr").innerHTML = "";
      document.getElementById("passerr").innerHTML = "";
      document.getElementById("uname").style.border="2px solid green";
      document.getElementById("pass").style.border="2px solid green";
      
    /*  arrayObject.push({"email":uname,"pass":pass});
      console.log(JSON.stringify(arrayObject));*/
      /*form.action='login';*/

      /*var param = 'objarray=' +arrayObject;

      $.ajax({
        url: '/login',
        type: 'POST', 
        dataType: 'json',  
        data: param,
        success: function(result) {
            alert('SUCCESS');
        }
      });*/
      
      var url="login?uname="+uname+"&pass="+pass;
		const request=new XMLHttpRequest();
		var value;
		try {
			request.open("POST",url,true);
			request.send();
			request.onreadystatechange=function(){
				if(request.readyState==4){
					value=request.responseText;
					alert(value);
					if(value=="Success"){
					window.location.href="addashboard.html";
					}else if(value=="unsuccess"){
						alert("USERNAME OR PASSWORD IS MISMATCH");
						window.location.href="login.html";
						window.reload();
					}
				}
			}
			
		} catch (error) {
			alert(error);
		
		}
      
    } 
  
}
