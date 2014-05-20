package encrypt;

import data.Key;
import data.Data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class SessionKey1 {
	Key skey1 = new Key(16);
	byte fCRC;
	int flength=0;
	
	public void generatePool()
	{
		String mkey = Data.getKey();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(mkey.getBytes());
        Data.pool.key_arr = md.digest();
        Data.pool.length = 64;
        //printKey(Data.pool);
        System.out.println("Pool : "+Data.pool);
	}
	
	public void generateCheckSum(String filepath) throws IOException
	{
		BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(filepath));
		CRC32 crc = new CRC32();
		int bt;
		while((bt = bfi.read()) != -1)
		{
			crc.update(bt);
			flength++;
		}
		bfi.close();
		fCRC = (byte) crc.getValue();
		//System.out.println(""+ fCRC + " " +flength  );
	}
	
	public void generate(String filepath) throws IOException
	{
		int i;
		generatePool();
		generateCheckSum(filepath);
		String mkey = ""+fCRC + flength;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(mkey.getBytes());
        Key index =  new Key();
        index.key_arr = md.digest();
        //printKey(index);
        index.length = 16;
        
        
        for(i=0; i<index.length; i++)
        {
        	int ind = (index.key_arr[i] & 0x3F);
        	//System.out.println(ind+"--> "+Integer.toHexString(0xFF &  Data.pool.key_arr[ind]));
        	skey1.key_arr[i] = Data.pool.key_arr[ind];
        }
        skey1.length = 16;
        //printKey(skey1);
        System.out.println("Session Key 1 : "+skey1);
	}
	
}
