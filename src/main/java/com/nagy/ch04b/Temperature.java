package com.nagy.ch04b;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Temperature {

    private BigDecimal degreesCelsius;
    private BigDecimal degreesFahrenheit;

    private static final int scale = 1000000;
    private static final BigDecimal fiveOverNine = new BigDecimal(5.0).divide(new BigDecimal(9.0), scale , RoundingMode.HALF_UP);
    private static final BigDecimal nineOverFive = new BigDecimal(9.0).divide(new BigDecimal(5.0), scale, RoundingMode.HALF_UP);
    private static final BigDecimal thirtyTwo = new BigDecimal(32);

    public static final BigDecimal ABSOLUTE_ZERO_CELSIUS = new BigDecimal(-273.15).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
    public static final BigDecimal ABSOLUTE_ZERO_FAHRENHEIT = new BigDecimal(-459.67).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
    public static final BigDecimal MAX_TEMP_CELSIUS = new BigDecimal(new BigInteger("1420000000000000000000000000000000"));
    public static final BigDecimal MAX_TEMP_FAHRENHEIT = new BigDecimal(new BigInteger("2556000000000000000000000000000000"));
    public static final String MSG_DEGREES_TO_LOW = "The temperature is less than absolute zero.";
    public static final String MSG_DEGREES_TO_HIGH = "The temperature is higher than the max temperature possible.";

    public Temperature() {
        this.degreesCelsius = new BigDecimal(0);
        this.degreesFahrenheit = new BigDecimal(32);
    }

    public BigDecimal getDegreesCelsius() {
        return degreesCelsius.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public void setDegreesCelsius(BigDecimal degreesCelsius) {
        validateCelsius(degreesCelsius);
        this.degreesCelsius = degreesCelsius;
        celsiusToFahrenheit();
    }

    public BigDecimal getDegreesFahrenheit() {
        return degreesFahrenheit.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public void setDegreesFahrenheit(BigDecimal degreesFahrenheit) {
        validateFahrenheit(degreesFahrenheit);
        this.degreesFahrenheit = degreesFahrenheit;
        fahrenheitToCelsius();
    }

    public void celsiusToFahrenheit() {
        // (°C × 9/5) + 32 = 32°F
        degreesFahrenheit = (degreesCelsius.multiply(nineOverFive)).add(thirtyTwo);
    }

    public void fahrenheitToCelsius() {
        // (°F - 32) * 5/9 = 32°F
        degreesCelsius = (degreesFahrenheit.subtract(thirtyTwo)).multiply(fiveOverNine);
    }

    private void validateCelsius(BigDecimal tempCelsius){
        if (tempCelsius.compareTo(ABSOLUTE_ZERO_CELSIUS) < 0 ){
            throw new IllegalArgumentException(MSG_DEGREES_TO_LOW);
        } else if (tempCelsius.compareTo(MAX_TEMP_CELSIUS) > 0){
            throw new IllegalArgumentException(MSG_DEGREES_TO_HIGH);
        }
    }

    private void validateFahrenheit(BigDecimal tempFahrenheit){
        if (tempFahrenheit.compareTo(ABSOLUTE_ZERO_FAHRENHEIT) < 0 ){
            throw new IllegalArgumentException(MSG_DEGREES_TO_LOW);
        } else if (tempFahrenheit.compareTo(MAX_TEMP_CELSIUS) > 0){
            throw new IllegalArgumentException(MSG_DEGREES_TO_HIGH);
        }
    }

}
