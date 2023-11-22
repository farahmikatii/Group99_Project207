package entity;

import java.awt.*;

public class CommonUploadedRecipe implements UploadedRecipe{

    private final String name;

    private final String ingredients;

    private final String instructions;

    private final Image recipeImage;
    /**
     *
     * @param name
     * @param ingredients
     * @param instructions
     * @param recipeImage
     */

    CommonUploadedRecipe(String name, String ingredients, String instructions, Image recipeImage){
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.recipeImage = recipeImage;
    }

    @Override
    public String getUploadedRecipeName() {
        return name;
    }

    @Override
    public String getInstructions() {
        return instructions;
    }

    @Override
    public String getIngredients() {
        return ingredients;
    }

    @Override
    public Image getImage() {
        return recipeImage;
    }
}
