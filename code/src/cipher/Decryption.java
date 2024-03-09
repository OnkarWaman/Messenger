package cipher;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class Decryption {
	static Properties pr = new Properties();
	static String key;
	
	public static String decrypt(String encryptedText) throws Exception {
		
    	pr.load(new FileInputStream("config/key.properties"));
    	key = pr.getProperty("key");
    	
    	while(key.length() < 16) {
	        key = key.concat(key);
	    }
	    key = key.substring(0, 16);

	    try {
			byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			return new String(decryptedBytes);
		} 
	    catch (Exception e) {
			return encryptedText;
		}
	}
}