import java.util.LinkedHashMap;
import java.util.Map;

public class Checker {

    String compareAccounts(LinkedHashMap<String, Integer> yearsTotalAccount, LinkedHashMap<String, Integer> monthsTotalAccount) {
        if (yearsTotalAccount.isEmpty() && monthsTotalAccount.isEmpty()) {
            return "Сначала считайте месячные отчеты (пункт 1) и годовой отчет (пункты 2)";
        } else if (yearsTotalAccount.isEmpty()) {
            return "Cначала считайте годовой отчет (пункт 2)";
        } else if (monthsTotalAccount.isEmpty()) {
            return "Сначала считайте месячный отчет (пункт 1)";
        } else {
            if (monthsTotalAccount.equals(yearsTotalAccount)) {
                return "Все в порядке, данные сходятся.";
            } else {
                StringBuilder difference = new StringBuilder();
                for (Map.Entry<String, Integer> item : monthsTotalAccount.entrySet()) {
                    if (!yearsTotalAccount.get(item.getKey()).equals(item.getValue())) {
                        difference.append(String.format("Данные по месяцу - %s не совпадают\n", item.getKey()));
                    }
                }
                return String.valueOf(difference);
            }
        }
    }
}


