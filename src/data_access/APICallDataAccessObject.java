package data_access;

import java.io.IOException;
import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class APICallDataAccessObject {
    public static void jsonToCsv(String jsonFile){
        try {
            String csvFile = "output.csv";
            JSONObject jsonObject = new JSONObject(jsonFile);
            JSONArray hits = jsonObject.getJSONArray("hits");
            FileWriter outputFile = new FileWriter(csvFile);
            CSVWriter writer = new CSVWriter(outputFile);
            String[] header = {"Recipe Name", "Source", "Diet Labels", "Health Labels", "Cuisine",
                    "Meal Type", "Dish Type",};
            writer.writeNext(header);
            for (int i = 0; i < hits.length(); i++) {
                JSONObject recipe = hits.getJSONObject(i).getJSONObject("recipe");
                String name = recipe.getString("label");
                String source = recipe.getString("source");
//                String dietLabels = recipe.getString("dietLabels");
//                String healthLabels = recipe.getString("healthLabels");
//                String cuisine = recipe.getString("cuisineType");
//                String mealType = recipe.getString("mealType");
//                String dishType = recipe.getString("dishType");
                String[] data = {name, source};
                writer.writeNext(data);

            }
            writer.close();
            System.out.println("CSV file generated successfully.");

        } catch(IOException e){
            e.printStackTrace();
        }


    }
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}






