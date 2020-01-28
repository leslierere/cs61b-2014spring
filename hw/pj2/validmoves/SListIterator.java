package pj2.validmoves;
import java.util.*;

public class SListIterator implements Iterator {
	SListNode n;

	public SListIterator(SList l){
		n = l.head;
	}

	public boolean hasNext(){
		return n!=null;
	}

	public Object next(){
		if(hasNext()){
			Object i = n.item;
			n = n.next;
			return i;
		}else{
			return null;
		}
	}
}