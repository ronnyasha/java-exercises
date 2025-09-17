import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

/**
 * Шукає слова, де всі символи різні.
 * Ігнорує пунктуацію на краях; дефіс і апостроф усередині не враховує.
 * Регістр можна вимкнути/увімкнути через прапорець.
 */
public class WordsWithUniqueCharacters {

    private static final boolean IGNORE_CASE = true; // true — не враховуємо регістр

    public static void main(String[] args) {
        System.out.println("=== Пошук слів з унікальними символами ===");
        System.out.println("Налаштування: регістронезалежна перевірка " + (IGNORE_CASE ? "УВІМКНЕНА" : "ВИМКНЕНА"));
        System.out.print("Введіть текст: ");

        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("\nПомилка: текст не введено!");
                return;
            }

            System.out.println("\nОбробка тексту: '" + input + "'");

            String[] result = findWordsWithAllUniqueChars(input);
            printResults(result);
        }
    }

    // Розбиває текст на слова, чистить краї, залишає тільки слова з унікальними символами
    public static String[] findWordsWithAllUniqueChars(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }

        return Arrays.stream(text.trim().split("\\s+"))
                .map(WordsWithUniqueCharacters::removeEdgePunctuation)
                .filter(word -> !word.isEmpty())
                .filter(WordsWithUniqueCharacters::hasAllUniqueChars)
                .toArray(String[]::new);
    }

    // Обрізає з початку/кінця все, що не літера і не цифра
    private static String removeEdgePunctuation(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        int start = 0;
        while (start < word.length() && !Character.isLetterOrDigit(word.charAt(start))) {
            start++;
        }

        int end = word.length() - 1;
        while (end >= start && !Character.isLetterOrDigit(word.charAt(end))) {
            end--;
        }

        return start <= end ? word.substring(start, end + 1) : "";
    }

    // Перевіряє чи всі символи у слові зустрічаються один раз
    // Дефіс і апостроф усередині слова ігноруємо
    private static boolean hasAllUniqueChars(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        String processedWord = IGNORE_CASE ? word.toLowerCase(Locale.ROOT) : word;
        Set<Character> seen = new HashSet<>();

        for (int i = 0; i < processedWord.length(); i++) {
            char c = processedWord.charAt(i);

            // Пропускаємо дефіси та апострофи всередині слів
            if (c == '-' || c == '\'' || c == '’') {
                continue;
            }

            if (!seen.add(c)) {
                return false;
            }
        }
        return true;
    }

    private static void printResults(String[] result) {
        System.out.println("\n════════════════ РЕЗУЛЬТАТ ════════════════");

        if (result.length == 0) {
            System.out.println("Слів з унікальними символами не знайдено");
        } else {
            System.out.println("Знайдено слів: " + result.length);
            System.out.println("Масив результатів: " + Arrays.toString(result));
            System.out.println("Список слів: " + String.join(", ", result));
        }

        System.out.println("Налаштування: регістронезалежна перевірка " + (IGNORE_CASE ? "УВІМКНЕНА" : "ВИМКНЕНА"));
        System.out.println("══════════════════════════════════════════=");
    }
}
