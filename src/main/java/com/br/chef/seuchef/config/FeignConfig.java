package com.br.chef.seuchef.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    // Interceptor para adicionar cabeçalhos globalmente (se necessário)
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Exemplo: Adiciona o cabeçalho Content-Type globalmente
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
