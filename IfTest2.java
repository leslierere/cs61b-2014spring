class IfTest2{
	public static void main (String[] args) {
		int x =2;
		if (x == 3) {
			System.out.println("x must be 3");
		}else{//else cannot start in a new line
			System.out.println("x is not 3");
		}
		System.out.println("This runs no matter what.");
	}
}