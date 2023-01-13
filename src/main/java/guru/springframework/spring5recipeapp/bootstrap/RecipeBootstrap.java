package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        StringBuilder sb = new StringBuilder();

        Recipe guacamole = new Recipe();

        guacamole.setDescription("How to Make the Best Guacamole");
        guacamole.setCookTime(0);
        guacamole.setPrepTime(10);
        guacamole.setServings(4);

        sb.append("1. Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.");
        sb.append("\n");
        sb.append("2. Using a fork, roughly mash the avocado.");
        sb.append("\n");
        sb.append("3. Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown. ");
        sb.append("Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat. ");
        sb.append("Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.");
        sb.append("\n");
        sb.append("If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. ");
        sb.append("(The oxygen in the air causes oxidation which will turn the guacamole brown.) ");
        sb.append("Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.");
        sb.append("Refrigerate leftover guacamole up to 3 days.");

        guacamole.setDirections(sb.toString());

        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setSource("Simply Recipes");
        //guacamole.setImage();
        guacamole.setDifficulty(Difficulty.EASY);

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        guacamole.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom, guacamole));

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        guacamole.setNotes(guacamoleNotes);
        guacamoleNotes.setRecipe(guacamole);

        recipes.add(guacamole);


        Recipe spicyGrilledChickenTaco = new Recipe();

        spicyGrilledChickenTaco.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTaco.setCookTime(15);
        spicyGrilledChickenTaco.setPrepTime(20);
        spicyGrilledChickenTaco.setServings(6);

        sb = new StringBuilder();

        sb.append("1. Prepare either a gas or charcoal grill for medium-high, direct heat.");
        sb.append("\n");
        sb.append("2. In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. ");
        sb.append("Stir in the orange juice and olive oil to make a loose paste. ");
        sb.append("Add the chicken to the bowl and toss to coat all over. ");
        sb.append("Set aside to marinate while the grill heats and you prepare the rest of the toppings.");
        sb.append("\n");
        sb.append("3. Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.");
        sb.append("\n");
        sb.append("4. Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.");
        sb.append("\n");
        sb.append("5. Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges");
        sb.append("\n");
        sb.append("6. Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side. ");
        sb.append("Wrap warmed tortillas in a tea towel to keep them warm until serving.");

        spicyGrilledChickenTaco.setDirections(sb.toString());

        spicyGrilledChickenTaco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChickenTaco.setSource("Simply Recipes");
        //spicyGrilledChickenTaco.setImage();
        spicyGrilledChickenTaco.setDifficulty(Difficulty.MODERATE);

        spicyGrilledChickenTaco.getCategories().add(americanCategory);
        spicyGrilledChickenTaco.getCategories().add(mexicanCategory);

        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Dried oregano", new BigDecimal(1), teaSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Clove of Garlic, chopped", new BigDecimal(1), eachUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("medium ripe avocados, slice", new BigDecimal(2), eachUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, spicyGrilledChickenTaco));

        Notes spicyGrilledChickenTacoNotes = new Notes();
        spicyGrilledChickenTacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        spicyGrilledChickenTaco.setNotes(spicyGrilledChickenTacoNotes);
        spicyGrilledChickenTacoNotes.setRecipe(spicyGrilledChickenTaco);

        recipes.add(spicyGrilledChickenTaco);

        return recipes;
    }

}
