package pj2.connect;

import pj2.player.MachinePlayer;
import static pj2.player.MachinePlayer.*;
// from pj2.player.MachinePlayer import BLACK;
// from pj2.player.MachinePlayer import WHITE;
import pj2.player.Move;
import static pj2.player.Move.*;


public class State{

	private int [][] board;
	private int blackNumber = 0;
	private int whiteNumber = 0;



	public State(){
		board = new int[8][8];
		// for empty chip, add number "2"
		for (int i = 0; i<8; i++) {
			for (int j = 0;j<8 ; j++) {
				board[i][j] = 2;
			}
		}
	}

	public int colorNumber(int color){
		if (color == BLACK) {
			return blackNumber;
		}else if (color == WHITE) {
			return whiteNumber;
		}else{
			System.out.println("Not valid color");
			return -1;
		}
	}

	

	public int getContent(int x, int y){
	//x: column
	//y: row
		return board[y][x];
	}

	public Move addContent(Move m, int c){
		//int x, int y, int color
		int x = m.x1;
		int y = m.y1;
		int x0 = m.x2;
		int y0 = m.y2;
		int kind = m.moveKind;
		int color = c;

		if(getContent(x, y)!=2){
			System.out.println("Chip already occupied");
		}else if (kind == STEP && x == x0 && y == y0) {
			System.out.println("Step cannot stay at("+x+","+y+")");
		}else if((x==0||x==7)&&(y==0||y==7)){
			System.out.println("Here at ("+ x + ","+y+"). No chip may in corners");
			
		}else if(isGroup(x, y, color)){
			System.out.println("Cause cluster when adding "+color+" to "+x+","+y);
		}else if(!(color==0||color==1)){
			System.out.println("Not valid color");			
		}else{
			board[y][x] = color;
			if (color==BLACK) {
				blackNumber++;
			}else{
				whiteNumber++;
			}
			return m;
		}
		return null;

		//test for connect class
	}

	

	private int searchAround(int x, int y){
		int color = getContent(x, y);
		int count = 0;
		for(int i =x-1; i<=x+1; i++){
			for(int j = y-1; j<=y+1; j++){
				if(i<0||i>7||j<0||j>7){
					continue;
				}else if(i==x&&j==y){
					continue;
				}
				if(this.getContent(i, j)==color){
					count++;
				}
			}
		}
		return count;
	} 



	private boolean isGroup(int x, int y, int color){
		/********
		obtain a new state
		for the new chip find out its surrounding elements have more than 2 
		
		*********/

		

		State newState = this.copyState();
		newState.board[y][x] = color;
		
		for(int i =x-1; i<=x+1; i++){
			for(int j = y-1; j<=y+1; j++){

				if(i<0||i>7||j<0||j>7){
					continue;
				}
				if(newState.getContent(i, j)==2){
					continue;
				}
				int sameAround = newState.searchAround(i, j);
				// System.out.println("("+i+","+j+"):"+sameAround);
				if(sameAround>=2){
					// System.out.println("The gird with clusters would be:\n"+newState.toString());
					return true;
				}
			}
		}
		return false;
	}

	public State copyState(){
		State newState = new State();
		for (int i = 0; i<8; i++) {
			for (int j = 0;j<8 ; j++) {
				newState.board[i][j] = this.board[i][j];
			}
		}
		return newState;
	}

	public void remove(int x, int y){
		board[x][y] = 2;
	}

	public String toString(){
		String s = new String();
		
		int y = 0;
		s+="_________________________________________________________________\n";
		while(y<8){
			int x = 0;
			while(x<8){
				int color = getContent(x,y);
				
				if(color==0){
					s+="|B\t";
				}else if(color==1){
					s+="|W\t";
				}else{
					s+="| \t";
				}
				
				x++;
			}
			s+="|\n";
			y++;
		}
		s+="_________________________________________________________________";
		return s;
	}

	public static void main(String[] args) {
		State s = new State();
		// s.addContent(3, 0, 1);
		// s.addContent(2, 1, 1);
		// s.addContent(5, 2, 1);
		// s.addContent(5, 3, 1);
		// s.addContent(2, 4, 1);
		// s.addContent(6, 5, 1);
		// s.addContent(1, 6, 1);
		// s.addContent(4, 7, 1);
		System.out.println(s.toString());
		// s.addContent(1, 0, 1);
		// System.out.println(s.searchAround(5, 2));
		
		// State newS = s.copyState();
		// System.out.println(newS.toString());
		// if(s.isGroup(3, 1, 1)){
		// 	System.out.println("cluster");
		// }
		// s.addContent(0, 0, 1);
		// s.addContent(1, 0, 1);
		// s.addContent(2, 0, 1);
		// s.addContent(3, 0, 1);
		// s.addContent(4, 0, 1);
		// s.addContent(5, 0, 1);
		// s.addContent(6, 0, 1);
		// s.addContent(7, 0, 1);
		// s.addContent(1, 2, 1);
		// s.addContent(2, 2, 1);
		// s.addContent(3, 2, 1);
		// s.addContent(4, 2, 1);
		// s.addContent(6, 2, 1);
		System.out.println(s.toString());
		// s.addContent(4, 7, 1);
		// System.out.println(s.searchAround(2, 0));
		// System.out.println(s.searchAround(2, 1));
		// System.out.println(s.searchAround(2, 2));
		// System.out.println(s.searchAround(3, 0));
		// System.out.println(s.searchAround(3, 1));
		// System.out.println(s.searchAround(3, 2));
		// System.out.println(s.searchAround(4, 0));
		// System.out.println(s.searchAround(4, 1));
		// System.out.println(s.searchAround(4, 2));
	}
	
}