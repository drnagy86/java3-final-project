package com.nagy.helpers;

public final class Helpers {

    public static final String ERROR_MESSAGE_STRING_TOO_SHORT = "This is too short";
    public static final String ERROR_MESSAGE_STRING_TOO_LONG = "This is too long";
    public static final String ERROR_MESSAGE_POSTAL_WRONG_SIZE = "The postal code is not 5 digits";

    public static final int MAX_SHORT_STRING_LENGTH = 50;
    public static final int MAX_LONG_STRING_LENGTH = 255;

    public static void validateStringLength(String string, int maxLength){
        if (string.length() == 0){
            throw new IllegalArgumentException(ERROR_MESSAGE_STRING_TOO_SHORT);
        }
        if (string.length() > maxLength){
            throw new IllegalArgumentException(ERROR_MESSAGE_STRING_TOO_LONG);
        }
    }

    public static void validatePostal(int postal){
        if (String.valueOf(postal).length() !=5 ){
            throw new IllegalArgumentException(ERROR_MESSAGE_POSTAL_WRONG_SIZE);
        }
    }

}
