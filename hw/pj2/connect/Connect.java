package pj2.connect;
// import connect.pj2.state.State;

public class Connect{
	private static SList searchAround(State state, int x, int y){
		SList chipList = new SList();
		int centerColor = state.getContent(x, y);
		int count = 0;
		for(int i =x-1; i<=x+1; i++){
			for(int j = y-1; j<=y+1; j++){
				if(i<0||i>7||j<0||j>7){
					continue;//out of grid
				}else if(i==x&&j==y){//it is itself
					continue;
				}
				
				if(state.getContent(i, j)==centerColor){
					int[] result =new int[2];//the item of the node
					result[0]=i;
					result[1]=j;
					chipList.insertBack(result);
					
				}
			}
		}
		return chipList;
		
	} 

	static boolean ifGroup(State state, int x, int y, int color){
		
		/********
		obtain a new state
		for the new chip find out its surrounding elements have more than 2 
		*********/
		State newState = state.copyState();
		newState.addContent(x, y, color);
		for(int i =x-1; i<=x+1; i++){
			for(int j = y-1; j<=y+1; j++){
				SList s = searchAround(newState, i, j);
				if(s.getSize>2){
					return false;
				}
			}
		}

	}

	public static void main(String[] args) {
		State s = new State();
		s.addContent(3, 0, 1);
		s.addContent(2, 1, 1);
		s.addContent(5, 2, 1);
		s.addContent(5, 3, 1);
		s.addContent(2, 4, 1);
		s.addContent(6, 5, 1);
		s.addContent(2, 6, 1);
		s.addContent(4, 7, 1);
		ifGroup(s, )
	}
}