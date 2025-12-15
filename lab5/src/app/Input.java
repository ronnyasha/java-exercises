package app;

import java.util.Scanner;

public final class Input {
    private static final Scanner SC = new Scanner(System.in);

    private Input() {}

    public static String line(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    public static int intValue(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(SC.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Некоректне число. Спробуй ще раз.");
            }
        }
    }

    public static char charValue(String prompt) {
        while (true) {
            String s = line(prompt);
            if (!s.isEmpty()) return s.charAt(0);
            System.out.println("Введи хоча б 1 символ.");
        }
    }
}
