package com.thinkbit.api.example.define;

public enum Direction {

    BUY(1), //买入
    SELL(2); //卖出

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public static Direction valueOf(int value) {
        switch (value) {
            case 1:
                return BUY;
            case 2:
                return SELL;
            default:
                throw new EnumConstantNotPresentException(Direction.class, String.valueOf(value));
        }
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        switch (value) {
            case 1:
                return Keyword.BUY;
            case 2:
                return Keyword.SELL;
            default:
                return null;
        }
    }

}
