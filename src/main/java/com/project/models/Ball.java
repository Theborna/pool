package com.project.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    private int x, y, r;
    private double vx, vy;
    private int num;
    private Circle tempCircle;
    public static ArrayList<List<Integer>> initialPos;

    public Ball(int num) {
        Random random = new Random();
        x = num * 70;
        y = random.nextInt(350) + 25;
        vx = random.nextInt(40);
        vy = random.nextInt(40);
        vx = 0;
        vy = 0;
        r = 15;
        this.num = num;
        tempCircle = new Circle(x, y, r, Color.hsb(num * 360 / 9, 1, 1));
        if (num == 9) {
            tempCircle.setFill(Color.WHITE);
        }
    }

    public void draw(GraphicsContext graphicsContext2D) { // TODO: has to change with respect to using pictures
        graphicsContext2D.setFill(tempCircle.getFill());
        graphicsContext2D.fillOval(x, y, 2 * r, 2 * r);
    }

    public void move(Canvas canvas, List<Ball> balls) {
        x += vx;
        y += vy;
        wallCollision(canvas);
        ballCollision(balls);
    }

    private void wallCollision(Canvas canvas) {
        if (x < 0 || x > canvas.getWidth() - r) {
            vx = -0.9 * vx;
            if (Math.abs(x) > Math.abs(x - canvas.getWidth() + r))
                x = (int) (canvas.getWidth() - r - 1);
            else
                x = 1;
        }
        if (y < 0 || y > canvas.getHeight() - r) {
            vy = -0.9 * vy;
            if (Math.abs(y) > Math.abs(y - canvas.getHeight() + r))
                y = (int) (canvas.getHeight() - r - 1);
            else
                y = 1;
        }
    }

    private void ballCollision(List<Ball> balls) {
        for (Ball ball : balls) {
            if (!ball.equals(this)) {
                if (((x - ball.x) * (x - ball.x) + (y - ball.y) * (y - ball.y)) < ((r + ball.r) * (r + ball.r))) {
                    double tempVx = vx;
                    double tempVy = vy;
                    vx = ball.vx;
                    vy = ball.vy;
                    ball.vx = tempVx;
                    ball.vy = tempVy;
                }
                while (((x - ball.x) * (x - ball.x) + (y - ball.y) * (y - ball.y)) < ((r + ball.r) * (r + ball.r))) {
                    x += vx;
                    y += vy;
                    ball.x += ball.vx;
                    ball.vx += ball.vy;
                }
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + num;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ball other = (Ball) obj;
        if (num != other.num)
            return false;
        return true;
    }

    public int getNum() {
        return num;
    }

    public int getCenterX() {
        return x + r;
    }

    public int getCenterY() {
        return y + r;
    }
}
