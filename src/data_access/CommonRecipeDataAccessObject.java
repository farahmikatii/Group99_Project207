package data_access;

import entity.CommonRecipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommonRecipeDataAccessObject {
    private final List<CommonRecipe> commonRecipeList = new ArrayList<>();
    private final String jsonFile;

    public CommonRecipeDataAccessObject(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public List<CommonRecipe> returnRecipeList(){
        JSONObject jsonObject = new JSONObject(jsonFile);
        JSONArray hits = jsonObject.getJSONArray("hits");
        for (int i = 0; i < hits.length(); i++) {
            JSONObject recipeJson = hits.getJSONObject(i).getJSONObject("recipe");

            // Extract recipe information
//            int id = recipeJson.getInt("id");
            String label = recipeJson.getString("label");
            String image = recipeJson.getString("image");


            // Assuming ingredients is an array, extract it
//            JSONArray ingredientsJsonArray = recipeJson.getJSONArray("ingredients");
//            String[] ingredients = new String[ingredientsJsonArray.length()];
//            for (int j = 0; j < ingredientsJsonArray.length(); j++) {
//                ingredients[j] = ingredientsJsonArray.getString(j);
//            }

            String url = recipeJson.getString("url");

            // Create a CommonRecipe object
            CommonRecipe commonRecipe = new CommonRecipe(label, image, url);
            commonRecipeList.add(commonRecipe);
        }
        return commonRecipeList;
    }
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}

