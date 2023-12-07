package entity;

import java.util.List;

public interface RecipeFactory {
    Recipe create(String name, String image, String recipeUrl, String ingredients);
}
