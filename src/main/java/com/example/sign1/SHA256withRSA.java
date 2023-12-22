package com.example.sign1;

import java.security.*;

/**
 * This example demonstrates the process of generating a key pair,
 * signing a message with the private key, and then verifying the signature with the public key.
 * Adjust the algorithms, key sizes, and error handling based on your specific requirements.
 */
public class SHA256withRSA {

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        // Generate a key pair using the RSA algorithm
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can adjust the key size as needed

        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] signData(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Create a Signature object and initialize it with the private key
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        // Update the signature with the data
        signature.update(data);

        // Generate the digital signature
        return signature.sign();
    }

    public static boolean verifySignature(byte[] data, byte[] signature, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Create a Signature object and initialize it with the public key
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);

        // Update the verifier with the data
        verifier.update(data);

        // Verify the signature
        return verifier.verify(signature);
    }
}
