public class metroTester{


  public static void main(String[] args){ //test the addFirst Element
  
  metroStation a = new metroStation("atwater");
   metroStation b = new metroStation("guy-concordia");
    metroStation c = new metroStation("peel");
  metroLine green = new metroLine();
  green.addFirst(c);
  System.out.println( "the metro station is : " + green.head.name );
  green.addFirst(b);
   System.out.println( "the metro station is : " + green.head.name );
  green.addFirst(a);
  System.out.println( "the metro station is : " + green.head.name );
  
  
  }



}