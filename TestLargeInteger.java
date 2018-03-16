


  public class TestLargeInteger {
    public static void main( String args[] ) {
        /* TEST YOUR METHODS BY ADDING CODE HERE */
        /* THIS CODE IS NOT GOING TO BE GRADED. IT'S JUST FOR YOU TO TEST YOUR PROGRAM */
     
        LargeInteger a = new LargeInteger("15345");

        LargeInteger b = new LargeInteger( "645456");
        
      
      System.out.println(b.standardMultiplication( a ));

      
        /*   
      long[] recursFast = new long[1000];
      long[] recurs = new long[1000];
      long[] standard = new long[1000];
      long[] iter = new long[1000];
      for (int i = 0; i<1000; i++){
        int n = 2;
        LargeInteger a = new LargeInteger(n);
        a = a.getRandom(n);
        LargeInteger b = new LargeInteger( n);
        b = b.getRandom(n);
        //System.out.println(a + " + " + b + " = " + a.add( b ) );
        //System.out.println(b + " - " + a + " = " + b.subtract( a ) );
        long currentTime = System.nanoTime();
        b.recursiveFastMultiplication(a);
        long currentTime2 = System.nanoTime();
        recursFast[i] = (currentTime2-currentTime);
        
        currentTime = System.nanoTime();
        b.standardMultiplication( a );
        currentTime2 = System.nanoTime();
        standard[i] = (currentTime2-currentTime);
        currentTime = System.nanoTime();
        b.recursiveMultiplication( a );
        currentTime2 = System.nanoTime();
        recurs[i] = (currentTime2-currentTime);
      }
     
       
       
        System.out.println(avg(standard));
        System.out.println(avg(recurs)) ;
        System.out.println(avg(recursFast)) ;
        
        
        /* int n = 8;
        LargeInteger a = new LargeInteger(n);
        a = a.getRandom(n);
        LargeInteger b = new LargeInteger( n);
        b = b.getRandom(n);
        
        
         long currentTime = System.nanoTime();
        b.iterativeAddition( a );
        long currentTime2 = System.nanoTime();
        System.out.println(currentTime2-currentTime); 
        
          
       
        
  
  
  
  
  }

    public static double avg(long[] x) {
     double y = 0;
     for (int i = 0; i<1000; i++){
        y = y + x[i];
        
        
      }
     y = y/1000;
     
     return y;
     */
     
      
    
    
    }




}