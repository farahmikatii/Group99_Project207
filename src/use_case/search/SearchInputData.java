package use_case.search;

import java.util.HashMap;

public class SearchInputData {
    final private HashMap<String, String> filterDict;

    public SearchInputData(HashMap<String, String> filterDict){
        this.filterDict = filterDict;
    }

    public HashMap<String, String> getFilterDict(){
        return filterDict;
    }
}
