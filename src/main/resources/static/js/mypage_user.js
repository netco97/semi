function realout() {
    var confirmDelete = confirm("정말 삭제하시겠습니까?");
    
    if (confirmDelete) {
        
        window.location.href ="/deleteUser" 
        
        
        
    } else {
        console.log("삭제 취소");
    }
}