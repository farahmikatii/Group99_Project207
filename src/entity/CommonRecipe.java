package entity;

import java.util.List;

public class CommonRecipe implements Recipe{
    private final String name;
    private final String image;
    private final String recipeUrl;

    /**
     *
     * @param name
     * @param image
     * @param recipeUrl
     */
    public CommonRecipe(String name, String image, String recipeUrl) {
        this.name = name;
        this.image = image;
        this.recipeUrl = recipeUrl;
    }

    @Override
    public String getRecipeName() {
        return name;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getRecipeUrl() {
        return recipeUrl;
    }


}
