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

function redirectToArtistDetail() {
		
		// 폼 동적으로 생성
        var form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', '/artist_detail');

        // 전화번호 입력 필드 추가
        var phoneNumberInput = document.createElement('input');
        phoneNumberInput.setAttribute('type', 'hidden');
        phoneNumberInput.setAttribute('name', 'userFullPhoneNumber');
        phoneNumberInput.setAttribute('value', userphone1);

        // 폼에 입력 필드 추가
        form.appendChild(phoneNumberInput);

        // 폼을 body에 추가하고 서브밋
        document.body.appendChild(form);
        form.submit();
   }