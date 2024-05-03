import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MonthsManager {
    public ArrayList<Month> months = new ArrayList<>();
    FileReader fileReader = new FileReader();
    Calculator calculator = new Calculator();

    public void readMonthsFiles() {
        LinkedHashMap<String, ArrayList<String>> monthsNamesAndFiles = new LinkedHashMap<>();
        monthsNamesAndFiles.put("January", fileReader.readFileContents("m.202101.csv"));
        monthsNamesAndFiles.put("February", fileReader.readFileContents("m.202102.csv"));
        monthsNamesAndFiles.put("March", fileReader.readFileContents("m.202103.csv"));
        for (Map.Entry<String, ArrayList<String>> item : monthsNamesAndFiles.entrySet()) {
            String month = item.getKey();
            ArrayList<String> monthAccount = item.getValue();
            for (int i = 1; i < monthAccount.size(); i++) {
                String[] line = monthAccount.get(i).split(",");
                String itemName = line[0];
                boolean isExpense = Boolean.parseBoolean(line[1]);
                int quantity = Integer.parseInt(line[2]);
                int unitPrice = Integer.parseInt(line[3]);
                Month monthLine = new Month(itemName, isExpense, quantity, unitPrice, month);
                months.add(monthLine);
            }

        }
    }

    public void showMonthsStatistic() {
        if (months == null || months.isEmpty()) {
            System.out.println("Firstly you have to read months files (point 1)");
        } else {
            LinkedHashMap<String, Integer> debAndCredJan = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> debAndCredFeb = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> debAndCredMar = new LinkedHashMap<>();
            for (Month line : months) {
                switch (line.month) {
                    case "January":
                        if (line.isExpense) {
                            debAndCredJan.put(line.itemName,
                                    debAndCredJan.getOrDefault(line.itemName, 0)
                                            - line.quantity * line.unitPrice);
                        } else {
                            debAndCredJan.put(line.itemName,
                                    debAndCredJan.getOrDefault(line.itemName, 0)
                                            + line.quantity * line.unitPrice);
                        }
                        break;
                    case "February":
                        if (line.isExpense) {
                            debAndCredFeb.put(line.itemName,
                                    debAndCredFeb.getOrDefault(line.itemName, 0)
                                            - line.quantity * line.unitPrice);
                        } else {
                            debAndCredFeb.put(line.itemName,
                                    debAndCredFeb.getOrDefault(line.itemName, 0)
                                            + line.quantity * line.unitPrice);
                        }
                        break;
                    case "March":
                        if (line.isExpense) {
                            debAndCredMar.put(line.itemName,
                                    debAndCredMar.getOrDefault(line.itemName, 0)
                                            - line.quantity * line.unitPrice);
                        } else {
                            debAndCredMar.put(line.itemName,
                                    debAndCredMar.getOrDefault(line.itemName, 0)
                                            + line.quantity * line.unitPrice);
                        }
                        break;
                }
            }
            System.out.println("statistics for January");
            calculator.getMaxProfitAndExpense(debAndCredJan);
            System.out.println("statistics for February");
            calculator.getMaxProfitAndExpense(debAndCredFeb);
            System.out.println("statistics for March");
            calculator.getMaxProfitAndExpense(debAndCredMar);
        }
    }

    public LinkedHashMap<String, Integer> getMonthsTotalAccount() {
        LinkedHashMap<String, Integer> monthsTotalAccount = new LinkedHashMap<>();
        for (Month line : months) {
            if (line.isExpense) {
                monthsTotalAccount.put(line.month,
                        monthsTotalAccount.getOrDefault(line.month, 0)
                                - line.quantity * line.unitPrice);
            } else {
                monthsTotalAccount.put(line.month,
                        monthsTotalAccount.getOrDefault(line.month, 0)
                                + line.quantity * line.unitPrice);
            }
        }
        return monthsTotalAccount;
    }

}


