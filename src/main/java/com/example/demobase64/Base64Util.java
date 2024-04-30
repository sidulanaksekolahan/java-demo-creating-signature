package com.example.demobase64;

import java.util.Base64;

public class Base64Util {

    public static String encode(byte[] stringByte) {
        return Base64.getEncoder().encodeToString(stringByte);
    }

    public static String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}
