package com.nagy.ch06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user = null;

    @BeforeEach
    void setUp() {user = new User();}

    @Test
    void setUserNameSucceeds() {
        // arrange
        final String expectedUserName = "new@company.com";
        String actualUserName = "";
        // act
        user.setUsername(expectedUserName);
        actualUserName = user.getUsername();

        // assert
        assertEquals(expectedUserName, actualUserName);
    }

    @Test
    void setUserNameThrowsExceptionIfEmpty() {
        // arrange
        final String expectedUserName = "";
        final String expectedMessage = User.ERROR_MSG_TOO_SHORT;
        String actualMessage = "";


        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setUsername(expectedUserName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setUserNameThrowsExceptionOver50Characters() {
        // arrange
        final String expectedUserName = "G1iwGX8FZpncag3AhmeBc5jNGFTQL1ETEiImK0LiUE4HvkxCHWA";
        final String expectedMessage = User.ERROR_MSG_TOO_LONG;
        String actualMessage = "";


        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setUsername(expectedUserName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setUserNameThrowsExceptionIfNotEmail() {
        // arrange
        final String expectedUserName = "p@f.e";
        final String expectedMessage = User.ERROR_MSG_FOR_EMAIL;
        String actualMessage = "";


        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setUsername(expectedUserName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setFirstNameSucceeds() {
        // arrange
        final String expectedName = "name";
        String actualName = "";
        // act
        user.setFirstName(expectedName);
        actualName = user.getFirstName();

        // assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void setFirstNameThrowsExceptionIfEmpty() {
        // arrange
        final String expectedName = "";
        final String expectedMessage = User.ERROR_MSG_TOO_SHORT;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setFirstName(expectedName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setFirstNameThrowsExceptionIfOver50Characters() {
        // arrange
        final String expectedName = "G1iwGX8FZpncag3AhmeBc5jNGFTQL1ETEiImK0LiUE4HvkxCHWA";
        final String expectedMessage = User.ERROR_MSG_TOO_LONG;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setFirstName(expectedName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setLastNameSucceeds() {
        // arrange
        final String expectedName = "name";
        String actualName = "";
        // act
        user.setLastName(expectedName);
        actualName = user.getLastName();

        // assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void setLastNameThrowsExceptionIfEmpty() {
        // arrange
        final String expectedName = "";
        final String expectedMessage = User.ERROR_MSG_TOO_SHORT;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setLastName(expectedName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setLastNameThrowsExceptionIfOver50Characters() {
        // arrange
        final String expectedName = "G1iwGX8FZpncag3AhmeBc5jNGFTQL1ETEiImK0LiUE4HvkxCHWA";
        final String expectedMessage = User.ERROR_MSG_TOO_LONG;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setLastName(expectedName) );
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPhoneNumberSucceeds() {
        // arrange
        final String testInput = "123-456-7890";
        final String expectedNumber = "1234567890";
        String actualNumber = "";
        // act
        user.setPhoneNumber(testInput);
        actualNumber = user.getPhoneNumber();

        // assert
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    void setPhoneNumberSucceedsWithDifferentFormatting() {
        // arrange
        final String testInput = "(123) 456-7890";
        final String expectedNumber = "1234567890";
        String actualNumber = "";

        // act
        user.setPhoneNumber(testInput);
        actualNumber = user.getPhoneNumber();

        // assert
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    void setPhoneNumberThrowsExceptionIfEmpty() {
        // arrange
        final String expectedMessage = User.ERROR_MSG_FOR_PHONE;
        final String testInput = "";
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(testInput));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPhoneNumberThrowsExceptionIfNotValidNumber() {
        // arrange
        final String expectedMessage = User.ERROR_MSG_FOR_PHONE;
        final String testInput = "123-345-6789-0";
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(testInput));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPasswordSucceeds() {
        // arrange
        final String expectedPassword = "P@ssw0rd";
        String actualPassword = "";
        // act
        user.setPassword(expectedPassword);
        actualPassword = user.getPassword();

        // assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setPasswordFailsIfNoDigit() {
        // arrange
        final String testPassword = "P@ssword";
        final String expectedMessage = User.ERROR_MSG_FOR_PASSWORD;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(testPassword));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPasswordFailsIfNoLowerCase() {
        // arrange
        final String testPassword = "P@SSW0RD";
        final String expectedMessage = User.ERROR_MSG_FOR_PASSWORD;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(testPassword));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPasswordFailsIfNoUpperCase() {
        // arrange
        final String testPassword = "p@ssw0rd";
        final String expectedMessage = User.ERROR_MSG_FOR_PASSWORD;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(testPassword));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPasswordFailsIfNoSpecialCharacters() {
        // arrange
        final String testPassword = "Passw0rd";
        final String expectedMessage = User.ERROR_MSG_FOR_PASSWORD;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(testPassword));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPasswordFailsTooShort() {
        // arrange
        final String testPassword = "P@ssw0r";
        final String expectedMessage = User.ERROR_MSG_FOR_PASSWORD;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(testPassword));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setPasswordFailsTooLong() {
        // arrange
        final String testPassword = "P@ssw0rd10P@ssw0rd101";
        final String expectedMessage = User.ERROR_MSG_FOR_PASSWORD;
        String actualMessage = "";

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(testPassword));
        actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }
}