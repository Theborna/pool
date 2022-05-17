package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import com.project.controllers.SecondaryController;
import com.project.enums.BallEnum;
import com.project.models.Ball;
import com.project.models.BlackBall;
import com.project.models.Cue;
import com.project.models.WhiteBall;

import javafx.scene.canvas.Canvas;

public class Game {

    private Canvas canvas;
    private ArrayList<Ball> balls = new ArrayList<>(/** list of balls */
            List.of(1, 2, 3, 4, 5, 6, 7, 9, 10).stream().map(BallEnum::get).map(Ball::new)
                    .collect(Collectors.toList()));
    private WhiteBall whiteBall = new WhiteBall(BallEnum.WHITE);
    private Ball blackBall = new BlackBall(BallEnum.BLACK);
    private Cue cue;// current cue
    private SecondaryController controller;
    private int turn;

    public Game(SecondaryController controller) {
        turn = 1;
        controller.setTurn("hiiiii");
        this.controller = controller;
        this.canvas = controller.getGameCanvas();
        balls.add(blackBall);
        balls.add(whiteBall);
        for (Ball ball : balls)
            ball.draw(this.canvas.getGraphicsContext2D());
        this.cue = new Cue(controller.getCue(), whiteBall, controller.getStrength());

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void run() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (Ball ball : balls) {
                    ball.move(canvas, balls);
                    ball.draw(canvas.getGraphicsContext2D());
                }
                cue.orient();
                cue.checkHit(balls);
                // controller.setTurn(controller.getName(turn));
                cue.showPath(canvas.getGraphicsContext2D());
                cue.hit();
            }

        };
        App.getGameThread().schedule(task, 0, 16);
    }

}
