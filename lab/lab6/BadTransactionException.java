public class BadTransactionException extends Exception{
	public int amount;
	public BadTransactionException(int badTransAmount){
		super("This is not a valid transaction amount: "+badTransAmount);
		amount = badTransAmount;	
	}


}