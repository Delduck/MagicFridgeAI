package com.github.delduck.magicfridgeai.service;

import com.github.delduck.magicfridgeai.model.FoodItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChatGptService {

    private final WebClient webClient;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public Mono<String> generateRecipe(List<FoodItem> foodItems) {

        String alimentos = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s", item.getNome(),
                        item.getCategoria(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("\n"));

        System.out.println(alimentos);
        String prompt = "Baseado no meu banco de dados faça uma receit com os seguintes itens:\n " + alimentos;

        Map<String, Object> requestBody = Map.of(
                "model","gpt-5-mini",
                "input", prompt);

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {

                    List<Map<String, Object>> output =
                            (List<Map<String, Object>>) response.get("output");

                    if (output == null || output.isEmpty()) {
                        return "Nenhuma resposta.";
                    }

                    Map<String, Object> first = output.get(1);

                    List<Map<String, Object>> content =
                            (List<Map<String, Object>>) first.get("content");

                    if (content == null || content.isEmpty()) {
                        return "Nenhuma resposta.";
                    }

                    return content.get(0).get("text").toString();
                });

    }

}
