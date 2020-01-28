package pj2.validmoves;
import java.util.*;

public class SList implements Iterable{
	SListNode head;
	private SListNode tail;//usual listnode doesn't point to tail
	private int size;

	public SList(){
		size = 0;
		head = null;
		tail = null;
		// head.next = tail;
		// tail.next = headk;
	}

	public int getSize(){
		return size;
	}

	public void insertBack(Object o){
		SListNode n = new SListNode(o);
		
		if(tail==null){
			head = n;
		}else{
			tail.next = n;
		}
		tail = n;
		size++;
	}

	// public delete(SListNode n){
	// 	SListNode point = head;

	// }

	public Iterator iterator(){
		return new SListIterator(this);	
	}

	public String toString(){
		SListNode n = head;
		String s = new String();
		while (n!=null){
			s += n.item+"\n";
			n = n.next;
		}
		return s;
	}



	public static void main(String[] args) {
		SList s = new SList();
		s.insertBack(1);
		s.insertBack(2);
		s.insertBack(3);
		System.out.println(s.toString());
		for (Object i: s) {
			// SListNode n = (SListNode)i;
			System.out.println(i);
			//it seems that it will print item directly
		}
	}
}