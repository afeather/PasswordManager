import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Password {
	
	byte[] plaintext;
	SecretKey secret;
	byte[] encrypted;
	
	public Password(byte[] plaintext) throws Exception {
		this.plaintext = plaintext;
		encrypt();
	}
	
	public Password(byte[] encrypted, byte[] secret)  throws Exception {
		this.encrypted = encrypted;
		this.secret = new SecretKeySpec(secret, 0, secret.length, "AES");
		
		decrypt();
	}
	
	public void encrypt() throws Exception {
		
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(128);
		
		secret = keygen.generateKey();
		
		Cipher AES = Cipher.getInstance("AES");
		
		AES.init(Cipher.ENCRYPT_MODE, secret);
		encrypted = AES.doFinal(plaintext);
		
	}
	
	public void decrypt() throws Exception {
		
		Cipher AES = Cipher.getInstance("AES");
		
		AES.init(Cipher.DECRYPT_MODE, secret);
		plaintext = AES.doFinal(encrypted);
		
	}
	
}