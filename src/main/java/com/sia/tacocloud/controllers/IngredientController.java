package com.sia.tacocloud.controllers;

import com.sia.tacocloud.model.Ingredient;
import com.sia.tacocloud.persistence.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute(name = "ingredient")
    public Ingredient ingredient() {
        return new Ingredient();
    }

    @GetMapping
    public String showIngredientsForm() {
        return "ingredients";
    }

    @PostMapping
    public String addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return "redirect:/design";
    }
}
