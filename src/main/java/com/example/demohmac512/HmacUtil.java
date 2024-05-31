package com.example.demohmac512;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HmacUtil {

    private static final String HMAC_ALGORITHM = "HmacSHA512";

    public static byte[] generateHmac(String message, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(keySpec);

        return mac.doFinal(message.getBytes());
    }

    public static boolean verifyHmac(String message, String secretKey, byte[] receivedHmac) throws Exception {
        byte[] calculatedHmac = generateHmac(message, secretKey);
        return MessageDigest.isEqual(calculatedHmac, receivedHmac);
    }

    public static byte[] hmacSha512(String key, String value) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    HMAC_ALGORITHM);

            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(keySpec);
            return mac.doFinal(value.getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
