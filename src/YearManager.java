import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class YearManager {
    public ArrayList<SaveYear> year = new ArrayList<>();
    FileReader fileReader = new FileReader();
    Calculator calculator = new Calculator();

    public void readYearFile() {
        ArrayList<String> annualAccount = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < annualAccount.size(); i++) {
            String[] line = annualAccount.get(i).split(",");
            String month = line[0];
            int amount = Integer.parseInt(line[1]);
            boolean isExpense = Boolean.parseBoolean(line[2]);
            SaveYear yearLine = new SaveYear(month, amount, isExpense);
            year.add(yearLine);
        }
    }

    public void showYearStatistic() {
        if (year.isEmpty()) {
            System.out.println("Firstly you have to read files");
        } else {
            ArrayList<Integer> deb = new ArrayList<>();
            ArrayList<Integer> cred = new ArrayList<>();
            for (SaveYear line : year) {
                if (line.isExpense) {
                    cred.add(line.amount);
                } else {
                    deb.add(line.amount);
                }
            }
            System.out.print("Year: 2021\n" +
                    "catch income\n");
            for (Map.Entry<String, Integer> item : getYearsTotalAccount().entrySet()) {
                System.out.printf("%-10s %-10d\n", item.getKey(), item.getValue());
            }
            System.out.printf("average consumption: %d\n", calculator.getAverageValue(cred));
            System.out.printf("average income: %d\n", calculator.getAverageValue(deb));
        }
    }

    public LinkedHashMap<String, Integer> getYearsTotalAccount() {
        LinkedHashMap<String, Integer> yearsTotalAccount = new LinkedHashMap<>();
        for (SaveYear line : year) {
            switch (line.month) {
                case "01":
                    if (line.isExpense) {
                        yearsTotalAccount.put("January",
                                yearsTotalAccount.getOrDefault("January", 0) - line.amount);
                    } else {
                        yearsTotalAccount.put("January",
                                yearsTotalAccount.getOrDefault("January", 0) + line.amount);
                    }
                    break;
                case "02":
                    if (line.isExpense) {
                        yearsTotalAccount.put("February",
                                yearsTotalAccount.getOrDefault("February", 0) - line.amount);
                    } else {
                        yearsTotalAccount.put("February",
                                yearsTotalAccount.getOrDefault("February", 0) + line.amount);
                    }
                    break;
                case "03":
                    if (line.isExpense) {
                        yearsTotalAccount.put("March",
                                yearsTotalAccount.getOrDefault("March", 0) - line.amount);
                    } else {
                        yearsTotalAccount.put("March",
                                yearsTotalAccount.getOrDefault("March", 0) + line.amount);
                    }
                    break;
            }
        }
        return yearsTotalAccount;
    }
}
