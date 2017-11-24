package com.crazy.demo;

public class Param {
    public static final int TEXT = 0;
    public static final int INT = 1;
    public static final int DATA = 2;
    public static final int PHONE = 3;
    public static final int IDCARD = 4;
    private String name;
    private boolean necessary;
    private int type;

    public Param(String name, boolean necessary) {
        this.name = name;
        this.necessary = necessary;
    }

    public Param(String name, boolean necessary, int type) {
        this.name = name;
        this.necessary = necessary;
        this.type = type;
    }



    @Override
    public String toString() {
        return "Param{" +
                "name='" + name + '\'' +
                ", necessary=" + necessary +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
