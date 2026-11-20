package br.com.tdmxp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FirecrawlConfig {

    @Value("${FIRECRAWL_API_KEY}")
    private String apiKey;

    @Bean
    public RestClient firecrawlRestClient() {

        return RestClient.builder()
                .baseUrl("https://api.firecrawl.dev")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }
}
