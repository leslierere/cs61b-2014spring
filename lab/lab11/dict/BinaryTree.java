/* BinaryTree.java */

package dict;

/**
 *  BinaryTree implements a Dictionary as a binary tree (unbalanced).  Multiple
 *  entries with the same key are permitted.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 *
 *  @author Jonathan Shewchuk
 **/
public class BinaryTree implements Dictionary {

  /** 
   *  size is the number of items stored in the dictionary.
   *  root is the BinaryTreeNode that serves as root of the tree.
   *  If there are no items, size is zero and root is null.
   **/
  protected int size;
  protected BinaryTreeNode root;

  /**
   *  Construct an empty binary tree.
   **/
  public BinaryTree() {
    makeEmpty();
  }

  /**
   *  makeEmpty() removes all the entries from the dictionary.
   */
  public void makeEmpty() {
    size = 0;
    root = null;
  }

  /** 
   *  size() returns the number of entries stored in the dictionary.
   *
   *  @return the number of entries stored in the dictionary.
   **/
  public int size() {
    return size;
  }

  /** 
   *  isEmpty() tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  insert() constructs and inserts a new Entry object, consisting of
   *  a (key, value) pair, into the dictionary, and returns a reference to the
   *  new Entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  @param key the key by which the entry can be retrieved.  Must be of
   *  a class that implements java.lang.Comparable.
   *  @param value an arbitrary object associated with the key.
   *  @return an Entry object referencing the key and value.
   **/
  public Entry insert(Object key, Object value) {
    Entry entry = new Entry(key, value);
    if (root == null) {
      root = new BinaryTreeNode(entry);
    } else {
      insertHelper(entry, (Comparable) key, root);
    }

    size++;
    return entry;
  }

  /**
   *  insertHelper() recursively does the work of inserting a new Entry object
   *  into the dictionary.
   *
   *  @param entry the Entry object to insert into the tree.
   *  @param key the key by which the entry can be retrieved.
   *  @param node the root of a subtree in which the new entry will be
   *         inserted.
   **/
  private void insertHelper(Entry entry, Comparable key, BinaryTreeNode node) {
    if (key.compareTo(node.entry.key()) <= 0) {
      if (node.leftChild == null) {
	node.leftChild = new BinaryTreeNode(entry, node);
      } else {
	insertHelper(entry, key, node.leftChild);
      }
    } else {
      if (node.rightChild == null) {
	node.rightChild = new BinaryTreeNode(entry, node);
      } else {
	insertHelper(entry, key, node.rightChild);
      }
    }
  }

  /** 
   *  find() searches for an entry with the specified key.  If such an entry is
   *  found, it returns the Entry object; otherwise, it returns null.  If more
   *  than one entry has the key, one of them is chosen arbitrarily and
   *  returned.
   *
   *  @param key the search key.  Must be of a class that implements
   *         java.lang.Comparable.
   *  @return an Entry referencing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry find(Object key) {
    BinaryTreeNode node = findHelper((Comparable) key, root);
    if (node == null) {
      return null;
    } else {
      return node.entry;
    }
  }

  /**
   *  Search for a node with the specified key, starting from "node".  If
   *  a matching key is found (meaning that key1.compareTo(key2) == 0), return
   *  a node containing that key.  Otherwise, return null.
   *
   *  Be sure this method returns null if node == null.
   **/

  private BinaryTreeNode findHelper(Comparable key, BinaryTreeNode node) {
    // Replace the following line with your solution.
    if (key.compareTo(node.entry.key())==0) {
      return node;
      
    }else if(key.compareTo(node.entry.key())<0){
      if (node.leftChild!=null) {
        return findHelper(key, node.leftChild);//you must add return here!!
      }
    }else{
      if (node.rightChild!=null) {
        return findHelper(key, node.rightChild);
      }
    }
    return null;
  }

  /** 
   *  remove() searches for an entry with the specified key.  If such an entry
   *  is found, it removes the Entry object from the Dictionary and returns it;
   *  otherwise, it returns null.  If more than one entry has the key, one of
   *  them is chosen arbitrarily, removed, and returned.
   *
   *  @param key the search key.  Must be of a class that implements
   *         java.lang.Comparable.
   *  @return an Entry referencing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry remove(Object key) {
    // Replace the following line with your solution.
    Entry removedNode = removeHelper(key);
    if (removedNode!=null) {
      size--;
      return removedNode;
    }
    return null;
  }

  private Entry removeHelper(Object key){
    BinaryTreeNode n = findHelper((Comparable)key, root);
    if (n!=null) {
      Entry copyN = n.entry;
      
      if (n.leftChild==null&&n.rightChild==null) {
        if (n.parent==null) {
          makeEmpty();
        }else{
          Comparable parentKey = (Comparable)n.parent.entry.key();
          if(parentKey.compareTo(n.entry.key())>0){
            n.parent.leftChild=null;
          }else{
            n.parent.rightChild=null;
          }
        }
        
      }else if(n.leftChild==null&&n.rightChild!=null){
        if (n.parent==null) {
          n.rightChild.parent = null;
          root = n.rightChild;
        }else{
          Comparable parentKey = (Comparable)n.parent.entry.key();
          n.rightChild.parent = n.parent;
          if (parentKey.compareTo(n.entry.key())>0) {
            n.parent.leftChild=n.rightChild;
          }else{
            n.parent.rightChild=n.rightChild;
          }
        }
     
      }else if(n.leftChild!=null&&n.rightChild==null){
        if (n.parent==null) {
          n.leftChild.parent = null;
          root = n.leftChild;
        }else{
          Comparable parentKey = (Comparable)n.parent.entry.key();
          n.leftChild.parent = n.parent;
          if (parentKey.compareTo(n.entry.key())>0) {
            n.parent.leftChild=n.leftChild;
          }else{
            n.parent.rightChild=n.leftChild;
          }
        }
        
      }else{//have two children
        BinaryTreeNode rightSmallest = rightSmallest(n);
        
        removeHelper(rightSmallest.entry.key());
        n.entry = rightSmallest.entry;
      }
    // size--;
    return copyN;
    }
    return null;
  }

  private BinaryTreeNode rightSmallest(BinaryTreeNode node){
    if (node.rightChild!=null) {
      return smallest(node.rightChild);
    }
    return null;
  }

  private BinaryTreeNode smallest(BinaryTreeNode node){
    BinaryTreeNode leftC  = node.leftChild;
    if (leftC!=null) {
      return smallest(leftC);
    }
    return node;
  }

  /**
   *  Convert the tree into a string.
   **/

  public String toString() {
    if (root == null) {
      return "";
    } else {
      return root.toString();
    }
  }

  /* Tests the binary search tree. */
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();

    System.out.println("Inserting 1A, 6V, 3K, 2Z, 5L, 9L:");
    tree.insert(new Integer(1), "A");
    tree.insert(new Integer(6), "V");
    tree.insert(new Integer(3), "K");
    tree.insert(new Integer(2), "Z");
    tree.insert(new Integer(5), "L");
    tree.insert(new Integer(9), "L");
    System.out.println("The tree is:  " + tree);
    System.out.println("Size:  " + tree.size());

    System.out.println("\nTesting find() ...");
    tree.testFind(1, "A");
    tree.testFind(9, "L");
    tree.testFind(5, "L");
    tree.testFind(4, null);
    tree.testFind(6, "V");
    tree.testFind(3, "K");

    System.out.println("\nTesting smallest() ...");
    System.out.println(tree.smallest(tree.root.rightChild).entry.value());
    



    System.out.println("\nTesting remove() (for nodes with < 2 children) ...");
    tree.testRemove(5, "1A(((2Z)3K)6V(9L))");
    tree.testRemove(3, "1A((2Z)6V(9L))");
    tree.testRemove(1, "(2Z)6V(9L)");
    tree.insert(new Integer(7), "S");
    tree.insert(new Integer(8), "X");
    tree.insert(new Integer(10), "B");
    System.out.println("After inserting 7S, 8X, 10B:  " + tree);
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 6) {
      System.out.println("  SHOULD BE 6.");
    }

    System.out.println("\nTesting remove() (for nodes with 2 children) ...");
    tree.testRemove(6, "(2Z)7S((8X)9L(10B))");
    tree.testRemove(9, "(2Z)7S((8X)10B)");
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 4) {
      System.out.println("  SHOULD BE 4.");
    }
  }

  private void testRemove(int n, String shouldBe) {
    Integer key = new Integer(n);
    System.out.print("After remove(" + n + "):  ");
    remove(key);
    System.out.println(this);
    if (!toString().equals(shouldBe)) {
      System.out.println("  SHOULD BE " + shouldBe);
    }
  }

  private void testFind(int n, Object truth) {
    Integer key = new Integer(n);
    Entry entry = find(key);
    System.out.println("Calling find() on " + n);
    if (entry == null) {
      System.out.println("  returned null.");
      if (truth != null) {
        System.out.println("  SHOULD BE " + truth + ".");
      }
    } else {
      System.out.println("  returned " + entry.value() + ".");
      if (!entry.value().equals(truth)) {
        if (truth == null) {
          System.out.println("  SHOULD BE null.");
        } else {
          System.out.println("  SHOULD BE " + truth + ".");
        }
      }
    }
  }
  
}
