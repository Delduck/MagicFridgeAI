package com.github.delduck.magicfridgeai.controller;


import com.github.delduck.magicfridgeai.model.FoodItem;
import com.github.delduck.magicfridgeai.service.ChatGptService;
import com.github.delduck.magicfridgeai.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final ChatGptService chatGptService;
    private final FoodItemService foodItemService;

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItem> foodItems = foodItemService.listAll();

        return chatGptService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
