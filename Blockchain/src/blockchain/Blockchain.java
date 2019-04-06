package blockchain;

import java.security.MessageDigest;
import java.util.ArrayList;

public class Blockchain {

   public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 2;

	public static void main(String[] args) {	
		//añade bloque a la lista blockchain
		
		blockchain.add(new Block("Bloque 1", "0"));
		
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Bloque 2",blockchain.get(blockchain.size()-1).hash));
		
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Bloque 3",blockchain.get(blockchain.size()-1).hash));
		
		blockchain.get(2).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		
		System.out.println("\nBlockchain: ");
                mostrarBlockchain();
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', 'a');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compara el hash actual con el calculado
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compara el hash del bloque anterior con el hash del bloque anterior
                        //registrado en el bloque actual
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//comprueba si el hash cumple con la dificultad
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
        public static void mostrarBlockchain(){
            for (int i = 0; i < blockchain.size(); i++) {
                System.out.println(blockchain.get(i).hash);
            }
        }
}




	
