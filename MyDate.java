public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate() {
        this(System.currentTimeMillis());
    }

    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }

    public void setDate(long elapsedTime) {
        // Convert elapsed time to seconds
        long seconds = elapsedTime / 1000;

        // Calculate the current year
        year = (int) (1970 + seconds / 31536000);

        // Calculate the remaining seconds after removing complete years
        seconds %= 31536000;

        // Calculate the current month and day
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        for (int i = 0; i < 12; i++) {
            int daysInThisMonth = daysInMonth[i];
            if (i == 1 && isLeapYear(year)) {
                daysInThisMonth++;
            }
            if (seconds < daysInThisMonth * 86400) {
                month = i + 1;
                day = (int) (seconds / 86400) + 1;
                break;
            }
            seconds -= daysInThisMonth * 86400;
        }
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);

        System.out
                .println("Date 1: Year=" + date1.getYear() + ", Month=" + date1.getMonth() + ", Day=" + date1.getDay());
        System.out
                .println("Date 2: Year=" + date2.getYear() + ", Month=" + date2.getMonth() + ", Day=" + date2.getDay());
    }
}
