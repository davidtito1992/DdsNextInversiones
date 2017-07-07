package conversorRomano;

public class ConversorRomano {
	
	public String convertir(int i) 
	{
		int n = 5;
		int res = i % n;
		if(res == (n-1))
		{
			return "IV"; 
		}
		else if(res == 0)
		{
			return "V";
		}
		else
		{
			return new String(new char[res]).replace("\0", "I");
		}
	}
}
