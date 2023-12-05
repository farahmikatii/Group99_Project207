package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonRecipeTest {
    private CommonRecipe recipe;

    public CommonRecipeTest(){
        this.recipe = new CommonRecipe(
                "lemonade", "./src", "www.test.com");
    }


    @Test
    public void getRecipeName() {
        assertEquals("lemonade", recipe.getRecipeName());
    }

    @Test
    public void getImage() {
        assertEquals("./src", recipe.getImage());
    }

    @Test
    public void getRecipeUrl() {
        assertEquals("www.test.com", recipe.getRecipeUrl());
    }
}