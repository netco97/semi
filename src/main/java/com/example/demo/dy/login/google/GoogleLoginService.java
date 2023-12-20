package com.example.demo.dy.login.google;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleLoginService {
	private final GoogleMapper googleMapper; 
	private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();
    
    public void socialLogin(String code, String registrationId, HttpSession session) {
        String accessToken = getAccessToken(code, registrationId);
        System.out.println("accessToken = " + accessToken);
        JsonNode userResourceNode = getUserResource(accessToken, registrationId);
        System.out.println("userResourceNode = " + userResourceNode);

        String id = userResourceNode.get("id").asText();
        String email = userResourceNode.get("email").asText();
        String nickname = userResourceNode.get("name").asText();
        System.out.println("id = " + id);
        System.out.println("email = " + email);
        System.out.println("nickname = " + nickname);
        
        
        
        
        
        // 중복처리
        List<GoogleUserDTO> existingUsers = googleMapper.SelUser(nickname);

        if (!existingUsers.isEmpty()) {
            GoogleUserDTO existingUser = existingUsers.get(0);
            System.out.println("이미 있는 아이디입니다.");
            // 이미 존재하는 사용자에 대한 추가 작업 수행
            
            
            
            
        } else {
            GoogleUserDTO newUser = new GoogleUserDTO(id, email, nickname);

            if (googleMapper.RegUser(newUser) == 1) {
                System.out.println("Google 테이블 등록 성공");
            } else {
                System.out.println("Google 테이블 등록 실패");
            }
        }
        
        
        session.setAttribute("userId", id);
        session.setAttribute("userEmail", email);
        session.setAttribute("userNickname", nickname);
        
       
        
        
    }
    
    
    
    
    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");
        
        System.out.println("clientId 확인 : " + clientId);
        System.out.println("clientSecret 확인 :"+ clientSecret); 
        System.out.println("redirectUri 확인 : " + redirectUri );
        System.out.println("tokenUri 확인 : " + tokenUri);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);
        
        System.out.println("entity 확인" +entity.getHeaders());
        System.out.println("entity 확인" +entity.getBody());

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        
        return accessTokenNode.get("access_token").asText();
    }
    private JsonNode getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2."+registrationId+".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        System.out.println("다왔따");
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
    
    
    
    
}

