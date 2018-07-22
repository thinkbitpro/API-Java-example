package com.thinkbit.api.example.define;

import org.joda.time.DateTime;

public enum Period {

    MINUTE_1(0), MINUTE_5(1), MINUTE_15(2), MINUTE_30(3), HOUR_1(4), HOUR_2(5), HOUR_4(6), HOUR_6(7), HOUR_12(8), DAY(9), WEEK(10), MONTH(11);

    public static final String[] KEYWORDS = new String[]{Keyword.MINUTE_1, Keyword.MINUTE_5, Keyword.MINUTE_15, Keyword.MINUTE_30, Keyword.HOUR_1, Keyword.HOUR_2, Keyword.HOUR_4, Keyword.HOUR_6, Keyword.HOUR_12, Keyword.DAY, Keyword.WEEK, Keyword.MONTH};
    public static final Period[] PERIODS = new Period[]{Period.MINUTE_1, Period.MINUTE_5, Period.MINUTE_15, Period.MINUTE_30, Period.HOUR_1, Period.HOUR_2, Period.HOUR_4, Period.HOUR_6, Period.HOUR_12, Period.DAY, Period.WEEK, Period.MONTH};
    public static final String[] CRONS = new String[]{"1 * * * * ? *", "1 0/5 * * * ? *", "1 0/15 * * * ? *", "1 0/30 * * * ? *", "1 0 0/1 * * ? *", "1 0 0/2 * * ? *", "1 0 0/4 * * ? *", "1 0 0/6 * * ? *", "1 0 0/12 * * ? *", "1 0 0 1/1 * ? *", "1 0 0 ? * 1#1 *", "1 0 0 1 1/1 ? *"};
    private int value = 0;

    Period(int value) {
        this.value = value;
    }

    public static Period valueOf(int value) {
        return PERIODS[value];
    }

    public static Period parse(String value) {
        for (int i = 0; i < KEYWORDS.length; i++) {
            if (KEYWORDS[i].equals(value))
                return PERIODS[i];
        }
        throw new EnumConstantNotPresentException(Period.class, value);
    }

    public static long computeScore(long unixtime, Period period) {
        DateTime time = new DateTime(unixtime);
        DateTime expect = null;
        switch (period) {
            case MINUTE_1:
                expect = time.withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case MINUTE_5:
                expect = time.withMinuteOfHour((int) (time.getMinuteOfHour() / 5.0) * 5).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case MINUTE_15:
                expect = time.withMinuteOfHour((int) (time.getMinuteOfHour() / 15.0) * 15).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case MINUTE_30:
                expect = time.withMinuteOfHour((int) (time.getMinuteOfHour() / 30.0) * 30).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case HOUR_1:
                expect = time.withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case HOUR_2:
                expect = time.withHourOfDay((int) (time.getHourOfDay() / 2.0) * 2).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case HOUR_4:
                expect = time.withHourOfDay((int) (time.getHourOfDay() / 4.0) * 4).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case HOUR_6:
                expect = time.withHourOfDay((int) (time.getHourOfDay() / 6.0) * 6).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case HOUR_12:
                expect = time.withHourOfDay((int) (time.getHourOfDay() / 12.0) * 12).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case DAY:
                expect = time.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case WEEK:
                expect = time.withDayOfWeek(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            case MONTH:
                expect = time.withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
            default:
                expect = time.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
                break;
        }
        return expect.getMillis();
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return KEYWORDS[this.value];
    }

    public String toCron() {
        return CRONS[this.value];
    }

}
