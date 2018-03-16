import java.io.*;
public class Comp202Photoshop {
  //make sure array has a correct size
  
 public static void main(String[] args) {
    if(args.length<4){
      throw new IllegalArgumentException("Invalid Statement");
    }
    //store picture
  try{
   Image input;
   input = ImageFileUtilities.read(args[0]);
 
   
   if(args[3].charAt(2)=='h'){
     input.flip(true);
   
   }
   if(args[3].charAt(2)=='v'){
     input.flip(false);
   
   }
   if(args[3].charAt(2)=='s'){
     input.toGrey();
   
   }
   if(args[3].charAt(2)=='r'){
     int x1 = Integer.parseInt(args[4]);
     int y1 = Integer.parseInt(args[5]);
     int x2 = Integer.parseInt(args[6]);
     int y2 = Integer.parseInt(args[7]);
     input.crop(x1,y1,x2,y2);
   
   
   
   }
     //if out put is pgm
   if(args[2].charAt(1)=='g'){
     ImageFileUtilities.writePgm(input,args[1]);
   
   }
   else if(args[2].charAt(1)=='n'){
     ImageFileUtilities.writePnm(input,args[1]);
   
   }
   
   }catch(IOException e){
      e.printStackTrace();
    } 
  }
  
  
}
