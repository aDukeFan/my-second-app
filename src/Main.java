import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        YearManager yearManager = new YearManager();
        MonthsManager monthsManager = new MonthsManager();
        Checker checker = new Checker();
        System.out.println("Здравствуйте!");
        while (true) {
            printMenu();
            int point = scanner.nextInt();
            switch (point) {
                case 1:
                    monthsManager.monthManager("Январь", fileReader.readFileContents("m.202101.csv"));
                    monthsManager.monthManager("Февраль", fileReader.readFileContents("m.202102.csv"));
                    monthsManager.monthManager("Март", fileReader.readFileContents("m.202103.csv"));
                    System.out.println("Отчеты за январь, ферваль и март считаны и сохранены");
                    break;
                case 2:
                    yearManager.yearManager(fileReader.readFileContents("y.2021.csv"));
                    System.out.println("Частичный годовой отчет за 2021 год считан и сохранен");
                    break;
                case 3:
                    System.out.println(checker.compareAccounts(yearManager.getYearsTotalAccount(), monthsManager.getMonthsTotalAccount()));
                    break;
                case 4:
                    monthsManager.showMonthsStatistic();
                    break;
                case 5:
                    yearManager.showYearStatistic();
                    break;
                case 0:
                    System.out.print("Спасибо, что пользуетесь нашей программой!\nВсего доброго!\n");
                    scanner.close();
                    return;
                default:
                    System.err.println("Такой команды нет");

            }
        }
    }

    static void printMenu() {
        System.out.print("Чего изволите?\n" +
                "1 - Считать все месячные отчёты — прочитать данные из файлов месячных отчётов, сохранить их в память программы\n" +
                "2 - Считать годовой отчёт — прочитать данные из файла годового отчёта, сохранить их в память программы.\n" +
                "3 - Сверить отчёты — по сохранённым данным проверить, сходятся ли отчёты за месяцы и за год.\n" +
                "4 - Вывести информацию обо всех месячных отчётах — по сохранённым данным вывести в консоль имеющуюся информацию.\n" +
                "5 - Вывести информацию о годовом отчёте — по сохранённым данным вывести в консоль имеющуюся информацию.\n" +
                "0 - Завершить работу\n");
    }
}

