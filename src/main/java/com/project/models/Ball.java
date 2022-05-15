package com.project.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.project.enums.BallEnum;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    /**
     *
     */
    private static final double MIN_SPEED = 0.1;
    private int num;
    protected double vx;
    protected double vy;
    private double x;
    private double y;
    private double r;
    private BallEnum ballEnum;
    private Circle tempCircle;

    public Ball(BallEnum ballEnum) {
        this.ballEnum = ballEnum;
        num = ballEnum.getNum();
        Random random = new Random();
        x = (ballEnum.getX0());
        y = (ballEnum.getY0());
        // if (false) {
        // vx = random.nextInt(40);
        // vy = random.nextInt(40);
        // }
        r = ballEnum.getR();
        tempCircle = ballEnum.getCircle();
    }

    public void draw(GraphicsContext graphicsContext2D) { // TODO: has to change with respect to using pictures
        graphicsContext2D.setFill(Color.BLACK);
        graphicsContext2D.fillOval(x, y, 2 * r, 2 * r);
        graphicsContext2D.setFill(tempCircle.getFill());
        graphicsContext2D.fillOval(x + 1, y + 1, 2 * r - 2, 2 * r - 2);
        graphicsContext2D.setFill(Color.WHITE);
        graphicsContext2D.fillOval(x + r / 2, y + r / 2, r, r);
        graphicsContext2D.setFill(Color.BLACK);
        graphicsContext2D.fillText(Integer.valueOf(num).toString(), getCenterX(), getCenterY(), 32);
    }

    public void move(Canvas canvas, List<Ball> balls) {
        x += vx;
        y += vy;
        vx *= 0.95;
        vy *= 0.95;
        wallCollision(canvas);
        ballCollision(balls);
    }

    private void wallCollision(Canvas canvas) {
        if (x < 0 || x > canvas.getWidth() - 2 * r) {
            vx = -0.9 * vx;
            if (Math.abs(x) > Math.abs(x - canvas.getWidth() + 2 * r))
                x = (int) (canvas.getWidth() - 2 * r - 1);
            else
                x = 1;
        }
        if (y < 0 || y > canvas.getHeight() - 2 * r) {
            vy = -0.9 * vy;
            if (Math.abs(y) > Math.abs(y - canvas.getHeight() + 2 * r))
                y = (int) (canvas.getHeight() - 2 * r - 1);
            else
                y = 1;
        }
    }

    private void ballCollision(List<Ball> balls) {
        for (Ball ball : balls)
            if (!ball.equals(this)) {
                if (ball.intersects(this)) {
                    double dotSpeed = ((ball.vx - vx) * (ball.x - x) + (ball.vy - vy) * (ball.y - y));
                    vx += (ball.x - x) * (dotSpeed / distanceSquared(ball));
                    vy += (ball.y - y) * (dotSpeed / distanceSquared(ball));
                    ball.vx -= (ball.x - x) * (dotSpeed / distanceSquared(ball));
                    ball.vy -= (ball.y - y) * (dotSpeed / distanceSquared(ball));
                }
                while (ball.intersects(this)) {
                    x += vx;
                    y += vy;
                    ball.x += ball.vx;
                    ball.y += ball.vy;
                }
            }
    }

    private boolean intersects(Ball ball) {
        return ((x - ball.x) * (x - ball.x) + (y - ball.y) * (y - ball.y)) < ((r + ball.r) * (r + ball.r));
    }

    private double distanceSquared(Ball ball) {
        return ((x - ball.x) * (x - ball.x) + (y - ball.y) * (y - ball.y));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + num);
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

    public double getNum() {
        return num;
    }

    public double getCenterX() {
        return x + r;
    }

    public double getCenterY() {
        return y + r;
    }

    public boolean isSteady() {
        return vx * vx + vy * vy < MIN_SPEED * MIN_SPEED;
    }

}
