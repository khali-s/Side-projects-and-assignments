import java.util.*;

public class Exp {

  public static char[] digits = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
  public static String[] ops = {"[", "]", "(" , ")", "++", "--", "+", "-", "*", "/"};


  public static Stack <String> parse( String val){
  
    StringBuilder parser = new StringBuilder();
    parser.append(val) ;
    Stack<String> exp = new Stack<String>();
    //int counter = 0;
    while(parser.length() != 0){
      if(val.charAt(0) ==' '){
        parser.deleteCharAt(0);
      }
      else if(parser.charAt(0) =='('){
        parser.deleteCharAt(0);
        exp.push(ops[2]);
      
      }
      else if(parser.charAt(0) ==')'){
        parser.deleteCharAt(0);
        exp.push(ops[3]);
      
      }
      else if(parser.charAt(0) =='['){
        parser.deleteCharAt(0);
        exp.push(ops[0]);
      
      }
      else if(parser.charAt(0) ==']'){
        parser.deleteCharAt(0);
        exp.push(ops[1]);
      
      }
      else if(parser.charAt(0) =='*'){
        parser.deleteCharAt(0);
        exp.push(ops[8]);
      
      }
      else if(parser.charAt(0) =='/'){
        parser.deleteCharAt(0);
        exp.push(ops[9]);
      }
      else{
        int counter = 0;
        if(parser.charAt(0) =='+'){
          if(parser.charAt(1) =='+'){
            parser.deleteCharAt(0);
            parser.deleteCharAt(0);
            exp.push(ops[4]);
          }
          else{
             parser.deleteCharAt(0);
             exp.push(ops[6]);
          }
          
        }
        else if(parser.charAt(0) =='-'){
          if(parser.charAt(1) =='-'){
            parser.deleteCharAt(0);
            parser.deleteCharAt(0);
            exp.push(ops[5]);
          }
          else{
             parser.deleteCharAt(0);
             exp.push(ops[7]);
          }
          
        }
        else if( ops.contains(parser.charAt(0))){
        StringBuilder digitParser = new StringBuilder();
        count =1;
        while( ops)
        }
             
        
      }
    }
    
    
    return exp;
  
  }


}