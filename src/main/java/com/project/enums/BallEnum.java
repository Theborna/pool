package com.project.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public enum BallEnum {
    WHITE(0), YELLOW(1), BLUE(2), RED(3), PURPLE(4), ORANGE(5), GREEN(6), MAROON(7), BLACK(8),
    CYAN(9), BROWN(10);

    int num;
    double x0, y0, r;
    Circle circle;

    BallEnum(int num) {
        this.num = num;
        r = 15;
        final double midX = 385, midY = 140 - r;
        final double multiplier = 2.5 * r;
        final double[] xPos = new double[10], yPos = new double[10];
        double j = 0, k = 0, kLast = 0;
        for (int i = 0; i < 10; i++, k++) {
            xPos[i] = midX + multiplier * (k) * Math.cos(Math.PI / 6)
                    + multiplier * (j) * Math.sin(Math.PI / 3) * Math.sin(Math.PI / 6);
            yPos[i] = midY + multiplier * (j) * Math.sin(Math.PI / 3) * Math.cos(Math.PI / 6)
                    - multiplier * (k) * Math.sin(Math.PI / 6);
            if (i == 3 || i == 6 || i == 8) {
                j++;
                k = kLast - 0.5;
                kLast = k + 1;
            }
        }
        if (num > 0) {
            x0 = xPos[num - 1];
            y0 = yPos[num - 1];
        } else {
            x0 = 150;
            y0 = midY;
        }
        // x0 = num * 70;
        // y0 = random.nextInt(350) + 25;
        circle = new Circle(x0, y0, r, Color.hsb(num * 360 / 9, 1, 1));
        final ArrayList<Color> colors = new ArrayList<Color>(List.of(Color.WHITE, Color.YELLOW, Color.BLUE, Color.RED,
                Color.PURPLE, Color.ORANGE, Color.GREEN, Color.MAROON, Color.BLACK, Color.CYAN, Color.BROWN));
        circle.setFill(colors.get(num));
    }

    public double getR() {
        return Double.valueOf(r);
    }

    public double getX0() {
        return Double.valueOf(x0);
    }

    public double getY0() {
        return Double.valueOf(y0);
    }

    public Circle getCircle() {
        return circle;
    }

    public int getNum() {
        return num;
    }

}
