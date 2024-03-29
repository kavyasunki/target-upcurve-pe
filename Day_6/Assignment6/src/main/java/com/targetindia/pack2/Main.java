package com.targetindia.pack2;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[10];
        shapes[0] = new Circle(5.0, "Red", true);
        shapes[1] = new Rectangle(4.0, 6.0, "Blue", false);
        shapes[2] = new Square(3.0, "Green", true);
        shapes[3] = new Circle(2.5, "Yellow", true);
        shapes[4] = new Rectangle(5.0, 7.0, "Orange", false);
        shapes[5] = new Square(4.0, "Purple", true);
        shapes[6] = new Circle(3.0, "Pink", false);
        shapes[7] = new Rectangle(3.5, 5.5, "Brown", true);
        shapes[8] = new Square(6.0, "Gray", false);
        shapes[9] = new Circle(4.0, "Cyan", true);

        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.println("Area: " + shape.getArea());
            System.out.println("Perimeter: " + shape.getPerimeter());
            System.out.println();
        }
    }
}
