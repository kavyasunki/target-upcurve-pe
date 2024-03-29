package com.targetindia.pack2;
public class Square extends Rectangle {
    private double side;

    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "A Square with side=" + side + ", which is a subclass of " + super.toString();
    }
}