/**
 * Auto Generated Java Class.
 */
import java.util.Arrays;
public class HeapsAlg {
  int[][] finalArray;
  
  
  public static void main(String[] args) { 
  int[] x = new int[4];
  x[0] = 1;
  x[1] = 13;
  x[2] = 12;
  x[3] = 18;
  HeapsAlg d = new HeapsAlg();
  d.finalArray = new int[factorial(x.length)][];
  d.permute(x, 4);
  for (int i = 0; i < d.finalArray.length; i++)
   System.out.println(Arrays.toString(d.finalArray[i]));
    
  }
  
  public static void swap(int[] v, int i, int j)
    {
        int t = v[i];
        v[i] = v[j];
        v[j] = t;
    }
 
    public void permute(int[] v, int n)
    {
        
        if (n == 1)
        {
            //x[0] = v;
            //return x;
            System.out.println(Arrays.toString(v));
            equalsArray(this.finalArray,v);
        }
        else
        {
            for (int i = 0; i < n; i++)
            {
                this.permute(v, n - 1);
                if (n % 2 == 1)
                {
                   swap(v, 0, n - 1);
                }
                else
                {
                    swap(v, i, n - 1);
                }
            }
        }
        
    }
     public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static void equalsArray(int[][] x, int[] v) {
      //n = n-1
        //int[][] x = new int[n.length][];
        //int counter = 0;
        //int limit = 0;
        for (int i = 0; i < x.length; i++) {
          if (x[i]==null) {
            x[i] = mergeArray(v);
            break;
          }
       /* }
        for (int i = 1; i < n.length; i++) {
          if (n[i]==null) {
            limit = i;
            break;
          }
        }
        for (int i = counter; i <counter + limit; i++) {
          m[i] = n[i];
        }
        x = m;
        return x; */
    }
    }
    public static int[] mergeArray(int[] v) {
      //n = n-1
        //int[][] x = new int[n.length][];
        //int counter = 0;
        //int limit = 0;
        int[] x = new int[v.length];
        for (int i = 0; i < v.length; i++) {
            x[i] = v[i];
          }
         return x;
     }
  

}