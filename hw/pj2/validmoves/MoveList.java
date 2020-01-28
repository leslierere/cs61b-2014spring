package pj2.validmoves;
import pj2.connect.State;
import pj2.player.Move;

public class MoveList{
	public static SList generateList(State s, int color){
		
		SList emptyList = new SList();
		SList addList = new SList();
		SList stepList = new SList();
		SList occupiedList = new SList();
		

		for (int i = 0; i<8 ; i++ ) {
			for (int j = 0;j<8 ;j++ ) {
				Move m = new Move(i, j);
				if (s.getContent(i, j)==2) {
					
					// emptyList.insertBack(m);
					State copyS = s.copyState();
					if (copyS.addContent(m, color)!=null) {
						addList.insertBack(m);
					}
				}else if (s.getContent(i, j)==color) {
					occupiedList.insertBack(m);
				}
			}
		}

		if (s.colorNumber(color)<=10){
			return addList;

		}else{
			for (Object o :occupiedList) {
				State copyS = s.copyState();
				Move dummyMove = (Move)o;
				copyS.remove(dummyMove.x1, dummyMove.x2);

				for (Object o1: emptyList) {
					Move dummyAdd = (Move)o1;
					if (copyS.addContent(dummyMove, color)!=null) {
						Move stepMove = new Move(dummyAdd.x1, dummyAdd.y1, dummyMove.x1, dummyMove.y1);
						stepList.insertBack(stepMove);
					}
				}
			}
			return stepList;
		}


		
	}

	public static void main(String[] args) {
		State beginState = new State();
		
		beginState.addContent(new Move(4,0), 1);
		beginState.addContent(new Move(5,0), 0);
		beginState.addContent(new Move(6,0), 1);
		beginState.addContent(new Move(1,2), 0);
		beginState.addContent(new Move(3,2), 1);
		beginState.addContent(new Move(7,2), 0);
		beginState.addContent(new Move(5,2), 1);
		beginState.addContent(new Move(2,3), 0);
		beginState.addContent(new Move(2,0), 1);
		beginState.addContent(new Move(5,3), 0);
		beginState.addContent(new Move(6,3), 1);
		beginState.addContent(new Move(0,4), 0);
		beginState.addContent(new Move(2,4), 1);
		beginState.addContent(new Move(5,5), 0);
		beginState.addContent(new Move(6,6), 1);
		beginState.addContent(new Move(4,5), 0);
		beginState.addContent(new Move(7,5), 1);
		beginState.addContent(new Move(2,5), 0);
		beginState.addContent(new Move(1,7), 1);
		beginState.addContent(new Move(3,7), 0);
		System.out.println(beginState.toString());
		SList moveL = generateList(beginState, 0);
		System.out.println(moveL.toString());
		

	}
	
}