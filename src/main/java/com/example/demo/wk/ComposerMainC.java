package com.example.demo.wk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ComposerMainC {
	
	private final ComposerService composerService;
	
	@GetMapping("/artist_list")
	public Map<String, Object> getArtistMainPage(@RequestParam(name = "page", defaultValue = "1") int page,
	                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
	                                @RequestParam(name = "query", required = false) String query) {
		
		if (query != null && !query.isEmpty()) {
			// 검색어가 주어진 경우
            List<ComposerDTO> searchResult = composerService.searchArtists(query);
            int totalSearchResults = searchResult.size();

            // 페이징 적용
            int startIndex = (page - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalSearchResults);
            List<ComposerDTO> paginatedResult = searchResult.subList(startIndex, endIndex);

            // 검색 결과를 담을 Map 생성
            Map<String, Object> searchResultMap = new HashMap<>();
            searchResultMap.put("artistList", paginatedResult);
            searchResultMap.put("totalArtists", totalSearchResults);
            searchResultMap.put("totalPages", (int) Math.ceil((double) totalSearchResults / pageSize));

            // 검색 결과 반환
            return searchResultMap;
            
		} else {
			
			// 아티스트 목록을 가져옴 (페이징 적용)
			List<ComposerDTO> artistList = composerService.getArtistsWithPagination(page, pageSize);
			
			// 전체 아티스트 수를 가져옴 (페이징 계산을 위해)
			int totalArtists = composerService.getTotalArtistsCount();
			
			// 전체 페이지 수를 계산
			int totalPages = (int) Math.ceil((double) totalArtists / pageSize);
			// 결과를 담을 Map 생성
			Map<String, Object> result = new HashMap<>();
			result.put("artistList", artistList);
			result.put("totalArtists", totalArtists);
			result.put("totalPages", totalPages);
			
			// 결과 반환
			return result;
		}

	}

}
