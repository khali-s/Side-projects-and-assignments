public class Image{
/*Question 2: Image Class (40 points)
Define an Image class which is meant to represent the abstract notion of an Image.
An Image consists of several private properties:
1. A String metadata which can store information about the image in a text format. For example, one
might include in metadata the author’s name, and the time at which the image was created.
2. An int maxRange which stores the upper bound of the values of the pixels. You can assume (that is,
no check is required) maxRange is greater than or equal to all of the values in the pixel array.
3. A Pixel[][] data which stores the values of the various pixels. The first “row” of the array (i.e. the
first array inside the 2D array) stores the first row of the image pixel values. This means that the
length of this array is the height of the image. The length of each ‘sub-array’ is the width of the
image. */
  
private String metadata;
private int maxRange;
private Pixel[][] picture;

/*. A constructor that takes values for all of the attributes as input in the order in which they are listed
above. Be sure to create and store a copy of the input Pixel[][] array (and copy each Pixel in the
array as well). Your constructor should throw an IllegalArgumentException if the maxRange value
is negative. */

public Image(String data, int range, Pixel[][] pic){
  if(range<0){
    throw new IllegalArgumentException("range can't be negative");
  }
  this.metadata = data;
  this.maxRange = range;
  //intialise pixel array assuming rectangular picture
  this.picture = new Pixel[pic.length][pic[0].length];
  for(int i=0; i<pic.length; i++){
    for(int j=0;j<pic[0].length;j++){
      //use get methods from pixel class
      this.picture[i][j] = new Pixel(pic[i][j].getRed(),pic[i][j].getGreen(),pic[i][j].getBlue());
    }
  }

} 

/*2. getMetadata(): a get method for the metadata attribute*/
  public String getMetadata(){
    return this.metadata; 
  }


/*3. getMaxRange(): a get method for the maxRange attribute*/
  public int getMaxRange(){
    return this.maxRange; 
  }
/*4. getWidth(): a get method for the image width*/
  public int getWidth(){
    return this.picture[0].length; 
  }
/*5. getHeight(): a get method for the image height*/
  public int getHeight(){
    int height = this.picture.length;
    return height; 
  }
/*6. getPixel(int i, int j): a get method for the Pixel object at index i,j in the data array. Note that
the above get methods should be non-static.*/
  public Pixel getPixel(int i, int j){
  //uses get methods from Pixel Class
    Pixel thePixel = new Pixel(this.picture[i][j].getRed(),this.picture[i][j].getGreen(),this.picture[i][j].getBlue());
      return thePixel; 
  }
/*7. A non-static method flip, which will be used to flip an image either vertically or horizontally. The
method should take as input a boolean horizontal and return void. If the boolean is true, it will
flip the image horizontally. If the boolean value is false, the flip should be done vertically. Here, we
consider a vertical flip to be one in which we swap values across the x-axis. A horizontal flip is one
in which we swap across the y-axis.
Hint: This method is easier if you create a new 2D Pixel array, populate it with the flipped values,
and then update the 2D Pixel array of the calling image
 * 
 */
  public void flip(boolean horizontal){
  //horizontal flip
  //make temporary pixel array to perform a switch
    Pixel[][] temp = new Pixel[this.picture.length][this.picture[0].length];
    if (horizontal==true){
      int x = this.picture.length - 1;
      for(int i=0; i<this.picture[0].length; i++){
        for(int j=0; j<this.picture.length; j++){
          temp[j][i]= this.picture[x - j][i];
      
        }
    
      }
    }
  //vertical flip
    else{
      int x = this.picture[0].length -1;
      for(int j=0; j<this.picture.length; j++){
        for(int i=0; i<this.picture[0].length; i++){
          temp[j][i]= this.picture[j][x-i];
        }
      }
  
    }
    this.picture = temp;

  }
  /*A non-static method toGrey, which converts the Image to a greyscale image. A grey scale image is one
in which the red, green, and blue components in each pixel are the same. Write a non-static method
toGrey to turn a color Image into a grey scale one. The method should take nothing as input and
updates the pixels of the calling image so that it becomes grey scale. This must be done by calling the
grey method in the Pixel class.*/
  public void toGrey(){
    //loop values for each of the pixels and use the grey method
    for(int j=0; j<this.picture.length; j++){
        for(int i=0; i<this.picture[0].length; i++){
          Pixel thePixel = new Pixel(this.picture[j][i].grey());
          //new pixel converts values and then they get copied. 
          this.picture[j][i]= thePixel;
        }
    }

  }
 
  /*A non-static method crop which crops a rectangular section of the original Image. The method
should take as input 4 int values: startX (inclusive), startY (inclusive), endX (exclusive), and endY
(exclusive). The method should then “cut” the Image so that only the parts of the Image within the
coordinates specified by the input are left. If the input arguments are invalid, then your method should
throw an IllegalArgumentException.
Note we count the pixels in an image starting from 0. Note also which parameters are inclusive and
which are exclusive. For example, to take the first 10 rows and 15 columns, you could call the method
with input crop(0,0,10,15)
We have provided some sample code in the file ImageGenerator.java to help test this class.
You may ignore this if you like. However, we strongly recommend adapting the sample
code to test your methods before moving on to the next section. */
  public void crop(int startX, int startY, int endX, int endY){
  // make new pixel 2d array that will excise the undesired parts of a picture, also coordinates cant overextend the pic
    if(startX > endX || startY > endY ||startX >this.picture[0].length||endX > this.picture[0].length||startY > this.picture.length||endY > this.picture.length){
      throw new IllegalArgumentException();
    }
    Pixel[][] cropped = new Pixel[endY-startX][endX-startY];
    //end must be > than start, and they can't be equal
     for(int j=startY; j<endY; j++){
        for(int i=startX; i<endX; i++){
          cropped[j][i] = this.picture[j][i];
        }
     }
     this.picture = cropped;
  
  
  }
  








}