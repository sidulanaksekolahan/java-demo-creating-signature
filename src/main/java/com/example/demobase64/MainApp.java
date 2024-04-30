package com.example.demobase64;

import java.util.Base64;

public class MainApp {

    public static void main(String[] args) {

        String originalString = "Hello, World!";

        String encodedString = Base64Util.encode(originalString.getBytes());
        System.out.println("encodedString: " + encodedString);

        String decodedString = Base64Util.decode(encodedString);
        System.out.println("decodedString: " + decodedString);

    }
}
