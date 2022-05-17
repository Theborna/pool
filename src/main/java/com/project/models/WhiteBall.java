package com.project.models;

import com.project.SoundEffect;
import com.project.enums.BallEnum;

public class WhiteBall extends Ball {

    private boolean canBeHit;

    public WhiteBall(BallEnum ballEnum) {
        super(ballEnum);
    }

    public void getPushed(double angle, double Speed) {
        vx = -Speed * Math.sin(angle);
        vy = Speed * Math.cos(angle);
        SoundEffect.QUE.play();
    }
}
