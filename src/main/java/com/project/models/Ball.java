package com.project.models;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    private int x, y, r;
    private int num;
    private Circle tempCircle;

    public Ball(int num) {
        Random random = new Random();
        x = random.nextInt(700) + 25;
        y = random.nextInt(350) + 25;
        this.num = num;
        tempCircle = new Circle(x, y, r, Color.RED);
    }

    public void draw(GraphicsContext graphicsContext2D) { // TODO: has to change with respect to using pictures
        System.out.println(num);
        graphicsContext2D.setFill(tempCircle.getFill());
        graphicsContext2D.fillOval(tempCircle.getCenterX(), tempCircle.getCenterY(), tempCircle.getRadius(),
                tempCircle.getRadius());
    }
}
