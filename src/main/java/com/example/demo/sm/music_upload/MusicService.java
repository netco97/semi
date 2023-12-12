package com.example.demo.sm.music_upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MusicService {
	
	private final MusicMapper musicMapper;
	
	public MusicService(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public List<String> saveFile(MusicUploadDTO musicUploadDTO) {
		MultipartFile img_file = musicUploadDTO.getAlbumArt();
	    MultipartFile audio_file = musicUploadDTO.getMusicFile();
	    
	    //Controller 에 반환할 list
	    List<String> result = new ArrayList<>();
	    
	    System.out.println("이미지 파일 이름 : " + img_file.getOriginalFilename());
	    System.out.println("오디오 파일 이름 : " + audio_file.getOriginalFilename());
	    
	    try {
	    	// 랜덤ID + 파일 이름 핸들링
		    String imgFileName = UUID.randomUUID().toString() + "_" + img_file.getOriginalFilename();
		    String audioFileName = UUID.randomUUID().toString() + "_" + audio_file.getOriginalFilename();
			
		    // controller 에 반환할 String List
		    result.add(imgFileName);
		    result.add(audioFileName);
		    
		    // 파일 서버에 저장
		    saveServerFile(img_file , imgFileName, "static/img");
		    saveServerFile(audio_file, audioFileName, "static/audio");
		    
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return result;
	}
	
	private void saveServerFile(MultipartFile file, String fileName, String subDirectory) throws IOException{
		// 파일을 저장할 경로 설정
        String uploadDir = "src/main/resources/" + subDirectory;
        Path uploadPath = Path.of(uploadDir);

        // 디렉토리가 존재하지 않으면 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일 저장
        try (var inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
	}

	public void saveSongs(SongsDTO songsDTO) {
		if(musicMapper.regSongs(songsDTO) == 1) {
			System.out.println("songs DB 등록 성공 ");
		}
	}
}
