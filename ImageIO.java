import java.util.Scanner;
import java.io.*;
public class ImageIO{

  public static void main(String[] args) {
    int[][] image;
    try{
      image = readImage("./Images/image.pbm");
      wrteImage("./Images/image-copy.pbm", image);
    }catch(IOException e){
      e.printStackTrace();
    }
  
  
  }
  
  public static void writeImage(String outName, int[][] Values) throws IOException{
    //writes a pbm file
    FileWriter fw = new FileWriter(outName);//creates a file writer
    BufferedWriter bw = new BufferedWriter(fw); // creates a buffered writer
    bw.write("P1\n");
    int height = values.length;
    int width = values[0].length;
    bw.write(width + " " + height + "\n");
    for(int i=0; i<values.length;i++){
      for(int j=0;j<values[i].length; j++){
        bw.write(values[i][j]+ " ");
      
      }
      bw.write("\n");
    
    }

  
    bw.close();
    fw.close();
  
  }
  
  
  
  
  
  public static int[][] readImage(String filename) throws IOException{
    Scanner sc = new Scanner(new File(filename));
    int[][] values;
    String temp = sc.nextLine(); //for p1 header
    temp = sc.nextLine();//for 1 comment
    int width = sc.nextInt();
    int height = sc.nextInt();
    values = new int[height][width];
    //while(sc.hasNext()){
    for(int i=0; i<values.length; i++){
      for(int j=0; i<values[i].length; j++){
        values[i][j] = sc.nextInt();
      
      }
      
    }
    sc.close();
    return values;
  
  }

}