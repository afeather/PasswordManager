import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Password {
	
	String plaintext;
	SecretKey secret;
	byte[] encrypted;
	
	public Password(String plaintext) {
		this.plaintext = plaintext;
		encrypt();
	}
	
	public Password(byte[] encrypted, byte[] secret) {
		this.encrypted = encrypted;
		this.secret = new SecretKeySpec(secret, "AES");
		decrypt();
	}
	
	public void encrypt() {

		try {
			
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(128);
			secret = keygen.generateKey();
			
			Cipher AES = Cipher.getInstance("AES");
			
			AES.init(Cipher.ENCRYPT_MODE, secret);
			encrypted = AES.doFinal(plaintext.getBytes());
		
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			System.err.println("COULD NOT ENCRYPT PASSWORD");
			System.err.println(e.getMessage());
			encrypted = null;
			secret = null;
		}
		
	}
	
	public void decrypt() {
		
		try {
		
			Cipher AES = Cipher.getInstance("AES");
			
			AES.init(Cipher.DECRYPT_MODE, secret);
			byte[] plaintext_bytes = AES.doFinal(encrypted);
			
			plaintext = "";
			for (int i=0; i<plaintext_bytes.length; i++)
				plaintext = plaintext + (char)plaintext_bytes[i];
		
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			System.err.println("COULD NOT DECRYPT PASSWORD");
			System.err.println(e.getMessage());
			plaintext = "ERROR";
		}
		
	}
	
}