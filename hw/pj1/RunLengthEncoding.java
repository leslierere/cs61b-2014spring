/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */
class RunLengthCell{
  int content;//shark||fish||empty
  int runLength;//number of the same kind
  int feeding;//only applicable to shark

  public RunLengthCell(int content, int runLength, int feeding){
    this.content = content;
    this.runLength = runLength;
    this.feeding = feeding;
  }


  public RunLengthCell(int content, int runLength){
    this.content = content;
    this.runLength = runLength;
    feeding = 0;
  }

  public String toString(){
    return "content"+content+"length"+","+feeding;
  }
}     

class RunLengthNode{
  RunLengthNode prev;//反正外面访问不到设public也没关系？
  RunLengthNode next;
  RunLengthCell item;

  public RunLengthNode(){
  }

  public RunLengthNode(RunLengthNode prev, RunLengthNode next, RunLengthCell item){
    this.prev = prev;
    this.next = next;
    this.item = item;
  }
  public RunLengthNode(RunLengthCell item){
    this.item = item;
  }

  public String toString(){
    if(item.content==0){
      return "."+item.runLength;
    }else if(item.content==1){
      return "S"+item.runLength+","+item.feeding;
    }else{
      return "F"+item.runLength;
    }
    
  }  
}



public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private int starveTime;
  private int size=0;
  private int width;
  private int height;
  private RunLengthNode head = new RunLengthNode();
  private int lengths = 0;//the sum of runlength
  // head.next = head;
  // head.prev = head;

  private RunLengthNode currentNode = head;//useful in nextRun() 


  /**
   *  The following methods are required for Part II.
   */
  public RunLengthEncoding(){//construct a linked list only with head
    head.prev = head;
    head.next = head;
  }
  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */


  public RunLengthEncoding(int i, int j, int starveTime) {
    // Your solution here.
    width = i;
    height = j;
    this.starveTime = starveTime;
    size = 1;
    head.prev = new RunLengthNode(head, head, new RunLengthCell(0, i*j,0));
    head.next = head.prev;
    lengths = i*j;
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime,
                           int[] runTypes, int[] runLengths) {
    // Your solution here.
    head.prev = head;
    head.next = head;
    this.starveTime = starveTime;
    width = i;
    height = j;
    for(int number = 0; number<runTypes.length; number++){
      if (runTypes[number] == 1){
        this.insertEnd(new RunLengthCell(1, runLengths[number], starveTime));
      }else{
        this.insertEnd(new RunLengthCell(runTypes[number], runLengths[number]));
      }
      lengths+=runLengths[number];
    }
  }

  public void insertEnd(RunLengthCell cell){
    RunLengthNode node = new RunLengthNode(cell);
    node.prev = head.prev;
    node.next = head;
    head.prev.next = node;
    head.prev = node;
    size++;
    lengths+=cell.runLength;
  }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as a
   *  TypeAndSize object), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    // Your solution here.
    currentNode = head;
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  a TypeAndSize object, which is nothing more than a way to return two
   *  integers at once.
   *  @return the next run in the enumeration, represented by a TypeAndSize
   *          object.
   */

  private TypeAndSize nextRun() {
    // Replace the following line with your solution.
    currentNode = currentNode.next;
    if (currentNode!=head){
      int nextType = currentNode.item.content;
      int nextSize = currentNode.item.runLength;
      return new TypeAndSize(nextType, nextSize);
    }else{
      return null;
    }
    
  }

  public String toString(){
    RunLengthNode startNode = head.next;
    String printStr="";
    do{
      printStr+=startNode.toString()+"\n";
      startNode = startNode.next;
    }while(startNode!=head);
    
    return printStr;
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */


  public Ocean toOcean() {
    // Replace the following line with your solution.
    int noCell = 0;//the number of cell in coverted ocean
    Ocean convertedOcean = new Ocean(width, height, starveTime);
    // System.out.println("width is" +width+ "length is" +height);
    restartRuns();
    while(nextRun()!=null){
      if(currentNode.item.content!=0){
        int startInt = noCell+1;//start order of the currentNode
        int endInt = noCell + currentNode.item.runLength;
        while(startInt<=endInt){
          int[] position = convertedOcean.orderToCo(startInt);
          // System.out.println("startInt is"+startInt);

          if(currentNode.item.content==1){  
            // System.out.println("add a shark at ("+position[0]+","+position[1]+")");
            convertedOcean.addShark(position[0], position[1], starveTime);

          }else{
            // System.out.println("add a fish at ("+position[0]+","+position[1]+")");
            convertedOcean.addFish(position[0], position[1]);
          }
          startInt++;
        }
      }
      noCell += currentNode.item.runLength;
    } 
    return convertedOcean;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    // constructor能引用另一个constructor吗
    head.prev = head;
    head.next = head;
    width = sea.width();
    height = sea.height();
    int place = 1;//indicate the order of the particular cell
    Cell[][] seaGrid = sea.getWrap();
    for (int j = 0; j<sea.height(); j++){
      for(int i = 0; i<sea.width(); i++){    
        if(place>1){
          int[] position = sea.orderToCo(place-1);//用来算上一个的coordinate
          // System.out.println("place is "+place);
          // System.out.println("i: "+i+" j: "+j);
          // System.out.println("position is ("+position[0]+","+position[1]+")");
          if(seaGrid[i][j].equals(seaGrid[position[0]][position[1]])){
            // System.out.println(seaGrid[i][j].toString());
            // System.out.println(seaGrid[position[0]][position[1]].toString());
            this.head.prev.item.runLength+=1;
          }else{
            if (seaGrid[i][j].content==1){
              this.insertEnd(new RunLengthCell(1,1,sea.sharkFeeding(i,j)));
            }else{
              this.insertEnd(new RunLengthCell(seaGrid[i][j].content,1));
            }    
            
          }
        }else{
          if (seaGrid[i][j].content==1){
              this.insertEnd(new RunLengthCell(1,1,sea.sharkFeeding(i,j)));
            }else{
              this.insertEnd(new RunLengthCell(seaGrid[i][j].content,1));
            } 
        }
        System.out.println("current run\n"+this.toString());
        place++;
      } 
    }
    check();
  }
    
  

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    int noOfCell = width*y + x +1;//8
    int noOfLength = 0;
    int i = 0;
    int j = 0;
    RunLengthNode midRun = head;
    RunLengthNode preRun = head;//the run of previous cell
    RunLengthNode latRun = head;//the run of next cell
    while (noOfLength<noOfCell){
      midRun = midRun.next;
      noOfLength += midRun.item.runLength;
      // System.out.println("Current noOfLength is"+noOfLength);
    }
    while (i<noOfCell-1){
      preRun = preRun.next;
      i += preRun.item.runLength;
      // System.out.println("Current noOfLength is"+noOfLength);
    }
    while (j<noOfCell+1){
      latRun = latRun.next;
      j += latRun.item.runLength;
      // System.out.println("Current noOfLength is"+noOfLength);
    }
    System.out.println("midRun.item.content:"+midRun.item.content);
    System.out.println("preRun.item.content:"+preRun.item.content);
    System.out.println("latRun.item.content:"+latRun.item.content);
    if(midRun.item.content==0){
      if (preRun.item.content==0){
        if(latRun.item.content==0){
          RunLengthNode midNode = new RunLengthNode(new RunLengthCell(2, 1));
          midRun.prev.next = new RunLengthNode(midRun.prev,midNode,new RunLengthCell(0, midRun.item.runLength-(noOfLength-noOfCell+1)));
          midRun.next.prev = new RunLengthNode(midNode, midRun.next, new RunLengthCell(0, noOfLength-noOfCell));
          midNode.prev = midRun.prev.next;
          midNode.next = midRun.next.prev;
        }else if(latRun.item.content==1){
          midRun.item.runLength--;
          midRun.next = new RunLengthNode(midRun, latRun, new RunLengthCell(2,1));
          latRun.prev = midRun.next;
        }else{//latRun.item.content ==2
          midRun.item.runLength--;
          latRun.item.runLength++;
        }     
      }else if(preRun.item.content==1){
        if(latRun.item.content==1){
          midRun.item.content = 2;
        }else if(latRun.item.content==2){
          latRun.item.runLength++;
          preRun.next = latRun;
          latRun.prev = preRun;
        }else{//latRun.item.content==0
          latRun.item.runLength--;
          preRun.next = new RunLengthNode(preRun, latRun, new RunLengthCell(2,1));
          latRun.prev = preRun.next;
        }       
      }else{
        if(latRun.item.content==2){
          preRun.item.runLength = preRun.item.runLength + 1 + latRun.item.runLength;
          preRun.next = latRun.next;
          latRun.next.prev = preRun;
        }else if(latRun.item.content==1){
          preRun.item.runLength++;
          preRun.next = latRun;
          latRun.prev = preRun;
        }else{//latRun.item.content==0
          preRun.item.runLength++;
          latRun.item.runLength--;
          preRun.next = latRun;
          latRun.prev = preRun;
        }
        
      }
    }

    check();
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    int noOfCell = width*y + x +1;//8
    int noOfLength = 0;
    int i = 0;
    int j = 0;
    RunLengthNode midRun = head;
    RunLengthNode preRun = head;//the run of previous cell
    RunLengthNode latRun = head;//the run of next cell
    while (noOfLength<noOfCell){
      midRun = midRun.next;
      noOfLength += midRun.item.runLength;
      // System.out.println("Current noOfLength is"+noOfLength);
    }
    while (i<noOfCell-1){
      preRun = preRun.next;
      i += preRun.item.runLength;
      // System.out.println("Current noOfLength is"+noOfLength);
    }
    while (j<noOfCell+1){
      latRun = latRun.next;
      j += latRun.item.runLength;
      // System.out.println("Current noOfLength is"+noOfLength);
    }
    System.out.println("midRun"+midRun.item.toString());
    System.out.println("preRun"+preRun.item.toString());
    System.out.println("latRun"+latRun.item.toString());
    if(midRun.item.content==0){
      if (preRun.item.content==0){
        if(latRun.item.content==0){
          RunLengthNode midNode = new RunLengthNode(new RunLengthCell(1, 1, starveTime));
          midRun.prev.next = new RunLengthNode(midRun.prev,midNode,new RunLengthCell(0, midRun.item.runLength-(noOfLength-noOfCell+1)));
          midRun.next.prev = new RunLengthNode(midNode, midRun.next, new RunLengthCell(0, noOfLength-noOfCell));
          midNode.prev = midRun.prev.next;
          midNode.next = midRun.next.prev;
        }else if(latRun.item.content==1)&&(latRun.item.feeding==starveTime){
          midRun.item.runLength--;
          latRun.item.runLength++;
        }else{//latRun is fish or shark with diff feeding
          preRun.item.runLength--;
          preRun.next = new RunLengthNode(preRun,latRun, new RunLengthCell(1,1,starveTime));
          latRun.prev = preRun.next;
        }     
      }else if((preRun.item.content==1)&&(preRun.item.feeding==starveTime)){
        if(latRun.item.content==0){
          preRun.item.runLength--;
          latRun.item.runLength++;
        }else if((latRun.item.content==1)&&(latRun.item.feeding==starveTime)){
          preRun.item.runLength = preRun.item.runLength+1+latRun.item.runLength;
          preRun.next = latRun.next;
          latRun.next.prev = preRun;
        }else{//latRun is fish or shark with diff feeding
          preRun.item.runLength++;
          preRun.next = latRun;
          latRun.prev = preRun;
        }
      }else{//latRun is fish or shark with diff feeding
        if(latRun.item.content==0){
          latRun--;
          preRun.next = new RunLengthNode(preRun,latRun, new RunLengthCell(1,1,starveTime));
          latRun.prev = preRun.next;
        }else if((latRun.item.content==1)&&(latRun.item.feeding==starveTime)){
          latRun.runLength++;
          preRun.next = latRun;
          latRun.prev = preRun;
        }else{//latRun is fish or shark with diff feeding
          midRun.item.content = 1;
          midRun.item.feeding = starveTime;
        }
        
      }
    }
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  public void check() {
    if (width*height!=lengths){
      System.out.println();
    }
    RunLengthNode aNode = head.next;
    while(aNode.next!=head){
      if((aNode.item.content==aNode.next.item.content)&&(aNode.item.feeding==aNode.next.item.feeding)){
        System.out.println("Illegal!");
        System.out.println("The current node is:" +aNode.toString());
        System.out.println("The next node is:" +aNode.next.toString());
      }
      aNode = aNode.next;
    }
  }

  public static void main(String[] argv){
    int[] runTypes = new int[]{1,0,2,1,2,0,2,1,2};
    int[] runLengths = new int[]{3,3,3,3,3,3,3,3,3};
    RunLengthEncoding oneRunLength= new RunLengthEncoding(3,9,3,runTypes, runLengths);
    System.out.println(oneRunLength.toString());
    // Ocean oneOcean = oneRunLength.toOcean();
    // RunLengthEncoding runFromOcean = new RunLengthEncoding(oneOcean);
    // System.out.println(runFromOcean.head.next.toString());
    // System.out.println(runFromOcean.toString());
    oneRunLength.addFish(0,1);
    System.out.println(oneRunLength.toString());
  }
}

