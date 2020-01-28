/* HashTableChained.java */

package hw6.dict;

import hw6.list.DList;
import hw6.list.DListNode;
import hw6.list.InvalidNodeException;
// import hw6.list.List.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  int buckets;
  final static int FACTORA = 3;
  final static int FACTORB = 4;
  int sizeEstimate;
  DList[] table;
  final static int DEFAULTSIZE = 97;
  int entryNo = 0;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    this.sizeEstimate = sizeEstimate;
    buckets = largerPrime(sizeEstimate);
    table = new DList[buckets];


  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(DEFAULTSIZE);

  }

  static int largerPrime(int n){
    boolean[] prime = new boolean[n+1];
    for (int i =1; i<=n; i++) {
      prime[i] = true;
    }
    for (int divisor = 2; divisor*divisor<=2*n; divisor++) {
      // if (prime[divisor]) {
        for (int i = 2*divisor; i<=2*n; i+=divisor) {
          if (i-n>0) {
            prime[i-n] = false;
          }
        }
      // }
    }
    int output = 1;
    for (int i = n+1; i<=2*n; i++) {
      if (prime[i-n]) {
        output = i;

      }
    }
    return output;


  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    // int largePrime;
    int bucketNo = ((FACTORA*code + FACTORB)% largerPrime(10*sizeEstimate)) % buckets;
    // int bucketNo = code / buckets;
    if (bucketNo<0) {
      bucketNo = bucketNo + buckets;
    }
    return bucketNo;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return entryNo;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return entryNo==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    int bucketNo = compFunction(key.hashCode());
    Entry entry = new GoodEntry(key, value);

    if (table[bucketNo]==null) {
      table[bucketNo] = new DList();
    }
    table[bucketNo].insertBack(entry);
    entryNo++;
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int bucketNo = compFunction(key.hashCode());
    if (table[bucketNo]==null) {
      return null;
    }
    DListNode firstNode = (DListNode)table[bucketNo].front();
    while(firstNode!=null){
      Entry foundEntry = (Entry)(firstNode.getItem());
      if (foundEntry.key().equals(key)) {
        return foundEntry;
      }
      firstNode = firstNode.getNext();
    }
    return null; 
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int bucketNo = compFunction(key.hashCode());
    if (table[bucketNo]==null) {
      return null;
    }
    DListNode firstNode = (DListNode)table[bucketNo].front();
    while(firstNode!=null){
      Entry foundEntry = (Entry)(firstNode.getItem());
      if (foundEntry.key().equals(key)) {
        try{
          firstNode.remove();  
        }catch(InvalidNodeException e){

        }
        
        entryNo--;
        return foundEntry;
      }
      firstNode = firstNode.getNext();
    }
    return null; 
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for (int i =0;i < table.length;i++) {
      table[i] = null;
    }

  }

  public void testCollisions(){
    System.out.println("Number of buckets:"+buckets);
    System.out.println("Number of entries:"+entryNo);
    int collisions = 0;
    String tableLook = "";
    for (int i = 0; i<buckets; i++) {
      if (table[i]!=null) {
        if (table[i].length()>1) {
          collisions++;
        }
        
        tableLook = tableLook+"["+table[i].length()+"]";
      }else{
        tableLook = tableLook+"["+0+"]";
      }
    }
    System.out.println("Number of collisions:"+collisions);
    
    double expected = Math.pow(1-(double)1/buckets, entryNo)*buckets-buckets+entryNo;
    System.out.println("Expected collosions:"+expected);
    System.out.println("Table look:\n"+tableLook);
  }

  public static void main(String[] args) {
    // System.out.println(largerPrime(10));
    // System.out.println(""+7%18);
    // System.out.println(""+DEFAULTSIZE);
    HashTableChained hashTable = new HashTableChained();
    hashTable.insert(125, "first");
  }
}
