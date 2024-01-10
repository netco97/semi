function realout() {
    var confirmDelete = confirm("정말 삭제하시겠습니까?");
    
    if (confirmDelete) {
        
        window.location.href ="/deleteUser" 
        
        
        
    } else {
        console.log("삭제 취소");
    }
}


document.addEventListener("DOMContentLoaded", function () {
    if (userPhoneNumber) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/api/getUserImage?phoneNumber=' + userPhoneNumber, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var imageUrl = xhr.responseText;
                var imgContainer = document.createElement("div");
                imgContainer.className = "img-container-mypage";
                imgContainer.innerHTML = '<img  src="' + '/images/profile/'+ imageUrl + '" alt="User Image">';
                												//onerror="this.src='/images/profile/default_profile.png'"			
                
                document.querySelector('.menu-container-mypage').prepend(imgContainer);
                
            }
        };
        xhr.send();
    }
});