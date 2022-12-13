	document.getElementById("submit").addEventListener("click", LOGIN);
function LOGIN(e) {
  e.preventDefault();
//  var v= prompt("Who are you?");  
//  alert("I am "+v);  
  var uname = document.getElementById("uname").value;
  var pass = document.getElementById("pass").value;
  var repass=document.getElementById("repass").value ;
  var email=document.getElementById("email").value;
  var phone =document.getElementById("phone").value;
  var nregex=/^[a-zA-Z ]{3,}$/;
  var phregex=/^[0-9]{10}$/;
  var eregex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
  var pregex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.* ).{6,}$/;

  if (!pass.match(pregex) && !email.match(eregex) && !uname.match(nregex) && !phone.match(phregex)) {
    document.getElementById("eerr").innerHTML =
      "Please enter the valid Email(Email id)";
      document.getElementById("nameerr").innerHTML =
      "Please enter the valid username";
      document.getElementById("perr").innerHTML =
      "Please enter the valid phone number";
    document.getElementById("passerr").innerHTML =
      "Please enter atleast one uppercase,one lowercase,<br/> one special character, one number";
    document.getElementById("uname").style.border="2px solid red";
    document.getElementById("email").style.border="2px solid red";
    document.getElementById("pass").style.border="2px solid red";
    document.getElementById("phone").style.border="2px solid red";
  }
  if (!pass.match(pregex)) {
    document.getElementById("passerr").innerHTML =
      "Please enter atleast one uppercase,one lowercase,<br/> one special character, one number";
    document.getElementById("pass").style.border="2px solid red";
  }
  if (!uname.match(nregex)) {
    document.getElementById("nameerr").innerHTML =
      "Please enter the valid username";
    document.getElementById("uname").style.border="2px solid red";
  }
  if(!email.match(eregex)){
	  document.getElementById("eerr").innerHTML =
	      "Please enter the valid Email(Email id)";
	  document.getElementById("email").style.border="2px solid red";
  }
  if(!phone.match(phregex)){
	  document.getElementById("perr").innerHTML =
	      "Please enter the valid phone number";
	  document.getElementById("phone").style.border="2px solid red";
  }
  if (uname.match(nregex)) {
    document.getElementById("nameerr").innerHTML = "";
    document.getElementById("uname").style.border="2px solid green";
  }
  if (pass.match(pregex)) {
    document.getElementById("passerr").innerHTML = "";
    document.getElementById("pass").style.border="2px solid green";
  }
  if(email.match(eregex)){
	  document.getElementById("eerr").innerHTML =
	      "";
	  document.getElementById("email").style.border="2px solid green";
  }
  if(phone.match(phregex)){
	  document.getElementById("perr").innerHTML =
	      "";
	  document.getElementById("phone").style.border="2px solid green";
  }
  if(pass!=repass||repass==""){
    document.getElementById("repasserr").innerHTML =
    "Password doesnot match";
  document.getElementById("repass").style.border="2px solid red";
  }
  if(pass==repass&&repass!=""){
    document.getElementById("repasserr").innerHTML =
      "";
    document.getElementById("repass").style.border="2px solid green";
  }
  
  
}
