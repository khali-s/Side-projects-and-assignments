//mhd said khalifeh

//260687985
// comp 307 assignment 2
// 
import java.util.Scanner;
public class encrypt {

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  //System.out.println("hello");
    int a;
         String s1;
         Scanner scan = new Scanner(System.in);
        

         System.out.println("enter your desired two digit key: ");
         a = scan.nextInt();
         if(a < 10 | a >99){
          System.out.println("please restart the program and select a two digit key");
          scan.close();
         } 
         else{
          System.out.println("your key is "+a);
    

          System.out.println("please enter the message you wish to encrypt: ");
          scan.nextLine();
          s1 = scan.nextLine();
          if(s1.length() > 1000*(a/10)){
           System.out.println("YOUR STRING IS TOO BIG!");
           scan.close();
          }
          else if(s1.isEmpty() | s1.equals(" ") | s1.equals("0")){
           System.out.println("you've entered an invalid string");
           scan.close();
          }
          else{ 
           System.out.println("stand by for encryption..");
          
           scan.close();

  
           encryptString(convertString(s1),a/10);
          }
         }
 }
 
 
 
 public static void encryptString( char[] s, int key ){
  
  
       
  
  //Turn Char Array in 2d block format
  char[][] t = new char[1000][key];
  int counter = 0;
  for(int i = 0; i < 1000; i++ ){
   for(int j = 0; j < key; j++ ){
    if(counter < s.length ){
     t[i][j] = s[counter];
     //System.out.println(t[i][j] +  "h j i");
     counter++; 
    }
    
   }
   
  }
  
  counter = 0;
  
  //transposing elements into 1d array
  int a[] = new int[key*1000];
  for(int j = 0; j < key; j++ ){
   for(int i = 0; i < 1000; i++){
    
       a[counter] = (int) t[i][j];
     //System.out.println(a[counter] +  " in char array");
     counter++;
    
   }
  }
  // ceasar encryption
  int[] b = new int[a.length];
  //int counter= 0;
  //int temp = 0;
  for(int i = 0; i < a.length ; i++ ){
   
    b[i] =  ( a[i] + key) % 255;;
   
    
    //System.out.println(b[i] + " ceasar done");
    
     }
  //bring it back to 2d block to print new encrypted string through display method
  char[][] t2 = new char[1000][key];
  counter = 0;
  for(int i = 0; i < 1000; i++ ){
   for(int j = 0; j < key; j++ ){
     t2[i][j] = (char) b[counter];
     
     counter++; 
    
    
   }
   
  
     }
  //print
  System.out.print("Encrypted string long format: ");
        displayLong(t2);
        System.out.println("");
  System.out.print("Encrypted string: ");
         display(t2);
         System.out.println("");
         //unencryption of previously made b array 
  
  int[] c = new int[a.length];
  //int counter= 0;
  //int temp = 0;
  for(int i = 0; i < a.length ; i++ ){
   
    c[i] =  ( ( b[i] - key) % 255);;
   
    
    //System.out.println(c[i] + " ceasar undone");
    
     }
  //back to the block cipher to print
  char[][] t3 = new char[1000][key];
  counter = 0;
  for(int i = 0; i < key; i++ ){
   for(int j = 0; j < 1000; j++ ){
     t3[j][i] = (char) c[counter];
     //System.out.println(t3[j][i] +  "h j i");
     counter++; 
    
    
   }
   
  
     }
  
  System.out.print("Unencrypted String:");
  
  display(t);
  System.out.println("");
  

  

 }
 
 
 public static void display( char t[][]){

  for(int i = 0; i < t.length; i++ ){
   //hide all array pre-initialization strings 
   for(int j = 0; j < t[0].length; j++ ){
    if(((int)t[i][j]) > 15 ){
     System.out.print(t[i][j]);
    }
    
    
   }
   
  }
  
 }
 public static void displayLong( char t[][]){

  for(int i = 0; i < t.length; i++ ){
   //hide all array pre-initialization strings 
   for(int j = 0; j < t[0].length; j++ ){
   
     System.out.print(t[i][j]);
    
    
    
   }
   
  }
  
 }
 
 public static char[] convertString( String s){
  char[] c = new char[s.length()];
  for(int i = 0; i < s.length(); i++ ){
   
    c[i] = s.charAt(i); 
    
  }
  
  return c;
   
 }
 
 

}
 
 
 
 