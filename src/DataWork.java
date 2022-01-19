import java.io.IOException;
import java.util.Scanner;

public class DataWork {
    private static final int JANUARY = 31;
    private static final int FEBRUARY = 28;
    private static final int MARCH = 31;
    private static final int APRIL = 30;
    private static final int MAY = 31;
    private static final int JUNE = 30;
    private static final int JULY = 31;
    private static final int AUGUST = 31;
    private static final int SEPTEMBER = 30;
    private static final int OCTOBER = 31;
    private static final int NOVEMBER = 30;
    private static final int DECEMBER = 31;
    private static final int[] MONTH = {0, JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER};

    public static void main(String[] args) throws IOException {
        int birthdayD = 17;
        int birthdayM = 12;
        int birthdayY = 2004;
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter current day: ");
        int dayCurrent = scanner.nextInt();

        System.out.print("Please enter current month: ");
        int monthCurrent = scanner.nextInt();

        System.out.print("Please enter current year: ");
        int yearCurrent = scanner.nextInt();

        result += MONTH[monthCurrent] - dayCurrent;

        if (monthCurrent > birthdayM) {
            for (int i = monthCurrent; i < MONTH.length; i++) {
                if (i == monthCurrent) continue;
                result += MONTH[i];
            }
            for (int i = 1; i < birthdayM; i++) {
                result += MONTH[i];
            }
            result += birthdayD;
            if (++yearCurrent % 4 == 0) {
                result++;
            }
        } else {
            for (int i = monthCurrent + 1; i < birthdayM; i++) {
                result += MONTH[i];
            }
            result += birthdayD;
        }
        System.out.println(result);
    }
}
