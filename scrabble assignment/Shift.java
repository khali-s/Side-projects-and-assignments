
public class Shift {
  
   public static String[] shift(String[] availableLetters) {
       //my code is here
     int arraySize = 1;
     String[] numberOfChars = new String[availableLetters.length];
     for( int i = 0 ; i<availableLetters.length; i++){
       numberOfChars[i] = availableLetters[i]; 
     }
     arraySize = factorial(availableLetters.length);
     
       
       
       //each permutation
     String[] totalSemi;
     String[] total = null;
     
     int counter = 0;
       
       if (availableLetters.length==1){
         return availableLetters; 
       } 
        for(int i=0; i<availableLetters.length; i++){
          String[] s = new String[availableLetters.length-1];
         for(int j = 0; j<availableLetters.length-1;j++){
           if (j!=i) {
           s[j] = numberOfChars[j] ;
           System.out.println(s[j]);
           
           counter++;
          total = addStrings(total,shift(s));
           }
          
           
         
           
         }
         
         
         
         
       }
       return total;
   }
        public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
        
        public static String[] addToString(String[] s, String add){
          String[] newString = new String[s.length];
          for(int i=0; i<s.length; i++){
            newString[i] = s[i] + add;
          }
          
          return newString;
        }
        
        public static String[] addStrings(String[] s, String[] add){
          String[] newString = null;
          if (null==s) { 
            newString = new String[add.length];
            for(int i=0; i<add.length; i++){
              newString[i] = add[i];
            }
          }
          else {
            newString = new String[(s.length + add.length)];
            for(int i=0; i<newString.length; i++){
              if ( i < s.length ) newString[i] = s[i];
              else {newString[i] = add[i-s.length];
              }
            }
          }
          
          return newString;
        }
          
  public static void main(String[] args) { 
        String toPrint[] = new String[2];
        toPrint[0] = "9";
        toPrint[1] = "2";
        
          toPrint = shift(toPrint);
          for(int i = 0 ; i<toPrint.length ; i++){
            System.out.println(toPrint[i]);
          }
 
    
  }
  
  
  /* ADD YOUR CODE HERE */
  
}
