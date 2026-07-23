package com.github.delduck.magicfridgeai.controller;

import com.github.delduck.magicfridgeai.model.FoodItem;
import com.github.delduck.magicfridgeai.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItem> add(@RequestBody FoodItem foodItem) {
        FoodItem foodsave = foodItemService.save(foodItem);
        return ResponseEntity.ok(foodsave);
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> list() {
        return ResponseEntity.ok(foodItemService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> listID(@PathVariable Long id) {
        Optional<FoodItem> optionalFoodItem = foodItemService.listByID(id);
        return optionalFoodItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeID(@PathVariable Long id) {
        foodItemService.removeByID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> update(@PathVariable Long id,
                                           @RequestBody FoodItem foodItem) {

        return foodItemService.listByID(id)
                .map(existsItem -> {
                    foodItem.setId(existsItem.getId());
                    FoodItem foodUpdate = foodItemService.update(foodItem);
                    return ResponseEntity.ok(foodUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
}
