package app;

import task1.MaxWordsLine;
import task2.ShapesApp;
import task3.ShiftCipher;
import task4.TagCounter;

import java.util.Map;

public final class Menu {
    private final ShapesApp shapesApp = new ShapesApp();

    public void run() {
        while (true) {
            System.out.println("\n=== I/O Streams LAB ===");
            System.out.println("1) Task1: рядок з максимальною кількістю слів у файлі");
            System.out.println("2) Task2: збереження/читання набору об'єктів (serialization)");
            System.out.println("3) Task3: шифрування/дешифрування потоків (Filter*)");
            System.out.println("4) Task4: підрахунок частоти HTML-тегів по URL");
            System.out.println("0) Вихід");

            int cmd = Input.intValue("Команда: ");
            try {
                switch (cmd) {
                    case 1 -> task1();
                    case 2 -> shapesApp.run();
                    case 3 -> task3();
                    case 4 -> task4();
                    case 0 -> { return; }
                    default -> System.out.println("Невідома команда.");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    private void task1() throws Exception {
        String path = Input.line("Введи шлях до текстового файлу: ");
        String line = MaxWordsLine.findLineWithMaxWords(path);
        System.out.println("Рядок з максимальною кількістю слів:");
        System.out.println(line);
    }

    private void task3() throws Exception {
        System.out.println("\n1) Encrypt file");
        System.out.println("2) Decrypt file");
        int c = Input.intValue("Команда: ");

        String in = Input.line("Input file path: ");
        String out = Input.line("Output file path: ");
        char key = Input.charValue("Key char (наприклад A): ");

        if (c == 1) {
            ShiftCipher.encryptFile(in, out, key);
            System.out.println("Зашифровано у: " + out);
        } else if (c == 2) {
            ShiftCipher.decryptFile(in, out, key);
            System.out.println("Розшифровано у: " + out);
        } else {
            System.out.println("Невірна команда.");
        }
    }

    private void task4() throws Exception {
        String url = Input.line("Введи URL (наприклад https://example.com): ");
        Map<String, Integer> freq = TagCounter.countTagsByUrl(url);
        TagCounter.printLexOrder(freq);
        TagCounter.printByFrequency(freq);
    }
}
