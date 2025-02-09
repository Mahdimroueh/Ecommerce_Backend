package com.mroueh.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ImgurService {

    private final String CLIENT_ID = "c0b1b2479c4fa0e";
    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";

    public String uploadBase64Image(String base64Image, String title, String description) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Client-ID " + CLIENT_ID);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", base64Image);
        body.add("type", "base64");
        body.add("title", title);
        body.add("description", description);


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);


        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(IMGUR_API_URL, requestEntity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return (String) ((Map<String, Object>) response.getBody().get("data")).get("link");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;

    }
}
