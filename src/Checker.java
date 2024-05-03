import java.util.LinkedHashMap;
import java.util.Map;

public class Checker {

    public String compareAccounts(LinkedHashMap<String, Integer> yearsTotalAccount,
                                  LinkedHashMap<String, Integer> monthsTotalAccount) {
        if (yearsTotalAccount.isEmpty() && monthsTotalAccount.isEmpty()) {
            return "Firstly you have to read months files (point 1) and year file (point 2)";
        } else if (yearsTotalAccount.isEmpty()) {
            return "Firstly you have to read year file (point 2)";
        } else if (monthsTotalAccount.isEmpty()) {
            return "Firstly you have to read months files (point 1)";
        } else {
            if (monthsTotalAccount.equals(yearsTotalAccount)) {
                return "Success! the data coincides";
            } else {
                StringBuilder difference = new StringBuilder();
                for (Map.Entry<String, Integer> item : monthsTotalAccount.entrySet()) {
                    if (!yearsTotalAccount.get(item.getKey()).equals(item.getValue())) {
                        difference.append(String.format("data of - %s month doesn't coincide\n", item.getKey()));
                    }
                }
                return String.valueOf(difference);
            }
        }
    }
}


