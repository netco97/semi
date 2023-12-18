	let user_id = `[[${session.userId}]]`;
	let user_name = `[[${session.userNickname}]]`;
	let user_email = `[[${session.userEmail}]]`;
	
	document.querySelector(".article_name").innerHTML = user_name;
	document.querySelector(".article_email").innerHTML = user_email;
	document.querySelectorAll(".article input").forEach(function(inputElement) {
    inputElement.style.background = "#4b4949";
});
  
  
  document.getElementById("submitButton").addEventListener("click", function() {
	  
	  
	  
           
    });