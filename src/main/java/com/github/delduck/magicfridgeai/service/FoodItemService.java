package com.github.delduck.magicfridgeai.service;

import com.github.delduck.magicfridgeai.model.FoodItem;
import com.github.delduck.magicfridgeai.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> listAll() {
        return foodItemRepository.findAll();
    }

    public FoodItem listByID(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public void removeByID(Long id) {
        foodItemRepository.deleteById(id);
    }

}
