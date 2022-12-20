package ForTinkoff;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Имеем 6 возможных комбинаций переводов между валютами
 */
public class SecondTask {
    private static Set<String> compositions = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();

        recursion(x, y, z, a, b, c);
        System.out.println(compositions.size());
    }

    public static void recursion(int x, int y, int z,
                                 int a, int b, int c) {
        compositions.add( x + " " + y + " " + z);
        if (x - a >= 0) {
            // a from x -> b to y
            if (!compositions.contains((x-a) + " " + (y+b) + " " + z)) {
                recursion(x - a, y + b, z, a, b, c);
            }
            // a from x -> c to z
            if (!compositions.contains((x-a) + " " + y + " " + (z+c))) {
                recursion(x - a, y, z + c, a, b, c);
            }
        }
        if (y - b >= 0) {
            // b from y -> a to x
            if (!compositions.contains((x+a) + " " + (y-b) + " " + z)) {
                recursion(x + a, y - b, z, a, b, c);
            }
            // b from y -> c to z
            if (!compositions.contains(x + " " + (y-b) + " " + (z+c))) {
                recursion(x, y - b, z + c, a, b, c);
            }
        }
        if (z - c >= 0) {
            // c from z -> b to y
            if (!compositions.contains(x + " " + (y+b) + " " + (z-c))) {
                recursion(x, y + b, z - c, a, b, c);
            }
            // c from z -> a to x
            if (!compositions.contains((x+a) + " " + y + " " + (z-c))) {
                recursion(x + a, y, z - c, a, b, c);
            }
        }
    }
}
