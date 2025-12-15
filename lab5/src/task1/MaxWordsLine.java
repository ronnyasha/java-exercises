package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class MaxWordsLine {
    private MaxWordsLine() {}

    public static String findLineWithMaxWords(String filePath) throws IOException {
        String bestLine = "";
        int bestCount = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int words = countWords(line);
                if (words > bestCount) {
                    bestCount = words;
                    bestLine = line;
                }
            }
        }
        return bestLine;
    }

    private static int countWords(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return 0;
        return trimmed.split("\\s+").length;
    }
}
