import java.lang.Math;

/*********************************************************/
/* NAME:Mhd Said Khalifeh                                          */
/* STUDENT ID: 260687985                                           */
/*********************************************************/

/* This class stores and manipulates very large non-negative integer numbers 
   The digits of the number are stored in an array of bytes. */
class LargeInteger {

    /* The digits of the number are stored in an array of bytes. 
       Each element of the array contains a value between 0 and 9. 
       By convention, digits[digits.length-1] correspond to units, 
       digits[digits.length-2] corresponds to tens, digits[digits.length-3] 
       corresponds to hundreds, etc. */

    byte digits[];


    
    /* Constructor that creates a new LargeInteger with n digits */
    public LargeInteger (int n) {
        digits= new byte[n];
    }

        
    /* Constructor that creates a new LargeInteger whose digits are those of the string provided */
    public LargeInteger (String s) {        
        digits = new byte[s.length()]; /* Note on "length" of arrays and strings: Arrays can be seen 
                                          as a class having a member called length. Thus we can access 
                                          the length of digits by writing digits.length
                                          However, in the class String, length is a method, so to access 
                                          it we need to write s.length() */

        for (int i=0;i<s.length();i++) digits[i] = (byte)Character.digit(s.charAt(i),10);
        /* Here, we are using a static method of the Character class, called digit, which 
           translates a character into an integer (in base 10). This integer needs to be 
           cast into a byte. ****/
    }


    /* Constructor that creates a LargeInteger from an array of bytes. Only the bytes  
       between start and up to but not including stop are copied. */
    public LargeInteger (byte[] array, int start, int stop) {
        digits = new byte[stop-start];
        for (int i=0;i<stop-start;i++) digits[i] = array[i+start];
    }


    /* This method returns a LargeInteger where eventual leading zeros are removed. 
       For example, it turns 000123 into 123. Special case: it turns 0000 into 0. */
    public LargeInteger removeLeadingZeros() {
        if (digits[0]!=0) return this;
        int i = 1;
        while (i<digits.length && digits[i]==0) i++;
        if (i==digits.length) return new LargeInteger("0");
        else return new LargeInteger(digits,i,digits.length);
    } // end of removeLeadingZeros
   

    /* This methods multiplies a given LargeInteger by 10^nbDigits, simply by shifting 
       the digits to the left and adding nbDigits zeros at the end */
    public LargeInteger shiftLeft(int nbDigits) {
        LargeInteger ret = new LargeInteger( digits.length + nbDigits );
        for (int i = 0 ; i < digits.length ; i++) ret.digits[ i ] = digits[ i ];
        for (int i = 0; i <  nbDigits; i++) ret.digits[ digits.length + i ] = 0;
        return ret;
    } // end of shiftLeft


      /* Returns true if the value of this is the same as the value of other */
    public boolean equals (LargeInteger other) {
        if ( digits.length != other.digits.length ) return false;
        for (int i = 0 ; i < digits.length ;i++ ) {
            if ( digits[i] != other.digits[i] ) return false;
        }
        return true;
    } // end of equals


      /* Returns true if the value of this is less than the value of other ****/
    public boolean isSmaller (LargeInteger other) {
        if ( digits.length > other.digits.length ) return false;
        if ( digits.length < other.digits.length ) return true;
        for (int i = 0 ; i < digits.length ; i++ ) {
            if ( digits[i] < other.digits[i] ) return true;
            if ( digits[i] > other.digits[i] ) return false;
        }
        return false;
    } // end of isSmaller
    


    /* This method adds two LargeIntegers: the one on which the method is 
       called and the one given as argument. The sum is returned. The algorithms 
       implemented is the normal digit-by-digit addition with carry. */

    LargeInteger add(LargeInteger other) {
        int size = Math.max( digits.length,other.digits.length );

        /* The sum can have at most one more digit than the two operands */
        LargeInteger sum = new LargeInteger( size + 1 ); 
        byte carry = 0;

        for (int i = 0; i < size + 1 ;i++) {
            // sumColumn will contain the sum of the two digits at position i plus the carry
            byte sumColumn = carry; 
            if ( digits.length - i  - 1 >= 0) sumColumn += digits[ digits.length - i - 1 ];
            if (other.digits.length - i - 1  >= 0) sumColumn += other.digits[ other.digits.length - i - 1 ];
            sum.digits[ sum.digits.length - 1 - i ] = (byte)( sumColumn % 10 ); // The i-th digit in the sum is sumColumn mod 10
            carry = (byte)( sumColumn / 10 );          // The carry for the next iteration is sumColumn/10
        }        
        return sum.removeLeadingZeros();
    } // end of add



    /* This method subtracts the LargeInteger other from that from where the method is called.
       Assumption: the argument other contains a number that is not larger than the current number. 
       The algorithm is quite interesting as it makes use of the addition code.
       Suppose numbers X and Y have six digits each. Then X - Y = X + (999999 - Y) - 1000000 + 1.
       It turns out that computing 999999 - Y is easy as each digit d is simply changed to 9-d. 
       Moreover, subtracting 1000000 is easy too, because we just have to ignore the '1' at the 
       first position of X + (999999 - Y). Finally, adding one can be done with the add code we already have.
       This tricks is the equivalent of the method used by most computers to do subtractions on binary numbers. ***/

    public LargeInteger subtract( LargeInteger other ) {
        // if other is larger than this number, simply return 0;
        if (this.isSmaller( other ) || this.equals( other ) ) return new LargeInteger( "0" );

        LargeInteger complement = new LargeInteger( digits.length ); /* complement will be 99999999 - other.digits */
        for (int i = 0; i < digits.length; i++) complement.digits[ i ] = 9;
        for (int i = 0; i < other.digits.length; i++) 
            complement.digits[ digits.length - i - 1 ] -= other.digits[other.digits.length - i -  1];

        LargeInteger temp = this.add( complement );     // add (999999- other.digits) to this
        temp = temp.add(new LargeInteger( "1" ));       // add one

        // return the value of temp, but skipping the first digit (i.e. subtracting 1000000)
        // also making sure to remove leading zeros that might have appeared.
        return new LargeInteger(temp.digits,1,temp.digits.length).removeLeadingZeros();
    } // end of subtract


    /* Returns a randomly generated LargeInteger of n digits */
    public static LargeInteger getRandom( int n ) {
        LargeInteger ret = new LargeInteger( n );
        for (int i = 0 ; i < n ; i++) {
            // Math.random() return a random number x such that 0<= x <1
            ret.digits[ i ]=(byte)( Math.floor( Math.random() * 10) );
            // if we generated a zero for first digit, regenerate a draw
            if ( i==0 && ret.digits[ i ] == 0 ) i--;
        }
        return ret;
    } // end of getRandom



    /* Returns a string describing a LargeInteger 17*/
    public String toString () {        

        /* We first write the digits to an array of characters ****/
        char[] out = new char[digits.length];
        for (int i = 0 ; i < digits.length; i++) out[ i ]= (char) ('0' + digits[i]);

        /* We then call a String constructor that takes an array of characters to create the string */
        return new String(out);
    } // end of toString




    /* This function returns the product of this and other by iterative addition */
    public LargeInteger iterativeAddition(LargeInteger other) {
      LargeInteger total = new LargeInteger(other.toString());
      //new variable to hold the iterative addition
      LargeInteger one = new LargeInteger( "1" );
      /* make a one LargeInteger for the "For-loop" paramater i*/
      for(LargeInteger i = new LargeInteger(this.toString());!(i.equals(one)); i=i.subtract(one)){
        total= total.add(other);
      
      }
        return total;
    } 



    /* This function returns the product of this and other by using the standard multiplication algorithm */
    public LargeInteger standardMultiplication(LargeInteger other) {
        LargeInteger total = new LargeInteger("0"); //intialise the total
        for (int i = this.digits.length-1; i>=0; i--){ //start the loop
          int l = -1 ; //initializing value
          LargeInteger tmpAdd = new LargeInteger(other.digits.length + 1); 
          int carry = 0;//for the extra remains of the integer multiplication
          for (int j = other.digits.length-1; j>=0; j--){ 
            int c = this.digits[i]*other.digits[j] + carry;
           // System.out.println(j);
            tmpAdd.digits[j+1] = (byte)(c%10);
            l=j;
            //System.out.println(tmpAdd.digits[j+1]);
            carry = c/10;
          }
           // System.out.println(carry);}
            tmpAdd.digits[l]= ((byte)carry);
            //System.out.println(tmpAdd.digits[l]);
            
            int s = this.digits.length-i-1;
            tmpAdd = tmpAdd.shiftLeft(s);
            //System.out.println(s);
            //System.out.println(tmpAdd);
         
            
          
         // System.out.println(i);
         // System.out.println(total);
          total = total.add(tmpAdd);
          //System.out.println(total);
           
   
     

    }  // end of the LargeInteger class

return total;
    } // end of standardMultiplication
                


    /* This function returns the product of this and other by using the basic recursive approach described 
       in the homework. Only use the built-in "*" operator to multiply single-digit numbers */
    public LargeInteger recursiveMultiplication( LargeInteger other ) {
       // left and right halves of this and number2                                                                                        
        LargeInteger leftThis, rightThis, leftOther, rightOther;
        LargeInteger term1,  term2,  term3,  term4, sum; // temporary terms                                                                      

        if ( digits.length==1 && other.digits.length==1 ) {
            int product = digits[0] * other.digits[0];
            return new LargeInteger( String.valueOf( product ) );
        }

        int k = digits.length;
        int n = other.digits.length;
        leftThis = new LargeInteger( digits, 0, k - k/2 );
        rightThis = new LargeInteger( digits, k - k/2, k );
        leftOther = new LargeInteger( other.digits, 0, n - n/2 );
        rightOther = new LargeInteger( other.digits, n - n/2, n );

        /* now recursively call recursiveMultiplication to compute the                    
           four products with smaller operands  */

        if ( n > 1 && k > 1 )  term1 = rightThis.recursiveMultiplication(rightOther );
        else term1 = new LargeInteger( "0" );

        if ( k>1 ) term2 = ( rightThis.recursiveMultiplication( leftOther ) ).shiftLeft( n/2 );
        else term2 = new LargeInteger( "0" );

        if ( n>1 ) term3 = ( leftThis.recursiveMultiplication( rightOther ) ).shiftLeft( k/2 );
        else term3 = new LargeInteger( "0" );

        term4 = ( leftThis.recursiveMultiplication( leftOther ) ).shiftLeft( k/2 + n/2 );

        sum = new LargeInteger( "0" );
        sum = sum.add( term1 );
        sum = sum.add( term2 );
        sum = sum.add( term3 );
        sum = sum.add( term4 );

        return sum;

       
    } // end of recursiveMultiplication             


    /* This method returns the product of this and other by using the faster recursive approach 
       described in the homework. It only uses the built-in "*" operator to multiply single-digit numbers */
    public LargeInteger recursiveFastMultiplication(LargeInteger other) {
       // left and right halves of this and number2
      
      int k = -1;
      int n = -1;
      String aString, bString;
      
      if ((other.isSmaller(this))){ 
      
       aString = this.toString();
       bString = other.toString();
       n = this.digits.length;
       k = other.digits.length;
      }
      else {
       aString = other.toString();
       bString = this.toString();
       n = other.digits.length;
       k = this.digits.length;
      
      }
      
      
      if (k==1) return this.standardMultiplication(other);
     // System.out.println(k+" =k " + n+"= n");
      
     // intialize string to split the digit in two halves (left and right) 
      String aRight ="";
      String aLeft ="";
      String bRight ="";
      String bLeft ="";
      
      
      for ( int i = (n-n/2); i<n; i++) {
        aRight = aRight + aString.charAt(i);
        
      
      }
      for ( int i = (k-k/2); i<k; i++) {
        bRight = bRight + bString.charAt(i);
      
      
      }
      for ( int i = 0; i<(n-n/2); i++) {
        aLeft = aLeft + aString.charAt(i);
      
      
      }
      
       for ( int i = 0; i<(k-k/2); i++) {
        bLeft = bLeft + bString.charAt(i);
       
      
      
       }
       //use string as template to make large integers and thenintialize terms and sum
      LargeInteger aIntRight = new LargeInteger(aRight);
      LargeInteger aIntLeft = new LargeInteger(aLeft);
      LargeInteger bIntRight = new LargeInteger(bRight);
      LargeInteger bIntLeft = new LargeInteger(bLeft);
      
      LargeInteger term1 =  new LargeInteger("0");
      LargeInteger term2 =  new LargeInteger("0");
      LargeInteger term3 =  new LargeInteger("0");
      LargeInteger term4 =  new LargeInteger("0");
      LargeInteger sum =  new LargeInteger("0");
       
      //apply equations
      
      term1 = aIntRight.recursiveFastMultiplication(bIntRight);
      
     
      
      term2 = aIntLeft.recursiveFastMultiplication(bIntLeft);
      
      
      
      aIntLeft = aIntLeft.shiftLeft(n/2 - k/2);
      
      aIntLeft = aIntLeft.add(aIntRight);
      
      bIntLeft = bIntLeft.add(bIntRight);
       
      term3 = bIntLeft.recursiveFastMultiplication(aIntLeft );
     
      term3 = term3.subtract(term2.shiftLeft(n/2 - k/2));
      
      term3 = term3.subtract(term1);
     
      
      sum = (term2.shiftLeft(n/2 + k/2)).add((term3.shiftLeft(k/2)).add(term1));
        
      return sum;
        
   }
   
      
      
      
     

}