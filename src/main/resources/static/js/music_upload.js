/* 업로드 할 앨범 아트 미리보기 */
    function showAlbumArt(input) {
        var file = input.files[0];
        if (file) {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('albumArtPreview').src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    }

/* 분위기 체크 박수 개수 제한 */
	var maxChecked = 10;   //선택가능한 체크박스 갯수
	var totalChecked = 0; // 설정 끝
	
	function CountChecked(field) {
	    if (field.checked) // input check 박스가 체크되면 
	        totalChecked += 1; // totalChecked 증가
	    else // 체크가 아니면
	        totalChecked -= 1; // totalChecked 감소
	
	    if (totalChecked > maxChecked) { // totalchecked 수가 maxchecked수보다 크다면
	        alert ("최대 10개 까지만 선택 할 수 있습니다."); // 팝업창 띄움
	    field.checked = false;
	    totalChecked -= 1;
	    }
	}