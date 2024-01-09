
function submitForm() {
	// 확인 대화상자를 통해 사용자에게 수정 여부를 물어봅니다.
	var isConfirmed = confirm('修正を終わりますか？');

	// 사용자가 확인을 선택한 경우에만 폼을 제출합니다.
	if (isConfirmed) {

		// 장르 선택 여부 확인
		var selectedGenres = document.querySelectorAll('#musicGenre option:checked');

		// 최소 하나의 장르를 선택했는지 검사
		if (selectedGenres.length === 0) {
			alert('少なくとも1つのジャンルを選択してください。');
			return; // 폼 제출을 중단
		}

		var actionUrl = '/artist_upload';
		$('#artistForm').attr('action', actionUrl);
		$('#artistForm').submit();
	}
}

function cancelProfileUpdate() {
	// 확인 대화상자를 통해 사용자에게 수정 취소 여부를 물어봅니다.
	var isConfirmed = confirm('修正を取り消しますか？');
	// 브라우저의 히스토리에서 뒤로가기
	if (isConfirmed) {
		window.history.back();
	}
}

/* 업로드 할 프로필 사진 미리보기 */
function showProfilePicture(input) {
	var file = input.files[0];
	if (file) {
		var reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('ProfilePreview').src = ''; // 이미지 초기화
			document.getElementById('ProfilePreview').src = e.target.result;

		};
		reader.readAsDataURL(file);
	}
}

function useDefaultProfile() {
	// 프로필 사진의 소스를 기본 이미지 경로로 설정
	document.getElementById("ProfilePreview").src = "/images/profile/default_profile.png";
	// 파일 입력 값을 지우어 새 파일 업로드를 방지
	document.getElementById("file").value = "default_profile.png";
	// 선택적: 기본 이미지가 사용 중임을 나타내는 숨겨진 입력 필드를 업데이트
	// document.getElementById("useDefaultProfileFlag").value = "true";
	//document.getElementById("artistForm").delete('composer_profilePicture');
}

