package com.tongji.bwm.pojo.AccessLog;

import lombok.Data;

@Data
public class CountByDay {
    private int Year;
    private int Month;
    private int Day;
    private int Count;

    public CountByDay() {
    }

    public CountByDay(int year, int month, int day, int count) {
        Year = year;
        Month = month;
        Day = day;
        Count = count;
    }
}
