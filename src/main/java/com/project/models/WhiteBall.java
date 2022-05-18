package com.project.models;

import com.project.App;
import com.project.Game;
import com.project.SoundEffect;
import com.project.enums.BallEnum;

public class WhiteBall extends Ball implements Moveable {

    public WhiteBall(BallEnum ballEnum) {
        super(ballEnum);
    }

    public void getPushed(double angle, double Speed) {
        vx = -Speed * Math.sin(angle);
        vy = Speed * Math.cos(angle);
        SoundEffect.QUE.play();
    }

    @Override
    public boolean pocketed(Game game) {
        if (super.pocketed(game)) {
            game.toggleMovingObject();
            App.mouseClicked = false;
            interacts = false;
        }
        return false;
    }

    @Override
    public void orient(Game game) {
        if (App.getMouseY() < 125)
            return;
        double mouseX = App.getMouseX(), mouseY = App.getMouseY();
        x = mouseX - Cue.CANVAS_X;
        y = mouseY - Cue.CANVAS_Y;
        if (App.mouseClicked) {
            game.toggleMovingObject();
            App.mouseClicked = false;
            interacts = true;
        }
    }
}
