import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] words = {
                "apple",
                "dog",
                "cat",
                "hello",
                "world",
                "abc",
                "noon"
        };

        String[] result = Arrays.stream(words)
                .filter(word -> word.chars().distinct().count() == word.length())
                .toArray(String[]::new);

        System.out.println(Arrays.toString(result));
    }
}
