package app;

import java.util.HashMap;
import java.util.Map;

public class Translator {

    private final Map<String, String> dictionary = new HashMap<>();

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translate(String phrase) {
        StringBuilder result = new StringBuilder();
        String[] words = phrase.toLowerCase().split("\\s+");

        for (String word : words) {
            if (dictionary.containsKey(word)) {
                result.append(dictionary.get(word));
            } else {
                result.append("[").append(word).append("]");
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}
