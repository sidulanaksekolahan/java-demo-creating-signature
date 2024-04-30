package com.example.demohmac512;

public class MainApp {

    public static void main(String[] args) {

        String message = "Hello, world!";
        String secretKey = "mySecretKey";

        try {
            // Generating HMAC
            byte[] hmac = HmacUtil.generateHmac(message, secretKey);
            System.out.println("Generated HMAC: " + hmac);

            // Verifying HMAC
            boolean isValid = HmacUtil.verifyHmac(message, secretKey, hmac);
            System.out.println("Is HMAC valid? " + isValid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
