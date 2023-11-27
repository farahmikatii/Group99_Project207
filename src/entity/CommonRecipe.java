package entity;

import java.util.List;

public class CommonRecipe implements Recipe{
    private final String name;
    private final String imagePath;
    private final String recipeUrl;

    /**
     *
     * @param name
     * @param imagePath
     * @param recipeUrl
     */
    public CommonRecipe(String name, String imagePath, String recipeUrl) {
        this.name = name;
        this.imagePath = imagePath;
        this.recipeUrl = recipeUrl;
    }

    @Override
    public String getRecipeName() {
        return name;
    }

    @Override
    public String getImage() {
        return imagePath;
    }

    @Override
    public String getRecipeUrl() {
        return recipeUrl;
    }


}
