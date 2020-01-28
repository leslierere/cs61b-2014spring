class Mother{
	int x;
	public static final int ACONSTANT = 0;

	// public void toString(){
	// 	System.out.println(x);
	// }

	public Mother(){
		x = 1;
	}

	public void aWord(){
		System.out.println("I am your mother");
	}

	public void toOverride(){
		System.out.println("Mother's method");
	}
}

interface Otherone{
	public void aWord();
	public static final int ACONSTANT = 100;
}

public class Son extends Mother implements Otherone{
	int y;

	public Son(){
		super();
		y = 2;
	}

	public void toOverride(){
		System.out.println("Son's method");
	}

	public static void main(String[] args) {
		/*********part 1***********/
		Mother[] xa = new Mother[2];
		Son[] ya = new Son[2];
		ya[0] = new Son();
		ya[1] = new Son();
		xa = ya;//1b,fine
		ya = (Son[])xa;//1b,fine

		xa = new Mother[2];
		// ya = (Son[])xa;//1c, run-time error
		xa[0] = new Son();
		xa[1] = new Son();
		// ya = (Son[])xa;//1c, run-time error

		/*********part 2***********/
		Son yourSon = new Son();//2a, it will compile
		// yourSon.aWord();
		// with different return type

		/*********part 3***********/
		System.out.println("the constant is:"+ACONSTANT);//3c

		/*********part 4***********/
		Son s  = new Son();
		((Mother)s).toOverride();//4a,call Son's methos, DML
		// ((Son)m).toOverride();//4b, run-time error
		//4c感觉只能再建一个新方法
		
	}
}