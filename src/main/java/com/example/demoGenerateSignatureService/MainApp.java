package com.example.demoGenerateSignatureService;

import com.example.demohmac512.HmacUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MainApp {

    public static void main(String[] args) throws JsonProcessingException {

        String httpmethod = "POST";
        String endpoinurl = "/snapplink/nobu/v1.0/qr/qr-mpm-notify";
        String accesstoken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJzZXNzaW9uSWRcIjpcIjkyZjIxNzc0LTI5NmQtNGU5ZC1hYmU4LWRjNmQ4MDlmNWFjNVwiLFwidXNlcklkXCI6XCJudWxsXCIsXCJ1c2VyRW1haWxcIjpcIjAwMTY0OTE0NzUzMDM4N1wiLFwiZW5jcnlwdGVkU2VjcmV0S2V5XCI6XCJYQWVMeGFTTFcwcmN2aGp3Z3hWQkpVUHord0svZWNBdktodTJkcVVpMy84XFx1MDAzZFwifSIsImV4cCI6MTcxNzEyODc4MiwiaWF0IjoxNzE3MTI3ODgyfQ.WUySjRnFv9sUbYQ7p93lU_Dry_vU3dZpHZXO9Qcn0g6vq_NHb-D5CCE86qy1piTWpqszrx3yYic78IRb7Y1fog";
        String body = "{\n" +
                "    \"originalReferenceNo\": \"\",\n" +
                "    \"originalPartnerReferenceNo\": \"ABYA20240136\",\n" +
                "    \"latestTransactionStatus\": \"00\",\n" +
                "    \"transactionStatusDesc\": \"Success\",\n" +
                "    \"amount\": {\n" +
                "        \"value\": \"175000.00\",\n" +
                "        \"currency\": \"IDR\"\n" +
                "    },\n" +
                "    \"externalStoreId\": \"ID2020081400095\",\n" +
                "    \"additionalInfo\": {\n" +
                "        \"callbackUrl\": \"https://dev-snap.plink.co.id/snapplink/nobu/v1.0/qr/qr-mpm-notify\",\n" +
                "        \"issuerId\": \"93600987\",\n" +
                "        \"merchantId\": \"936005030000048824\",\n" +
                "        \"paymentDate\": \"2024-05-31 10:57:33\",\n" +
                "        \"retrievalReferenceNo\": \"292422549393\",\n" +
                "        \"paymentReferenceNo\": \"1010224053100002984600000493989\"\n" +
                "    }\n" +
                "}";
        String timeStamp = "2024-05-31T10:57:51+07:00";
        String xclientsecret = "398c7f5d55b688d3f624985c";
        String stringToSign = httpmethod + ":" + endpoinurl + ":" + accesstoken + ":" + sha256(minifyBodyRequest(body)) +
                ":" + timeStamp;
        System.out.println("stringToSign = " + stringToSign);

        String generatedSignature = Base64.getEncoder().encodeToString(HmacUtil.hmacSha512(xclientsecret, stringToSign));
        System.out.println("generatedSignature = " + generatedSignature);
    }

    public static String minifyBodyRequest(String bodyReq) throws JsonProcessingException {
        if (bodyReq == null || bodyReq.isEmpty()) {
            return "";
        }
        String prettyJsonString = bodyReq;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(prettyJsonString, JsonNode.class);
        return jsonNode.toString();
    }

    /**
     * To convert a string to its SHA-256 hash in Java, you can use the MessageDigest class from the java.security package
     */
    public static String sha256(String originalString) {
        StringBuilder hexString = null;
        try {
            // Get an instance of the SHA-256 MessageDigest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the input string
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array into a hexadecimal string
            hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Print the SHA-256 hash
            System.out.println(hexString.toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }
}
