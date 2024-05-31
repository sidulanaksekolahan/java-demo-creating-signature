package com.example.sign1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MainClassDemo {

    public static void main(String[] args) throws Exception {

        String keyString = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCX6RSwgU6yTcep\n" +
                "rFaDkwsybXDvjQ5ZsbI4ARgSbHl1cQWukuxgw6U33V4PIokv+ps3WoefWLconyPX\n" +
                "mJX1TmjqxtMJ5h5Z3eCPbkXi05cwO8S4Nq4J/kBSqN9d01sBDKO3EFPwY/Q82MYQ\n" +
                "Q0wDnRXqBOibTb8Wf/Q4uU2XGA6nQ8JiDZn1Qcaw4ZKTUvNJeLo4YzHtjrJRkMkU\n" +
                "PeC9z3FmhwPizaOyG/HxgCuW861iKpJyY41BJVn+raiHxuCH6nvxN263E19yovNC\n" +
                "tY7c+k5svF6pl0nCBt37nlpPaLrqWNWSaWL6l8T4Rn8LbES+C9zIYoCTmTnKWJpJ\n" +
                "EUp2ulkDAgMBAAECggEAf4vyFSjc3JS12d6bXSnvMs31Dw1OST0LuD4i6gUuDc9x\n" +
                "x9wQoEZkyH0TZn3G1gQCov1d9grU7kSAIZs5/ZdyO/3oX91ZCndi/srF3udtf9AP\n" +
                "IeS3SCiz9TNZKDw3oq/sHd6Vu7cQ8K3JzwnSHw6bwbuRWiRzFv8kHSRo4z4Nv523\n" +
                "vuS99OPwcBfa/gaUfGB3QcuiBMuF0dcy7UWWGDeynmCWeEknJBIgqAOBQ6RqVdUd\n" +
                "o2EiDEKW09giysCIf9DssvmZ2UnhJJ/IKopFUUnemhxY3EdImkRaaPbsw4v1Da6h\n" +
                "QeSpayYYJ7GsqnyGddCUaw6GKK/QAb0wHm3704Is4QKBgQDHz/td7cmX2l/56qmb\n" +
                "mCXxc6+lCnAlniPBhsXuU+LdsxljIazxI1FhyirfcMqaoeb+NdBH6FuIX2TXHRxK\n" +
                "pIFKScx2YAzLoaQAcy7xqME8nuYmE+5wxEpwRJvQf4hPlj+OnYjNejL/MBYcR2Ae\n" +
                "fyJOsBMcdM6FCc8qrHVlG3HjuwKBgQDCoMBzm5nER8ocrwTCPtynURiB0mkFgbTI\n" +
                "odDFwI1Be/nYgQnsGgLv1UieV2SflfCCXCk/H/PL6pn4eT/8MRk1W5LrrBACpst4\n" +
                "o3dRXQ5CWMLASAVB+mQm3OITVUsTfm/Cif6hzZ/2hV0OUzHR11/J6D87hK2nUXYQ\n" +
                "dFZ48M83WQKBgG0XQ3Er80WiJ4ZmNUXLEuQ5uisMgOA47+KoN4aAbDuInhK+fxKE\n" +
                "qtkOLMwjY6p7PIeMT/QCi37wPjkoHpsuJ5ysoCH9HN9MmCLawEWWUjJLuvINLcCJ\n" +
                "mu6itsNl7cEuEfNGVTuCPTeQRHIaEgN0fAGkJjsCFwMRsPbmg425JzwjAoGAUJM0\n" +
                "magFlNkTt6RNmA1mVG6icryldf4o1hcH05bP5Bhra1KgxPpP1PK7ybHsmpT8qTt2\n" +
                "aAfWasmJTPEFqGa7RPfQb3kgd7iN7DfgofKggJ82oVwvsQz0gZJYliBtoHhr1GJE\n" +
                "5L+4RdsYl2Gh8nnz0icwZnebXPXApzdCxXcMkqECgYBRRoHgaD+BxNjSpnMqHHVg\n" +
                "otMcBRW6qjhHTjofttLY3Ckm44XCcVeFx2q9Lfjx9Zd4zlDo0GsFjT3BDU9QUzgs\n" +
                "c7dW5gIzgiln2lThLLlS21cYm+gWhLsRqPBWHXWMggOoc5NoaYNnHr0Lwgwxg1Bf\n" +
                "l5kRBXq3pyCjm8Pcl6GYkw==";

        PrivateKey privateKey = getPrivateKeyFromString(keyString);

        String dataToSign = "ddff6162722a419b84a02dbbf6961a1b|2024-05-14T13:10:26+07:00";
        byte[] dataBytes = dataToSign.getBytes(StandardCharsets.UTF_8);
        // Sign the data with the private key
        byte[] digitalSignature = SHA256withRSA.signData(dataBytes, privateKey);
        System.out.println("digitalSignature = " + Base64.getEncoder().encodeToString(digitalSignature));

        // Verify the signature with the public key
        boolean isSignatureValid = SHA256withRSA.verifySignature(dataBytes, digitalSignature, getPublicKeyFromString("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl+kUsIFOsk3HqaxWg5ML\n" +
                "Mm1w740OWbGyOAEYEmx5dXEFrpLsYMOlN91eDyKJL/qbN1qHn1i3KJ8j15iV9U5o\n" +
                "6sbTCeYeWd3gj25F4tOXMDvEuDauCf5AUqjfXdNbAQyjtxBT8GP0PNjGEENMA50V\n" +
                "6gTom02/Fn/0OLlNlxgOp0PCYg2Z9UHGsOGSk1LzSXi6OGMx7Y6yUZDJFD3gvc9x\n" +
                "ZocD4s2jshvx8YArlvOtYiqScmONQSVZ/q2oh8bgh+p78TdutxNfcqLzQrWO3PpO\n" +
                "bLxeqZdJwgbd+55aT2i66ljVkmli+pfE+EZ/C2xEvgvcyGKAk5k5yliaSRFKdrpZ\n" +
                "AwIDAQAB"));
        System.out.println("isSignatureValid = " + isSignatureValid);
    }

    public static PrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
        String privateKeyContent = key;
//        privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        privateKeyContent = getPrivateKeyContent(privateKeyContent);
//        log.info("privateKeyContent===>{}<====", privateKeyContent);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);
        return privKey;
    }

    public static PublicKey getPublicKeyFromString(String keyString) throws Exception {
        // Remove any headers and footers if present
        keyString = keyString.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", ""); // Remove any whitespace

        // Decode the Base64 encoded string
        byte[] keyBytes = Base64.getDecoder().decode(keyString);

        // Generate the public key
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // or "DSA", "EC" depending on the key type
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;
    }

    public static String getPrivateKeyContent(String snapBankPrivateKey) {
        return snapBankPrivateKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replace("\n", "")
                .replace("\r", "")
                .trim();
    }
}
