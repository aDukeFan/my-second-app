import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MonthsManager {
    public ArrayList<SaveMonth> months = new ArrayList<>();
    FileReader fileReader = new FileReader();
    Calculator calculator = new Calculator();

    public void readMonthsFiles() {
        LinkedHashMap<String, ArrayList<String>> monthsNamesAndFiles = new LinkedHashMap<>();
        monthsNamesAndFiles.put("Январь", fileReader.readFileContents("m.202101.csv"));
        monthsNamesAndFiles.put("Февраль", fileReader.readFileContents("m.202102.csv"));
        monthsNamesAndFiles.put("Март", fileReader.readFileContents("m.202103.csv"));
        for (Map.Entry<String, ArrayList<String>> item : monthsNamesAndFiles.entrySet()) {
            String month = item.getKey();
            ArrayList<String> monthAccount = item.getValue();
            for (int i = 1; i < monthAccount.size(); i++) {
                String[] line = monthAccount.get(i).split(",");
                String itemName = line[0];
                boolean isExpense = Boolean.parseBoolean(line[1]);
                int quantity = Integer.parseInt(line[2]);
                int unitPrice = Integer.parseInt(line[3]);
                SaveMonth monthLine = new SaveMonth(itemName, isExpense, quantity, unitPrice, month);
                months.add(monthLine);
            }

        }
    }

    public void showMonthsStatistic() {
        if (months == null || months.isEmpty()) {
            System.out.println("Сначала считайте месячные отчеты (пункт 1)");
        } else {
            LinkedHashMap<String, Integer> debAndCredJan = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> debAndCredFeb = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> debAndCredMar = new LinkedHashMap<>();
            for (SaveMonth line : months) {
                switch (line.month) {
                    case "Январь":
                        if (line.isExpense) {
                            debAndCredJan.put(line.itemName, debAndCredJan.getOrDefault(line.itemName, 0) - line.quantity * line.unitPrice);
                        } else {
                            debAndCredJan.put(line.itemName, debAndCredJan.getOrDefault(line.itemName, 0) + line.quantity * line.unitPrice);
                        }
                        break;
                    case "Февраль":
                        if (line.isExpense) {
                            debAndCredFeb.put(line.itemName, debAndCredFeb.getOrDefault(line.itemName, 0) - line.quantity * line.unitPrice);
                        } else {
                            debAndCredFeb.put(line.itemName, debAndCredFeb.getOrDefault(line.itemName, 0) + line.quantity * line.unitPrice);
                        }
                        break;
                    case "Март":
                        if (line.isExpense) {
                            debAndCredMar.put(line.itemName, debAndCredMar.getOrDefault(line.itemName, 0) - line.quantity * line.unitPrice);
                        } else {
                            debAndCredMar.put(line.itemName, debAndCredMar.getOrDefault(line.itemName, 0) + line.quantity * line.unitPrice);
                        }
                        break;
                }
            }
            System.out.println("Статистика за январь:");
            calculator.getMaxProfitAndExpense(debAndCredJan);
            System.out.println("Статистика за февраль:");
            calculator.getMaxProfitAndExpense(debAndCredFeb);
            System.out.println("Статистика за март:");
            calculator.getMaxProfitAndExpense(debAndCredMar);
        }
    }

    public LinkedHashMap<String, Integer> getMonthsTotalAccount() {
        LinkedHashMap<String, Integer> monthsTotalAccount = new LinkedHashMap<>();
        for (SaveMonth line : months) { // здесь тоже были ненужные условия
            if (line.isExpense) {
                monthsTotalAccount.put(line.month, monthsTotalAccount.getOrDefault(line.month, 0) - line.quantity * line.unitPrice);
            } else {
                monthsTotalAccount.put(line.month, monthsTotalAccount.getOrDefault(line.month, 0) + line.quantity * line.unitPrice);
            }
        }
    return monthsTotalAccount;
    }

}


