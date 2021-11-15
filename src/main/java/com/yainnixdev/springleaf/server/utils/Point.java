package com.yainnixdev.springleaf.server.utils;

public class Point {
    private Float x;
    private Float y;

    public Point(){

    }
    public Point(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }


}