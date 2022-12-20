package ForTinkoff;

import java.util.Scanner;

/**
 * Необходимо:
 * - Считать дано
 * - определить, является ли слово некрасивым
 * - получить слова и соответствующие им раскраски
 * - посчитать число некрасивых слов
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lettersAmount = sc.nextInt();
        String agencyName = getAgencyName(lettersAmount, sc);
        String colorMap = sc.nextLine();
        String[] words = getColoredWords(agencyName, colorMap);
        System.out.println(countBadWords(words));
    }

    public static String getAgencyName(int letters, Scanner sc) {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < letters) {
            stringBuilder.append(sc.nextLine());
        }
        return stringBuilder.toString();
    }

    public static int badWord(String coloredWord) {
        for (int i = 0; i < coloredWord.length()-1; i++) {
            if (coloredWord.charAt(i) == coloredWord.charAt(i+1)) {
                return 1;
            }
        }
        return 0;
    }

    public static String[] getColoredWords(String agencyName,
                                           String colorMap) {
        String[] words = agencyName.split(" ");
        String[] coloredWords = new String[words.length];
        int index = 0;
        for (int i = 0; i < words.length; i ++) {
            StringBuilder curString = new StringBuilder();
            for (int j = 0; j < words[i].length(); j++) {
                curString.append(colorMap.charAt(index++));
            }
            coloredWords[i] = curString.toString();
        }
        return coloredWords;
    }

    public static int countBadWords(String[] coloredWords) {
        int amount = 0;
        for (String word: coloredWords) {
            amount += badWord(word);
        }
        return amount;
    }
}
