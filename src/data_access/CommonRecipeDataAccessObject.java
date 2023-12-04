package data_access;

import entity.CommonRecipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.loggedIn.LoggedInDataAccessInterface;
import use_case.recipePopup.RecipePopupDataAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommonRecipeDataAccessObject implements LoggedInDataAccessInterface, RecipePopupDataAccessInterface {
    private final List<CommonRecipe> commonRecipeList = new ArrayList<>();
    private final String jsonFile;

    public CommonRecipeDataAccessObject(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public List<CommonRecipe> returnRecipeList(int diff){
        String targetDictionary;
        if (diff == 1){
            targetDictionary = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/src/images";
        }
        else{
            targetDictionary = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/src/filter/images";
        }
        JSONObject jsonObject = new JSONObject(jsonFile);
        JSONArray hits = jsonObject.getJSONArray("hits");
        for (int i = 0; i < hits.length(); i++) {
            JSONObject recipeJson = hits.getJSONObject(i).getJSONObject("recipe");

            // Extract recipe information
//            int id = recipeJson.getInt("id");
            String label = recipeJson.getString("label");
            String url = recipeJson.getString("url");

            String image_path = Objects.requireNonNull(downloadImage(recipeJson.getString("image"), url, targetDictionary)).toString();

            //String image_path = recipeJson.getString("image");


            // Assuming ingredients is an array, extract it
//            JSONArray ingredientsJsonArray = recipeJson.getJSONArray("ingredients");
//            String[] ingredients = new String[ingredientsJsonArray.length()];
//            for (int j = 0; j < ingredientsJsonArray.length(); j++) {
//                ingredients[j] = ingredientsJsonArray.getString(j);
//            }



            // Create a CommonRecipe object
            CommonRecipe commonRecipe = new CommonRecipe(label, image_path, url);
            commonRecipeList.add(commonRecipe);
        }
        return commonRecipeList;
    }

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static Path downloadImage(String imageUrl, String recipeUrl, String targetDirectory) {
        // Returns path of image which is downloaded under 'images' package in src
        try {
            URL url = new URL(imageUrl);
            String[] illegal = {"#", "%", "&", "}", "{", "\\", ">", "<", "*", "?", "/", "$", "!", "'", "\"", ":", "@", "+", "`", "|", "=", ""};

            try (InputStream in = url.openStream()) {
                //String fileName = recipeName.replaceAll(Arrays.toString(illegal), "_") + ".png";
                String fileName = recipeUrl.replaceAll(Arrays.toString(illegal), "_") + ".png";
                Path destination = Paths.get(targetDirectory, fileName);

                // Creation of target directory if non-existent
                Files.createDirectories(destination.getParent());

                Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);

                return destination;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public CommonRecipe findRecipe(String label, List<CommonRecipe> commonRecipeList) {
        for (CommonRecipe recipe : commonRecipeList) {
            if (recipe.getRecipeName().equalsIgnoreCase(label)) {
                return recipe;
            }
        }
        return null;  // Recipe not found
    }
}