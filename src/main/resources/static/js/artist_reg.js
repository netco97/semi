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

function cancelProfileReg() {
	// 확인 대화상자를 통해 사용자에게 수정 취소 여부를 물어봅니다.
	var isConfirmed = confirm('登録を辞めますか？');
	// 브라우저의 히스토리에서 뒤로가기
	if (isConfirmed) {
		event.preventDefault();
		window.history.back();
	}
}

function useDefaultProfile() {
	// 프로필 사진의 소스를 기본 이미지 경로로 설정
	document.getElementById("ProfilePreview").src = "/images/profile/default_profile.png";
	// 파일 입력 값을 지우어 새 파일 업로드를 방지
	document.getElementById("file").value = "";
	// 선택적: 기본 이미지가 사용 중임을 나타내는 숨겨진 입력 필드를 업데이트
	// document.getElementById("useDefaultProfileFlag").value = "true";
	document.getElementById("artistForm").delete('composer_profilePicture');
}

function submitForm() {
	var form = document.getElementById('artistForm');
	var formData = new FormData(form);

	// 'introduce' 텍스트 값을 FormData에 추가
	var introduceValue = document.querySelector('.introduce').value;
	formData.append('introduce', introduceValue);

	// 파일명을 FormData에 추가
	var fileName = document.getElementById('file').files[0].name;
	formData.append('fileName', fileName);

	// 입력된 값들을 확인하기 위한 예제
	console.log('아티스트 명:', formData.get('composer_name'));
	console.log('주 장르:', formData.get('composer_genre'));
	console.log('자기 소개:', formData.get('composer_introduce'));
	console.log('프로필 이미지:', formData.get('composer_profilePicture'));

	var xhr = new XMLHttpRequest();
	xhr.open('POST', form.action, true);

	// 서버로부터 응답을 받았을 때의 처리
	xhr.onload = function() {
		if (xhr.status === 200) {
			// 성공적으로 처리됐을 때의 동작
			console.log(xhr.responseText);
		} else {
			// 오류 발생 시의 동작
			console.error('Upload failed with status ' + xhr.status);
		}
	};

	// 폼 데이터를 서버로 전송

	xhr.send(formData);
}