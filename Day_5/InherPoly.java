class Circle {
    private double radius;
    private String color;

    public Circle() {
        this.radius = 1.0;
        this.color = "red";
    }

    public Circle(double radius) {
        this.radius = radius;
        this.color = "red";
    }

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle[radius=" + radius + ", color=" + color + "]";
    }
}

class Cylinder extends Circle {
    private double height;

    public Cylinder() {
        super();
        this.height = 1.0;
    }

    public Cylinder(double radius) {
        super(radius);
        this.height = 1.0;
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public Cylinder(double radius, double height, String color) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return super.getArea() * height;
    }
}

public class InherPoly {
    public static void main(String[] args) {
        Circle[] circles = {
            new Cylinder(12.34),
            new Cylinder(12.34, 10.0),
            new Cylinder(12.34, 10.0, "blue")
        };

        for (Circle circle : circles) {
            if (circle instanceof Cylinder) {
                Cylinder cylinder = (Cylinder) circle;
System.out.println("[Radius: " + cylinder.getRadius() +
                                   ", Height: " + cylinder.getHeight() +
                                   ", Color: " + cylinder.getColor()+"]");
                System.out.println("Area of circular region: " + circle.getArea());
                System.out.println("Volume of cylinder: " + cylinder.getVolume());
            }
        }
    }
}
