package cipher;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;


public class Encryption {
	static Properties pr = new Properties();
	static String key;
	
	public static String encrypt(String data) throws Exception {
    	pr.load(new FileInputStream("config/key.properties"));
    	key = pr.getProperty("key");
    	while(key.length() < 16) {
	        key = key.concat(key);
	    }
	    key = key.substring(0, 16);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
	}
}