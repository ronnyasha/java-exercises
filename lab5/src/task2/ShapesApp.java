package task2;

import app.Input;
import io.FileService;
import task2.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ShapesApp {

    private final List<Shape> shapes = new ArrayList<>();

    public ShapesApp() {
        // демо-набір (можеш замінити на свій із Task 3 Simple OOP)
        shapes.add(new Circle("Red", 3));
        shapes.add(new Rectangle("Black", 2, 5));
        shapes.add(new Triangle("Green", 4, 6));
        shapes.add(new Circle("Blue", 2));
        shapes.add(new Rectangle("Yellow", 3, 3));
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Task2: Shapes (save/load + search) ---");
            System.out.println("1) Показати набір");
            System.out.println("2) Пошук: сумарна площа за типом");
            System.out.println("3) Пошук: сумарна площа за кольором");
            System.out.println("4) Зберегти у файл (ObjectOutputStream)");
            System.out.println("5) Завантажити з файлу (ObjectInputStream)");
            System.out.println("0) Назад");

            int cmd = Input.intValue("Команда: ");
            try {
                switch (cmd) {
                    case 1 -> printAll();
                    case 2 -> sumByType();
                    case 3 -> sumByColor();
                    case 4 -> saveToFile();
                    case 5 -> loadFromFile();
                    case 0 -> { return; }
                    default -> System.out.println("Невідома команда.");
                }
            } catch (Exception e) { // вимога try-catch
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    private void printAll() {
        if (shapes.isEmpty()) {
            System.out.println("(порожньо)");
            return;
        }
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println((i + 1) + ") " + shapes.get(i));
        }
    }

    private void sumByType() {
        String t = Input.line("Введи тип (RECTANGLE/TRIANGLE/CIRCLE): ").toUpperCase();
        ShapeType type = ShapeType.valueOf(t);

        double sum = 0;
        for (Shape s : shapes) {
            if (matchesType(s, type)) sum += s.calcArea();
        }
        System.out.printf("Сума площ для %s = %.2f%n", type, sum);
    }

    private void sumByColor() {
        String color = Input.line("Введи колір: ");
        double sum = 0;
        for (Shape s : shapes) {
            if (s.getColor().equalsIgnoreCase(color)) sum += s.calcArea();
        }
        System.out.printf("Сума площ для color='%s' = %.2f%n", color, sum);
    }

    private boolean matchesType(Shape s, ShapeType type) {
        return switch (type) {
            case RECTANGLE -> s instanceof Rectangle;
            case TRIANGLE -> s instanceof Triangle;
            case CIRCLE -> s instanceof Circle;
        };
    }

    private void saveToFile() throws IOException {
        String path = Input.line("Шлях до файлу для збереження (наприклад E:\\\\data\\\\shapes.bin): ");
        FileService.saveObject(path, (ArrayList<Shape>) new ArrayList<>(shapes));
        System.out.println("Збережено у: " + path);
    }

    private void loadFromFile() throws IOException, ClassNotFoundException {
        String path = Input.line("Шлях до файлу для читання: ");
        @SuppressWarnings("unchecked")
        ArrayList<Shape> loaded = FileService.loadObject(path, ArrayList.class);
        shapes.clear();
        shapes.addAll(loaded);
        System.out.println("Завантажено. К-ть об'єктів: " + shapes.size());
    }
}
