package list;

public class LockDListNode extends DListNode{
	protected boolean locked;
	protected LockDListNode prev;//!!!!!!!
  	protected LockDListNode next;//!!!!!!!


	LockDListNode(Object i, LockDListNode p, LockDListNode n){
		super(i, p, n);
		locked = false;
	}

	LockDListNode(Object i){
		super(i);
		locked = false;
	}

	public boolean getStatus(){
		return locked;
	}
}