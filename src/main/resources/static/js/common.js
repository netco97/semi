// JavaScript로 드롭다운 메뉴를 토글하는 함수
document.getElementById("profile-icon").addEventListener("click", function (event) {
    var dropdown = document.getElementById("myDropdown");
    var target = event.target;

    // 만약 클릭한 요소가 profile-icon-link이라는 id를 가진 링크가 아니라면 실행
    if (!target.closest('#profile-icon-link')) {
        if (window.getComputedStyle(dropdown).display === "none") {
            dropdown.style.display = "block";
        } else {
            dropdown.style.display = "none";
        }
    }
});

// 드롭다운 외의 영역을 클릭하면 닫히도록 하는 이벤트
window.onclick = function (event) {
    if (!event.target.closest('#profile-icon')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (window.getComputedStyle(openDropdown).display === "block") {
                openDropdown.style.display = "none";
            }
        }
    }
}
