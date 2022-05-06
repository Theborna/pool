package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import com.project.models.Ball;
import com.project.models.Cue;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public class Game {

    private Canvas canvas;
    private ArrayList<Ball> balls = new ArrayList<>(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().map(Ball::new).collect(Collectors.toList()));// list of balls
    private Ball myBall;
    private Cue cue;// current cue

    public Game(Canvas canvas, ImageView cue) {
        this.canvas = canvas;
        for (Ball ball : balls)
            ball.draw(this.canvas.getGraphicsContext2D());
        this.cue = new Cue(cue);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void run() {
        myBall = balls.get(8);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (Ball ball : balls) {
                    ball.move(canvas, balls);
                    ball.draw(canvas.getGraphicsContext2D());
                    cue.orient(canvas,myBall);
                }
            }

        };
        timer.schedule(task, 0, 16);
    }
}
