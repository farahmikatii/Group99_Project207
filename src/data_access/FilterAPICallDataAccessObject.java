package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import use_case.search.SearchUserDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FilterAPICallDataAccessObject implements SearchUserDataAccessInterface {

    private final String key_id = "https://api.edamam.com/api/recipes/v2?app_id=0e94da52&app_key=%20a1c655a3813bf3c3fc6362ee953aa8e3&type=public&random=true";

    public String getKey_id(){return key_id;}

    public String searchApiCall(HashMap<String, String> filterDict) {
        StringBuilder returnString = new StringBuilder(getKey_id());
        for (HashMap.Entry<String, String> entry : filterDict.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null){
                returnString.append("&").append(key).append("=").append(value);
            }
        }
        String url = returnString.toString();
        String filePath = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/responseOutputFiltered.json";
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;
                filePath = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/responseOutputFiltered.json";

                // Write the response to a file
                try (BufferedSink sink = Okio.buffer(Okio.sink(new File(filePath))) ) {
                    sink.writeAll(response.body().source());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("API response saved to file: " + filePath);
            }
            response.close();
        } catch(IOException e) {
            try {
                throw new IOException("error");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return filePath;
    }


}
