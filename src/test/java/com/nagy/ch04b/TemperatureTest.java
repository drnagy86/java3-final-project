package com.nagy.ch04b;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    private Temperature temperature = null;


    @BeforeEach
    void setUp() {
        temperature = new Temperature();
    }

    @Test
    void getDegreesCelsiusReturnsZeroForConstructor() {
        final BigDecimal expected = new BigDecimal(0);
        BigDecimal actual = temperature.getDegreesCelsius();
        assertEquals(expected, actual);

    }

    @Test
    void setDegreesCelsiusToZeroSetsFahrenheitTo32() {
        // arrange
        final BigDecimal degreesCelsius = new BigDecimal(0);
        final BigDecimal expectedFahrenheit = new BigDecimal(32);

        // act
        temperature.setDegreesCelsius(degreesCelsius);
        BigDecimal actualFahrenheit = temperature.getDegreesFahrenheit();

        // assert
        assertEquals(expectedFahrenheit, actualFahrenheit);
    }

    @Test
    void setDegreesCelsiusToNegative10PointFiveSetsFahrenheitToThirteenPoint1() {
        // arrange
        final BigDecimal degreesCelsius = new BigDecimal(-10.5);
        final BigDecimal expectedFahrenheit = new BigDecimal(13.1).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();

        // act
        temperature.setDegreesCelsius(degreesCelsius);
        BigDecimal actualFahrenheit = temperature.getDegreesFahrenheit();

        // assert
        assertEquals(expectedFahrenheit, actualFahrenheit);
    }

    @Test
    void setDegreesCelsiusToNegativeFortySetsFahrenheitToNegativeForty() {
        // arrange
        final BigDecimal degreesCelsius = new BigDecimal(-40);
        final BigDecimal expectedFahrenheit = new BigDecimal(-40).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();

        // act
        temperature.setDegreesCelsius(degreesCelsius);
        BigDecimal actualFahrenheit = temperature.getDegreesFahrenheit();

        // assert
        assertEquals(expectedFahrenheit, actualFahrenheit);
    }

    @Test
    void setDegreesCelsiusToAbsoluteZero() {
        // arrange
        final BigDecimal degreesCelsius = Temperature.ABSOLUTE_ZERO_CELSIUS;
//        final BigDecimal expectedFahrenheit = new BigDecimal(-459.67).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
        final BigDecimal expectedFahrenheit = Temperature.ABSOLUTE_ZERO_FAHRENHEIT;

        // act
        temperature.setDegreesCelsius(degreesCelsius);
        BigDecimal actualFahrenheit = temperature.getDegreesFahrenheit();

        // assert
        assertEquals(expectedFahrenheit, actualFahrenheit);
    }

    @Test
    void getDegreesFahrenheit() {
        final BigDecimal expected = new BigDecimal(32);
        BigDecimal actual = temperature.getDegreesFahrenheit();
        assertEquals(expected, actual);
    }

    @Test
    void setDegreesFahrenheitTo32SetsCelsiusToZero() {
        // arrange
        final BigDecimal degreesFahrenheit = new BigDecimal(32);
        final BigDecimal expectedCelsius = new BigDecimal(0);
        BigDecimal actualCelsius = null;

        // act
        temperature.setDegreesFahrenheit(degreesFahrenheit);
        actualCelsius = temperature.getDegreesCelsius();

        // assert
        assertEquals(expectedCelsius, actualCelsius);
    }

    @Test
    void setDegreesFahrenheitTo60SetsCelsiusTo15Point556() {
        // arrange
        final BigDecimal degreesFahrenheit = new BigDecimal(60);
        final BigDecimal expectedCelsius = new BigDecimal(15.556).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
        BigDecimal actualCelsius = null;

        // act
        temperature.setDegreesFahrenheit(degreesFahrenheit);
        actualCelsius = temperature.getDegreesCelsius();

        // assert
        assertEquals(expectedCelsius, actualCelsius);
    }

    @Test
    void setDegreesFahrenheitTo0SetsCelsiusToNegative17Point778() {
        // arrange
        final BigDecimal degreesFahrenheit = new BigDecimal(0);
        final BigDecimal expectedCelsius = new BigDecimal(-17.778).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
        BigDecimal actualCelsius = null;

        // act
        temperature.setDegreesFahrenheit(degreesFahrenheit);
        actualCelsius = temperature.getDegreesCelsius();

        // assert
        assertEquals(expectedCelsius, actualCelsius);
    }

    @Test
    void setDegreesFahrenheitToAbsoluteZero() {
        final BigDecimal degreesFahrenheit = Temperature.ABSOLUTE_ZERO_FAHRENHEIT;
        final BigDecimal expectedCelsius = Temperature.ABSOLUTE_ZERO_CELSIUS;
        BigDecimal actualCelsius = null;

        // act
        temperature.setDegreesFahrenheit(degreesFahrenheit);
        actualCelsius = temperature.getDegreesCelsius();

        // assert
        assertEquals(expectedCelsius, actualCelsius);
    }

    @Test
    void setDegreesCelsiusBelowAbsoluteZero() {
        // arrange
        final BigDecimal degreesCelsius = Temperature.ABSOLUTE_ZERO_CELSIUS.subtract(new BigDecimal(0.0001));
        //final BigDecimal expectedFahrenheit = Temperature.ABSOLUTE_ZERO_FAHRENHEIT;
        final String expectedMessage = Temperature.MSG_DEGREES_TO_LOW;
        String actualMessage = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> temperature.setDegreesCelsius(degreesCelsius));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void setDegreesCelsiusAboveHighestTemp() {
        // arrange
        final BigDecimal degreesCelsius = Temperature.MAX_TEMP_CELSIUS.add(new BigDecimal(0.0001));
        final String expectedMessage = Temperature.MSG_DEGREES_TO_HIGH;
        String actualMessage = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> temperature.setDegreesCelsius(degreesCelsius));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setDegreesFahrenheitBelowAbsoluteZero() {
        // arrange
        final BigDecimal degreesFahrenheit = Temperature.ABSOLUTE_ZERO_FAHRENHEIT.subtract(new BigDecimal(0.0001));
        final String expectedMessage = Temperature.MSG_DEGREES_TO_LOW;
        String actualMessage = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> temperature.setDegreesFahrenheit(degreesFahrenheit));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setDegreesFahrenheitAboveHighestTemp() {
        // arrange
        final BigDecimal degreesFahrenheit = Temperature.MAX_TEMP_FAHRENHEIT.add(new BigDecimal(0.0001));
        final String expectedMessage = Temperature.MSG_DEGREES_TO_HIGH;
        String actualMessage = null;

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> temperature.setDegreesFahrenheit(degreesFahrenheit));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }


}