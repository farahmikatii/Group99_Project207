package use_case.loggedIn;

public class LoggedInInputData {
    private String recipeLabel;

    private String imageUrl;


    public LoggedInInputData(String recipeLabel, String imageUrl){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
    }

    public String getRecipeLabel(){
        return recipeLabel;
    }
    public void setRecipeLabel(String recipeLabel){
        this.recipeLabel = recipeLabel;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
