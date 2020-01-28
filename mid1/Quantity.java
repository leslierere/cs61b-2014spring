public class Quantity {
  public String thing;// The thing being measured. 
  public double amount;// Its numerical quantity.



// Constructor.
  public Quantity(String thingString, double amount) {
	thing = thingString;
	this.amount = amount;
  }

// Constructor for thing with quantity 100. Calls the other constructor.
  public Quantity(String thingString) { 
  	this(thingString, 100.0);
  }

  public static void main(String[] args) {
  	Quantity q = new Quantity("I love Java this much:  ");
  	System.out.println(q.thing + q.amount);
  }
}