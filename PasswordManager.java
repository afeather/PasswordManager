import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class PasswordManager {
	
	public static void main(String[] args) {
		
		try { new PasswordManager(); } catch (Exception e) { e.printStackTrace(); }
		
	}

	String filename = "/home/alex/workspace/PasswordManager2/passwords";
	
	ArrayList<Password> passwords = new ArrayList<Password>();
	
	public PasswordManager() {
	
		String line;
		String[] splitline;
		
		byte[] encrypted;
		byte[] secret;
		Password temp;
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			while ((line = reader.readLine()) != null) {
				
				splitline = line.split(" ");
				secret = stringToBytes(splitline[0]);
				encrypted = stringToBytes(splitline[1]);
				
				temp = new Password(encrypted, secret);
				
				passwords.add(temp);
				
			}
			
			reader.close();
			
		} catch (IOException e) {
			System.err.println("COULD NOT READ FROM FILE");
			System.err.println(e.getMessage());
		}
		
		System.out.println(passwords.size() + " passwords found");
		for (int i=0; i<passwords.size(); i++)
			System.out.println(passwords.get(i).plaintext);
		
	}
	
	public void addPassword(String pass) {
		
		Password p = new Password(pass);
		
		String secret = bytesToString(p.secret.getEncoded());
		String encrypted = bytesToString(p.encrypted);
		
		String output = secret + " " + encrypted + "\n";
		
		try {
			Files.write(Paths.get(filename), output.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("COULD NOT WRITE TO FILE");
			System.err.println(e.getMessage());
		}
		
	}
	
	public static final String bytesToString(byte[] b) {
		
		if (b == null) return "";

		String s = "";
		String temp;
		
		for (int i=0; i<b.length; i++) {
			temp = "00" + Integer.toHexString(b[i] & 0xFF);
			s = s + temp.substring(temp.length() - 2);
		}
		
		return s;
		
	}
	
	public static final byte[] stringToBytes(String s) {
		
		byte[] b = new byte[s.length()/2];
		
		for (int i=0; i<s.length(); i+=2) {
			b[i/2] = (byte) (Integer.parseInt(s.substring(i, i+2), 16) & 0xFF);
		}
		
		return b;
		
	}
	
}
