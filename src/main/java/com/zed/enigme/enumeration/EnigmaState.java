package com.zed.enigme.enumeration;

public enum EnigmaState {

    BEGINNING(1),
    END(2);

    private int num;

    private EnigmaState(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
