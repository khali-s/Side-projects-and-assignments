// STUDENT_NAME:
// STUDENT_ID:

import java.util.*;
import java.io.*;

public class Scrable{
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
     public static String longestWord(char availableLetters[], String prefix) {
      
      
       //first convert char[] to int[]
       int[] integerArray = new int[availableLetters.length];
      // public static int[] convertArray(char[] c){
           //this method converts a char array into and int[] (this is used first to perform the heap algorithm)
           //int[] x = new int[c.length];
           for (int i = 0; i < availableLetters.length; i++) {
                 integerArray[i] = (int)availableLetters[i];
               }      
       int nFact=1;
           //this method measures n! which is equal to the total number of arrangements of a set n             
            for (int i = 1; i <= availableLetters.length; i++) {
                nFact *= i;
            }
       int[][] comboArray = new int[nFact][];
     
      combinations(integerArray, integerArray.length, comboArray);
      
       for(int s =0; s<comboArray[0].length; s++){
         
         for ( int i = 0; i<comboArray.length; i++){
           
           char[] last = new char[availableLetters.length];
           
        
                 //this method converts an int array into a char array {this is used last to transform 
                 //each permutation into a string
                 for (int o = 0; o < availableLetters.length; o++) {
                       last[o] = (char)comboArray[i][o];
                     }
            
                 
               
               
             String temp = prefix;
             
             for(int j =0; j<comboArray[0].length-s; j++){
             
               temp = temp + last[j];
             
           
             }
             if (myDictionary.contains(temp)){
               
             System.out.println("The word " + temp + " is in the dictionary");
             
             return temp;
             
             }
          
         }
         System.out.println("There are  no such words in the dictionary");
       }
 
  String longest = "";

  return longest;


    }
     
     public static void switchArray(int[] m, int l, int z)
     {
         int temporary = m[l];
         
         m[l] = m[z];
         
         m[z] = temporary;
     }
  
     public static void combinations(int[] x, int y, int[][]permutations)
     {
         //Using Heap's algorithm to find all permutations
         if (y == 1){
               // this method adds an int array to the first null spot in the 2d array 
                 for (int i = 0; i < permutations.length; i++) {
                   if (permutations[i]==null) {
                     permutations[i] = copyArray(x);
                     break;
                   
                   }
                 
          }
         }
     
         else
         {
             for (int i = 0; i < y; i++)
             {
                 combinations(x, y - 1, permutations);
                 if (y % 2 == 0) // checking if even
                 {
                    switchArray(x, i, y - 1); 
                 }
                 else
                 {
                     switchArray(x, 0, y - 1); 
                 }
             }
         }
         
     }
     public static int[] copyArray(int[] s) {
         // this method copies an array to another (due to reference type problems)
         int[] t = new int[s.length];
         for (int i = 0; i < s.length; i++) {
             t[i] = s[i];
           }
          return t;
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
     System.out.println("The longest word is "+longest);
      //if (myDictionary.contains("adzuki")) System.out.println("The word " + " a " + " is in the dictionary");

        } while (letters.length!=0);

        keyboard.close();
        
    }
    
}
