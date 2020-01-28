/* Ocean.java */

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

/*Cell is a class representing one cell of the ocean

*/
class Cell{
  int content;//shark||fish||empty
  int feeding;//how long can a shark starve for
  //類的變量和實例變量問題

  public Cell(){//construct an empty cell
    content = 0;
    feeding = 0;
  }

  public Cell(int content){//construct a fish cell
    this.content = content;
    feeding = 0;
  }

  public Cell(int content, int feeding){//construct a shark cell
    this.content = content;
    this.feeding = feeding;
  }

  public boolean equals(Cell testCell){
    if((this.content == testCell.content)&&(this.feeding==testCell.feeding)){
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    if (content==1){
      return "S"+ feeding;
    }else if(content==0){
      return ".";
    }else{
      return "~";
    }
  }
}



public class Ocean {

  /**
   *  Do not rename these constants.  WARNING:  if you change the numbers, you
   *  will need to recompile Test4.java.  Failure to do so will give you a very
   *  hard-to-find bug.
   */

  public final static int EMPTY = 0;
  public final static int SHARK = 1;
  public final static int FISH = 2;

  /**
   *  Define any variables associated with an Ocean object here.  These
   *  variables MUST be private.
   */
  private int starveTime;
  private Cell[][] wrap;//the 2-dimensional list representing the ocean
  private Cell[][] backWrap;//the backup of wrap
  private int phase;


  /**
   *  The following methods are required for Part I.
   */

  /**
   *  Ocean() is a constructor that creates an empty ocean having width i and
   *  height j, in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public Ocean(int i, int j, int starveTime) {
    // Your solution here.

    wrap = new Cell[i][j];
    backWrap = new Cell[i][j];
    this.starveTime = starveTime;
    for(int xNo = 0; xNo<i; xNo++){
      for (int yNo = 0; yNo<j; yNo++){
        wrap[xNo][yNo] = new Cell();
        backWrap[xNo][yNo] = new Cell();
      }
    }
  }

  private int[] wrapper(int i, int j){//to get the shark and fish acound a cell
    int aroundShark = 0;
    int aroundFish = 0;
    for(int xNo = i-1;xNo<i+2; xNo++){
      for(int yNo = j-1; yNo<j+2; yNo++){
        if((xNo==i)&&(yNo==j)){
          continue;
        }
        try{
          if (backWrap[xNo][yNo].content==1){
            aroundShark++;
          }else if(backWrap[xNo][yNo].content==2){
            aroundFish++;
            // System.out.println("for ("+i+","+j+") a fish at ("+xNo+","+yNo+")");
          }
        }catch(ArrayIndexOutOfBoundsException e1){
          // System.out.println("out of bouds at("+xNo+","+yNo+")");
          continue;
        }  
      }
       
    }
    // System.out.println("the aound shark of ("+i+","+j+")is"+aroundShark);
    // System.out.println("the aound fish of ("+i+","+j+")is"+aroundFish);
    int[] aroundGroup = new int[2];//有没有直接加元素的方法？
    aroundGroup[0]=aroundShark;
    aroundGroup[1]=aroundFish;
    return aroundGroup;
  }

  public Cell[][] getWrap(){
    return wrap;
  }

  /**
   *  width() returns the width of an Ocean object.
   *  @return the width of the ocean.
   */

  public int width() {
    // Replace the following line with your solution.
    return wrap.length;
  }

  /**
   *  height() returns the height of an Ocean object.
   *  @return the height of the ocean.
   */

  public int height() {
    // Replace the following line with your solution.
    return wrap[0].length;
  }

  /**
   *  starveTime() returns the number of timesteps sharks survive without food.
   *  @return the number of timesteps sharks survive without food.
   */

  public int starveTime() {
    // Replace the following line with your solution.
    return starveTime;
  }

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    if (wrap[x][y].content == 0){
      wrap[x][y].content = 2;
      backWrap[x][y].content = 2;
    }
    
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here.
    if (wrap[x][y].content == 0){
      wrap[x][y].content = 1;
      wrap[x][y].feeding = starveTime;
      backWrap[x][y].content = 1;
      backWrap[x][y].feeding = starveTime;

    }
  }

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
    // Replace the following line with your solution.
    if (wrap[x][y].content==0){
      return EMPTY;
    }else if (wrap[x][y].content==1){
      return SHARK;
    }else{
      return FISH;
    }
  }

  public int[] orderToCo(int order){//order starts from 1
    int[] position = new int[2];
    position[0] = order % this.width()-1;//x coordinate
    position[1] = order / this.width();
    if (position[0]==-1){
      position[0] = this.width()-1;
      position[1]--;
    }
    return position;
  }

  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
    // Replace the following line with your solution.
    for (int i = 0;i<this.width();i++){
      for(int j =0; j<this.height(); j++){
        int[] around = wrapper(i,j);
        int aroundShark = around[0];
        int aroundFish = around[1];
        Cell tempCell = backWrap[i][j];//to point at the cell

        if ((tempCell.content==1)&&(tempCell.feeding==0)){
          wrap[i][j]=new Cell(0);
        }else if(tempCell.content==1){
          if (aroundFish>0){
            tempCell.feeding = starveTime;
          }else{
            tempCell.feeding--;
          }
        }

        if(tempCell.content==2){
          if(aroundShark==1){
            wrap[i][j]=new Cell(0);
          }else if(aroundShark>1){
            wrap[i][j]=new Cell(1, starveTime+1);
          }
        }

        if(tempCell.content==0){
          if((aroundFish>1)&&(aroundShark<2)){
            wrap[i][j]=new Cell(2);
          }else if((aroundFish>1)&&(aroundShark>1)){
            wrap[i][j]=new Cell(1, starveTime);
          }
        }
      }

    }
    backWrap = wrap;
    return this;
  }

  /**
   *  The following method is required for Part II.
   */

  /**
   *  addShark() (with three parameters) places a shark in cell (x, y) if the
   *  cell is empty.  The shark's hunger is represented by the third parameter.
   *  If the cell is already occupied, leave the cell as it is.  You will need
   *  this method to help convert run-length encodings to Oceans.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   *  @param feeding is an integer that indicates the shark's hunger.  You may
   *         encode it any way you want; for instance, "feeding" may be the
   *         last timestep the shark was fed, or the amount of time that has
   *         passed since the shark was last fed, or the amount of time left
   *         before the shark will starve.  It's up to you, but be consistent.
   */

  public void addShark(int x, int y, int feeding) {
    // Your solution here.
    wrap[x][y].content = 1;
    wrap[x][y].feeding = feeding;
    backWrap[x][y].content = 1;
    backWrap[x][y].feeding = feeding;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  sharkFeeding() returns an integer that indicates the hunger of the shark
   *  in cell (x, y), using the same "feeding" representation as the parameter
   *  to addShark() described above.  If cell (x, y) does not contain a shark,
   *  then its return value is undefined--that is, anything you want.
   *  Normally, this method should not be called if cell (x, y) does not
   *  contain a shark.  You will need this method to help convert Oceans to
   *  run-length encodings.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int sharkFeeding(int x, int y) {
    // Replace the following line with your solution.
    if (cellContents(x, y)==1){
      return wrap[x][y].feeding;
    }else{
      System.out.println("This is not a Shark.");
      return 0;
    }
  }
}
