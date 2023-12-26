/* 업로드 할 프로필 사진 미리보기 */
function showProfilePicture(input) {
	var file = input.files[0];
	if (file) {
		var reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('ProfilePreview').src = e.target.result;
		};
		reader.readAsDataURL(file);
	}
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
	console.log('아티스트 명:', formData.get('name'));
	console.log('주 장르:', formData.get('genre'));
	console.log('자기 소개:', formData.get('introduce'));
	console.log('프로필 이미지:', formData.get('profilePicture'));

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
