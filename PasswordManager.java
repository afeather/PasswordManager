import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class PasswordManager {
	
	public static void main(String[] args) {
		
		try { new PasswordManager(); } catch (Exception e) { e.printStackTrace(); }
		
	}

	String filename = "/home/alex/workspace/PasswordManager2/passwords";
	
	ArrayList<Password> passwords = new ArrayList<Password>();
	
	public PasswordManager() throws Exception {
	
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line;
		String[] splitline;
		
		byte[] encrypted;
		byte[] secret;
		Password temp;
		
		while ((line = reader.readLine()) != null) {
			
			splitline = line.split(" ");
			secret = hexStringToBytes(splitline[0]);
			encrypted = hexStringToBytes(splitline[1]);
			
			temp = new Password(encrypted, secret);
			
			System.out.println(temp.plaintext);
			passwords.add(temp);
			
		}
		
		reader.close();
		
	}
	
	public void addPassword(String pass) throws Exception {
		
		Password p = new Password(pass.getBytes());
		
		String secret = bytesToHexString(p.secret.getEncoded());
		String encrypted = bytesToHexString(p.encrypted);
		
		String output = secret + " " + encrypted + "\n";
		
		Files.write(Paths.get(filename), output.getBytes(), StandardOpenOption.APPEND);
		System.out.println(output);
		
	}
	
	public static final String bytesToHexString(byte[] b) {
		
		String s = "";
		String temp;
		for (int i = 0; i < b.length; i++) {
			temp = "00" + Integer.toHexString(b[i] & 0xFF).toUpperCase();
			s = s + temp.substring(temp.length() - 2);
		}
		return s;
	
	}
	
	public static final byte[] hexStringToBytes(String s) {
		
		byte[] b = new byte[s.length()/2];
		String temp;
		for (int i = 0; i < s.length(); i+=2) {
			temp = s.substring(i, i+2);
			b[i/2] = (byte) (Integer.parseInt(temp, 16) & 0xFF);
		}
		return b;
		
	}
	
}
