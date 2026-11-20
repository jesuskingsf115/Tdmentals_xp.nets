package br.com.tdmxp.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class FirecrawlService {

    private final RestClient clientRest;

    public FirecrawlService(RestClient clientRest) {
        this.clientRest = clientRest;
    }

    public JsonNode buscarFontes(String estado, String cidade){

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("O estado é obrigatório.");
        }

        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("A cidade é obrigatória.");
        }

        //Envia uma pesquisa ao Firecrawl procurando páginas de órgãos oficiais.
        String pesquisa = "Sites oficiais da Defesa Civil, prefeitura e órgãos "
                + "públicos com alertas e notícias sobre "
                + cidade + ", " + estado
                + ", Brasil. Priorizar fontes governamentais oficiais.";

        Map<String, Object> requisicao = Map.of(
                "query", pesquisa,
                "limit", 10,
                "country", "BR",
                "lang", "pt-BR"
        );

        return clientRest.post()
                .uri("/v2/search")
                .body(requisicao)
                .retrieve()
                .body(JsonNode.class);
    }







    }
}


