package com.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.project.controllers.SecondaryController;
import com.project.enums.BallEnum;
import com.project.models.Ball;
import com.project.models.Cue;
import com.project.models.Moveable;
import com.project.models.WhiteBall;

import javafx.scene.canvas.Canvas;

public class Game {
    public static double CANVAS_WIDTH, CANVAS_HEIGHT, CANVAS_x, CANVAS_Y;
    public static TimerTask task;
    public static boolean first = true;
    private HashSet<Ball> balls = new HashSet<>(/** list of balls */
            Stream.of(BallEnum.values()).filter(ball -> ball.getNum() != 0).map(Ball::new)
                    .collect(Collectors.toList()));
    private HashSet<Ball> pocketedBalls = new HashSet<>();
    private Canvas canvas;
    private WhiteBall whiteBall = new WhiteBall(BallEnum.WHITE);
    private Cue cue;// current cue
    private SecondaryController controller;
    private int turn;
    private Moveable movingObject;

    public Game(SecondaryController controller) {
        first = true;
        turn = 1;
        balls.add(whiteBall);
        controller.setTurn("hiiiii");
        this.controller = controller;
        this.canvas = controller.getGameCanvas();
        CANVAS_WIDTH = canvas.getWidth();
        CANVAS_HEIGHT = canvas.getHeight();
        for (Ball ball : balls)
            ball.draw(this.canvas.getGraphicsContext2D());
        this.cue = new Cue(controller.getCue(), whiteBall, controller.getStrength());
        movingObject = cue;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void run() {
        task = new TimerTask() {

            @Override
            public void run() {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (Ball ball : balls) {
                    ball.move(canvas, balls);
                    ball.draw(canvas.getGraphicsContext2D());
                    if (ball.pocketed(Game.this))
                        pocketedBalls.add(ball);
                }
                processPocketedBalls();
                movingObject.orient(Game.this);
                cue.checkHit(balls);
                cue.showPath(canvas.getGraphicsContext2D());
                cue.hit();
            }

        };
        App.getGameThread().schedule(task, 0, 16);
    }

    private void processPocketedBalls() {
        try {
            balls.removeAll(pocketedBalls);
            if (balls.size() == 1)
                controller.restart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggleMovingObject() {
        if (movingObject.equals(cue))
            movingObject = whiteBall;
        else if (movingObject.equals(whiteBall))
            movingObject = cue;
    }

    public enum Pocket {
        UP_LEFT(0, 0), UP_MID(0.5, 0), UP_RIGHT(1, 0), DOWN_LEFT(0, 1), DOWN_MID(0.5, 1), DOWN_RIGHT(1, 1);

        double x, y, r;

        private Pocket(double x, double y) {
            this.x = x * CANVAS_WIDTH;
            this.y = y * CANVAS_HEIGHT;
            r = 20;
        }

        public boolean pocketed(Ball ball) {
            return (x - ball.getCenterX()) * (x - ball.getCenterX())
                    + (y - ball.getCenterY()) * (y - ball.getCenterY()) < (r + ball.getR()) * (r + ball.getR());
        }
    }

}
