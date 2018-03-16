import java.util.*;
import java.io.*;


class Sudoku
 
{
    /* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For 
     * a standard Sudoku puzzle, SIZE is 3 and N is 9. */
    int SIZE, N;
      List<LinkedList<Integer>> row ;
     List<LinkedList<Integer>> column;
     List<LinkedList<Integer>> box;

    /* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
     * not yet been revealed are stored as 0. */
    int Grid[][];

   
    public static LinkedList<Integer> realList(LinkedList<Integer> temp){
     LinkedList<Integer> real = new LinkedList<Integer>();
     for(int i =1; i<10 ; i++){
       if(!temp.contains(i)){
         real.add(i);
         //System.out.println(i);
       }
     }
     return real;
    }
    /*The solve() method should remove all the unknown characters ('x') in the Grid
     * and replace them with the numbers from 1-9 that satisfy the Sudoku puzzle. */
    public void solve(){
     this.row = new ArrayList<LinkedList<Integer>>(this.N);
     this.column = new ArrayList<LinkedList<Integer>>(this.N);
     this.box = new ArrayList<LinkedList<Integer>>(this.N);
     for (int i=0; i<this.N; i++){
       LinkedList<Integer> x = new LinkedList<Integer>();
       LinkedList<Integer> y = new LinkedList<Integer>();
       for(int j=0;j<this.N;j++){ 
         if(this.Grid[i][j] != 0) {
           x.add(this.Grid[i][j]);
         }//add rows
          if(this.Grid[j][i] != 0) y.add(this.Grid[j][i]); //add columns
       }
      x=realList(x);
      y=realList(y);
       row.add(x);
       column.add(y);
     }
      for(int boxV =0; boxV<this.SIZE;boxV++){
      for(int boxH=0; boxH<this.SIZE;boxH++){
      int u = boxV*this.SIZE;
      int l = boxH*this.SIZE;
      LinkedList<Integer> z = new LinkedList<Integer>();
      for(int j = u ; j < (this.SIZE + u); j++){ 
        for(int i = l ; i < (this.SIZE + l); i++){ 
          if(this.Grid[j][i] != 0){
            z.add(this.Grid[j][i]);
          }
        }
      }
      z=realList(z);
      box.add(z);
      }
     }
      cracker(0,0,this.Grid);
      
    }
     boolean cracker(int i, int j, int[][] cells) {
       int boxNum = 0;   
        if (i==9){
            i=0;
            if (++j == 9) return true;
        }
        if(i>8){
         boxNum = whichBox(8,i);
       }
       else{
         boxNum = whichBox(i,j);
       }
        if (cells[i][j] != 0){  // skip filled cells
            return cracker(i+1,j,cells);
        }
        for (int val = 1; val <= 9; ++val) {
            if (isLegal(i,j,val,cells)) {
                cells[i][j] = val;
               
                if (cracker(i+1,j,cells)){
                    return true;
                }
                else{
               // remove from the available list
                     row.get(i).add(val);
                   column.get(j).add(val);
                  // System.out.print("i= "+ i + ", j= " + j + ", boxNum= " + boxNum);
                  box.get(boxNum).add(val);
                
                }
            }
        }
        cells[i][j] = 0; // reset on backtrack
        return false;
    }


      boolean isLegal(int i, int j, int val, int[][] cells) {
       int boxNum = whichBox(i,j);
        // System.out.println("h");
       if((row.get(i).contains(val) && column.get(j).contains(val) && box.get(boxNum).contains(val))){
        
            row.get(i).removeFirstOccurrence(val);
            column.get(j).removeFirstOccurrence(val);
            box.get(boxNum).removeFirstOccurrence(val);
       
       return true;
       }
       

        return false; // no violations, so it's legal
    }
  
    public int whichBox(int y, int x){
    //x-->0,1,2 
    int boxV= y/this.SIZE;
    int boxH = x/this.SIZE;
    int num = boxV*this.SIZE + boxH;
    return num;
    } 
    public boolean isDone(){
    
        for(int i = 0; i < this.N ; i++){
         for(int j = 0; j < this.N ; j++){
           if(this.Grid[i][j] == 0) return false;
         }
        }
        return true;
    }


    /*****************************************************************************/
    /* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE FUNCTIONS BELOW THIS LINE. */
    /*****************************************************************************/
 
    /* Default constructor.  This will initialize all positions to the default 0
     * value.  Use the read() function to load the Sudoku puzzle from a file or
     * the standard input. */
    public Sudoku( int size )
    {
        SIZE = size;
        N = size*size;

        Grid = new int[N][N];
        for( int i = 0; i < N; i++ ) 
            for( int j = 0; j < N; j++ ) 
                Grid[i][j] = 0;
    }


    /* readInteger is a helper function for the reading of the input file.  It reads
     * words until it finds one that represents an integer. For convenience, it will also
     * recognize the string "x" as equivalent to "0". */
    static int readInteger( InputStream in ) throws Exception
    {
        int result = 0;
        boolean success = false;

        while( !success ) {
            String word = readWord( in );

            try {
                result = Integer.parseInt( word );
                success = true;
            } catch( Exception e ) {
                // Convert 'x' words into 0's
                if( word.compareTo("x") == 0 ) {
                    result = 0;
                    success = true;
                }
                // Ignore all other words that are not integers
            }
        }

        return result;
    }


    /* readWord is a helper function that reads a word separated by white space. */
    static String readWord( InputStream in ) throws Exception
    {
        StringBuffer result = new StringBuffer();
        int currentChar = in.read();
 String whiteSpace = " \t\r\n";
        // Ignore any leading white space
        while( whiteSpace.indexOf(currentChar) > -1 ) {
            currentChar = in.read();
        }

        // Read all characters until you reach white space
        while( whiteSpace.indexOf(currentChar) == -1 ) {
            result.append( (char) currentChar );
            currentChar = in.read();
        }
        return result.toString();
    }


    /* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
     * grid is filled in one row at at time, from left to right.  All non-valid
     * characters are ignored by this function and may be used in the Sudoku file
     * to increase its legibility. */
    public void read( InputStream in ) throws Exception
    {
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                Grid[i][j] = readInteger( in );
            }
        }
    }


    /* Helper function for the printing of Sudoku puzzle.  This function will print
     * out text, preceded by enough ' ' characters to make sure that the printint out
     * takes at least width characters.  */
    void printFixedWidth( String text, int width )
    {
        for( int i = 0; i < width - text.length(); i++ )
            System.out.print( " " );
        System.out.print( text );
    }


    /* The print() function outputs the Sudoku grid to the standard output, using
     * a bit of extra formatting to make the result clearly readable. */
    public void print()
    {
        // Compute the number of digits necessary to print out each number in the Sudoku puzzle
        int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

        // Create a dashed line to separate the boxes 
        int lineLength = (digits + 1) * N + 2 * SIZE - 3;
        StringBuffer line = new StringBuffer();
        for( int lineInit = 0; lineInit < lineLength; lineInit++ )
            line.append('-');

        // Go through the Grid, printing out its values separated by spaces
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                printFixedWidth( String.valueOf( Grid[i][j] ), digits );
                // Print the vertical lines between boxes 
                if( (j < N-1) && ((j+1) % SIZE == 0) )
                    System.out.print( " |" );
                System.out.print( " " );
            }
            System.out.println();

            // Print the horizontal line between boxes
            if( (i < N-1) && ((i+1) % SIZE == 0) )
                System.out.println( line.toString() );
        }
    }


    /* The main function reads in a Sudoku puzzle from the standard input, 
     * unless a file name is provided as a run-time argument, in which case the
     * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
     * outputs the completed puzzle to the standard output. */
    public static void main( String args[] ) throws Exception
    {
        InputStream in;
        if( args.length > 0 ) 
            in = new FileInputStream( args[0] );
        else
            in = System.in;

        // The first number in all Sudoku files must represent the size of the puzzle.  See
        // the example files for the file format.
        int puzzleSize = readInteger( in );
        if( puzzleSize > 100 || puzzleSize < 1 ) {
            System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
            System.exit(-1);
        }

        Sudoku s = new Sudoku( puzzleSize );

        // read the rest of the Sudoku puzzle
        s.read( in );

        // Solve the puzzle.  We don't currently check to verify that the puzzle can be
        // successfully completed.  You may add that check if you want to, but it is not
        // necessary.
        s.solve();

        // Print out the (hopefully completed!) puzzle
        s.print();
    }
}
