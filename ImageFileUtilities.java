import java.util.Scanner;
import java.io.*;
public class ImageFileUtilities {
  
  
  public static Image read(String filename) throws IOException{
    Scanner sc = new Scanner(new File(filename));
    //Scanner s = new Scanner(System.in);
    int[][] values;
    String type = sc.nextLine(); //to hold of P2 and P3 types
    String temporary = "";
    //first we take care of comments with hashtags. Knowing there are atleast one comment 
    String metadata = "";
    int maxRange = -1;
    
    while(sc.hasNext("#")){
      temporary = sc.nextLine();
      metadata = metadata + temporary + "\n";
   
    }
    //take width and hieght of picture
    int width = sc.nextInt();
    int height = sc.nextInt();
    maxRange = sc.nextInt();
    Pixel[][] picture = new Pixel[height][width];
    
    //read depending on whether image is P2 or P3  
    if(type.charAt(1)=='2'){
      //if it's P2 therefore grey method
      for(int i=0; i<height ; i++){
        for(int j=0; j<width ; j++){
          int intensity = sc.nextInt();
          picture[i][j] = new Pixel(intensity);
         /* picture[i][j].red = intensity;
          picture[i][j].blue = intensity;
          picture[i][j].green = intensity;*/
        
        }
      }
    }
    if(type.charAt(1)=='3'){
      //if it's P3 therefore each pixel has triplets
      for(int i=0; i<height ; i++){
        for(int j=0; j<width ; j++){
          picture[i][j] = new Pixel(sc.nextInt(),sc.nextInt(),sc.nextInt());
         
        
        }
      }
    }
    Image image = new Image(metadata, maxRange, picture);
   
    sc.close();
    return image;
  }
  /*2. Write a static method writePnm which takes as input an Image and a String filename and returns
void. This method should produce a pnm file (P3 format) based on the contents of the Image provided.
Page 5
3. Write a static method writePgm which takes as input an Image and a String filename and returns
void. This method should produce a pgm file (P2 format) based on the contents of the Image provided.
Be sure to convert the pixels to grey scale before you write this new file. */
  //this methods makes a p3 file
  
    public static void writePnm(Image pic, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename); 
    BufferedWriter bw = new BufferedWriter(fw);
    //write the type of image
    bw.write("P3 \n");
    int height = pic.getHeight();
    int width = pic.getWidth();
    //write the comments
    bw.write(pic.getMetadata() + "\n");
    //add size
    bw.write(width + " " + height + "\n");
    //add max range
    bw.write(pic.getMaxRange() + "\n");
    //write pixels
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        bw.write((pic.getPixel(i,j).getRed())+ " " + (pic.getPixel(i,j).getGreen())+ " " +(pic.getPixel(i,j).getBlue()) + "  ");
      }
      //next line
      bw.write("\n");
    }
    
    bw.close();
    fw.close();
  }
  
  public static void writePgm(Image pic, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename); 
    BufferedWriter bw = new BufferedWriter(fw);
    //write image type
    bw.write("P2 \n");
    //turn image to grey
    pic.toGrey();
    //specify pic size
    int height = pic.getHeight();
    int width = pic.getWidth();
    //write the comments
    bw.write(pic.getMetadata() + "\n");
    bw.write(width + " " + height + "\n");
    //add max range
    bw.write(pic.getMaxRange() + "\n");
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        //since its grey the pixel requires only intensity
        bw.write((pic.getPixel(i,j).getRed())+ " ");
      }
      //next line
      bw.write("\n");
    }
    
    bw.close();
    fw.close();
  }
}
  

  
  
  
