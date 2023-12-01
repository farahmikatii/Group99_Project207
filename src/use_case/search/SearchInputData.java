package use_case.search;

import java.util.HashMap;

public class SearchInputData {
    final private HashMap<StringBuilder, StringBuilder> filterDict;

    public SearchInputData(HashMap<StringBuilder, StringBuilder> filterDict){
        this.filterDict = filterDict;
    }

    public HashMap<StringBuilder, StringBuilder> getFilterDict(){
        return filterDict;
    }
}
