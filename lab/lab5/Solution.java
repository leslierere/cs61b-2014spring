class Mother{
	int x;

	// public void toString(){
	// 	System.out.println(x);
	// }

	public Mother(){
		x = 1;
	}
}

public class Son extends Son{
	int y;

	public Son(){
		super();
		y = 2;
	}

	public static void main(String[] args) {
		Mother[] xa = new Mother[3];
		Son[] ya = new Son[3];
		ya = xa;
	}
}