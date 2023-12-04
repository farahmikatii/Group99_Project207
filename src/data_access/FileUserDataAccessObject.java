package data_access;

import app.UploadsUseCaseFactory;
import entity.UploadedRecipe;
import entity.User;
import entity.UserFactory;
import entity.UploadedRecipeFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.uploading.UploadingDataAccessInterface;
import use_case.uploads.UploadsDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, UploadingDataAccessInterface, UploadsDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

/*    private final Map<String, User> accounts = new HashMap<>();*/

    private final List<User> accounts = new ArrayList<>();

    private final Map<String, UploadedRecipe> uploadedRecipeMap = new HashMap<>();

    private UserFactory userFactory;

    private UploadedRecipeFactory uploadedRecipeFactory;
    private String loggedInUsername;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        //headers.put("creation_time", 2);
        headers.put("uploaded recipes", 2);

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
                accounts.add(user);

        /*        Map<String, User> userMap = new HashMap<>();
                userMap.put(username, user);
                accounts.add(userMap);*/
            }
        }
    }

}

    /**
     * Return whether a user exists with username identifier.
     *
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        //return accounts.containsKey(identifier);
  /*      for (Map<String, User> user : accounts){
            if (user.containsKey(identifier)){
                return true;
            }
        }
        return false;*/
        for (User user : accounts){
            if (user.getName().equals(identifier)){
                return true;
            }
        } return false;
    }

    @Override
    public void save(User user) {
        accounts.add(user);
        this.save();
/*        Map<String, User> userMap = new HashMap<>();
        userMap.put(user.getName(), user);
        accounts.add(userMap);
        this.save();*/
    }

    public User get(String username) {
        for (User user : accounts){
            if (user.getName().equals(username)){
                return user;
            }
        } return null;
/*        for (Map<String, User> user : accounts) {
            if (user.containsKey(username)) {
                return user.get(username);
            }
        } return null;*/
    }

    @Override
    public void setUsername(String username) {
        this.loggedInUsername = username;
    }

    private void save() {
        // rewrite entire csv file
        // add new line for last element of accounts
        BufferedWriter writer;

        if (accounts.isEmpty()){
            try {
                writer = new BufferedWriter(new FileWriter(csvFile));
                writer.write(String.join(",", headers.keySet()));
                writer.newLine();

                for (User user : accounts) {

                    String userline = String.format("%s,%s, []",
                            user.getName(), user.getPassword());

                    writer.write(userline);
                    writer.newLine();
                }

                writer.close();

            } catch (IOException e) {
                throw new RuntimeException(e);}
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));){
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    content.append(line).append("\n");
                }
                String userline = String.format("%s,%s, []",
                        accounts.get(accounts.size() -1 ).getName(), accounts.get(accounts.size() -1 ).getPassword());
                content.append(userline).append("\n");

                try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(csvFile))) {
                    writer2.write(content.toString());
                } catch (IOException e){
                    throw new RuntimeException(e);
                }

            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }

    }


        public void saveUploadedRecipe(UploadedRecipe uploadedRecipe) {
        uploadedRecipeMap.put(uploadedRecipe.getUploadedRecipeName(), uploadedRecipe);
        System.out.println(uploadedRecipeMap);
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

    private void saveUploadedRecipe() {
        String targetUsername = loggedInUsername;
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String[] copyArray = new String[3];
                if (parts.length >= 3 && parts[0].equals(targetUsername)) {
                    int startIndex = -1;
                    for (int i = 2; i < parts.length; i++) {
                        if (parts[i].trim().startsWith("[")) {
                            startIndex = i;
                            break;
                        }
                    }

                    if (startIndex != -1) {
                        // Extract existing recipes from the CSV line
                        StringBuilder currentUploadedRecipes = new StringBuilder(parts[startIndex]);
                        for (int i = startIndex + 1; i < parts.length; i++) {
                            currentUploadedRecipes.append(",").append(parts[i]);
                            if (parts[i].trim().endsWith("]")) {
                                break;
                            }
                        }
                        String currentRecipesString = currentUploadedRecipes.toString();
                        List<Map<String, Object>> parsedMap = parseListOfHashMap(currentRecipesString);

                        for (UploadedRecipe uploadedRecipe : uploadedRecipeMap.values()) {
                            Map<String, Object> uploadedrecipeDataMap = new HashMap<>();
                            uploadedrecipeDataMap.put("Name", uploadedRecipe.getUploadedRecipeName());
                            uploadedrecipeDataMap.put("Ingredients", uploadedRecipe.getIngredients());
                            uploadedrecipeDataMap.put("Instructions", uploadedRecipe.getInstructions());
                            uploadedrecipeDataMap.put("Image", uploadedRecipe.getImage());
                            parsedMap.add(uploadedrecipeDataMap);
                        }

                        String updatedUploadedRecipes = convertMapListToString(parsedMap);

                        for (int i = 0, k = 0; i < parts.length; i++){
                            if (i == 2 | i > 2){
                                continue;
                            } copyArray[k++] = parts[i];
                        }

                        copyArray[startIndex] = updatedUploadedRecipes;
                        updatedLines.add(String.join(",", copyArray));
                    }
                } else if (!parts[0].equals(targetUsername)){
                    updatedLines.add(String.join(",", parts));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static List<Map<String, Object>> parseListOfHashMap(String input) {
        List<Map<String, Object>> list = new ArrayList<>();

        input = input.trim();

        if (input.equals("[]")) {
            return list;
        }
        if (input.startsWith("[") && input.endsWith("]")) {
            input = input.substring(1, input.length() - 1);
        }

        String[] mapStrings = input.split(",(?![^{}]*\\})");

        for (String mapString : mapStrings) {
            Map<String, Object> map = parseHashMap(mapString.trim());
            list.add(map);
        }

        return list;
    }

    private static Map<String, Object> parseHashMap(String input) {
        Map<String, Object> map = new HashMap<>();

        // Remove curly braces
        input = input.replaceAll("[{}]", "");

        // Split by commas
        String[] pairs = input.split(",");

        for (String pair : pairs) {
            // Split each pair by '='
            String[] keyValue = pair.trim().split("=");

            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                map.put(key, value);
            }
        }

        return map;
    }

    private static String convertMapListToString(List<Map<String, Object>> mapList) {
        StringBuilder result = new StringBuilder("[");

        for (Map<String, Object> map : mapList) {
            result.append("{");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
            }
            result.setLength(result.length() - 2);  // Remove the trailing comma and space
            result.append("}, ");
        }

        if (!mapList.isEmpty()) {
            result.setLength(result.length() - 2);  // Remove the trailing comma and space
        }

        result.append("]");
        return result.toString();
    }
}





