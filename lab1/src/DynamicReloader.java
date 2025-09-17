import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

public class DynamicReloader {

    private static final String CLASS_NAME = "TestModule";
    private static final Path   SOURCE     = Path.of("lab1", "src", "TestModule.java");
    private static final Path   OUTPUT     = Path.of("lab1", "out_reload");

    public static void main(String[] args) throws Exception {
        if (ToolProvider.getSystemJavaCompiler() == null) {
            System.err.println("Потрібен JDK, не JRE.");
            return;
        }

        Files.createDirectories(OUTPUT);

        System.out.println("=== Dynamic class reloading demo ===");

        long lastModified = 0;

        while (true) {
            File javaFile = SOURCE.toFile();
            long ts = javaFile.lastModified();

            if (ts > lastModified) {
                lastModified = ts;

                System.out.println("\nЗнайдено зміни, компілюю у: " + OUTPUT.toAbsolutePath());
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

                int result = compiler.run(null, null, null, "-d", OUTPUT.toString(), SOURCE.toString());
                if (result != 0) {
                    System.out.println("Помилка компіляції! Перевір синтаксис TestModule.java");
                } else {
                    try (URLClassLoader loader = new URLClassLoader(new URL[]{OUTPUT.toUri().toURL()}, null)) {
                        Class<?> clazz = loader.loadClass(CLASS_NAME);
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        System.out.println("Новий клас: " + instance);
                    }
                }
            }

            Thread.sleep(2000);
        }
    }
}
