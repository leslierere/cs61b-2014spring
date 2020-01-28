package list;

public class LockDList extends DList{
	protected LockDListNode head;

	public LockDList() {
    //  Your solution here.
	    size = 0;
	    head = new LockDListNode(null, null, null);
	    head.next = head;
	    head.prev = head;
  	}

  	protected LockDListNode newNode(Object item, LockDListNode prev, LockDListNode next) { 		
    	return new LockDListNode(item, prev, next);
  	}//return type!!!!return type 不同好像还是可以override


	public void lockNode(LockDListNode node) {
		node.locked = true;
	}

	public void remove(LockDListNode node) {
    // Your solution here.
	    if(node!=null||node.locked==false){
	      node.prev.next = node.next;
	      node.next.prev = node.prev;
	      size--;
    	}
    }
    

  	// insertfront
  	// public void insertFront(Object item) {
   //  // Your solution here.
	  //   LockDListNode n = newNode(item, head, head.next);	
	  //   head.next.prev = n;
	  //   head.next = n;
	  //   size++;
  	// }

  	// // insertback
  	// public void insertBack(Object item) {
   //  // Your solution here.
	  //   LockDListNode n = newNode(item, head.prev, head);
	  //   System.out.println("head.prev is:"+head.prev);
	  //   head.prev.next = n;
	  //   head.prev = n;
	  //   size++;
  	// }

  	

  	// insertafter
 //  	public void insertAfter(Object item, LockDListNode node) {
 //    // Your solution here.
	//     if(node!=null){
	//       LockDListNode n = newNode(item, node, node.next);
	//       node.next.prev = n;
	//       node.next = n;
	//       size++;
	//     }	
	// }

	// public void insertBefore(Object item, LockDListNode node) {
 //    // Your solution here.
	//     if(node!=null){
	//       LockDListNode n = newNode(item, node.prev, node);
	//       node.prev.next = n;
	//       node.prev = n;
	//       size++;
	//     }
	// }

	// public String toString() {
 //    String result = "[  ";
 //    DListNode current = head.next;
 //    // System.out.println("next current"+current.next.item);
 //    while (current != head) {
 //      result = result + current.item + "  ";
 //      current = current.next;
 //    }
 //    return result + "]";
 //  }

  	

  	public static void main(String[] args) {
  		//ensure empty lovkdlist is correct
  		LockDList l = new LockDList();
  		//ensure empty lovkdlist is correct
  		// if(l.head.next == l.head&&l.head.prev == l.head){
  		// 	System.out.println("correct");
  		// }
  		l.insertBack(1);
  		System.out.println(l.toString());
  		
  		l.insertBack(2);
  		System.out.println(l.toString());
  		
  		l.insertFront(3);
  		l.insertFront(4);
  		System.out.println(l.toString());


  		// l.insertFront(1);
  		// System.out.println(l.toString());
  		// l.insertFront(2);
  		// l.insertFront(3);
  		
  		l.lockNode(l.head.next);
  		l.remove(l.head.next);
  		// System.out.println("current size "+l.length());
  		if (l.head.next instanceof LockDListNode){
  			System.out.println("it is LockDListNode");
  			if (l.head.next.getStatus()==false){
  				System.out.println("it is not locked");
  			}else{
  				System.out.println("it is locked");
  			}
  		}else{
  			System.out.println("it is not LockDListNode");
  		}
  		l.insertAfter(5, l.front());
  		l.insertBefore(6, l.back());
  		System.out.println(l.toString());
  		// System.out.println(l.back().item);
  		// System.out.println(l.front().item);
  		// System.out.println("the last item is"+l.head.prev.item);
  		
  	}
}