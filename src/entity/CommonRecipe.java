package entity;

public class CommonRecipe implements Recipe{
    private final String name;
    private final String imagePath;
    private final String recipeUrl;
    private final String ingredients;

    /**
     * @param name
     * @param imagePath
     * @param recipeUrl
     * @param ingredients
     */
    public CommonRecipe(String name, String imagePath, String recipeUrl, String ingredients) {
        this.name = name;
        this.imagePath = imagePath;
        this.recipeUrl = recipeUrl;
        this.ingredients = ingredients;
    }
    public String getIngredients() {
        return ingredients;
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
