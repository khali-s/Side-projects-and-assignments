/*Define a new type Pixel. A Pixel has 3 private integer attributes, each representing a red, a green and a
blue field. There should be two overloaded constructors:
1. One constructor should take as input 3 values: red, green, and blue (in that order). It should initialize
the 3 properties of the Pixel to be those 3 values respectively.
2. The second constructor should take as input just 1 value intensity. It should initialize all 3 properties
of the Pixel to be that same value of intensity.
Both of the above constructors should throw an IllegalArgumentException if any of the intensities are outside
of the expected range [0 ? 255] (inclusive).
*/

public class Pixel{
  //set rgb attributes as private
  private int red;
  private int green;
  private int blue;
  
  
 //make constructors
  
  public Pixel(int r, int g, int b){
    //throw excpetion such that values are contained between [0-255]
    if((r>255||g>255||b>255)||(r<0||g<0||b<0)){
      throw new IllegalArgumentException("all intensities must be between 0 and 255");
    }
    this.red = r;
    this.green = g;
    this.blue = b;
  
  }

  public Pixel( int intensity){
    if((intensity>255)||(intensity<0)){
      throw new IllegalArgumentException("all intensities must be between 0 and 255");
    }
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }
  /*Next, write 3 get methods, getRed(), getGreen() and getBlue() to return the corresponding data field values
of a Pixel.
Finally, write a method grey() which returns an int representing the average of the 3 properties. If the
average is not an integer, you should truncate the decimal point. This value will be used later to convert a
colour image to greyscale.*/
  public int getRed(){
    return this.red;
  }
  public int getGreen(){
    return this.green;
  }
  public int getBlue(){
    return this.blue;
  }
  public int grey(){
    //measure the average
    double average = (this.green + this.blue + this.red)/3.0;
    //return truncated form by switching double to int 
    int avg = (int) average;
   return avg;
  }


}