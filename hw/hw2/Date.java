/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int day;
  private int month;
  private int year;
  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    this.month = month;
    this.day = day;
    this.year = year;
    if (isValidDate(month,day, year)==false){
      System.out.println("This is not a valid date!");
      System.exit(0);
    }
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
    int in1 = s.indexOf("/");//return -1 if the character does not occur
    if(in1 == -1){
      System.out.println(s+" is not a valid date!");
      System.exit(0);
    }
    int in2 = s.indexOf("/", in1+1);
    if (in2 ==-1){
      System.out.println(s+" is not a valid date!");
      System.exit(0);
    }
    // System.out.println("in2:"+in2);
    int digitsOfD = s.length();
    month = Integer.parseInt(s.substring(0,in1));
    day = Integer.parseInt(s.substring(in1+1,in2));
    year = Integer.parseInt(s.substring(in2+1, digitsOfD));
    if (isValidDate(month,day, year)==false){
      System.out.println(s+" is not a valid date!");
      System.exit(0);
    }
    // System.out.println(month+"/"+day+"/"+year);
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    if (year%100 == 0){
      if (year%400 == 0){
        return true;
      }
    }else if(year%4==0){
      return true;
    }
    return false;                        // replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    // int m = month;
    // int y = year;
    int day;
    switch(month){
      case 2:if(isLeapYear(year)){
      day = 29;}else{day = 28;}
      break;
      case 4:day=30;
      break;
      case 6:day = 30;
      break;
      case 9:day =30;
      break;
      case 11:day = 30;
      break;
      default: day = 31;
      break;
    }    
    return day;                           // replace this line with your solution
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    // int day = day;
    // int month = month;
    // int year = year;
    if((year>0)&&(12>=month)&&(month>=1)&&(day<=daysInMonth(month,year))&&(day>=0)){
      return true;
    }
    return false;                        // replace this line with your solution
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are printed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    return (""+month+"/"+day+"/"+year);                     // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
    if (this.year<d.year){
      return true;
    }else if((this.year==d.year)&&(this.month<d.month)){
      return true;
    }else if((this.year==d.year)&&(this.month==d.month)){
      if(this.day<d.day){
        return true;
      }
    }
    return false;
                            // replace this line with your solution
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    if ((d.year==this.year)&&(this.month<d.month)&&(this.day<d.day)){
      return false;
    }
    return this.isBefore(d);                        // replace this line with your solution
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is only used for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    int days = 0;
    for(int m=1; m<month;m++){
      days = days+daysInMonth(m, year);
    }
    days = days+day;
    return days;                           // replace this line with your solution
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/1997 and d is 12/14/1997, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    if (this.year==d.year){
      return this.dayInYear()-d.dayInYear();
    }else if(this.year>d.year){
      int diff = 0;
      for(int i=1;i<(this.year-d.year);i++){
        diff += (new Date(12,31,d.year+i)).dayInYear();
        //daysin2018=2019.1.1-2018.1.1
      }
      diff += (new Date(12,31,d.year)).dayInYear()-d.dayInYear();//2017.1.1-2017.12.31
      diff +=this.dayInYear();
      return diff;
    }else{
      int diff = 0;
      for(int i=1;i<(d.year-this.year);i++){
        diff += (new Date(12,31,this.year+i)).dayInYear();
        //daysin2018=2019.1.1-2018.1.1
      }
      diff += (new Date(12,31,this.year)).dayInYear()-this.dayInYear();//2017.1.1-2017.12.31
      diff +=d.dayInYear();
      return -diff;
    }
                               // replace this line with your solution
  }

  public static void main(String[] argv) {
    // System.out.println((new Date(1, 1, 2019)).difference(new Date(1, 1, 2015)));
    // System.out.println((new Date(12, 31, 2018)).dayInYear());
    // System.out.println((new Date(12, 31, 2017)).dayInYear());
    // System.out.println((new Date(12, 31, 2016)).dayInYear());
    // System.out.println((new Date(12, 31, 2015)).dayInYear());
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
