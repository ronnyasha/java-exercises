package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        System.out.println("=== English → Ukrainian Translator ===");

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("i", "я");
        translator.addWord("love", "люблю");
        translator.addWord("java", "джава");

        System.out.print("Скільки слів додати у словник? ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("English word: ");
            String en = scanner.nextLine();

            System.out.print("Ukrainian word: ");
            String ua = scanner.nextLine();

            translator.addWord(en, ua);
        }

        // переклад фрази
        System.out.print("\nВведи фразу англійською: ");
        String phrase = scanner.nextLine();

        String translated = translator.translate(phrase);
        System.out.println("Переклад: " + translated);
    }
}
