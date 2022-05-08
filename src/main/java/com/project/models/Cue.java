package com.project.models;

import javafx.scene.image.ImageView;

import static com.project.App.getMouseX;
import static com.project.App.getMouseY;

public class Cue {

    private ImageView view;
    private double ballX, ballY;
    private Ball myBall;
    private static final double CANVAS_Y = 175 + 36, CANVAS_X = 25 + 36;

    public Cue(ImageView cue, Ball myBall) {
        view = cue;
        this.myBall = myBall;
    }

    public void orient() {
        if (getMouseY() < 200)
            return;
        view.setLayoutX(getMouseX() - view.getFitWidth() / 2);
        view.setLayoutY(getMouseY() - view.getFitWidth() / 2);
        ballX = myBall.getCenterX() + CANVAS_X;
        ballY = myBall.getCenterY() + CANVAS_Y;
        view.setRotate(-45 - getAngle(ballY, ballX, getMouseY(), getMouseX()));
    }

    private float getAngle(double x1, double y1, double x2, double y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if (angle < 0)
            angle += 360;
        return angle;
    }
}