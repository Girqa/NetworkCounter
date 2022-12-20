package ForTinkoff;


import java.util.Scanner;

public class ThirdTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int downBorder = (int) Math.sqrt(n);
        int topBorder = n/2 + 1;

        int minLcm = n*n;
        int resA = downBorder;
        int resB = n - downBorder;

        for (int a = downBorder; a < topBorder; a++) {
            int curLcm = lcm(a, n-a);
            if (curLcm < minLcm) {
                minLcm = curLcm;
                resA = a;
                resB = n - a;
            }
        }
        System.out.println(resA + " " + resB);
    }

    public static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }


    public static int lcm(int a, int b)
    {
        return (a / gcd(a, b)) * b;
    }
}
