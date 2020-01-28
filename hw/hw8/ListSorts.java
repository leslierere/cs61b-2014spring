/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    
    for (int i = 1; i<=q.size(); i++) {

      try{
        Object o = q.dequeue(); 
        LinkedQueue singleQueue = new LinkedQueue();
        singleQueue.enqueue(o);
        q.enqueue(singleQueue);
      }catch (QueueEmptyException e) {
        System.out.println("EMPTY QUEUE!");
      }
    }

    return q;
    






    // Object frontItem;
    // try{
    //   frontItem = q.front();
    // }catch (QueueEmptyException e) {
    //   System.out.println("EMPTY QUEUE!");
    //   return null;
    // }

    // if (frontItem instanceof LinkedQueue) {
    //   return q;   
    // }else{
    //   LinkedQueue singleQueue = new LinkedQueue();
    //   singleQueue.enqueue(frontItem);
      
    //   try{
    //     q.dequeue(); 
    //   }catch (QueueEmptyException e) {
    //     System.out.println("EMPTY QUEUE!");
    //   }
    //   q.enqueue(singleQueue);
    //   return makeQueueOfQueues(q);
    // }

  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue newQueue = new LinkedQueue();
    Comparable front1, front2;
    while(q1.size()!=0||q2.size()!=0){
      try{
        front1 = (Comparable)q1.front();
      }catch (QueueEmptyException e) {
        System.out.println("EMPTY QUEUE!");
        front1 = null;
      }

      try{
        front2 = (Comparable)q2.front();
      }catch (QueueEmptyException e) {
        System.out.println("EMPTY QUEUE!");
        front2 = null;
      }

      if (front2==null) {
        newQueue.enqueue(front1);
        try{
          q1.dequeue(); 
        }catch (QueueEmptyException e) {
          System.out.println("EMPTY QUEUE!");
        }
      }else if(front1==null){
        newQueue.enqueue(front2);
        try{
          q2.dequeue(); 
        }catch (QueueEmptyException e) {
          System.out.println("EMPTY QUEUE!");
        }
      }else if (front1.compareTo(front2)<=0) {
        newQueue.enqueue(front1);
        try{
          q1.dequeue(); 
        }catch (QueueEmptyException e) {
          System.out.println("EMPTY QUEUE!");
        }
      }else{
        newQueue.enqueue(front2);
        try{
          q2.dequeue(); 
        }catch (QueueEmptyException e) {
          System.out.println("EMPTY QUEUE!");
        }
      }
    }
    
    return newQueue;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    while(qIn.size()>0){
      try{
        Comparable item = (Comparable)qIn.dequeue();
        if (item.compareTo(pivot)<0) {
          qSmall.enqueue(item);
        }else if (item.compareTo(pivot)>0) {
          qLarge.enqueue(item);
        }else{
          qEquals.enqueue(item);
        }
      }catch (QueueEmptyException e) {
        System.out.println("QUEUE IS EMPTY NOW!");
      }
    }


    // try{
    //   Comparable item = (Comparable)qIn.dequeue();
    //   if (item.compareTo(pivot)<0) {
    //     qSmall.enqueue(item);
    //   }else if (item.compareTo(pivot)>0) {
    //     qLarge.enqueue(item);
    //   }else{
    //     qEquals.enqueue(item);
    //   }
    // }catch (QueueEmptyException e) {
    //   System.out.println("QUEUE IS EMPTY NOW!");
    // }
    // if (qIn.size()>0) {
    //   partition(qIn, pivot, qSmall, qEquals, qLarge);
    // }

  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    makeQueueOfQueues(q);
    
    while(q.size()>1){
      LinkedQueue q1 = null;
      LinkedQueue q2 = null;
      try{
        q1 = (LinkedQueue)q.dequeue();
        q2 = (LinkedQueue)q.dequeue();
      }catch (QueueEmptyException e) {
        System.out.println("EMPTY QUEUE!");
      }
      LinkedQueue sortedQ= mergeSortedQueues(q1, q2);
      q.enqueue(sortedQ);

    }
    

    try{
      LinkedQueue qFront = (LinkedQueue)q.front();
      q.append(qFront);
      q.dequeue();
    }catch (QueueEmptyException e) {
      System.out.println("EMPTY QUEUE!");
    }
    
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    int size = q.size();
    int pivotNo =(int)(Math.random() * size) + 1;
    // System.out.println(pivot);
    Comparable pivot = (Comparable)q.nth(pivotNo);
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    partition(q, pivot,qSmall, qEquals, qLarge);
    if (qSmall.size()>1) {
      quickSort(qSmall);
    }
    if (qLarge.size()>1) {
      // q.append(qEquals);
      quickSort(qLarge);
    }
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);    
    
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {
    LinkedQueue q = new LinkedQueue();
    q.enqueue("1A");
    q.enqueue("3A");
    q.enqueue("3B");
    q.enqueue("5A");
    q.enqueue("1B");
    q.enqueue("3C");
    q.enqueue("4A");
    // q.enqueue();
    // q.enqueue();
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());






    

    // LinkedQueue q = makeRandom(10);
    // System.out.println(q.toString());
    // mergeSort(q);
    // System.out.println(q.toString());

    // q = makeRandom(10);
    // System.out.println(q.toString());
    // quickSort(q);
    // System.out.println(q.toString());

    // Remove these comments for Part III.
    // Timer stopWatch = new Timer();
    // q = makeRandom(1000000);
    // stopWatch.start();
    // mergeSort(q);
    // stopWatch.stop();
    // System.out.println("Mergesort time, " + 1000000 + " Integers:  " +
    //                    stopWatch.elapsed() + " msec.");

    // stopWatch.reset();
    // q = makeRandom(1000000);
    // stopWatch.start();
    // quickSort(q);
    // stopWatch.stop();
    // System.out.println("Quicksort time, " + 1000000 + " Integers:  " +
    //                    stopWatch.elapsed() + " msec.");
    /*
    Mergesort time, 10 Integers:  1 msec.
Quicksort time, 10 Integers:  0 msec.
Mergesort time, 100 Integers:  9 msec.
Quicksort time, 100 Integers:  0 msec.
Mergesort time, 1000 Integers:  53 msec.
Quicksort time, 1000 Integers:  3 msec.

    */
  }

}
