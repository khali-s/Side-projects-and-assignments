//need the import for the InputMismatchException
import java.util.InputMismatchException;

//a class that describe an n-dimensional vector
public class Vector{
  //array to hold the coordinates
  private double[] coords;
  //double for the magnitude
  private double magnitude;
  
  //constructor
  public Vector(){
    System.out.println("Making a new Vector");
    this.magnitude = -1;
  }
  
  //another constructor
  public Vector(int dimension){
    //double[] coords = new double[dimension];
    this.coords = new double[dimension];
    this.magnitude = 0;
  }
  
  //another constructor
  public Vector(double[] coordinates){
    //coords should be a copy of coordinates
    //this.coords = coordinates; doesn't make a copy
    this.coords = new double[coordinates.length];
    for(int i=0; i<this.coords.length; i++){
      this.coords[i] = coordinates[i];
    }
    this.computeMagnitude();
  }
  
  //get method for magnitude
  public double getMagnitude(){
    return this.magnitude;
  }
  //No set method!
  
  //get method for coodinates
  //returns a copy!
  public double[] getCoords(){
    double[] c = new double[this.coords.length];
    for(int i =0; i<c.length; i++){
      c[i] = this.coords[i];
    }
    return c;
    //return this.coords; <- this would return a reference to the
    //coords array
  }
  
  //set method that sets a coordinate at an index
  public void setCoordAtIndex(int index, double value){
    this.coords[index] = value;
    this.computeMagnitude();
  }
  
  //set method for all of the coordinates
  public void setAllCoords(double[] numbers){
    //relying on short-cicuiting
    if(this.coords != null && this.coords.length != numbers.length){
      throw new InputMismatchException("Cannot change the dimension of a Vector");
    }
    //either coords is null. Or coords and numbers have the same length
    if(this.coords == null){
      this.coords = new double[numbers.length];
    }
    //coords is not null, and has the same length as numbers
    for(int i=0; i<this.coords.length; i++){
      this.coords[i] = numbers[i];
    }
  }
  
  //print method
  //v.print()
  public void print(){
    //code to print the contents of the coords
    //the this keyword is a name for the object that made the method call.
    for(int i=0; i<this.coords.length; i++){
      System.out.println("coordinate " + i + " " + this.coords[i]);
    }
  }
  
  //static add method
  //adds an array of Vectors together
  //z = v1+v2+v3+....vn
  public static Vector add(Vector[] vectors){
    Vector z = new Vector(vectors[0].coords.length);
    for(int i=0; i<vectors.length; i++){
      //z = z + vectors[i]
      z.add(vectors[i]);
    }
    return z;
  }
  
  //static add method
  //z=x+y
  public static Vector add(Vector x, Vector y){
    if(x.coords.length!=y.coords.length){
      //not going to add them
      throw new IllegalArgumentException();
    }
    //here, we know that they have the same length
    Vector z = new Vector();
    z.coords = new double[x.coords.length];
    for(int i=0; i<x.coords.length; i++){
      z.coords[i] = x.coords[i] + y.coords[i];
    }
    return z;
  }
  
  //non-static overloaded add method
  //v = v + u
  //v.add(u)
  public void add(Vector u){
    //add the coordinates of u, to the coordinates of the
    //vector that made the method call.
    if(u.coords.length!= this.coords.length){
      throw new IllegalArgumentException("different dimensions");
    }
    //the vectors have the same dimension, so we can add them
    //adding the elements of the array of u to the elements of the
    //array of the vector that called this method
    for(int i=0; i<this.coords.length; i++){
      this.coords[i] = this.coords[i] + u.coords[i];
    }
  }
  
  //method to compute magnitude/length of a vector
  //square root of the sum of the squares of the coords
  //s.length() 
  private void computeMagnitude(){
    double sum = 0;
    for(int i=0; i<this.coords.length; i++){
      sum = sum + Math.pow(this.coords[i], 2);
    }
    this.magnitude = Math.sqrt(sum);
  }
  
  //finds the index of the Vector with the largest magnitude
  public static int indexOfLargestMagnitude(Vector[] vectors){
    //find the Vector with the largest magnitude
    double max = vectors[0].magnitude;
    int index = 0;
    for(int i=1; i<vectors.length; i++){
      if(max<vectors[i].magnitude){
        max = vectors[i].magnitude;
        index = i;
      }
    }
    return index;
  }
    
    
}