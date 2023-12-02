package interface_adapter.search;

public class SearchState {
    private String query = "";
    private final String queryEmpty = null;
    private String dietLabel = "";
    private final String dietLabelEmpty = null;
    private String healthLabel = "";
    private final String healthLabelEmpty = null;
    private String cuisine = "";
    private final String cuisineEmpty = null;
    private String mealType = "";
    private final String mealTypeEmpty = null;
    private String dishType = "";
    private final String dishTypeEmpty = null;
    private String searchError = "";

    public SearchState(SearchState copy) {
        query = copy.query;
        dietLabel = copy.dietLabel;
        healthLabel = copy.healthLabel;
        cuisine = copy.cuisine;
        mealType = copy.mealType;
        dishType = copy.dishType;
        searchError = copy.searchError;
    }

    public SearchState(){}

    public String getQuery() {
        return query;
    }

    public String getQueryEmpty() {
        return queryEmpty;
    }


    public String getDietLabel() {
        return dietLabel;
    }

    public String getDietLabelEmpty() {
        return dietLabelEmpty;
    }

    public String getHealthLabel() {
        return healthLabel;
    }

    public String getHealthLabelEmpty() {
        return healthLabelEmpty;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getCuisineEmpty() {
        return cuisineEmpty;
    }

    public String getMealType() {
        return mealType;
    }

    public String getMealTypeEmpty() {
        return mealTypeEmpty;
    }

    public String getDishType() {
        return dishType;
    }

    public String getDishTypeEmpty() {
        return dishTypeEmpty;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setDietLabel(String dietLabel) {
        this.dietLabel = dietLabel;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public void setHealthLabel(String healthLabel) {
        this.healthLabel = healthLabel;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
