package com.br.chef.seuchef.client;

import com.br.chef.seuchef.client.response.GeminiResponse;
import com.br.chef.seuchef.config.FeignConfig;
import com.br.chef.seuchef.client.request.GeminiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "geminiClient",
        url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent",
        configuration = FeignConfig.class
)
public interface GeminiFeignClient {
    @PostMapping()
    ResponseEntity<GeminiResponse> generateContent(
            @RequestParam("key") String apiKey,
            @RequestBody GeminiRequest request
    );
}

