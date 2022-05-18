package com.project.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Collection;

import com.project.App;
import com.project.Game;

public class Cue implements Moveable {

    private ImageView view;
    private boolean getClicks;
    private double ballX, ballY;
    private double mouseX, mouseY;
    private WhiteBall myBall;
    private Slider strength;
    public static final double CANVAS_Y = 175 + 33, CANVAS_X = 75 + 36;
    private static final double MAX_DISTANCE = 300;
    private double power;
    private static final double PUSH_SPEED = 30;

    public Cue(ImageView cue, WhiteBall myBall, Slider strength) {
        view = cue;
        this.myBall = myBall;
        this.strength = strength;
    }

    public void orient(Game game) {
        if (App.getMouseY() < 125)
            return;
        mouseX = App.getMouseX();
        mouseY = App.getMouseY();
        view.setLayoutX(mouseX - view.getFitWidth() / 2);
        view.setLayoutY(mouseY - view.getFitWidth() / 2);
        ballX = myBall.getCenterX() + CANVAS_X;
        ballY = myBall.getCenterY() + CANVAS_Y;
        double distanceRatio = (Math.sqrt(ballDistanceSquared())) / ((double) MAX_DISTANCE);
        power = (distanceRatio - 0.5) * 2;
        if (power < 0.0)
            power = 0;
        if (power > 1.0)
            power = 1;
        view.setRotate(-45 - getAngle(ballY, ballX, mouseY, mouseX));
        strength.setValue(power * 100);
    }

    public void showPath(GraphicsContext graphicsContext2D) {
        if (mouseX != App.getMouseX())
            return;
        double angle = getAngle(ballY, ballX, mouseY, mouseX), spaceBetween = power * myBall.getR();
        double dx = spaceBetween * Math.sin(angle * Math.PI / 180), dy = spaceBetween * Math.cos(angle * Math.PI / 180);
        graphicsContext2D.setFill(Color.WHITE);
        for (int i = 1; i < 8; i++) {
            graphicsContext2D.fillOval(myBall.getCenterX() - 3 - i * dx, myBall.getCenterY() - 3 - i * dy,
                    10 / Math.sqrt(i),
                    10 / Math.sqrt(i));
        }
    }

    private double ballDistanceSquared() {
        return (mouseX - ballX) * (mouseX - ballX) + (mouseY - ballY) * (mouseY - ballY);
    }

    private float getAngle(double x1, double y1, double x2, double y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if (angle < 0)
            angle += 360;
        return angle;
    }

    public boolean checkHit(Collection<Ball> balls) {
        getClicks = true;
        for (Ball ball : balls) {
            if (!ball.isSteady())
                getClicks = false;
        }
        return getClicks;
    }

    public void hit() {
        if (getClicks && App.mouseClicked)
            myBall.getPushed((180 - getAngle(ballY, ballX, mouseY, mouseX)) * Math.PI / 180, PUSH_SPEED * power);
    }
}