package task4;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TagCounter {
    private TagCounter() {}

    private static final Pattern TAG = Pattern.compile("<\\s*([a-zA-Z][a-zA-Z0-9]*)\\b");

    public static Map<String, Integer> countTagsByUrl(String url) throws Exception {
        String html = fetch(url);
        Matcher m = TAG.matcher(html);

        Map<String, Integer> freq = new HashMap<>();
        while (m.find()) {
            String tag = m.group(1).toLowerCase();
            freq.merge(tag, 1, Integer::sum);
        }
        return freq;
    }

    public static void printLexOrder(Map<String, Integer> freq) {
        System.out.println("\nТеги (лексикографічно зростання):");
        freq.keySet().stream().sorted().forEach(k -> System.out.println(k + " = " + freq.get(k)));
    }

    public static void printByFrequency(Map<String, Integer> freq) {
        System.out.println("\nТеги (за зростанням частоти):");
        freq.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String,Integer>>comparingInt(Map.Entry::getValue)
                        .thenComparing(Map.Entry::getKey))
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
    }

    private static String fetch(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .header("User-Agent", "Java HttpClient")
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
}
