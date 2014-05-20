package encrypt;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SessionKey1 sk1 = new SessionKey1();
		//sk1.generateCheckSum("input.txt");
		sk1.generate(args[0]);
	}

}
