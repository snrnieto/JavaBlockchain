
package blockchain;

import java.util.Date;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

public class Block {
	
	public String hash;
	public String previousHash; 
	private String data; 
	private long timeStamp;
	private int nonce;
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); 
	}
	
        
        
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash =( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return getSHA(calculatedhash);
	}
	
	public void mineBlock(int difficulty) {
            int i=0;
		String target = new String(new char[difficulty]).replace('\0', 'a'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
                        System.out.println("Prueba: "+i+"  "+hash);
                        i++;
                        
		}
		System.out.println("\n\\nMined!!"+
                        
                                   "\nPrevious Hash: "+previousHash +
                                   "\nDate: "+Long.toString(timeStamp) +
                                   "\nNonce: "+Integer.toString(nonce) + 
                                   "\nData: "+data +"\n\n");
	}
        
         public static String getSHA(String input) 
    { 
  
        try { 
  
            // Static getInstance method is called with hashing SHA 
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
            // digest() method called 
            // to calculate message digest of an input 
            // and return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown"
                               + " for incorrect algorithm: " + e); 
  
            return null; 
        } 
    } 
}