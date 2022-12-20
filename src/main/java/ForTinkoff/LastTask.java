package ForTinkoff;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LastTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Для формирования результирующей строки
        StringBuilder stringBuilder = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        // Пока множество пустое - ответ 0
        int request = sc.nextInt();
        set.add(request);
        stringBuilder.append(0+"\n");

        // При добавлении Нового числа сверяем максимальный и текущий XOR. Храним последний максимальный.
        int maxXor = 0;
        for (int i = 1; i < n; i++) {
            request = sc.nextInt();
            if (!set.contains(request)) {
                int curXor = getMaxXorForNumber(set, request);
                set.add(request);
                if (curXor > maxXor) {
                    maxXor = curXor;
                }
            }
            stringBuilder.append(maxXor+"\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static int getMaxXorForNumber(Set<Integer> integerSet, int number) {
        int max = 0;
        for (int val: integerSet) {
            int xor = val ^ number;
            if (xor > max) {
                max = xor;
            }
        }
        return max;
    }
}
