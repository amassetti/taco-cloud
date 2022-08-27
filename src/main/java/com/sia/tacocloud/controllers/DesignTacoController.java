package com.sia.tacocloud.controllers;

import com.sia.tacocloud.model.Ingredient;
import com.sia.tacocloud.model.IngredientType;
import com.sia.tacocloud.model.Taco;
import com.sia.tacocloud.model.TacoOrder;
import com.sia.tacocloud.persistence.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        IngredientType [] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.name().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String createTacoOrder(
            @Valid Taco taco,
            Errors errors,
            @ModelAttribute TacoOrder tacoOrder
    ) {
        if (errors.hasErrors()) {
            log.error(errors.toString());
            return "design";
        }
        log.info("Adding taco {} to order...", taco);
        tacoOrder.addTaco(taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredientsIt, IngredientType type) {
        Stream<Ingredient> ingredients = StreamSupport.stream(ingredientsIt.spliterator(), false);
        return ingredients
                .filter( i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}
