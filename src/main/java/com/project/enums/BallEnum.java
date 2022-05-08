package com.project.enums;

import javafx.scene.shape.Circle;

public class BallEnum {

    int x0, y0, num;
    Circle circle;

    public BallEnum(int n) {
        this.num = n;
        switch (n) {
            case 1:
                break;
            default:
                break;
        }
    }


    public int getNum() {
        return num;
    }
}
