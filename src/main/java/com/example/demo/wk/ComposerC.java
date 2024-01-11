package com.example.demo.wk;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

// html에서 form요청이 들어오거나, 내가 url에 직접 입력한주소(즉 다른페이지에서 어떠한 항목을 누를때자바스크립트단에서 바뀌는 url--> <a>태그의 href 또는 자바스크립트의 온클릭등) 
// 그에 해당하는 주소에 @GetMapping 하면, view로 쏠 수 있음. 

// RestController의 역할은 비동기통신(ajax, fetch등 ) 이런 요청의 주소값이 들어왔을때 프론트단의 js에서 성공(suceess)했을 경우 그부분에서 사용하기 위해서 우리가 컨트롤러에서
// 넘겨주는 

@Controller
@RequiredArgsConstructor
public class ComposerC {

	private final ComposerService composerService;

//	@GetMapping("/artist_detail/{userFullPhoneNumber}")
//	public String getComposerById(@PathVariable String userFullPhoneNumber, Model model) {
//		
//		ComposerDTO composer = composerService.getComposerById(userFullPhoneNumber);
//		System.out.println("여기까지 찍히는지 확인 " + userFullPhoneNumber);
//
//		// 이미지 파일의 경로 설정 (기본 이미지 포함)
//		composer.setComposer_img(composer.getImgOrDefault());
//
//		model.addAttribute("composer", composer);
//		System.out.println(composer);
//
//		model.addAttribute("content", "wk/artist_detail");
//
//		return "wk/index";

	@GetMapping("/artist_main")
	public String getArtistMainPage(Model model) {
		// 아티스트 목록을 가져옴
		List<ComposerDTO> artistList = composerService.getAllArtists();

		// 아티스트 목록을 모델에 추가
		model.addAttribute("artistList", artistList);

		// content에 artist_main을 추가
		model.addAttribute("content", "wk/artist_main");

		return "wk/index";
	}

	@GetMapping("/artists/search")
	public String searchArtists(@RequestParam String keyword, Model model) {
		List<ComposerDTO> artistList = composerService.searchArtists(keyword);
		model.addAttribute("artistList", artistList);
		model.addAttribute("content", "wk/artist_main");
		return "wk/index";
	}

	@PostMapping("/artist_detail")
	public String getComposerById(@RequestParam String userFullPhoneNumber, Model model, HttpSession httpSession) {
		System.out.println("detail확인" + userFullPhoneNumber);
		System.out.println(model.getAttribute("composer"));

		ComposerDTO composer = composerService.getComposerById(userFullPhoneNumber);

		String followId = (String) httpSession.getAttribute("userFullPhoneNumber");

		String isfollow = "";
		
		
		// 세션 값 이 있을경우 ( 로그인이 되어있는 경우 )
		if (followId != null) {
			isfollow = composerService.selectIsFollow(followId, userFullPhoneNumber);

			System.out.println("팔로우 입니다");
			System.out.println(isfollow);

			if (isfollow.equals("1")) {
				composer.setIsFollow("1");
			} else {
				composer.setIsFollow("0");
			}
		}
		
		// 세션이 없는 경우 ( 미로그인 시 )
		else {
			composer.setIsFollow("0");
		}

		// 이미지 파일의 경로 설정 (기본 이미지 포함)
		// composer.setComposer_img(composer.getImgOrDefault());

		model.addAttribute("composer", composer);
		System.out.println(composer);

		model.addAttribute("content", "wk/artist_detail");
		return "wk/index";
	}

	@GetMapping("artist_reg")
	public String artist_reg(Model model) {
		model.addAttribute("content", "wk/artist_reg");
		return "wk/index";
	}

	@PostMapping("artist_reg/upload")
	public String uploadArtist(@ModelAttribute ComposerDTO composer, Model model, HttpSession session) {
		String userFullPhoneNumber = (String) session.getAttribute("userFullPhoneNumber");

		System.out.println("여기까지 찍히는지 확인 " + userFullPhoneNumber);
		System.out.println("아티스트명 " + composer.getComposer_name());
		System.out.println("장르 " + composer.getComposer_genre());
		System.out.println("자기소개서 " + composer.getComposer_text());
		System.out.println("음악 사진 " + composer.getComposer_profilePicture());
		System.out.println("음악 사진 이름 " + composer.getComposer_profilePicture().getOriginalFilename());

		composer.setComposer_id(userFullPhoneNumber);

		// 프로필 사진 저장 및 파일명 설정
		String fileName = saveProfilePicture(composer.getComposer_profilePicture());
		composer.setComposer_img(fileName);

		// 아티스트 등록 로직 수행
		composerService.regComposer(composer);

		// 세션의 userId를 이용해서 iscomposer를 업데이트
		composerService.updateIsComposer(userFullPhoneNumber);

		// userTable의 nickName을 artist등록시 artistnickname으로 변경
		composerService.updateUserNickName(composer.getComposer_name(), userFullPhoneNumber);

		// 세션 지우고 다시 iscomposer를 1로 만들어야함
		session.removeAttribute("iscomposer");
		session.setAttribute("iscomposer", 1);

		session.removeAttribute("userNickname");
		session.setAttribute("userNickname", composer.getComposer_name());

		model.addAttribute("content", "wk/artist_main");
		return "wk/index";
	}

	@PostMapping("/artist_update")
	public String getArtistUpdatePage(@RequestParam("userFullPhoneNumber") String composer_id, Model model) {
		// 해당 composerId에 해당하는 정보를 가져와서 모델에 추가
		ComposerDTO composer = composerService.getComposerById(composer_id);

		model.addAttribute("composer", composer);
		model.addAttribute("content", "wk/artist_update");
		return "wk/index";
	}

	@PostMapping("/artist_upload")
	public String updateArtist(@RequestParam("userFullPhoneNumber") String composer_id,
			@ModelAttribute ComposerDTO composer, Model model, HttpSession session) {
		try {
			System.out.println("composer_id test" + composer_id);
			System.out.println("composer test" + composer);
			// composer.setComposer_id(composer_id);

			if (composer.getComposer_profilePicture() == null || composer.getComposer_profilePicture().isEmpty()) {
				// 기존 이미지를 가져와서 설정
				ComposerDTO existingComposer = composerService.getComposerById(composer_id);
				composer.setComposer_img(existingComposer.getComposer_img());
			} else {
				// 이미지를 변경한 경우에만 저장하고 파일명 설정
				String newFileName = saveProfilePicture(composer.getComposer_profilePicture());
				composer.setComposer_img(newFileName);
			}

			// songs table update 로직
			if (composerService.updateSongs(composer.getComposer_name(), composer_id) >= 1) {
				System.out.println("songs table update success");
			}

			// user table update 로직
			if (composerService.updateUserTable(composer.getComposer_name(), composer_id) >= 1) {
				System.out.println("user table update success");
			}

			// comment table update 로직
			if (composerService.updateCommentTable(composer.getComposer_name(), composer_id) >= 1) {
				System.out.println("comment table update success");
			}

			// CreateRoomTable user1 update 로직
			if (composerService.updateCreateRoomTable1(composer.getComposer_name(), composer_id) >= 1) {
				System.out.println("CreateRoom table update success");
			}

			// CreateRoomTable user2 update 로직
			if (composerService.updateCreateRoomTable2(composer.getComposer_name(), composer_id) >= 1) {
				System.out.println("CreateRoom table update success");
			}

			// ChatTable update 로직
			if (composerService.updateChatTable(composer.getComposer_name(), composer_id) >= 1) {
				System.out.println("chat table update success");
			}

			System.out.println("단위 테스트 " + composer.getComposer_name());
			System.out.println("단위 테스트 " + composer.getComposer_genre());
			System.out.println("단위 테스트 " + composer.getComposer_text());
			System.out.println("단위 테스트 " + composer.getComposer_img());
			composer.setComposer_id(composer_id);
			// 업데이트 로직 수행
			composerService.updateComposer(composer);

			// 업데이트된 composer 다시 가져오기
			ComposerDTO updatedComposer = composerService.getComposerById(composer_id);
			model.addAttribute("composer", updatedComposer);

			System.out.println(updatedComposer);

			System.out.println("아티스트명: " + updatedComposer.getComposer_name());
			System.out.println("장르: " + updatedComposer.getComposer_genre());
			System.out.println("자기소개: " + updatedComposer.getComposer_text());
			System.out.println("음악 사진: " + updatedComposer.getComposer_profilePicture());
			// System.out.println("음악 사진 이름: " +
			// updatedComposer.getComposer_profilePicture().getOriginalFilename());

			// 세션수정(composer_name)
			// 세션 지우고 다시 usernickname 갱신해야함
			session.removeAttribute("userNickname");
			session.setAttribute("userNickname", updatedComposer.getComposer_name());

			return "forward:/artist_detail";
			// return getComposerById(composer_id, model);

		} catch (Exception e) {
			// 업데이트 실패시 에러 메시지 추가
			model.addAttribute("error", "Error updating composer");
			model.addAttribute("content", "wk/artist_update");
			return "wk/index";
		}
	}

	// 프로필 사진 저장 로직
	private String saveProfilePicture(MultipartFile file) {
		// TODO: 프로필 사진을 저장하고 파일명을 반환하는 로직 추가
		try {
			// 파일이 비어있는 경우 기본 이미지 파일명 반환
			if (file == null || file.isEmpty()) {
				return "default_profile.png";
			}
			// 프로필 사진을 저장할 디렉토리 경로
			String directoryPath = "src/main/resources/static/images/profile/";

			// UUID를 사용한 파일명 생성
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

			// 디렉토리가 존재하지 않으면 생성
			File directory = new File(directoryPath);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			// 파일을 지정된 경로에 복사
			FileCopyUtils.copy(file.getBytes(), new File(directoryPath + fileName));

			return fileName; // 저장된 파일의 상대 경로를 반환

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}