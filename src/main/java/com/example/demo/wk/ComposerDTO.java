package com.example.demo.wk;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
public class ComposerDTO {
	private String composer_id;
	private String composer_name;
	private String composer_genre;
	private String composer_text;
	private String composer_img;
	private MultipartFile composer_profilePicture;
	
	
	// 이미지가 없을 때 기본 이미지 파일 경로 반환
	public String getImgOrDefault() {
		if (composer_img != null && !composer_img.isEmpty()) {
			return composer_img;
		} else {
			return "default_profile.png";
		}
	}
	
    // 주 장르를 가져와서 변환하는 메서드
    public String getFormattedGenre() {
        if (composer_genre != null && !composer_genre.isEmpty()) {
            // DB에 저장된 형식을 변환 (예: "rnb,rock" -> "알앤비, 락")
            String[] genres = composer_genre.split(",");
            StringBuilder formattedGenre = new StringBuilder();

            for (String genre : genres) {
                formattedGenre.append(getFormattedSingleGenre(genre.trim())).append(", ");
            }

            // 마지막의 ", "를 제거하고 반환
            return formattedGenre.substring(0, formattedGenre.length() - 2);
        } else {
            return "";
        }
    }

    // 단일 장르를 변환하는 메서드
    private String getFormattedSingleGenre(String genre) {
        switch (genre) {
            case "pop":
                return "ポップ";
            case "dance":
                return "ダンス";
            case "electronic":
                return "エレクトロニック";
            case "hiphop":
            	return "ヒップホップ";
            case "rnb":
            	return "R&B";
            case "classic":
            	return "クラシック";
            case "newage":
            	return "ニューエイジ";
            case "rock":
            	return "ロック";
            case "ballad":
            	return "バラード";
            case "indie":
            	return "インディ";
            case "jazz_swing":
            	return "ジャズ／スウィング";
            case "latin":
            	return "ラテン";
            case "korean":
            	return "伝統音楽";
            case "world":
            	return "ワールドミュージック";
            case "ambient":
            	return "アンビエント";
            case "trot":
            	return "演歌";
            case "etc":
            	return "その他";
            // 추가적으로 필요한 장르에 대한 처리를 계속해서 추가할 수 있습니다.
            default:
                return genre; // 기본적으로는 변환하지 않고 원래 값 사용
        }
    }
}
