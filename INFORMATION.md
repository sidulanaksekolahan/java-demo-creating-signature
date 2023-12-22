**Scenario Overview:**

Let's consider a scenario where Party A wants to send a message to Party B, and Party B needs to verify the integrity and authenticity of the received message.

1. Key Pair Generation:
   
- Party A:
  - Generates a key pair (private and public key).
  - Keeps the private key secret and shares the public key with Party B.


- Party B:
  - Generates a key pair (private and public key).
  - Keeps the private key secret and shares the public key with Party A.

2. Message Sending:
   
- Party A:
  - Prepares a message to send to Party B.
  - Signs the message using its private key to create a digital signature.
  - Sends the message and the digital signature to Party B.

3. Message Receiving and Verification:

- Party B:
  - Receives the message and the digital signature from Party A.
  - Uses Party A's public key (previously shared) to verify the digital signature.
  - If the signature is valid, Party B can trust the integrity and authenticity of the message.