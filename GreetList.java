import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
public class GreetList implements Iterable<HelloWorldAnonymousClasses> {


  private List<HelloWorldAnonymousClasses> iterGreet = new ArrayList<HelloWorldAnonymousClasses>(){{
  
    
    add(new HelloWorldAnonymousClasses());
    add(new HelloWorldAnonymousClasses());
    add(new HelloWorldAnonymousClasses());
    add(new HelloWorldAnonymousClasses());
    add(new HelloWorldAnonymousClasses());
    add(new HelloWorldAnonymousClasses());
    
    
    
    

  
  
  }};
  
  
  public Iterator<HelloWorldAnonymousClasses> iterator() {
    Iterator<HelloWorldAnonymousClasses> iter = iterGreet.iterator();
      return iter; 
  
  }
 




}