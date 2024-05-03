import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearManager yearManager = new YearManager();
        MonthsManager monthsManager = new MonthsManager();
        Checker checker = new Checker();
        System.out.println("Welcome!");
        while (true) {
            printMenu();
            int point = scanner.nextInt();
            switch (point) {
                case 1:
                    monthsManager.readMonthsFiles();
                    System.out.println(
                            "Reports for January, February and March are read and saved");
                    break;
                case 2:
                    yearManager.readYearFile();
                    System.out.println("Partial annual report for 2021 read and saved");
                    break;
                case 3:
                    System.out.println(checker.compareAccounts(
                            yearManager.getYearsTotalAccount(),
                            monthsManager.getMonthsTotalAccount()));
                    break;
                case 4:
                    monthsManager.showMonthsStatistic();
                    break;
                case 5:
                    yearManager.showYearStatistic();
                    break;
                case 0:
                    System.out.print("Have a nice day!");
                    scanner.close();
                    return;
                default:
                    System.err.println("Oops! There is no such point =(");

            }
        }
    }

    static void printMenu() {
        System.out.print("---------------\n" +
                "1 - Read month reports\n" +
                "2 - Read year report\n" +
                "3 - Compare accounts\n" +
                "4 - Show months report\n" +
                "5 - Show year report\n" +
                "0 - Close the app\n" +
                "---------------\n");
    }
}

