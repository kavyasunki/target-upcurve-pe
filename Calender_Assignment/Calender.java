import java.util.Scanner;
public class Calender {
    static int daysinmonth(int m, int y)
    {
        if(m==1||m==3||m==5||m==7||m==8||m==10||m==12) return 31;
        else if(m==2)
        {
            if(Leap(y)) return 29;
            else return 28;
        }
        return 30;
    }
    public static boolean Leap(int y)
    {
        if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0)
            return true;

        return false;
    }
    public static void main(String[] args)
    {
        int y, m;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Year: ");
        y = sc.nextInt();
        System.out.print("Enter Month: ");
        m = sc.nextInt();
        if(y>1900 && m<12 && m>0)
        {
            int dys = daysinmonth(m, y), day;
            day = 1;
            for (int i = 1900; i < y; i++) {
                if (Leap(i)) day += 2;
                else day += 1;
            }
            for (int i = 1; i < m; i++) {
                day = day + (daysinmonth(i, y) % 7);
            }
            day = day % 7;
            int n = 1;
            System.out.println("Su Mo Tu We Th Fr Sa");
            for (int i = 0; i < day; i++) {
                System.out.print("   ");
            }
            for (int i = day; i < 7; i++) {
                System.out.print(n + "  ");
                n++;
            }
            System.out.println();
            while (n <= dys) {
                for (int i = 1; i <= 7; i++) {
                    if (n <= dys) {
                        if (n < 10) System.out.print(n + "  ");
                        else System.out.print(n + " ");
                        n++;
                    }
                }
                System.out.println();
            }
        }
        else System.out.println("Invalid year or month");
    }
}
