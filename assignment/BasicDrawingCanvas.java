abstract class Shape {
    private static int counter = 0;
    private final String shapeId;

    Shape() {
        shapeId = "SH-" + (++counter);
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
        applyScale(Math.sqrt(xFactor * yFactor));
    }

    protected abstract void applyScale(double factor);

    public String getShapeId() {
        return shapeId;
    }

    public static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    protected void applyScale(double factor) {
        radius *= factor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    public double calculateArea() {
        return side * side;
    }

    protected void applyScale(double factor) {
        side *= factor;
    }
}

public class BasicDrawingCanvas {
    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.printf("%.2f%n", c.calculateArea());
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        Shape.printArea(c);
        System.out.println(c.getShapeId());
    }
}
