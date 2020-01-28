package pj2.validmoves;

public class SListNode{
	Object item;
	SListNode next;

	public SListNode(){
		item = null;
	}

	public SListNode(Object o){
		item = o;
	}
	// public String toString(){
	// 	String s = new String(item);
	// 	return s;
	// }
	public Object getItem(){
		return item;
	}
}