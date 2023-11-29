package data_access;

import entity.UploadedRecipe;
import entity.User;
import entity.UserFactory;
import entity.UploadedRecipeFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.uploading.UploadingDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, UploadingDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private final Map<String, UploadedRecipe> uploadedRecipeMap = new HashMap<>();

    private UserFactory userFactory;

    private UploadedRecipeFactory uploadedRecipeFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        //headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // TODO clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    //String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    //LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, password);
                    accounts.put(username, user);
                }
            }
        }
    }


    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s",
                        user.getName(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUploadedRecipe(UploadedRecipe uploadedRecipe) {
        uploadedRecipeMap.put(uploadedRecipe.getUploadedRecipeName(), uploadedRecipe);
        this.saveUploadedRecipe();
    }

    @Override
    public List<Map<String, Object>> readUploadedRecipesFromCSV() {
        List<Map<String, Object>> recipesList = new ArrayList<>();

        for (UploadedRecipe uploadedRecipe : uploadedRecipeMap.values()) {
            Map<String, Object> uploadedRecipeDataMap = new HashMap<>();
            uploadedRecipeDataMap.put("Name", uploadedRecipe.getUploadedRecipeName());
            uploadedRecipeDataMap.put("Ingredients", uploadedRecipe.getIngredients());
            uploadedRecipeDataMap.put("Instructions", uploadedRecipe.getInstructions());
            uploadedRecipeDataMap.put("Image", uploadedRecipe.getImage());

            recipesList.add(uploadedRecipeDataMap);
        }

        return recipesList;
    }

    private void saveUploadedRecipe(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.newLine();

            for (UploadedRecipe uploadedRecipe: uploadedRecipeMap.values()){
                HashMap<String, Object> uploadedrecipeDataMap = new HashMap<>();

                uploadedrecipeDataMap.put("Name", uploadedRecipe.getUploadedRecipeName());
                uploadedrecipeDataMap.put("Ingredients", uploadedRecipe.getIngredients());
                uploadedrecipeDataMap.put("Instructions", uploadedRecipe.getUploadedRecipeName());
                uploadedrecipeDataMap.put("Image", uploadedRecipe.getImage());

                StringBuilder lineBuilder = new StringBuilder();
                for (Map.Entry<String, UploadedRecipe> entry : uploadedRecipeMap.entrySet()){
                    lineBuilder.append(String.format("%s=%s,", entry.getKey(), entry.getValue()));
                }

                String line = lineBuilder.toString().replaceAll(",$", "");
                writer.write(line);
                writer.newLine();
            }
            writer.close();


        } catch (IOException e){
            throw new RuntimeException(e);
        }
        }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

}
