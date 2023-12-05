package interface_adapter.uploadedRecipe;

import entity.CommonUploadedRecipe;
import interface_adapter.uploads.UploadsState;

import java.awt.*;
import java.util.Map;

public class UploadedRecipeState {

    private Map<String, Object> uploadedRecipe;

    private String uploadedRecipeName;

    private String uploadedRecipeIngredients;

    private String uploadedRecipeInstructions;

    private Image uploadedRecipeImage;

    public UploadedRecipeState(UploadedRecipeState copy){
        uploadedRecipe = copy.uploadedRecipe;
        uploadedRecipeName = copy.uploadedRecipeName;
        uploadedRecipeIngredients = copy.uploadedRecipeIngredients;
        uploadedRecipeInstructions = copy.uploadedRecipeInstructions;
        uploadedRecipeImage = copy.uploadedRecipeImage;
    }

    public UploadedRecipeState(){}

    public Map<String, Object> getUploadedRecipe(){return uploadedRecipe;}

    public void setUploadedRecipe(Map<String, Object>  uploadedRecipe){this.uploadedRecipe = uploadedRecipe;}

    public void setUploadedRecipeName(String uploadedRecipeName){this.uploadedRecipeName = uploadedRecipeName;}

    public String getUploadedRecipeName(){return uploadedRecipeName;}

    public void setUploadedRecipeIngredients(String uploadedRecipeIngredients){this.uploadedRecipeIngredients = uploadedRecipeIngredients;}

    public String getUploadedRecipeIngredients(){return uploadedRecipeIngredients;}

    public void setUploadedRecipeInstructions(String  uploadedRecipeInstructions){this.uploadedRecipeInstructions = uploadedRecipeInstructions;}

    public String getUploadedRecipeInstructions(){return uploadedRecipeInstructions;}

    public void setUploadedRecipeImage(Image uploadedRecipImage){this.uploadedRecipeImage = uploadedRecipImage;
    }
    public Image getUploadedRecipeImage(){return uploadedRecipeImage;}


}
