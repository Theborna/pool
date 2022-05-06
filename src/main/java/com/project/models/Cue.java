package com.project.models;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import static com.project.App.getMouseX;
import static com.project.App.getMouseY;

public class Cue {

    private ImageView view;

    public Cue(ImageView cue) {
        view = cue;
    }

    public void orient(Canvas canvas, Ball myBall) {
        if (getMouseY() < 200)
            return;
        view.setLayoutX(getMouseX() - view.getFitWidth() / 2);
        view.setLayoutY(getMouseY() - view.getFitWidth() / 2);
        view.setRotate(45 - getAngle( myBall.getCenterY(), myBall.getCenterX(),(int) getMouseY(), (int) getMouseX()));
    }

    private float getAngle(int x1, int y1, int x2, int y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if (angle < 0)
            angle += 360;
        return angle;
    }
}