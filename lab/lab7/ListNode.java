/* ListNode.java */

/**
 *  ListNode is a very simple headless list class, akin to cons cells in
 *  Scheme.  Each ListNode contains an item and a reference to the next node.
 **/
class ListNode {

  public Object item;
  public ListNode next;

  /**
   *  Constructs a ListNode with item i and next node n.
   *  @param i the item to store in the ListNode.
   *  @param n the next ListNode following this ListNode.
   **/
  ListNode(Object i, ListNode n) {
    item = i;
    next = n;
  }

  public String toString(){
    ListNode pn = this;
    String output = "";
    while(pn!=null){
      output= output+"\n ------------";
      output= output+"\n |\t\t|";
      output= output+"\n |\t"+pn.item+"\t|";
      output= output+"\n |\t\t|";
      output= output+"\n ------------";
      pn = pn.next;
      if(pn!=null){
        output+="\n\t|\t";
        output+="\n\tV\t";
      }
    }
    return output;
  }

}
