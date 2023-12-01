package use_case.search;

import java.util.HashMap;

public interface SearchUserDataAccessInterface {
    String searchApiCall(HashMap<StringBuilder, StringBuilder> filterDict);
}
