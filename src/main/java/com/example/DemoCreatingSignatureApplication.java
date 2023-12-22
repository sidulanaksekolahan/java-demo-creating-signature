package com.example;

import com.example.sign1.SHA256withRSA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@SpringBootApplication
public class DemoCreatingSignatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCreatingSignatureApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		// Generate a key pair (public and private keys)
		try {
			KeyPair keyPair = SHA256withRSA.generateKeyPair();

			// Get private and public keys from the key pair
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();

			// Create a sample data to sign
			String dataToSign = "Hello, Digital Signature!";
			byte[] dataBytes = dataToSign.getBytes(StandardCharsets.UTF_8);

			// Sign the data with the private key
			byte[] digitalSignature = SHA256withRSA.signData(dataBytes, privateKey);

			// Verify the signature with the public key
			boolean isSignatureValid = SHA256withRSA.verifySignature(dataBytes, digitalSignature, publicKey);

			// Display the result
			System.out.println("Original data: " + dataToSign);
			System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));
			System.out.println("Is Signature Valid? " + isSignatureValid);

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        return args -> {

		};
	}

}
