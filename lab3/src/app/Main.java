package app;

import controller.ShapeController;
import model.Shape;
import model.ShapeRepository;
import view.ShapeView;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = ShapeRepository.createSample(12, 42L); // 10+ елементів

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);

        controller.run(shapes);
    }
}
