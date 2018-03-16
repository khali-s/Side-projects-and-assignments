// STUDENT_NAME:Mhd Said Khalifeh 
// STUDENT_ID:260687985
    /* **************************************************************************************************************
     * COMMENTS ABOUT MY ALGORITHM AND HOW IT WORKS
     * 1. take the character array and make an int array out of it
     * 2. put that int array into a heap's algorithm that will non-statically add the output into a 2d int array
     * 3. add a nested loop that will take that 2d array convert each subarray into a string and then add it with the 
     * prefix and call upon boolean method that checks whether that word exists in the dictionary, if it finds it
     * it returns it, if it doesn't it will truncate the end of each character in each subarray by one and continue checking
     * until nothing is left.
     * 
     * *************************************************************************************************************
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
import java.util.*;
import java.io.*;



public class Scrabble{
   int[][] finalArray;
   //for the non-static call

    static HashSet<String> myDictionary; // this is where the words of the dictionary are stored

    // DO NOT CHANGE THIS METHOD
    // Reads dictionary from file
    public static void readDictionaryFromFile(String fileName) throws Exception {
        myDictionary=new HashSet<String>();

        BufferedReader myFileReader = new BufferedReader(new FileReader(fileName) );

        String word;
        while ((word=myFileReader.readLine())!=null) myDictionary.add(word);

 myFileReader.close();
    }



    /* Arguments: 
        char availableLetters[] : array of characters containing the letters that remain available
        String prefix : Word assembled to date
        Returns: String corresponding to the longest English word starting with prefix, completed with zero or more letters from availableLetters. 
          If no such word exists, it returns the String ""*/
    //Swapping algorithm for the shuffle (heap) algorithm 
    public static void swap(int[] n, int i, int j)
    {
        int t = n[i];
        n[i] = n[j];
        n[j] = t;
    }
 
    public void shuffle(int[] w, int n)
    {
        //a heap algorithm to have a 2d array that includes each permutation of n objects
        //PSEUDOCODE : https://en.wikipedia.org/wiki/Heap's_algorithm
      
        if (n == 1)
        {
            //x[0] = v;
            //return x;
           // System.out.println(Arrays.toString(v));
            equalsArray(this.finalArray,w);
        }
        else
        {
            for (int i = 0; i < n; i++)
            {
                this.shuffle(w, n - 1);
                //to check if odd or not
                if (n % 2 == 1)
                {
                   swap(w, 0, n - 1);
                }
                else
                {
                    swap(w, i, n - 1);
                }
            }
        }
        
    }
     public static int factorial(int n) {
       //this method measures n! which is equal to the total number of arrangements of a set n
        int mult = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            mult *= i;
        }
        return mult;
    }
    public static void equalsArray(int[][] x, int[] v) {
      // this method adds an int array to the first null spot in the 2d array 
        for (int i = 0; i < x.length; i++) {
          if (x[i]==null) {
            x[i] = mergeArray(v);
            break;
          }
       
        }
    }
    public static int[] mergeArray(int[] v) {
        // this method copies an array to another (due to reference type problems)
        int[] x = new int[v.length];
        for (int i = 0; i < v.length; i++) {
            x[i] = v[i];
          }
         return x;
         
     }
    public static int[] convertArray(char[] c){
      //this method converts a char array into and int[] (this is used first to perform the heap algorithm)
      int[] x = new int[c.length];
      for (int i = 0; i < c.length; i++) {
            x[i] = (int)c[i];
          }
         return x;
      
    
    }
    public static char[] convertArray(int[] c){
      //this method converts an int array into a char array {this is used last to transform 
      //each permutation into a string
      char[] x = new char[c.length];
      for (int i = 0; i < c.length; i++) {
            x[i] = (char)c[i];
          }
         return x;
      
    
    }
     public static String longestWord(char availableLetters[], String prefix) {
       
      
       //first convert char[] to int[]
       int[] integerArray = new int[availableLetters.length];
       
       integerArray = convertArray(availableLetters); 
       //then make non-static 2d array that will hold all arrangements
       Scrabble d = new Scrabble();
       //there are n! arrangements; where n is the # of letters provided
       d.finalArray = new int[factorial(integerArray.length)][];
       //this method will shuffle them then return them to the 2d array
       d.shuffle(integerArray, integerArray.length);
       //this is the 3 dimentional loop. Summary:
       //loop s is in charge of the length of each permutation (at first it's n-letters, then n-1, then n-2...etc 
       //loop i is in charge of indicating the specific array in the 2d array of int
       //this is also where each specific integer array gets converted back into a character array
       // loop j is responsible for constructing the string with the provided prefix and then checking whether
       //it is part of the dictionary just outside that j loop
       // when such a string is identified that string will be returned and will be the longest due to the s loop
       //if nothing exists in the dictionary then there will be a print method in the end indicating so.
       for(int s =0; s<d.finalArray[0].length; s++){
         
         for ( int i = 0; i<d.finalArray.length; i++){
           
           char[] last = new char[d.finalArray[0].length];
           
             last = convertArray(d.finalArray[i]);
             
             String temp = prefix;
             
             for(int j =0; j<d.finalArray[0].length-s; j++){
             
               temp = temp + last[j];
             
           
             }
             if (myDictionary.contains(temp)){
               
             System.out.println("The word " + temp + " is in the dictionary");

             return temp;
             
             }
          
         }
         
       }
       //incase the prefix is in the dictionary
       if (myDictionary.contains(prefix))System.out.println("only the word " + prefix + " is in the dictionary");
       else System.out.println("There are  no such words in the dictionary"); 

      
       //String d= new String(availableLetters);
       //int x = availableLetters.length;
       //if (availableLetters.length==1) return d;
       // for(int i=0; i<x; i++){
        // char[] s = new char[availableLetters.length-1];
        // for(int j = 0; j<x-1;j++){
          // if (j!=i) {
          // s[j] = availableLetters[j] ;
          // }
          // longestWord(s, prefix);
           
        // }
      // }
        
  /* WRITE YOUR CODE HERE */
  String longest = "";

  // example of how to check with a string is in the dictionary. Remove this line when you understand how this works. 
  //if (myDictionary.contains(prefix)) System.out.println("The word " + prefix + " is in the dictionary");

  return longest;


    }

    
    
    /* main method
        You should not need to change anything here.
     */
    public static void main (String args[]) throws Exception {
       
 // First, read the dictionary
 try {
     readDictionaryFromFile("englishDictionary.txt");
        }
        catch(Exception e) {
            System.out.println("Error reading the dictionary: "+e);
        }
        
        
        // Ask user to type in letters
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in) );
        char letters[]; 
        String prefix = "";
        do {
            //added the prefix call here
            System.out.println("Enter your prefix):");
            
            prefix = keyboard.readLine();
            
            System.out.println("Enter your letters (no spaces or commas):");
            
            letters = keyboard.readLine().toCharArray();

     // now, enumerate the words that can be formed
            String longest = longestWord(letters, prefix);
          if (longest!="") System.out.println("The longest word is "+longest);
      //if (myDictionary.contains("adzuki")) System.out.println("The word " + " a " + " is in the dictionary");

        } while (letters.length!=0);

        keyboard.close();
        
    }
    
}
