package data;

public class Key {
	public byte []key_arr;
	public int length;
	
	public Key(int l)
	{
		key_arr = new byte[l];
	}
	public Key()
	{
	}
	
	public String toString()
	{
		StringBuffer hexString = new StringBuffer();
	    for(int i =0; i<length ; i++)
	    {
	       	String hex = Integer.toHexString(0xFF & key_arr[i]);
	       	if(hex.length() == 1) hexString.append('0');
	    	hexString.append(hex);
	    }
	      
	    return hexString.toString();
		
	}
}
