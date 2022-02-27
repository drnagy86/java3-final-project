package com.nagy.ch04b;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AverageModel {
    private String num1;
    private String num2;
    private String num3;
    private BigDecimal average;
    private final String DEFAULT_NUM = "0";

    public AverageModel() {
        this.num1 = DEFAULT_NUM;
        this.num2 = DEFAULT_NUM;
        this.num3 = DEFAULT_NUM;
    }

    public AverageModel(String num1, String num2, String num3) {
        validateNum(num1);
        validateNum(num2);
        validateNum(num3);
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public BigDecimal getAverage() {
        calcAverage();
        return average;
    }

    public void calcAverage() {
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        BigDecimal bd3 = new BigDecimal(num3);
        BigDecimal sum = bd1.add(bd2).add(bd3);
        BigDecimal three = new BigDecimal("3");

        try {
            this.average = sum.divide(three);
        } catch (ArithmeticException ex){
            this.average = sum.divide(three, 15, RoundingMode.HALF_UP);
        }


    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        validateNum(num1);
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        validateNum(num2);
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        validateNum(num3);
        this.num3 = num3;
    }

    private void validateNum(String num) {
        try {
            Double.parseDouble(num);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number");
        }
    }
}
