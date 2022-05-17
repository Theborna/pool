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
        Random random = new Random();
        r = 15;
        final int midX = 435, midY = 140;
        final double multiplier = 2.5 * r;
        final double[] xPos = new double[10], yPos = new double[10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                xPos[i + j] = (i - 3) * multiplier + midX;
                yPos[i + j] = (j - 2) * multiplier + midY;
            }
        }
        if (num > 0) {
            x0 = xPos[num - 1];
            y0 = yPos[num - 1];
        } else {
            x0 = 100;
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
        return r;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public Circle getCircle() {
        return circle;
    }

    public int getNum() {
        return num;
    }

    public static BallEnum get(int num) {
        switch (num) {
            case 0:
                return WHITE;
            case 1:
                return YELLOW;
            case 2:
                return BLUE;
            case 3:
                return RED;
            case 4:
                return PURPLE;
            case 5:
                return ORANGE;
            case 6:
                return GREEN;
            case 7:
                return MAROON;
            case 8:
                return BLACK;
            case 9:
                return CYAN;
            case 10:
                return BROWN;
            default:
                return WHITE;
        }
    }
}
